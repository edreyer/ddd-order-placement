package com.liquidsoftware.order.domain.publictypes;

import com.liquidsoftware.order.domain.OrderId;
import com.liquidsoftware.order.domain.simpletypes.EmailAddress;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface OrderAcknowledgmentSent {

    interface Cases<R> {
        R orderAcknowledgmentSent(
            OrderId orderId,
            EmailAddress email
        );
    }

    <R> R match(Cases<R> cases);

}
