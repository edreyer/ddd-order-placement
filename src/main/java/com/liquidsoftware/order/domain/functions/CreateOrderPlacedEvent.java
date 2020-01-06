package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.PricedOrder;
import com.liquidsoftware.order.domain.PricedOrders;
import com.liquidsoftware.order.domain.publictypes.OrderPlaced;
import com.liquidsoftware.order.domain.publictypes.OrderPlaceds;
import io.vavr.Function1;

public interface CreateOrderPlacedEvent extends Function1<PricedOrder, OrderPlaced> {

    CreateOrderPlacedEvent createOrderPlacedEvent = pricedOrder ->
        OrderPlaceds.orderPlaced(
            PricedOrders.getOrderId(pricedOrder),
            PricedOrders.getCustomerInfo(pricedOrder),
            PricedOrders.getShippingAddress(pricedOrder),
            PricedOrders.getBillingAddress(pricedOrder),
            PricedOrders.getAmountToBill(pricedOrder),
            PricedOrders.getLines(pricedOrder)
        );
}
