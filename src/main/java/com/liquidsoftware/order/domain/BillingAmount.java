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
public abstract class BillingAmount {

    BillingAmount() {}

    public abstract <R> R match(@FieldNames("billingAmount") Function<Double, R> billingAmount);

    @ExportAsPublic
    static Either<ValidationError, BillingAmount> create(double billingAmount) {
        return createDouble("billingAmount", billingAmount, 0, 10000)
            .map(ba -> BillingAmounts.billingAmount0(ba));
    }

}
