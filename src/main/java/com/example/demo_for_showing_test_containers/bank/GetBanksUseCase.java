package com.example.demo_for_showing_test_containers.bank;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBanksUseCase {
    private final BankRepository bankRepository;

    public GetBanksUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    List<Bank> handle() throws Exception {
        try {
            return bankRepository.listBanks();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
