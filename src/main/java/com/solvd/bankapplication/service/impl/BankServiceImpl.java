package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Bank;
import com.solvd.bankapplication.persistence.BankDao;
import com.solvd.bankapplication.persistence.CustomerDao;
import com.solvd.bankapplication.service.BankService;
import com.solvd.bankapplication.utils.ClassInstantiation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class BankServiceImpl implements BankService {
    private final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    private final Scanner scanner = new Scanner(System.in);
    private BankDao bankDao = ClassInstantiation.generateClassInstance("BankDaoImpl");
    private CustomerDao customerDao = ClassInstantiation.generateClassInstance("CustomerDaoImpl");
    private long bankId;

    @Override
    public void findBankCustomerDetails() {
        OUTPUT_LOGGER.info("Enter the bank's ID: ");
        try {
            bankId = scanner.nextLong();
        } catch (InputMismatchException e) {
            OUTPUT_LOGGER.info("Invalid input.");
        }
        Optional<Bank> bank = bankDao.findById(bankId);
        if (bank.isEmpty()) {
            OUTPUT_LOGGER.info("No bank with that ID found.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s", "Customer ID", "Email", "Phone Number")).append(System.lineSeparator());
        customerDao.findAll().stream()
                .filter(customer -> customer.getCustomerID() == bankId)
                .forEach(customer -> {
                    sb.append(String.format("%-30s%-30s%-30s",
                            customer.getCustomerID(),
                            customer.getEmail(),
                            customer.getPhoneNumber()));
                    sb.append(System.lineSeparator());
                });
        OUTPUT_LOGGER.info(sb.toString());
    }
}
