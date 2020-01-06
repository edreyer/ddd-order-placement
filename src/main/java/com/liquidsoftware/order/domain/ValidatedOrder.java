package com.liquidsoftware.order.domain;

import io.vavr.collection.List;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface ValidatedOrder {

    interface Cases<R> {
        R validatedOrder(
            OrderId orderId,
            CustomerInfo customerInfo,
            Address shippingAddress,
            Address billingAddress,
            List<ValidatedOrderLine> lines
        );
    }

    <R> R match(Cases<R> cases);

}
