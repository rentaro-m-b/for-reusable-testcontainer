package com.example.demo_for_showing_test_containers.bank.domain;

import java.time.LocalDateTime;

public record Company(String id, String name, LocalDateTime createdAt) {}
