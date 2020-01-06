package com.liquidsoftware.order.domain.simpletypes;

import com.liquidsoftware.order.domain.ValidationError;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.regex.Pattern;

import static com.liquidsoftware.order.domain.ValidationErrors.validationError;
import static java.lang.String.format;

public final class ConstrainedTypes {

    private ConstrainedTypes() {}

    public static Either<ValidationError, String> createString(String fieldName, String str, int maxLen) {
        if (str == null || str.isBlank()) {
            return Either.left(
                validationError(format("%s: must not be null or empty", fieldName))
            );
        } else if (str.length() > maxLen) {
            return Either.left(
                validationError(format("%s: must not have more than %d characters", fieldName, maxLen))
            );
        }
        return Either.right(str);
    }

    public static Option<String> createStringOption(String fieldName, String str, int maxLen) {
        return createString(fieldName, str, maxLen).toOption();
    }

    public static Either<ValidationError, Integer> createInt(String fieldName, int num, int min, int max) {
        if (num < min) {
            return Either.left(
                validationError(format("%s: must be greater than %d", fieldName, min))
            );
        } else if (num > max) {
            return Either.left(
                validationError(format("%s: must be less than %d", fieldName, max))
            );
        }
        return Either.right(num);
    }

    public static Either<ValidationError, Double> createDouble(String fieldName, double num, double min, double max) {
        if (num < min) {
            return Either.left(
                validationError(format("%s: must be greater than %f", fieldName, min))
            );
        } else if (num > max) {
            return Either.left(
                validationError(format("%s: must be less than %f", fieldName, max))
            );
        }
        return Either.right(num);
    }

    public static Either<ValidationError, String> createLike(String fieldName, String str, Pattern pattern) {
        if (str == null || str.isBlank()) {
            return Either.left(
                validationError(format("%s: must not be null or empty", fieldName))
            );
        } else if (pattern.matcher(str).matches()) {
            return Either.right(str);
        }
        return Either.left(
            validationError(format("%s: '%s' must match pattern '%s", fieldName, str, pattern.toString()))
        );
    }
}
