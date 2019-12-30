package com.liquidsoftware.order.domain.publictypes;

import io.vavr.collection.List;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface UnvalidatedOrder {

    interface Cases<R> {
        R unvalidatedOrder(
            String orderId,
            UnvalidatedCustomerInfo unvalidatedCustomerInfo,
            UnvalidatedAddress shippingAddress,
            UnvalidatedAddress billingAddress,
            List<UnvalidatedOrderLine> lines,
            String promotionCode
        );
    }

    <R> R match(Cases<R> cases);

}
