package com.webbank.springsecurity.controller;

import static java.util.stream.Collectors.toList;

import com.webbank.springsecurity.model.Contact;
import com.webbank.springsecurity.repository.ContactRepository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

   // @PreFilter("filterObject.contactName != 'test'")
    @PostMapping("/contact")
    public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        return contactRepository.saveAll(contacts.stream()
            .map(contact -> {
                contact.setContactId(getServiceReqNumber());
                contact.setCreateDt(LocalDate.now());
                return contact;
            })
            .collect(toList()) // Collect the modified contacts into a list
        );
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR"+ranNum;
    }

}
