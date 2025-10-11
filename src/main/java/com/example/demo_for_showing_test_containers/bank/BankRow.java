package com.example.demo_for_showing_test_containers.bank;

import java.time.LocalDateTime;

record BankRow(String id, int value, String unit, LocalDateTime createdAt) {}
