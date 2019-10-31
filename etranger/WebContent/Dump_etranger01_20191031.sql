-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: etranger01
-- ------------------------------------------------------
-- Server version	5.7.25-log

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
-- Table structure for table `category_city`
--

DROP TABLE IF EXISTS `category_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_city` (
  `category_city_code` int(11) NOT NULL AUTO_INCREMENT,
  `category_city_name` varchar(45) NOT NULL,
  `category_city_region_code` int(11) NOT NULL,
  PRIMARY KEY (`category_city_code`),
  UNIQUE KEY `category_city_name_UNIQUE` (`category_city_name`),
  KEY `fk_category_city_category_region1` (`category_city_region_code`),
  CONSTRAINT `fk_category_city_category_region1` FOREIGN KEY (`category_city_region_code`) REFERENCES `category_region` (`category_region_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_city`
--

LOCK TABLES `category_city` WRITE;
/*!40000 ALTER TABLE `category_city` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_region`
--

DROP TABLE IF EXISTS `category_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_region` (
  `category_region_code` int(11) NOT NULL AUTO_INCREMENT,
  `category_region_name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_region_code`),
  UNIQUE KEY `category_region_name_UNIQUE` (`category_region_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_region`
--

LOCK TABLES `category_region` WRITE;
/*!40000 ALTER TABLE `category_region` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_theme`
--

DROP TABLE IF EXISTS `category_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_theme` (
  `category_theme_code` int(11) NOT NULL AUTO_INCREMENT,
  `category_theme_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`category_theme_code`),
  UNIQUE KEY `category_theme_name_UNIQUE` (`category_theme_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_theme`
--

LOCK TABLES `category_theme` WRITE;
/*!40000 ALTER TABLE `category_theme` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `member_id` varchar(12) NOT NULL,
  `member_passwd` varchar(20) NOT NULL,
  `member_name` varchar(15) NOT NULL,
  `member_addr` varchar(200) NOT NULL,
  `member_phone` varchar(11) NOT NULL,
  `member_email` varchar(100) NOT NULL,
  `member_birth` date NOT NULL,
  `member_gender` varchar(1) NOT NULL,
  `member_leg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `member_last_login` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `member_grade` varchar(45) NOT NULL DEFAULT 'bronze',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_category`
--

DROP TABLE IF EXISTS `package_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package_category` (
  `package_category_code` varchar(100) NOT NULL,
  `package_category_name` varchar(50) NOT NULL,
  `package_category_region` int(11) NOT NULL,
  `package_category_city` int(11) NOT NULL,
  `package_category_theme` varchar(100) DEFAULT NULL,
  `package_category_image` varchar(100) NOT NULL,
  `package_category_content` varchar(2000) NOT NULL,
  PRIMARY KEY (`package_category_code`),
  UNIQUE KEY `package_category_name_UNIQUE` (`package_category_name`),
  UNIQUE KEY `package_category_conbineUNIQUE` (`package_category_city`,`package_category_region`,`package_category_theme`),
  KEY `fk_package_category_region` (`package_category_region`),
  CONSTRAINT `fk_package_category_city` FOREIGN KEY (`package_category_city`) REFERENCES `category_city` (`category_city_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_package_category_region` FOREIGN KEY (`package_category_region`) REFERENCES `category_region` (`category_region_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_category`
--

LOCK TABLES `package_category` WRITE;
/*!40000 ALTER TABLE `package_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `package_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_product`
--

DROP TABLE IF EXISTS `package_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package_product` (
  `package_product_num` varchar(100) NOT NULL,
  `package_category_code` varchar(100) NOT NULL,
  `package_product_depart_date` date NOT NULL,
  `package_product_arriv_date` date NOT NULL,
  `package_product_price` int(11) NOT NULL,
  `package_product_total` int(11) NOT NULL,
  `package_product_current` int(11) NOT NULL,
  `package_product_wish_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`package_product_num`),
  KEY `fk_package_product_package_category` (`package_category_code`),
  CONSTRAINT `fk_package_product_package_category` FOREIGN KEY (`package_category_code`) REFERENCES `package_category` (`package_category_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_product`
--

LOCK TABLES `package_product` WRITE;
/*!40000 ALTER TABLE `package_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `package_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `reservation_num` int(11) NOT NULL AUTO_INCREMENT,
  `reservation_member_id` varchar(12) NOT NULL,
  `reservation_product_num` varchar(100) NOT NULL,
  `reservation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reservation_price` int(11) NOT NULL,
  `reservation_headcount` int(11) NOT NULL,
  `reservation_pay_way` varchar(45) NOT NULL,
  `reservation_ispayment` varchar(45) NOT NULL,
  PRIMARY KEY (`reservation_num`),
  KEY `fk_reservation_member1` (`reservation_member_id`),
  KEY `fk_reservation_package_product1` (`reservation_product_num`),
  CONSTRAINT `fk_reservation_member1` FOREIGN KEY (`reservation_member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_package_product1` FOREIGN KEY (`reservation_product_num`) REFERENCES `package_product` (`package_product_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `review_num` int(11) NOT NULL AUTO_INCREMENT,
  `review_member_id` varchar(12) NOT NULL,
  `review_subject` varchar(100) NOT NULL,
  `review_image` varchar(100) NOT NULL,
  `review_content` varchar(2000) NOT NULL,
  `review_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `review_readcount` int(11) NOT NULL,
  `review_package_category_code` varchar(100) NOT NULL,
  PRIMARY KEY (`review_num`),
  KEY `fk_review_package_category1` (`review_package_category_code`),
  KEY `fk_review_member1` (`review_member_id`),
  CONSTRAINT `fk_review_member1` FOREIGN KEY (`review_member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_package_category1` FOREIGN KEY (`review_package_category_code`) REFERENCES `package_category` (`package_category_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_reply`
--

DROP TABLE IF EXISTS `review_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review_reply` (
  `review_reply_num` int(11) NOT NULL AUTO_INCREMENT,
  `review_reply_member_id` varchar(12) DEFAULT NULL,
  `review_reply_review_num` int(11) DEFAULT NULL,
  `review_reply_ref` int(11) DEFAULT NULL,
  `review_reply_lev` int(11) DEFAULT NULL,
  `review_reply_seq` int(11) DEFAULT NULL,
  `review_reply_timestamp` timestamp NULL DEFAULT NULL,
  `review_reply_content` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`review_reply_num`),
  KEY `fk_review_reply_ref1` (`review_reply_ref`),
  KEY `fk_review_reply_member1` (`review_reply_member_id`),
  KEY `fk_review_reply_review1` (`review_reply_review_num`),
  CONSTRAINT `fk_review_reply_member1` FOREIGN KEY (`review_reply_member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_reply_ref1` FOREIGN KEY (`review_reply_ref`) REFERENCES `review_reply` (`review_reply_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_reply_review1` FOREIGN KEY (`review_reply_review_num`) REFERENCES `review` (`review_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_reply`
--

LOCK TABLES `review_reply` WRITE;
/*!40000 ALTER TABLE `review_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `review_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish`
--

DROP TABLE IF EXISTS `wish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wish` (
  `wish_num` int(11) NOT NULL,
  `wish_member_id` varchar(12) NOT NULL,
  `wish_product_num` varchar(100) NOT NULL,
  PRIMARY KEY (`wish_num`),
  KEY `fk_wish_member1` (`wish_member_id`),
  KEY `fk_wish_package_product1` (`wish_product_num`),
  CONSTRAINT `fk_wish_member1` FOREIGN KEY (`wish_member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_wish_package_product1` FOREIGN KEY (`wish_product_num`) REFERENCES `package_product` (`package_product_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish`
--

LOCK TABLES `wish` WRITE;
/*!40000 ALTER TABLE `wish` DISABLE KEYS */;
/*!40000 ALTER TABLE `wish` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-31  9:05:12
