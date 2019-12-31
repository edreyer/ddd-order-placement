package com.liquidsoftware.order.domain.internaltypes;

import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface ListOfOption extends Function1<Option, List> {

    static <A> List<A> apply(Option<A> as) {
        return as.map(a -> List.of(a)).getOrElse(List.empty());
    }
}
