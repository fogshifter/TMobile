-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: provider
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,'89277798576',2,1,0),(3,'89277798635',6,2,0),(4,'89277798665',7,5,0),(5,'89277798696',8,1,0),(7,'89277798765',9,3,0),(10,'89277798853',5,4,0),(11,'89277798885',10,5,0);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contract_options`
--

LOCK TABLES `contract_options` WRITE;
/*!40000 ALTER TABLE `contract_options` DISABLE KEYS */;
INSERT INTO `contract_options` VALUES (3,1),(3,2),(4,1),(4,4),(5,1),(7,1),(7,2),(7,4),(10,1),(11,1),(11,2),(11,3);
/*!40000 ALTER TABLE `contract_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `options_compat`
--

LOCK TABLES `options_compat` WRITE;
/*!40000 ALTER TABLE `options_compat` DISABLE KEYS */;
INSERT INTO `options_compat` VALUES (4,2),(4,3),(5,2),(6,1),(6,3),(6,5);
/*!40000 ALTER TABLE `options_compat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `options_required`
--

LOCK TABLES `options_required` WRITE;
/*!40000 ALTER TABLE `options_required` DISABLE KEYS */;
INSERT INTO `options_required` VALUES (2,1),(3,1),(4,1),(5,1),(3,2),(5,4);
/*!40000 ALTER TABLE `options_required` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_option`
--

LOCK TABLES `t_option` WRITE;
/*!40000 ALTER TABLE `t_option` DISABLE KEYS */;
INSERT INTO `t_option` VALUES (1,10,1,'Option 1 desc','Option 1',1),(2,20,2,'Option 2 desc','Option 2',1),(3,30,3,'Option 3 desc','Option 3',1),(4,40,4,'Option 4 desc','Option 4',1),(5,50,5,'Option 5 desc','Option 5',0),(6,60,6,'Option 6 desc','Option 6',0);
/*!40000 ALTER TABLE `t_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tariff`
--

LOCK TABLES `tariff` WRITE;
/*!40000 ALTER TABLE `tariff` DISABLE KEYS */;
INSERT INTO `tariff` VALUES (1,'Tariff 1',1,'Tariff 1 desc',0),(2,'Tariff 2',2,'Tariff 2 desc',0),(3,'Tariff 3',3,'Tariff 3 desc',0),(4,'Tariff 4',4,'Tariff 4 desc',0),(5,'Tariff 5',5,'Tariff 5 desc',0);
/*!40000 ALTER TABLE `tariff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tariff_options`
--

LOCK TABLES `tariff_options` WRITE;
/*!40000 ALTER TABLE `tariff_options` DISABLE KEYS */;
INSERT INTO `tariff_options` VALUES (1,1),(1,4),(2,1),(2,2),(2,3),(2,4),(3,1),(3,2),(3,4),(3,5),(4,1),(4,2),(4,6),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6);
/*!40000 ALTER TABLE `tariff_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Sun','1970-11-27 16:25:08.255000','admin2@mail.com','Super1','White1','$2a$11$6N/tAQ3Xtif9fbCS2HQgVOMbeLE770rKV6EZGpSxzszOAIQPUSIc.','ROLE_MANAGER',''),(2,'Moon','2002-08-25 19:42:47.381000','crotch@mail.com','Wafle','Sugah','$2a$11$PNhRwxpp1AsOqYPavIZcaOfMYe/JAq5ax0SjKtgV2X07h1MrOw01m','ROLE_CUSTOMER',''),(4,'Sun','1970-11-27 16:25:08.255000','admin1@mail.com','Super','White','$2a$11$6N/tAQ3Xtif9fbCS2HQgVOMbeLE770rKV6EZGpSxzszOAIQPUSIc.','ROLE_MANAGER',''),(5,'Contract 1 address','2018-01-01 00:00:00.000000','Contract1@mail.com','Contract 1','Contract 1 last name','$2a$11$C3WCUr3.LB8rcGZDnBEk6OiJ7mtcI7CgHsxnNWBUaZGnLNFPAw99i','ROLE_CUSTOMER','Contract 1 pass data'),(6,'Contract 2 address','2018-01-02 00:00:00.000000','Contract2@mail.com','Contract 2','Contract 2 last name','$2a$11$oH2XNObB.QFHoQFf2qCiGOoytuYfcFEXQn2sGEWqfr9Otxk5CmDn6','ROLE_CUSTOMER','Contract 2 pass data'),(7,'Contract 3 address','2018-01-03 00:00:00.000000','Contract3@mail.com','Contract 3','Contract 3 last name','$2a$11$lgH9OpeGSNJYj8fsj1sZd.XY8RJSAmvyaP/ZNro0SbKk.KdyCSdQK','ROLE_CUSTOMER','Contract 3 pass data'),(8,'address','2018-01-04 00:00:00.000000','Contract4@mail.com','Contract 4','Contract 4 last name','$2a$11$obELkhQIGWgKwJn.0nvBJ.jcXi0BQWJJbbjZCFnMQwy.NMB22tjYO','ROLE_CUSTOMER','Contract 4 pass data'),(9,'Contract 1 address','2018-01-01 00:00:00.000000','Customer1@mail.com','Contract 1','Contract 1 last name','$2a$11$vrw7uoECE6sZVJx1hUNIG.wIycTBpK/Nr6vtSFsbJnxMQ./GjQqG6','ROLE_CUSTOMER','Contract 1 pass data'),(10,'Contract 1 address','2018-01-01 00:00:00.000000','Contract@mail.com','Contract 1','Contract 1 last name','$2a$11$pxpGvxpbQKVV0bPoCyY7D.yALGdWAdnHnLh.DyuXLrSeLvlbWufhC','ROLE_CUSTOMER','Contract 1 pass data');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-29 14:10:09
