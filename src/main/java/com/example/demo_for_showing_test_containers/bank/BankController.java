package com.example.demo_for_showing_test_containers.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/banks")
public class BankController {
    private final GetBanksUseCase getBanksUseCase;

    public BankController(GetBanksUseCase getBanksUseCase) {
        this.getBanksUseCase = getBanksUseCase;
    }

    @GetMapping()
    @ResponseStatus(OK)
    GetBanksResponse getBanks() {
        var value = getBanksUseCase.handle();
        return new GetBanksResponse(
                List.of(
                        new GetBanksResponseBankJson(value.getFirst().value()),
                        new GetBanksResponseBankJson(value.get(1).value())
                )
        );
    }
}
