package com.webbank.springsecurity.factory;

import static java.util.stream.Collectors.toList;

import com.webbank.springsecurity.configuration.SecurityConfig;
import com.webbank.springsecurity.model.Account;
import com.webbank.springsecurity.model.AccountTransactions;
import com.webbank.springsecurity.model.Authorities;
import com.webbank.springsecurity.model.AuthorityEnum;
import com.webbank.springsecurity.model.Card;
import com.webbank.springsecurity.model.Customer;
import com.webbank.springsecurity.model.Loan;
import com.webbank.springsecurity.model.Notice;
import com.webbank.springsecurity.repository.AccountRepository;
import com.webbank.springsecurity.repository.AccountTransactionsRepository;
import com.webbank.springsecurity.repository.AuthoritiesRepository;
import com.webbank.springsecurity.repository.CardsRepository;
import com.webbank.springsecurity.repository.CustomerRepository;
import com.webbank.springsecurity.repository.LoanRepository;
import com.webbank.springsecurity.repository.NoticeRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private CardsRepository cardsRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Set<Authorities> authorities = createAuthorities();
        Customer customer = createCustomer(authorities);
        Account account = createAccount(customer);
        createAccountTransactions(customer, account);
        createLoans(customer);
        createCards(customer);
        createNoticeDetails();

    }

    private Set<Authorities> createAuthorities() {

        Set<Authorities> authorities = Set.of(
            Authorities.builder()
                .name(AuthorityEnum.ROLE_USER.getName())
                .build(),
            Authorities.builder()
                .name(AuthorityEnum.ROLE_ADMIN.getName())
                .build()
        );

        authoritiesRepository.saveAll(authorities);
        return authorities;
    }

    private void createNoticeDetails() {

        List<Notice> noticeDetails = List.of(
            Notice.builder()
                .noticeSummary("Home Loan Interest rates reduced")
                .noticeDetails("Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately")
                .createDt(LocalDate.now().minusDays(30))
                .noticEndDt(LocalDate.now().plusDays(30))
                .build(),
            Notice.builder()
                .noticeSummary("Net Banking Offers")
                .noticeDetails("Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher")
                .createDt(LocalDate.now().minusDays(30))
                .noticEndDt(LocalDate.now().plusDays(30))
                .build(),
            Notice.builder()
                .noticeSummary("Mobile App Downtime")
                .noticeDetails("The mobile application of the Parodi S.A will be down from 2AM-5AM on 12/09/2023 due to maintenance activities")
                .createDt(LocalDate.now().minusDays(30))
                .noticEndDt(LocalDate.now().plusDays(30))
                .build(),
            Notice.builder()
                .noticeSummary("Launch of Millennia Cards")
                .noticeDetails("There will be a e-auction on 12/09/2023 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction")
                .createDt(LocalDate.now().minusDays(30))
                .noticEndDt(LocalDate.now().plusDays(30))
                .build()
        );

        noticeRepository.saveAll(noticeDetails);

    }

    private void createCards(Customer customer) {
        List<Card> cards = List.of(
            Card.builder()
                .cardNumber("4565XXXX4656")
                .customer(customer)
                .cardType("visa credit")
                .totalLimit(1000)
                .amountUsed(100)
                .availableAmount(900)
                .createDt(LocalDate.now())
                .build(),
            Card.builder()
                .cardNumber("4565XXXX4657")
                .customer(customer)
                .cardType("mastercard credit")
                .totalLimit(1000)
                .amountUsed(100)
                .availableAmount(900)
                .createDt(LocalDate.now())
                .build()
        );

        cardsRepository.saveAll(cards);
    }

    private void createLoans(Customer customer) {
       // 1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13
        List<Loan> loans = List.of(
            Loan.builder()
                .customer(customer)
                .createDt(LocalDate.of(2023, 8, 1))
                .startDt(LocalDate.of(2023, 8, 1))
                .loanType("Home")
                .totalLoan(200)
                .amountPaid(50)
                .outstandingAmount(300)
                .build(),
            Loan.builder()
                .customer(customer)
                .createDt(LocalDate.of(2023, 7, 1))
                .startDt(LocalDate.of(2023, 7, 1))
                .loanType("Home")
                .totalLoan(300)
                .amountPaid(100)
                .outstandingAmount(400)
                .build(),
            Loan.builder()
                .customer(customer)
                .createDt(LocalDate.of(2023, 6, 1))
                .startDt(LocalDate.of(2023, 6, 1))
                .loanType("Home")
                .totalLoan(400)
                .amountPaid(150)
                .outstandingAmount(500)
                .build(),
            Loan.builder()
                .customer(customer)
                .createDt(LocalDate.of(2023, 5, 1))
                .startDt(LocalDate.of(2023, 5, 1))
                .loanType("Home")
                .totalLoan(500)
                .amountPaid(200)
                .outstandingAmount(600)
                .build()
        );

        loanRepository.saveAll(loans);
    }


    private Account createAccount(Customer customer) {
        Account account = Account.builder()
            .accountType("Savings")
            .branchAddress("CABA")
            .createDt(LocalDate.now())
            .customer(customer)
            .build();

        accountRepository.save(account);

        return account;

    }

    private Customer createCustomer(Set<Authorities> authoritiesCollections) {
        String encodedPassword = SecurityConfig.passwordEncoder().encode("12345");
        Authorities authorities = getJustViewAccountAuthority();

        Customer customer = Customer.builder()
            .email("julianparodi19@gmail.com")
            .name("juliparodi")
            .pwd(encodedPassword)
            .authorities(authoritiesCollections)
            .build();

        Customer customer2 = Customer.builder()
            .email("123@hola.com")
            .name("hola")
            .pwd(encodedPassword)
            .authorities(Set.of(authorities))
            .build();


        customerRepository.saveAll(List.of(customer, customer2));

        return customer;

    }

    private Authorities getJustViewAccountAuthority() {
        return authoritiesRepository.findByName(AuthorityEnum.ROLE_USER.getName());
    }

    private void createAccountTransactions(Customer customer, Account account) {
        List<AccountTransactions> accountTransactions = List.of(
            AccountTransactions.builder()
                .account(account)
                .customer(customer)
                .createDt(LocalDate.now())
                .transactionDt(LocalDate.now().minusDays(7))
                .transactionSummary("Coffe Shop")
                .transactionType("withdrawn")
                .transactionAmt(100)
                .closingBalance(20000)
                .build(),
            AccountTransactions.builder()
                .account(account)
                .customer(customer)
                .createDt(LocalDate.now().minusDays(6))
                .transactionDt(LocalDate.now().minusDays(6))
                .transactionSummary("Cabify")
                .transactionType("withdrawn")
                .transactionAmt(50)
                .closingBalance(19950)
                .build(),
            AccountTransactions.builder()
                .account(account)
                .customer(customer)
                .createDt(LocalDate.now().minusDays(5))
                .transactionDt(LocalDate.now().minusDays(5))
                .transactionSummary("Self Deposit")
                .transactionType("deposit")
                .transactionAmt(100)
                .closingBalance(20050)
                .build(),
            AccountTransactions.builder()
                .account(account)
                .customer(customer)
                .createDt(LocalDate.now().minusDays(4))
                .transactionDt(LocalDate.now().minusDays(4))
                .transactionSummary("mercado libre")
                .transactionType("withdrawn")
                .transactionAmt(50)
                .closingBalance(2000)
                .build(),
            AccountTransactions.builder()
                .account(account)
                .customer(customer)
                .createDt(LocalDate.now().minusDays(3))
                .transactionDt(LocalDate.now().minusDays(3))
                .transactionSummary("Binance")
                .transactionType("withdrawn")
                .transactionAmt(100)
                .closingBalance(19900)
                .build(),
            AccountTransactions.builder()
                .account(account)
                .customer(customer)
                .createDt(LocalDate.now().minusDays(1))
                .transactionDt(LocalDate.now().minusDays(1))
                .transactionSummary("transfer")
                .transactionType("deposit")
                .transactionAmt(100)
                .closingBalance(20000)
                .build()
        );

        accountTransactionsRepository.saveAll(accountTransactions);
    }
}
