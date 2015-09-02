CREATE TABLE IF NOT EXISTS `template_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `template_id` VARCHAR(128) NULL,
  `name` VARCHAR(128) NULL,
  `type` VARCHAR(45) NULL,
  `desc` VARCHAR(512) NULL,
  `create_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `template_id_UNIQUE` (`template_id` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `template_basic_conf` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `template_id` VARCHAR(128) NULL,
  `attr` VARCHAR(45) NULL,
  `value` VARCHAR(128) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `template_user_conf` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `template_id` VARCHAR(128) NULL,
  `name` VARCHAR(64) NULL,
  `groups` VARCHAR(64) NULL,
  `homedir` VARCHAR(64) NULL,
  `password` VARCHAR(128) NULL,
  `iscrypted` INT NULL,
  `shell` VARCHAR(128) NULL,
  `uid` VARCHAR(128) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `template_part_conf` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `template_id` VARCHAR(128) NULL,
  `mntpoint` VARCHAR(45) NULL,
  `size` INT(32) NULL,
  `grow` INT NULL,
  `maxsize` INT(32) NULL,
  `noformat` INT NULL,
  `onpart` VARCHAR(45) NULL,
  `ondisk` VARCHAR(45) NULL,
  `asprimary` INT NULL,
  `fstype` VARCHAR(45) NULL,
  `start` VARCHAR(45) NULL,
  `end` VARCHAR(45) NULL,
  `bytes_per_inode` INT(32) NULL,
  `recommended` INT NULL,
  `onbiosdisk` INT NULL,
  `fsoptions` VARCHAR(256) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `template_volgroup_conf` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `template_id` VARCHAR(128) NULL,
  `name` VARCHAR(64) NULL,
  `partition` VARCHAR(45) NULL,
  `noformat` INT NULL,
  `useexisting` INT NULL,
  `presize` INT(32) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `template_logvol_conf` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `template_id` VARCHAR(128) NULL,
  `mntpoint` VARCHAR(45) NULL,
  `vgname` VARCHAR(64) NULL,
  `size` INT(32) NULL,
  `name` VARCHAR(64) NULL,
  `useexisting` INT NULL,
  `noformat` INT NULL,
  `fstype` VARCHAR(45) NULL,
  `fsoptoins` VARCHAR(256) NULL,
  `byte_pre_inode` INT(32) NULL,
  `precent` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `image_default_conf` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_name` VARCHAR(64) NULL,
  `template` VARCHAR(256) NULL,
  `pkglist` VARCHAR(256) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

