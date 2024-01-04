package com.solvd.bankapplication.menu;

public interface IMenu {
    void displayMenu();

    int getUserChoice();

    void performUserChoice(int choice);
}
