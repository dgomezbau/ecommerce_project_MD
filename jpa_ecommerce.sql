-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.10-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for jpa_ecommerce
CREATE DATABASE IF NOT EXISTS `jpa_ecommerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `jpa_ecommerce`;

-- Dumping structure for table jpa_ecommerce.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `CUST_ID` int(11) NOT NULL,
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) NOT NULL,
  `STREET` varchar(50) DEFAULT NULL,
  `APPT` varchar(20) DEFAULT NULL,
  `CITY` varchar(25) DEFAULT NULL,
  `ZIP_CODE` varchar(10) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `LAST_UPDATED_TIME` datetime DEFAULT NULL,
  `PASS` varchar(50) NOT NULL,
  PRIMARY KEY (`CUST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table jpa_ecommerce.customer: ~2 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
REPLACE INTO `customer` (`CUST_ID`, `FIRST_NAME`, `LAST_NAME`, `STREET`, `APPT`, `CITY`, `ZIP_CODE`, `EMAIL`, `LAST_UPDATED_TIME`, `PASS`) VALUES
	(100, 'Daniel', 'Gómez', 'Calle Mayor 3', '2A', 'Calatorao', '50026', 'daniel.gomez@movicoders.com', '2020-02-29 08:59:07', '1234'),
	(110, 'Mihai', 'Aurar', 'Plaza Cataluña 1', '1D', 'Huesca', '22084', 'mihai.aurar@movicoders.com', '2020-02-29 08:59:07', '1234');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table jpa_ecommerce.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `ORDER_ID` int(11) NOT NULL,
  `CUST_ID` int(11) NOT NULL,
  `TOTAL_PRICE` double DEFAULT NULL,
  `OREDER_DESC` varchar(100) DEFAULT NULL,
  `ORDER_DATE` datetime DEFAULT curdate(),
  `LAST_UPDATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `ORDER_FK` (`CUST_ID`),
  CONSTRAINT `ORDER_FK` FOREIGN KEY (`CUST_ID`) REFERENCES `customer` (`CUST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table jpa_ecommerce.orders: ~15 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
REPLACE INTO `orders` (`ORDER_ID`, `CUST_ID`, `TOTAL_PRICE`, `OREDER_DESC`, `ORDER_DATE`, `LAST_UPDATED_TIME`) VALUES
	(111, 100, 100, 'Internet and phone', '2020-02-29 08:59:07', '2020-02-29 08:59:07'),
	(222, 100, 15, 'Cable at discounted price', '2020-02-29 08:59:07', '2020-02-29 08:59:07'),
	(333, 110, 99, '3 in one offer', '2020-02-29 08:59:07', '2020-02-29 08:59:07'),
	(501, 100, 180, 'Test Description', NULL, NULL),
	(551, 100, 240, 'Test Description', '2020-03-12 17:30:06', NULL),
	(601, 100, 300, 'Test Description', '2020-03-12 17:53:46', NULL),
	(651, 100, 100, 'Test Description', '2020-03-12 18:28:52', '2020-03-12 18:28:52'),
	(701, 100, 400, 'Test Description', '2020-03-12 18:34:08', '2020-03-12 18:34:08'),
	(751, 100, 50, 'Test Description', '2020-03-12 18:36:14', '2020-03-12 18:36:14'),
	(801, 100, 50, 'Test Description', '2020-03-12 18:36:45', '2020-03-12 18:36:45'),
	(851, 100, 50, 'Test Description', '2020-03-12 18:37:23', '2020-03-12 18:37:23'),
	(901, 110, 240, 'Test Description', '2020-03-20 18:32:06', '2020-03-20 18:32:06'),
	(951, 110, 240, 'Test Description', '2020-03-20 21:42:15', '2020-03-20 21:42:15'),
	(1001, 110, 53, 'Test Description', '2020-03-20 21:58:44', '2020-03-20 21:58:44'),
	(1051, 110, 336, 'Test Description', '2020-03-20 22:17:53', '2020-03-20 22:17:53');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table jpa_ecommerce.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `ORDER_ID` int(11) NOT NULL,
  `PROD_ID` int(11) NOT NULL,
  `PRICE` double DEFAULT NULL,
  `LAST_UPDATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`,`PROD_ID`),
  KEY `PROD_ID_FK` (`PROD_ID`),
  CONSTRAINT `ORDER_ID_FK` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`ORDER_ID`),
  CONSTRAINT `PROD_ID_FK` FOREIGN KEY (`PROD_ID`) REFERENCES `product` (`PROD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table jpa_ecommerce.order_detail: ~19 rows (approximately)
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
REPLACE INTO `order_detail` (`ORDER_ID`, `PROD_ID`, `PRICE`, `LAST_UPDATED_TIME`) VALUES
	(111, 2020, 40, '2020-02-29 08:59:07'),
	(222, 2010, 15, '2020-02-29 08:59:07'),
	(333, 2000, 33, '2020-02-29 08:59:07'),
	(333, 2010, 33, '2020-02-29 08:59:07'),
	(333, 2020, 33, '2020-02-29 08:59:07'),
	(501, 2000, NULL, NULL),
	(551, 2000, NULL, NULL),
	(601, 2000, 300, NULL),
	(651, 2030, 100, NULL),
	(701, 2030, 400, NULL),
	(751, 2030, 50, NULL),
	(801, 2030, 50, NULL),
	(851, 2030, 50, NULL),
	(901, 2000, 60, NULL),
	(951, 2020, 40, NULL),
	(1001, 2000, 8, NULL),
	(1001, 2020, 7, NULL),
	(1051, 2000, 8, NULL),
	(1051, 2050, 148, NULL);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;

-- Dumping structure for table jpa_ecommerce.order_invoice
CREATE TABLE IF NOT EXISTS `order_invoice` (
  `INVOICE_ID` int(11) NOT NULL,
  `ORDER_ID` int(11) NOT NULL,
  `DATE_RAISED` datetime DEFAULT curdate(),
  `AMOUNT_DUE` double DEFAULT NULL,
  `DATE_SETTLED` datetime DEFAULT NULL,
  `DATE_CANCELLED` datetime DEFAULT NULL,
  `LAST_UPDATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`INVOICE_ID`),
  KEY `ORDER_INVOICE_FK` (`ORDER_ID`),
  CONSTRAINT `ORDER_INVOICE_FK` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table jpa_ecommerce.order_invoice: ~15 rows (approximately)
