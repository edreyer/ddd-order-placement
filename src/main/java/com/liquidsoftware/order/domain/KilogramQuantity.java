package com.liquidsoftware.order.domain;

import io.vavr.control.Either;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import java.util.function.Function;

import static com.liquidsoftware.order.domain.simpletypes.ConstrainedTypes.createDouble;


@Data(@Derive(withVisibility = Visibility.Smart))
public abstract class KilogramQuantity {

    KilogramQuantity() {}

    public abstract <R> R match(@FieldNames("kgQuantity") Function<Double, R> kgQuantity);

    @ExportAsPublic
    static Either<ValidationError, KilogramQuantity> create(double kgQuantity) {
        return createDouble("kgQuantity", kgQuantity, 0.5, 100.0)
            .map(kq -> KilogramQuantities.kgQuantity0(kq));
    }

}
