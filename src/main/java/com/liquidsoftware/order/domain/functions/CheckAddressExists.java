package com.liquidsoftware.order.domain.functions;

import com.liquidsoftware.order.domain.internaltypes.AddressValidationError;
import com.liquidsoftware.order.domain.internaltypes.CheckedAddress;
import com.liquidsoftware.order.domain.publictypes.UnvalidatedAddress;
import io.vavr.Function1;
import io.vavr.control.Either;

public interface CheckAddressExists
    extends Function1<UnvalidatedAddress, Either<AddressValidationError, CheckedAddress>> {}
