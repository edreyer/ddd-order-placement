package com.liquidsoftware.order.domain.internaltypes.functions;

import com.liquidsoftware.order.domain.internaltypes.PricedOrder;
import com.liquidsoftware.order.domain.publictypes.OrderAcknowledgmentSent;
import io.vavr.Function1;
import io.vavr.concurrent.Future;

import java.util.Optional;

public interface AcknowledgeOrder
    extends Function1<CreateOrderAcknowledgementLetter,
            Function1<SendOrderAcknowledgment,
            Function1<PricedOrder, Future<Optional<OrderAcknowledgmentSent>>>>> {
}
