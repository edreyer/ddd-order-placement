package com.liquidsoftware.order.domain.simpletypes;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface PromotionCode {

    interface Cases<R> {
        R promotionCode(String promotionCode);
    }

    <R> R match(Cases<R> cases);
}
