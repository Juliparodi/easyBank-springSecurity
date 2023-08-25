package com.webbank.springsecurity.repository;

import com.webbank.springsecurity.model.Customer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer WHERE email = ?1 LIMIT 1", nativeQuery = true)
    Optional<Customer> findTopByEmail(String email);
    List<Customer> findByEmail(String email);


}
