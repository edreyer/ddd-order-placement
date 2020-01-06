package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.AddressValidationError;
import com.liquidsoftware.order.domain.CheckedAddress;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedAddress;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedAddresses;
import io.vavr.Function1;
import io.vavr.control.Either;

import static com.liquidsoftware.order.domain.CheckedAddresses.checkedAddress;

public interface CheckAddressExists
    extends Function1<UnvalidatedAddress, Either<AddressValidationError, CheckedAddress>> {

    CheckAddressExists checkAddressExists =
        // just wrap the unvalidated in a checked (never errors)
        unvalidatedAddress ->
            Either.right(checkedAddress(
                UnvalidatedAddresses.getAddressLine1(unvalidatedAddress),
                UnvalidatedAddresses.getAddressLine2(unvalidatedAddress),
                UnvalidatedAddresses.getAddressLine3(unvalidatedAddress),
                UnvalidatedAddresses.getAddressLine4(unvalidatedAddress),
                UnvalidatedAddresses.getCity(unvalidatedAddress),
                UnvalidatedAddresses.getZipCode(unvalidatedAddress)
            ));

}
