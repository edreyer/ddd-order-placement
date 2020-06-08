package com.liquidsoftware.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.liquidsoftware.order.InterfaceTest.InnerA.innerA;

@SpringBootTest
class DddOrderPlacementApplicationTests {

	@Test
	void contextLoads() {
		innerA.apply("bob");
	}

}
