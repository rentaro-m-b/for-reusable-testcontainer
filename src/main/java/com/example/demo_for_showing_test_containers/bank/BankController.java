package com.example.demo_for_showing_test_containers.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/banks")
public class BankController {
    @GetMapping()
    @ResponseStatus(OK)
    GetBanksResponse getBanks() {
        GetBanksResponseBankJson adam = new GetBanksResponseBankJson();
        adam.setValue(100);

        GetBanksResponseBankJson eve = new GetBanksResponseBankJson();
        eve.setValue(120);

        List<GetBanksResponseBankJson> banks = new ArrayList<>();
        banks.add(adam);
        banks.add(eve);

        GetBanksResponse result = new GetBanksResponse();
        result.setBanks(banks);
        return result;
    }
}
