package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Bank;
import com.solvd.bankapplication.persistence.BankDao;
import com.solvd.bankapplication.persistence.impl.BankDaoImpl;
import com.solvd.bankapplication.service.BankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankServiceImpl implements BankService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final Scanner scanner = new Scanner(System.in);

    private BankDao bankDao;

    public BankServiceImpl() {
        bankDao = new BankDaoImpl();
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
        bankDao.create(bank);
        logger.info("Created bank with the name: " + bankName);
    }
}
