package com.example.demo_for_showing_test_containers.bank.domain;

import java.util.List;

public interface BankRepository {
    List<Bank> listBanks() throws Exception;

    void createBank(Bank bank) throws Exception;

    void updateBank(Bank bank) throws Exception;

    void deleteBank(String id) throws Exception;
}
