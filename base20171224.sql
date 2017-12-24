/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-12-24 19:37:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `introduction` varchar(100) DEFAULT NULL,
  `create_name` varchar(30) NOT NULL,
  `create_time` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('7', '文章要长', '这是摘要', '超级管理员', '2017-11-27 14:03:14');

-- ----------------------------
-- Table structure for article_keyword
-- ----------------------------
DROP TABLE IF EXISTS `article_keyword`;
CREATE TABLE `article_keyword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `a_id` bigint(20) NOT NULL,
  `k_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`a_id`,`k_id`),
  CONSTRAINT `aid` FOREIGN KEY (`a_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_keyword
-- ----------------------------
INSERT INTO `article_keyword` VALUES ('5', '7', '38');
INSERT INTO `article_keyword` VALUES ('4', '7', '42');

-- ----------------------------
-- Table structure for keyword
-- ----------------------------
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `create_name` varchar(30) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keyword
-- ----------------------------
INSERT INTO `keyword` VALUES ('32', '金融', '超级管理员', '2017-11-01 21:55:41');
INSERT INTO `keyword` VALUES ('33', '证券', '超级管理员', '2017-11-03 23:15:27');
INSERT INTO `keyword` VALUES ('34', '企业', '超级管理员', '2017-11-03 23:15:31');
INSERT INTO `keyword` VALUES ('35', '银行', '超级管理员', '2017-11-05 18:22:17');
INSERT INTO `keyword` VALUES ('36', '房地产', '超级管理员', '2017-11-07 13:55:06');
INSERT INTO `keyword` VALUES ('37', '特朗普', '超级管理员', '2017-11-07 14:25:08');
INSERT INTO `keyword` VALUES ('38', '比特币', '超级管理员', '2017-11-07 14:55:07');
INSERT INTO `keyword` VALUES ('39', '区块链', '超级管理员', '2017-11-07 15:16:55');
INSERT INTO `keyword` VALUES ('40', '对称加密', '超级管理员', '2017-11-07 15:23:53');
INSERT INTO `keyword` VALUES ('41', '非对称加密', '超级管理员', '2017-11-07 15:37:35');
INSERT INTO `keyword` VALUES ('42', '数字认证', '超级管理员', '2017-11-07 15:38:22');
INSERT INTO `keyword` VALUES ('43', '非关键', '超级管理员', '2017-11-07 15:38:47');
INSERT INTO `keyword` VALUES ('44', '关键字', '超级管理员', '2017-11-07 16:14:58');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` bigint(25) NOT NULL,
  `code` varchar(15) NOT NULL,
  `name` varchar(40) NOT NULL,
  `industry` varchar(40) DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('204367477860', '300380', '安硕信息', '软件服务', '上海');
INSERT INTO `stock` VALUES ('235343481945', '601238', '广汽集团', '汽车整车', '广东');
INSERT INTO `stock` VALUES ('314268833152', '002847', '盐津铺子', '食品', '湖南');
INSERT INTO `stock` VALUES ('451411199185', '002383', '合众思壮', '通信设备', '北京');
INSERT INTO `stock` VALUES ('455866117321', '300252', '金信诺', '电气设备', '深圳');
INSERT INTO `stock` VALUES ('476142945738', '600039', '四川路桥', '建筑施工', '四川');
INSERT INTO `stock` VALUES ('727564184723', '002045', '国光电器', '元器件', '广东');
INSERT INTO `stock` VALUES ('737669439933', '600528', '中铁工业', '建筑施工', '四川');
INSERT INTO `stock` VALUES ('829843835330', '601985', '中国核电', '新型电力', '北京');
INSERT INTO `stock` VALUES ('871920315441', '000036', '华联控股', '全国地产', '深圳');
INSERT INTO `stock` VALUES ('988964068998', '000587', '金洲慈航', '批发业', '黑龙江');

-- ----------------------------
-- Table structure for stock_price
-- ----------------------------
DROP TABLE IF EXISTS `stock_price`;
CREATE TABLE `stock_price` (
  `id` bigint(30) NOT NULL,
  `rise` float(10,0) DEFAULT NULL,
  `present_price` float(10,2) DEFAULT NULL,
  `rise_full` varchar(10) DEFAULT NULL,
  `buy_price` float(10,2) DEFAULT NULL,
  `sale_price` float(10,2) DEFAULT NULL,
  `total_hand` bigint(20) DEFAULT NULL,
  `now_hand` bigint(20) DEFAULT NULL,
  `rising_speed` float(10,2) DEFAULT NULL,
  `turnover` float(10,2) DEFAULT NULL,
  `open` float(10,2) DEFAULT NULL,
  `highest` float(10,2) DEFAULT NULL,
  `lowest` float(10,2) DEFAULT NULL,
  `prev_close` float(10,2) DEFAULT NULL,
  `prowave` float(10,2) DEFAULT NULL,
  `total_amount` float(20,2) DEFAULT NULL,
  `qrr` float(10,2) DEFAULT NULL,
  `stock_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `stock` (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_price
-- ----------------------------
INSERT INTO `stock_price` VALUES ('1577354938', '3', '9.89', '0.29', '9.89', '9.90', '346107', '4328', '0.10', '3.05', '9.57', '9.97', '9.53', '9.60', '7.83', '339042112.00', '2.80', '871920315441');
INSERT INTO `stock_price` VALUES ('94395890589', '4', '6.76', '0.23', '6.75', '6.76', '360457', '7551', '0.00', '3.13', '6.54', '6.82', '6.54', '6.53', '14.78', '241065088.00', '1.72', '988964068998');
INSERT INTO `stock_price` VALUES ('103239788517', '-1', '27.42', '-0.23', '27.42', '27.44', '51988', '2', '-0.03', '0.12', '27.75', '27.75', '27.40', '27.65', '14.95', '143012448.00', '0.89', '235343481945');
INSERT INTO `stock_price` VALUES ('403842788293', '-1', '32.49', '-0.35', '32.49', '32.50', '15497', '324', '0.15', '5.00', '32.69', '32.69', '32.21', '32.84', '56.29', '50247648.00', '0.41', '314268833152');
INSERT INTO `stock_price` VALUES ('434561631117', '-1', '22.80', '-0.16', '22.78', '22.80', '11327', '206', '-0.03', '1.68', '22.90', '23.15', '22.68', '22.96', '1397.78', '25915814.00', '0.70', '204367477860');
INSERT INTO `stock_price` VALUES ('477020646001', '0', '18.98', '-0.09', '18.97', '18.98', '317615', '3017', '-0.25', '6.72', '18.68', '19.36', '18.37', '19.07', '148.50', '597157632.00', '1.32', '451411199185');
INSERT INTO `stock_price` VALUES ('611910700883', '-1', '4.14', '-0.03', '4.14', '4.15', '73635', '3', '0.00', '0.24', '4.16', '4.16', '4.12', '4.17', '25.78', '30468240.00', '0.68', '476142945738');
INSERT INTO `stock_price` VALUES ('847021280006', '1', '19.25', '0.12', '19.24', '19.25', '107579', '1235', '0.00', '2.59', '19.13', '19.45', '18.70', '19.13', '63.45', '205332912.00', '0.66', '727564184723');
INSERT INTO `stock_price` VALUES ('853782876954', '0', '7.69', '-0.01', '7.68', '7.69', '322677', '24', '0.13', '0.76', '7.68', '7.73', '7.66', '7.70', '21.87', '248186992.00', '0.67', '829843835330');
INSERT INTO `stock_price` VALUES ('854268540670', '-2', '13.30', '-0.22', '13.28', '13.29', '102184', '38', '-0.22', '0.70', '13.47', '13.47', '13.29', '13.52', '24.62', '136629488.00', '0.77', '737669439933');
INSERT INTO `stock_price` VALUES ('854315292158', '1', '19.20', '0.11', '19.20', '19.21', '28157', '472', '0.00', '0.93', '19.08', '19.37', '18.90', '19.09', '52.49', '54092536.00', '0.74', '455866117321');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_id` bigint(20) DEFAULT NULL,
  `flag` varchar(100) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `fileurl` varchar(255) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('4', '7', 'article', '104212qvrkkqwllkra2k87.png', '04/4516-1511762592743.png', '超级管理员', '2017-11-27 14:03:14');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `type` char(1) DEFAULT '0' COMMENT '类型(0.菜单 1.按钮)',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '系统管理', 'cogs', '0', '0', '', '0');
INSERT INTO `sys_resource` VALUES ('2', '用户管理', 'users', '0', '0', '', '0');
INSERT INTO `sys_resource` VALUES ('3', '菜单配置', 'gear', '1', '0', 'menu', '0');
INSERT INTO `sys_resource` VALUES ('4', '账号管理', 'street-view', '2', '0', 'userCenter', '0');
INSERT INTO `sys_resource` VALUES ('5', '文档管理', 'glass', '0', '0', null, '0');
INSERT INTO `sys_resource` VALUES ('6', '角色管理', null, '2', '0', 'userRole', '0');
INSERT INTO `sys_resource` VALUES ('9', '关键词管理', null, '5', '0', 'keyword', '0');
INSERT INTO `sys_resource` VALUES ('10', '文章管理', null, '5', '0', 'article', '0');
INSERT INTO `sys_resource` VALUES ('11', '行情管理', 'paypal', '0', '0', '', '0');
INSERT INTO `sys_resource` VALUES ('12', '股票管理', null, '11', '0', 'stock', '0');
INSERT INTO `sys_resource` VALUES ('13', '行情列表', null, '11', '0', 'stockPrice', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '访客', '访客权限');
INSERT INTO `sys_role` VALUES ('2', '普通用户', '普通权限');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `sys_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1', '1', '5');
INSERT INTO `sys_role_resource` VALUES ('2', '1', '9');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `del_flag` smallint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '86f3059b228c8acf99e69734b6bb32cc', '超级管理员', 'admin@qq.com', '13691110090', '0');
INSERT INTO `sys_user` VALUES ('2', 'guest', '5bc5eb2a88b7ce89e25c6956daa3f93d', '访客', 'guest@qq.com', '13691110090', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '2');
