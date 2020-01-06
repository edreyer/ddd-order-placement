package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.publictypes.UnvalidatedAddress;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface CheckedAddress {

    interface Cases<R> {
        R checkedAddress(
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
