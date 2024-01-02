package com.solvd.bankapplication.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements IMenu {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        logger.info("Bank Application");
        logger.info("1. View Accounts");
        logger.info("2. View Cards");
        logger.info("3. View Payments");
        logger.info("4. View Transfers");
        logger.info("5. View Customers");
        logger.info("6. Switch Customer");
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
            case 0:
                return;
            case 1:
                return;
            case 2:
                return;
            case 3:
                return;
            case 4:
                return;
            case 5:
                return;
            case 6:
                return;
        }
    }
}
