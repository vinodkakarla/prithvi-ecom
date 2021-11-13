ALTER TABLE `product` ADD COLUMN `order_by` smallint NULL DEFAULT '1000';
ALTER TABLE `product` ADD COLUMN `sub_name` varchar(255) NULL AFTER `name`;
ALTER TABLE `product_unit_price` ADD COLUMN `mrp` float NULL DEFAULT '0' AFTER `unit_price`;
ALTER TABLE `product_units` ADD COLUMN `order_by` smallint NULL DEFAULT '1000';