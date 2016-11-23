-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: formular_manager
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `forms_response`
--

DROP TABLE IF EXISTS `forms_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forms_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` int(11) NOT NULL,
  `value` longtext NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forms_response`
--

LOCK TABLES `forms_response` WRITE;
/*!40000 ALTER TABLE `forms_response` DISABLE KEYS */;
INSERT INTO `forms_response` VALUES (1,1,'{\"radio-group-1479720793151\":\"option-2\",\"select-1479733553038\":\"option-2\",\"text-1479733554734\":\"hallo\",\"textarea-1479733555697\":\"\"}',0,'2016-11-21 10:51:01'),(2,1,'{\"radio-group-1479720793151\":\"option-2\",\"select-1479733553038\":\"option-2\",\"text-1479733554734\":\"hallo\",\"textarea-1479733555697\":\"\"}',0,'2016-11-21 14:36:27'),(3,1,'{\"radio-group-1479720793151\":\"option-3\",\"select-1479733553038\":\"option-1\",\"text-1479733554734\":\"\",\"textarea-1479733555697\":\"\"}',0,'2016-11-21 16:43:00'),(4,1,'{\"radio-group-1479720793151\":\"option-2\",\"select-1479733553038\":\"option-2\",\"text-1479733554734\":\"\",\"textarea-1479733555697\":\"\"}',0,'2016-11-22 07:40:13'),(5,1,'{\"radio-group-1479720793151\":\"option-1\",\"select-1479733553038\":\"option-1\",\"text-1479733554734\":\"\",\"textarea-1479733555697\":\"\"}',0,'2016-11-22 11:24:20'),(6,1,'{\"checkbox-1479733689313\":\"on\",\"radio-group-1479720793151\":\"option-1\",\"select-1479733553038\":\"option-1\",\"text-1479733554734\":\"\",\"textarea-1479733555697\":\"\"}',0,'2016-11-22 11:26:45'),(7,1,'{\"radio-group-1479720793151\":\"option-1\",\"select-1479733553038\":\"option-1\",\"text-1479733554734\":\"\",\"textarea-1479733555697\":\"\"}',0,'2016-11-22 11:33:41'),(8,1,'{\"radio-group-1479720793151\":\"option-1\",\"select-1479733553038\":\"option-1\",\"text-1479733554734\":\"\",\"textarea-1479733555697\":\"\"}',0,'2016-11-22 11:35:38'),(9,5,'{\"antwort-weihnachtsfeier\":\"vielleicht\"}',0,'2016-11-22 11:51:44'),(10,5,'{\"antwort-weihnachtsfeier\":\"ja\"}',0,'2016-11-22 11:51:59'),(11,5,'{\"antwort-weihnachtsfeier\":\"ja\"}',0,'2016-11-22 11:52:06'),(12,5,'{\"antwort-weihnachtsfeier\":\"nein\"}',0,'2016-11-22 11:52:14'),(13,5,'{\"antwort-weihnachtsfeier\":\"vielleicht\"}',0,'2016-11-22 11:53:25'),(14,5,'{\"antwort-weihnachtsfeier\":\"ja\"}',0,'2016-11-22 14:12:46'),(15,5,'{\"antwort-weihnachtsfeier\":\"vielleicht\"}',0,'2016-11-22 14:13:13'),(16,5,'{\"antwort-weihnachtsfeier\":\"vielleicht\"}',0,'2016-11-22 14:20:11'),(17,5,'{\"antwort-weihnachtsfeier\":\"nein\"}',0,'2016-11-22 14:20:25');
/*!40000 ALTER TABLE `forms_response` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-22 15:09:36
