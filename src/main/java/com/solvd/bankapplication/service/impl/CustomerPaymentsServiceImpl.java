package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Account;
import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.domain.Customer;
import com.solvd.bankapplication.persistence.AccountDao;
import com.solvd.bankapplication.persistence.CardDao;
import com.solvd.bankapplication.persistence.CustomerDao;
import com.solvd.bankapplication.persistence.PaymentDao;
import com.solvd.bankapplication.service.CustomerPaymentsService;
import com.solvd.bankapplication.utils.ClassInstantiation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomerPaymentsServiceImpl implements CustomerPaymentsService {
    private final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    private final Scanner scanner = new Scanner(System.in);
    private final CustomerDao customerDao = ClassInstantiation.generateClassInstance("CustomerDaoImpl");
    private final AccountDao accountDao = ClassInstantiation.generateClassInstance("AccountDaoImpl");
    private final CardDao cardDao = ClassInstantiation.generateClassInstance("CardDaoImpl");
    private final PaymentDao paymentDao = ClassInstantiation.generateClassInstance("PaymentDaoImpl");
    private List<Account> accountList;
    private long customerId;

    @Override
    public void findAllCustomerCardPayments() {
        Optional<Customer> customer;
        OUTPUT_LOGGER.info("Enter the customer's ID: ");
        try {
            customerId = scanner.nextLong();
        } catch (InputMismatchException e) {
            OUTPUT_LOGGER.info("Invalid input.");
        }
        customer = customerDao.findById(customerId);
        if (customer.isEmpty()) {
            OUTPUT_LOGGER.info("Customer with id not found.");
            return;
        }
        accountList = accountDao.findByCustomer(customerId);
        if (accountList.isEmpty()) {
            OUTPUT_LOGGER.info("The customer does not have any accounts.");
            return;
        }

        List<Card> cardList = accountList.stream()
                .filter(account -> !cardDao.findAllByAccount(account.getAccountID()).isEmpty())
                .map(account -> cardDao.findAllByAccount(account.getAccountID()))
                .flatMap(list -> list.stream())
                .filter(card -> !paymentDao.findAllByCard(card.getCardID()).isEmpty())
                .collect(Collectors.toList());

        if (cardList.isEmpty()) {
            OUTPUT_LOGGER.info("Customer has no card payments.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Customer ID: ").append(customer.get().getCustomerID()).append(System.lineSeparator());
        cardList.stream().forEach(card -> {
            sb.append("Card: ").append(card.getCardID()).append(System.lineSeparator());
            sb.append("Card Type: ").append(card.getCardType()).append(System.lineSeparator());
            sb.append(String.format("%-30s%-30s%-30s%-30s", "Payment ID", "Company", "Date", "Amount"))
                    .append(System.lineSeparator());
            paymentDao.findAllByCard(card.getCardID()).stream().forEach(payment -> {
                sb.append(String.format("%-30s%-30s%-30s%-30s",
                        payment.getPaymentID(),
                        payment.getCompanyName(),
                        payment.getDate(),
                        payment.getAmount()));
                sb.append(System.lineSeparator());
            });
        });
        OUTPUT_LOGGER.info(sb.toString());
    }
}
