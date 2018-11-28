/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : lims2018adb

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-11-27 17:01:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data_item`
-- ----------------------------
DROP TABLE IF EXISTS `data_item`;
CREATE TABLE `data_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `up_data_item_id` bigint(20) DEFAULT NULL,
  `data_value` varchar(255) DEFAULT NULL,
  `data_key_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnprgfo8f3e7rircgrb4ngq9sp` (`up_data_item_id`),
  KEY `FKkrcyfiko6bj0p9oo0v20m9scg` (`data_key_id`),
  CONSTRAINT `FKkrcyfiko6bj0p9oo0v20m9scg` FOREIGN KEY (`data_key_id`) REFERENCES `data_key` (`id`),
  CONSTRAINT `FKnprgfo8f3e7rircgrb4ngq9sp` FOREIGN KEY (`up_data_item_id`) REFERENCES `data_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_item
-- ----------------------------
INSERT INTO `data_item` VALUES ('1', '0', null, null, '13');
INSERT INTO `data_item` VALUES ('2', '0', '1', '本科生', '14');
INSERT INTO `data_item` VALUES ('3', '0', null, null, '13');
INSERT INTO `data_item` VALUES ('4', '0', '3', '学术硕士', '14');
