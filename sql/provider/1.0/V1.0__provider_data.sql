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
INSERT INTO `contract` VALUES (1,'89277798576',2,1,0),(2,'89277708576',2,1,0),(3,'89257708576',5,3,0),(4,'89253708576',6,2,0),(5,'89153708576',7,4,0),(6,'89103708576',8,2,0),(7,'89103808576',5,3,0),(10,'89277700576',9,2,0),(11,'89272700576',10,2,0),(14,'89372700577',11,1,0),(15,'89372701577',12,1,0),(17,'89372701604',15,4,0),(18,'89372701634',16,3,0),(19,'89372701666',17,4,0),(20,'89372701698',18,3,0),(22,'89372701757',20,3,0),(23,'89372701784',21,2,0),(24,'89372701814',22,2,0),(26,'89372701851',24,3,0);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contract_options`
--

LOCK TABLES `contract_options` WRITE;
/*!40000 ALTER TABLE `contract_options` DISABLE KEYS */;
INSERT INTO `contract_options` VALUES (10,10),(10,12),(14,5),(14,6),(14,7),(14,10),(14,11),(14,12),(15,5),(15,10),(15,11),(15,12),(7,1),(7,2),(17,2),(17,3),(17,10),(17,11),(17,12),(18,1),(18,4),(18,9),(18,12),(11,2),(11,3),(19,8),(19,9),(20,3),(20,6),(20,12),(4,9),(4,12),(3,1),(3,2),(3,9),(3,12),(22,1),(22,2),(22,9),(22,12),(23,1),(23,2),(23,9),(23,12),(24,2),(24,3),(1,2),(1,5),(1,10),(1,11),(1,12),(26,1),(26,2),(26,9),(26,12),(2,3),(2,5),(2,10),(2,11),(2,12);
/*!40000 ALTER TABLE `contract_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `options_compat`
--

