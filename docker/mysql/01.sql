CREATE DATABASE IF NOT EXISTS `account_db`;
CREATE USER 'account_us'@'%' IDENTIFIED BY 'account_pw';
GRANT ALL ON `account_db`.* TO 'account_us'@'%';

CREATE DATABASE IF NOT EXISTS `transaction_db`;
CREATE USER 'transaction_us'@'%' IDENTIFIED BY 'transaction_pw';
GRANT ALL ON `transaction_db`.* TO 'transaction_us'@'%';

