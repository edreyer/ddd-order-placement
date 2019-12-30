package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.simpletypes.String50;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface PersonalName {

    interface Cases<R> {
        R PersonalName(
            String50 first,
            String50 last
        );
    }

    <R> R match(Cases<R> cases);

}
