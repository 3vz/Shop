-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.40-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema db_database24
--

CREATE DATABASE IF NOT EXISTS db_database24;
USE db_database24;

--
-- Definition of table `tb_customer`
--

DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_customer`
--

/*!40000 ALTER TABLE `tb_customer` DISABLE KEYS */;
INSERT INTO `tb_customer` (`id`,`username`,`password`,`realname`,`address`,`email`,`mobile`) VALUES 
 (1,'mrsoft','mrsoft',NULL,'...','mrsoft@mrsoft.com','139xxxx8888');
/*!40000 ALTER TABLE `tb_customer` ENABLE KEYS */;


--
-- Definition of table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `orderId` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(200) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `totalPrice` float DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `paymentWay` varchar(15) DEFAULT NULL,
  `orderState` varchar(10) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `FKFA98EE3DD0EDC05E` (`customerId`),
  CONSTRAINT `FKFA98EE3DD0EDC05E` FOREIGN KEY (`customerId`) REFERENCES `tb_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_order`
--

/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` (`orderId`,`name`,`address`,`mobile`,`totalPrice`,`createTime`,`paymentWay`,`orderState`,`customerId`) VALUES 
 ('201004201352400389678','mrsoft','address','mobile',120,'2010-04-20 13:52:40','POSTOFFICE_PAY','DELIVERED',1),
 ('201004220944580771720','mrsoft','address','mobile',120,'2010-04-22 09:44:58','POSTOFFICE_PAY','DELIVERED',1),
 ('201004220957520484473','mrsoft','address','mobile',0,'2010-04-22 09:57:52','POSTOFFICE_PAY','DELIVERED',1);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;


--
-- Definition of table `tb_orderitem`
--

DROP TABLE IF EXISTS `tb_orderitem`;
CREATE TABLE `tb_orderitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) NOT NULL,
  `productName` varchar(200) NOT NULL,
  `productPrice` float NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `orderId` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBF076F042FE3E9B` (`orderId`),
  CONSTRAINT `FKBF076F042FE3E9B` FOREIGN KEY (`orderId`) REFERENCES `tb_order` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_orderitem`
--

/*!40000 ALTER TABLE `tb_orderitem` DISABLE KEYS */;
INSERT INTO `tb_orderitem` (`id`,`productId`,`productName`,`productPrice`,`amount`,`orderId`) VALUES 
 (1,1,'Java',120,1,NULL),
 (2,1,'Java',120,1,NULL);
/*!40000 ALTER TABLE `tb_orderitem` ENABLE KEYS */;


--
-- Definition of table `tb_productcategory`
--

DROP TABLE IF EXISTS `tb_productcategory`;
CREATE TABLE `tb_productcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB405573CFB37BC9D` (`pid`),
  CONSTRAINT `FKB405573CFB37BC9D` FOREIGN KEY (`pid`) REFERENCES `tb_productcategory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=350 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_productcategory`
--

/*!40000 ALTER TABLE `tb_productcategory` DISABLE KEYS */;
INSERT INTO `tb_productcategory` (`id`,`name`,`level`,`pid`) VALUES 

/*!40000 ALTER TABLE `tb_productcategory` ENABLE KEYS */;


--
-- Definition of table `tb_productinfo`
--

DROP TABLE IF EXISTS `tb_productinfo`;
CREATE TABLE `tb_productinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `createTime` datetime DEFAULT NULL,
  `baseprice` float DEFAULT NULL,
  `marketprice` float DEFAULT NULL,
  `sellprice` float DEFAULT NULL,
  `sexrequest` varchar(5) DEFAULT NULL,
  `commend` bit(1) DEFAULT NULL,
  `clickcount` int(11) DEFAULT NULL,
  `sellCount` int(11) DEFAULT NULL,
  `categoryId` int(11) DEFAULT NULL,
  `uploadFile` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uploadFile` (`uploadFile`),
  KEY `FK28A6CDEC487D8DAB` (`categoryId`),
  KEY `FK28A6CDEC3E1DFEF5` (`uploadFile`),
  CONSTRAINT `FK28A6CDEC3E1DFEF5` FOREIGN KEY (`uploadFile`) REFERENCES `tb_uploadfile` (`id`),
  CONSTRAINT `FK28A6CDEC487D8DAB` FOREIGN KEY (`categoryId`) REFERENCES `tb_productcategory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_productinfo`
--

/*!40000 ALTER TABLE `tb_productinfo` DISABLE KEYS */;
INSERT INTO `tb_productinfo` (`id`,`name`,`description`,`createTime`,`baseprice`,`marketprice`,`sellprice`,`sexrequest`,`commend`,`clickcount`,`sellCount`,`categoryId`,`uploadFile`) VALUES
/*!40000 ALTER TABLE `tb_productinfo` ENABLE KEYS */;


--
-- Definition of table `tb_uploadfile`
--

DROP TABLE IF EXISTS `tb_uploadfile`;
CREATE TABLE `tb_uploadfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_uploadfile`
--

/*!40000 ALTER TABLE `tb_uploadfile` DISABLE KEYS */;
INSERT INTO `tb_uploadfile` (`id`,`path`) VALUES 
 (1,'201004201340260341.jpg'),
 (2,'201004201342270110.jpg'),
 (3,'201004201359330922.jpg'),
 (4,'201004201400560420.jpg'),
 (5,'201004201401400092.jpg');
/*!40000 ALTER TABLE `tb_uploadfile` ENABLE KEYS */;


--
-- Definition of table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_user`
--

/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` (`id`,`username`,`password`) VALUES 
 (1,'admin','admin');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
