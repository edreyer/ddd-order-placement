package com.liquidsoftware.order.domain.simpletypes;

import io.vavr.control.Either;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import java.util.function.Function;
import java.util.regex.Pattern;

@Data(@Derive(withVisibility = Visibility.Smart))
public abstract class ZipCode {

    ZipCode() {}

    public abstract <R> R match(@FieldNames("zipCode") Function<String, R> zipCode);

    @ExportAsPublic
    static Either<ValidationError, ZipCode> create(String zip) {
        return ConstrainedTypes.createLike("zip", zip, Pattern.compile("\\d{5}"))
            .map(validZip -> ZipCodes.zipCode0(validZip));
    }

}
