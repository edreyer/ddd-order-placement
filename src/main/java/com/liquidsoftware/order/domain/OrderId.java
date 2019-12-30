package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.simpletypes.String50s;
import com.liquidsoftware.order.domain.simpletypes.ValidationError;
import io.vavr.control.Either;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import java.util.function.Function;

@Data(@Derive(withVisibility = Visibility.Smart))
public abstract class OrderId {

    OrderId() {}

    public abstract <R> R match(@FieldNames("orderId") Function<String, R> orderId);

    @ExportAsPublic
    static Either<ValidationError, OrderId> create(String orderId) {
        return String50s
            .create(orderId)
            .map(str50 ->
                OrderIds.orderId0(String50s.getStr50(str50))
            );
    }

}
