package com.example.demo_for_showing_test_containers.bank;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBanksUseCase {
    private final BankRepository bankRepository;

    public GetBanksUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    List<Bank> handle() {
        var banks = bankRepository.searchBanks();
        return List.of(new Bank(100), new Bank(120));
    }
}
