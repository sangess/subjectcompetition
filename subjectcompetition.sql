/*
 Navicat Premium Data Transfer

 Source Server         : 测试
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : subjectcompetition

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 29/05/2021 01:12:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `target_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '校级管理员', '123456', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 'admin');

-- ----------------------------
-- Table structure for awards
-- ----------------------------
DROP TABLE IF EXISTS `awards`;
CREATE TABLE `awards`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_date` datetime(0) NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `sender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `update_apply_date` datetime(6) NULL DEFAULT NULL,
  `project_id` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK3td532ajaie398knl0t5mxb46`(`project_id`) USING BTREE,
  CONSTRAINT `FK3td532ajaie398knl0t5mxb46` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of awards
-- ----------------------------

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `target_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '计算机与信息工程学院', '123', 'c8adde47-3569-4a60-91db-ae1cc5270a92', '1212714291', '#193BE1');
INSERT INTO `college` VALUES (2, '土木工程学院', '123456', '4c3a6b9e-fcbb-4651-8951-b8927b939aa6', '546444554', '#5EA54C');
INSERT INTO `college` VALUES (3, '生命科学技术学院', '123456', '3ad2ae19-abac-4393-8420-8d30cb87ad21', '1703790869', '#E118BB');
INSERT INTO `college` VALUES (4, '物理与电子工程学院', '123456', '1188f7a8-9059-4057-b079-d80bf1330ca3', '721842408', '#F03963');
INSERT INTO `college` VALUES (5, '外国语学院', '123456', 'c2e7e424-d7f7-42b8-a548-f9ad6af8e70a', '956909296', NULL);
INSERT INTO `college` VALUES (6, '机械工程学院', '123456', '28c5950b-f3ca-4699-a22f-525a39586489', '1029801934', NULL);
INSERT INTO `college` VALUES (7, '电气工程学院', '123456', '66b95da8-232f-4ca3-8d50-8945503d766c', '39768663', NULL);
INSERT INTO `college` VALUES (8, '化学化工学院', '123456', 'c15f44a3-391d-43a3-ae44-81a3f078ce15', '1796400568', NULL);

-- ----------------------------
-- Table structure for college_comp
-- ----------------------------
DROP TABLE IF EXISTS `college_comp`;
CREATE TABLE `college_comp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_date` datetime(0) NULL DEFAULT NULL,
  `apply_or_join` int(11) NOT NULL,
  `update_apply_date` datetime(0) NULL DEFAULT NULL,
  `college_id` int(11) NULL DEFAULT NULL,
  `comp_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK25qgrhbumyfgs8r3cr7ky19sd`(`college_id`) USING BTREE,
  INDEX `FKj5j5by2864amy4mlkf8p7mje8`(`comp_id`) USING BTREE,
  CONSTRAINT `FK25qgrhbumyfgs8r3cr7ky19sd` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKj5j5by2864amy4mlkf8p7mje8` FOREIGN KEY (`comp_id`) REFERENCES `comp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 157 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of college_comp
-- ----------------------------
INSERT INTO `college_comp` VALUES (3, '2019-11-29 01:30:49', 3, '2019-12-09 01:30:49', 7, 3);
INSERT INTO `college_comp` VALUES (4, '2020-08-10 17:41:42', 3, '2019-10-09 01:58:36', 1, 2);
INSERT INTO `college_comp` VALUES (5, '2020-08-10 17:41:45', 3, '2020-07-09 01:58:43', 7, 5);
INSERT INTO `college_comp` VALUES (6, '2020-08-07 13:40:54', 3, '2020-03-17 10:38:42', 7, 2);
INSERT INTO `college_comp` VALUES (7, '2020-08-08 03:51:55', 3, '2020-07-17 13:40:54', 7, 4);
INSERT INTO `college_comp` VALUES (8, '2020-08-08 03:51:57', 3, '2020-07-18 03:51:55', 6, 2);
INSERT INTO `college_comp` VALUES (9, '2020-10-12 12:08:32', 3, '2020-07-18 03:51:57', 5, 3);
INSERT INTO `college_comp` VALUES (10, '2021-01-12 12:08:33', 3, '2020-07-18 03:57:01', 8, 8);
INSERT INTO `college_comp` VALUES (11, '2020-10-01 12:08:34', 3, '2020-07-18 03:57:03', 8, 9);
INSERT INTO `college_comp` VALUES (12, '2021-05-12 12:08:36', 3, '2020-07-20 17:34:49', 3, 5);
INSERT INTO `college_comp` VALUES (13, '2021-03-12 12:08:37', 3, '2020-07-20 17:35:49', 1, 5);
INSERT INTO `college_comp` VALUES (14, '2021-03-20 12:08:39', 3, '2020-07-20 17:39:47', 7, 2);
INSERT INTO `college_comp` VALUES (15, '2020-09-10 17:41:48', 3, '2020-07-20 17:39:48', 1, 3);
INSERT INTO `college_comp` VALUES (16, '2020-07-10 17:39:52', 3, '2020-07-20 17:39:52', 7, 6);
INSERT INTO `college_comp` VALUES (17, '2020-07-10 17:39:56', 3, '2020-07-20 17:39:56', 7, 7);
INSERT INTO `college_comp` VALUES (18, '2020-10-12 12:08:32', 3, '2020-07-20 17:39:59', 7, 9);
INSERT INTO `college_comp` VALUES (19, '2021-01-12 12:08:33', 3, '2020-07-20 17:40:01', 6, 13);
INSERT INTO `college_comp` VALUES (20, '2020-10-01 12:08:34', 3, '2020-07-20 17:40:26', 5, 2);
INSERT INTO `college_comp` VALUES (21, '2021-05-12 12:08:36', 3, '2020-07-20 17:40:27', 8, 3);
INSERT INTO `college_comp` VALUES (22, '2021-03-12 12:08:37', 3, '2020-07-20 17:40:28', 8, 5);
INSERT INTO `college_comp` VALUES (23, '2021-03-20 12:08:39', 3, '2020-07-20 17:40:31', 3, 6);
INSERT INTO `college_comp` VALUES (24, '2021-04-24 12:08:39', 3, '2020-07-20 17:40:32', 1, 7);
INSERT INTO `college_comp` VALUES (25, '2020-12-23 12:08:40', 3, '2020-07-20 17:40:33', 4, 9);
INSERT INTO `college_comp` VALUES (26, '2020-07-10 17:40:35', 3, '2020-07-20 17:40:35', 8, 13);
INSERT INTO `college_comp` VALUES (27, '2020-07-10 17:40:59', 3, '2020-07-20 17:40:59', 8, 2);
INSERT INTO `college_comp` VALUES (28, '2020-07-10 17:41:38', 3, '2020-07-20 17:40:59', 1, 3);
INSERT INTO `college_comp` VALUES (29, '2020-07-10 17:41:41', 3, '2020-07-20 17:41:00', 5, 5);
INSERT INTO `college_comp` VALUES (30, '2020-07-10 17:41:42', 3, '2020-07-20 17:41:01', 2, 6);
INSERT INTO `college_comp` VALUES (31, '2020-09-10 17:41:45', 3, '2020-07-20 17:41:03', 7, 7);
INSERT INTO `college_comp` VALUES (32, '2020-08-10 17:41:42', 3, '2020-07-20 17:41:05', 1, 9);
INSERT INTO `college_comp` VALUES (33, '2020-08-10 17:41:45', 3, '2020-07-20 17:41:07', 7, 13);
INSERT INTO `college_comp` VALUES (34, '2020-08-07 13:40:54', 3, '2020-07-20 17:41:12', 7, 16);
INSERT INTO `college_comp` VALUES (35, '2020-08-08 03:51:55', 3, '2020-07-20 17:41:13', 7, 17);
INSERT INTO `college_comp` VALUES (36, '2020-08-08 03:51:57', 3, '2020-07-20 17:41:14', 6, 18);
INSERT INTO `college_comp` VALUES (37, '2020-10-12 12:08:32', 3, '2020-07-20 17:41:36', 5, 2);
INSERT INTO `college_comp` VALUES (38, '2021-01-12 12:08:33', 3, '2020-07-20 17:41:36', 8, 3);
INSERT INTO `college_comp` VALUES (39, '2020-10-01 12:08:34', 3, '2020-07-20 17:41:37', 8, 5);
INSERT INTO `college_comp` VALUES (40, '2021-05-12 12:08:36', 3, '2020-07-20 17:41:38', 3, 6);
INSERT INTO `college_comp` VALUES (41, '2021-03-12 12:08:37', 3, '2020-07-20 17:41:41', 1, 7);
INSERT INTO `college_comp` VALUES (42, '2021-03-20 12:08:39', 3, '2020-07-20 17:41:42', 4, 9);
INSERT INTO `college_comp` VALUES (43, '2020-09-10 17:41:48', 3, '2020-07-20 17:41:45', 8, 13);
INSERT INTO `college_comp` VALUES (44, '2020-09-10 17:41:48', 3, '2020-07-20 17:41:48', 8, 16);
INSERT INTO `college_comp` VALUES (45, '2020-09-10 17:41:49', 3, '2020-07-20 17:41:49', 4, 17);
INSERT INTO `college_comp` VALUES (46, '2020-11-10 17:41:50', 3, '2020-07-20 17:41:50', 8, 18);
INSERT INTO `college_comp` VALUES (47, '2021-05-12 12:08:31', 3, '2021-05-12 12:08:54', 4, 18);
INSERT INTO `college_comp` VALUES (48, '2021-05-12 12:08:32', 3, '2021-05-12 12:08:54', 5, 17);
INSERT INTO `college_comp` VALUES (49, '2021-05-12 12:08:33', 3, '2021-05-12 12:08:54', 2, 16);
INSERT INTO `college_comp` VALUES (50, '2021-05-12 12:08:34', 3, '2021-05-12 12:08:55', 4, 13);
INSERT INTO `college_comp` VALUES (51, '2020-07-10 17:41:38', 3, '2021-05-12 12:08:55', 4, 9);
INSERT INTO `college_comp` VALUES (52, '2020-07-10 17:41:41', 3, '2021-05-12 12:08:55', 3, 7);
INSERT INTO `college_comp` VALUES (53, '2020-07-10 17:41:42', 3, '2021-05-12 12:08:55', 2, 6);
INSERT INTO `college_comp` VALUES (54, '2020-09-10 17:41:45', 3, '2021-05-12 12:08:55', 1, 5);
INSERT INTO `college_comp` VALUES (55, '2020-09-10 17:41:48', 3, '2021-05-12 12:08:55', 7, 3);
INSERT INTO `college_comp` VALUES (56, '2020-08-10 17:41:42', 3, '2021-05-12 12:08:56', 4, 2);
INSERT INTO `college_comp` VALUES (57, '2020-08-10 17:41:45', 3, '2020-07-20 17:41:36', 7, 2);
INSERT INTO `college_comp` VALUES (58, '2020-08-07 13:40:54', 3, '2020-07-20 17:41:36', 1, 3);
INSERT INTO `college_comp` VALUES (59, '2020-08-08 03:51:55', 3, '2020-07-20 17:41:37', 7, 5);
INSERT INTO `college_comp` VALUES (60, '2020-08-08 03:51:57', 3, '2020-07-20 17:41:38', 7, 32);
INSERT INTO `college_comp` VALUES (61, '2020-10-12 12:08:32', 3, '2020-07-20 17:41:41', 7, 12);
INSERT INTO `college_comp` VALUES (62, '2021-01-12 12:08:33', 3, '2020-07-20 17:41:42', 6, 33);
INSERT INTO `college_comp` VALUES (63, '2020-10-01 12:08:34', 3, '2020-07-20 17:41:45', 5, 18);
INSERT INTO `college_comp` VALUES (64, '2021-05-12 12:08:36', 3, '2020-07-20 17:41:48', 8, 19);
INSERT INTO `college_comp` VALUES (65, '2021-03-12 12:08:37', 3, '2020-07-20 17:41:49', 8, 17);
INSERT INTO `college_comp` VALUES (66, '2020-11-10 17:41:50', 3, '2020-07-20 17:41:50', 3, 18);
INSERT INTO `college_comp` VALUES (67, '2020-11-05 12:08:31', 3, '2021-05-12 12:08:54', 1, 18);
INSERT INTO `college_comp` VALUES (68, '2020-10-12 12:08:32', 3, '2021-05-12 12:08:54', 4, 17);
INSERT INTO `college_comp` VALUES (69, '2021-01-12 12:08:33', 3, '2021-05-12 12:08:54', 8, 16);
INSERT INTO `college_comp` VALUES (70, '2020-10-01 12:08:34', 3, '2021-05-12 12:08:55', 4, 2);
INSERT INTO `college_comp` VALUES (71, '2021-05-12 12:08:36', 3, '2021-05-12 12:08:55', 8, 3);
INSERT INTO `college_comp` VALUES (72, '2021-03-12 12:08:37', 3, '2021-05-12 12:08:55', 5, 7);
INSERT INTO `college_comp` VALUES (73, '2021-03-20 12:08:39', 3, '2021-05-12 12:08:55', 5, 4);
INSERT INTO `college_comp` VALUES (74, '2021-04-24 12:08:39', 3, '2021-05-12 12:08:55', 6, 6);
INSERT INTO `college_comp` VALUES (75, '2021-03-19 12:08:40', 3, '2021-05-12 12:08:55', 4, 7);
INSERT INTO `college_comp` VALUES (76, '2021-02-18 12:08:40', 3, '2021-05-12 12:08:56', 2, 8);
INSERT INTO `college_comp` VALUES (77, '2021-05-12 12:08:36', 3, '2021-05-12 12:08:55', 8, 3);
INSERT INTO `college_comp` VALUES (78, '2021-03-12 12:08:37', 3, '2021-05-12 12:08:55', 5, 7);
INSERT INTO `college_comp` VALUES (79, '2021-03-20 12:08:39', 3, '2021-05-12 12:08:55', 5, 4);
INSERT INTO `college_comp` VALUES (80, '2021-04-24 12:08:39', 3, '2021-05-12 12:08:55', 6, 6);
INSERT INTO `college_comp` VALUES (81, '2021-03-19 12:08:40', 3, '2021-05-12 12:08:55', 4, 7);
INSERT INTO `college_comp` VALUES (82, '2021-02-18 12:08:40', 3, '2021-05-12 12:08:56', 2, 8);
INSERT INTO `college_comp` VALUES (83, '2020-09-29 01:30:49', 3, '2021-04-29 01:39:06', 1, 3);
INSERT INTO `college_comp` VALUES (85, '2021-04-29 01:58:43', 3, '2021-04-29 01:59:22', 1, 5);
INSERT INTO `college_comp` VALUES (86, '2021-01-07 10:38:42', 3, '2021-05-07 20:14:23', 2, 2);
INSERT INTO `college_comp` VALUES (87, '2021-05-07 13:40:54', 3, '2021-05-07 20:14:25', 1, 4);
INSERT INTO `college_comp` VALUES (88, '2021-05-08 03:51:55', 3, '2021-05-08 03:53:07', 3, 2);
INSERT INTO `college_comp` VALUES (89, '2021-05-08 03:51:57', 3, '2021-05-08 03:53:08', 3, 3);
INSERT INTO `college_comp` VALUES (90, '2021-05-08 03:57:01', 3, '2021-05-08 04:12:38', 1, 8);
INSERT INTO `college_comp` VALUES (141, '2021-05-21 23:56:03', 3, '2021-05-21 23:56:11', 1, 62);
INSERT INTO `college_comp` VALUES (152, '2021-05-25 19:40:38', 5, '2021-05-26 16:35:56', 1, 75);
INSERT INTO `college_comp` VALUES (153, '2021-05-28 22:42:07', 0, '2021-05-28 22:42:07', 1, 76);
INSERT INTO `college_comp` VALUES (155, '2021-05-29 00:08:04', 3, '2021-05-29 00:08:23', 1, 77);
INSERT INTO `college_comp` VALUES (156, '2021-05-29 00:22:53', 3, '2021-05-29 00:23:14', 1, 78);

-- ----------------------------
-- Table structure for comp
-- ----------------------------
DROP TABLE IF EXISTS `comp`;
CREATE TABLE `comp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `can_apply_time` datetime(0) NULL DEFAULT NULL,
  `comp_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `comp_state` int(11) NULL DEFAULT NULL,
  `count_project` int(11) NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `max_count_project` int(11) NULL DEFAULT NULL,
  `max_count_student` int(11) NULL DEFAULT NULL,
  `max_count_teacher` int(11) NULL DEFAULT NULL,
  `not_apply_time` datetime(0) NULL DEFAULT NULL,
  `occurrence_time` datetime(0) NULL DEFAULT NULL,
  `organizer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `publish_time` datetime(0) NULL DEFAULT NULL,
  `subject_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `visiable` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comp
-- ----------------------------
INSERT INTO `comp` VALUES (2, '2021-07-11 00:00:00', '第七届蓝桥杯', 1, 0, 1, 10, 4, 4, '2021-06-16 00:00:00', '2021-07-11 00:00:00', '河南省XX大学', '河南省XX大学', '2021-05-12 12:08:36', '计算机', b'1');
INSERT INTO `comp` VALUES (3, '2021-03-27 12:08:37', 'ACM', 3, 1, 0, 10, 3, 4, '2021-04-16 12:08:37', '2021-05-11 12:08:37', '中XX大学', '安徽合肥省', '2021-03-12 12:08:37', '计算机', b'1');
INSERT INTO `comp` VALUES (4, '2020-05-14 00:37:02', '全国大学生歌唱比赛', 3, 0, 2, 10, 10, 2, '2020-06-03 00:37:02', '2020-06-28 00:37:02', 'XX大学', 'XX大学', '2020-04-29 00:37:02', '艺术', b'1');
INSERT INTO `comp` VALUES (5, '2020-05-14 00:43:23', '疫情感恩作文大赛', 3, 4, 0, 4, 1, 1, '2020-06-03 00:43:23', '2020-06-28 00:43:23', '国家教育厅', '中国北京', '2020-04-29 00:43:23', '文学', b'1');
INSERT INTO `comp` VALUES (6, '2020-05-14 00:44:15', '全国大学生结构设计大赛', 3, 0, 0, 10, 10, 3, '2020-06-03 00:44:15', '2020-06-28 00:44:15', '河北教育部', '河北省石家庄', '2020-04-29 00:44:15', '计算机', b'1');
INSERT INTO `comp` VALUES (7, '2020-05-14 00:45:17', '大学生多媒体作品设计大赛', 3, 0, 2, 10, 4, 2, '2020-06-03 00:45:17', '2020-06-28 00:45:17', 'XX大学', 'A14', '2020-04-29 00:45:17', '计算机', b'1');
INSERT INTO `comp` VALUES (8, '2020-05-14 01:04:36', '“CCTV杯”全国英语演讲大赛', 3, 0, 0, 10, 1, 2, '2020-06-03 01:04:36', '2020-06-28 01:04:36', '中国央视', '中国北京', '2020-04-29 01:04:36', '影视传媒/英语', b'1');
INSERT INTO `comp` VALUES (9, '2020-05-23 03:36:12', '全国大学生数学竞赛', 3, 0, 0, 10, 10, 2, '2020-06-12 03:36:12', '2020-07-07 03:36:12', '国家教育部', '北京', '2020-05-08 03:36:12', '数学', b'1');
INSERT INTO `comp` VALUES (10, '2020-07-25 17:39:56', '全国大学生街舞挑战赛', 3, 0, 1, 999, 10, 1, '2020-08-14 17:39:56', '2020-09-08 17:39:56', '中国街舞协会', '天津', '2020-07-10 17:39:56', '艺术', b'1');
INSERT INTO `comp` VALUES (11, '2021-04-04 12:08:39', '全国大学生结构设计大赛', 3, 0, 0, 10, 4, 2, '2021-04-24 12:08:39', '2021-05-19 12:08:39', '河北教育部', '石家庄', '2021-03-20 12:08:39', '综合', b'1');
INSERT INTO `comp` VALUES (12, '2020-10-27 12:08:32', '全国大学生数学建模竞赛', 3, 0, 0, 10, 2, 1, '2020-11-16 12:08:32', '2020-12-11 12:08:32', '国家教育部', 'XX大学', '2020-10-12 12:08:32', '数学', b'1');
INSERT INTO `comp` VALUES (13, '2020-05-23 03:45:16', '大学生机电产品创新设计竞赛', 3, 0, 1, 4, 4, 1, '2020-06-12 03:45:16', '2020-07-07 03:45:16', '河南省教育部', 'XX大学', '2020-05-08 03:45:16', '电子信息', b'1');
INSERT INTO `comp` VALUES (14, '2020-05-23 03:46:53', 'SCILAB自由软件编程竞赛', 3, 0, 0, 4, 3, 2, '2020-06-12 03:46:53', '2020-07-07 03:46:53', '河南省教育部', 'XX大学', '2020-05-08 03:46:53', '综合', b'1');
INSERT INTO `comp` VALUES (15, '2020-05-23 04:10:12', '大学生计算机设计大赛', 3, 0, 0, 10, 3, 2, '2020-06-12 04:10:12', '2020-07-07 04:10:12', '河南省教育部', '郑州', '2020-05-08 04:10:12', '计算机科学', b'1');
INSERT INTO `comp` VALUES (16, '2020-10-16 12:08:34', '全国大学生创新创业竞赛', 3, 0, 0, 40, 7, 4, '2020-11-05 12:08:34', '2020-11-30 12:08:34', 'XX大学', 'XX大学', '2020-10-01 12:08:34', '创新', b'1');
INSERT INTO `comp` VALUES (17, '2021-05-09 12:08:39', '全国大学生数学建模竞赛', 2, 0, 2, 20, 5, 2, '2021-05-29 12:08:39', '2021-06-23 12:08:39', 'XX大学', '数学学院', '2021-04-24 12:08:39', '数学', b'1');
INSERT INTO `comp` VALUES (18, '2021-03-27 12:08:37', '中国大学生智力运动联赛', 3, 0, 1, 40, 7, 2, '2021-04-16 12:08:37', '2021-05-11 12:08:37', 'XX大学', 'XX大学', '2021-03-12 12:08:37', '体育', b'1');
INSERT INTO `comp` VALUES (19, '2020-07-25 17:40:59', '全国大学生数学竞赛', 3, 4, 0, 4, 1, 1, '2020-08-14 17:40:59', '2020-09-08 17:40:59', '国家教育厅', '中国北京', '2020-07-10 17:40:59', '文学', b'1');
INSERT INTO `comp` VALUES (20, '2020-10-27 12:08:32', '全国大学生英语竞赛', 3, 0, 0, 10, 10, 3, '2020-11-16 12:08:32', '2020-12-11 12:08:32', '河北教育部', '河北省石家庄', '2020-10-12 12:08:32', '计算机', b'1');
INSERT INTO `comp` VALUES (21, '2020-05-14 00:45:17', '全国大学校院学生创意实作竞赛', 3, 0, 2, 10, 4, 2, '2020-06-03 00:45:17', '2020-06-28 00:45:17', '新乡学院', 'A14', '2020-04-29 00:45:17', '计算机', b'1');
INSERT INTO `comp` VALUES (22, '2020-05-14 01:04:36', '“CCTV杯”全国英语演讲大赛', 3, 0, 0, 10, 1, 2, '2020-06-03 01:04:36', '2020-06-28 01:04:36', '中国央视', '中国北京', '2020-04-29 01:04:36', '影视传媒/英语', b'1');
INSERT INTO `comp` VALUES (23, '2020-05-23 03:36:12', '全大学生DV影像艺术竞赛', 3, 0, 0, 10, 10, 2, '2020-06-12 03:36:12', '2020-07-07 03:36:12', '国家教育部', '北京', '2020-05-08 03:36:12', '数学', b'1');
INSERT INTO `comp` VALUES (24, '2020-05-23 03:41:07', '全国大学生街舞挑战赛', 3, 0, 1, 999, 10, 1, '2020-06-12 03:41:07', '2020-07-07 03:41:07', '中国街舞协会', '天津', '2020-05-08 03:41:07', '艺术', b'1');
INSERT INTO `comp` VALUES (25, '2020-10-27 12:08:32', '大学生多媒体作品设计大赛', 3, 0, 0, 10, 4, 2, '2020-11-16 12:08:32', '2020-12-11 12:08:32', '河北教育部', '石家庄', '2020-10-12 12:08:32', '综合', b'1');
INSERT INTO `comp` VALUES (26, '2020-07-25 17:39:52', '全国大学生歌唱比赛', 3, 0, 0, 10, 2, 1, '2020-08-14 17:39:52', '2020-09-08 17:39:52', '国家教育部', 'XX大学', '2020-07-10 17:39:52', '数学', b'1');
INSERT INTO `comp` VALUES (27, '2021-01-27 12:08:33', '全国大学生数学建模竞赛', 3, 0, 1, 4, 4, 1, '2021-02-16 12:08:33', '2021-03-13 12:08:33', '河南省教育部', 'XX大学', '2021-01-12 12:08:33', '电子信息', b'1');
INSERT INTO `comp` VALUES (28, '2020-05-23 03:46:53', '大学生机电产品创新设计竞赛', 3, 0, 0, 4, 3, 2, '2020-06-12 03:46:53', '2020-07-07 03:46:53', '河南省教育部', 'XX大学', '2020-05-08 03:46:53', '综合', b'1');
INSERT INTO `comp` VALUES (29, '2021-01-27 12:08:33', 'SCILAB自由软件编程竞赛', 3, 0, 0, 10, 3, 2, '2021-02-16 12:08:33', '2021-03-13 12:08:33', '河南省教育部', '郑州', '2021-01-12 12:08:33', '计算机科学', b'1');
INSERT INTO `comp` VALUES (30, '2020-05-25 16:29:26', '全国大学生结构设计大赛', 3, 0, 0, 40, 7, 4, '2020-06-14 16:29:26', '2020-07-09 16:29:26', 'XX大学', 'XX大学', '2020-05-10 16:29:26', '创新', b'1');
INSERT INTO `comp` VALUES (31, '2021-05-27 12:08:36', 'ACM国际编程大赛', 1, 0, 2, 20, 5, 2, '2021-06-16 12:08:36', '2021-07-11 12:08:36', 'XX大学', '数学学院', '2021-05-12 12:08:36', '数学', b'1');
INSERT INTO `comp` VALUES (32, '2021-04-03 12:08:40', '全国大学生计算机知识竞赛', 3, 0, 1, 40, 7, 2, '2021-04-23 12:08:40', '2021-05-18 12:08:40', 'XX大学', 'XX大学', '2021-03-19 12:08:40', '体育', b'1');
INSERT INTO `comp` VALUES (33, '2021-04-04 12:08:39', '疫情感恩作文大赛', 3, 0, 1, 40, 7, 2, '2021-04-24 12:08:39', '2021-05-19 12:08:39', 'XX大学', 'XX大学', '2021-03-20 12:08:39', '体育', b'1');
INSERT INTO `comp` VALUES (34, '2020-10-16 12:08:34', '第三节青春有你歌唱比赛', 3, 4, 0, 4, 1, 1, '2020-11-05 12:08:34', '2020-11-30 12:08:34', '国家教育厅', '中国北京', '2020-10-01 12:08:34', '文学', b'1');
INSERT INTO `comp` VALUES (35, '2021-05-13 02:08:57', '第七届蓝桥杯', 2, 1, 1, 10, 4, 4, '2021-06-02 02:08:57', '2021-06-27 02:08:57', 'XX大学', 'XX大学', '2021-04-28 02:08:57', '计算机', b'1');
INSERT INTO `comp` VALUES (36, '2021-05-13 10:19:02', 'ACM', 2, 1, 0, 10, 3, 4, '2021-06-02 10:19:02', '2021-06-27 10:19:02', 'XX大学', '安徽合肥省', '2021-04-28 10:19:02', '计算机', b'1');
INSERT INTO `comp` VALUES (37, '2021-05-14 00:37:02', '全国大学生歌唱比赛', 2, 0, 2, 10, 10, 2, '2021-06-03 00:37:02', '2021-06-28 00:37:02', 'XX大学', 'XX大学', '2021-04-29 00:37:02', '艺术', b'1');
INSERT INTO `comp` VALUES (38, '2021-05-14 00:43:23', '疫情感恩作文大赛', 2, 4, 0, 4, 1, 1, '2021-06-03 00:43:23', '2021-06-28 00:43:23', '国家教育厅', '中国北京', '2021-04-29 00:43:23', '文学', b'1');
INSERT INTO `comp` VALUES (39, '2021-05-14 00:44:15', '全国大学生结构设计大赛', 2, 0, 0, 10, 10, 3, '2021-06-03 00:44:15', '2021-06-28 00:44:15', '河北教育部', '河北省石家庄', '2021-04-29 00:44:15', '计算机', b'1');
INSERT INTO `comp` VALUES (40, '2021-05-14 00:45:17', '大学生多媒体作品设计大赛', 2, 0, 2, 10, 4, 2, '2021-06-03 00:45:17', '2021-06-28 00:45:17', '新乡学院', 'A14', '2021-04-29 00:45:17', '计算机', b'1');
INSERT INTO `comp` VALUES (41, '2021-05-14 01:04:36', '“CCTV杯”全国英语演讲大赛', 2, 0, 0, 10, 1, 2, '2021-06-03 01:04:36', '2021-06-28 01:04:36', '中国央视', '中国北京', '2021-04-29 01:04:36', '影视传媒/英语', b'1');
INSERT INTO `comp` VALUES (42, '2021-05-23 03:36:12', '全国大学生数学竞赛', 1, 0, 0, 10, 10, 2, '2021-06-12 03:36:12', '2021-07-07 03:36:12', '国家教育部', '北京', '2021-05-08 03:36:12', '数学', b'1');
INSERT INTO `comp` VALUES (43, '2021-05-23 03:41:07', '全国大学生街舞挑战赛', 1, 0, 1, 999, 10, 1, '2021-06-12 03:41:07', '2021-07-07 03:41:07', '中国街舞协会', '天津', '2021-05-08 03:41:07', '艺术', b'1');
INSERT INTO `comp` VALUES (44, '2021-05-23 03:42:15', '全国大学生结构设计大赛', 1, 0, 0, 10, 4, 2, '2021-06-12 03:42:15', '2021-07-07 03:42:15', '河北教育部', '石家庄', '2021-05-08 03:42:15', '综合', b'1');
INSERT INTO `comp` VALUES (45, '2021-05-23 03:44:25', '全国大学生数学建模竞赛', 1, 0, 0, 10, 2, 1, '2021-06-12 03:44:25', '2021-07-07 03:44:25', '国家教育部', 'XX大学', '2021-05-08 03:44:25', '数学', b'1');
INSERT INTO `comp` VALUES (46, '2021-05-23 03:45:16', '大学生机电产品创新设计竞赛', 1, 0, 1, 4, 4, 1, '2021-06-12 03:45:16', '2021-07-07 03:45:16', '河南省教育部', 'XX大学', '2021-05-08 03:45:16', '电子信息', b'1');
INSERT INTO `comp` VALUES (47, '2021-05-23 03:46:53', 'SCILAB自由软件编程竞赛', 1, 0, 0, 4, 3, 2, '2021-06-12 03:46:53', '2021-07-07 03:46:53', '河南省教育部', 'XX大学', '2021-05-08 03:46:53', '综合', b'1');
INSERT INTO `comp` VALUES (48, '2021-05-23 04:10:12', '大学生计算机设计大赛', 1, 0, 0, 10, 3, 2, '2021-06-12 04:10:12', '2021-07-07 04:10:12', '河南省教育部', '郑州', '2021-05-08 04:10:12', '计算机科学', b'1');
INSERT INTO `comp` VALUES (49, '2021-05-25 16:29:26', '全国大学生创新创业竞赛', 1, 0, 0, 40, 7, 4, '2021-06-14 16:29:26', '2021-07-09 16:29:26', 'XX大学', 'XX大学', '2021-05-10 16:29:26', '创新', b'1');
INSERT INTO `comp` VALUES (50, '2021-05-25 16:31:08', '全国大学生数学建模竞赛', 1, 0, 2, 20, 5, 2, '2021-06-14 16:31:08', '2021-07-09 16:31:08', 'XX大学', '数学学院', '2021-05-10 16:31:08', '数学', b'1');
INSERT INTO `comp` VALUES (51, '2021-05-25 16:32:59', '中国大学生智力运动联赛', 1, 0, 1, 40, 7, 2, '2021-06-14 16:32:59', '2021-07-09 16:32:59', 'XX大学', 'XX大学', '2021-05-10 16:32:59', '体育', b'1');
INSERT INTO `comp` VALUES (52, '2021-06-28 00:00:00', '第十四届全国大学生信息安全竞赛', 1, 1, 1, 10, 4, 2, '2021-06-19 00:00:00', '2021-06-28 00:00:00', '国卫信安教育科技有限公司', '线上赛', '2021-05-17 01:23:49', '计算机', b'1');
INSERT INTO `comp` VALUES (53, '2021-06-30 00:00:00', '共和国会更好', 1, 1, 0, 12, 6, 2, '2021-05-31 00:00:00', '2021-06-30 00:00:00', '是大餐', '12', '2021-05-17 15:36:27', '啥的', b'1');
INSERT INTO `comp` VALUES (54, '2021-06-30 00:00:00', 'cxv', 1, 0, 0, 12, 3, 2, '2021-05-31 00:00:00', '2021-06-30 00:00:00', '123', '45', '2021-05-17 15:50:48', '12', b'1');
INSERT INTO `comp` VALUES (55, '2021-07-14 00:00:00', '人工智能', 2, 1, 0, 12, 6, 2, '2021-05-25 00:00:00', '2021-07-14 00:00:00', '12', '12', '2021-05-17 16:08:48', '12', b'1');
INSERT INTO `comp` VALUES (56, '2021-07-20 00:00:00', 'zzz', 1, 1, 0, 10, 1, 2, '2021-06-28 00:00:00', '2021-07-20 00:00:00', 'xx', '14', '2021-05-19 16:57:11', 'jisaunji', b'1');
INSERT INTO `comp` VALUES (57, '2021-06-30 00:00:00', '520竞赛', 1, 1, 0, 10, 4, 2, '2021-06-01 00:00:00', '2021-06-30 00:00:00', 'xxxx', '郑州', '2021-05-20 16:11:44', '计算机', b'1');
INSERT INTO `comp` VALUES (58, '2021-07-31 00:00:00', '新竞赛', 1, 1, 0, 4, 4, 2, '2021-05-31 00:00:00', '2021-07-31 00:00:00', 'xx大学', '郑州', '2021-05-21 11:53:36', '计算机', b'1');
INSERT INTO `comp` VALUES (59, '2021-06-30 00:00:00', '新竞赛', 1, 1, 0, 4, 4, 1, '2021-05-31 00:00:00', '2021-06-30 00:00:00', 'xx大学', '郑州', '2021-05-21 12:47:40', '计算机', b'1');
INSERT INTO `comp` VALUES (60, '2021-07-31 00:00:00', '这是一个新竞赛', 1, 1, 1, 4, 4, 1, '2021-05-31 00:00:00', '2021-07-31 00:00:00', 'xx大学', 'xx大学', '2021-05-21 12:59:23', '计算机', b'1');
INSERT INTO `comp` VALUES (61, '2021-05-31 00:00:00', 'xingjingsai', 2, 1, 0, 10, 2, 1, '2021-05-23 00:00:00', '2021-05-31 00:00:00', 'xinxii', 'zhnegzou', '2021-05-21 22:22:02', 'jisuanji', b'1');
INSERT INTO `comp` VALUES (62, '2021-06-01 00:00:00', '比赛yi', 2, 1, 1, 10, 2, 1, '2021-05-24 00:00:00', '2021-06-01 00:00:00', '计算机协会', '郑州', '2021-05-21 23:51:18', '计算机', b'1');
INSERT INTO `comp` VALUES (63, '2021-06-26 00:00:00', '全国大学生计算机设计大赛', 1, 1, 0, 10, 4, 2, '2021-06-05 00:00:00', '2021-06-26 00:00:00', 'xx大学', 'xx大学', '2021-05-22 16:11:31', '计算机', b'1');
INSERT INTO `comp` VALUES (75, '2021-08-26 00:00:00', '新竞赛', 1, 0, 2, 10, 2, 2, '2021-05-30 00:00:00', '2021-08-26 00:00:00', '新乡学院', '新乡学院', '2021-05-26 16:35:56', '人文艺术', b'1');
INSERT INTO `comp` VALUES (76, '2021-06-01 00:00:00', '计算机大赛', 0, 0, 0, 10, 2, 1, '2021-06-30 00:00:00', '2021-07-10 00:00:00', '新乡学院', '现象学院', NULL, '计算机', b'0');
INSERT INTO `comp` VALUES (77, '2021-07-30 00:00:00', '360信息安全大赛', 1, 1, 0, 10, 4, 1, '2021-06-20 00:00:00', '2021-07-30 00:00:00', '新乡学院', '计算机学院', '2021-05-28 23:17:05', '计算机', b'1');
INSERT INTO `comp` VALUES (78, '2021-08-06 00:00:00', '全国大学生计算机设计大赛', 1, 1, 0, 10, 10, 2, '2021-06-29 00:00:00', '2021-08-06 00:00:00', '新乡学院', 'A14楼', '2021-05-29 00:18:16', '计算机', b'1');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `if_read` bit(1) NULL DEFAULT NULL,
  `msg_date` datetime(6) NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `receiver_role` int(11) NULL DEFAULT NULL,
  `sender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sender_role` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 138 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (2, '李安老师，用户名：445393889发出项目申请  项目名:最强大脑所属比赛为：疫情感恩作文大赛请尽快处理！', b'0', '2021-05-07 13:10:31.976000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (3, '生命科学技术学院正在申请比赛：第七届蓝桥杯,请尽快处理！', b'0', '2021-05-08 03:51:55.306000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '3ad2ae19-abac-4393-8420-8d30cb87ad21', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (4, '生命科学技术学院正在申请比赛：ACM,请尽快处理！', b'0', '2021-05-08 03:51:57.082000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '3ad2ae19-abac-4393-8420-8d30cb87ad21', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (5, '计算机与信息工程学院正在申请比赛：“CCTV杯”全国英语演讲大赛,请尽快处理！', b'0', '2021-05-08 03:57:01.109000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (6, '计算机与信息工程学院正在申请比赛：全国大学生数学竞赛,请尽快处理！', b'0', '2021-05-08 03:57:02.664000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (7, '计算机与信息工程学院已为您创建新账号：1564369508,您可以申请项目了哟', b'0', '2021-05-08 03:57:46.203000', '54323861-5ddf-4d25-956d-77399ebba4b0', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '欢迎新成员！', 2);
INSERT INTO `message` VALUES (8, '李仕达老师，用户名：1564369508发出项目申请  项目名:乘风破浪，所属比赛为：第七届蓝桥杯请尽快处理！', b'1', '2021-05-08 03:59:35.627000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '54323861-5ddf-4d25-956d-77399ebba4b0', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (9, '李仕达老师，用户名：1564369508发出项目申请  项目名:小强当自强，所属比赛为：全国大学生歌唱比赛请尽快处理！', b'1', '2021-05-08 04:00:25.068000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '54323861-5ddf-4d25-956d-77399ebba4b0', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (10, '李仕达老师，用户名：1564369508发出项目申请  项目名:黑马程序员联盟，所属比赛为：ACM请尽快处理！', b'1', '2021-05-08 04:00:50.125000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '54323861-5ddf-4d25-956d-77399ebba4b0', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (11, '计算机与信息工程学院为你创建的新的账号：2141021750', b'0', '2021-05-08 04:01:54.615000', '470d2b6b1dd04d45859e79194c00ad0e', 0, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '欢迎新成员', 2);
INSERT INTO `message` VALUES (12, '李仕达老师，用户名：1564369508发出项目申请  项目名:青春的声音，所属比赛为：全国大学生歌唱比赛请尽快处理！', b'1', '2021-05-08 04:14:43.464000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '54323861-5ddf-4d25-956d-77399ebba4b0', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (13, '计算机与信息工程学院已同意您申请的项目：最强大脑', b'1', '2021-05-08 04:15:20.748000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (14, '计算机与信息工程学院已同意您申请的项目：乘风破浪', b'1', '2021-05-08 04:15:23.105000', '54323861-5ddf-4d25-956d-77399ebba4b0', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (15, '计算机与信息工程学院已同意您申请的项目：小强当自强', b'1', '2021-05-08 04:15:25.668000', '54323861-5ddf-4d25-956d-77399ebba4b0', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (16, '计算机与信息工程学院已同意您申请的项目：黑马程序员联盟', b'1', '2021-05-08 04:15:27.590000', '54323861-5ddf-4d25-956d-77399ebba4b0', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (17, '计算机与信息工程学院已同意您申请的项目：青春的声音', b'1', '2021-05-08 04:15:29.333000', '54323861-5ddf-4d25-956d-77399ebba4b0', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (18, '梅笑艳同学，用户名：2141021750申请加入 项目:新院之心所属比赛为：第七届蓝桥杯', b'0', '2021-05-08 04:17:42.956000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '470d2b6b1dd04d45859e79194c00ad0e', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (19, '梅笑艳同学，用户名：2141021750申请加入 项目:最强大脑所属比赛为：疫情感恩作文大赛', b'0', '2021-05-08 04:17:59.889000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '470d2b6b1dd04d45859e79194c00ad0e', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (20, '梅笑艳同学，用户名：2141021750申请加入 项目:黑马程序员联盟所属比赛为：ACM', b'1', '2021-05-08 04:18:08.415000', '54323861-5ddf-4d25-956d-77399ebba4b0', 1, '470d2b6b1dd04d45859e79194c00ad0e', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (21, '梅笑艳同学，用户名：2141021750申请加入 项目:青春的声音所属比赛为：全国大学生歌唱比赛', b'1', '2021-05-08 04:18:10.200000', '54323861-5ddf-4d25-956d-77399ebba4b0', 1, '470d2b6b1dd04d45859e79194c00ad0e', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (22, '李仕达老师，用户名：1564369508驳回了你的申请 项目:黑马程序员联盟，所属比赛为：ACM', b'0', '2021-05-08 04:18:32.151000', '470d2b6b1dd04d45859e79194c00ad0e', 0, '54323861-5ddf-4d25-956d-77399ebba4b0', 1, '你的比赛申请已被驳回', 1);
INSERT INTO `message` VALUES (23, '李仕达老师，用户名：1564369508驳回了你的申请 项目:青春的声音，所属比赛为：全国大学生歌唱比赛', b'0', '2021-05-08 04:18:35.051000', '470d2b6b1dd04d45859e79194c00ad0e', 0, '54323861-5ddf-4d25-956d-77399ebba4b0', 1, '你的比赛申请已被驳回', 1);
INSERT INTO `message` VALUES (24, '物理与电子工程学院正在申请比赛：中国大学生智力运动联赛,请尽快处理！', b'1', '2021-05-12 12:08:31.291000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (25, '物理与电子工程学院正在申请比赛：全国大学生数学建模竞赛,请尽快处理！', b'1', '2021-05-12 12:08:32.001000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (26, '物理与电子工程学院正在申请比赛：全国大学生创新创业竞赛,请尽快处理！', b'1', '2021-05-12 12:08:32.950000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (27, '物理与电子工程学院正在申请比赛：大学生机电产品创新设计竞赛,请尽快处理！', b'1', '2021-05-12 12:08:34.174000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (28, '物理与电子工程学院正在申请比赛：全国大学生数学竞赛,请尽快处理！', b'1', '2021-05-12 12:08:35.517000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (29, '物理与电子工程学院正在申请比赛：大学生多媒体作品设计大赛,请尽快处理！', b'1', '2021-05-12 12:08:36.847000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (30, '物理与电子工程学院正在申请比赛：全国大学生结构设计大赛,请尽快处理！', b'1', '2021-05-12 12:08:38.752000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (31, '物理与电子工程学院正在申请比赛：疫情感恩作文大赛,请尽快处理！', b'1', '2021-05-12 12:08:39.147000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (32, '物理与电子工程学院正在申请比赛：ACM,请尽快处理！', b'1', '2021-05-12 12:08:39.783000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (33, '物理与电子工程学院正在申请比赛：第七届蓝桥杯,请尽快处理！', b'1', '2021-05-12 12:08:40.388000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (34, '管理员已同意您申请的比赛：中国大学生智力运动联赛', b'0', '2021-05-12 12:08:53.590000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (35, '管理员已同意您申请的比赛：全国大学生数学建模竞赛', b'0', '2021-05-12 12:08:54.189000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (36, '管理员已同意您申请的比赛：全国大学生创新创业竞赛', b'0', '2021-05-12 12:08:54.339000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (37, '管理员已同意您申请的比赛：大学生机电产品创新设计竞赛', b'0', '2021-05-12 12:08:54.508000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (38, '管理员已同意您申请的比赛：全国大学生数学竞赛', b'0', '2021-05-12 12:08:54.732000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (39, '管理员已同意您申请的比赛：大学生多媒体作品设计大赛', b'0', '2021-05-12 12:08:54.925000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (40, '管理员已同意您申请的比赛：全国大学生结构设计大赛', b'0', '2021-05-12 12:08:55.100000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (41, '管理员已同意您申请的比赛：疫情感恩作文大赛', b'0', '2021-05-12 12:08:55.286000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (42, '管理员已同意您申请的比赛：ACM', b'0', '2021-05-12 12:08:55.461000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (43, '管理员已同意您申请的比赛：第七届蓝桥杯', b'0', '2021-05-12 12:08:55.620000', '1188f7a8-9059-4057-b079-d80bf1330ca3', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (44, '李安老师，用户名：445393889申请加入项目:舞龙天下所属比赛为：第七届蓝桥杯', b'0', '2021-05-12 20:26:37.435000', '373fa9e1-bf71-429b-a138-17b2a3616809', 1, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新成员想加入', 2);
INSERT INTO `message` VALUES (45, '化学化工学院正在申请比赛：ACM国际编程大赛,请尽快处理！', b'1', '2021-05-13 00:08:04.203000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c15f44a3-391d-43a3-ae44-81a3f078ce15', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (46, '化学化工学院正在申请比赛：中国大学生智力运动联赛,请尽快处理！', b'1', '2021-05-13 00:08:05.648000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c15f44a3-391d-43a3-ae44-81a3f078ce15', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (47, '化学化工学院正在申请比赛：全国大学生数学建模竞赛,请尽快处理！', b'1', '2021-05-13 00:08:07.084000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c15f44a3-391d-43a3-ae44-81a3f078ce15', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (48, '化学化工学院正在申请比赛：全国大学生创新创业竞赛,请尽快处理！', b'1', '2021-05-13 00:08:09.479000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c15f44a3-391d-43a3-ae44-81a3f078ce15', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (49, '计算机与信息工程学院正在申请比赛：第十四届全国大学生信息安全竞赛,请尽快处理！', b'1', '2021-05-17 01:31:49.407000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (50, '管理员已同意您申请的比赛：第十四届全国大学生信息安全竞赛', b'1', '2021-05-17 01:32:02.171000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (51, '梅笑艳老师，用户名：1900656401发出项目申请  项目名:白帽子一盟，所属比赛为：第十四届全国大学生信息安全竞赛请尽快处理！', b'1', '2021-05-17 01:33:15.280000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '9747ec05-17ff-47ba-9c9b-4a08a4ded27e', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (52, '计算机与信息工程学院已同意您申请的项目：白帽子一盟', b'0', '2021-05-17 01:33:55.255000', '9747ec05-17ff-47ba-9c9b-4a08a4ded27e', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (53, '李安老师，用户名：445393889申请加入项目:白帽子一盟所属比赛为：第十四届全国大学生信息安全竞赛', b'0', '2021-05-17 01:56:52.119000', '9747ec05-17ff-47ba-9c9b-4a08a4ded27e', 1, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新成员想加入', 2);
INSERT INTO `message` VALUES (54, '你申请加入的项目:白帽子一盟审核通过了，所属比赛为：第十四届全国大学生信息安全竞赛', b'0', '2021-05-17 01:56:57.804000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '9747ec05-17ff-47ba-9c9b-4a08a4ded27e', 1, '你的项目申请通过了', 2);
INSERT INTO `message` VALUES (55, '冯菲同学，用户名：759084478申请加入 项目:白帽子一盟所属比赛为：第十四届全国大学生信息安全竞赛', b'0', '2021-05-17 01:58:23.882000', '9747ec05-17ff-47ba-9c9b-4a08a4ded27e', 1, '87341b63684241faaf89305b969f3812', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (56, '梅笑艳老师，用户名：1900656401同意了你的申请你已加入项目:白帽子一盟，所属比赛为：第十四届全国大学生信息安全竞赛', b'1', '2021-05-17 01:59:15.289000', '87341b63684241faaf89305b969f3812', 0, '9747ec05-17ff-47ba-9c9b-4a08a4ded27e', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (57, '计算机与信息工程学院正在申请比赛：共和国会更好,请尽快处理！', b'1', '2021-05-17 15:37:44.219000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (58, '管理员已同意您申请的比赛：共和国会更好', b'0', '2021-05-17 15:38:06.190000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (59, '李安老师，用户名：445393889发出项目申请  项目名:大学生，所属比赛为：共和国会更好请尽快处理！', b'0', '2021-05-17 15:39:27.687000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (60, '计算机与信息工程学院已同意您申请的项目：大学生', b'0', '2021-05-17 15:39:51.542000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (61, '李安老师，用户名：445393889申请加入项目:蓝桥杯新院分杯所属比赛为：全国大学生歌唱比赛', b'0', '2021-05-17 15:43:00.291000', '9747ec05-17ff-47ba-9c9b-4a08a4ded27e', 1, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新成员想加入', 2);
INSERT INTO `message` VALUES (62, '周力同学，用户名：1912685673申请加入 项目:大学生所属比赛为：共和国会更好', b'0', '2021-05-17 15:43:38.236000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '859cec7c2e5d4b9e8271439931c7ac03', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (63, '李安老师，用户名：445393889同意了你的申请你已加入项目:大学生，所属比赛为：共和国会更好', b'0', '2021-05-17 15:44:04.682000', '859cec7c2e5d4b9e8271439931c7ac03', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (64, '计算机与信息工程学院正在申请比赛：cxv,请尽快处理！', b'1', '2021-05-17 15:52:09.023000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (65, '管理员已同意您申请的比赛：cxv', b'0', '2021-05-17 15:52:15.494000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (66, '计算机与信息工程学院正在申请比赛：人工智能,请尽快处理！', b'1', '2021-05-17 16:09:29.777000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (67, '管理员已同意您申请的比赛：人工智能', b'0', '2021-05-17 16:09:55.067000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (68, '李安老师，用户名：445393889发出项目申请  项目名:123，所属比赛为：人工智能请尽快处理！', b'0', '2021-05-17 16:10:34.529000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (69, '计算机与信息工程学院已同意您申请的项目：123', b'0', '2021-05-17 16:10:53.382000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (70, '周力同学，用户名：1912685673申请加入 项目:123所属比赛为：人工智能', b'1', '2021-05-17 16:12:25.023000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '859cec7c2e5d4b9e8271439931c7ac03', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (71, '同学一老师，用户名：445393889同意了你的申请你已加入项目:123，所属比赛为：人工智能', b'1', '2021-05-17 16:12:35.278000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (72, '同学一同学，用户名：159951159申请加入 项目:大学生所属比赛为：共和国会更好', b'1', '2021-05-17 16:20:14.172000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (73, '同学一同学，用户名：159951159申请加入 项目:计算机竞赛小分队所属比赛为：ACM', b'1', '2021-05-17 16:21:23.257000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (74, '同学一同学，用户名：159951159申请加入 项目:123所属比赛为：人工智能', b'1', '2021-05-18 01:13:08.951000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (75, '计算机与信息工程学院正在申请比赛：zzz,请尽快处理！', b'1', '2021-05-19 16:59:21.895000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (76, '管理员已同意您申请的比赛：zzz', b'0', '2021-05-19 16:59:36.468000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (77, '老师1老师，用户名：445393889发出项目申请  项目名:新项目，所属比赛为：zzz请尽快处理！', b'0', '2021-05-19 17:01:54.634000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (78, '计算机与信息工程学院已同意您申请的项目：新项目', b'0', '2021-05-19 17:02:09.081000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (79, '学生1同学，用户名：159951159申请加入 项目:新项目所属比赛为：zzz', b'0', '2021-05-19 17:04:52.946000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (80, '老师1老师，用户名：445393889同意了你的申请你已加入项目:新项目，所属比赛为：zzz', b'1', '2021-05-19 17:05:02.990000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (81, '学生1同学，用户名：159951159申请加入 项目:白帽子一盟所属比赛为：第十四届全国大学生信息安全竞赛', b'0', '2021-05-19 17:12:44.485000', '9747ec05-17ff-47ba-9c9b-4a08a4ded27e', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (82, '老师1老师，用户名：445393889发出项目申请  项目名:xinxiangmu，所属比赛为：zzz请尽快处理！', b'0', '2021-05-19 17:36:53.414000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (83, '计算机与信息工程学院已同意您申请的项目：xinxiangmu', b'0', '2021-05-19 17:37:05.138000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (84, '老师1老师，用户名：445393889同意了你的申请你已加入项目:123，所属比赛为：人工智能', b'1', '2021-05-19 17:37:33.574000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (85, '计算机与信息工程学院正在申请比赛：520竞赛,请尽快处理！', b'1', '2021-05-20 16:31:06.577000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (86, '管理员已同意您申请的比赛：520竞赛', b'0', '2021-05-20 16:31:17.308000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (87, '老师1老师，用户名：445393889发出项目申请  项目名:520一队，所属比赛为：520竞赛请尽快处理！', b'0', '2021-05-20 16:31:44.462000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (88, '计算机与信息工程学院已同意您申请的项目：520一队', b'0', '2021-05-20 16:31:51.957000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (89, '计算机与信息工程学院正在申请比赛：新竞赛,请尽快处理！', b'1', '2021-05-21 11:54:21.531000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (90, '管理员已同意您申请的比赛：新竞赛', b'0', '2021-05-21 11:54:31.448000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (91, '老师1老师，用户名：445393889发出项目申请  项目名:新项目，所属比赛为：新竞赛请尽快处理！', b'0', '2021-05-21 11:55:06.027000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (92, '计算机与信息工程学院已同意您申请的项目：新项目', b'0', '2021-05-21 11:55:22.334000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (93, '学生1同学，用户名：159951159申请加入 项目:新项目所属比赛为：新竞赛', b'0', '2021-05-21 11:55:59.732000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (94, '老师1老师，用户名：445393889同意了你的申请你已加入项目:新项目，所属比赛为：新竞赛', b'1', '2021-05-21 11:56:10.553000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (95, '计算机与信息工程学院正在申请比赛：新竞赛,请尽快处理！', b'1', '2021-05-21 12:48:25.743000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (96, '管理员已同意您申请的比赛：新竞赛', b'0', '2021-05-21 12:48:43.577000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (97, '老师1老师，用户名：445393889发出项目申请  项目名:521521521，所属比赛为：新竞赛请尽快处理！', b'0', '2021-05-21 12:49:49.579000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (98, '计算机与信息工程学院已同意您申请的项目：521521521', b'0', '2021-05-21 12:50:06.053000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (99, '学生1同学，用户名：159951159申请加入 项目:521521521所属比赛为：新竞赛', b'0', '2021-05-21 12:50:53.871000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (100, '老师1老师，用户名：445393889同意了你的申请你已加入项目:521521521，所属比赛为：新竞赛', b'1', '2021-05-21 12:51:09.267000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (101, '计算机与信息工程学院正在申请比赛：这是一个新竞赛,请尽快处理！', b'1', '2021-05-21 13:00:00.504000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (102, '管理员已同意您申请的比赛：这是一个新竞赛', b'0', '2021-05-21 13:00:17.519000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (103, '老师1老师，用户名：445393889发出项目申请  项目名:这是一个新的项目，所属比赛为：这是一个新竞赛请尽快处理！', b'0', '2021-05-21 13:01:13.767000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (104, '计算机与信息工程学院已同意您申请的项目：这是一个新的项目', b'0', '2021-05-21 13:01:28.834000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (105, '学生1同学，用户名：159951159申请加入 项目:这是一个新的项目所属比赛为：这是一个新竞赛', b'0', '2021-05-21 13:02:02.614000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (106, '老师1老师，用户名：445393889同意了你的申请你已加入项目:这是一个新的项目，所属比赛为：这是一个新竞赛', b'1', '2021-05-21 13:02:10.843000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (107, '计算机与信息工程学院正在申请比赛：xingjingsai,请尽快处理！', b'1', '2021-05-21 22:22:30.441000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (108, '管理员已同意您申请的比赛：xingjingsai', b'0', '2021-05-21 22:22:37.965000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (109, '老师1老师，用户名：445393889发出项目申请  项目名:xinxiangmu，所属比赛为：xingjingsai请尽快处理！', b'0', '2021-05-21 22:23:02.498000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (110, '计算机与信息工程学院已同意您申请的项目：xinxiangmu', b'0', '2021-05-21 22:23:09.573000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (111, '计算机与信息工程学院正在申请比赛：比赛yi,请尽快处理！', b'1', '2021-05-21 23:56:02.592000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (112, '管理员已同意您申请的比赛：比赛yi', b'0', '2021-05-21 23:56:11.157000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (113, '老师1老师，用户名：445393889发出项目申请  项目名:项目一，所属比赛为：比赛yi请尽快处理！', b'0', '2021-05-21 23:58:05.132000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (114, '计算机与信息工程学院已同意您申请的项目：项目一', b'0', '2021-05-21 23:58:13.063000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (115, '计算机与信息工程学院正在申请比赛：全国大学生计算机设计大赛,请尽快处理！', b'0', '2021-05-22 16:21:07.174000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (116, '计算机与信息工程学院正在申请比赛：全国大学生计算机设计大赛,请尽快处理！', b'0', '2021-05-22 17:27:21.365000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (117, '管理员已同意您申请的比赛：全国大学生计算机设计大赛', b'0', '2021-05-22 17:27:29.200000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (118, '老师1老师，用户名：445393889发出项目申请  项目名:一队，所属比赛为：全国大学生计算机设计大赛请尽快处理！', b'0', '2021-05-22 17:27:57.849000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (119, '计算机与信息工程学院已同意您申请的项目：一队', b'0', '2021-05-22 17:28:12.624000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (120, '学生1同学，用户名：159951159申请加入 项目:一队所属比赛为：全国大学生计算机设计大赛', b'0', '2021-05-22 17:28:33.273000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (121, '老师1老师，用户名：445393889同意了你的申请你已加入项目:一队，所属比赛为：全国大学生计算机设计大赛', b'1', '2021-05-22 17:28:45.506000', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (122, '学生12同学，用户名：1912685673申请加入 项目:计信青年一队所属比赛为：第七届蓝桥杯', b'0', '2021-05-28 21:58:54.281000', '54323861-5ddf-4d25-956d-77399ebba4b0', 1, '859cec7c2e5d4b9e8271439931c7ac03', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (123, '计算机与信息工程学院正在申请比赛：360信息安全大赛,请尽快处理！', b'0', '2021-05-29 00:03:35.384000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (124, '计算机与信息工程学院正在申请比赛：360信息安全大赛,请尽快处理！', b'0', '2021-05-29 00:08:04.004000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (125, '管理员已同意您申请的比赛：360信息安全大赛', b'0', '2021-05-29 00:08:23.153000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (126, '老师1老师，用户名：445393889发出项目申请  项目名:红队，所属比赛为：360信息安全大赛请尽快处理！', b'0', '2021-05-29 00:09:10.885000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (127, '计算机与信息工程学院已同意您申请的项目：红队', b'0', '2021-05-29 00:09:30.074000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (128, '学生11同学，用户名：759084478申请加入 项目:红队所属比赛为：360信息安全大赛', b'0', '2021-05-29 00:10:10.415000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '87341b63684241faaf89305b969f3812', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (129, '老师1老师，用户名：445393889同意了你的申请你已加入项目:红队，所属比赛为：360信息安全大赛', b'1', '2021-05-29 00:10:23.859000', '87341b63684241faaf89305b969f3812', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (130, '学生1同学，用户名：159951159申请加入 项目:红队所属比赛为：360信息安全大赛', b'0', '2021-05-29 00:12:33.903000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (131, '计算机与信息工程学院正在申请比赛：全国大学生计算机设计大赛,请尽快处理！', b'0', '2021-05-29 00:22:52.792000', '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您有新的比赛申请', 0);
INSERT INTO `message` VALUES (132, '管理员已同意您申请的比赛：全国大学生计算机设计大赛', b'0', '2021-05-29 00:23:14.244000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '3f9a2ca2-ee80-43ea-b51e-3d461d55d7ad', 3, '您的比赛申请通过了！', 2);
INSERT INTO `message` VALUES (133, '老师1老师，用户名：445393889发出项目申请  项目名:高校学科竞赛管理系统，所属比赛为：全国大学生计算机设计大赛请尽快处理！', b'0', '2021-05-29 00:24:05.165000', 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你有新的项目申请', 0);
INSERT INTO `message` VALUES (134, '计算机与信息工程学院已同意您申请的项目：高校学科竞赛管理系统', b'0', '2021-05-29 00:24:21.444000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'c8adde47-3569-4a60-91db-ae1cc5270a92', 2, '您的项目申请通过了！', 2);
INSERT INTO `message` VALUES (135, '学生11同学，用户名：759084478申请加入 项目:高校学科竞赛管理系统所属比赛为：全国大学生计算机设计大赛', b'0', '2021-05-29 00:25:03.185000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '87341b63684241faaf89305b969f3812', 0, '你有新的成员想加入', 0);
INSERT INTO `message` VALUES (136, '老师1老师，用户名：445393889同意了你的申请你已加入项目:高校学科竞赛管理系统，所属比赛为：全国大学生计算机设计大赛', b'0', '2021-05-29 00:25:24.686000', '87341b63684241faaf89305b969f3812', 0, '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, '你的比赛申请已同意', 2);
INSERT INTO `message` VALUES (137, '学生1同学，用户名：159951159申请加入 项目:高校学科竞赛管理系统所属比赛为：全国大学生计算机设计大赛', b'0', '2021-05-29 00:27:12.655000', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', 1, 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', 0, '你有新的成员想加入', 0);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `admin_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKd816ad20ht41wty5l6f85w8mb`(`admin_id`) USING BTREE,
  CONSTRAINT `FKd816ad20ht41wty5l6f85w8mb` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '这个是编辑后的公告', 100, '2021-05-08 04:07:23', '大批竞赛来袭！', '竞赛信息', 1);
INSERT INTO `notice` VALUES (2, '各参赛院校和团队：为了参赛团队能够更加准确的理解企业的赛题需求，经组委会与命题企业的积极沟通，针对学生团队关注较多的命题，特别开设企业视频直播答疑活动。', 80, '2021-04-28 01:42:45', '关于公布全国建筑业财税知识竞赛决赛规则的通知', '系统公告', 1);
INSERT INTO `notice` VALUES (3, '备赛不中断服务永在线——服创大赛组委会致参赛师生的一封信亲爱的老师、同学们：新型冠状病毒爆发以来，全国人民以团结奋斗的精神、忘我牺牲的行动投身到抗击疫情的斗争中。', 80, '2021-04-28 01:43:03', '备赛不中断 服务永在线 ——服创大赛组委会致参赛师生的一封信', '系统公告', 1);
INSERT INTO `notice` VALUES (4, '一、企业数据1、A01+A02-八维通-赛题所需数据接口注：八维通专家：A01+A02两个题目都更侧重设计，接口文档更大的意义是让同学们了解能获取到的信息有哪些。这份文档里还没有实际接口（地址），后面demo开发阶段企业会补充提供，并附上简单例程。', 80, '2021-04-28 01:43:20', 'A类赛题数据及答疑集锦（不定时更新）', '竞赛信息', 1);
INSERT INTO `notice` VALUES (5, '第十届中国大学生服务外包创新创业大赛全国赛获奖名单公告第十届中国大学生服务外包创新创业大赛全国赛获奖团队信息公示', 80, '2021-04-28 01:46:20', '第十届中国大学生服务外包创新创业大赛全国赛获奖名单公告', '荣誉墙', 1);
INSERT INTO `notice` VALUES (6, '一等奖（2人）\r\n如山夫《渐渐清晰的自我存在》(组诗)\r\n薛小平《每一块矿石 都绽放出五彩缤纷的祖国》（组诗）\r\n二等奖（4人）\r\n孙凤山《魅力：马鞍山纪事或卷轴》（组诗）\r\n詹传亭《祖国啊，我热爱你厚重的山水》（组诗）\r\n王 喜《马鞍山略记》（组诗）\r\n仝志男《经过九号高炉》（外四首）\r\n三等奖（6人）\r\n王克金《论山水》（外四首）\r\n陈忠龙《七十年：这被重用的时光，在马鞍山升值》\r\n张 萍《秋天的风》（散文）\r\n胡立森《诗城走笔》（组诗）\r\n王学建《雪中》（外二首）\r\n蒋 文《马鞍山》（组诗）', 80, '2021-04-28 01:46:14', '诗歌散文大赛获奖公告', '荣誉墙', 1);
INSERT INTO `notice` VALUES (7, '由教育部科技发展中心主办，东南大学承办的第三届“全国高校云计算应用创新大赛”全国总决赛，于2017年4月23日在南京江北新区产业技术研创园落下帷幕。我系PASA大数据实验室由郭晨、黄圣彬、黄志、姜茜四位硕士研究生同学组成的“结果很好”队，由顾荣老师指导，在技能赛中经过预赛、决赛长达半年的角逐，从128支技能赛队伍中脱颖而出，获得第1名的优异成绩，荣获大赛一等奖！这是PASA大数据实验室继2015年和2016年蝉联两届“全国云计算应用创新大赛”技能赛冠军后，连续第三次荣获该项赛事全国冠军，实现了三连冠！  ', 70, '2021-04-28 01:47:01', 'PASA大数据实验室荣获2017年全国高校计算应用创新大赛大数据技能赛冠军', '荣誉墙', 1);
INSERT INTO `notice` VALUES (8, '日前，第二届鄂尔多斯国际文化创意大赛颁奖典礼在内蒙古鄂尔多斯市国宾馆隆重举行，工业设计学院交通工具设计专业张宇同学凭借设计作品《Insect-自走式玉米青贮饲料收割机》斩获“北斗新锐奖&middot;最具创新能力设计”奖，并获得10万元的新锐设计奖奖金。', 80, '2021-04-28 01:47:18', '工业设计学院学生荣获鄂尔多斯国际文化创意大赛“北斗新锐奖”', '荣誉墙', 1);
INSERT INTO `notice` VALUES (9, '本届大奖赛自2019年9月3日发布公告至10月31日截止，共收到619幅作品报名参赛，于2019年11月下旬召开了优秀作品评审会。评审专家组由上海各高校、上海知名工业设计公司和上海电气集团的专家们组成，评审专家组本着公平、公正的原则，对这些作品进行了评选，经过初审、初评、复评和终评共四轮评审，分别评出金、银、铜、优秀奖共46个奖项。同时，根据参赛单位的作品数量与作品获奖情况，评选出10家优秀组织奖单位。', 100, '2021-04-28 01:48:05', '第十七届“上海电气杯”产品设计大奖赛获奖结果公布', '荣誉墙', 1);
INSERT INTO `notice` VALUES (10, '一直以来，我都宣称自己是有梦想的人，在固执己见的岁月流淌中，年岁渐长，大成就没有，小惊喜不断。到今天，我仍然觉得自己是个有梦想的人。也很少能说清楚自己的梦想是什么，只觉得我的人生不应该满足于现状，可以更好。这是做梦的情怀，虽然听起来有些幼稚的可笑！', 90, '2021-04-28 01:48:35', '有梦想的人，请打开心灵de门', '竞赛信息', 1);
INSERT INTO `notice` VALUES (11, '今年5月12日是我国第十个防灾减灾日，主题是“行动起来，减轻身边的灾害风险”。下面是由美文阅读网小编带来的“2019‘防灾减灾日’宣传活动总结”，欢迎阅读。\r\n\r\n　　2019“防灾减灾日”宣传活动总结\r\n\r\n　　5月7日至13日为防灾减灾宣传周。为做好防灾减灾宣传教育活动，进一步提高人民群众的防灾减灾能力水平，xx县多举措积极部署“防灾减灾日”宣传活动。', 80, '2021-04-28 01:48:54', '2019“防灾减灾日”宣传活动总结', '竞赛信息', 1);
INSERT INTO `notice` VALUES (12, '近几天总能听到回国人员的言行，令在国内封闭了两个月的人民群众动怒的新闻。你看看，我们从欧洲回来，就这种待遇?待遇不好回国干嘛，还要给你带游泳池的别墅吗?我同学这么说的。看到被挤爆的机场航站楼，很多人都表现出了不高兴，给谁纳税找谁隔离治疗去。说的有点偏激，再怎么说也是同胞，想疫情初期，旅居国外的华人把当地的口罩都买空了运回国内。同根同源，再怎么说也应该心连心，谁不把生命看得高于一切呢?', 80, '2021-04-28 01:49:17', '是人权重要还是人命重要', '荣誉墙', 1);
INSERT INTO `notice` VALUES (13, '3月17日上午，学校新冠肺炎疫情防控工作领导小组在901会议室召开专题会议（第十六次），研究审议学校疫情防控工作相关事项，学校疫情防控工作领导小组全体成员及各工作专班负责同志参加。会议由党委书记尚宝平主持。', 100, '2021-04-28 01:50:03', '学校召开新冠肺炎疫情防控工作领导小组工作会议（第十六次）', '系统公告', 1);
INSERT INTO `notice` VALUES (14, '各单位：\r\n\r\n根据《河南省人力资源和社会保障厅关于做好2020年享受国务院特殊津贴人员推荐工作的通知》（豫人社办函〔2020〕424号）文件精神和新乡市人社局的有关安排，我校开展2020年享受国务院特殊津贴人员推荐工作，请各单位高度重视，广泛宣传，积极组织相关人员认真学习文件精神，对照条件进行申报，具体要求见附件，请符合条件人员将所有申报材料统一整理成一个PDF文档，以“姓名+国贴专家”命名，电子版提交邮箱xxxyrsc@126.com，纸质版暂不提交。', 100, '2021-04-28 01:50:22', '关于做好2020年享受国务院特殊津贴人员推荐工作的通知', '系统公告', 1);
INSERT INTO `notice` VALUES (15, '根据新型冠状病毒肺炎疫情的严峻形势和教育部延迟开学的有关通知，同时为严格执行学校关于疫情防控的文件精神，决定将2020级成教新生报到的时间推迟至3月1日之后。具体报到时间请2020级新生关注学校网站和新乡学院继续教育学院微信公众号通知。', 100, '2021-04-28 01:50:36', '关于2020级成教新生延迟报到的通知', '系统公告', 1);
INSERT INTO `notice` VALUES (17, '今日起（2021年5月4日），我校新乡学院就东区餐厅食堂占座，排队拥挤，餐具乱放等不文明行为进行大力整顿，整顿内容如下：\r\n   1，学校管理员发现后上报政教处，进行处罚，更严厉者进行违规罚款\r\n   2.学生检举经查证属实，进行对应处罚', 85, '2021-05-08 03:25:26', '校园东区餐厅整顿通知', '系统公告', 1);
INSERT INTO `notice` VALUES (35, 'xingg', 100, '2021-05-22 16:19:10', 'xingg', '系统公告', 1);
INSERT INTO `notice` VALUES (36, '新公告', 100, '2021-05-22 17:26:18', 'xingonggao', '竞赛信息', 1);
INSERT INTO `notice` VALUES (37, '信息公告新色', 100, '2021-05-29 00:01:38', '信息更改显示', '系统公告', 1);
INSERT INTO `notice` VALUES (38, '时间0.06', 100, '2021-05-29 00:06:43', '新公告', '系统公告', 1);
INSERT INTO `notice` VALUES (39, '全国大学生计算机设计大赛', 100, '2021-05-29 00:21:42', '新竞赛来了', '竞赛信息', 1);

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '竞赛Id',
  `apply_state` int(11) NULL DEFAULT NULL COMMENT '审核状态（0:未审核;1:审核通过;2:审核不通过）',
  `count_student` int(11) NULL DEFAULT NULL COMMENT '学生人数',
  `count_teacher` int(11) NULL DEFAULT NULL COMMENT '老师人数',
  `pay` int(11) NULL DEFAULT NULL COMMENT '团队预期耗资',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `comp_id` int(11) NULL DEFAULT NULL COMMENT '所属竞赛',
  `create_by_teacher_id` int(11) NULL DEFAULT NULL COMMENT '申请老师(带队老师)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK8mhj4eusaf066fpu912vg9xok`(`comp_id`) USING BTREE,
  INDEX `FKkp8ldeiu9luu6xerj1i910t9p`(`create_by_teacher_id`) USING BTREE,
  CONSTRAINT `FK8mhj4eusaf066fpu912vg9xok` FOREIGN KEY (`comp_id`) REFERENCES `comp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkp8ldeiu9luu6xerj1i910t9p` FOREIGN KEY (`create_by_teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (7, 1, 2, 1, 1111, '新院之心', 2, 1);
INSERT INTO `project` VALUES (8, 1, 1, 1, 101, '计信青年一队', 2, 3);
INSERT INTO `project` VALUES (9, 1, 3, 1, 10101, '计信青年二队', 3, 2);
INSERT INTO `project` VALUES (10, 1, 0, 1, 10101, '来吧，少年', 5, 20);
INSERT INTO `project` VALUES (11, 1, 1, 1, 200, '最强大脑', 5, 19);
INSERT INTO `project` VALUES (12, 1, 0, 1, 159, '乘风破浪', 2, 15);
INSERT INTO `project` VALUES (13, 1, 0, 1, 500, '小强当自强', 8, 16);
INSERT INTO `project` VALUES (14, 1, 0, 1, 500, '黑马程序员联盟', 3, 11);
INSERT INTO `project` VALUES (15, 1, 0, 1, 500, '青春的声音', 7, 1);
INSERT INTO `project` VALUES (16, 1, 0, 1, 200, '蓝桥杯新院分杯', 4, 4);
INSERT INTO `project` VALUES (17, 1, 0, 1, 200, '计算机竞赛小分队', 3, 7);
INSERT INTO `project` VALUES (18, 1, 0, 1, 200, '感恩之声', 5, 24);
INSERT INTO `project` VALUES (19, 0, 0, 0, 200, '蓝桥杯竞赛', 2, 7);
INSERT INTO `project` VALUES (20, 0, 0, 0, 100, 'acm', 3, 7);
INSERT INTO `project` VALUES (21, 1, 1, 1, 500, '挑战自我', 2, 4);
INSERT INTO `project` VALUES (22, 1, 3, 1, 500, '眉飞色舞小队', 8, 17);
INSERT INTO `project` VALUES (23, 1, 0, 1, 500, '梦想啊啊啊', 3, 18);
INSERT INTO `project` VALUES (24, 1, 1, 1, 200, '黑暗骑士多', 7, 14);
INSERT INTO `project` VALUES (25, 1, 0, 1, 10101, '舞龙天下', 2, 21);
INSERT INTO `project` VALUES (26, 1, 1, 1, 200, '自由变成哈哈哈哈', 8, 22);
INSERT INTO `project` VALUES (27, 1, 3, 1, 159, '接受设计', 3, 23);
INSERT INTO `project` VALUES (28, 1, 0, 1, 10101, '歌唱祖国啊', 2, 24);
INSERT INTO `project` VALUES (29, 1, 1, 1, 10101, '歌唱啾啾啾', 8, 10);
INSERT INTO `project` VALUES (30, 1, 1, 1, 200, '哈哈哈哈测试', 3, 9);
INSERT INTO `project` VALUES (31, 1, 3, 1, 200, '测试使用法院', 7, 8);
INSERT INTO `project` VALUES (32, 1, 0, 1, 100, '感恩社会', 4, 2);
INSERT INTO `project` VALUES (33, 1, 1, 1, 500, '申诉沙发和赛', 3, 3);
INSERT INTO `project` VALUES (34, 1, 0, 1, 200, '机电机电我最强', 2, 1);
INSERT INTO `project` VALUES (35, 1, 0, 1, 200, '青春有我', 8, 1);
INSERT INTO `project` VALUES (36, 1, 0, 1, 200, '歌唱祖国啊', 2, 24);
INSERT INTO `project` VALUES (37, 1, 1, 1, 200, '歌唱啾啾啾', 8, 10);
INSERT INTO `project` VALUES (38, 1, 1, 1, 200, '哈哈哈哈测试', 3, 9);
INSERT INTO `project` VALUES (39, 1, 2, 1, 1111, '新院之心二队', 2, 1);
INSERT INTO `project` VALUES (40, 1, 0, 1, 500, '黑马程序员联盟二队', 3, 2);
INSERT INTO `project` VALUES (41, 1, 0, 1, 500, '黑马程序员联盟三队', 3, 2);
INSERT INTO `project` VALUES (42, 1, 0, 1, 500, '黑马程序员联盟四队', 3, 2);
INSERT INTO `project` VALUES (43, 1, 0, 1, 500, '黑马程序员联盟五队', 3, 2);
INSERT INTO `project` VALUES (44, 1, 0, 1, 500, '黑马程序员联盟六队', 3, 2);
INSERT INTO `project` VALUES (45, 1, 0, 1, 500, '黑马程序员联盟七队', 3, 2);
INSERT INTO `project` VALUES (46, 1, 0, 1, 200, '勇者无惧', 3, 2);
INSERT INTO `project` VALUES (47, 1, 0, 1, 200, '智者无敌', 3, 1);
INSERT INTO `project` VALUES (48, 1, 1, 1, 200, '白帽子一盟', 52, 4);
INSERT INTO `project` VALUES (49, 1, 1, 1, 22, '大学生', 53, 7);
INSERT INTO `project` VALUES (50, 1, 2, 1, 12, '123', 55, 4);
INSERT INTO `project` VALUES (51, 1, 1, 1, 5100, '新项目', 56, 5);
INSERT INTO `project` VALUES (52, 1, 0, 1, 500, 'xinxiangmu', 56, 3);
INSERT INTO `project` VALUES (53, 1, 0, 1, 520, '520一队', 57, 2);
INSERT INTO `project` VALUES (54, 1, 1, 1, 500, '新项目', 58, 8);
INSERT INTO `project` VALUES (55, 1, 1, 1, 200, '521521521', 59, 8);
INSERT INTO `project` VALUES (56, 1, 1, 1, 500, '这是一个新的项目', 60, 4);
INSERT INTO `project` VALUES (57, 1, 0, 1, 100, 'xinxiangmu', 61, 1);
INSERT INTO `project` VALUES (58, 1, 0, 1, 500, '项目一', 62, 1);
INSERT INTO `project` VALUES (59, 1, 1, 1, 500, '一队', 63, 1);
INSERT INTO `project` VALUES (60, 1, 1, 1, 500, '红队', 77, 1);
INSERT INTO `project` VALUES (61, 1, 1, 1, 500, '高校学科竞赛管理系统', 78, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `target_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `college_id` int(11) NULL DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gender` int(11) NULL DEFAULT NULL,
  `session_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `skey` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `wx_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `comp_count` int(11) NULL DEFAULT 0,
  `score` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKlq9gp46xirqgtyslqs0wkowu5`(`college_id`) USING BTREE,
  CONSTRAINT `FKlq9gp46xirqgtyslqs0wkowu5` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '学生1', '101', 'be81d0c1-fc49-40f1-a910-1c6fa0db0da1', '159951159', 1, 'o8cVh5QX4mzMW7iR6Qdfyd1rVM0Q', 'https://thirdwx.qlogo.cn/mmopen/vi_32/5Xxan9To9y7rsvHwHuyRNkCLKQOLeDiaEkqRk9WTz9OcTVQUCxgqrqyNhWcgclkmu3jsRM6VV0ibJ7xoQ3mQOc3Q/132', 1, 'AHF12+r3VOVHNpmlpujCPA==', 'ea1bb963-f5ef-4714-87fa-f089f59cf8ee', '知了', 0, 140000);
INSERT INTO `student` VALUES (2, '学生2', '123', '53596879a20c47e497d5af0f10e4790c', '1295820856', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (3, '学生3', '123', '470d2b6b1dd04d45859e79194c00ad0e', '2141021750', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (4, '学生4', '123456', 'cd590cc660e14c96aed42a0be723c153', '612367633', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (5, '学生5', '123', 'db6092c07c1d4889930f59adff0d225c', '1921328235', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (6, '学生6', '123', '70c2759b03274ee584e64e93e81a160e', '370778752', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (7, '学生7', '123', '7179b28486e74af0aaf8b971f8c29bae', '421280866', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (8, '学生8', '123', 'd1b1226fba8e4ae2b0947e1acfe865ea', '637143404', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (9, '学生9', '123', 'f4a7a1a693944da485a3b03f906fa0e6', '821249590', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (10, '学生10', '123', '1a1bc16e96494393b98abc57f0b6370b', '955816895', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (11, '学生11', '123', '87341b63684241faaf89305b969f3812', '759084478', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (12, '学生12', '123', '859cec7c2e5d4b9e8271439931c7ac03', '1912685673', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (13, '学生13', '123', '4db872f52cff42aaac15cda98834fd70', '1016161475', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (14, '学生14', '123', 'ca609e728e4243f7af1950ec0dc17f01', '794625189', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (15, '学生15', '123', '9c89ceb042a441319f0e45985336cf7b', '104003050', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (16, '学生16', '123', '1b368ad4b18a4137bd596624d37482ee', '1983490804', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (17, '学生17', '123', '119e27b822b74dbea5e767409e03d796', '1954856967', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (18, '学生18', '123', '7965220303c44455ae2e40995f155296', '68210409', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (19, '学生19', '123', 'c4eccd979be141188437a254870bec44', '79155650', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (20, '学生20', '123', '9a78ab33cba3451988cb50f7d493b924', '1360183001', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (21, '学生21', '123', 'af11137f8b584670a276f5b7281ab1a0', '1510202796', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (22, '学生22', '123', 'b30189950e4c4cf680396cf01d1e8f31', '955829923', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (23, '学生23', '123', '7c0673fcbfbc40f880d342d9c42b2dfa', '535408073', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (24, '学生24', '123', 'fa428e0318834f28a0c1a82d20617578', '2103860490', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (25, '学生25', '123', '75d8aed5d88a4c69a89f814e30464d0d', '1852207031', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (26, '学生26', '123', 'a771400f101a47449b72161dcf027cd1', '1091733008', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (27, '学生27', '123', '6c888090d28945f9910f513c6116b91d', '948665470', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (28, '学生28', '123', 'b1c3ed1233ac4ff39080d91469f1bc6e', '899225507', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (29, '学生29', '123', 'c4b63825b245438a9f2c5f4dfa626eef', '1087710728', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (30, '学生30', '123', '71bba5006222442b9ce800ba9d0ccfb6', '1565195925', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (31, '学生31', '123', '1159ef3b21c64decacf44bfa3190d679', '1171935247', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (32, '学生32', '123', '9cfba05e446745a19b7e8787b33aa3d9', '322836870', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (33, '学生33', '123', 'b895a33bd3474ddba8b5aafcbc84e3ea', '33244943', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (34, '学生34', '123', '055cfdb73c54447787e5595399bc8e60', '1530763860', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (35, '学生35', '123', '6230965d245342a085eddfb01e1d9008', '1945759814', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (36, '学生36', '123', '055c2e6fb9534bdc902fa8c33e8219c2', '1078800122', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (37, '学生37', '123', '362083e7f4c044b8bb49caeb27a56aa5', '2024762803', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (38, '学生38', '123', 'cda29155593c4d62a0ddb483f08bb599', '715212636', 4, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (39, '学生39', '123', '090edd473706430f96e6624669e9a15b', '1261600130', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (40, '学生40', '123', 'bfb3ac5bb40e4cfeb0327f0f7b58d65d', '948145526', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (41, '学生41', '123', '5010949f1a1f45a184f0d219686e1e62', '1215102289', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (42, '学生42', '123', 'ee04c779dff847578add59b489e84798', '941900974', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (43, '学生43', '123', '2ee332db199440149b9d3300ae45fdb7', '2081537026', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (44, '学生44', '123', '4e36a2cdbbcf4d5798607449c1f8465e', '1946628390', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (45, '学生45', '123', '1047fbeb24b14e8dba959c627214d469', '135370176', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (46, '学生46', '123', '30d109866beb48b686b244ac1445de55', '267938099', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (47, '学生47', '123', 'ebde0bbf78244689aac974e796e8adab', '1620621735', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (48, '学生48', '123', '3f5841be18c04e6095f9d6682c762da8', '1825853523', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (49, '学生49', '123', 'b591d56c06194deb9043c6735d312465', '749371825', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (50, '学生50', '123', 'f7da26afc6684944a1edb73e86808133', '1364945632', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (51, '学生51', '123', '6cc098128970404eaa24dac011ac10be', '536485955', 5, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (52, '学生52', '123', '4087df3d5c72460781189a6f002de7e5', '294645039', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (53, '学生53', '123', 'c6e203d6a6af45f39d9d086d20ce50a0', '9379473', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (54, '学生54', '123', '72ee3a7b5c364dabb07b607b9437a1c1', '1432374272', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (55, '学生55', '123', '39a5dbf2a66a4855bd9070d186c3a67f', '2013995960', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (56, '学生56', '123', 'fa1c5f42fb4f4ce5991de5c5897f5190', '1777919372', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (57, '学生57', '123', '5a8025e5f2d240f8ada800f534de6717', '958659075', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (58, '学生58', '123', '09e88836342745ae9b0a8e84a969992e', '152600634', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (59, '学生59', '123', 'ad7559c6041845f98742fc6aecb53187', '1689352658', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (60, '学生60', '123', '75056a9902ab4467b8c33a9b2112b24b', '949598996', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (61, '学生61', '123', '22be535611464ff493157fb207d939a6', '251537778', 6, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (62, '学生62', '123', 'b1c734db9fd9414ebf73e5b9221f7929', '860333207', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (63, '学生63', '123', 'ee94d28c95694c868297a3464b5178f4', '1662196677', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (64, '学生64', '123', 'd51a06e21e7f4cfbb390604adaa48122', '658904665', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (65, '学生65', '123', '06d86a6f02d7490fa4a43ba9438e0300', '859372671', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (66, '学生66', '123', 'c6c1c83dff28430490ee20cbfa935f0a', '673518828', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (67, '学生67', '123', '0367fd2437154f44a2d1f84f505a356c', '650715059', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (68, '学生68', '123', '59780933fad54a389493b3d4a424c609', '1905183530', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (69, '学生69', '123', 'b24ab3e9d3f443cba17a1488df0451c8', '1085278093', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (70, '学生70', '123', 'fd7a3f4a357d46e3804526f4b2382b6f', '1862650594', 7, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (71, '学生71', '123', '337881daa0e5495fb1652a51c43ae42e', '1392029062', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `student` VALUES (72, '学生72', '123', '5b34d77df11b481faa2702c42d43f211', '10786702', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for student_project
-- ----------------------------
DROP TABLE IF EXISTS `student_project`;
CREATE TABLE `student_project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关系记录id',
  `apply_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
  `apply_or_join` int(11) NULL DEFAULT NULL COMMENT '学生和项目的状态  ',
  `update_apply_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `project_id` int(11) NULL DEFAULT NULL COMMENT '项目id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKlmwp7mytxvlna99s526lfvgcp`(`project_id`) USING BTREE,
  INDEX `FKl37sau7p5bk28p0v29vhoja4e`(`student_id`) USING BTREE,
  CONSTRAINT `FKl37sau7p5bk28p0v29vhoja4e` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKlmwp7mytxvlna99s526lfvgcp` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 251 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_project
-- ----------------------------
INSERT INTO `student_project` VALUES (43, '2021-01-12 12:08:33', 1, '2021-05-05 19:39:23', 9, 7);
INSERT INTO `student_project` VALUES (44, '2020-10-01 12:08:34', 3, '2021-05-10 17:49:02', 7, 19);
INSERT INTO `student_project` VALUES (45, '2021-05-12 12:08:36', 3, '2021-05-10 17:49:10', 18, 7);
INSERT INTO `student_project` VALUES (46, '2021-03-12 12:08:37', 3, '2021-05-08 04:18:32', 14, 11);
INSERT INTO `student_project` VALUES (47, '2021-03-20 12:08:39', 3, '2021-05-08 04:18:35', 25, 14);
INSERT INTO `student_project` VALUES (48, '2021-04-24 12:08:39', 3, '2021-05-10 17:49:13', 7, 25);
INSERT INTO `student_project` VALUES (49, '2021-03-19 12:08:40', 2, '2021-05-10 17:49:36', 11, 32);
INSERT INTO `student_project` VALUES (50, '2021-02-18 12:08:40', 1, '2021-05-10 17:48:00', 17, 18);
INSERT INTO `student_project` VALUES (51, '2021-05-12 12:08:36', 3, '2021-05-08 04:18:32', 14, 18);
INSERT INTO `student_project` VALUES (52, '2019-09-29 01:58:36', 3, '2021-05-08 04:18:35', 7, 9);
INSERT INTO `student_project` VALUES (53, '2020-06-29 01:58:43', 3, '2021-05-10 17:49:13', 16, 1);
INSERT INTO `student_project` VALUES (54, '2020-03-07 10:38:42', 2, '2021-05-10 17:49:36', 7, 48);
INSERT INTO `student_project` VALUES (55, '2020-07-07 13:40:54', 1, '2021-05-10 17:48:00', 18, 18);
INSERT INTO `student_project` VALUES (56, '2020-07-08 03:51:55', 3, '2021-05-08 04:18:32', 14, 5);
INSERT INTO `student_project` VALUES (57, '2020-07-08 03:51:57', 3, '2021-05-08 04:18:35', 25, 25);
INSERT INTO `student_project` VALUES (58, '2020-10-12 12:08:32', 3, '2021-05-10 17:49:13', 32, 15);
INSERT INTO `student_project` VALUES (59, '2021-01-12 12:08:33', 2, '2021-05-10 17:49:36', 11, 6);
INSERT INTO `student_project` VALUES (60, '2020-10-01 12:08:34', 1, '2021-05-10 17:48:00', 17, 14);
INSERT INTO `student_project` VALUES (61, '2021-05-12 12:08:36', 3, '2021-05-08 04:18:32', 14, 59);
INSERT INTO `student_project` VALUES (62, '2021-03-12 12:08:37', 3, '2021-05-08 04:18:35', 15, 7);
INSERT INTO `student_project` VALUES (63, '2021-03-20 12:08:39', 3, '2021-05-10 17:49:13', 7, 48);
INSERT INTO `student_project` VALUES (64, '2020-07-10 17:39:52', 2, '2021-05-10 17:49:36', 11, 12);
INSERT INTO `student_project` VALUES (65, '2020-07-10 17:39:56', 1, '2021-05-10 17:48:00', 17, 70);
INSERT INTO `student_project` VALUES (66, '2020-10-12 12:08:32', 3, '2021-05-08 04:18:32', 14, 11);
INSERT INTO `student_project` VALUES (67, '2021-01-12 12:08:33', 3, '2021-05-08 04:18:35', 7, 18);
INSERT INTO `student_project` VALUES (68, '2020-10-01 12:08:34', 3, '2021-05-10 17:49:13', 9, 10);
INSERT INTO `student_project` VALUES (69, '2021-05-12 12:08:36', 2, '2021-05-10 17:49:36', 7, 25);
INSERT INTO `student_project` VALUES (70, '2021-03-12 12:08:37', 1, '2021-05-10 17:48:00', 18, 32);
INSERT INTO `student_project` VALUES (71, '2021-03-20 12:08:39', 3, '2021-05-08 04:18:32', 14, 17);
INSERT INTO `student_project` VALUES (72, '2021-04-24 12:08:39', 3, '2021-05-08 04:18:35', 15, 15);
INSERT INTO `student_project` VALUES (73, '2020-12-23 12:08:40', 3, '2021-05-10 17:49:13', 7, 59);
INSERT INTO `student_project` VALUES (74, '2020-07-10 17:40:35', 2, '2021-05-10 17:49:36', 11, 11);
INSERT INTO `student_project` VALUES (75, '2020-07-10 17:40:59', 1, '2021-05-10 17:48:00', 17, 14);
INSERT INTO `student_project` VALUES (76, '2020-10-12 12:08:32', 3, '2021-05-08 04:18:32', 14, 59);
INSERT INTO `student_project` VALUES (77, '2021-01-12 12:08:33', 3, '2021-05-08 04:18:35', 15, 32);
INSERT INTO `student_project` VALUES (78, '2020-10-01 12:08:34', 3, '2021-05-10 17:49:13', 7, 17);
INSERT INTO `student_project` VALUES (79, '2021-05-12 12:08:36', 2, '2021-05-10 17:49:36', 7, 48);
INSERT INTO `student_project` VALUES (80, '2021-03-12 12:08:37', 1, '2021-05-10 17:48:00', 9, 18);
INSERT INTO `student_project` VALUES (81, '2021-03-20 12:08:39', 3, '2021-05-08 04:18:32', 7, 14);
INSERT INTO `student_project` VALUES (82, '2020-07-10 17:39:52', 3, '2021-05-08 04:18:35', 18, 25);
INSERT INTO `student_project` VALUES (83, '2020-07-10 17:39:56', 3, '2021-05-10 17:49:13', 14, 59);
INSERT INTO `student_project` VALUES (84, '2020-10-12 12:08:32', 2, '2021-05-10 17:49:36', 25, 17);
INSERT INTO `student_project` VALUES (85, '2020-10-12 12:08:32', 1, '2021-05-10 17:48:00', 32, 26);
INSERT INTO `student_project` VALUES (86, '2021-01-12 12:08:33', 3, '2021-05-08 04:18:32', 14, 59);
INSERT INTO `student_project` VALUES (87, '2020-10-01 12:08:34', 3, '2021-05-08 04:18:35', 15, 48);
INSERT INTO `student_project` VALUES (88, '2021-05-12 12:08:36', 3, '2021-05-10 17:49:13', 7, 9);
INSERT INTO `student_project` VALUES (89, '2021-03-12 12:08:37', 2, '2021-05-10 17:49:36', 11, 7);
INSERT INTO `student_project` VALUES (90, '2021-03-20 12:08:39', 1, '2021-05-10 17:48:00', 17, 15);
INSERT INTO `student_project` VALUES (91, '2021-04-24 12:08:39', 3, '2021-05-08 04:18:32', 14, 14);
INSERT INTO `student_project` VALUES (92, '2021-03-19 12:08:40', 3, '2021-05-08 04:18:35', 15, 44);
INSERT INTO `student_project` VALUES (93, '2021-02-18 12:08:40', 3, '2021-05-10 17:49:13', 7, 32);
INSERT INTO `student_project` VALUES (94, '2021-05-12 12:08:36', 2, '2021-05-10 17:49:36', 11, 17);
INSERT INTO `student_project` VALUES (95, '2021-03-12 12:08:37', 1, '2021-05-10 17:48:00', 17, 71);
INSERT INTO `student_project` VALUES (96, '2021-03-20 12:08:39', 3, '2021-05-08 04:18:32', 14, 7);
INSERT INTO `student_project` VALUES (97, '2021-04-24 12:08:39', 3, '2021-05-08 04:18:35', 15, 3);
INSERT INTO `student_project` VALUES (99, '2020-07-10 17:39:52', 2, '2021-05-10 17:49:36', 11, 59);
INSERT INTO `student_project` VALUES (100, '2020-07-10 17:39:56', 1, '2021-05-10 17:48:00', 17, 11);
INSERT INTO `student_project` VALUES (101, '2020-10-12 12:08:32', 1, '2021-05-10 17:48:00', 17, 11);
INSERT INTO `student_project` VALUES (102, '2020-10-12 12:08:32', 1, '2021-05-10 17:48:00', 17, 8);
INSERT INTO `student_project` VALUES (103, '2021-01-12 12:08:33', 3, '2021-05-08 04:18:32', 14, 9);
INSERT INTO `student_project` VALUES (104, '2020-10-01 12:08:34', 3, '2021-05-08 04:18:35', 15, 3);
INSERT INTO `student_project` VALUES (105, '2021-05-12 12:08:36', 3, '2021-05-10 17:49:13', 7, 69);
INSERT INTO `student_project` VALUES (106, '2021-03-12 12:08:37', 2, '2021-05-10 17:49:36', 16, 10);
INSERT INTO `student_project` VALUES (107, '2021-03-20 12:08:39', 1, '2021-05-10 17:48:00', 7, 9);
INSERT INTO `student_project` VALUES (108, '2021-04-24 12:08:39', 1, '2021-05-10 17:48:00', 9, 39);
INSERT INTO `student_project` VALUES (109, '2021-03-19 12:08:40', 3, '2021-05-08 04:18:32', 7, 4);
INSERT INTO `student_project` VALUES (110, '2021-02-18 12:08:40', 3, '2021-05-08 04:18:35', 18, 14);
INSERT INTO `student_project` VALUES (111, '2021-05-12 12:08:36', 3, '2021-05-10 17:49:13', 14, 59);
INSERT INTO `student_project` VALUES (125, '2021-03-12 12:08:37', 1, '2021-05-10 17:48:00', 27, 7);
INSERT INTO `student_project` VALUES (126, '2021-03-20 12:08:39', 1, '2021-05-10 17:48:00', 26, 10);
INSERT INTO `student_project` VALUES (127, '2021-04-24 12:08:39', 3, '2021-05-08 04:18:32', 28, 3);
INSERT INTO `student_project` VALUES (128, '2021-03-19 12:08:40', 3, '2021-05-08 04:18:35', 20, 36);
INSERT INTO `student_project` VALUES (129, '2021-03-12 12:08:37', 3, '2021-05-10 17:49:13', 7, 9);
INSERT INTO `student_project` VALUES (130, '2020-10-12 12:08:32', 2, '2021-05-10 17:49:36', 11, 35);
INSERT INTO `student_project` VALUES (131, '2021-01-12 12:08:33', 1, '2021-05-10 17:48:00', 17, 18);
INSERT INTO `student_project` VALUES (132, '2020-10-01 12:08:34', 1, '2021-05-10 17:48:00', 17, 14);
INSERT INTO `student_project` VALUES (133, '2021-05-12 12:08:36', 3, '2021-05-08 04:18:32', 14, 34);
INSERT INTO `student_project` VALUES (134, '2021-03-12 12:08:37', 3, '2021-05-08 04:18:35', 15, 32);
INSERT INTO `student_project` VALUES (135, '2021-03-20 12:08:39', 3, '2021-05-10 17:49:13', 7, 17);
INSERT INTO `student_project` VALUES (136, '2021-04-24 12:08:39', 2, '2021-05-10 17:49:36', 11, 39);
INSERT INTO `student_project` VALUES (137, '2020-08-10 17:41:42', 1, '2021-05-10 17:48:00', 17, 7);
INSERT INTO `student_project` VALUES (138, '2020-08-10 17:41:45', 1, '2021-05-10 17:48:00', 17, 11);
INSERT INTO `student_project` VALUES (139, '2020-08-07 13:40:54', 3, '2021-05-08 04:18:32', 14, 19);
INSERT INTO `student_project` VALUES (140, '2020-08-08 03:51:55', 3, '2021-05-08 04:18:35', 15, 3);
INSERT INTO `student_project` VALUES (141, '2020-08-08 03:51:57', 3, '2021-05-10 17:49:13', 7, 11);
INSERT INTO `student_project` VALUES (142, '2020-10-12 12:08:32', 2, '2021-05-10 17:49:36', 11, 40);
INSERT INTO `student_project` VALUES (143, '2021-01-12 12:08:33', 1, '2021-05-10 17:48:00', 17, 9);
INSERT INTO `student_project` VALUES (144, '2020-10-01 12:08:34', 1, '2021-05-10 17:48:00', 17, 7);
INSERT INTO `student_project` VALUES (145, '2021-05-12 12:08:36', 3, '2021-05-08 04:18:32', 14, 35);
INSERT INTO `student_project` VALUES (146, '2021-03-12 12:08:37', 3, '2021-05-08 04:18:35', 15, 14);
INSERT INTO `student_project` VALUES (147, '2021-04-24 12:08:39', 3, '2021-05-10 17:49:13', 7, 39);
INSERT INTO `student_project` VALUES (148, '2021-03-19 12:08:40', 2, '2021-05-10 17:49:36', 14, 32);
INSERT INTO `student_project` VALUES (149, '2021-02-18 12:08:40', 1, '2021-05-10 17:48:00', 25, 17);
INSERT INTO `student_project` VALUES (150, '2021-05-12 12:08:36', 1, '2021-05-10 17:48:00', 32, 38);
INSERT INTO `student_project` VALUES (151, '2021-03-12 12:08:37', 3, '2021-05-08 04:18:32', 17, 7);
INSERT INTO `student_project` VALUES (152, '2021-03-20 12:08:39', 3, '2021-05-08 04:18:35', 14, 43);
INSERT INTO `student_project` VALUES (154, '2021-03-19 12:08:40', 2, '2021-05-10 17:49:36', 9, 7);
INSERT INTO `student_project` VALUES (155, '2020-07-08 03:51:55', 1, '2021-05-10 17:48:00', 7, 9);
INSERT INTO `student_project` VALUES (156, '2020-07-08 03:51:57', 1, '2021-05-10 17:48:00', 18, 7);
INSERT INTO `student_project` VALUES (157, '2020-10-12 12:08:32', 3, '2021-05-08 04:18:32', 14, 49);
INSERT INTO `student_project` VALUES (158, '2021-01-12 12:08:33', 3, '2021-05-08 04:18:35', 25, 14);
INSERT INTO `student_project` VALUES (159, '2020-10-01 12:08:34', 3, '2021-05-10 17:49:13', 14, 25);
INSERT INTO `student_project` VALUES (160, '2021-05-12 12:08:36', 2, '2021-05-10 17:49:36', 14, 40);
INSERT INTO `student_project` VALUES (161, '2021-03-12 12:08:37', 1, '2021-05-10 17:48:00', 25, 17);
INSERT INTO `student_project` VALUES (162, '2021-03-20 12:08:39', 1, '2021-05-10 17:48:00', 32, 18);
INSERT INTO `student_project` VALUES (163, '2020-07-10 17:39:52', 3, '2021-05-08 04:18:32', 17, 52);
INSERT INTO `student_project` VALUES (164, '2020-07-10 17:39:56', 3, '2021-05-08 04:18:35', 14, 25);
INSERT INTO `student_project` VALUES (165, '2020-10-12 12:08:32', 3, '2021-05-10 17:49:13', 7, 50);
INSERT INTO `student_project` VALUES (166, '2021-01-12 12:08:33', 2, '2021-05-10 17:49:36', 9, 17);
INSERT INTO `student_project` VALUES (167, '2020-10-01 12:08:34', 1, '2021-05-10 17:48:00', 7, 14);
INSERT INTO `student_project` VALUES (168, '2021-05-12 12:08:36', 1, '2021-05-10 17:48:00', 14, 59);
INSERT INTO `student_project` VALUES (169, '2020-08-10 17:41:42', 3, '2021-05-08 04:18:32', 25, 68);
INSERT INTO `student_project` VALUES (170, '2020-08-10 17:41:45', 3, '2021-05-08 04:18:35', 32, 14);
INSERT INTO `student_project` VALUES (171, '2020-08-07 13:40:54', 3, '2021-05-10 17:49:13', 7, 65);
INSERT INTO `student_project` VALUES (172, '2020-08-08 03:51:55', 2, '2021-05-10 17:49:36', 11, 32);
INSERT INTO `student_project` VALUES (173, '2020-08-08 03:51:57', 1, '2021-05-10 17:48:00', 17, 64);
INSERT INTO `student_project` VALUES (174, '2020-10-12 12:08:32', 1, '2021-05-10 17:48:00', 17, 14);
INSERT INTO `student_project` VALUES (175, '2021-01-12 12:08:33', 3, '2021-05-08 04:18:32', 14, 60);
INSERT INTO `student_project` VALUES (176, '2020-10-01 12:08:34', 3, '2021-05-08 04:18:35', 15, 70);
INSERT INTO `student_project` VALUES (177, '2021-05-12 12:08:36', 3, '2021-05-10 17:49:13', 7, 71);
INSERT INTO `student_project` VALUES (178, '2020-09-10 17:41:45', 2, '2021-05-10 17:49:36', 11, 49);
INSERT INTO `student_project` VALUES (179, '2020-08-10 17:41:42', 1, '2021-05-10 17:48:00', 17, 11);
INSERT INTO `student_project` VALUES (180, '2020-08-10 17:41:45', 1, '2021-05-10 17:48:00', 17, 19);
INSERT INTO `student_project` VALUES (181, '2020-08-07 13:40:54', 3, '2021-05-08 04:18:32', 14, 15);
INSERT INTO `student_project` VALUES (182, '2020-08-08 03:51:55', 3, '2021-05-08 04:18:35', 7, 45);
INSERT INTO `student_project` VALUES (183, '2020-08-08 03:51:57', 3, '2021-05-10 17:49:13', 9, 11);
INSERT INTO `student_project` VALUES (184, '2020-10-12 12:08:32', 2, '2021-05-10 17:49:36', 7, 18);
INSERT INTO `student_project` VALUES (185, '2021-01-12 12:08:33', 1, '2021-05-10 17:48:00', 18, 14);
INSERT INTO `student_project` VALUES (186, '2020-10-01 12:08:34', 1, '2021-05-10 17:48:00', 14, 25);
INSERT INTO `student_project` VALUES (187, '2021-05-12 12:08:36', 3, '2021-05-08 04:18:32', 25, 32);
INSERT INTO `student_project` VALUES (188, '2020-07-10 17:41:41', 3, '2021-05-08 04:18:35', 32, 17);
INSERT INTO `student_project` VALUES (189, '2020-07-10 17:41:42', 3, '2021-05-10 17:49:13', 7, 14);
INSERT INTO `student_project` VALUES (190, '2020-09-10 17:41:45', 2, '2021-05-10 17:49:36', 9, 59);
INSERT INTO `student_project` VALUES (191, '2020-09-10 17:41:48', 1, '2021-05-10 17:48:00', 7, 52);
INSERT INTO `student_project` VALUES (192, '2020-09-10 17:41:49', 1, '2021-05-10 17:48:00', 9, 48);
INSERT INTO `student_project` VALUES (193, '2020-11-10 17:41:50', 3, '2021-05-08 04:18:32', 7, 59);
INSERT INTO `student_project` VALUES (194, '2021-05-12 12:08:31', 3, '2021-05-08 04:18:35', 18, 52);
INSERT INTO `student_project` VALUES (195, '2021-05-12 12:08:32', 3, '2021-05-10 17:49:13', 14, 18);
INSERT INTO `student_project` VALUES (196, '2021-05-12 12:08:33', 2, '2021-05-10 17:49:36', 25, 14);
INSERT INTO `student_project` VALUES (197, '2021-05-12 12:08:34', 1, '2021-05-10 17:48:00', 32, 25);
INSERT INTO `student_project` VALUES (198, '2020-07-10 17:41:38', 1, '2021-05-10 17:48:00', 7, 32);
INSERT INTO `student_project` VALUES (199, '2020-07-10 17:41:41', 3, '2021-05-08 04:18:32', 18, 17);
INSERT INTO `student_project` VALUES (200, '2020-08-10 17:41:42', 3, '2021-05-08 04:18:35', 14, 14);
INSERT INTO `student_project` VALUES (201, '2020-08-10 17:41:45', 3, '2021-05-10 17:49:13', 25, 59);
INSERT INTO `student_project` VALUES (202, '2020-08-07 13:40:54', 2, '2021-05-10 17:49:36', 32, 52);
INSERT INTO `student_project` VALUES (203, '2020-08-08 03:51:55', 1, '2021-05-10 17:48:00', 9, 48);
INSERT INTO `student_project` VALUES (204, '2020-08-08 03:51:57', 1, '2021-05-10 17:48:00', 7, 49);
INSERT INTO `student_project` VALUES (205, '2020-10-12 12:08:32', 3, '2021-05-08 04:18:32', 18, 18);
INSERT INTO `student_project` VALUES (206, '2021-01-12 12:08:33', 3, '2021-05-08 04:18:35', 14, 14);
INSERT INTO `student_project` VALUES (207, '2020-10-01 12:08:34', 3, '2021-05-10 17:49:13', 25, 25);
INSERT INTO `student_project` VALUES (208, '2021-05-12 12:08:36', 2, '2021-05-10 17:49:36', 32, 32);
INSERT INTO `student_project` VALUES (209, '2021-03-12 12:08:37', 1, '2021-05-10 17:48:00', 7, 17);
INSERT INTO `student_project` VALUES (210, '2021-03-20 12:08:39', 1, '2021-05-10 17:48:00', 9, 14);
INSERT INTO `student_project` VALUES (211, '2020-09-10 17:41:48', 3, '2021-05-08 04:18:32', 7, 59);
INSERT INTO `student_project` VALUES (212, '2020-09-10 17:41:49', 3, '2021-05-08 04:18:35', 18, 52);
INSERT INTO `student_project` VALUES (213, '2020-11-10 17:41:50', 3, '2021-05-10 17:49:13', 14, 48);
INSERT INTO `student_project` VALUES (214, '2020-11-05 12:08:31', 2, '2021-05-10 17:49:36', 25, 49);
INSERT INTO `student_project` VALUES (215, '2020-10-12 12:08:32', 1, '2021-05-10 17:48:00', 32, 11);
INSERT INTO `student_project` VALUES (216, '2019-09-29 01:58:36', 3, '2021-05-08 04:18:35', 7, 9);
INSERT INTO `student_project` VALUES (217, '2020-06-29 01:58:43', 3, '2021-05-10 17:49:13', 9, 16);
INSERT INTO `student_project` VALUES (218, '2020-03-07 10:38:42', 2, '2021-05-10 17:49:36', 7, 48);
INSERT INTO `student_project` VALUES (219, '2020-07-07 13:40:54', 1, '2021-05-10 17:48:00', 18, 18);
INSERT INTO `student_project` VALUES (220, '2020-07-08 03:51:55', 3, '2021-05-08 04:18:32', 14, 5);
INSERT INTO `student_project` VALUES (221, '2020-07-08 03:51:57', 3, '2021-05-08 04:18:35', 25, 25);
INSERT INTO `student_project` VALUES (222, '2020-10-12 12:08:32', 3, '2021-05-10 17:49:13', 32, 15);
INSERT INTO `student_project` VALUES (223, '2021-01-12 12:08:33', 2, '2021-05-10 17:49:36', 11, 6);
INSERT INTO `student_project` VALUES (224, '2020-10-01 12:08:34', 1, '2021-05-10 17:48:00', 17, 14);
INSERT INTO `student_project` VALUES (225, '2021-05-12 12:08:36', 3, '2021-05-08 04:18:32', 14, 59);
INSERT INTO `student_project` VALUES (226, '2021-03-12 12:08:37', 3, '2021-05-08 04:18:35', 15, 7);
INSERT INTO `student_project` VALUES (227, '2021-03-20 12:08:39', 3, '2021-05-10 17:49:13', 7, 48);
INSERT INTO `student_project` VALUES (228, '2020-07-10 17:39:52', 2, '2021-05-10 17:49:36', 11, 12);
INSERT INTO `student_project` VALUES (229, '2020-07-10 17:39:56', 1, '2021-05-10 17:48:00', 17, 70);
INSERT INTO `student_project` VALUES (230, '2020-10-12 12:08:32', 3, '2021-05-08 04:18:32', 14, 11);
INSERT INTO `student_project` VALUES (231, '2021-01-12 12:08:33', 3, '2021-05-08 04:18:35', 7, 18);
INSERT INTO `student_project` VALUES (232, '2020-10-01 12:08:34', 3, '2021-05-10 17:49:13', 9, 10);
INSERT INTO `student_project` VALUES (233, '2021-05-12 12:08:36', 2, '2021-05-10 17:49:36', 7, 25);
INSERT INTO `student_project` VALUES (234, '2021-03-12 12:08:37', 1, '2021-05-10 17:48:00', 18, 32);
INSERT INTO `student_project` VALUES (235, '2021-03-20 12:08:39', 3, '2021-05-08 04:18:32', 14, 17);
INSERT INTO `student_project` VALUES (236, '2021-04-24 12:08:39', 3, '2021-05-08 04:18:35', 15, 15);
INSERT INTO `student_project` VALUES (237, '2020-12-23 12:08:40', 3, '2021-05-10 17:49:13', 7, 59);
INSERT INTO `student_project` VALUES (238, '2020-07-10 17:40:35', 2, '2021-05-10 17:49:36', 11, 11);
INSERT INTO `student_project` VALUES (239, '2020-07-10 17:40:59', 1, '2021-05-10 17:48:00', 17, 14);
INSERT INTO `student_project` VALUES (240, '2020-10-12 12:08:32', 3, '2021-05-08 04:18:32', 14, 59);
INSERT INTO `student_project` VALUES (241, '2021-05-05 19:28:27', 3, '2021-05-05 20:09:55', 8, 1);
INSERT INTO `student_project` VALUES (242, '2021-05-05 19:39:23', 1, '2021-05-05 19:39:23', 10, 1);
INSERT INTO `student_project` VALUES (243, '2021-04-08 04:17:43', 3, '2021-05-10 17:49:02', 7, 3);
INSERT INTO `student_project` VALUES (244, '2021-01-08 04:18:00', 3, '2021-05-10 17:49:10', 11, 3);
INSERT INTO `student_project` VALUES (245, '2021-05-22 17:28:33', 3, '2021-05-22 17:28:46', 59, 1);
INSERT INTO `student_project` VALUES (246, '2021-05-28 21:58:54', 1, '2021-05-28 21:58:54', 8, 12);
INSERT INTO `student_project` VALUES (247, '2021-05-29 00:10:10', 3, '2021-05-29 00:10:24', 60, 11);
INSERT INTO `student_project` VALUES (248, '2021-05-29 00:12:34', 1, '2021-05-29 00:12:34', 60, 1);
INSERT INTO `student_project` VALUES (249, '2021-05-29 00:25:03', 3, '2021-05-29 00:25:25', 61, 11);
INSERT INTO `student_project` VALUES (250, '2021-05-29 00:27:13', 1, '2021-05-29 00:27:13', 61, 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `target_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `college_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK763n3dvgpu6dl9swenb5pc1hh`(`college_id`) USING BTREE,
  CONSTRAINT `FK763n3dvgpu6dl9swenb5pc1hh` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '老师1', '101', '4f0b4d56-fe10-4985-b5ea-dda38872d0d7', '445393889', 1);
INSERT INTO `teacher` VALUES (2, '老师2', '123456', '13c325f2-1b21-44a5-8029-9d21dbeddd28', '356803480', 1);
INSERT INTO `teacher` VALUES (3, '老师3', '159951', '54323861-5ddf-4d25-956d-77399ebba4b0', '1564369508', 2);
INSERT INTO `teacher` VALUES (4, '老师4', '123456', '9747ec05-17ff-47ba-9c9b-4a08a4ded27e', '1900656401', 1);
INSERT INTO `teacher` VALUES (5, '老师5', '123', '61ce09dd-7239-458c-aa34-6eaf78c011f7', '1006940043', 6);
INSERT INTO `teacher` VALUES (6, '老师6', '123', '7537d0b8-4aac-4598-a49f-9fb0bba9b989', '812058051', 2);
INSERT INTO `teacher` VALUES (7, '老师7', '123', '3bf071e3-72e9-4d2b-8d6a-9f492a207636', '1039276811', 3);
INSERT INTO `teacher` VALUES (8, '老师8', '123', '8a0a5ee6-8593-4c24-a4ef-af33aa289b2f', '1819124624', 3);
INSERT INTO `teacher` VALUES (9, '老师9', '123', '87254bc8-e63c-47d6-bdd2-c54143863465', '549903260', 3);
INSERT INTO `teacher` VALUES (10, '老师10', '123', '5cf2f0e5-caff-4953-8dbe-f9c70d745ec3', '1300013458', 4);
INSERT INTO `teacher` VALUES (11, '老师11', '123', 'ee43e8b9-9b36-4240-9494-89e8eefd4f66', '365696847', 1);
INSERT INTO `teacher` VALUES (12, '老师12', '123', 'fba1ad3b-b32d-440e-8e43-2831f3f88eca', '2108555677', 4);
INSERT INTO `teacher` VALUES (13, '老师13', '123', 'ff5dab32-52a5-4e3f-bbb2-ab140abbfb8a', '815800506', 5);
INSERT INTO `teacher` VALUES (14, '老师14', '123', 'afd5fc05-d24a-4813-96c1-4fbb959a4d71', '136455144', 5);
INSERT INTO `teacher` VALUES (15, '老师15', '123', '0255e2a5-7136-4d47-9a96-d5978b1445db', '1527992535', 5);
INSERT INTO `teacher` VALUES (16, '老师16', '123', '6f278537-1c8b-41d8-81a3-aab87853f629', '1819658341', 6);
INSERT INTO `teacher` VALUES (17, '老师17', '123', 'be257625-3e87-447d-9ae5-8022dac6d55b', '1702269287', 6);
INSERT INTO `teacher` VALUES (18, '老师18', '123', 'd4a9915f-a910-4bcd-8610-7f190d5083d3', '739818380', 6);
INSERT INTO `teacher` VALUES (19, '老师19', '123', 'e9abbb7a-a355-45ff-be6a-afae6eaffce9', '543147759', 7);
INSERT INTO `teacher` VALUES (20, '老师20', '123', '8af2071f-4a0e-4810-b64a-90031f042027', '397360304', 7);
INSERT INTO `teacher` VALUES (21, '老师21', '123', '373fa9e1-bf71-429b-a138-17b2a3616809', '2086631387', 7);
INSERT INTO `teacher` VALUES (22, '老师22', '123', 'd4533e22-d636-4a3e-a923-7abb156ac207', '840135070', 8);
INSERT INTO `teacher` VALUES (23, '老师23', '123', '96568d45-e9af-46bc-8d60-20e321713822', '661192131', 8);
INSERT INTO `teacher` VALUES (24, '老师24', '123', '7c4c5c71-148e-4956-a469-67a25b79d871', '1390528310', 8);

-- ----------------------------
-- Table structure for teacher_project
-- ----------------------------
DROP TABLE IF EXISTS `teacher_project`;
CREATE TABLE `teacher_project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_date` datetime(0) NULL DEFAULT NULL,
  `apply_or_join` int(11) NULL DEFAULT NULL,
  `is_creator` bit(1) NULL DEFAULT NULL,
  `update_apply_date` datetime(0) NULL DEFAULT NULL,
  `project_id` int(11) NULL DEFAULT NULL,
  `teacher_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKsdoy9fkcc4ms2ha1rh17w9af2`(`project_id`) USING BTREE,
  INDEX `FKja9xfmqotd96u4ekqov1eq8h`(`teacher_id`) USING BTREE,
  CONSTRAINT `FKja9xfmqotd96u4ekqov1eq8h` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKsdoy9fkcc4ms2ha1rh17w9af2` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 186 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher_project
-- ----------------------------
INSERT INTO `teacher_project` VALUES (11, '2021-04-20 19:11:11', 3, b'1', '2021-04-22 19:11:11', 18, 1);
INSERT INTO `teacher_project` VALUES (12, '2021-04-20 19:50:53', 3, b'1', '2021-04-22 19:50:53', 40, 2);
INSERT INTO `teacher_project` VALUES (13, '2021-04-20 19:52:16', 3, b'0', '2021-04-22 19:52:16', 25, 2);
INSERT INTO `teacher_project` VALUES (14, '2021-04-20 19:52:17', 3, b'0', '2021-04-22 19:52:17', 32, 20);
INSERT INTO `teacher_project` VALUES (15, '2021-04-24 23:52:02', 3, b'1', '2021-04-26 23:52:02', 11, 19);
INSERT INTO `teacher_project` VALUES (16, '2020-10-02 12:08:32', 3, b'1', '2020-10-04 12:08:32', 17, 15);
INSERT INTO `teacher_project` VALUES (17, '2021-01-02 12:08:33', 3, b'1', '2021-01-04 12:08:33', 41, 11);
INSERT INTO `teacher_project` VALUES (18, '2020-09-21 12:08:34', 3, b'1', '2020-09-23 12:08:34', 15, 2);
INSERT INTO `teacher_project` VALUES (19, '2021-05-02 12:08:36', 3, b'1', '2021-05-04 12:08:36', 39, 1);
INSERT INTO `teacher_project` VALUES (20, '2021-03-02 12:08:37', 3, b'1', '2021-03-04 12:08:37', 11, 4);
INSERT INTO `teacher_project` VALUES (21, '2021-03-10 12:08:39', 3, b'1', '2021-03-12 12:08:39', 17, 5);
INSERT INTO `teacher_project` VALUES (22, '2021-04-14 12:08:39', 3, b'0', '2021-04-16 12:08:39', 42, 2);
INSERT INTO `teacher_project` VALUES (24, '2020-06-30 17:40:35', 3, b'1', '2020-07-02 17:40:35', 9, 7);
INSERT INTO `teacher_project` VALUES (40, '2021-03-10 12:08:39', 3, b'1', '2021-03-12 12:08:39', 17, 16);
INSERT INTO `teacher_project` VALUES (41, '2021-04-30 17:45:07', 3, b'1', '2021-05-02 17:45:07', 18, 2);
INSERT INTO `teacher_project` VALUES (42, '2020-10-02 12:08:32', 1, b'1', '2020-10-04 12:08:32', 19, 1);
INSERT INTO `teacher_project` VALUES (43, '2021-01-02 12:08:33', 1, b'1', '2021-01-04 12:08:33', 20, 4);
INSERT INTO `teacher_project` VALUES (44, '2020-09-21 12:08:34', 3, b'1', '2020-09-23 12:08:34', 43, 1);
INSERT INTO `teacher_project` VALUES (45, '2021-05-02 12:08:36', 3, b'1', '2021-05-04 12:08:36', 15, 24);
INSERT INTO `teacher_project` VALUES (48, '2021-04-14 12:08:39', 3, b'1', '2021-04-16 12:08:39', 17, 4);
INSERT INTO `teacher_project` VALUES (49, '2020-12-13 12:08:40', 3, b'1', '2020-12-15 12:08:40', 18, 17);
INSERT INTO `teacher_project` VALUES (50, '2020-06-30 17:40:35', 1, b'1', '2020-07-02 17:40:35', 19, 7);
INSERT INTO `teacher_project` VALUES (51, '2020-06-30 17:40:59', 1, b'1', '2020-07-02 17:40:59', 20, 1);
INSERT INTO `teacher_project` VALUES (52, '2020-10-02 12:08:32', 3, b'1', '2020-10-04 12:08:32', 45, 1);
INSERT INTO `teacher_project` VALUES (53, '2021-01-02 12:08:33', 3, b'1', '2021-01-04 12:08:33', 15, 2);
INSERT INTO `teacher_project` VALUES (56, '2021-04-14 12:08:39', 3, b'1', '2021-04-16 12:08:39', 17, 15);
INSERT INTO `teacher_project` VALUES (57, '2020-12-13 12:08:40', 3, b'1', '2020-12-15 12:08:40', 18, 16);
INSERT INTO `teacher_project` VALUES (58, '2020-06-30 17:40:35', 1, b'1', '2020-07-02 17:40:35', 19, 2);
INSERT INTO `teacher_project` VALUES (59, '2020-06-30 17:40:59', 1, b'1', '2020-07-02 17:40:59', 20, 1);
INSERT INTO `teacher_project` VALUES (60, '2020-10-02 12:08:32', 3, b'1', '2020-10-04 12:08:32', 44, 1);
INSERT INTO `teacher_project` VALUES (61, '2021-01-02 12:08:33', 3, b'1', '2021-01-04 12:08:33', 15, 5);
INSERT INTO `teacher_project` VALUES (62, '2020-09-21 12:08:34', 3, b'0', '2020-09-23 12:08:34', 7, 1);
INSERT INTO `teacher_project` VALUES (63, '2021-05-02 12:08:36', 3, b'1', '2021-05-04 12:08:36', 16, 4);
INSERT INTO `teacher_project` VALUES (64, '2021-03-02 12:08:37', 3, b'1', '2021-03-04 12:08:37', 46, 2);
INSERT INTO `teacher_project` VALUES (65, '2021-03-10 12:08:39', 3, b'1', '2021-03-12 12:08:39', 18, 20);
INSERT INTO `teacher_project` VALUES (66, '2020-06-30 17:39:52', 1, b'1', '2020-07-02 17:39:52', 19, 19);
INSERT INTO `teacher_project` VALUES (67, '2020-06-30 17:39:56', 1, b'1', '2020-07-02 17:39:56', 18, 15);
INSERT INTO `teacher_project` VALUES (69, '2020-10-02 12:08:32', 3, b'1', '2020-10-04 12:08:32', 25, 2);
INSERT INTO `teacher_project` VALUES (70, '2021-01-02 12:08:33', 3, b'0', '2021-01-04 12:08:33', 32, 1);
INSERT INTO `teacher_project` VALUES (71, '2020-09-21 12:08:34', 3, b'1', '2020-09-23 12:08:34', 11, 4);
INSERT INTO `teacher_project` VALUES (72, '2021-05-02 12:08:36', 3, b'1', '2021-05-04 12:08:36', 17, 5);
INSERT INTO `teacher_project` VALUES (74, '2020-06-30 17:39:56', 1, b'1', '2020-07-02 17:39:56', 15, 7);
INSERT INTO `teacher_project` VALUES (76, '2020-10-02 12:08:32', 3, b'1', '2020-10-04 12:08:32', 11, 3);
INSERT INTO `teacher_project` VALUES (77, '2021-01-02 12:08:33', 3, b'1', '2021-01-04 12:08:33', 47, 1);
INSERT INTO `teacher_project` VALUES (80, '2021-03-02 12:08:37', 3, b'1', '2021-03-04 12:08:37', 9, 20);
INSERT INTO `teacher_project` VALUES (82, '2020-10-02 12:08:32', 1, b'1', '2020-10-04 12:08:32', 18, 15);
INSERT INTO `teacher_project` VALUES (84, '2020-09-21 12:08:34', 3, b'1', '2020-09-23 12:08:34', 15, 2);
INSERT INTO `teacher_project` VALUES (85, '2021-05-02 12:08:36', 3, b'1', '2021-05-04 12:08:36', 15, 1);
INSERT INTO `teacher_project` VALUES (88, '2021-04-14 12:08:39', 3, b'1', '2021-04-16 12:08:39', 17, 24);
INSERT INTO `teacher_project` VALUES (89, '2020-12-13 12:08:40', 3, b'1', '2020-12-15 12:08:40', 18, 7);
INSERT INTO `teacher_project` VALUES (90, '2020-06-30 17:40:35', 1, b'1', '2020-07-02 17:40:35', 19, 7);
INSERT INTO `teacher_project` VALUES (91, '2020-06-30 17:40:59', 1, b'1', '2020-07-02 17:40:59', 20, 7);
INSERT INTO `teacher_project` VALUES (93, '2021-01-02 12:08:33', 3, b'1', '2021-01-04 12:08:33', 15, 3);
INSERT INTO `teacher_project` VALUES (96, '2021-03-02 12:08:37', 3, b'1', '2021-03-04 12:08:37', 17, 19);
INSERT INTO `teacher_project` VALUES (97, '2021-03-10 12:08:39', 3, b'1', '2021-03-12 12:08:39', 18, 15);
INSERT INTO `teacher_project` VALUES (98, '2020-06-30 17:39:52', 1, b'1', '2020-07-02 17:39:52', 19, 16);
INSERT INTO `teacher_project` VALUES (99, '2020-06-30 17:39:56', 1, b'1', '2020-07-02 17:39:56', 20, 2);
INSERT INTO `teacher_project` VALUES (101, '2021-04-28 04:14:43', 3, b'1', '2021-04-30 04:14:43', 15, 4);
INSERT INTO `teacher_project` VALUES (103, '2021-01-02 12:08:33', 3, b'1', '2021-01-04 12:08:33', 16, 2);
INSERT INTO `teacher_project` VALUES (104, '2020-10-02 12:08:32', 3, b'1', '2020-10-04 12:08:32', 17, 1);
INSERT INTO `teacher_project` VALUES (105, '2020-10-02 12:08:32', 3, b'1', '2020-10-04 12:08:32', 18, 3);
INSERT INTO `teacher_project` VALUES (106, '2021-01-02 12:08:33', 1, b'1', '2021-01-04 12:08:33', 18, 2);
INSERT INTO `teacher_project` VALUES (109, '2021-03-02 12:08:37', 3, b'1', '2021-03-04 12:08:37', 25, 15);
INSERT INTO `teacher_project` VALUES (110, '2021-03-10 12:08:39', 3, b'0', '2021-03-12 12:08:39', 32, 16);
INSERT INTO `teacher_project` VALUES (111, '2021-04-14 12:08:39', 3, b'0', '2021-04-16 12:08:39', 17, 11);
INSERT INTO `teacher_project` VALUES (114, '2020-06-30 17:40:59', 1, b'1', '2020-07-02 17:40:59', 9, 5);
INSERT INTO `teacher_project` VALUES (116, '2021-01-02 12:08:33', 3, b'1', '2021-01-04 12:08:33', 18, 7);
INSERT INTO `teacher_project` VALUES (118, '2021-05-02 12:08:36', 3, b'0', '2021-05-04 12:08:36', 25, 4);
INSERT INTO `teacher_project` VALUES (119, '2021-03-10 12:08:39', 3, b'1', '2021-03-12 12:08:39', 32, 4);
INSERT INTO `teacher_project` VALUES (121, '2020-06-30 17:39:56', 3, b'1', '2020-07-02 17:39:56', 11, 24);
INSERT INTO `teacher_project` VALUES (122, '2020-10-02 12:08:32', 1, b'1', '2020-10-04 12:08:32', 17, 7);
INSERT INTO `teacher_project` VALUES (123, '2020-10-02 12:08:32', 1, b'1', '2020-10-04 12:08:32', 17, 7);
INSERT INTO `teacher_project` VALUES (125, '2020-10-02 12:08:32', 3, b'1', '2020-10-04 12:08:32', 15, 3);
INSERT INTO `teacher_project` VALUES (128, '2021-05-02 12:08:36', 3, b'1', '2021-05-04 12:08:36', 17, 19);
INSERT INTO `teacher_project` VALUES (130, '2021-03-10 12:08:39', 1, b'1', '2021-03-12 12:08:39', 25, 16);
INSERT INTO `teacher_project` VALUES (131, '2021-04-14 12:08:39', 1, b'1', '2021-04-16 12:08:39', 32, 2);
INSERT INTO `teacher_project` VALUES (141, '2020-06-30 17:39:56', 3, b'1', '2020-07-02 17:39:56', 11, 24);
INSERT INTO `teacher_project` VALUES (142, '2020-10-02 12:08:32', 1, b'1', '2020-10-04 12:08:32', 17, 7);
INSERT INTO `teacher_project` VALUES (143, '2020-10-02 12:08:32', 1, b'1', '2020-10-04 12:08:32', 17, 7);
INSERT INTO `teacher_project` VALUES (145, '2020-10-02 12:08:32', 3, b'1', '2020-10-04 12:08:32', 15, 3);
INSERT INTO `teacher_project` VALUES (148, '2021-05-02 12:08:36', 3, b'1', '2021-05-04 12:08:36', 17, 19);
INSERT INTO `teacher_project` VALUES (150, '2021-03-10 12:08:39', 1, b'1', '2021-03-12 12:08:39', 25, 16);
INSERT INTO `teacher_project` VALUES (151, '2021-04-14 12:08:39', 1, b'1', '2021-04-16 12:08:39', 32, 2);
INSERT INTO `teacher_project` VALUES (152, '2021-04-20 19:11:11', 3, b'1', '2021-04-22 19:11:11', 7, 1);
INSERT INTO `teacher_project` VALUES (153, '2021-04-20 19:50:53', 3, b'1', '2021-04-22 19:50:53', 8, 1);
INSERT INTO `teacher_project` VALUES (155, '2021-04-20 19:52:17', 3, b'0', '2021-04-22 19:52:17', 8, 2);
INSERT INTO `teacher_project` VALUES (156, '2021-04-24 23:52:02', 3, b'1', '2021-04-26 23:52:02', 9, 1);
INSERT INTO `teacher_project` VALUES (157, '2021-04-25 14:30:28', 3, b'1', '2021-04-27 14:30:28', 10, 2);
INSERT INTO `teacher_project` VALUES (158, '2021-04-27 13:10:32', 3, b'1', '2021-04-29 13:10:32', 11, 1);
INSERT INTO `teacher_project` VALUES (159, '2021-04-28 03:59:36', 3, b'1', '2021-04-30 03:59:36', 12, 3);
INSERT INTO `teacher_project` VALUES (160, '2021-04-28 04:00:25', 3, b'1', '2021-04-30 04:00:25', 13, 3);
INSERT INTO `teacher_project` VALUES (182, '2021-05-21 23:58:05', 3, b'1', '2021-05-21 23:58:13', 58, 1);
INSERT INTO `teacher_project` VALUES (183, '2021-05-22 17:27:58', 3, b'1', '2021-05-22 17:28:13', 59, 1);
INSERT INTO `teacher_project` VALUES (184, '2021-05-29 00:09:11', 3, b'1', '2021-05-29 00:09:30', 60, 1);
INSERT INTO `teacher_project` VALUES (185, '2021-05-29 00:24:05', 3, b'1', '2021-05-29 00:24:21', 61, 1);

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_date` datetime(0) NULL DEFAULT NULL,
  `score` int(11) UNSIGNED NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `work_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `project_id` int(11) NULL DEFAULT NULL,
  `if_mark` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKf4jf1abybj7fc5w1nt686svxt`(`project_id`) USING BTREE,
  CONSTRAINT `FKf4jf1abybj7fc5w1nt686svxt` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES (10, '2021-05-29 00:14:26', 90, '2021-05-29 00:14:57', '作品', 8, b'1');
INSERT INTO `work` VALUES (11, '2021-05-29 00:29:22', 90, '2021-05-29 00:29:52', '新作品', 61, b'1');

-- ----------------------------
-- Table structure for work_file
-- ----------------------------
DROP TABLE IF EXISTS `work_file`;
CREATE TABLE `work_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_id` int(11) NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKfrplyaqs12p76j3rx4mqxkmtx`(`work_id`) USING BTREE,
  CONSTRAINT `FKfrplyaqs12p76j3rx4mqxkmtx` FOREIGN KEY (`work_id`) REFERENCES `work` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_file
-- ----------------------------
INSERT INTO `work_file` VALUES (30, '/upload/work/土木工程学院/第七届蓝桥杯/计信青年一队/2021/5/29/项目问题.txt', 10, '项目问题.txt');
INSERT INTO `work_file` VALUES (31, '/upload/work/计算机与信息工程学院/全国大学生计算机设计大赛/高校学科竞赛管理系统/2021/5/29/新建文本文档.txt', 11, '新建文本文档.txt');

SET FOREIGN_KEY_CHECKS = 1;
