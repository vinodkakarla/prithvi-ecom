
ALTER TABLE `orders` ADD `status` varchar(255) DEFAULT 'InProgress' AFTER `total_amount`;

ALTER TABLE `orders` ADD `pick_up` varchar(255) DEFAULT NULL AFTER `status`;

ALTER TABLE `product` ADD `inventory_id` bigint(20) DEFAULT NULL;

ALTER TABLE `product`
    ADD CONSTRAINT `inventory_id`
    FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`);