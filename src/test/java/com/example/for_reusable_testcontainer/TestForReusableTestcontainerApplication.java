package com.example.for_reusable_testcontainer;

import org.springframework.boot.SpringApplication;

public class TestForReusableTestcontainerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ForReusableTestcontainerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
