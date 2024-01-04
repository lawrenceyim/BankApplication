package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Bank;
import com.solvd.bankapplication.persistence.BankRepository;
import com.solvd.bankapplication.persistence.impl.BankRepositoryImpl;
import com.solvd.bankapplication.service.BankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankServiceImpl implements BankService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final Scanner scanner = new Scanner(System.in);

    private BankRepository bankRepository;

    public BankServiceImpl() {
        bankRepository = new BankRepositoryImpl();
    }

    @Override
    public void createBank() {
        Bank bank = new Bank();
        String bankName;
        try {
            bankName = scanner.nextLine().trim();
        } catch (InputMismatchException e) {
            logger.info("Invalid input. Returning to menu");
            return;
        }
        bank.setBankName(bankName);
        bankRepository.create(bank);
        logger.info("Created bank with the name: " + bankName);
    }
}
