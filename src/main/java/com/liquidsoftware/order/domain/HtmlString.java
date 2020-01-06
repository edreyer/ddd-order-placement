package com.liquidsoftware.order.domain;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface HtmlString {

    interface Cases<R> {
        R htmlString(String html);
    }

    <R> R match(Cases<R> cases);
}
