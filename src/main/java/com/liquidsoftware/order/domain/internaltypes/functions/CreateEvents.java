package com.liquidsoftware.order.domain.internaltypes.functions;

import com.liquidsoftware.order.domain.internaltypes.OrderAcknowledgement;
import com.liquidsoftware.order.domain.internaltypes.PricedOrder;
import com.liquidsoftware.order.domain.publictypes.PlaceOrderEvent;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface CreateEvents
    extends Function1<PricedOrder,
            Function1<Option<OrderAcknowledgement>, List<PlaceOrderEvent>>> {
}
