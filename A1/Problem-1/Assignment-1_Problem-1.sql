-- New Schema creation for database BedBreakfast
CREATE SCHEMA IF NOT EXISTS bedbreakfast;
USE BedBreakfast;

-- Table 1: Bed_and_Breakfast
CREATE TABLE IF NOT EXISTS bedbreakfast.Bed_and_Breakfast (
BnB_ID INT NOT NULL PRIMARY KEY,
BnB_Email VARCHAR(45) NOT NULL,
BnB_Address VARCHAR(45) NOT NULL,
BnB_Working_Hours DATETIME NOT NULL);

-- Table 2: Branches
CREATE TABLE IF NOT EXISTS bedbreakfast.Branches (
Branch_ID INT NOT NULL PRIMARY KEY,
Branch_City_Name VARCHAR(45) NOT NULL,
Branch_Location VARCHAR(45) NOT NULL,
Branch_Contact_Number VARCHAR(45) NOT NULL,
BnB_ID INT REFERENCES Bed_and_Breakfast(BnB_ID));

-- Table 3: Coupon
CREATE TABLE IF NOT EXISTS bedbreakfast.Coupon (
Coupon_ID INT NOT NULL PRIMARY KEY,
Coupon_Code VARCHAR(45) NOT NULL,
Coupon_Discount_Amount DECIMAL NOT NULL,
Coupon_Expiration_Date DATETIME NOT NULL,
Branch_ID INT REFERENCES Branches(Branch_ID));

-- Table 4: Breakfast_Item
CREATE TABLE IF NOT EXISTS bedbreakfast.Breakfast_Item (
Item_ID INT NOT NULL PRIMARY KEY,
Item_Name VARCHAR(45) NOT NULL,
Item_Price DECIMAL NOT NULL,
Item_Availability VARCHAR(45) NOT NULL,
Item_Description VARCHAR(45) NOT NULL );

-- Table 5: Ingredients
CREATE TABLE IF NOT EXISTS bedbreakfast.Ingredients (
Ingredients VARCHAR(45) NOT NULL,
Item_ID INT REFERENCES Breakfast_Item(Item_ID));

-- Table 6: Staff
CREATE TABLE IF NOT EXISTS bedbreakfast.Staff (
Staff_ID INT NOT NULL PRIMARY KEY,
Employee_Name VARCHAR(45) NOT NULL,
Staff_Address VARCHAR(45) ,
Staff_Email VARCHAR(45) NOT NULL,
Staff_Position VARCHAR(45) NOT NULL,
Staff_Age VARCHAR(45) NOT NULL,
Staff_Salary DECIMAL NOT NULL,
Staff_Gender VARCHAR(45) NOT NULL,
Staff_Date_of_Birth DATETIME NOT NULL,
Staff_Date_of_Joining DATETIME NOT NULL,
Branch_ID INT REFERENCES Branches(Branch_ID));

-- Table 7: Staff_Contact
CREATE TABLE IF NOT EXISTS bedbreakfast.Staff_Contact (
Staff_Contact_Number VARCHAR(45) NOT NULL,
Staff_ID INT REFERENCES Staff(Staff_ID));

-- Table 8: Room
CREATE TABLE IF NOT EXISTS bedbreakfast.Room (
Room_ID INT NOT NULL PRIMARY KEY,
Room_Type VARCHAR(45) ,
Room_Capacity VARCHAR(45) NOT NULL,
Room_Availability VARCHAR(45) NOT NULL,
Room_Price DECIMAL NOT NULL,
Branch_ID INT REFERENCES Branches(Branch_ID));

-- Table 9: Guest
CREATE TABLE IF NOT EXISTS bedbreakfast.Guest (
Guest_ID INT NOT NULL PRIMARY KEY,
Guest_Name VARCHAR(45) NOT NULL,
Guest_Address VARCHAR(45) ,
Guest_Email VARCHAR(45) NOT NULL,
Guest_Preferences VARCHAR(45) NOT NULL,
Reservation_ID INT REFERENCES Reservation(Reservation_ID));

-- Table 10: Guest_Contact
CREATE TABLE IF NOT EXISTS bedbreakfast.Guest_Contact (
Guest_Contact_Number VARCHAR(45) NOT NULL,
Guest_ID INT REFERENCES Guest(Guest_ID));

-- Table 11: Reservation
CREATE TABLE IF NOT EXISTS bedbreakfast.Reservation (
Reservation_ID INT NOT NULL PRIMARY KEY,
Check_in_Date DATETIME NOT NULL,
Check_out_Date DATETIME NOT NULL,
Guest_ID INT REFERENCES Guest(Guest_ID),
Room_ID INT REFERENCES Room(Room_ID));

-- Table 12: Invoice
CREATE TABLE IF NOT EXISTS bedbreakfast.Invoice (
Invoice_ID INT NOT NULL,
Email VARCHAR(45) NOT NULL,
Payment_Type VARCHAR(45) ,
Reservation_ID INT REFERENCES Reservation(Reservation_ID));

-- Table 13: Amenities
CREATE TABLE IF NOT EXISTS bedbreakfast.Amenities (
Amenities_ID INT NOT NULL,
Amenities_Name VARCHAR(45) NOT NULL,
Amenities_Description VARCHAR(45),
Amenities_Availability VARCHAR(45) NOT NULL,
Room_ID INT REFERENCES Room(Room_ID));

-- Table 14: Dependant
CREATE TABLE IF NOT EXISTS bedbreakfast.Dependant (
Dependant_Name VARCHAR(45) NOT NULL,
Dependant_Gender VARCHAR(45),
Dependant_Relationship VARCHAR(45) NOT NULL,
Dependant_Date_of_Birth DATETIME NOT NULL,
Staff_ID INT REFERENCES Staff(Staff_ID));