package com.example.for_reusable_testcontainer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController("/api/health")
public class HealthController {
    @GetMapping
    @ResponseStatus(NO_CONTENT)
    public void getHealth() {}
}
