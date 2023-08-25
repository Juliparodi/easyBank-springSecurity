package com.webbank.springsecurity.controller;

import com.webbank.springsecurity.model.Account;
import com.webbank.springsecurity.repository.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountsRepository;

    @GetMapping("/account")
    public List<Account> getAccountDetails(@RequestParam Long id) {
        List<Account> accounts = accountsRepository.findByCustomerCustomerId(id);
        return accounts;
    }

}
