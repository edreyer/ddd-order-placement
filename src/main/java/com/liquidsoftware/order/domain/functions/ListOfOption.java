package com.liquidsoftware.order.domain.functions;

import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface ListOfOption<A> extends Function1<Option<A>, List<A>> {

    static <A> List<A> apply(Option<A> as) {
        return as.map(a -> List.of(a)).getOrElse(List.empty());
    }

}
