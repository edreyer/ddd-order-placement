package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.simpletypes.String50s;
import io.vavr.control.Either;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import java.util.function.Function;

@Data(@Derive(withVisibility = Visibility.Smart))
public abstract class OrderLineId {

    OrderLineId() {}

    public abstract <R> R match(@FieldNames("orderLineId") Function<String, R> orderLineId);

    @ExportAsPublic
    static Either<ValidationError, OrderLineId> create(String orderLineId) {
        return String50s
            .create(orderLineId)
            .map(str50 ->
                OrderLineIds.orderLineId0(String50s.getStr50(str50))
            );
    }

}
