package com.example.demo_for_showing_test_containers.bank;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankRepository {
    private final BankMapper bankMapper;

    public BankRepository(BankMapper bankMapper) {
        this.bankMapper = bankMapper;
    }

    List<Bank> listBanks() throws Exception {
        try {
            return BankRow.toEntity(bankMapper.listBanks());
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }

    void createBank(Bank bank) throws Exception {
        try {
            bankMapper.createBank(BankRow.of(bank));
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }
}
