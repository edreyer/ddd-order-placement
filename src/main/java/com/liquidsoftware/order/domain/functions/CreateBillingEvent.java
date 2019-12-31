package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.BillingAmounts;
import com.liquidsoftware.order.domain.internaltypes.PricedOrder;
import com.liquidsoftware.order.domain.internaltypes.PricedOrders;
import com.liquidsoftware.order.domain.publictypes.BillableOrderPlaced;
import com.liquidsoftware.order.domain.publictypes.BillableOrderPlaceds;
import io.vavr.Function1;
import io.vavr.control.Option;

public interface CreateBillingEvent extends Function1<PricedOrder, Option<BillableOrderPlaced>> {

    CreateBillingEvent createBillingEvent = pricedOrder -> {
        var billingAmount = BillingAmounts.getBillingAmount(PricedOrders.getAmountToBill(pricedOrder));
        return (billingAmount > 0)
            ? Option.of(BillableOrderPlaceds.billableOrderPlaced(
                PricedOrders.getOrderId(pricedOrder),
                PricedOrders.getBillingAddress(pricedOrder),
                PricedOrders.getAmountToBill(pricedOrder)
            ))
            : Option.none();
    };

}

