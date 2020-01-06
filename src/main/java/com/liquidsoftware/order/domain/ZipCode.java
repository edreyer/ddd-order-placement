package com.liquidsoftware.order.domain;

import java.util.function.Function;
import java.util.regex.Pattern;

import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import io.vavr.control.Either;

import static com.liquidsoftware.order.domain.simpletypes.ConstrainedTypes.createLike;

@Data(@Derive(withVisibility = Visibility.Smart))
public abstract class ZipCode {

    ZipCode() {}

    public abstract <R> R match(@FieldNames("zipCode") Function<String, R> zipCode);

    @ExportAsPublic
    static Either<ValidationError, ZipCode> create(String zip) {
        return createLike("zip", zip, Pattern.compile("\\d{5}"))
            .map(validZip -> ZipCodes.zipCode0(validZip));
    }

}
