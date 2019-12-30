package com.liquidsoftware.order.domain.internaltypes;

import com.liquidsoftware.order.domain.publictypes.UnvalidatedAddress;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface CheckedAddress {

    interface Cases<R> {
        R checkedAddress(UnvalidatedAddress checkedAddress);
    }

    default public UnvalidatedAddress toUnvalidated() {
        return CheckedAddresses.getCheckedAddress(this);
    }

    <R> R match(Cases<R> cases);

}
