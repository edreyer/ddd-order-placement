package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.CustomerInfos;
import com.liquidsoftware.order.domain.OrderAcknowledgements;
import com.liquidsoftware.order.domain.PricedOrder;
import com.liquidsoftware.order.domain.PricedOrders;
import com.liquidsoftware.order.domain.SendResults;
import com.liquidsoftware.order.domain.publictypes.OrderAcknowledgmentSent;
import com.liquidsoftware.order.domain.publictypes.OrderAcknowledgmentSents;
import io.vavr.Function3;
import io.vavr.control.Option;

public interface AcknowledgeOrder extends Function3<
    CreateOrderAcknowledgmentLetter,
    SendOrderAcknowledgment,
    PricedOrder,
    Option<OrderAcknowledgmentSent>> {

    AcknowledgeOrder acknowledgeOrder =
        (createOrderAcknowledgementLetter, sendOrderAcknowledgment, pricedOrder) -> {
            var letter =
                createOrderAcknowledgementLetter.apply(pricedOrder);

            var acknowledgment =
                OrderAcknowledgements.orderAcknowledgement(
                    CustomerInfos.getEmail(PricedOrders.getCustomerInfo(pricedOrder)),
                    letter
                );

            return SendResults.caseOf(sendOrderAcknowledgment.apply(acknowledgment))
                .sent(() ->
                    Option.of(OrderAcknowledgmentSents.orderAcknowledgmentSent(
                        PricedOrders.getOrderId(pricedOrder),
                        CustomerInfos.getEmail(PricedOrders.getCustomerInfo(pricedOrder)))
                    )
                ).notSent(() ->
                    Option.none()
                );
        };

}
