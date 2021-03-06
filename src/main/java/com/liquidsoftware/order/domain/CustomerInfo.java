package com.liquidsoftware.order.domain;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface CustomerInfo {

    interface Cases<R> {
        R CustomerInfo(
            PersonalName name,
            EmailAddress email
        );
    }

    <R> R match(Cases<R> cases);
}
