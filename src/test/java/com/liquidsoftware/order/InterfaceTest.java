package com.liquidsoftware.order;

import io.vavr.Function1;

public interface InterfaceTest {

    interface InnerA extends Function1<String, String> {
        InnerA innerA = (s) -> "Hi, " + s;
    }

}
