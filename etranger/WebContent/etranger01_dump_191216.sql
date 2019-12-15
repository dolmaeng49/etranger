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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_city`
--

LOCK TABLES `category_city` WRITE;
/*!40000 ALTER TABLE `category_city` DISABLE KEYS */;
INSERT INTO `category_city` VALUES (1,'Busan',1),(2,'파리',2),(3,'런던',2),(4,'바르셀로나',2),(5,'마드리드',2),(6,'산토리니',2),(7,'카이로',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_region`
--

LOCK TABLES `category_region` WRITE;
/*!40000 ALTER TABLE `category_region` DISABLE KEYS */;
INSERT INTO `category_region` VALUES (1,'Asia'),(2,'유럽');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_theme`
--

LOCK TABLES `category_theme` WRITE;
/*!40000 ALTER TABLE `category_theme` DISABLE KEYS */;
INSERT INTO `category_theme` VALUES (2,'hi'),(1,'IT투어'),(9,'가족'),(3,'나홀로'),(8,'노쇼핑'),(7,'노팁'),(6,'미식'),(13,'직관'),(12,'축구'),(4,'커플'),(10,'크루즈'),(5,'테마'),(11,'해리포터');
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
  UNIQUE KEY `member_name` (`member_name`),
  UNIQUE KEY `uniq_member_id_name` (`member_id`,`member_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('admin','1234','관리자','관리자',NULL,NULL,NULL,'관리자','etrangermanager@gmail.com','2019-12-10','N','2019-12-13 07:41:09','2019-12-13 07:41:09','관리자');
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
INSERT INTO `notice` VALUES (2,'admin','[항공권소식] [2019년 12월 부 유류할증료 안내]','<p><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">m ipsum dolor sit amet, consectetur adipisici</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupidita</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">ng elit. Ducimus itaque, autem necessitatibus volupt</span></p>',NULL,0,'2019-12-13 03:07:30'),(3,'admin','[항공권소식] [국제선]환불수수료 변경 안내!!','<p><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">pisicing elit. Ducimus itaque, autem necessit</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">rem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur simili</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">atibus voluptate quod mo</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">bus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur s</span><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">llitia delectus aut, sunt placea</span></p>',NULL,1,'2019-12-13 03:08:10'),(4,'admin','공지사항 등록 ','<p><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">m ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt place</span><br></p>',NULL,0,'2019-12-13 03:09:19');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_category`
--

LOCK TABLES `package_category` WRITE;
/*!40000 ALTER TABLE `package_category` DISABLE KEYS */;
INSERT INTO `package_category` VALUES ('1-1-!hi','sdfsdaf',1,1,'!hi','eat-2410362_640.png','asdfasdfasdf',1,1),('1-1-!IT투어','부산의 명물 ITWILL 부산교육센터',1,1,'!IT투어','salmon-518032_1920.jpg','ㅎㅎㅎㅎㅎㅎㅎ',1,2),('2-2-!미식!테마','Bonjour France 프랑스일주 7일',2,2,'!미식!테마','tour-5.jpg','인천국제공항 제1터미널 3층 A카운터 11,12번\r\n호텔 조식 후\r\n고성의 도시 루아르 지역 이동 (약 3시간 소요)\r\n\r\n루아르\r\n앙부아즈 성 및 시내관광\r\n\r\n이탈리아의 르네상스 양식이 프랑스 건축에 적용된 대표적인 앙부아즈 성(내부) 및 마을관광\r\n\r\n2000년 세계문화유산으로 지정한 프랑스 루아르 계곡에 있는 여러 고성 가운데 하나인 프랑스 상트르 주 앵드르에루아르에 있는 고성. 이탈리아의 르네상스 양식이 프랑스 건축에 적용된 대표적인 사례로 15~16세기 프랑스 왕족들의 거주지로 사용되었다.\r\n\r\n투르\r\n투르 이동 (약 40분 소요)\r\n\r\n몽생미셸 이동 (약 4시간 소요)\r\n몽생미셸\r\n몽생미셸 관광\r\n\r\n대천사 미카엘이 바위산 꼭대기에 성당을 지으라고 명했으며, 바위산 전체가 수도원인 몽생미셸 수도원',0,3),('2-3-!직관!축구!테마','etranger만의 스페셜 축구직관 런던투어',2,3,'!직관!축구!테마','the-ball-488713_1280.jpg','etranger만의 스페셜 축구직관 런던투어\r\n토트넘 직관! ',0,4),('2-3-!해리포터!나홀로','해리포터 스튜디오 일정 포함! 5박6일 런던투어',2,3,'!해리포터!나홀로','redbritish.jpg','해리포터 스튜디오 일정 포함! 5박6일 런던투어',0,5),('2-6-!IT투어','산토리',2,6,'!IT투어','press etranger.jpg','니',0,6),('2-6-!크루즈!가족','이태리&그리스섬 완벽 일주 크루즈',2,6,'!크루즈!가족','tour-1.jpg','산토리니\r\n전일해상\r\n■ 셀러브리티 엣지호는 산토리니를 향해 전일 항해합니다. ■\r\n■ 선내 각종 부대시설 및 다양한 프로그램 이용하며 선내 자유일정\r\n\r\n   -다양한 쇼를 관람할 수 있는 대형극장, 카지노, 나이트클럽, 다양한 종류의 실내/외 수영장, 정찬/부페 레스토랑,\r\n    피자테리아, 각종 바/라운지, 자쿠지/스파/사우나, 이/미용실, 도서관, 인터넷 카페, 갤러리, 면세점, 휘트니스 센터/\r\n    조깅트랙/스포츠덱, 골프 시뮬레이션, 어린이를 위한 놀이 공간 등\r\n셀러브리티 엣지호\r\n\r\n셀러브리티 크루즈 중 가장 규모가 큰 클래스가 될 엣지 클래스의 첫 번째 크루즈선인 엣지호는 12만9500톤(14층)으로 총 탑승객 3373명과 승무원 1320명이 탑승 가능합니다. 엣지호는 혁신적이고 고급스러움과 편안함을 강조한 것이 특징입니다. 네이트 버커스(Nate Berkus), 켈리 호픈(Kelly Hoppen) 등 세계적인 디자이너들이 참여해 한층 세련되고 미래 지향적으로 디자인이 돋보입니다.',0,7),('2-7-!가족!테마','스핑크스 보러 이집트 3박4일 ',2,7,'!가족!테마','tour-7.jpg','스핑크스 보러 이집트 3박4일 ',0,8);
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
INSERT INTO `package_product` VALUES ('1-1-!hi2019/12/28','1-1-!hi','2019-12-19','2019-12-28',123,34,4);
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
  `reservation_ispayment` varchar(10) NOT NULL,
  `reservation_progress` varchar(45) NOT NULL,
  PRIMARY KEY (`reservation_num`),
  KEY `fk_reservation_member1` (`reservation_member_id`),
  KEY `fk_reservation_package_product1` (`reservation_product_num`),
  KEY `fk_reservation_category_code` (`reservation_category_code`),
  CONSTRAINT `fk_reservation_category_code` FOREIGN KEY (`reservation_category_code`) REFERENCES `package_category` (`package_category_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_member1` FOREIGN KEY (`reservation_member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_package_product1` FOREIGN KEY (`reservation_product_num`) REFERENCES `package_product` (`package_product_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'admin','1-1-!hi2019/12/28','1-1-!hi','2019-12-13',246,2,'N','예약완료'),(2,'admin','1-1-!hi2019/12/28','1-1-!hi','2019-12-13',246,2,'N','예약완료');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (8,'admin','관리자','첫 리뷰','file.jpg','<p>3434<img src=\"./reviewUpload/file.jpg\" style=\"width: 708px;\"></p>','2019-12-13 10:23:31',1,'1-1-!hi',8,6),(10,'admin','관리자','두 번째 리뷰','press etranger.jpg','<p><img src=\"./reviewUpload/press etranger.jpg\" style=\"width: 708px;\"><br></p>','2019-12-13 11:08:55',3,'1-1-!hi',9,0),(14,'admin','관리자','후기 글쓰기 별점 작성','image_7.jpg','<p><span style=\"color: rgb(128, 128, 128); font-size: 15px;\">orem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupiditate numqu</span><img src=\"./reviewUpload/image_4.jpg\" style=\"width: 728.021px;\"><img src=\"./reviewUpload/image_7.jpg\" style=\"width: 728.021px;\"><br></p>','2019-12-13 12:18:00',2,'1-1-!hi',8,1),(15,'admin','관리자','테스트','cat.png','<p>ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ<img src=\"./reviewUpload/cat.png\" style=\"width: 200px;\"></p>','2019-12-13 16:41:54',0,'1-1-!hi',8,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_comment`
--

LOCK TABLES `review_comment` WRITE;
/*!40000 ALTER TABLE `review_comment` DISABLE KEYS */;
INSERT INTO `review_comment` VALUES (1,'admin','관리자',8,'1',1,0,0,'2019-12-13 10:39:06'),(2,'admin','관리자',8,'2',2,0,0,'2019-12-13 10:39:10'),(3,'admin','관리자',8,'3',3,0,0,'2019-12-13 10:39:13'),(4,'admin','관리자',8,'4',4,0,0,'2019-12-13 10:39:17'),(7,'admin','관리자',8,'1-1',2,1,1,'2019-12-13 10:40:00'),(8,'admin','관리자',8,'2-1',3,1,1,'2019-12-13 10:40:07'),(9,'admin','관리자',14,'맛있게 먹음',9,0,0,'2019-12-13 12:18:21');
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
INSERT INTO `wish` VALUES (48,'admin','1-1-!IT투어'),(52,'admin','1-1-!IT투어'),(53,'admin','1-1-!hi');
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

-- Dump completed on 2019-12-13 17:38:15
