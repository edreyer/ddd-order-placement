package com.liquidsoftware.order.domain.publictypes;

import com.liquidsoftware.order.domain.internaltypes.PricedOrder;
import org.derive4j.ArgOption;
import org.derive4j.Data;

/**
 * Java's way of type aliasing is via extension
 */
@Data(arguments = ArgOption.checkedNotNull)
public interface OrderPlaced extends PricedOrder {
}
