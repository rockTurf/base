/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-23 21:24:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for new_keyword
-- ----------------------------
DROP TABLE IF EXISTS `new_keyword`;
CREATE TABLE `new_keyword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `new_id` bigint(20) DEFAULT NULL,
  `keyword_id` bigint(20) DEFAULT NULL,
  `create_time` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
