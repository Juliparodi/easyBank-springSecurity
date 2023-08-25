package com.webbank.springsecurity.repository;

import com.webbank.springsecurity.model.Contact;
import com.webbank.springsecurity.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
