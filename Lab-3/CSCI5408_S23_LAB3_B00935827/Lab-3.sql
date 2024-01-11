-- DMWA Lab-3 Transactions and Normalization
-- Step-1
-- Create banking_application database
CREATE SCHEMA banking_application;

-- Switch to the banking_application database
USE banking_application;

-- Create customer details table
CREATE TABLE customer (
cust_id INT PRIMARY KEY, 
cust_name VARCHAR (45) NOT NULL,
cust_age VARCHAR (45),
cust_DOB DATETIME,
mailing_address VARCHAR (45) NOT NULL,
permanent_address VARCHAR (255) NOT NULL, 
primary_email VARCHAR (45) NOT NULL, 
primary_phone_number VARCHAR (255) NOT NULL
);

-- Create account details table
CREATE TABLE account_details (
account_number INT PRIMARY KEY,
account_balance VARCHAR (255) NOT NULL,
cust_id INT,
FOREIGN KEY(cust_id) references customer(cust_id)
);

-- Create account transfer details table
CREATE TABLE account_transfer_details (
date_of_transfer DATETIME PRIMARY KEY,
recipient_name VARCHAR (255) NOT NULL,
status VARCHAR (255) NOT NULL,
account_balance VARCHAR (255) NOT NULL,
account_number INT,
FOREIGN KEY(account_number) references account_details(account_number),
cust_id INT,
FOREIGN KEY(cust_id) references customer(cust_id)
);

-- Insert record into customer table
INSERT INTO customer (cust_id, cust_name, cust_age, cust_DOB, mailing_address, permanent_address, primary_email, primary_phone_number)
VALUES ('01', 'Bhavisha','25', '1997-04-01', 'ahmedabad', 'India', 'erbhavisha@gmail.com', '9876097710');

INSERT INTO customer (cust_id, cust_name, cust_age, cust_DOB, mailing_address, permanent_address, primary_email, primary_phone_number)
VALUES ('02', 'Bhaumik','26', '1996-10-18', 'ahmedabad', 'India', 'bhaumik123@gmail.com', '9878620710');

-- Insert record into account details table
INSERT INTO account_details (account_number, account_balance, cust_id)
VALUES ('123456789', '3231000','01');

INSERT INTO account_details (account_number, account_balance, cust_id)
VALUES ('987654321', '125125','02');

-- Insert record into account transfer details table
INSERT INTO account_transfer_details (date_of_transfer, recipient_name, status, account_balance, account_number, cust_id)
VALUES ('2023-06-12', 'Bhavisha','approved', '3231997', '123456789', '01');

INSERT INTO account_transfer_details (date_of_transfer, recipient_name, status, account_balance, account_number, cust_id)
VALUES ('2023-06-11', 'Bhaumik','approved', '125125', '987654321', '02');

-- Step-2
SET autocommit = 0;
-- Create a transaction environment
START TRANSACTION;

UPDATE account_details
SET account_balance = account_balance - 1000
WHERE account_number = 987654321;

SELECT * FROM account_details;

INSERT INTO account_transfer_details (date_of_transfer, recipient_name, status, account_balance, account_number, cust_id)
VALUES ('2023-06-10', 'New Change', 'waiting', '124125', '987654321', '02');

SELECT * FROM account_transfer_details;

-- Step-3
-- Step-3.1
-- Scenario-1: For the approved transaction status
-- Create a stored procedure to perform the update balance

DELIMITER //
CREATE PROCEDURE transaction_verification1()
BEGIN
	SET autocommit = 0;
    
	START TRANSACTION;

	-- Create a savepoint before transaction
    SAVEPOINT before_transaction_update;
    
    UPDATE account_details
	SET account_balance = account_balance - 1000
	WHERE account_number = 987654321;

	SELECT * FROM account_details;

	INSERT INTO account_transfer_details (date_of_transfer, recipient_name, status, account_balance, account_number, cust_id)
	VALUES ('2023-06-10', 'New Change', 'waiting', '124125', '987654321', '02');
    
    -- Scenario-1: Update status as approved
    UPDATE account_transfer_details
	SET status = 'approved'
	WHERE account_number = 987654321;
        
	SET @transaction_status = (SELECT status FROM account_transfer_details);

	-- Consider a condition, that if transaction status is aprroved, do commit. Else rollback to the SAVEPOINT created
    IF (@transaction_status = 'approved' )
    THEN
		COMMIT;
        
	ELSEIF @transaction_status = 'declined' 
    THEN
		ROLLBACK TO SAVEPOINT before_transaction_update;
	END IF;
    
    SELECT * FROM account_transfer_details;
END //

DELIMITER ;

CALL transaction_verification1();

-- Step-3.2
-- Scenario-2: For the declined transaction status
-- Create a stored procedure to perform the update balance

DELIMITER //
CREATE PROCEDURE transaction_verification2()
BEGIN
	SET autocommit = 0;
    
	START TRANSACTION;

	-- Create a savepoint before transaction
    SAVEPOINT before_transaction_update;
    
    -- Scenario-2: Update status as declined
    UPDATE account_transfer_details
	SET status = 'declined'
	WHERE account_number = 987654321;
        
	SET @transaction_status = (SELECT status FROM account_transfer_details);

	-- Consider a condition, that if transaction status is aprroved, do commit. Else rollback to the SAVEPOINT created
    IF (@transaction_status = 'approved' )
    THEN
		COMMIT;
        
	ELSEIF @transaction_status = 'declined' 
    THEN
		ROLLBACK TO SAVEPOINT before_transaction_update;
	END IF;
    
    SELECT * FROM account_transfer_details;
END //

DELIMITER ;

CALL transaction_verification2();