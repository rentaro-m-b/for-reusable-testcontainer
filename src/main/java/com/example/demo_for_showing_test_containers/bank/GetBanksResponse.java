package com.example.demo_for_showing_test_containers.bank;

import java.util.List;

record GetBanksResponse(List<GetBanksResponseBankJson> banks) {
    static GetBanksResponse of(List<Bank> banks) {
        return new GetBanksResponse(GetBanksResponseBankJson.of(banks));
    }
}
