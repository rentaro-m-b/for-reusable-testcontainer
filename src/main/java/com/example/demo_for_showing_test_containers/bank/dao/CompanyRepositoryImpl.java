package com.example.demo_for_showing_test_containers.bank.dao;

import com.example.demo_for_showing_test_containers.bank.domain.Company;
import com.example.demo_for_showing_test_containers.bank.domain.CompanyRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    private final CompanyMapper companyMapper;

    public CompanyRepositoryImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    public List<Company> listCompanies() throws Exception {
        try {
            return CompanyRow.toEntities(companyMapper.listCompanies());
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }

    public void createCompany(Company company) throws Exception {
        try {
            companyMapper.createCompany(CompanyRow.of(company));
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }

    public void updateCompany(Company company) throws Exception {
        try {
            companyMapper.updateCompany(CompanyRow.of(company));
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }

    public void deleteCompany(String id) throws Exception {
        try {
            companyMapper.deleteCompany(id);
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }
}
