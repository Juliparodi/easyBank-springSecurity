package com.webbank.springsecurity.service;

import com.webbank.springsecurity.model.Customer;
import com.webbank.springsecurity.repository.CustomerRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Optional<Customer> createCustomer(Customer customer) {
        String hashPwd = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashPwd);
        customer.setCreateDt(LocalDate.now());
        return Optional.of(customerRepository.save(customer));
    }
}
