package com.webbank.springsecurity.repository;

import com.webbank.springsecurity.model.Loan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    //@PreAuthorize("hasRole('USER')")
    List<Loan> findByCustomerCustomerIdOrderByStartDtDesc(Long id);
}
