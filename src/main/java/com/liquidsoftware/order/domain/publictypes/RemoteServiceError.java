package com.liquidsoftware.order.domain.publictypes;

import java.net.URI;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface RemoteServiceError {

    interface Cases<R> {
        R remoteServiceError(ServiceInfo service, Exception exception);
    }

    <R> R match(Cases<R> cases);

}
