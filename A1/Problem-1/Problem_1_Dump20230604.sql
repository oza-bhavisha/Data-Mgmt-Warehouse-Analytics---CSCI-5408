-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: bedbreakfast
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Amenities`
--

DROP TABLE IF EXISTS `Amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Amenities` (
  `Amenities_ID` int NOT NULL,
  `Amenities_Name` varchar(45) NOT NULL,
  `Amenities_Description` varchar(45) DEFAULT NULL,
  `Amenities_Availability` varchar(45) NOT NULL,
  `Room_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Amenities`
--

LOCK TABLES `Amenities` WRITE;
/*!40000 ALTER TABLE `Amenities` DISABLE KEYS */;
/*!40000 ALTER TABLE `Amenities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bed_and_Breakfast`
--

DROP TABLE IF EXISTS `Bed_and_Breakfast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bed_and_Breakfast` (
  `BnB_ID` int NOT NULL,
  `BnB_Email` varchar(45) NOT NULL,
  `BnB_Address` varchar(45) NOT NULL,
  `BnB_Working_Hours` datetime NOT NULL,
  PRIMARY KEY (`BnB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bed_and_Breakfast`
--

LOCK TABLES `Bed_and_Breakfast` WRITE;
/*!40000 ALTER TABLE `Bed_and_Breakfast` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bed_and_Breakfast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Branches`
--

DROP TABLE IF EXISTS `Branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Branches` (
  `Branch_ID` int NOT NULL,
  `Branch_City_Name` varchar(45) NOT NULL,
  `Branch_Location` varchar(45) NOT NULL,
  `Branch_Contact_Number` varchar(45) NOT NULL,
  `BnB_ID` int DEFAULT NULL,
  PRIMARY KEY (`Branch_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Branches`
--

LOCK TABLES `Branches` WRITE;
/*!40000 ALTER TABLE `Branches` DISABLE KEYS */;
/*!40000 ALTER TABLE `Branches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Breakfast_Item`
--

DROP TABLE IF EXISTS `Breakfast_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Breakfast_Item` (
  `Item_ID` int NOT NULL,
  `Item_Name` varchar(45) NOT NULL,
  `Item_Price` decimal(10,0) NOT NULL,
  `Item_Availability` varchar(45) NOT NULL,
  `Item_Description` varchar(45) NOT NULL,
  PRIMARY KEY (`Item_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Breakfast_Item`
--

LOCK TABLES `Breakfast_Item` WRITE;
/*!40000 ALTER TABLE `Breakfast_Item` DISABLE KEYS */;
/*!40000 ALTER TABLE `Breakfast_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Coupon`
--

DROP TABLE IF EXISTS `Coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Coupon` (
  `Coupon_ID` int NOT NULL,
  `Coupon_Code` varchar(45) NOT NULL,
  `Coupon_Discount_Amount` decimal(10,0) NOT NULL,
  `Coupon_Expiration_Date` datetime NOT NULL,
  `Branch_ID` int DEFAULT NULL,
  PRIMARY KEY (`Coupon_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coupon`
--

LOCK TABLES `Coupon` WRITE;
/*!40000 ALTER TABLE `Coupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `Coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dependant`
--

DROP TABLE IF EXISTS `Dependant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dependant` (
  `Dependant_Name` varchar(45) NOT NULL,
  `Dependant_Gender` varchar(45) DEFAULT NULL,
  `Dependant_Relationship` varchar(45) NOT NULL,
  `Dependant_Date_of_Birth` datetime NOT NULL,
  `Staff_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dependant`
--

LOCK TABLES `Dependant` WRITE;
/*!40000 ALTER TABLE `Dependant` DISABLE KEYS */;
/*!40000 ALTER TABLE `Dependant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guest`
--

DROP TABLE IF EXISTS `Guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Guest` (
  `Guest_ID` int NOT NULL,
  `Guest_Name` varchar(45) NOT NULL,
  `Guest_Address` varchar(45) DEFAULT NULL,
  `Guest_Email` varchar(45) NOT NULL,
  `Guest_Preferences` varchar(45) NOT NULL,
  `Reservation_ID` int DEFAULT NULL,
  PRIMARY KEY (`Guest_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guest`
--

LOCK TABLES `Guest` WRITE;
/*!40000 ALTER TABLE `Guest` DISABLE KEYS */;
/*!40000 ALTER TABLE `Guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guest_Contact`
--

DROP TABLE IF EXISTS `Guest_Contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Guest_Contact` (
  `Guest_Contact_Number` varchar(45) NOT NULL,
  `Guest_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guest_Contact`
--

LOCK TABLES `Guest_Contact` WRITE;
/*!40000 ALTER TABLE `Guest_Contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `Guest_Contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ingredients`
--

DROP TABLE IF EXISTS `Ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ingredients` (
  `Ingredients` varchar(45) NOT NULL,
  `Item_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredients`
--

LOCK TABLES `Ingredients` WRITE;
/*!40000 ALTER TABLE `Ingredients` DISABLE KEYS */;
/*!40000 ALTER TABLE `Ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Invoice`
--

DROP TABLE IF EXISTS `Invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Invoice` (
  `Invoice_ID` int NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Payment_Type` varchar(45) DEFAULT NULL,
  `Reservation_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Invoice`
--

LOCK TABLES `Invoice` WRITE;
/*!40000 ALTER TABLE `Invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `Invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Reservation` (
  `Reservation_ID` int NOT NULL,
  `Check_in_Date` datetime NOT NULL,
  `Check_out_Date` datetime NOT NULL,
  `Guest_ID` int DEFAULT NULL,
  `Room_ID` int DEFAULT NULL,
  PRIMARY KEY (`Reservation_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Room` (
  `Room_ID` int NOT NULL,
  `Room_Type` varchar(45) DEFAULT NULL,
  `Room_Capacity` varchar(45) NOT NULL,
  `Room_Availability` varchar(45) NOT NULL,
  `Room_Price` decimal(10,0) NOT NULL,
  `Branch_ID` int DEFAULT NULL,
  PRIMARY KEY (`Room_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `Staff_ID` int NOT NULL,
  `Employee_Name` varchar(45) NOT NULL,
  `Staff_Address` varchar(45) DEFAULT NULL,
  `Staff_Email` varchar(45) NOT NULL,
  `Staff_Position` varchar(45) NOT NULL,
  `Staff_Age` varchar(45) NOT NULL,
  `Staff_Salary` decimal(10,0) NOT NULL,
  `Staff_Gender` varchar(45) NOT NULL,
  `Staff_Date_of_Birth` datetime NOT NULL,
  `Staff_Date_of_Joining` datetime NOT NULL,
  `Branch_ID` int DEFAULT NULL,
  PRIMARY KEY (`Staff_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff_Contact`
--

DROP TABLE IF EXISTS `Staff_Contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff_Contact` (
  `Staff_Contact_Number` varchar(45) NOT NULL,
  `Staff_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff_Contact`
--

LOCK TABLES `Staff_Contact` WRITE;
/*!40000 ALTER TABLE `Staff_Contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `Staff_Contact` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-04 20:23:46
