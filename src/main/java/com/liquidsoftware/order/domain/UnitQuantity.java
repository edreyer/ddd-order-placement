package com.liquidsoftware.order.domain;

import io.vavr.control.Either;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.FieldNames;
import org.derive4j.Visibility;

import java.util.function.Function;

import static com.liquidsoftware.order.domain.simpletypes.ConstrainedTypes.createInt;


@Data(@Derive(withVisibility = Visibility.Smart))
public abstract class UnitQuantity {

    UnitQuantity() {}

    public abstract <R> R match(@FieldNames("unitQuantity") Function<Integer, R> unitQuantity);

    @ExportAsPublic
    static Either<ValidationError, UnitQuantity> create(int unitQuantity) {
        return createInt("unitQuantity", unitQuantity, 1, 1000)
            .map(uq -> UnitQuantities.unitQuantity0(uq));
    }

}
