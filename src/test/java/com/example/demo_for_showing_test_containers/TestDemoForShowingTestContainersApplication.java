package com.example.demo_for_showing_test_containers;

import org.springframework.boot.SpringApplication;

public class TestDemoForShowingTestContainersApplication {

	public static void main(String[] args) {
		SpringApplication.from(DemoForShowingTestContainersApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
