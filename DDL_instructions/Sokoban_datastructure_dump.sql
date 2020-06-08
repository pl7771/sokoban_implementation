-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: ID222177_g86.db.webhosting.be    Database: ID222177_g86
-- ------------------------------------------------------
-- Server version	5.7.29-32-log

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
-- Table structure for table `Spel`
--

DROP TABLE IF EXISTS `Spel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Spel` (
  `spelID` int(11) NOT NULL AUTO_INCREMENT,
  `naamSpel` varchar(50) NOT NULL,
  `gebruikersnaam` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`spelID`),
  KEY `FK_gebruikersnaam_idx` (`gebruikersnaam`),
  CONSTRAINT `FK_gebruikersnaam` FOREIGN KEY (`gebruikersnaam`) REFERENCES `Speler` (`gebruikersnaam`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Spelbord`
--

DROP TABLE IF EXISTS `Spelbord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Spelbord` (
  `SpelbordID` int(11) NOT NULL AUTO_INCREMENT,
  `levelMap` varchar(150) NOT NULL,
  `SpelID` int(11) NOT NULL,
  PRIMARY KEY (`SpelbordID`),
  KEY `Spelbord_ibfk_1` (`SpelID`),
  CONSTRAINT `Spelbord_ibfk_1` FOREIGN KEY (`SpelID`) REFERENCES `Spel` (`spelID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Speler`
--

DROP TABLE IF EXISTS `Speler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Speler` (
  `gebruikersnaam` varchar(50) NOT NULL,
  `wachtwoord` varchar(20) NOT NULL,
  `adminrechten` tinyint(1) DEFAULT NULL,
  `naam` varchar(100) DEFAULT NULL,
  `voornaam` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`gebruikersnaam`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'ID222177_g86'
--

--
-- Dumping routines for database 'ID222177_g86'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-14 20:03:27
