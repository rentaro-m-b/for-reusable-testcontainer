package com.example.demo_for_showing_test_containers.bank;

import java.time.LocalDateTime;

public record Bank(String id, int value, String unit, LocalDateTime createdAt) {}
