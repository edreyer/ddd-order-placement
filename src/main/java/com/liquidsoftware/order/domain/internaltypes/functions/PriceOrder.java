package com.liquidsoftware.order.domain.internaltypes.functions;

import com.liquidsoftware.order.domain.OrderQuantities;
import com.liquidsoftware.order.domain.internaltypes.PricedOrder;
import com.liquidsoftware.order.domain.internaltypes.ValidatedOrder;
import com.liquidsoftware.order.domain.internaltypes.ValidatedOrderLine;
import com.liquidsoftware.order.domain.internaltypes.ValidatedOrderLines;
import com.liquidsoftware.order.domain.internaltypes.ValidatedOrders;
import com.liquidsoftware.order.domain.publictypes.PricedOrderLine;
import com.liquidsoftware.order.domain.publictypes.PricingError;
import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.control.Either;

public interface PriceOrder
    extends Function2<GetProductPrice, ValidatedOrder, Either<PricingError, PricedOrder>> {

    PriceOrder priceOrder = (getProductPrice, validatedOrder) -> {

        List<PricedOrderLine> lines = ValidatedOrders.getLines(validatedOrder)
            .map(line -> toPricedOrderLine(getProductPrice, line));


    };

    private static PricedOrderLine toPricedOrderLine(GetProductPrice getProductPrice, ValidatedOrderLine validatedOrderLine) {
        ValidatedOrderLines.getQuantity(validatedOrderLine).match(OrderQuantities.cases()
            .KgQuantity(kg -> kg.))
    }
}
