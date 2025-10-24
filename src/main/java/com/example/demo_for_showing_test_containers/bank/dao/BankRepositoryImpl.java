package com.example.demo_for_showing_test_containers.bank.dao;

import com.example.demo_for_showing_test_containers.bank.domain.Bank;
import com.example.demo_for_showing_test_containers.bank.domain.BankRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankRepositoryImpl implements BankRepository {
    private final BankMapper bankMapper;

    public BankRepositoryImpl(BankMapper bankMapper) {
        this.bankMapper = bankMapper;
    }

    public List<Bank> listBanks() throws Exception {
        try {
            return BankRow.toEntity(bankMapper.listBanks());
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }

    public void createBank(Bank bank) throws Exception {
        try {
            bankMapper.createBank(BankRow.of(bank));
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }

    public void updateBank(Bank bank) throws Exception {
        try {
            bankMapper.updateBank(BankRow.of(bank));
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }

    public void deleteBank(String id) throws Exception {
        try {
            bankMapper.deleteBank(id);
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }
}
