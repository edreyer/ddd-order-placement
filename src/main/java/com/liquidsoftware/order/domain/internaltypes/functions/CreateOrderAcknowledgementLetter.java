package com.liquidsoftware.order.domain.internaltypes.functions;

import com.liquidsoftware.order.domain.internaltypes.HtmlString;
import com.liquidsoftware.order.domain.internaltypes.PricedOrder;
import io.vavr.Function1;

public interface CreateOrderAcknowledgementLetter
    extends Function1<PricedOrder, HtmlString> { }
