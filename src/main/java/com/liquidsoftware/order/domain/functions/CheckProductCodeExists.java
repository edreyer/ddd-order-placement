package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.ProductCode;
import io.vavr.Function1;

public interface CheckProductCodeExists
    extends Function1<ProductCode, Boolean> {

    CheckProductCodeExists checkProductCodeExists =
        // dummy implementation
        productCode -> true;

}
