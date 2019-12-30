package com.liquidsoftware.order.domain.publictypes;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface UnvalidatedCustomerInfo {

    interface Cases<R> {
        R unvalidatedCustomerInfo(
            String firstName,
            String lastName,
            String emailAddress,
            String vipStatus
        );
    }

    <R> R match(Cases<R> cases);

}
