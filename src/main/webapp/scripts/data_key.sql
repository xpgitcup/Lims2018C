/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : lims2018adb

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-12-06 15:53:01
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

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
INSERT INTO `data_key` VALUES ('15', '0', '3', 'dataKeyNormal', '1', '无量纲', ',', null, '类型', '1', '0');
INSERT INTO `data_key` VALUES ('16', '0', '3', 'dataKeyNormal', '1', '无量纲', ',', null, '年级', '1', '0');
INSERT INTO `data_key` VALUES ('17', '0', '3', 'dataKeyNormal', '1', '无量纲', ',', null, '专业', '1', '0');
INSERT INTO `data_key` VALUES ('18', '0', '3', 'dataKeyNormal', '1', '无量纲', ',', null, '导师', '1', '0');
INSERT INTO `data_key` VALUES ('19', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '项目管理', '1', '0');
INSERT INTO `data_key` VALUES ('20', '1', '19', 'dataKeyRef', '1', '无量纲', ',', '4', '项目', '1', '0');
INSERT INTO `data_key` VALUES ('21', '1', '19', 'dataKeyRef', '1', '无量纲', ',', '3', '参与学生', '1', '0');
INSERT INTO `data_key` VALUES ('22', '1', '19', 'dataKeyRef', '1', '无量纲', ',', '1', '参与教师', '1', '0');
INSERT INTO `data_key` VALUES ('23', '0', '19', 'dataKeyRef', '1', '无量纲', ',', null, '进展情况', '1', '0');
INSERT INTO `data_key` VALUES ('24', '0', '23', 'dataKeyDateTime', '1', '无量纲', ',', null, '时间', '1', '0');
INSERT INTO `data_key` VALUES ('25', '0', '23', 'dataKeyNormal', '1', '无量纲', ',', null, '本次进展', '1', '0');
INSERT INTO `data_key` VALUES ('26', '0', '23', 'dataKeyNormal', '1', '无量纲', ',', null, '所遇问题', '1', '0');
INSERT INTO `data_key` VALUES ('27', '0', '23', 'dataKeyNormal', '1', '无量纲', ',', null, '点评', '1', '0');
INSERT INTO `data_key` VALUES ('28', '0', '23', 'dataKeyNormal', '1', '无量纲', ',', null, '支撑资料', '1', '0');
INSERT INTO `data_key` VALUES ('29', '0', '23', 'dataKeyNormal', '1', '无量纲', ',', null, '贡献者', '1', '0');
INSERT INTO `data_key` VALUES ('30', '0', '4', 'dataKeyNormal', '1', '无量纲', ',', null, '甲方单位', '1', '0');
