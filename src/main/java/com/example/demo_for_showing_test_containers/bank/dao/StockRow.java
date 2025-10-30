package com.example.demo_for_showing_test_containers.bank.dao;

import java.time.LocalDateTime;

public record StockRow(String id, String companyId, int issuedShares, LocalDateTime createdAt) {}
