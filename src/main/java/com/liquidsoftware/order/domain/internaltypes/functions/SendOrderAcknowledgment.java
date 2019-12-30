package com.liquidsoftware.order.domain.internaltypes.functions;

import com.liquidsoftware.order.domain.internaltypes.OrderAcknowledgement;
import com.liquidsoftware.order.domain.internaltypes.SendResult;
import io.vavr.Function1;
import io.vavr.concurrent.Future;

public interface SendOrderAcknowledgment
    extends Function1<OrderAcknowledgement, Future<SendResult>> { }
