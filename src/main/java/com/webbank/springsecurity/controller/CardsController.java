package com.webbank.springsecurity.controller;

import com.webbank.springsecurity.model.Card;
import com.webbank.springsecurity.repository.CardsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/cards")
    public List<Card> getCardDetails(@RequestParam Long id) {
        List<Card> cards = cardsRepository.findByCustomerCustomerId(id);
        return cards;
    }

}
