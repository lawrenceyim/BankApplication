package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Bank;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.AccountDao;
import com.solvd.bankapplication.persistence.BankDao;
import com.solvd.bankapplication.persistence.jdbc.BankDaoImpl;
import com.solvd.bankapplication.service.BankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class BankServiceImpl implements BankService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final String configFile = "config.properties";
    private final Scanner scanner = new Scanner(System.in);

    private BankDao bankDao;

    public BankServiceImpl() {
        try (InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream(configFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            final String classPath = properties.getProperty("implementation-path");
            bankDao = (BankDao) Class.forName(classPath + "BankDaoImpl").getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
