package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.OrderLineId;
import com.liquidsoftware.order.domain.OrderQuantity;
import com.liquidsoftware.order.domain.ProductCode;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface ValidatedOrderLine {

    interface Cases<R> {
        R validatedOrderLine(
            OrderLineId orderLineId,
            ProductCode productCode,
            OrderQuantity quantity
        );
    }

    <R> R match(Cases<R> cases);

}
