package com.example.demo_for_showing_test_containers.currency;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @GetMapping()
    @ResponseStatus(OK)
    public void getCurrency() {}
}
