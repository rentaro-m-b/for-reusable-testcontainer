package com.example.demo_for_showing_test_containers.bank.usecase;

import com.example.demo_for_showing_test_containers.bank.dao.BankRepository;
import com.example.demo_for_showing_test_containers.bank.domain.Bank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBanksUseCase {
    private final BankRepository bankRepository;

    public GetBanksUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> handle() throws Exception {
        try {
            return bankRepository.listBanks();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
