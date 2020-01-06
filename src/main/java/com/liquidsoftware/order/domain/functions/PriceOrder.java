package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.BillingAmount;
import com.liquidsoftware.order.domain.BillingAmounts;
import com.liquidsoftware.order.domain.KilogramQuantities;
import com.liquidsoftware.order.domain.OrderQuantities;
import com.liquidsoftware.order.domain.Price;
import com.liquidsoftware.order.domain.PricedOrders;
import com.liquidsoftware.order.domain.UnitQuantities;
import com.liquidsoftware.order.domain.ValidatedOrderLines;
import com.liquidsoftware.order.domain.ValidatedOrders;
import com.liquidsoftware.order.domain.ValidationError;
import com.liquidsoftware.order.domain.PricedOrder;
import com.liquidsoftware.order.domain.ValidatedOrder;
import com.liquidsoftware.order.domain.ValidatedOrderLine;
import com.liquidsoftware.order.domain.publictypes.PricedOrderLine;
import com.liquidsoftware.order.domain.publictypes.PricedOrderLines;
import com.liquidsoftware.order.domain.publictypes.PricingError;
import com.liquidsoftware.order.domain.publictypes.PricingErrors;
import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.control.Either;

import static com.liquidsoftware.order.domain.ValidationErrors.getValidationError;

public interface PriceOrder
    extends Function2<GetProductPrice, ValidatedOrder, Either<PricingError, PricedOrder>> {

    PriceOrder priceOrder = (getProductPrice, validatedOrder) -> {

        Either<PricingError, List<PricedOrderLine>> linesE =
            Either.sequenceRight(ValidatedOrders.getLines(validatedOrder)
                .map(line -> toPricedOrderLine(getProductPrice, line)))
            .map(seq -> seq.toList())
            .mapLeft(ve -> PricingErrors.pricingError(getValidationError(ve)));

        Either<PricingError, BillingAmount> amountToBillE =
            linesE.map(lines ->
                lines.map(line -> PricedOrderLines.getLinePrice(line))
            ).flatMap(prices ->
                BillingAmounts.sumPrices(prices)
                    .mapLeft(ve -> PricingErrors.pricingError(getValidationError(ve)))
            );

        return linesE.flatMap(lines ->
            amountToBillE.map(amountToBill ->
                PricedOrders.pricedOrder(
                    ValidatedOrders.getOrderId(validatedOrder),
                    ValidatedOrders.getCustomerInfo(validatedOrder),
                    ValidatedOrders.getShippingAddress(validatedOrder),
                    ValidatedOrders.getBillingAddress(validatedOrder),
                    amountToBill,
                    lines
                )
            )
        );
    };

    private static Either<ValidationError, PricedOrderLine> toPricedOrderLine(
        GetProductPrice getProductPrice, ValidatedOrderLine validatedOrderLine) {

        Double qty =
            OrderQuantities.caseOf(ValidatedOrderLines.getQuantity(validatedOrderLine))
            .UnitQuantity(uq -> Double.valueOf(UnitQuantities.getUnitQuantity(uq)))
            .KgQuantity(kq -> KilogramQuantities.getKgQuantity(kq));

        Price price =
            getProductPrice.apply(ValidatedOrderLines.getProductCode(validatedOrderLine));

        Either<ValidationError, Price> linePriceE = price.multiply(qty);

        return linePriceE.map(linePrice -> PricedOrderLines.pricedOrderLine(
            ValidatedOrderLines.getOrderLineId(validatedOrderLine),
            ValidatedOrderLines.getProductCode(validatedOrderLine),
            ValidatedOrderLines.getQuantity(validatedOrderLine),
            linePrice
        ));
    }
}
