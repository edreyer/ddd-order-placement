package com.liquidsoftware.order.domain.publictypes;

import com.liquidsoftware.order.domain.Address;
import com.liquidsoftware.order.domain.BillingAmount;
import com.liquidsoftware.order.domain.CustomerInfo;
import com.liquidsoftware.order.domain.OrderId;
import io.vavr.collection.List;

import org.derive4j.ArgOption;
import org.derive4j.Data;

/**
 * Java's way of type aliasing is via extension
 */
@Data(arguments = ArgOption.checkedNotNull)
public interface OrderPlaced {

    interface Cases<R> {
        R orderPlaced(
            OrderId orderId,
            CustomerInfo customerInfo,
            Address shippingAddress,
            Address billingAddress,
            BillingAmount amountToBill,
            List<PricedOrderLine> lines
        );
    }

    <R> R match(Cases<R> cases);

}
