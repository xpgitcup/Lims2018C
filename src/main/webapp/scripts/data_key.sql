/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : lims2018adb

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-11-27 17:01:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data_key`
-- ----------------------------
DROP TABLE IF EXISTS `data_key`;
CREATE TABLE `data_key` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `up_data_key_id` bigint(20) DEFAULT NULL,
  `data_key_type` varchar(255) NOT NULL,
  `column_number` int(11) NOT NULL,
  `data_unit` varchar(255) DEFAULT NULL,
  `column_seperator` varchar(255) NOT NULL,
  `append_parameter` varchar(255) DEFAULT NULL,
  `data_tag` varchar(255) NOT NULL,
  `dictionary_id` bigint(20) NOT NULL,
  `order_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgnosaw19330m9b74wxm8aknnf` (`up_data_key_id`),
  KEY `FKl2uqn74kg27n0as7b242b89oq` (`dictionary_id`),
  CONSTRAINT `FKgnosaw19330m9b74wxm8aknnf` FOREIGN KEY (`up_data_key_id`) REFERENCES `data_key` (`id`),
  CONSTRAINT `FKl2uqn74kg27n0as7b242b89oq` FOREIGN KEY (`dictionary_id`) REFERENCES `data_dictionary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_key
-- ----------------------------
INSERT INTO `data_key` VALUES ('1', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '教师信息', '1', '0');
INSERT INTO `data_key` VALUES ('2', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '设备信息', '1', '0');
INSERT INTO `data_key` VALUES ('3', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '学生信息', '1', '0');
INSERT INTO `data_key` VALUES ('4', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '项目信息', '1', '0');
INSERT INTO `data_key` VALUES ('5', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '实验信息', '1', '0');
INSERT INTO `data_key` VALUES ('6', '0', '1', 'dataKeyNormal', '1', '无量纲', ',', null, '姓名', '1', '0');
INSERT INTO `data_key` VALUES ('7', '0', '1', 'dataKeyNormal', '1', '无量纲', ',', null, '工号', '1', '0');
INSERT INTO `data_key` VALUES ('8', '0', '1', 'dataKeyNormal', '1', '无量纲', ',', null, '职称', '1', '0');
INSERT INTO `data_key` VALUES ('9', '0', '3', 'dataKeyNormal', '1', '无量纲', ',', null, '姓名', '1', '0');
INSERT INTO `data_key` VALUES ('10', '0', '3', 'dataKeyNormal', '1', '无量纲', ',', null, '学号', '1', '0');
INSERT INTO `data_key` VALUES ('11', '0', '4', 'dataKeyNormal', '1', '无量纲', ',', null, '项目名称', '1', '0');
INSERT INTO `data_key` VALUES ('12', '0', '4', 'dataKeyNormal', '1', '无量纲', ',', null, '项目类别', '1', '0');
INSERT INTO `data_key` VALUES ('13', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '学生类别', '1', '0');
INSERT INTO `data_key` VALUES ('14', '0', '13', 'dataKeyNormal', '1', '无量纲', ',', null, '名称', '1', '0');
