package com.liquidsoftware.order.domain.publictypes;

import com.liquidsoftware.order.domain.OrderLineId;
import com.liquidsoftware.order.domain.OrderQuantity;
import com.liquidsoftware.order.domain.Price;
import com.liquidsoftware.order.domain.ProductCode;
import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface PricedOrderLine {

    interface Cases<R> {
        R pricedOrderLine(
            OrderLineId orderLineId,
            ProductCode productCode,
            OrderQuantity quantity,
            Price price
        );
    }

    <R> R match(Cases<R> cases);
}
