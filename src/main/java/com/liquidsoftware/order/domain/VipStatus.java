package com.liquidsoftware.order.domain;

import com.liquidsoftware.order.domain.simpletypes.ValidationError;
import io.vavr.control.Either;
import org.derive4j.Data;
import org.derive4j.ExportAsPublic;

import static com.liquidsoftware.order.domain.simpletypes.ValidationErrors.validationError;

@Data
public enum VipStatus {

    Normal{
        @Override
        public <R> R match(Cases<R> cases) {
            return cases.Normal();
        }
    },
    Vip{
        @Override
        public <R> R match(Cases<R> cases) {
            return cases.Vip();
        }
    };

    @ExportAsPublic
    static Either<ValidationError, VipStatus> fromString(String status) {
        if (status.toLowerCase().equals("normal")) {
            return Either.right(VipStatus.Normal);
        } else if (status.toLowerCase().equals("vip")) {
            return Either.right(VipStatus.Vip);
        }
        return Either.left(validationError("'status' must be one of 'Normal' or 'VIP'"));
    }

    public abstract <R> R match(Cases<R> cases);

    interface Cases<R> {
        R Normal();
        R Vip();
    }

}
