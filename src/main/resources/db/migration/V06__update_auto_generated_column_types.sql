SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE `category` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `inventory` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `orders` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `order_details` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `product` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `product_units` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
SET FOREIGN_KEY_CHECKS = 1;