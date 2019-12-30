package com.liquidsoftware.order.domain.internaltypes;

import com.liquidsoftware.order.domain.simpletypes.PromotionCode;
import com.liquidsoftware.order.domain.simpletypes.PromotionCodes;
import io.vavr.control.Option;
import org.derive4j.ArgOption;
import org.derive4j.Data;
import org.derive4j.ExportAsPublic;

@Data(arguments = ArgOption.checkedNotNull)
public interface PricingMethod {

    interface Cases<R> {
        R standard();
        R promotion(PromotionCode promotionCode);
    }

    <R> R match(Cases<R> cases);

    @ExportAsPublic
    static PricingMethod create(Option<String> promotionCode) {
        return promotionCode.map(pc ->
            PricingMethods.promotion(PromotionCodes.promotionCode(pc))
        ).getOrElse(PricingMethods.standard());
    }
}
