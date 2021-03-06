package com.liquidsoftware.order.domain;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface OrderAcknowledgement {

    interface Cases<R> {
        R orderAcknowledgement(
            EmailAddress email,
            HtmlString letter
        );
    }

    <R> R match(Cases<R> cases);

}
