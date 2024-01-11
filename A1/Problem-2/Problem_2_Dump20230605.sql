-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: provincial_parks
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
-- Table structure for table `adventure_sport`
--

DROP TABLE IF EXISTS `adventure_sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adventure_sport` (
  `adventure_sport_id` int NOT NULL,
  `adventure_sport_name` varchar(45) NOT NULL,
  `adventure_sport_description` varchar(45) DEFAULT NULL,
  `adventure_sport_operation_timings` datetime DEFAULT NULL,
  `adventure_sport_operation_days` varchar(45) NOT NULL,
  `adventure_sport_age_group` int NOT NULL,
  `park_id` int NOT NULL,
  PRIMARY KEY (`adventure_sport_id`),
  KEY `fk1_park_id_idx` (`park_id`),
  CONSTRAINT `fk1_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adventure_sport`
--

LOCK TABLES `adventure_sport` WRITE;
/*!40000 ALTER TABLE `adventure_sport` DISABLE KEYS */;
/*!40000 ALTER TABLE `adventure_sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amusement_ride`
--

DROP TABLE IF EXISTS `amusement_ride`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amusement_ride` (
  `amusement_ride_id` int NOT NULL,
  `amusement_ride_name` varchar(45) NOT NULL,
  `amusement_ride_type` varchar(45) NOT NULL,
  `amusement_ride_description` varchar(45) NOT NULL,
  `amusement_ride_age_group` varchar(45) NOT NULL,
  `park_id` int NOT NULL,
  PRIMARY KEY (`amusement_ride_id`),
  KEY `fk2_park_id_idx` (`park_id`),
  CONSTRAINT `fk2_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amusement_ride`
--

LOCK TABLES `amusement_ride` WRITE;
/*!40000 ALTER TABLE `amusement_ride` DISABLE KEYS */;
/*!40000 ALTER TABLE `amusement_ride` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` int NOT NULL,
  `department_name` varchar(45) NOT NULL,
  `department_description` varchar(45) DEFAULT NULL,
  `department_contact` int DEFAULT NULL,
  `department_email` varchar(45) NOT NULL,
  `staff_id` int DEFAULT NULL,
  `park_id` int DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  KEY `staff_id_idx` (`staff_id`),
  KEY `fk9_park_id_idx` (`park_id`),
  CONSTRAINT `fk9_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`),
  CONSTRAINT `staff_id` FOREIGN KEY (`staff_id`) REFERENCES `Staff` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dependant`
--

DROP TABLE IF EXISTS `Dependant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dependant` (
  `dependant_name` varchar(45) NOT NULL,
  `dependant_gender` varchar(45) DEFAULT NULL,
  `dependant_relationship` varchar(45) NOT NULL,
  `dependant_Email` varchar(45) DEFAULT NULL,
  `dependant_date_of_birth` datetime NOT NULL,
  `staff_id` int NOT NULL,
  KEY `staff_id` (`staff_id`),
  CONSTRAINT `dependant_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `Staff` (`staff_id`)
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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `event_id` int NOT NULL,
  `event_type` varchar(45) NOT NULL,
  `event_date` datetime NOT NULL,
  `event_name` varchar(45) NOT NULL,
  `event_time` datetime NOT NULL,
  `event_description` varchar(45) DEFAULT NULL,
  `event_sponsors` varchar(45) NOT NULL,
  `event_location` varchar(45) NOT NULL,
  `park_id` int NOT NULL,
  `visitor_id` int NOT NULL,
  KEY `fk3_park_id_idx` (`park_id`),
  KEY `visitor_id_idx` (`visitor_id`),
  CONSTRAINT `fk3_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`),
  CONSTRAINT `visitor_id` FOREIGN KEY (`visitor_id`) REFERENCES `visitor` (`visitor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_sponsors`
--

DROP TABLE IF EXISTS `event_sponsors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_sponsors` (
  `sponsors_name` int NOT NULL,
  `sponsor_contact_number` varchar(45) NOT NULL,
  `sponsorship_amount` decimal(30,0) DEFAULT NULL,
  `sponsorship_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_sponsors`
--

LOCK TABLES `event_sponsors` WRITE;
/*!40000 ALTER TABLE `event_sponsors` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_sponsors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility`
--

DROP TABLE IF EXISTS `facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facility` (
  `facility_id` int NOT NULL,
  `facility_name` varchar(45) NOT NULL,
  `facility_description` varchar(45) DEFAULT NULL,
  `park_id` int NOT NULL,
  PRIMARY KEY (`facility_id`),
  KEY `park_id_idx` (`park_id`),
  CONSTRAINT `fk_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility`
--

LOCK TABLES `facility` WRITE;
/*!40000 ALTER TABLE `facility` DISABLE KEYS */;
/*!40000 ALTER TABLE `facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `hotel_id` int NOT NULL,
  `hotel_name` varchar(45) DEFAULT NULL,
  `hotel_description` varchar(45) DEFAULT NULL,
  `hotel_location` varchar(45) DEFAULT NULL,
  `hotel_type` varchar(45) DEFAULT NULL,
  `hotel_rating` int NOT NULL,
  `room_id` int NOT NULL,
  `hotel_website` varchar(45) NOT NULL,
  `park_id` int NOT NULL,
  PRIMARY KEY (`hotel_id`),
  KEY `park_id_idx` (`park_id`),
  CONSTRAINT `park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_room`
--

DROP TABLE IF EXISTS `hotel_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_room` (
  `room_id` int NOT NULL,
  `room_type` varchar(45) NOT NULL,
  `room_capacity` varchar(45) NOT NULL,
  `room_availability` varchar(45) NOT NULL,
  `room_price` varchar(45) NOT NULL,
  `hotel_id` int NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `hotel_id_idx` (`hotel_id`),
  CONSTRAINT `hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_room`
--

LOCK TABLES `hotel_room` WRITE;
/*!40000 ALTER TABLE `hotel_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `park`
--

DROP TABLE IF EXISTS `park`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `park` (
  `park_id` int NOT NULL,
  `park_name` varchar(45) NOT NULL,
  `park_address` varchar(45) NOT NULL,
  `park_timings` datetime NOT NULL,
  `park_working_days` varchar(45) NOT NULL,
  `park_description` varchar(45) DEFAULT NULL,
  `park_website` varchar(45) DEFAULT NULL,
  `park_location` varchar(45) NOT NULL,
  `park_restaurant_id` int NOT NULL,
  `park_lake_id` int NOT NULL,
  `park_hiking_trail_id` int NOT NULL,
  `park_biking_trail_id` int NOT NULL,
  PRIMARY KEY (`park_id`),
  KEY `park_restaurant_id_idx` (`park_restaurant_id`),
  KEY `park_lake_id_idx` (`park_lake_id`),
  KEY `park_hiking_trail_id_idx` (`park_biking_trail_id`,`park_hiking_trail_id`,`park_lake_id`,`park_restaurant_id`),
  KEY `park_hiking_trail_id_idx1` (`park_hiking_trail_id`),
  CONSTRAINT `park_biking_trail_id` FOREIGN KEY (`park_biking_trail_id`) REFERENCES `park_biking_trails` (`park_biking_trail_id`),
  CONSTRAINT `park_hiking_trail_id` FOREIGN KEY (`park_hiking_trail_id`) REFERENCES `park_hiking_trails` (`park_hiking_trail_id`),
  CONSTRAINT `park_lake_id` FOREIGN KEY (`park_lake_id`) REFERENCES `park_lake` (`park_lake_id`),
  CONSTRAINT `park_restaurant_id` FOREIGN KEY (`park_restaurant_id`) REFERENCES `park_restaurant` (`park_restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `park`
--

LOCK TABLES `park` WRITE;
/*!40000 ALTER TABLE `park` DISABLE KEYS */;
/*!40000 ALTER TABLE `park` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `park_biking_trails`
--

DROP TABLE IF EXISTS `park_biking_trails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `park_biking_trails` (
  `park_biking_trail_id` int NOT NULL,
  `biking_trail_name` varchar(45) DEFAULT NULL,
  `biking_trail_description` varchar(45) DEFAULT NULL,
  `biking_trail_distance` varchar(45) NOT NULL,
  `biking_trail_difficulty_level` varchar(45) NOT NULL,
  `biking_trail_location` varchar(45) NOT NULL,
  `biking_trail_age_group` varchar(45) NOT NULL,
  `park_id` int NOT NULL,
  PRIMARY KEY (`park_biking_trail_id`),
  KEY `fk7_park_id_idx` (`park_id`),
  CONSTRAINT `fk7_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `park_biking_trails`
--

LOCK TABLES `park_biking_trails` WRITE;
/*!40000 ALTER TABLE `park_biking_trails` DISABLE KEYS */;
/*!40000 ALTER TABLE `park_biking_trails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `park_hiking_trails`
--

DROP TABLE IF EXISTS `park_hiking_trails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `park_hiking_trails` (
  `park_hiking_trail_id` int NOT NULL,
  `hiking_trail_name` varchar(45) NOT NULL,
  `hiking_trail_description` varchar(45) DEFAULT NULL,
  `hiking_trail_distance` varchar(45) DEFAULT NULL,
  `hiking_trail_difficulty_level` varchar(45) NOT NULL,
  `hiking_trail_location` varchar(45) NOT NULL,
  `hiking_trail_age_group` varchar(45) NOT NULL,
  `park_id` int NOT NULL,
  PRIMARY KEY (`park_hiking_trail_id`),
  KEY `fk8_park_id_idx` (`park_id`),
  CONSTRAINT `fk8_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `park_hiking_trails`
--

LOCK TABLES `park_hiking_trails` WRITE;
/*!40000 ALTER TABLE `park_hiking_trails` DISABLE KEYS */;
/*!40000 ALTER TABLE `park_hiking_trails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `park_lake`
--

DROP TABLE IF EXISTS `park_lake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `park_lake` (
  `park_lake_id` int NOT NULL,
  `lake_name` varchar(45) DEFAULT NULL,
  `lake_description` varchar(45) DEFAULT NULL,
  `lake_type` varchar(45) NOT NULL,
  `lake_depth` varchar(45) NOT NULL,
  `lake_location` varchar(45) NOT NULL,
  `park_id` int NOT NULL,
  PRIMARY KEY (`park_lake_id`),
  KEY `fk6_park_id_idx` (`park_id`),
  CONSTRAINT `fk6_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `park_lake`
--

LOCK TABLES `park_lake` WRITE;
/*!40000 ALTER TABLE `park_lake` DISABLE KEYS */;
/*!40000 ALTER TABLE `park_lake` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `park_restaurant`
--

DROP TABLE IF EXISTS `park_restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `park_restaurant` (
  `park_restaurant_id` int NOT NULL,
  `restaurant_name` varchar(45) NOT NULL,
  `restaurant_email` varchar(45) NOT NULL,
  `restaurant_contact_number` varchar(45) NOT NULL,
  `restaurant_address` varchar(45) DEFAULT NULL,
  `restaurant_working_hours` datetime NOT NULL,
  `park_id` int NOT NULL,
  PRIMARY KEY (`park_restaurant_id`),
  KEY `fk4_park_id_idx` (`park_id`),
  CONSTRAINT `fk5_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `park_restaurant`
--

LOCK TABLES `park_restaurant` WRITE;
/*!40000 ALTER TABLE `park_restaurant` DISABLE KEYS */;
/*!40000 ALTER TABLE `park_restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `staff_id` int NOT NULL,
  `employee_name` varchar(45) NOT NULL,
  `staff_address` varchar(45) DEFAULT NULL,
  `staff_email` varchar(45) NOT NULL,
  `staff_position` varchar(45) NOT NULL,
  `staff_age` varchar(45) NOT NULL,
  `staff_salary` decimal(10,0) NOT NULL,
  `staff_gender` varchar(45) NOT NULL,
  `staff_date_of_birth` datetime NOT NULL,
  `staff_date_of_joining` datetime NOT NULL,
  `staff_contact_number` varchar(45) NOT NULL,
  `department_id` int NOT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `departmeny_id_idx` (`department_id`),
  CONSTRAINT `departmeny_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
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
-- Table structure for table `visitor`
--

DROP TABLE IF EXISTS `visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visitor` (
  `visitor_id` int NOT NULL,
  `visitor_name` varchar(45) NOT NULL,
  `visitor_DOB` datetime DEFAULT NULL,
  `visitor_email` varchar(45) NOT NULL,
  `visitor_contact_number` varchar(45) DEFAULT NULL,
  `visitor_age` int NOT NULL,
  `visitor_gender` varchar(45) DEFAULT NULL,
  `visitor_address` varchar(45) DEFAULT NULL,
  `park_id` int NOT NULL,
  `event_id` int NOT NULL,
  PRIMARY KEY (`visitor_id`),
  KEY `fk4_park_id_idx` (`park_id`),
  KEY `fk2_event_id_idx` (`event_id`),
  CONSTRAINT `fk4_park_id` FOREIGN KEY (`park_id`) REFERENCES `park` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor`
--

LOCK TABLES `visitor` WRITE;
/*!40000 ALTER TABLE `visitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `visitor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-05  2:07:52
