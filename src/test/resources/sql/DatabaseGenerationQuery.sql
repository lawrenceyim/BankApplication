DROP DATABASE IF EXISTS BankDB;
CREATE DATABASE IF NOT EXISTS BankDB;
USE BankDB;

CREATE TABLE Banks (
    bank_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    bank_name VARCHAR(45) NOT NULL,
    PRIMARY KEY (bank_id)
);

CREATE TABLE Positions (
	position_id INT AUTO_INCREMENT  NOT NULL UNIQUE,
    title VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (position_id)
);

CREATE TABLE Employees (
	employee_id INT AUTO_INCREMENT NOT NULL UNIQUE,
	bank_id INT NOT NULL,
    first_name VARCHAR(45) NOT NULL,
    middle_name VARCHAR(45),
    last_name VARCHAR(45) NOT NULL,
    position INT NOT NULL,
    branch_id INT NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (position) REFERENCES Positions(position_id),
    FOREIGN KEY (bank_id) REFERENCES Banks(bank_id)
);

CREATE TABLE EmployeeLoginDetails (
	employee_id INT NOT NULL UNIQUE,
	username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(45) NOT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
);

CREATE TABLE Locations (
	location_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    street_address VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    country VARCHAR(45) NOT NULL,
    PRIMARY KEY (location_id)
);

CREATE TABLE Branches (
	branch_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    location_id INT NOT NULL,
    PRIMARY KEY (branch_id),
    FOREIGN KEY (location_id) REFERENCES Locations(location_id)
);

CREATE TABLE ATMs (
	atm_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    location_id INT NOT NULL,
    cash_amount DECIMAL NOT NULL,
    PRIMARY KEY (atm_id),
    FOREIGN KEY (location_id) REFERENCES Locations(location_id)
);

CREATE TABLE Customers (
	customer_id INT AUTO_INCREMENT NOT NULL UNIQUE,
	bank_id INT NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    phone_number VARCHAR(45) NOT NULL UNIQUE,
    street_address VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    country VARCHAR(45) NOT NULL,
    PRIMARY KEY (customer_id),
    FOREIGN KEY (bank_id) REFERENCES Banks(bank_id)
);

CREATE TABLE CustomerLoginDetails (
	customer_id INT NOT NULL UNIQUE,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(45) NOT NULL,
    PRIMARY KEY (customer_id),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Individuals (
	customer_id INT NOT NULL UNIQUE,
    first_name VARCHAR(45) NOT NULL,
    middle_name VARCHAR(45),
    last_name VARCHAR(45),
    PRIMARY KEY (customer_id),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Businesses (
	customer_id INT NOT NULL UNIQUE,
    business_name VARCHAR(45) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Accounts (
	account_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    customer_id INT NOT NULL,
    account_type VARCHAR(45) NOT NULL,
    balance DECIMAL NOT NULL,
    PRIMARY KEY (account_id),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Cards (
	card_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    card_type VARCHAR(45) NOT NULL,
    account_id INT NOT NULL,
    PRIMARY KEY (card_id),
    FOREIGN KEY (account_id) REFERENCES Accounts(account_id)
);

CREATE TABLE Transfers (
	transfer_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    date DATETIME NOT NULL,
    amount DECIMAL NOT NULL,
    from_account_id INT NOT NULL,
    to_account_id INT NOT NULL,
    PRIMARY KEY (transfer_id),
    FOREIGN KEY (from_account_id) REFERENCES Accounts(account_id),
	FOREIGN KEY (to_account_id) REFERENCES Accounts(account_id)
);

CREATE TABLE Payments (
	payment_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    company VARCHAR(45) NOT NULL,
    date DATETIME NOT NULL,
    amount DECIMAL NOT NULL,
    card_id INT NOT NULL,
    PRIMARY KEY (payment_id),
    FOREIGN KEY (card_id) REFERENCES Cards(card_id)
);

CREATE TABLE Loans (
	loan_id INT AUTO_INCREMENT NOT NULL UNIQUE,
	customer_id INT NOT NULL,
    loan_amount DECIMAL NOT NULL,
    date DATETIME NOT NULL,
    PRIMARY KEY (loan_id),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);