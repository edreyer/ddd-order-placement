package com.liquidsoftware.order.domain.api;

import com.liquidsoftware.order.domain.publictypes.PlaceOrderError;
import com.liquidsoftware.order.domain.publictypes.PlaceOrderEvent;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedOrder;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;

// The Public API
public interface PlaceOrder
    extends Function1<UnvalidatedOrder, Future<Either<PlaceOrderError, List<PlaceOrderEvent>>>> {
}
