package com.liquidsoftware.order.domain.publictypes;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface UnvalidatedOrderLine {

    interface Cases<R> {
        R unvalidatedOrderLine(
            String orderLineId,
            String productCode,
            double quantity
        );
    }

    <R> R match(Cases<R> cases);

}
