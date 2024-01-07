USE BankDB;

INSERT INTO Banks VALUES
    (1, "Bank of America");

INSERT INTO Customers VALUES
	(1, 1, 'customer1@email.com', '123456789', 'Street Address 1', 'City A', 'Country A'),
	(2, 1, 'customer2@email.com', '123456790', 'Street Address 2', 'City A', 'Country A'),
	(3, 1, 'customer3@email.com', '123456791', 'Street Address 3', 'City B', 'Country A'),
	(4, 1, 'customer4@email.com', '123456792', 'Street Address 4', 'City C', 'Country B'),
	(5, 1, 'customer5@email.com', '123456793', 'Street Address 5', 'City D', 'Country B'),
	(6, 1, 'customer6@email.com', '123456794', 'Street Address 6', 'City E', 'Country B'),
	(7, 1, 'customer7@email.com', '123456795', 'Street Address 7', 'City F', 'Country C');

INSERT INTO Individuals (customer_id, first_name, last_name) VALUES (2, 'First Name 2', 'Last Name 2');
INSERT INTO Individuals VALUES
	(1, 'First Name 1', 'Middle Name 1', 'Last Name 1'),
	(3, 'First Name 3', 'Middle Name 3', 'Last Name 3'),
	(4, 'First Name 4', 'Middle Name 4', 'Last Name 4');

INSERT INTO Businesses VALUES
	(5, 'Company 5'),
	(6, 'Company 6'),
	(7, 'Company 7');

INSERT INTO CustomerLoginDetails VALUES
	(1, 'username1', 'password1'),
	(2, 'username2', 'password2'),
	(3, 'username3', 'password3'),
	(4, 'username4', 'password4'),
	(5, 'username5', 'password5'),
	(6, 'username6', 'password6'),
	(7, 'username7', 'password7');

INSERT INTO Accounts VALUES
	(1, 1, 'Savings', 0),
	(2, 1, 'Checkings', 0),
	(3, 2, 'Savings', 0),
	(4, 2, 'Checkings', 0),
	(5, 3, 'Savings', 0),
	(6, 3, 'Checkings', 0),
	(7, 4, 'Savings', 0),
	(8, 4, 'Checkings', 0),
	(9, 5, 'Savings', 0),
	(10, 5, 'Checkings', 0),
	(11, 6, 'Savings', 0),
	(12, 6, 'Checkings', 0),
	(13, 7, 'Savings', 0),
	(14, 7, 'Checkings', 0);

INSERT INTO Transfers VALUES
	(1, '2000-01-01', 3100.32, 1, 2),
	(2, '2000-01-08', 45.99, 3, 4),
	(3, '2000-01-21', 2.00, 1, 3);

INSERT INTO Cards VALUES
	(1, 'Debit', 1),
	(2, 'Credit', 2),
	(3, 'Debit', 3),
	(4, 'Credit', 4);

INSERT INTO Payments VALUES
	(1, 'Company 1', '2000-01-19', 123.12, 2),
	(2, 'Company 2', '2000-03-11', 400.00, 4);

INSERT INTO Positions VALUES
	(1, 'Chief Execution Officer'),
    (2, 'President'),
    (3, 'Vice-President'),
	(4, 'Chief Financial Officer'),
    (5, 'Project Manager'),
    (6, 'Tech Lead'),
    (7, 'Senior Developer'),
    (8, 'Junior Developer'),
    (9, 'Accountant'),
    (10, 'Sales Consultant');

INSERT INTO Locations VALUES
	(1, 'Address 1', 'City A', 'Country A'),
    (2, 'Address 2', 'City B', 'Country A'),
    (3, 'Address 3', 'City C', 'Country B');

INSERT INTO ATMs VALUES
	(1, 1, 1299.23),
    (2, 1, 49000),
    (3, 2, 2500.01),
    (4, 3, 10000);

INSERT INTO Branches VALUES
	(1, 1),
    (2, 3);

INSERT INTO Employees VALUES
	(1, 1, 'Carl', 'Bill', 'Kevin', 1, 1, 'employee1@email.com'),
    (2, 1, 'Ryan', 'Chloe', 'Kath', 2, 1, 'employee2@gmail.com');

INSERT INTO Loans VALUES
	(1, 1, 5000.00, '2023-12-18 08:00:00'),
	(2, 2, 7000.00, '2023-12-18 09:00:00'),
	(3, 2, 3000.00, '2023-12-18 10:00:00'),
	(4, 4, 9000.00, '2023-12-18 11:00:00'),
	(5, 5, 6000.00, '2023-12-18 12:00:00'),
	(6, 6, 4500.00, '2023-12-18 13:00:00'),
	(7, 6, 8000.00, '2023-12-18 14:00:00');
