package com.liquidsoftware.order.domain.internaltypes;

import org.derive4j.Data;

@Data
public interface SendResult {

    interface Cases<R> {
        R sent();
        R notSent();
    }

    <R> R match(Cases<R> cases);
}
