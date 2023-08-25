package com.webbank.springsecurity.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long transactionId;

    @ManyToOne
    @JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber", foreignKey = @ForeignKey(name = "accounts_ibfk_2"))
    private Account account;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", foreignKey = @ForeignKey(name = "customer_account_transactions_ibfk_1"))
    private Customer customer;

    private LocalDate transactionDt;

    private String transactionSummary;

    private String transactionType;

    private int transactionAmt;

    private int closingBalance;

    private LocalDate createDt;

}
