package com.liquidsoftware.order.domain;

import io.vavr.control.Either;
import org.derive4j.ArgOption;
import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.ExportAsPublic;
import org.derive4j.Visibility;

import static com.liquidsoftware.order.domain.ValidationErrors.validationError;
import static java.lang.String.format;

@Data(
    value = @Derive(withVisibility = Visibility.Smart),
    arguments = ArgOption.checkedNotNull
)
public abstract class ProductCode {

    ProductCode() {}

    interface Cases<R> {
        R Widget(WidgetCode widgetCode);
        R Gizmo(GizmoCode gizmoCode);
    }

    public abstract <R> R match(Cases<R> cases);

    @ExportAsPublic
    static Either<ValidationError, ProductCode> create(String productCode) {
        if (productCode == null || productCode.isBlank()) {
            return Either.left(validationError("productCode: must not be null or empty"));
        } else if (productCode.startsWith("W")) {
            return WidgetCodes.create(productCode)
                .map(wc -> ProductCodes.Widget0(wc));
        } else if (productCode.startsWith("G")) {
            return GizmoCodes.create(productCode)
                .map(gc -> ProductCodes.Gizmo0(gc));
        } else {
            return Either.left(validationError(format("productCode: Format not recognized '%s'", productCode)));
        }
    }

}
