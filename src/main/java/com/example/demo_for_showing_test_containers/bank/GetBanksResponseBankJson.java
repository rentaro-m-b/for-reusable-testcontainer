package com.example.demo_for_showing_test_containers.bank;

import java.util.List;
import java.util.stream.Collectors;

record GetBanksResponseBankJson(int value) {
    static List<GetBanksResponseBankJson> of(List<Bank> banks) {
        return banks.stream().map(GetBanksResponseBankJson::of).collect(Collectors.toList());
    }
    static GetBanksResponseBankJson of(Bank bank) {
        return new GetBanksResponseBankJson(
                bank.deposit()
        );
    }
}
