package com.example.demo_for_showing_test_containers.bank;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankRepository {

    List<Bank> searchBanks() {
        return List.of();
    }
}
