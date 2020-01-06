package com.liquidsoftware.order.domain.publictypes;

import org.derive4j.Data;

@Data
public interface PlaceOrderEvent {

    interface Cases<R> {
        R orderPlaced(OrderPlaced OrderPlaced);
        R billableOrderPlaced(BillableOrderPlaced billableOrderPlaced);
        R acknowledgementSent(OrderAcknowledgmentSent orderAcknowledgmentSent);
    }

    <R> R match(Cases<R> cases);
}
