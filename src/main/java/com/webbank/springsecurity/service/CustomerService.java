package com.webbank.springsecurity.service;

import com.webbank.springsecurity.model.Customer;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> createCustomer(Customer customer);

}
