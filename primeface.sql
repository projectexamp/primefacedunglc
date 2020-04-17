-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for primeface
CREATE DATABASE IF NOT EXISTS `primeface` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `primeface`;

-- Dumping structure for table primeface.function
CREATE TABLE IF NOT EXISTS `function` (
  `function_id` int(11) NOT NULL AUTO_INCREMENT,
  `function_url` varchar(50) DEFAULT NULL,
  `function_name` varchar(50) NOT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table primeface.function: ~3 rows (approximately)
DELETE FROM `function`;
/*!40000 ALTER TABLE `function` DISABLE KEYS */;
INSERT INTO `function` (`function_id`, `function_url`, `function_name`) VALUES
	(1, 'user', 'User'),
	(2, 'function', 'Function'),
	(3, 'role', 'Role');
/*!40000 ALTER TABLE `function` ENABLE KEYS */;

-- Dumping structure for table primeface.function_role
CREATE TABLE IF NOT EXISTS `function_role` (
  `function_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK_ek3ie9xpmb0rhnd3wxfp3qo5i` (`function_id`),
  KEY `FK_og8nmlel0kf0fnp5y04sg7y0f` (`role_id`),
  CONSTRAINT `FK_ek3ie9xpmb0rhnd3wxfp3qo5i` FOREIGN KEY (`function_id`) REFERENCES `function` (`function_id`),
  CONSTRAINT `FK_og8nmlel0kf0fnp5y04sg7y0f` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table primeface.function_role: ~4 rows (approximately)
DELETE FROM `function_role`;
/*!40000 ALTER TABLE `function_role` DISABLE KEYS */;
INSERT INTO `function_role` (`function_id`, `role_id`) VALUES
	(3, 2),
	(2, 1),
	(1, 1),
	(3, 1);
/*!40000 ALTER TABLE `function_role` ENABLE KEYS */;

-- Dumping structure for table primeface.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table primeface.role: ~2 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `role_name`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table primeface.role_user
CREATE TABLE IF NOT EXISTS `role_user` (
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  KEY `FK_1p4td69hdcg4iaau4pvj20m3h` (`role_id`),
  CONSTRAINT `FK_1p4td69hdcg4iaau4pvj20m3h` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table primeface.role_user: ~3 rows (approximately)
DELETE FROM `role_user`;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;
INSERT INTO `role_user` (`role_id`, `user_id`) VALUES
	(1, 3),
	(2, 43),
	(2, 4);
/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;

-- Dumping structure for table primeface.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `RecordNo` varchar(20) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Sex` varchar(20) DEFAULT NULL,
  `Dob` date DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  `UserName` varchar(50) DEFAULT NULL,
  `PassWord` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- Dumping data for table primeface.user: ~22 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `RecordNo`, `Name`, `Age`, `Sex`, `Dob`, `Remark`, `UserName`, `PassWord`) VALUES
	(3, '3', 'smita', 471, 'Female', '1996-01-09', 'Jolly Lady', 'admin', 'admin'),
	(4, '4', 'Sarita', 37, 'Male', '1998-01-07', 'good beautiful lady', NULL, NULL),
	(7, '7', 'Rashmi', 23, 'Male', '2016-01-25', 'good person', NULL, NULL),
	(9, '9', 'Deepak', 21, 'Male', '2016-01-26', 'good person', NULL, NULL),
	(10, '10', 'Jyoti Rath', 21, 'Male', '2016-01-26', 'good person', NULL, NULL),
	(11, '11', 'Sruti Rath', 21, 'Male', '2016-01-26', 'good person', NULL, NULL),
	(13, '13', 'Pradipta Prusty', 34, 'Male', '2016-01-18', 'good person', NULL, NULL),
	(14, '14', 'sunita misra', 123, 'Female', '2016-01-27', 'good lady', NULL, NULL),
	(15, '15', 'sibananda mitra', 32, 'Male', '2016-01-25', 'good person', NULL, NULL),
	(17, '17', 'Smita satpathy', 21, 'Female', '2016-01-26', 'good lady', NULL, NULL),
	(18, '18', 'N.C Das', 32, 'Male', '2016-01-26', 'Good Person', NULL, NULL),
	(26, '19', 'sarita subudhi', 43, 'Female', '2016-01-26', 'good lady', NULL, NULL),
	(27, '27', 'Ravi Mohanty', 44, 'Male', '2016-01-26', 'good person', NULL, NULL),
	(28, '28', 'Sita', 23, 'Female', '2016-02-23', 'good lady', NULL, NULL),
	(29, '29', 'congdung', 22, 'Male', '2020-04-16', '?cc', NULL, NULL),
	(30, '30', 'congdung', 22, 'Male', '2020-04-16', '?cc', NULL, NULL),
	(31, '31', 'Raichand', 48, 'Male', '2014-06-16', 'Good Person', NULL, NULL),
	(32, '32', 'Deepak', 212, 'Male', '2016-01-26', 'good person', NULL, NULL),
	(36, '33', 'grdt', 87, 'Male', '2020-04-14', '651', NULL, NULL),
	(42, '37', 'cong dung', 123, 'Male', '2020-04-15', '2', NULL, NULL),
	(43, '43', 'sdg', 32, 'Male', '2020-04-12', 'e', NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
