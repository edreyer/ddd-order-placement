package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.api.PlaceOrder;
import com.liquidsoftware.order.domain.publictypes.PlaceOrderErrors;
import io.vavr.Function5;
import io.vavr.concurrent.Future;

public interface PlaceOrderWorkflow extends Function5<
    CheckProductCodeExists,             // dependency
    CheckAddressExists,                 // dependency
    GetProductPrice,                    // dependency
    CreateOrderAcknowledgmentLetter,    // dependency
    SendOrderAcknowledgment,            // dependency
    PlaceOrder> {

    PlaceOrderWorkflow placeOrderWorkflow = (
        checkProductCodeExists,
        checkAddressExists,
        getProductPrice,
        createOrderAcknowledgmentLetter,
        sendOrderAcknowledgment) ->
            unvalidatedOrder ->
                Future.of(() -> {

                    var validatedOrderE = ValidateOrder.validateOrder
                        .apply(checkProductCodeExists, checkAddressExists, unvalidatedOrder)
                        .mapLeft(err -> PlaceOrderErrors.validation(err));

                    var pricedOrderE = validatedOrderE.flatMap(validatedOrder ->
                        PriceOrder.priceOrder
                            .apply(getProductPrice, validatedOrder)
                            .mapLeft(err -> PlaceOrderErrors.pricing(err))
                    );

                    var events = pricedOrderE.map(pricedOrder -> {
                        var acknowledgementOpt = AcknowledgeOrder.acknowledgeOrder.apply(
                            createOrderAcknowledgmentLetter, sendOrderAcknowledgment, pricedOrder);

                        return CreateEvents.createEvents.apply(pricedOrder, acknowledgementOpt);
                    });

                    return events;
                });

}
