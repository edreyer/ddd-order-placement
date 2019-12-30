package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.simpletypes.String50;
import com.liquidsoftware.order.domain.simpletypes.UsStateCode;
import com.liquidsoftware.order.domain.simpletypes.ZipCode;
import io.vavr.control.Option;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface Address {

    interface Cases<R> {
        R Address(
            String50 addressLine1,
            Option<String50> addressLine2,
            Option<String50> addressLine3,
            Option<String50> addressLine4,
            String50 city,
            ZipCode zipCode,
            UsStateCode state,
            String50 country
        );
    }

    <R> R match(Cases<R> cases);

}
