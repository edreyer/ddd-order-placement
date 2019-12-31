package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.internaltypes.OrderAcknowledgement;
import com.liquidsoftware.order.domain.internaltypes.SendResult;
import io.vavr.Function1;

public interface SendOrderAcknowledgment
    extends Function1<OrderAcknowledgement, SendResult> { }
