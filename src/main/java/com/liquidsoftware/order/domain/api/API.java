package com.liquidsoftware.order.domain.api;

import com.liquidsoftware.order.domain.internaltypes.functions.CheckAddressExists;
import com.liquidsoftware.order.domain.internaltypes.functions.CheckProductCodeExists;
import io.vavr.control.Either;

import static com.liquidsoftware.order.domain.internaltypes.CheckedAddresses.checkedAddress;

public final class API {

    private API() {}

    public static final CheckProductCodeExists checkProductCodeExists =
        // dummy implementation
        productCode -> true;


    public static final CheckAddressExists checkAddressExists =
        // just wrap the unvalidated in a checked (never errors)
        unvalidatedAddress ->
            Either.right(checkedAddress(unvalidatedAddress));


}
