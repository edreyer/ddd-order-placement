package com.liquidsoftware.order.domain.simpletypes;

import io.vavr.control.Either;
import io.vavr.control.Option;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import java.util.function.Function;

@Data(@Derive(withVisibility = Visibility.Smart))
public abstract class String50 {

    String50() {}

    public abstract <R> R match(@FieldNames("str50") Function<String, R> str50);

    @ExportAsPublic
    static Either<ValidationError, String50> create(String str50) {
        return ConstrainedTypes.createString("string50", str50, 50)
            .map(str -> String50s.str500(str50));
    }

    @ExportAsPublic
    static Option<String50> createOption(String str50) {
        return ConstrainedTypes.createStringOption("string50", str50, 50)
            .map(str -> String50s.str500(str50));
    }

}
