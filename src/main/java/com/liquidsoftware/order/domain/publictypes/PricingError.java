package com.liquidsoftware.order.domain.publictypes;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface PricingError {

    interface Cases<R> {
        R pricingError(String pricingError);
    }

    <R> R match(Cases<R> cases);
}
