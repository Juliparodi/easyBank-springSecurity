package com.webbank.springsecurity.controller;

import com.webbank.springsecurity.model.AccountTransactions;
import com.webbank.springsecurity.repository.AccountTransactionsRepository;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/balance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam Long id) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
            findByCustomerCustomerIdOrderByTransactionDtDesc(id);
        log.info("holaa" + accountTransactions);
        return accountTransactions;
    }

}
