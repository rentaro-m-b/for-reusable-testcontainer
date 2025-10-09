package com.example.demo_for_showing_test_containers.bank;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBanksUseCase {
    List<Bank> handle() {
        return List.of(new Bank(100), new Bank(120));
    }
}
