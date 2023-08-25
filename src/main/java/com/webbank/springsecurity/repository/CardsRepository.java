package com.webbank.springsecurity.repository;

import com.webbank.springsecurity.model.Card;
import com.webbank.springsecurity.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardsRepository extends JpaRepository<Card, Long> {

    List<Card> findByCustomerCustomerId(Long id);
}
