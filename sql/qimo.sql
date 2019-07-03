/*
MySQL Backup
Source Server Version: 5.7.17
Source Database: qimozuoye
Date: 2019/7/3 16:19:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `sno` varchar(10) NOT NULL,
  `sname` varchar(8) NOT NULL DEFAULT '',
  `sex` char(1) NOT NULL DEFAULT 'm',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(45) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `student` VALUES ('2','s021','李四','m'), ('3','s0311','王五','w'), ('4','s042','赵六','w'), ('5','s0171','王小二','m'), ('6','s018','钱七','w'), ('7','s019','孙大圣','m'), ('8','s117','玄奘','m'), ('9','s118','沙僧','m'), ('10','s119','八戒','m'), ('11','s2221','T','m'), ('12','s0121','林冲','m'), ('13','s013','鲁智深','m'), ('14','s014','吴用','m'), ('15','s0151','宋江','m'), ('16','s023','武松','m'), ('18','s024','公孙胜','m'), ('19','s025','二哈','m'), ('20','s026','金毛','m'), ('21','s027','泰迪','m'), ('22','s027','labu','w'), ('23','s00001','李四j','w'), ('24','s00011','888','w'), ('26','s332','999','m'), ('27','s0215','rrr','m');
INSERT INTO `user` VALUES ('1','m0001','小韩','123'), ('2','m0002','小雪','123'), ('3','m0003','小石','123'), ('4','m0004','小陈','123'), ('5','m0005','zuo','123');
