package com.liquidsoftware.order.domain.publictypes;

import java.net.URI;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface ServiceInfo {

    interface Cases<R> {
        R serviceInfo(String name, URI endpoint);
    }

    <R> R match(Cases<R> cases);

}
