-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lab_2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lab_2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lab_2` ;
USE `lab_2` ;

-- -----------------------------------------------------
-- Table `lab_2`.`Restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab_2`.`Restaurant` (
  `Restaurant_ID` INT NOT NULL,
  `Restaurant_Name` VARCHAR(45) NOT NULL,
  `Restaurant_Email` VARCHAR(45) NOT NULL,
  `Restaurant_Contact_Number` VARCHAR(45) NOT NULL,
  `Restaurant_Address` VARCHAR(45) NOT NULL,
  `Restaurant_Working_Hours` DATETIME NOT NULL,
  PRIMARY KEY (`Restaurant_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab_2`.`Branches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab_2`.`Branches` (
  `Branch_ID` INT NOT NULL,
  `Branch_City_Name` VARCHAR(45) NOT NULL,
  `Branch_Location` VARCHAR(45) NOT NULL,
  `Branch_Contact_Number` VARCHAR(45) NOT NULL,
  `Restaurant_Restaurant_ID` INT NOT NULL,
  PRIMARY KEY (`Branch_ID`, `Restaurant_Restaurant_ID`),
  INDEX `fk_Branches_Restaurant1_idx` (`Restaurant_Restaurant_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Branches_Restaurant1`
    FOREIGN KEY (`Restaurant_Restaurant_ID`)
    REFERENCES `lab_2`.`Restaurant` (`Restaurant_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab_2`.`Menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab_2`.`Menu` (
  `Menu_ID` INT NOT NULL,
  `Menu_Name` VARCHAR(45) NOT NULL,
  `Menu_Price` DECIMAL(2) NOT NULL,
  `Menu_Ingredients` VARCHAR(45) NOT NULL,
  `Branches_Branch_ID` INT NOT NULL,
  `Branches_Restaurant_Restaurant_ID` INT NOT NULL,
  PRIMARY KEY (`Menu_ID`, `Branches_Branch_ID`, `Branches_Restaurant_Restaurant_ID`),
  UNIQUE INDEX `Menu_Ingredients_UNIQUE` (`Menu_Ingredients` ASC) VISIBLE,
  INDEX `fk_Menu_Branches1_idx` (`Branches_Branch_ID` ASC, `Branches_Restaurant_Restaurant_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Menu_Branches1`
    FOREIGN KEY (`Branches_Branch_ID` , `Branches_Restaurant_Restaurant_ID`)
    REFERENCES `lab_2`.`Branches` (`Branch_ID` , `Restaurant_Restaurant_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab_2`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab_2`.`Employee` (
  `Employee_ID` INT NOT NULL,
  `Employee_Name` VARCHAR(45) NOT NULL,
  `Employee_Gender` VARCHAR(45) NULL,
  `Employee_Email` VARCHAR(45) NULL,
  `Employee_Contact_number` VARCHAR(45) NOT NULL,
  `Employee_Address` VARCHAR(45) NULL,
  `Employee_DateOfBirth` DATETIME NOT NULL,
  `Employee_DateOfJoining` DATETIME NULL,
  `Employee_Age` INT NULL,
  `Employee_Designation` VARCHAR(45) NOT NULL,
  `Branches_Branch_ID` INT NOT NULL,
  PRIMARY KEY (`Employee_ID`, `Branches_Branch_ID`),
  UNIQUE INDEX `Employee_Email_UNIQUE` (`Employee_Email` ASC) VISIBLE,
  INDEX `fk_Employee_Branches1_idx` (`Branches_Branch_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Employee_Branches1`
    FOREIGN KEY (`Branches_Branch_ID`)
    REFERENCES `lab_2`.`Branches` (`Branch_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab_2`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab_2`.`Customer` (
  `Customer_ID` INT NOT NULL,
  `Customer_Name` VARCHAR(45) NOT NULL,
  `Customer_Email` VARCHAR(45) NULL,
  `Customer_Contact_number` VARCHAR(45) NOT NULL,
  `Customer_Address` VARCHAR(45) NOT NULL,
  `Menu_Menu_ID` INT NOT NULL,
  `Employee_Employee_ID` INT NOT NULL,
  `Employee_Branches_Branch_ID` INT NOT NULL,
  `Restaurant_Restaurant_ID` INT NOT NULL,
  PRIMARY KEY (`Customer_ID`, `Menu_Menu_ID`, `Employee_Employee_ID`, `Employee_Branches_Branch_ID`, `Restaurant_Restaurant_ID`),
  UNIQUE INDEX `Customer_Email_UNIQUE` (`Customer_Email` ASC) VISIBLE,
  INDEX `fk_Customer_Menu1_idx` (`Menu_Menu_ID` ASC) VISIBLE,
  INDEX `fk_Customer_Employee1_idx` (`Employee_Employee_ID` ASC, `Employee_Branches_Branch_ID` ASC) VISIBLE,
  INDEX `fk_Customer_Restaurant1_idx` (`Restaurant_Restaurant_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Customer_Menu1`
    FOREIGN KEY (`Menu_Menu_ID`)
    REFERENCES `lab_2`.`Menu` (`Menu_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Employee1`
    FOREIGN KEY (`Employee_Employee_ID` , `Employee_Branches_Branch_ID`)
    REFERENCES `lab_2`.`Employee` (`Employee_ID` , `Branches_Branch_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Restaurant1`
    FOREIGN KEY (`Restaurant_Restaurant_ID`)
    REFERENCES `lab_2`.`Restaurant` (`Restaurant_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab_2`.`Employee_Dependant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab_2`.`Employee_Dependant` (
  `Dependant_Name` INT NOT NULL,
  `Dependant_Gender` VARCHAR(45) NOT NULL,
  `Dependant_DateOfBirth` VARCHAR(45) NOT NULL,
  `Dependant_Relation` VARCHAR(45) NOT NULL,
  `Employee_Employee_ID` INT NOT NULL,
  `Employee_Branches_Branch_ID` INT NOT NULL,
  PRIMARY KEY (`Employee_Employee_ID`, `Employee_Branches_Branch_ID`),
  CONSTRAINT `fk_Employee_Dependant_Employee1`
    FOREIGN KEY (`Employee_Employee_ID` , `Employee_Branches_Branch_ID`)
    REFERENCES `lab_2`.`Employee` (`Employee_ID` , `Branches_Branch_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab_2`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab_2`.`Category` (
  `Category_ID` INT NOT NULL,
  `Category_Name` VARCHAR(45) NOT NULL,
  `Menu_Menu_ID` INT NOT NULL,
  `Menu_Branches_Branch_ID` INT NOT NULL,
  `Menu_Branches_Restaurant_Restaurant_ID` INT NOT NULL,
  PRIMARY KEY (`Category_Name`, `Menu_Menu_ID`, `Menu_Branches_Branch_ID`, `Menu_Branches_Restaurant_Restaurant_ID`),
  INDEX `fk_Category_Menu1_idx` (`Menu_Menu_ID` ASC, `Menu_Branches_Branch_ID` ASC, `Menu_Branches_Restaurant_Restaurant_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Category_Menu1`
    FOREIGN KEY (`Menu_Menu_ID` , `Menu_Branches_Branch_ID` , `Menu_Branches_Restaurant_Restaurant_ID`)
    REFERENCES `lab_2`.`Menu` (`Menu_ID` , `Branches_Branch_ID` , `Branches_Restaurant_Restaurant_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab_2`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab_2`.`Payment` (
  `Payment_ID` INT NOT NULL,
  `Payment_Type` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Payment_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab_2`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab_2`.`Orders` (
  `Oredr_ID` INT NOT NULL,
  `Order_DateTime` DATETIME NOT NULL,
  `Order_Quantity` VARCHAR(45) NOT NULL,
  `Order_Total_Bill` DECIMAL(2) NOT NULL,
  `Delivery_Time` DATETIME NOT NULL,
  `Order_Status` VARCHAR(45) NOT NULL,
  `Delay` DATETIME NOT NULL,
  `Customer_Customer_ID` INT NOT NULL,
  `Payment_Payment_ID` INT NOT NULL,
  PRIMARY KEY (`Oredr_ID`, `Customer_Customer_ID`, `Payment_Payment_ID`),
  INDEX `fk_Orders_Customer1_idx` (`Customer_Customer_ID` ASC) VISIBLE,
  INDEX `fk_Orders_Payment1_idx` (`Payment_Payment_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Orders_Customer1`
    FOREIGN KEY (`Customer_Customer_ID`)
    REFERENCES `lab_2`.`Customer` (`Customer_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Orders_Payment1`
    FOREIGN KEY (`Payment_Payment_ID`)
    REFERENCES `lab_2`.`Payment` (`Payment_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
