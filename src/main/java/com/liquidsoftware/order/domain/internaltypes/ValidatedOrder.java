package com.liquidsoftware.order.domain.internaltypes;

import com.liquidsoftware.order.domain.Address;
import com.liquidsoftware.order.domain.CustomerInfo;
import com.liquidsoftware.order.domain.OrderId;
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
            List<ValidatedOrderLine> lines,
            PricingMethod pricingMethod
        );
    }

    <R> R match(Cases<R> cases);

}
