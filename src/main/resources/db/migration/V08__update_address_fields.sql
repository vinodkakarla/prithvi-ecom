ALTER TABLE account_address ADD COLUMN `name` varchar(255) DEFAULT "";
ALTER TABLE account_address ADD COLUMN is_active bit(1) NOT NULL DEFAULT 1;