package com.webbank.springsecurity.controller;

import com.webbank.springsecurity.model.Loan;
import com.webbank.springsecurity.repository.LoanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/loans")
    public List<Loan> getLoanDetails(@RequestParam Long id) {
        List<Loan> loans = loanRepository.findByCustomerCustomerIdOrderByStartDtDesc(id);
        if (loans != null ) {
            return loans;
        }else {
            return null;
        }
    }
}
