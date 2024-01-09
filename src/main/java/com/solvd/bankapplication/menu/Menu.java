package com.solvd.bankapplication.menu;

import com.solvd.bankapplication.service.*;
import com.solvd.bankapplication.service.impl.*;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.module.Configuration;
import java.util.*;

public class Menu implements IMenu {
    AccountService accountService;
    CardService cardService;
    CustomerService customerService;
    PaymentService paymentService;
    TransferService transferService;
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final Scanner scanner = new Scanner(System.in);
    private final String configFile = "config.properties";
    private final String implementationProperty = "use-implementation";
    private final List<String> validImplementations = new ArrayList<>(Arrays.asList("mybatis", "jdbc"));
    private final String implementationMode;

    public Menu() {
        try (InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream(configFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            implementationMode = properties.getProperty(implementationProperty);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (implementationMode == null) {
            throw new RuntimeException("No use-implementation property found");
        } else if (!validImplementations.contains(implementationMode)) {
            throw new RuntimeException("No valid implementation found");
        }

        accountService = new AccountServiceImpl(implementationMode);
        cardService = new CardServiceImpl(implementationMode);
        customerService = new CustomerServiceImpl(implementationMode);
        paymentService = new PaymentServiceImpl(implementationMode);
        transferService = new TransferServiceImpl(implementationMode);
    }

    @Override
    public void displayMenu() {
        logger.info("Bank Application");
        logger.info("1. View Customers");
        logger.info("2. View Accounts");
        logger.info("3. View Cards");
        logger.info("4. View Payments");
        logger.info("5. View Transfers");
        logger.info("6. Exit");
    }

    @Override
    public int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= 6) {
                return choice;
            }
        } catch (InputMismatchException e) {
            logger.info("Invalid input. Returning to menu");
        }
        return 0;
    }

    @Override
    public void performUserChoice(int choice) {
        switch (choice) {
            case 1:
                customerService.findAll();
                return;
            case 2:
                accountService.findAll();
                return;
            case 3:
                cardService.findAll();
                return;
            case 4:
                paymentService.findAll();
                return;
            case 5:
                transferService.findAll();
                return;
            case 6:
                System.exit(0);
        }
    }
}
