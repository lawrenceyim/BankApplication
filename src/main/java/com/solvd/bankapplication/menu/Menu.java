package com.solvd.bankapplication.menu;

import com.solvd.bankapplication.service.AccountService;
import com.solvd.bankapplication.service.CardService;
import com.solvd.bankapplication.service.CustomerService;
import com.solvd.bankapplication.service.PaymentService;
import com.solvd.bankapplication.service.TransferService;
import com.solvd.bankapplication.service.impl.AccountServiceImpl;
import com.solvd.bankapplication.service.impl.CardServiceImpl;
import com.solvd.bankapplication.service.impl.CustomerServiceImpl;
import com.solvd.bankapplication.service.impl.PaymentServiceImpl;
import com.solvd.bankapplication.service.impl.TransferServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements IMenu {
    AccountService accountService;
    CardService cardService;
    CustomerService customerService;
    PaymentService paymentService;
    TransferService transferService;
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final Scanner scanner = new Scanner(System.in);

    public Menu() {
        accountService = new AccountServiceImpl();
        cardService = new CardServiceImpl();
        customerService = new CustomerServiceImpl();
        paymentService = new PaymentServiceImpl();
        transferService = new TransferServiceImpl();
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
