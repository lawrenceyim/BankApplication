package com.solvd.bankapplication.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Menu implements IMenu {
    private final Logger logger = (Logger) LogManager.getLogger("Output");

    @Override
    public void displayMenu() {
        logger.debug("Hello");
    }

    @Override
    public void performUserChoice() {

    }
}
