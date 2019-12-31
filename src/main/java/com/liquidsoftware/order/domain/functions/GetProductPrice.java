package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.Price;
import com.liquidsoftware.order.domain.ProductCode;
import io.vavr.Function1;

public interface GetProductPrice
    extends Function1<ProductCode, Price> {}
