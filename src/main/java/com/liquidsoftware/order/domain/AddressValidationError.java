package com.liquidsoftware.order.domain;

import org.derive4j.Data;

@Data
public interface AddressValidationError {

    interface Cases<R> {
        R invalidFormat();
        R addressNotFound();
    }

    <R> R match(Cases<R> cases);

}
