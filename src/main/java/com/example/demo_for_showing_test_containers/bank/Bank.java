package com.example.demo_for_showing_test_containers.bank;

import java.time.LocalDateTime;

public record Bank(String id, int deposit, String unit, LocalDateTime createdAt) {}
