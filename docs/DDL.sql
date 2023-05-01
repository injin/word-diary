-- 스키마
CREATE SCHEMA `diary` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

-- 멤버 테이블
CREATE TABLE `diary`.`member` (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` VARCHAR(255) NULL,
  `status` varchar(45) COLLATE utf8mb3_bin NOT NULL,
  `created_date` DATETIME NOT NULL,
  `last_modified_date` DATETIME NOT NULL,
  PRIMARY KEY (`member_id`)),
  UNIQUE KEY `email_UNIQUE` (`email`);

-- 이메일 인증 테이블
CREATE TABLE `diary`.`confirm_email` (
  `confirm_email_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  `date_expired` DATETIME NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `security_key` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`confirm_email_id`)
);

-- 히스토리 테이블
CREATE TABLE `diary`.`history` (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `target_date` date NOT NULL,
  `description` longtext COLLATE utf8mb3_bin,
  `created_date` datetime NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`history_id`)
);

-- 단어 테이블
CREATE TABLE `diary`.`word` (
  `word_id` INT NOT NULL AUTO_INCREMENT,
  `history_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`word_id`),
  INDEX `word_history_fk_idx` (`history_id` ASC) VISIBLE,
  CONSTRAINT `word_history_fk`
    FOREIGN KEY (`history_id`)
    REFERENCES `diary`.`history` (`history_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
