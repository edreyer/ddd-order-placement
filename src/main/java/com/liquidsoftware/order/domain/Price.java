package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.simpletypes.ValidationError;
import io.vavr.control.Either;
import org.derive4j.ArgOption;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import java.util.function.Function;

import static com.liquidsoftware.order.domain.simpletypes.ConstrainedTypes.createDouble;

@Data(
    value = @Derive(withVisibility = Visibility.Smart),
    arguments = ArgOption.checkedNotNull
)
public abstract class Price {

    Price() {}

    public abstract <R> R match(@FieldNames("price") Function<Double, R> price);

    @ExportAsPublic
    static Either<ValidationError, Price> create(double price) {
        return createDouble("price", price, 0, 1000)
            .map(p -> Prices.price0(p));
    }

    @ExportAsPublic
    static Price unsafeCreate(double price) {
        return Price.create(price).get();
    }

    public Either<ValidationError, Price> multiply(double p) {
        return Price.create(Prices.getPrice(this) * p);
    }

    public Either<ValidationError, Price> plus(Price p) {
        return Price.create(Prices.getPrice(this) + Prices.getPrice(p));
    }

}
