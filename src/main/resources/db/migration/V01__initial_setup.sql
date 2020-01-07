-- -------------------------------------------------------------
-- TablePlus 2.12(282)
--
-- https://tableplus.com/
--
-- Database: ecom
-- Generation Time: 2020-01-07 16:57:20.6850
-- -------------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_roles_name` (`name`),
  UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `username` varchar(15) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_users_username` (`username`),
  UNIQUE KEY `uk_users_email` (`email`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_roles_role_id` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/************************************************************************/
/* PRITHVI PRODUCTS & INVENTORY TABLE */
/************************************************************************/

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `product_units`;
CREATE TABLE `product_units` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `unit_quantity` bigint(20) DEFAULT NULL,
  `unit_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `product_unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FKtjty9sg1lew3fnyt43v2vx9ub` (`product_unit_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKtjty9sg1lew3fnyt43v2vx9ub` FOREIGN KEY (`product_unit_id`) REFERENCES `product_units` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `total_quantity` bigint(20) DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp7gj4l80fx8v0uap3b2crjwp5` (`product_id`),
  CONSTRAINT `FKp7gj4l80fx8v0uap3b2crjwp5` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


/************************************************************************/
/* ACCOUNTS TABLE */
/************************************************************************/

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `account_address`;
CREATE TABLE `account_address` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `pin_code` bigint(20) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKshgrb002krs0wl4e88yob94ty` (`account_id`),
  CONSTRAINT `FKshgrb002krs0wl4e88yob94ty` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `account_cred`;
CREATE TABLE `account_cred` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `encrypted_password` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqktpiomxmpcc5te7j6gcptfd4` (`account_id`),
  CONSTRAINT `FKqktpiomxmpcc5te7j6gcptfd4` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/************************************************************************/
/************************************************************************/

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3c7gbsfawn58r27cf5b2km72f` (`account_id`),
  KEY `FK5dcb6sm74kwspq429nadhu21y` (`address_id`),
  CONSTRAINT `FK3c7gbsfawn58r27cf5b2km72f` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK5dcb6sm74kwspq429nadhu21y` FOREIGN KEY (`address_id`) REFERENCES `account_address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `price_per_unit` double DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `units` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;