/*!40000 ALTER TABLE `order_invoice` DISABLE KEYS */;
REPLACE INTO `order_invoice` (`INVOICE_ID`, `ORDER_ID`, `DATE_RAISED`, `AMOUNT_DUE`, `DATE_SETTLED`, `DATE_CANCELLED`, `LAST_UPDATED_TIME`) VALUES
	(1, 111, '2020-02-29 08:59:07', 100, '2020-02-29 08:59:07', NULL, '2020-02-29 08:59:07'),
	(2, 222, '2020-02-29 08:59:07', 15, '2020-02-29 08:59:07', '2020-02-29 08:59:07', '2020-02-29 08:59:07'),
	(3, 333, '2020-02-29 08:59:07', 99, '2020-02-29 08:59:07', NULL, '2020-02-29 08:59:07'),
	(502, 501, NULL, 180, NULL, NULL, NULL),
	(552, 551, '2020-03-12 17:30:06', 240, NULL, NULL, '2020-03-12 17:30:06'),
	(602, 601, '2020-03-12 17:53:46', 300, NULL, NULL, '2020-03-12 17:53:46'),
	(652, 651, '2020-03-12 18:28:52', 100, NULL, NULL, '2020-03-12 18:28:52'),
	(702, 701, '2020-03-12 18:34:08', 400, NULL, NULL, '2020-03-12 18:34:08'),
	(752, 751, '2020-03-12 18:36:14', 50, NULL, NULL, '2020-03-12 18:36:14'),
	(802, 801, '2020-03-12 18:36:45', 50, NULL, NULL, '2020-03-12 18:36:45'),
	(852, 851, '2020-03-12 18:37:23', 50, NULL, NULL, '2020-03-12 18:37:23'),
	(902, 901, '2020-03-20 18:32:06', 240, NULL, NULL, '2020-03-20 18:32:06'),
	(952, 951, '2020-03-20 21:42:15', 240, NULL, NULL, '2020-03-20 21:42:15'),
	(1002, 1001, '2020-03-20 21:58:44', 53, NULL, NULL, '2020-03-20 21:58:44'),
	(1052, 1051, '2020-03-20 22:17:53', 336, NULL, NULL, '2020-03-20 22:17:53');
/*!40000 ALTER TABLE `order_invoice` ENABLE KEYS */;

-- Dumping structure for table jpa_ecommerce.product
CREATE TABLE IF NOT EXISTS `product` (
  `PROD_ID` int(11) NOT NULL,
  `PROD_NAME` varchar(50) NOT NULL,
  `PROD_DESC` varchar(200) DEFAULT NULL,
  `REGULAR_PRICE` double DEFAULT NULL,
  `LAST_UPDATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`PROD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table jpa_ecommerce.product: ~6 rows (approximately)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
REPLACE INTO `product` (`PROD_ID`, `PROD_NAME`, `PROD_DESC`, `REGULAR_PRICE`, `LAST_UPDATED_TIME`) VALUES
	(2000, 'Charmin', 'Charmin Ultra Strong Mega Plus 8', 8, '2020-02-29 08:59:07'),
	(2010, 'Donald Trump Toilet Paper', 'Extra Republican Toilet Paper', 50, '2020-02-29 08:59:07'),
	(2020, 'Scottex', 'Scottex Original', 7, '2020-02-29 08:59:07'),
	(2030, 'Colhogar', 'Colhogar 8 double', 9, '2020-02-29 08:59:07'),
	(2040, 'Recicled generic toilet paper', 'Tough guys\'s toilet paper', 12, '2020-02-29 08:59:07'),
	(2050, 'Covid-19 special paper', 'Toilet paper and mask', 148, '2020-03-20 23:16:08');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Dumping structure for table jpa_ecommerce.sequence
CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table jpa_ecommerce.sequence: ~1 rows (approximately)
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
REPLACE INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
	('SEQ_GEN', 1100);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
