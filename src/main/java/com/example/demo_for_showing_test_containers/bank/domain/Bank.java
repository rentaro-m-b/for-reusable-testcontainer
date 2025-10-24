package com.example.demo_for_showing_test_containers.bank.domain;

import java.time.LocalDateTime;

public record Bank(String id, Money money, LocalDateTime createdAt) {}
