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
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL,
  `p_number` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `tariff_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj6fluygwv28m5ra2sj703c61w` (`customer_id`),
  KEY `FKr9ow9rvoc733hmd7wfglmft3g` (`tariff_id`),
  CONSTRAINT `FKj6fluygwv28m5ra2sj703c61w` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKr9ow9rvoc733hmd7wfglmft3g` FOREIGN KEY (`tariff_id`) REFERENCES `tariff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contract_options`
--

DROP TABLE IF EXISTS `contract_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract_options` (
  `Contract_id` int(11) NOT NULL,
  `options_id` int(11) NOT NULL,
  KEY `FKdkigbvyfkmr69bhdk14xgi5b` (`options_id`),
  KEY `FK37peinimwve8g24934lmwhdby` (`Contract_id`),
  CONSTRAINT `FK37peinimwve8g24934lmwhdby` FOREIGN KEY (`Contract_id`) REFERENCES `contract` (`id`),
  CONSTRAINT `FKdkigbvyfkmr69bhdk14xgi5b` FOREIGN KEY (`options_id`) REFERENCES `t_option` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `options_compat`
--

DROP TABLE IF EXISTS `options_compat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options_compat` (
  `option_id` int(11) NOT NULL,
  `compat_option_id` int(11) NOT NULL,
  KEY `option_id` (`option_id`),
  KEY `compat_option_id` (`compat_option_id`),
  CONSTRAINT `options_compat_ibfk_1` FOREIGN KEY (`option_id`) REFERENCES `t_option` (`id`) ON DELETE CASCADE,
  CONSTRAINT `options_compat_ibfk_2` FOREIGN KEY (`compat_option_id`) REFERENCES `t_option` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `options_required`
--

DROP TABLE IF EXISTS `options_required`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options_required` (
  `option_id` int(11) NOT NULL,
  `required_option_id` int(11) NOT NULL,
  PRIMARY KEY (`option_id`,`required_option_id`),
  KEY `required_option_id` (`required_option_id`),
  CONSTRAINT `options_required_ibfk_1` FOREIGN KEY (`option_id`) REFERENCES `t_option` (`id`),
  CONSTRAINT `options_required_ibfk_2` FOREIGN KEY (`required_option_id`) REFERENCES `t_option` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_option`
--

DROP TABLE IF EXISTS `t_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_option` (
  `id` int(11) NOT NULL,
  `payment` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `description` text NOT NULL,
  `name` varchar(255) NOT NULL,
  `compatible` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tariff`
--

DROP TABLE IF EXISTS `tariff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariff` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `description` text NOT NULL,
  `default_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tariff_options`
--

DROP TABLE IF EXISTS `tariff_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariff_options` (
  `tariff_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  PRIMARY KEY (`tariff_id`,`option_id`),
  KEY `tariff_id` (`tariff_id`),
  KEY `option_id` (`option_id`),
  CONSTRAINT `tariff_options_ibfk_1` FOREIGN KEY (`tariff_id`) REFERENCES `tariff` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tariff_options_ibfk_2` FOREIGN KEY (`option_id`) REFERENCES `t_option` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'CUSTOMER',
  `passport_data` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-09 17:32:19
