package com.example.demo_for_showing_test_containers.dao;

import com.example.demo_for_showing_test_containers.domain.Company;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record CompanyRow(String id, String name, LocalDateTime createdAt) {
    static List<Company> toEntities(List<CompanyRow> companyRows) {
        return companyRows.stream().map(CompanyRow::toEntity).collect(Collectors.toList());
    }

    static Company toEntity(CompanyRow companyRow) {
        return new Company(
                companyRow.id,
                companyRow.name,
                companyRow.createdAt
        );
    }

    static CompanyRow of(Company company) {
        return new CompanyRow(
                company.id(),
                company.name(),
                company.createdAt()
        );
    }
}
