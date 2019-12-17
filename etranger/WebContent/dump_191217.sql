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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_city`
--

LOCK TABLES `category_city` WRITE;
/*!40000 ALTER TABLE `category_city` DISABLE KEYS */;
INSERT INTO `category_city` VALUES (1,'Busan',1),(2,'파리',2),(3,'런던',2),(4,'바르셀로나',2),(5,'마드리드',2),(6,'산토리니',2),(7,'카이로',2),(8,'자카르타',3),(9,'이스탄불',3),(10,'두바이',3),(11,'카파도키아',3);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_region`
--

LOCK TABLES `category_region` WRITE;
/*!40000 ALTER TABLE `category_region` DISABLE KEYS */;
INSERT INTO `category_region` VALUES (1,'Asia'),(2,'유럽'),(3,'중동');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_theme`
--

LOCK TABLES `category_theme` WRITE;
/*!40000 ALTER TABLE `category_theme` DISABLE KEYS */;
INSERT INTO `category_theme` VALUES (2,'hi'),(1,'IT투어'),(9,'가족'),(3,'나홀로'),(8,'노쇼핑'),(7,'노팁'),(6,'미식'),(14,'조심'),(13,'직관'),(12,'축구'),(4,'커플'),(10,'크루즈'),(5,'테마'),(11,'해리포터');
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
  `member_addr2` varchar(45) DEFAULT NULL,
  `member_addr3` varchar(45) DEFAULT NULL,
  `member_addr4` varchar(45) DEFAULT NULL,
  `member_phone` varchar(11) NOT NULL,
  `member_email` varchar(100) NOT NULL,
  `member_birth` date NOT NULL,
  `member_gender` varchar(1) NOT NULL,
  `member_leg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `member_last_login` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `member_grade` varchar(45) NOT NULL DEFAULT 'bronze',
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `uniq_member_id_name` (`member_id`,`member_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('admin','1234','관리자','관리자',NULL,NULL,NULL,'관리자','etrangermanager@gmail.com','2019-12-10','N','2019-12-17 08:14:54','2019-12-17 08:14:54','관리자');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `notice_num` int(11) NOT NULL AUTO_INCREMENT,
  `notice_member_id` varchar(12) NOT NULL,
  `notice_subject` varchar(100) NOT NULL,
  `notice_content` varchar(2000) NOT NULL,
  `notice_image` varchar(100) DEFAULT NULL,
  `notice_readcount` int(11) NOT NULL,
  `notice_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_num`),
  KEY `fk_notice_member1` (`notice_member_id`),
  CONSTRAINT `fk_notice_member1` FOREIGN KEY (`notice_member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (2,'admin','[항공권소식] [2019년 12월 부 유류할증료 안내]','<p><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">m ipsum dolor sit amet, consectetur adipisici</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupidita</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">ng elit. Ducimus itaque, autem necessitatibus volupt</span></p>',NULL,0,'2019-12-13 03:07:30'),(3,'admin','[항공권소식] [국제선]환불수수료 변경 안내!!','<p><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">pisicing elit. Ducimus itaque, autem necessit</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">rem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur simili</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">atibus voluptate quod mo</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">bus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur s</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">llitia delectus aut, sunt placea</span></p>',NULL,1,'2019-12-13 03:08:10'),(4,'admin',' [etranger] 공지사항 등록','<p><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">m ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt place~~</span><br></p>',NULL,2,'2019-12-17 02:22:36');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
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
  `package_category_wish_count` int(11) DEFAULT NULL,
  `package_category_num` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`package_category_code`),
  UNIQUE KEY `package_category_name_UNIQUE` (`package_category_name`),
  UNIQUE KEY `package_category_num` (`package_category_num`),
  UNIQUE KEY `package_category_conbineUNIQUE` (`package_category_city`,`package_category_region`,`package_category_theme`),
  KEY `fk_package_category_region` (`package_category_region`),
  CONSTRAINT `fk_package_category_city` FOREIGN KEY (`package_category_city`) REFERENCES `category_city` (`category_city_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_package_category_region` FOREIGN KEY (`package_category_region`) REFERENCES `category_region` (`category_region_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
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
  `reservation_category_code` varchar(100) NOT NULL,
  `reservation_date` date NOT NULL,
  `reservation_price` int(11) NOT NULL,
  `reservation_headcount` int(11) NOT NULL,
  `reservation_ispayment` varchar(45) DEFAULT NULL,
  `reservation_progress` varchar(45) NOT NULL,
  PRIMARY KEY (`reservation_num`),
  KEY `fk_reservation_member1` (`reservation_member_id`),
  KEY `fk_reservation_package_product1` (`reservation_product_num`),
  KEY `fk_reservation_category_code` (`reservation_category_code`),
  CONSTRAINT `fk_reservation_category_code` FOREIGN KEY (`reservation_category_code`) REFERENCES `package_category` (`package_category_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_member1` FOREIGN KEY (`reservation_member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_package_product1` FOREIGN KEY (`reservation_product_num`) REFERENCES `package_product` (`package_product_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
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
  `review_member_name` varchar(15) NOT NULL,
  `review_subject` varchar(100) NOT NULL,
  `review_image` varchar(100) NOT NULL,
  `review_content` varchar(2000) NOT NULL,
  `review_date` datetime DEFAULT NULL,
  `review_readcount` int(11) NOT NULL,
  `review_package_category_code` varchar(100) NOT NULL,
  `review_star` int(11) NOT NULL DEFAULT '0',
  `review_comment_count` int(11) DEFAULT '0',
  PRIMARY KEY (`review_num`),
  KEY `fk_review_package_category1` (`review_package_category_code`),
  KEY `fk_review_member1` (`review_member_id`,`review_member_name`),
  CONSTRAINT `fk_review_member1` FOREIGN KEY (`review_member_id`, `review_member_name`) REFERENCES `member` (`member_id`, `member_name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_review_package_category1` FOREIGN KEY (`review_package_category_code`) REFERENCES `package_category` (`package_category_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_comment`
--

DROP TABLE IF EXISTS `review_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review_comment` (
  `review_comment_num` int(11) NOT NULL AUTO_INCREMENT,
  `review_comment_member_id` varchar(12) NOT NULL,
  `review_comment_member_name` varchar(15) NOT NULL,
  `review_comment_review_num` int(11) DEFAULT NULL,
  `review_comment_content` varchar(200) DEFAULT NULL,
  `review_comment_ref` int(11) DEFAULT NULL,
  `review_comment_lev` int(11) DEFAULT NULL,
  `review_comment_seq` int(11) DEFAULT NULL,
  `review_comment_date` datetime DEFAULT NULL,
  PRIMARY KEY (`review_comment_num`),
  KEY `fk_review_comment_member1` (`review_comment_member_id`,`review_comment_member_name`),
  KEY `fk_review_comment_review1` (`review_comment_review_num`),
  CONSTRAINT `fk_review_comment_member1` FOREIGN KEY (`review_comment_member_id`, `review_comment_member_name`) REFERENCES `member` (`member_id`, `member_name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_review_comment_review1` FOREIGN KEY (`review_comment_review_num`) REFERENCES `review` (`review_num`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_comment`
--

LOCK TABLES `review_comment` WRITE;
/*!40000 ALTER TABLE `review_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `review_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish`
--

DROP TABLE IF EXISTS `wish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wish` (
  `wish_num` int(11) NOT NULL AUTO_INCREMENT,
  `wish_member_id` varchar(12) NOT NULL,
  `wish_category_code` varchar(100) NOT NULL,
  PRIMARY KEY (`wish_num`),
  KEY `fk_wish_member1` (`wish_member_id`),
  KEY `fk_wish_package_product1` (`wish_category_code`),
  CONSTRAINT `fk_wish_member1` FOREIGN KEY (`wish_member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_wish_package_product1` FOREIGN KEY (`wish_category_code`) REFERENCES `package_category` (`package_category_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
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

-- Dump completed on 2019-12-17 17:24:48
