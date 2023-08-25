package com.webbank.springsecurity.repository;

import com.webbank.springsecurity.model.AccountTransactions;
import com.webbank.springsecurity.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, Long> {

    List<AccountTransactions> findByCustomerCustomerIdOrderByTransactionDtDesc(Long customerId);

}
