package com.liquidsoftware.order.domain;

import org.derive4j.Data;

@Data
public interface PricingMethod {

    interface Cases<R> {
        R standard();
    }

    <R> R match(Cases<R> cases);

}
