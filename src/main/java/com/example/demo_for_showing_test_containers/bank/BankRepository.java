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
            System.out.println("検証----------------------------------------");
            var bankRows = bankMapper.listBanks();
            System.out.println(bankRows);
            var banks = BankRow.of(bankMapper.listBanks());
            System.out.println(banks);
            return banks;
        } catch (DataAccessException e) {
            throw new Exception(e);
        }
    }
}
