package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.HtmlString;
import com.liquidsoftware.order.domain.PricedOrder;
import io.vavr.Function1;

public interface CreateOrderAcknowledgmentLetter
    extends Function1<PricedOrder, HtmlString> { }
