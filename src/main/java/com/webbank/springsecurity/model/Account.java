package com.webbank.springsecurity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", foreignKey = @ForeignKey(name = "customer_account_ibfk_1"))
    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long accountNumber;

    private String accountType;

    private String branchAddress;

    private LocalDate createDt;

}
