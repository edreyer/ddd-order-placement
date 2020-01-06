package com.liquidsoftware.order.domain;

import org.derive4j.ArgOption;
import org.derive4j.Data;

@Data(arguments = ArgOption.checkedNotNull)
public interface PdfAttachment {

    interface Cases<R> {
        R pdfAttachment(
            String name,
            Byte[] bytes
        );
    }

    <R> R match(Cases<R> cases);


}
