package com.liquidsoftware.order.domain.publictypes;

import com.liquidsoftware.order.domain.Address;
import com.liquidsoftware.order.domain.BillingAmount;
import com.liquidsoftware.order.domain.OrderId;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface BillableOrderPlaced {

    interface Cases<R> {
        R billableOrderPlaced(
            OrderId orderId,
            Address billingAddress,
            BillingAmount amountToBill
        );
    }

    <R> R match(Cases<R> cases);

}
