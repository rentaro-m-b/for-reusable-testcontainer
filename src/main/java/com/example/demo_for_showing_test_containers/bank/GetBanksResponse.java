package com.example.demo_for_showing_test_containers.bank;

import java.util.List;

class GetBanksResponse {
    List<GetBanksResponseBankJson> banks;

    public List<GetBanksResponseBankJson> getBanks() {
        return banks;
    }

    public void setBanks(List<GetBanksResponseBankJson> bank) {
        this.banks = bank;
    }
}
