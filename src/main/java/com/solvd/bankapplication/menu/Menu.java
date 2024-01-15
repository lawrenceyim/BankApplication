package com.solvd.bankapplication.menu;

import com.solvd.bankapplication.service.AccountService;
import com.solvd.bankapplication.service.CardService;
import com.solvd.bankapplication.service.CustomerPaymentsService;
import com.solvd.bankapplication.service.CustomerService;
import com.solvd.bankapplication.service.impl.AccountServiceImpl;
import com.solvd.bankapplication.service.impl.CardServiceImpl;
import com.solvd.bankapplication.service.impl.CustomerPaymentsServiceImpl;
import com.solvd.bankapplication.service.impl.CustomerServiceImpl;
import com.solvd.bankapplication.utils.jaxb.JaxbParser;
import com.solvd.bankapplication.utils.json.JsonParser;
import com.solvd.bankapplication.utils.sax.SaxParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements IMenu {
    AccountService accountService;
    CardService cardService;
    CustomerPaymentsService customerPaymentsService;
    CustomerService customerService;

    private final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    private final Scanner scanner = new Scanner(System.in);

    public Menu() {
        accountService = new AccountServiceImpl();
        cardService = new CardServiceImpl();
        customerPaymentsService = new CustomerPaymentsServiceImpl();
        customerService = new CustomerServiceImpl();
    }

    @Override
    public void displayMenu() {
        OUTPUT_LOGGER.info("Bank Application");
        OUTPUT_LOGGER.info("1. View Customers");
        OUTPUT_LOGGER.info("2. View Accounts");
        OUTPUT_LOGGER.info("3. View Cards and Payments");
        OUTPUT_LOGGER.info("4. View Customer Payments");
        OUTPUT_LOGGER.info("5. View Locations");
        OUTPUT_LOGGER.info("6. View Employees");
        OUTPUT_LOGGER.info("7. View Employee Login Details");
        OUTPUT_LOGGER.info("8. Exit");
    }

    @Override
    public int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= 8) {
                return choice;
            }
        } catch (InputMismatchException e) {
            OUTPUT_LOGGER.info("Invalid input. Returning to menu");
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
                accountService.findAllAccounts();
                return;
            case 3:
                cardService.findAllCardsAndPayments();
                return;
            case 4:
                customerPaymentsService.findAllCustomerCardPayments();
                return;
            case 5:
                JsonParser.parseLocations();
                return;
            case 6:
                SaxParser.parseEmployees();
                return;
            case 7:
                JaxbParser.parseEmployeeLoginDetail();
                return;
            case 8:
                System.exit(0);
            default:
                OUTPUT_LOGGER.info("Invalid choice");
        }
    }
}
