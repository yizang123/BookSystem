/*
 Navicat Premium Data Transfer

 Source Server         : 丫丫
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : mybook

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 11/10/2022 17:09:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员主键',
  `username` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '1234');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '图书表主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '书名',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ISBN号码',
  `number` int(0) NULL DEFAULT 0 COMMENT '现存数量',
  `deleted` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '假如给我三天光明', '15926', 99, 0, '2022-10-08 09:25:53', '2022-10-11 15:10:00');
INSERT INTO `book` VALUES (2, '钢铁是怎样炼成的', '14928', 101, 1, '2022-10-08 09:26:27', '2022-10-11 15:57:08');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '借书表主键',
  `bid` int(0) NULL DEFAULT NULL COMMENT '图书主键',
  `rid` int(0) NULL DEFAULT NULL COMMENT '读者主键',
  `book_date` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '借书时间段',
  `deleted` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `readied` tinyint(0) NULL DEFAULT 0 COMMENT '是否被预留',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (1, 1, 1, '2022-09-05~2022-09-06', 0, '2022-10-08 22:02:27', '2022-10-08 22:02:30', 0);
INSERT INTO `borrow` VALUES (4, 1, 2, '2022-09-05~2022-09-06', 1, '2022-10-09 09:04:40', '2022-10-10 19:47:43', 0);
INSERT INTO `borrow` VALUES (9, 1, 13, '2022-10-11~2022-11-10', 0, '2022-10-11 06:19:08', '2022-10-11 15:10:00', 0);

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '读者id',
  `name` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '读者姓名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `deleted` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES (1, '温', '15037311458', 0, '2022-10-08 21:58:26', '2022-10-11 08:27:56');
INSERT INTO `reader` VALUES (2, '张', '11111111111', 1, '2022-10-08 11:09:31', '2022-10-10 19:47:43');
INSERT INTO `reader` VALUES (3, '里', '13783816970', 0, '2022-10-08 11:10:01', '2022-10-10 13:38:14');
INSERT INTO `reader` VALUES (4, '王', '23332', 0, '2022-10-08 11:10:16', '2022-10-08 11:10:18');
INSERT INTO `reader` VALUES (5, '阿萨德', '88888', 0, '2022-10-08 11:10:32', '2022-10-08 11:10:36');
INSERT INTO `reader` VALUES (6, '我的', '23131', 0, '2022-10-08 11:10:50', '2022-10-08 11:10:52');
INSERT INTO `reader` VALUES (7, '爱仕达', '231313', 0, '2022-10-08 11:11:05', '2022-10-08 11:11:07');
INSERT INTO `reader` VALUES (8, '四大', '231', 0, '2022-10-08 11:11:24', '2022-10-08 11:11:27');
INSERT INTO `reader` VALUES (9, '我', '23123', 0, '2022-10-08 11:11:36', '2022-10-08 11:11:38');
INSERT INTO `reader` VALUES (10, '我', '23134', 0, '2022-10-08 21:58:38', '2022-10-08 21:58:40');
INSERT INTO `reader` VALUES (11, 'we', '2312s3', 1, '2022-10-08 11:12:04', '2022-10-10 19:45:11');
INSERT INTO `reader` VALUES (12, '233', 'dsd', 1, '2022-10-09 09:45:17', '2022-10-10 19:45:11');
INSERT INTO `reader` VALUES (13, '许巍', '15294861193', 0, '2022-10-09 01:49:57', '2022-10-11 08:28:04');
INSERT INTO `reader` VALUES (14, '王天虎', '15294889196', 0, '2022-10-10 10:49:38', '2022-10-10 10:49:38');

SET FOREIGN_KEY_CHECKS = 1;
