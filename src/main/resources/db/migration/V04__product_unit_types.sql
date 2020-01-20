DROP TABLE IF EXISTS `product_unit_price`;
CREATE TABLE `product_unit_price` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `unit_price` float NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `product_unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9ok6rknydkd59bqpya87xpani` (`product_id`),
  KEY `FKfylpjekrj7ol4mhbgqtkywpy4` (`product_unit_id`),
  CONSTRAINT `FK9ok6rknydkd59bqpya87xpani` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKfylpjekrj7ol4mhbgqtkywpy4` FOREIGN KEY (`product_unit_id`) REFERENCES `product_units` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `product_unit_price` (`created_at`, `created_by`, `id`, `is_active`, `product_id`, `product_unit_id`, `unit_price`, `updated_at`, `updated_by`) VALUES (NULL, NULL, '1', '1', '1', '1', '11', NULL, NULL);
