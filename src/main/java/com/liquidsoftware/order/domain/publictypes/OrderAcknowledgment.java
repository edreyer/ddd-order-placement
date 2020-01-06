package com.liquidsoftware.order.domain.publictypes;

import org.derive4j.ArgOption;
import org.derive4j.Data;

import com.liquidsoftware.order.domain.EmailAddress;
import com.liquidsoftware.order.domain.HtmlString;
import com.liquidsoftware.order.domain.OrderId;

@Data(arguments = ArgOption.checkedNotNull)
public interface OrderAcknowledgment {

    interface Cases<R> {
        R orderAcknowledgment(EmailAddress email, HtmlString letter);
    }

    <R> R match(Cases<R> cases);

}
