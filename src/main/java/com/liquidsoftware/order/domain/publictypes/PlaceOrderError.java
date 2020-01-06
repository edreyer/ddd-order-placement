package com.liquidsoftware.order.domain.publictypes;

import org.derive4j.ArgOption;
import org.derive4j.Data;

import com.liquidsoftware.order.domain.ValidationError;

@Data
public interface PlaceOrderError {

    interface Cases<R> {
        R validation(ValidationError validationError);
        R pricing(PricingError pricingError);
        R remoteService(RemoteServiceError remoteServiceError);
    }

    <R> R match(Cases<R> cases);

}
