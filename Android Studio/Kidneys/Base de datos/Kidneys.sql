#DROP DATABASE IF EXISTS Kidneys;
#DROP TABLE IF EXISTS myFoods,Ingredients,MyRecipes,Foods,Categories,Recipes,Users;

CREATE DATABASE kidneys;

#CREATE SCHEMA IF NOT EXISTS `kidneys` DEFAULT CHARACTER SET utf8 ;
USE `kidneys` ;


CREATE TABLE IF NOT EXISTS `kidneys`.`users` (
	`email` VARCHAR(255) NOT NULL PRIMARY KEY,
    `password` VARCHAR(40) NOT NULL,
	`name` VARCHAR(45) NOT NULL,
    `gender` VARCHAR(1) NOT NULL,
    `image` VARCHAR(255) NOT NULL,
    `dateofbirth` DATE NOT NULL,
    `weight` DOUBLE NOT NULL,
    `height` DOUBLE NOT NULL,
    `datecatheter` DATE NOT NULL,
    `typeofsolution` DOUBLE NOT NULL,
    `imc` DOUBLE NOT NULL,
    `typeoftreatment` VARCHAR(39) NOT NULL,
    `emergencycontact` VARCHAR(14) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `kidneys`.`healthrange` (
	`idhealthrange` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(7) NOT NULL)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `kidneys`.`recipes` (
	`idrecipe` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(45) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `ingredients` TEXT NOT NULL,
    `prepare` TEXT NOT NULL,
	`image` VARCHAR(255) NOT NULL,
    `idhealthrange` INT NOT NULL,
    `portion` VARCHAR(7) NOT NULL,
    `sodium` VARCHAR(6) NOT NULL,
    `potassium` VARCHAR(6) NOT NULL,
    `phosphor` VARCHAR(6) NOT NULL,
     CONSTRAINT `idhealthrange`
    FOREIGN KEY(`idhealthrange`)
	REFERENCES `kidneys`.`healthrange` (`idhealthrange`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `kidneys`.`categories` (
	`idcategory` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(31) NOT NULL)
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
    
CREATE TABLE IF NOT EXISTS `kidneys`.`foods` (
	`idfood` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `idcategory` INT NOT NULL,
    `image` VARCHAR(255) NOT NULL,
     `idhealthrange` INT NOT NULL,
    `portion` VARCHAR(7) NOT NULL,
    `sodium` VARCHAR(6) NOT NULL,
    `potassium` VARCHAR(6) NOT NULL,
    `phosphor` VARCHAR(6) NOT NULL,
    CONSTRAINT `idcategory`
    FOREIGN KEY(`idcategory`)
	REFERENCES `kidneys`.`categories` (`idcategory`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
     CONSTRAINT `idhealthrange2`
    FOREIGN KEY(`idhealthrange`)
	REFERENCES `kidneys`.`healthrange` (`idhealthrange`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `kidneys`.`follows` (
	`idfollow` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `date` DATE NOT NULL,
    `email` VARCHAR(255) NOT NULL,
	`idhealthrange` INT NOT NULL,
    CONSTRAINT `email3` 
    FOREIGN KEY (`email`)
    REFERENCES `kidneys`.`users` (`email`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
     CONSTRAINT `idhealthrange3`
    FOREIGN KEY(`idhealthrange`)
	REFERENCES `kidneys`.`healthrange` (`idhealthrange`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `kidneys`.`followsday` (
	`idfollowday` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `typeofsolution` DOUBLE NOT NULL,
    `start` TIME NOT NULL,
	`end` TIME NOT NULL,
    `drainage` DOUBLE NOT NULL,
    `uf` DOUBLE NOT NULL,
    `ingestedliquid` DOUBLE NOT NULL,
    `idfollow` INT NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    CONSTRAINT `email4` 
    FOREIGN KEY (`email`)
    REFERENCES `kidneys`.`users` (`email`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
     CONSTRAINT `idfollow`
    FOREIGN KEY(`idfollow`)
	REFERENCES `kidneys`.`follows` (`idfollow`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `kidneys`.`questions` (
	`idquestion` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `question` VARCHAR(255) NOT NULL,
    `answer` TEXT NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `kidneys`.`guides` (
	`idguide` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `numpages` INT NOT NULL,
    `image` VARCHAR(255) NOT NULL,
    `pdf` VARCHAR(255) NOT NULL,
    `author` VARCHAR(45) NOT NULL,
    `yearpublication` YEAR(4) NOT NULL,
    `reference` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `kidneys`.`locations` (
	`idlocation` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL,
    `latitude` DOUBLE NOT NULL,
    `longitude` DOUBLE NOT NULL,
    `idcategory` INT NOT NULL,
     CONSTRAINT `idcategory2`
    FOREIGN KEY(`idcategory`)
	REFERENCES `kidneys`.`categories` (`idcategory`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



