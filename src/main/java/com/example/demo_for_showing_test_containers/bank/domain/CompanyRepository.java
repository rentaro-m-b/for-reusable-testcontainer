package com.example.demo_for_showing_test_containers.bank.domain;

import java.util.List;

public interface CompanyRepository {
    List<Company> listCompanies() throws Exception;

    void createCompany(Company company) throws Exception;

    void updateCompany(Company company) throws Exception;

    void deleteCompany(String id) throws Exception;
}
