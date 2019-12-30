package com.liquidsoftware.order.domain.internaltypes;

import com.liquidsoftware.order.domain.Address;
import com.liquidsoftware.order.domain.BillingAmount;
import com.liquidsoftware.order.domain.CustomerInfo;
import com.liquidsoftware.order.domain.OrderId;
import com.liquidsoftware.order.domain.publictypes.PricedOrderLine;
import io.vavr.collection.List;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface PricedOrder {

    interface Cases<R> {
        R pricedOrder(
            OrderId orderId,
            CustomerInfo customerInfo,
            Address shippingAddress,
            Address billingAddress,
            BillingAmount amountToBill,
            List<PricedOrderLine> lines,
            PricingMethod pricingMethod
        );
    }

    <R> R match(Cases<R> cases);

}
