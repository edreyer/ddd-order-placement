package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.simpletypes.ConstrainedTypes;
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
public abstract class EmailAddress {

    EmailAddress() {}

    public abstract <R> R match(@FieldNames("email") Function<String, R> email);

    @ExportAsPublic
    static Either<ValidationError, EmailAddress> create(String email) {
        return createLike("email", email, Pattern.compile(".+@.+"))
            .map(validEmail -> EmailAddresses.email0(validEmail));
    }

}
