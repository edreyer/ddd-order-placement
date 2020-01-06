package com.liquidsoftware.order.domain;

import io.vavr.control.Either;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.Visibility;

@Data(value = @Derive(withVisibility = Visibility.Smart))
public abstract class OrderQuantity {

    OrderQuantity() {}

    interface Cases<R> {
        R UnitQuantity(UnitQuantity unitQuantity);
        R KgQuantity(KilogramQuantity kilogramQuantity);
    }

    public abstract <R> R match(Cases<R> cases);

    @ExportAsPublic
    static Either<ValidationError, OrderQuantity> create(ProductCode pc, double quantity) {
        return ProductCodes.caseOf(pc)
            .Widget(it -> UnitQuantities.create((int)quantity)
                .map(uq -> OrderQuantities.UnitQuantity0(uq)))
            .Gizmo(it -> KilogramQuantities.create(quantity)
                .map(kq -> OrderQuantities.KgQuantity0(kq)));
    }

}
