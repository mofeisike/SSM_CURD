/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : ssm_curd

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-02-23 18:17:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_emp
-- ----------------------------
DROP TABLE IF EXISTS `tbl_emp`;
CREATE TABLE `tbl_emp` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `fk_emp_dept` (`d_id`),
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`d_id`) REFERENCES `tnl_dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_emp
-- ----------------------------
INSERT INTO `tbl_emp` VALUES ('1', '9d343', 'M', 'fgrertfger3@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('2', '26b08', 'M', '8@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('5', 'e4ffb', 'M', 'e4ffb@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('6', 'b9aa8', 'M', 'b9aa8@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('7', '71338', 'M', '71338@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('8', 'f3bbf', 'M', 'bf@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('9', '730c8', 'M', '730c8@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('10', '92874', 'M', '92874@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('11', '548c5', 'M', '548c5@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('12', '5b627', 'M', '5b627@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('13', '6ecb1', 'M', '6ecb1@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('14', '1c4cc', 'M', 'c@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('15', 'b61e3', 'M', 'b61e3@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('16', 'b2cfb', 'M', 'b2cfb@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('17', '71b0e', 'M', '71b0e@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('18', '6f0d0', 'M', '6f0d0@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('19', 'db802', 'M', 'db802@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('20', '111dd', 'M', '111dd@163.com', '1');
INSERT INTO `tbl_emp` VALUES ('21', 'afdmin', 'M', '2214663365@qq.com', '1');
INSERT INTO `tbl_emp` VALUES ('22', 'mofei', 'M', '2214663365@qq.com', '2');
INSERT INTO `tbl_emp` VALUES ('23', 'totoca', 'M', '2214663365@qq.com', '1');
INSERT INTO `tbl_emp` VALUES ('24', 'dfe', 'M', '2214663365@qq.com', '1');

-- ----------------------------
-- Table structure for tnl_dept
-- ----------------------------
DROP TABLE IF EXISTS `tnl_dept`;
CREATE TABLE `tnl_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tnl_dept
-- ----------------------------
INSERT INTO `tnl_dept` VALUES ('1', '开发部');
INSERT INTO `tnl_dept` VALUES ('2', '服务部');
