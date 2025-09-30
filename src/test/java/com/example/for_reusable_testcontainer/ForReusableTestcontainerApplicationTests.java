package com.example.for_reusable_testcontainer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ForReusableTestcontainerApplicationTests {

	@Test
	void contextLoads() {
	}

}
