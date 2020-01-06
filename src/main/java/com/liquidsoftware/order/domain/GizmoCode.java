package com.liquidsoftware.order.domain;

import io.vavr.control.Either;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import java.util.function.Function;
import java.util.regex.Pattern;

import static com.liquidsoftware.order.domain.simpletypes.ConstrainedTypes.createLike;

@Data(@Derive(withVisibility = Visibility.Smart))
public abstract class GizmoCode {

    GizmoCode() {}

    public abstract <R> R match(@FieldNames("gizmoCode") Function<String, R> gizmoCode);

    @ExportAsPublic
    static Either<ValidationError, GizmoCode> create(String gizmoCode) {
        return createLike("gizmoCode", gizmoCode, Pattern.compile("W\\d{4}"))
            .map(gc -> GizmoCodes.gizmoCode0(gc));
    }
}
