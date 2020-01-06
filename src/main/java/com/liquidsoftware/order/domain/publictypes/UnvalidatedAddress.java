package com.liquidsoftware.order.domain.publictypes;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface UnvalidatedAddress {

    interface Cases<R> {
        R unvalidatedAddress(
            String addressLine1,
            String addressLine2,
            String addressLine3,
            String addressLine4,
            String city,
            String zipCode
        );
    }

    <R> R match(Cases<R> cases);

}
