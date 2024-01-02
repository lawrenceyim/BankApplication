package com.solvd.bankapplication;

import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.util.Generator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Main {
    private static final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    static {
//        Generator.createDatabase();
//        Generator.fillDataBase();
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        int choice = 0;
        while (choice > -1) {
            menu.displayMenu();
            choice = menu.getUserChoice();
            menu.performUserChoice(choice);
        }
    }
}
