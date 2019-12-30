package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.simpletypes.ValidationError;
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
public abstract class WidgetCode {

    WidgetCode() {}

    public abstract <R> R match(@FieldNames("widgetCode") Function<String, R> widgetCode);

    @ExportAsPublic
    static Either<ValidationError, WidgetCode> create(String widgetCode) {
        return createLike("widgetCode", widgetCode, Pattern.compile("W\\d{4}"))
            .map(wc -> WidgetCodes.widgetCode0(wc));
    }
}
