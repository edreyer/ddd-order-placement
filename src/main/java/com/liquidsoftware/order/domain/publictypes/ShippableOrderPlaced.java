package com.liquidsoftware.order.domain.publictypes;

import com.liquidsoftware.order.domain.Address;
import com.liquidsoftware.order.domain.OrderId;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface ShippableOrderPlaced {

    interface Cases<R> {
        R shippableOrderPlaced(
            OrderId orderId,
            Address shippingAddress
            //List<ShippableOrderLine> shipmentLines,
            //PdfAttachment pdf;
        );
    }

    <R> R match(Cases<R> cases);

}
