package com.liquidsoftware.order.domain.publictypes;

import io.vavr.collection.List;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface UnvalidatedOrder {

    interface Cases<R> {
        R unvalidatedOrder(
            String orderId,
            UnvalidatedCustomerInfo customerInfo,
            UnvalidatedAddress shippingAddress,
            UnvalidatedAddress billingAddress,
            List<UnvalidatedOrderLine> lines
        );
    }

    <R> R match(Cases<R> cases);

}