LOCK TABLES `options_compat` WRITE;
/*!40000 ALTER TABLE `options_compat` DISABLE KEYS */;
INSERT INTO `options_compat` VALUES (13,1),(13,2);
/*!40000 ALTER TABLE `options_compat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `options_required`
--

LOCK TABLES `options_required` WRITE;
/*!40000 ALTER TABLE `options_required` DISABLE KEYS */;
/*!40000 ALTER TABLE `options_required` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_option`
--

LOCK TABLES `t_option` WRITE;
/*!40000 ALTER TABLE `t_option` DISABLE KEYS */;
INSERT INTO `t_option` VALUES (1,5,10,'Fastest unlimited 2G internet in the world, join today!','Unlimited 2G',1),(2,10,15,'Serf world wide internet without any further delay!','Superhit 3G',1),(3,2,5,'10 hours of calls, almost for free!','Unlimited 10',1),(4,3,15,'20 hours of calls, for speeky persons!','Unlimited 20',1),(5,1,5,'Base package of services.','Small',1),(6,4,8,'1 hour of calls and 1GB of internet for free','Medium',1),(7,10,20,'5 hour of calls and 5GB of internet for free','Large',1),(8,15,30,'10 hour of calls and 10GB of internet for free','Extra-Large',1),(9,30,100,'Unlimited calls and 100GB of internet','Black Monday',1),(10,0,5,'0.1c for 1 second of call','Seconds',1),(11,0,5,'1GB for 1$','Internet',1),(12,0,5,'1 MMS for 1$','MMS',1),(13,0,0,'test1 desc','test1',1);
/*!40000 ALTER TABLE `t_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tariff`
--

LOCK TABLES `tariff` WRITE;
/*!40000 ALTER TABLE `tariff` DISABLE KEYS */;
INSERT INTO `tariff` VALUES (1,'Orange',40,'Orange tariff superhit qweasdgrbergd fgdfgdgf etrerte dgdfg asdads egfdgdfg',1),(2,'Black',400,'Black tariff superhit asdhjyknf ujhfhd tyhfdsf erw yjykiykiugnfgd rrg',0),(3,'Greedy Green',1200,'Green tariff superhit qweasdgrbergd fgdfgdgf etrerte dgdfg asdads egfdgdfg',0),(4,'Murder face',10,'Murderface tariff superhit qweasdgrbergd fgdfgdgf etrerte dgdfg asdads egfdgdfg',0),(5,'Atica',102,'Atica tariff superhit qweasdgrbergd fgdfgdgf etrerte dgdfg asdads egfdgdfg',0);
/*!40000 ALTER TABLE `tariff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tariff_options`
--

LOCK TABLES `tariff_options` WRITE;
/*!40000 ALTER TABLE `tariff_options` DISABLE KEYS */;
INSERT INTO `tariff_options` VALUES (1,5),(1,10),(1,11),(1,12),(2,9),(2,12),(3,2),(3,9),(3,12),(4,10),(4,11),(4,12),(5,4),(5,5);
/*!40000 ALTER TABLE `tariff_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'marylon st. miami','1991-04-23 19:42:40.224000','qwerty@bredor.com','Jay','By','qwerty1','CUSTOMER',''),(2,'Moon','2002-08-25 19:42:47.381000','crotch@mail.com','Wafle','Sugah','$2a$11$PNhRwxpp1AsOqYPavIZcaOfMYe/JAq5ax0SjKtgV2X07h1MrOw01m','ROLE_CUSTOMER',''),(3,'Sun','2002-09-28 00:45:55.692000','god@gmail.com','Super','Bad','$2a$11$FY.fFkqERl/Ae97X6kKLzubvSnzs9ulvZ0KxOuhaOtomVeOWqxY62',NULL,''),(4,'Sun','1970-11-27 16:25:08.255000','supergod@gmail.com','Super','White','$2a$11$6N/tAQ3Xtif9fbCS2HQgVOMbeLE770rKV6EZGpSxzszOAIQPUSIc.','ROLE_MANAGER',''),(5,'Alaska, 5 woods st. h. 5','1975-06-24 07:26:22.412000','alaska@mail.com','Town','Brown',NULL,'ROLE_CUSTOMER',''),(6,'Texas, 7 cowboys st. h. 7','1987-09-25 03:24:28.743000','texas@mail.com','Orange','Juice',NULL,'ROLE_CUSTOMER',''),(7,'Washington, White house','1958-10-26 19:43:53.566000','washington@mail.com','Power','Wolf',NULL,'ROLE_CUSTOMER',''),(8,'Florida, GBO inc.','1962-05-27 23:39:05.601000','florida@mail.com','Static','X',NULL,'ROLE_CUSTOMER',''),(9,'test address','1983-01-06 00:09:00.000000','test@mail.com','New contract test','last name test','$2a$11$xpT5drXASr4ViAqnM0IspexqvttImSry8evc91aB3gqxry8DLLP8e',NULL,'34567891'),(10,'5432345333434','2015-01-02 00:12:00.000000','session_test@mail.com','session test','qwerty','$2a$11$KGDMM.i6TVE4SmbJHbcJVu1HZbfpbIu.xvbJD/eCr2zrk9KPvI6Jy',NULL,'12345'),(11,'address','2017-01-31 00:12:00.000000','email@email.com','hjgf','hmjngbfv','$2a$11$.bMueVslutBdt7p5HwuFCOkLwfivYRDS0Tq1goRCYsOS36eEQP6ti',NULL,'data'),(12,'address','2017-01-31 00:12:00.000000','emaoil@email.com','hjgf','hmjngbfv','$2a$11$V1AuY6k43L5gbadORny5.uXvsowFtnuUEdTS8G7Id9P2rZz9b34x2',NULL,'data'),(13,'uyhj','2018-01-31 00:12:00.000000','t@q.q','test phone','test name','$2a$11$y5R7Ok6.uyouCmWgpwhnZulMD4728Sty0LJhCFxVpndDvHqhoSxWq',NULL,'12345'),(14,'123','2016-01-31 00:01:00.000000','kjhhgj','w','q','$2a$11$GfZ/xw6V0VJilto/Ydr/POEW2E6k/pWKIZhKpsqHkehBp3qkvVpPe',NULL,'213'),(15,'432',NULL,'mnbvc','q','q','$2a$11$2Ucz2IjNZGkqPViS3RqPC.PBaFZWDwFCEcp.63h4ht6R9qZNFEUvq',NULL,'5432'),(16,'fg','2018-01-31 00:12:00.000000','g','er','re','$2a$11$MIPzxLthjJtojTj5J71siegkZYibhrCvCISgEDPazMOdeqfcyXaXe',NULL,'fg'),(17,'addres qwe','2018-01-01 00:12:00.000000','test@mailexample.com','asd','asd','$2a$11$w5bv6GCyE3RM2ERJKTA6Du51mvw/0TowAsBsxGrtb6Gc.Grfmb7VS',NULL,'qwer'),(18,'123456','2018-01-02 00:12:00.000000','asd','asdad','hf','$2a$11$92SnxEqKeyQzAbKFYsXGmeteYIZrlxH0Hr3YC.pZVXemajXy9liFK',NULL,'asdasd'),(19,'','2018-01-28 00:04:00.000000','','','','$2a$11$PcoVjd80sUX2iNfDJQ1tk.it1vIgX5GWYGJIaAUL4/mZAEWcdFcNq',NULL,''),(20,'qwe','2018-01-07 00:04:00.000000','test0@mail.com','test0 name','test0 lname','$2a$11$P8PKG69Yh3Hep9qmGeYx.u1OFWOP1ahlEXhxA0gIzLCxZla14rFDy',NULL,'qwe'),(21,'dfg dfgd ','2018-01-05 00:04:00.000000','test1@mail.com','test name1','test lname1','$2a$11$Hah6zAT7NgMPyMmXjdX3n.10.jiZQyuJALwx6b7yjXu7Gvd64SwzG',NULL,'1234567 fgf'),(22,'gdfg','2018-01-15 00:04:00.000000','test2@mail.com','sdfsf','sdfsdf','$2a$11$vSJ0ygZzUTM9SwBGRBQDvezil0vHRr5Xn/JpeOb3w8SP408etSPN.',NULL,'123123'),(24,'dfg','2018-01-15 00:04:00.000000','test3l@mail.com','test3','test3l','$2a$11$ZAdRRDoio2T1C7qXalOvyeifqfnf0V1ucVTQfWWLRv5MZH0FhE9he',NULL,'dfgd');
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

-- Dump completed on 2018-05-09 17:34:40
