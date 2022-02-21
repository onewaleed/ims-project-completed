##drop schema ims;
CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items`(
`id` INT(11) NOT NULL AUTO_INCREMENT,
`itemName` VARCHAR (255) NOT NULL,
`itemCost` DOUBLE  NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders`(
`id` INT(11) NOT NULL AUTO_INCREMENT,
`CustomerID` INT NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`CustomerID`) references `customers`(`id`)
);


CREATE TABLE IF NOT EXISTS `ims`.`order_items`(
`OrdersID` INT(11) NOT NULL ,
`itemID` INT(255) NOT NULL,
`numItems` INT(255) NOT NULL,
FOREIGN KEY (`OrdersID`) references orders(`id`),
FOREIGN KEY(`itemID`) references items(`id`)

);