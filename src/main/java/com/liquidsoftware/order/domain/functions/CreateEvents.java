package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.PricedOrder;
import com.liquidsoftware.order.domain.publictypes.OrderAcknowledgmentSent;
import com.liquidsoftware.order.domain.publictypes.PlaceOrderEvent;
import com.liquidsoftware.order.domain.publictypes.PlaceOrderEvents;
import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.control.Option;

import static com.liquidsoftware.order.domain.functions.CreateBillingEvent.createBillingEvent;
import static com.liquidsoftware.order.domain.functions.CreateOrderPlacedEvent.createOrderPlacedEvent;

public interface CreateEvents
    extends Function2<
        PricedOrder,
        Option<OrderAcknowledgmentSent>,
        List<PlaceOrderEvent>> {

    CreateEvents createEvents = (pricedOrder, orderAcknowledgementsOpt) -> {

        List<PlaceOrderEvent> acknowledgmentEvents = ListOfOption.apply(
            orderAcknowledgementsOpt.map(ack -> PlaceOrderEvents.acknowledgementSent(ack))
        );

        List<PlaceOrderEvent> orderPlacedEvents = createOrderPlacedEvent
            .andThen(PlaceOrderEvents::orderPlaced)
            .andThen(List::of)
            .apply(pricedOrder);

        List<PlaceOrderEvent> billingEvents = ListOfOption.apply(
            createBillingEvent.apply(pricedOrder)
                .map(bop -> PlaceOrderEvents.billableOrderPlaced(bop))
        );

        return List
            .ofAll(acknowledgmentEvents)
            .appendAll(orderPlacedEvents)
            .appendAll(billingEvents);
    };

}
