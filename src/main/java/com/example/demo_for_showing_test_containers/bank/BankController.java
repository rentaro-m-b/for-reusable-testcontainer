package com.example.demo_for_showing_test_containers.bank;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
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
    GetBanksResponse getBanks() throws Exception {
        return GetBanksResponse.of(getBanksUseCase.handle());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {
        ProblemDetail detail = ProblemDetail.forStatus(INTERNAL_SERVER_ERROR);
        detail.setDetail("サーバー内部で予期しないエラーが発生しました。");
        return detail;
    }
}
