package com.liquidsoftware.order.domain.simpletypes;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface ValidationError {

    interface Cases<R> {
        R validationError(String validationError);
    }

    <R> R match(Cases<R> cases);

}
