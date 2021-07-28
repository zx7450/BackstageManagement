/*
 Navicat MySQL Data Transfer

 Source Server         : 到云远程数据库连接
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 47.106.170.237:3306
 Source Schema         : daoyundb

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 29/07/2021 00:54:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `courseId` int NOT NULL AUTO_INCREMENT,
  `courseName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `courseclass` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `term` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `teacherName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `joinable` int NOT NULL,
  `isschoolclass` int NOT NULL,
  `courseschool` int NOT NULL,
  `coursemajor` int NOT NULL,
  `learningrequire` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teachprogess` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `examarrange` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `coursestate` int NULL DEFAULT NULL,
  PRIMARY KEY (`courseId`) USING BTREE,
  INDEX `teacherName`(`teacherName`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacherName`) REFERENCES `user` (`userName`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 100017 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (100014, 'testcourse', '1', '2020-2021-1', 'teacher', 2, 1, 1, 2, NULL, NULL, NULL, '+393yN/d8jrG98r9yxe28uXbF21dhPHtV51udJU30pu66evoaIesdz0gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD/9k=', 1);
INSERT INTO `course` VALUES (100015, 'test', 'test1', '2021-2022-2', 'gou22gou', 2, 2, 1, 2, '', NULL, '', 'data:image/png;base64,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', 1);
INSERT INTO `course` VALUES (100016, '111', '\\\\\\', '2021-2022-2', 'ajj', 2, 2, 1, 5, '[[[', NULL, 'k', 'data:image/png;base64,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', 2);

-- ----------------------------
-- Table structure for coursestudent
-- ----------------------------
DROP TABLE IF EXISTS `coursestudent`;
CREATE TABLE `coursestudent`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `courseId` int NOT NULL,
  `studentId` int NOT NULL,
  `studentname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `totalExp` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `coursestudent_ibfk_2`(`studentId`) USING BTREE,
  INDEX `courseId`(`courseId`) USING BTREE,
  INDEX `studentname`(`studentname`) USING BTREE,
  CONSTRAINT `coursestudent_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `coursestudent_ibfk_2` FOREIGN KEY (`studentId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `coursestudent_ibfk_3` FOREIGN KEY (`studentname`) REFERENCES `user` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coursestudent
-- ----------------------------
INSERT INTO `coursestudent` VALUES (10, 100014, 25, 'student1', 2);
INSERT INTO `coursestudent` VALUES (11, 100014, 26, 'student2', 2);

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `dictionaryId` int NOT NULL AUTO_INCREMENT,
  `dictionaryCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dictionaryDescribe` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pId` int NULL DEFAULT NULL,
  PRIMARY KEY (`dictionaryId`, `dictionaryCode`) USING BTREE,
  INDEX `dictionaryCode`(`dictionaryCode`) USING BTREE,
  INDEX `dictionaryId`(`dictionaryId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (7, 'sex', '性别', 0);
INSERT INTO `dictionary` VALUES (8, 'school', '学校', 0);
INSERT INTO `dictionary` VALUES (11, 'major', '学院', 8);
INSERT INTO `dictionary` VALUES (13, 'term', '学期', 0);

-- ----------------------------
-- Table structure for dictionarydetail
-- ----------------------------
DROP TABLE IF EXISTS `dictionarydetail`;
CREATE TABLE `dictionarydetail`  (
  `dictionaryDetailId` int NOT NULL AUTO_INCREMENT,
  `dictionaryId` int NOT NULL,
  `dictionaryCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `itemKey` int NOT NULL,
  `itemValue` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isdefault` int NOT NULL,
  `detailpId` int NULL DEFAULT NULL,
  PRIMARY KEY (`dictionaryDetailId`, `itemKey`) USING BTREE,
  INDEX `FK_Reference_8`(`dictionaryId`) USING BTREE,
  INDEX `dictionarydetail_ibfk_1`(`dictionaryCode`) USING BTREE,
  CONSTRAINT `dictionarydetail_ibfk_1` FOREIGN KEY (`dictionaryCode`) REFERENCES `dictionary` (`dictionaryCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`dictionaryId`) REFERENCES `dictionary` (`dictionaryId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dictionarydetail
-- ----------------------------
INSERT INTO `dictionarydetail` VALUES (10, 8, 'school', 1, '福州大学', 0, 0);
INSERT INTO `dictionarydetail` VALUES (11, 8, 'school', 2, '厦门大学', 0, 0);
INSERT INTO `dictionarydetail` VALUES (21, 11, 'major', 2, '计算机', 0, 10);
INSERT INTO `dictionarydetail` VALUES (22, 11, 'major', 3, '计算机科学院', 0, 11);
INSERT INTO `dictionarydetail` VALUES (23, 11, 'major', 4, '电气', 0, 10);
INSERT INTO `dictionarydetail` VALUES (24, 11, 'major', 5, '生工', 0, 10);
INSERT INTO `dictionarydetail` VALUES (25, 11, 'major', 6, '机械', 0, 11);
INSERT INTO `dictionarydetail` VALUES (28, 13, 'term', 1, '2021-2022-1', 1, 0);
INSERT INTO `dictionarydetail` VALUES (29, 13, 'term', 2, '2021-2022-2', 0, 0);
INSERT INTO `dictionarydetail` VALUES (30, 7, 'sex', 1, '男', 0, 0);
INSERT INTO `dictionarydetail` VALUES (31, 7, 'sex', 2, '女', 0, 0);
INSERT INTO `dictionarydetail` VALUES (33, 13, 'term', 3, '2022-2023-1', 0, 0);
INSERT INTO `dictionarydetail` VALUES (34, 11, 'major', 7, '数学与计算机科学学院', 0, 10);
INSERT INTO `dictionarydetail` VALUES (35, 8, 'school', 0, '未知', 1, 0);
INSERT INTO `dictionarydetail` VALUES (36, 11, 'major', 0, '未知', 1, 35);
INSERT INTO `dictionarydetail` VALUES (37, 7, 'sex', 0, '未知', 1, 0);
INSERT INTO `dictionarydetail` VALUES (39, 11, 'major', 8, '化学', 0, 10);

-- ----------------------------
-- Table structure for githubuser
-- ----------------------------
DROP TABLE IF EXISTS `githubuser`;
CREATE TABLE `githubuser`  (
  `userId` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of githubuser
-- ----------------------------

-- ----------------------------
-- Table structure for initsign
-- ----------------------------
DROP TABLE IF EXISTS `initsign`;
CREATE TABLE `initsign`  (
  `teachersignId` int NOT NULL AUTO_INCREMENT,
  `teacherId` int NOT NULL,
  `courseId` int NOT NULL,
  `begtime` bigint NOT NULL,
  `endtime` bigint NULL DEFAULT NULL,
  `teachlongitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `teachlatitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sightype` int NOT NULL,
  `isEnd` int NULL DEFAULT NULL,
  PRIMARY KEY (`teachersignId`) USING BTREE,
  INDEX `teacherId`(`teacherId`) USING BTREE,
  INDEX `courseId`(`courseId`) USING BTREE,
  CONSTRAINT `initsign_ibfk_2` FOREIGN KEY (`teacherId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `initsign_ibfk_3` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of initsign
-- ----------------------------
INSERT INTO `initsign` VALUES (70, 24, 100014, 1624965559470, 1624965579188, '119.19811733211', '26.055361985439', 1, 0);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menuId` int NOT NULL AUTO_INCREMENT,
  `parentId` int NOT NULL DEFAULT 0,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int NULL DEFAULT NULL,
  `hidden` tinyint(1) NULL DEFAULT 0,
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `menutype` int NULL DEFAULT NULL,
  PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (47, 0, '首页', 'home', 'HomeOutlined', '无', NULL, NULL, '2021-07-28 12:17:51', 1);
INSERT INTO `menu` VALUES (48, 0, '我的', 'me', 'UserOutlined', '无', NULL, NULL, '2021-07-28 12:17:56', 1);
INSERT INTO `menu` VALUES (49, 0, '用户列表', 'user', 'AuditOutlined', '无', NULL, NULL, '2021-07-28 12:20:47', 1);
INSERT INTO `menu` VALUES (50, 0, '我的班课', 'class', 'UnorderedListOutlined', '无', NULL, NULL, '2021-07-28 12:31:05', 1);
INSERT INTO `menu` VALUES (51, 0, '数据字典', 'dictation', 'ReadOutlined', '无', NULL, NULL, '2021-07-28 12:36:24', 1);
INSERT INTO `menu` VALUES (52, 0, '系统参数', 'system-param', 'SettingOutlined', '无', NULL, NULL, '2021-07-29 00:19:52', 1);
INSERT INTO `menu` VALUES (53, 0, '菜单管理', 'menu-edit', 'SettingOutlined', '/menu/addMenu,/menu/deleteMenu/,/menu/updateMenu', NULL, NULL, '2021-07-28 23:37:30', 1);
INSERT INTO `menu` VALUES (54, 0, '权限管理', 'right-edit', 'SettingOutlined', '/roleright/addRole,/menu/addRoleMenu,/menu/deleteRoleMenu,/roleright/updateRoleById,/roleright/deleteRoleById/', NULL, NULL, '2021-07-28 23:37:36', 1);
INSERT INTO `menu` VALUES (55, 49, '获取用户列表', 'user:list', '无', '/getalluser', NULL, NULL, '2021-07-28 12:58:53', 2);
INSERT INTO `menu` VALUES (56, 49, '编辑用户', 'user:edit', '无', '无', NULL, NULL, '2021-07-28 22:58:21', NULL);
INSERT INTO `menu` VALUES (58, 50, '我创建的班课', 'class:created', '无', '/createlist', NULL, NULL, '2021-07-28 12:31:37', NULL);
INSERT INTO `menu` VALUES (60, 50, '所有班课', 'class:all', '无', '/getallcourse', NULL, NULL, '2021-07-28 12:33:41', NULL);
INSERT INTO `menu` VALUES (61, 60, '删除任意班课', 'class:delete_all', '无', '无', NULL, NULL, '2021-07-28 12:34:05', NULL);
INSERT INTO `menu` VALUES (62, 50, '班课详情', 'class:describe', '无', '无', NULL, NULL, '2021-07-28 12:34:34', NULL);
INSERT INTO `menu` VALUES (64, 62, '编辑任意班课', 'class:describe:editAll', '无', '无', NULL, NULL, '2021-07-28 12:35:39', NULL);
INSERT INTO `menu` VALUES (65, 50, '新增班课', 'class:creating', '无', '/creatclass', NULL, NULL, '2021-07-28 12:36:13', NULL);
INSERT INTO `menu` VALUES (66, 51, '修改数据字典', 'dictation:edit', '无', '/dictionary/updatedictionary，/dictionary/adddic', NULL, NULL, '2021-07-28 23:08:12', NULL);
INSERT INTO `menu` VALUES (67, 52, '修改系统参数', 'sysparam:edit', '无', '/syspara/addpara,/syspara/deletepara,,/syspara/updatepara', NULL, NULL, '2021-07-28 12:38:27', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `roleDes` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `perms` varchar(510) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (13, 'admin', '管理员', 'home,me,user,user:list,user:edit,class,class:created,class:all,class:delete_all,dictation,dictation:edit,system-param,sysparam:edit,menu-edit,right-edit,class:describe,class:creating,class:describe:editAll,');
INSERT INTO `role` VALUES (14, 'teacher', '教师', 'home,me,user:list,class,class:created,class:describe,class:creating,dictation,system-param,user,');
INSERT INTO `role` VALUES (15, 'student', '学生', 'home,me,user:list,class,class:describe,class:created,class:creating,');
INSERT INTO `role` VALUES (16, 'admin-teacher', '教务处老师', 'home,me,user,user:list,user:edit,user:delete,class,class:created,class:delete_created,class:all,class:delete_all,class:describe,class:describe:editCreated,class:describe:editAll,class:creating,dictation,dictation:edit,system-param,sysparam:edit,');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `rolemenuId` int NOT NULL AUTO_INCREMENT,
  `roleId` int NOT NULL,
  `menuId` int NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rolemenuId`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  INDEX `menuId`(`menuId`) USING BTREE,
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`menuId`) REFERENCES `menu` (`menuId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 13, 47, '2021-07-25 00:38:15');
INSERT INTO `role_menu` VALUES (2, 13, 48, '2021-07-25 00:38:32');
INSERT INTO `role_menu` VALUES (3, 13, 49, '2021-07-25 00:38:32');
INSERT INTO `role_menu` VALUES (4, 13, 55, '2021-07-25 00:38:32');
INSERT INTO `role_menu` VALUES (5, 13, 56, '2021-07-25 00:38:32');
INSERT INTO `role_menu` VALUES (7, 13, 50, '2021-07-25 00:38:32');
INSERT INTO `role_menu` VALUES (16, 13, 51, '2021-07-25 00:38:32');
INSERT INTO `role_menu` VALUES (17, 13, 66, '2021-07-25 00:38:32');
INSERT INTO `role_menu` VALUES (18, 13, 52, '2021-07-25 00:38:32');
INSERT INTO `role_menu` VALUES (19, 13, 67, '2021-07-25 00:38:32');
INSERT INTO `role_menu` VALUES (20, 13, 53, '2021-07-25 00:44:34');
INSERT INTO `role_menu` VALUES (21, 13, 54, '2021-07-25 00:44:34');
INSERT INTO `role_menu` VALUES (22, 14, 47, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (23, 14, 48, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (24, 14, 49, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (25, 14, 55, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (26, 14, 50, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (27, 14, 58, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (29, 14, 62, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (31, 14, 65, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (32, 14, 51, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (33, 14, 52, '2021-07-25 00:45:02');
INSERT INTO `role_menu` VALUES (34, 15, 47, '2021-07-25 00:45:17');
INSERT INTO `role_menu` VALUES (35, 15, 48, '2021-07-25 00:45:17');
INSERT INTO `role_menu` VALUES (37, 15, 55, '2021-07-25 00:45:17');
INSERT INTO `role_menu` VALUES (38, 15, 50, '2021-07-25 00:45:17');
INSERT INTO `role_menu` VALUES (39, 15, 62, '2021-07-25 00:45:17');
INSERT INTO `role_menu` VALUES (40, 16, 47, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (41, 16, 48, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (42, 16, 49, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (43, 16, 55, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (44, 16, 56, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (46, 16, 50, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (47, 16, 58, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (49, 16, 60, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (50, 16, 61, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (51, 16, 62, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (53, 16, 64, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (54, 16, 65, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (55, 16, 51, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (56, 16, 66, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (57, 16, 52, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (58, 16, 67, '2021-07-25 01:03:53');
INSERT INTO `role_menu` VALUES (59, 13, 60, '2021-07-26 22:39:52');
INSERT INTO `role_menu` VALUES (60, 13, 58, '2021-07-26 22:39:52');
INSERT INTO `role_menu` VALUES (65, 13, 64, '2021-07-28 12:45:00');
INSERT INTO `role_menu` VALUES (66, 13, 61, '2021-07-28 12:45:00');
INSERT INTO `role_menu` VALUES (68, 13, 64, '2021-07-28 12:45:25');
INSERT INTO `role_menu` VALUES (69, 13, 62, '2021-07-28 23:14:45');
INSERT INTO `role_menu` VALUES (70, 13, 65, '2021-07-28 23:14:45');
INSERT INTO `role_menu` VALUES (71, 13, 64, '2021-07-28 23:14:45');
INSERT INTO `role_menu` VALUES (72, 15, 58, '2021-07-29 00:40:01');
INSERT INTO `role_menu` VALUES (73, 15, 65, '2021-07-29 00:40:01');

-- ----------------------------
-- Table structure for studentsign
-- ----------------------------
DROP TABLE IF EXISTS `studentsign`;
CREATE TABLE `studentsign`  (
  `studentsignid` int NOT NULL AUTO_INCREMENT,
  `teachersignId` int NOT NULL,
  `studentId` int NOT NULL,
  `courseId` int NOT NULL,
  `longitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `latitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `signtype` int NOT NULL,
  `signtime` bigint NULL DEFAULT NULL,
  `signstatus` int NOT NULL,
  `getexp` int NULL DEFAULT NULL,
  PRIMARY KEY (`studentsignid`) USING BTREE,
  INDEX `teachersignId`(`teachersignId`) USING BTREE,
  INDEX `studentId`(`studentId`) USING BTREE,
  INDEX `courseId`(`courseId`) USING BTREE,
  CONSTRAINT `studentsign_ibfk_1` FOREIGN KEY (`teachersignId`) REFERENCES `initsign` (`teachersignId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentsign_ibfk_2` FOREIGN KEY (`studentId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentsign_ibfk_3` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of studentsign
-- ----------------------------
INSERT INTO `studentsign` VALUES (71, 70, 25, 100014, '119.19811733211', '26.055361985439', 1, 1624965571610, 2, 2);
INSERT INTO `studentsign` VALUES (72, 70, 26, 100014, '119.19811733211', '26.055361985439', 1, 1624965571610, 2, 2);

-- ----------------------------
-- Table structure for syapara
-- ----------------------------
DROP TABLE IF EXISTS `syapara`;
CREATE TABLE `syapara`  (
  `sysparaid` int NOT NULL AUTO_INCREMENT,
  `hintname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `keyword` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` int NOT NULL,
  PRIMARY KEY (`sysparaid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of syapara
-- ----------------------------
INSERT INTO `syapara` VALUES (10, '距离', 'distance', 200);
INSERT INTO `syapara` VALUES (11, '签到经验值', 'experience', 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userPassward` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` int NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` int NULL DEFAULT NULL,
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthyear` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userschool` int NULL DEFAULT NULL,
  `depart` int NULL DEFAULT NULL,
  `perid` int NULL DEFAULT NULL,
  `avatar` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  INDEX `userName`(`userName`) USING BTREE,
  INDEX `role`(`role`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (23, 'admin', '123456', 1, '15900000001', 13, NULL, '1970-01-06T00:00:02.021Z', 1, 2, 0, NULL);
INSERT INTO `user` VALUES (24, 'teacher', '123456', 2, '15900000002', 14, NULL, '1970-01-06T00:00:02.021Z', 1, 2, 0, NULL);
INSERT INTO `user` VALUES (25, 'student1', '123456', 1, '15900000003', 15, NULL, '1970-01-06T00:00:02.021Z', 1, 2, 0, NULL);
INSERT INTO `user` VALUES (26, 'student2', '123456', 1, '15900000004', 15, NULL, '1996-01-06T00:00:02.021Z', 1, 2, 0, NULL);
INSERT INTO `user` VALUES (27, 'gou22gou', '123456', 1, '18065287766', 13, '2', '2005-01-20T16:00:00.000Z', 1, 8, 2003271732, 'data:image/jpeg;base64,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');
INSERT INTO `user` VALUES (28, 'ajj', '123456', 1, '15398105516', 14, NULL, '2009-01-05T16:00:00.000Z', 1, 2, 23213, 'data:image/jpeg;base64,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');

SET FOREIGN_KEY_CHECKS = 1;
