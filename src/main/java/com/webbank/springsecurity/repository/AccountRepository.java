package com.webbank.springsecurity.repository;

import com.webbank.springsecurity.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomerCustomerId(Long customerId);

}
