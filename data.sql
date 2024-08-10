/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : dlyk

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 20/05/2024 20:47:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，活动ID',
  `owner_id` int NULL DEFAULT NULL COMMENT '活动所属人ID',
  `name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `start_time` datetime NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '活动结束时间',
  `cost` decimal(11, 2) NULL DEFAULT NULL COMMENT '活动预算',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '活动描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '活动创建时间',
  `create_by` int NULL DEFAULT NULL COMMENT '活动创建人',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '活动编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '活动编辑人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `owner`(`owner_id` ASC) USING BTREE,
  INDEX `create_by`(`create_by` ASC) USING BTREE,
  INDEX `edit_by`(`edit_by` ASC) USING BTREE,
  CONSTRAINT `t_activity_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_activity_ibfk_2` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_activity_ibfk_3` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '市场活动表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_activity
-- ----------------------------
INSERT INTO `t_activity` VALUES (2, 2, '充话费-送手机', '2023-03-28 17:48:49', '2023-04-30 17:48:54', 9000.00, '充话费,送手机,充满送Iphone14', '2023-03-28 17:49:28', 1, '2024-05-18 19:09:07', 1);
INSERT INTO `t_activity` VALUES (7, 1, '抖音推广啊', '2023-04-07 12:03:09', '2023-04-30 12:03:09', 15800.00, '抖音直播推广活动1', NULL, 6, '2024-05-04 16:54:43', 1);
INSERT INTO `t_activity` VALUES (8, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-30 12:03:09', 15800.00, '抖音直播推广活动2', NULL, 7, '2023-04-28 14:28:53', NULL);
INSERT INTO `t_activity` VALUES (10, 2, '11', '2023-04-11 00:30:58', '2023-04-30 00:31:00', 131.00, '12312312', NULL, NULL, '2024-05-10 22:49:25', 2);
INSERT INTO `t_activity` VALUES (11, 3, '1213', '2023-04-01 00:00:00', '2023-04-30 00:31:13', 123123.00, '23123123', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (12, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, '2024-05-07 15:04:20', 1);
INSERT INTO `t_activity` VALUES (13, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, '2024-05-04 10:10:41', 1);
INSERT INTO `t_activity` VALUES (14, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (15, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动11111', NULL, NULL, '2023-04-26 17:09:49', NULL);
INSERT INTO `t_activity` VALUES (16, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (17, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (18, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (19, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (20, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (21, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (22, 1, '抖音推广', '2023-04-01 10:03:09', '2023-04-09 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (23, 1, '抖音推广', '2023-04-01 10:03:09', '2023-04-11 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (24, 1, '抖音推广', '2023-04-01 10:03:09', '2023-04-12 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (25, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (26, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (27, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (28, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (29, 1, '抖音推广', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, '2024-05-06 23:19:21', 1);
INSERT INTO `t_activity` VALUES (40, 1, '抖音推广11', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (41, 1, '抖音推广11', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (42, 1, '抖音推广11', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动1', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (43, 1, '抖音推广11', '2023-04-01 12:03:09', '2023-04-01 12:03:09', 15800.00, '抖音直播推广活动2', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (46, 3, '抖音短视频广告', '2023-11-14 00:00:00', '2023-11-30 00:00:00', 5000.00, '抖音短视频广告，宣传产品', NULL, NULL, NULL, NULL);
INSERT INTO `t_activity` VALUES (47, 3, '我去恶趣味', '2023-11-15 16:51:40', '2024-05-24 16:51:42', 2312.00, '色达所大所大所多', NULL, NULL, '2024-05-10 22:16:02', 1);
INSERT INTO `t_activity` VALUES (62, 4, '伟大伟大', '2024-05-08 00:00:00', '2024-05-13 19:33:27', 223423.00, '丰富的v发展v的', '2024-05-13 19:33:33', 4, '2024-05-14 17:37:41', 4);
INSERT INTO `t_activity` VALUES (63, 2, '干掉卷王', '2024-04-17 00:00:00', '2024-05-18 11:22:10', 2345.00, '四谷牧凯斯介乎的隐去', '2024-05-18 11:22:33', 1, NULL, NULL);
INSERT INTO `t_activity` VALUES (64, 5, '原神代练大活动', '2023-10-11 00:00:00', '2024-06-29 00:00:00', 2333.00, '啊擦擦擦我擦二分', '2024-05-18 11:26:28', 1, NULL, NULL);

-- ----------------------------
-- Table structure for t_activity_remark
-- ----------------------------
DROP TABLE IF EXISTS `t_activity_remark`;
CREATE TABLE `t_activity_remark`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，活动备注ID',
  `activity_id` int NULL DEFAULT NULL COMMENT '活动ID',
  `note_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '备注创建时间',
  `create_by` int NULL DEFAULT NULL COMMENT '备注创建人',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '备注编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '备注编辑人',
  `deleted` int NULL DEFAULT NULL COMMENT '删除状态（0正常，1删除）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  INDEX `t_activity_remark_ibfk_2`(`create_by` ASC) USING BTREE,
  INDEX `t_activity_remark_ibfk_3`(`edit_by` ASC) USING BTREE,
  CONSTRAINT `t_activity_remark_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_activity_remark_ibfk_2` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_activity_remark_ibfk_3` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '市场活动备注表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_activity_remark
-- ----------------------------
INSERT INTO `t_activity_remark` VALUES (1, 46, '1111111111111', '2023-05-17 14:07:48', 1, NULL, NULL, 1);
INSERT INTO `t_activity_remark` VALUES (3, 2, '1231231', '2023-06-07 23:08:32', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (4, 2, '素有萨乌丁我达瓦大屋顶从', '2023-06-07 23:08:57', 1, '2024-05-04 17:50:58', 1, NULL);
INSERT INTO `t_activity_remark` VALUES (5, 10, '恶趣味群二无群二群无', '2023-06-07 23:10:20', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (7, 7, '额粉色粉色', '2024-05-04 15:24:22', 1, NULL, NULL, 1);
INSERT INTO `t_activity_remark` VALUES (8, 7, '分色法', '2024-05-04 15:27:06', 1, NULL, NULL, 1);
INSERT INTO `t_activity_remark` VALUES (9, 7, '粉色粉色', '2024-05-04 15:27:29', 1, NULL, NULL, 1);
INSERT INTO `t_activity_remark` VALUES (10, NULL, '粉色粉色粉色发', '2024-05-04 15:29:05', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (11, 7, '二分色法色法色法', '2024-05-04 15:30:13', 1, NULL, NULL, 1);
INSERT INTO `t_activity_remark` VALUES (12, 11, '丝袜吊袜带', '2024-05-04 16:29:10', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (13, 11, '沃达丰萨芬', '2024-05-04 16:31:33', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (14, 11, '打啊打我的', '2024-05-04 16:32:15', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (15, 10, '今天是个好日子', '2024-05-04 16:41:53', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (16, 10, '贤惠幼妻仙狐小姐', '2024-05-04 16:44:13', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (17, 10, '不能使用表情包留言', '2024-05-04 16:50:47', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (19, 2, '管理员先生你好，你好啊', '2024-05-04 16:55:45', 2, '2024-05-04 17:51:16', 1, NULL);
INSERT INTO `t_activity_remark` VALUES (20, 7, '测试数据单独', '2024-05-04 18:09:44', 1, NULL, NULL, 1);
INSERT INTO `t_activity_remark` VALUES (21, 7, '你的擦赐额u部分v肤色白v', '2024-05-04 18:10:28', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (28, 47, '发我发飞啊飞啊分色法，jjjjj', '2024-05-05 18:52:35', 1, NULL, NULL, 1);
INSERT INTO `t_activity_remark` VALUES (29, 47, '大伟大伟大大苏打。达瓦大屋顶', '2024-05-05 18:52:40', 1, NULL, NULL, 1);
INSERT INTO `t_activity_remark` VALUES (30, 46, '阿伟大碗大苏打的', '2024-05-05 18:52:47', 1, NULL, NULL, 1);
INSERT INTO `t_activity_remark` VALUES (34, 8, '今天黑人女孩日期分色法色法', '2024-05-07 14:43:20', 1, '2024-05-07 14:43:39', 1, NULL);
INSERT INTO `t_activity_remark` VALUES (35, 12, '不错的活动', '2024-05-10 10:56:18', 1, NULL, NULL, NULL);
INSERT INTO `t_activity_remark` VALUES (37, 62, '粉色粉色发fsafesf', '2024-05-13 19:36:03', 4, '2024-05-13 19:36:14', 1, NULL);

-- ----------------------------
-- Table structure for t_clue
-- ----------------------------
DROP TABLE IF EXISTS `t_clue`;
CREATE TABLE `t_clue`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，线索ID',
  `owner_id` int NULL DEFAULT NULL COMMENT '线索所属人ID',
  `activity_id` int NULL DEFAULT NULL COMMENT '活动ID',
  `full_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `appellation` int NULL DEFAULT NULL COMMENT '称呼',
  `phone` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `weixin` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `qq` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'QQ号',
  `email` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `job` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '职业',
  `year_income` decimal(10, 2) NULL DEFAULT NULL COMMENT '年收入',
  `address` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地址',
  `need_loan` int NULL DEFAULT NULL COMMENT '是否需要贷款（0不需要，1需要）',
  `intention_state` int NULL DEFAULT NULL COMMENT '意向状态',
  `intention_product` int NULL DEFAULT NULL COMMENT '意向产品',
  `state` int NULL DEFAULT NULL COMMENT '线索状态',
  `source` int NULL DEFAULT NULL COMMENT '线索来源',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '线索描述',
  `next_contact_time` datetime NULL DEFAULT NULL COMMENT '下次联系时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建人',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '编辑人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `appellation`(`appellation` ASC) USING BTREE,
  INDEX `state`(`state` ASC) USING BTREE,
  INDEX `source`(`source` ASC) USING BTREE,
  INDEX `owner`(`owner_id` ASC) USING BTREE,
  INDEX `create_by`(`create_by` ASC) USING BTREE,
  INDEX `edit_by`(`edit_by` ASC) USING BTREE,
  INDEX `t_clue_ibfk_7`(`activity_id` ASC) USING BTREE,
  INDEX `t_clue_ibfk_8`(`need_loan` ASC) USING BTREE,
  INDEX `t_clue_ibfk_9`(`intention_state` ASC) USING BTREE,
  INDEX `t_clue_ibfk_10`(`intention_product` ASC) USING BTREE,
  CONSTRAINT `t_clue_ibfk_1` FOREIGN KEY (`appellation`) REFERENCES `t_dic_value` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_ibfk_10` FOREIGN KEY (`intention_product`) REFERENCES `t_product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_ibfk_3` FOREIGN KEY (`source`) REFERENCES `t_dic_value` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_ibfk_4` FOREIGN KEY (`owner_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_ibfk_5` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_ibfk_6` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_ibfk_7` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_ibfk_8` FOREIGN KEY (`need_loan`) REFERENCES `t_dic_value` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_ibfk_9` FOREIGN KEY (`intention_state`) REFERENCES `t_dic_value` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1197 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '线索表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_clue
-- ----------------------------
INSERT INTO `t_clue` VALUES (1, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 50, 46, 5, -1, 3, '近期在看车', '2023-04-27 10:33:47', '2023-07-17 15:17:52', 1, '2024-05-13 18:03:14', 1);
INSERT INTO `t_clue` VALUES (2, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 50, 47, 8, -1, 33, '通过打电话获取的线索', '2024-05-18 19:09:46', '2023-06-10 01:01:13', NULL, '2024-05-18 19:09:48', 1);
INSERT INTO `t_clue` VALUES (3, 2, 7, '张玉祥', 18, '13876903226', '13876903226', '123456', NULL, 26, NULL, 9.00, '天津和平', 50, 48, 10, -1, 44, '有购车意向，需要跟踪，活动', '2023-04-15 00:00:00', '2024-05-10 22:01:34', 1, '2024-05-13 10:52:19', 2);
INSERT INTO `t_clue` VALUES (4, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 50, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47', '2023-04-12 15:17:52', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (5, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 50, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51', '2023-06-10 01:01:19', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (6, 2, 7, '张翔', 18, '13876903226', '13876903226', NULL, NULL, 26, NULL, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00', '2023-06-10 01:01:23', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (7, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47', '2023-04-12 15:17:52', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (8, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51', '2024-05-10 10:41:06', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (9, 2, 7, '张翔', 18, '13876903226', '13876903226', NULL, NULL, 26, NULL, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-28 13:24:10', '2023-06-10 01:01:28', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (10, 1, 46, '王菲', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '', 10.00, '北京亦庄', 49, 46, 5, -1, 3, '近期在看车', '2023-04-27 10:33:47', '2024-05-10 10:41:18', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (11, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 8, 24, 33, '通过打电话获取的线索', '2023-04-30 10:33:51', '2023-06-10 01:01:30', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (12, 2, 7, '张翔', 18, '13876903226', '13876903226', NULL, NULL, 26, NULL, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00', '2023-06-10 01:01:33', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (13, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, -1, 3, '近期在看车', '2023-04-27 10:33:47', '2023-04-12 15:17:52', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (14, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51', '2023-06-10 01:01:36', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (15, 2, 7, '张翔', 18, '13876903226', '13876903226', NULL, NULL, 26, NULL, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00', '2023-06-10 01:01:38', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (16, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47', '2023-04-12 15:17:52', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (17, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51', '2023-06-10 01:01:41', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (18, 2, 7, '张翔', 18, '13876903226', '13876903226', NULL, NULL, 26, NULL, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00', '2023-06-10 01:01:44', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (19, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, -1, 3, '近期在看车', '2023-04-27 10:33:47', '2023-06-12 15:17:52', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (20, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 8, 24, 33, '通过打电话获取的线索', '2023-04-30 10:33:51', '2023-06-10 01:01:46', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (21, 2, 7, '张翔', 18, '13876903226', '13876903226', NULL, NULL, 26, NULL, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00', '2023-06-10 01:01:46', NULL, '2024-05-10 23:12:41', 2);
INSERT INTO `t_clue` VALUES (22, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47', '2023-04-12 15:17:52', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (23, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51', '2023-06-10 01:01:46', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (24, 2, 7, '张翔', 18, '13876903226', '13876903226', NULL, NULL, 26, NULL, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00', '2023-06-10 01:01:46', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (25, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47', '2023-04-12 15:17:52', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (26, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 8, 24, 33, '通过打电话获取的线索', '2023-04-30 10:33:51', '2023-06-10 01:01:46', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (27, 2, 7, '张翔', 18, '13876903226', '13876903226', NULL, NULL, 26, NULL, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00', '2023-06-10 01:01:46', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (28, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47', '2023-06-12 15:17:52', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (29, 1, 47, '张峰', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 8, 24, 33, '通过打电话获取的线索', '2023-04-30 10:33:51', '2023-06-10 01:01:46', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (31, 3, 2, '213213', 20, '12312312', '12312312', '2312312', '12312312', 12, '341231', 2131231.00, '12312312', 50, 46, 7, 24, 44, 'asfeefsdewrewr', '2023-04-27 16:48:30', '2023-06-10 01:01:46', NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1119, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', NULL, NULL, NULL, NULL, NULL, '近期在看车', '2023-11-27 20:33:25', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1120, 1, 47, '张怡然', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', NULL, NULL, NULL, NULL, NULL, '通过打电话获取的线索', '2023-11-30 10:33:51', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1121, 2, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', NULL, 26, NULL, 9.00, '天津和平', NULL, NULL, NULL, NULL, NULL, '有购车意向，需要跟踪', '2023-11-15 10:30:00', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1122, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', NULL, NULL, NULL, NULL, NULL, '近期在看车', '2023-12-27 09:20:21', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1123, 1, 47, '张珊珊', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', NULL, NULL, NULL, NULL, NULL, '通过打电话获取的线索', '2023-11-30 13:33:51', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1124, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', NULL, NULL, NULL, NULL, NULL, '近期在看车', '2023-11-27 20:33:25', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1125, 1, 47, '张怡然', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', NULL, NULL, NULL, NULL, NULL, '通过打电话获取的线索', '2023-11-30 10:33:51', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1127, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', NULL, NULL, NULL, NULL, NULL, '近期在看车', '2023-12-27 09:20:21', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1128, 1, 47, '张珊珊', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', NULL, NULL, NULL, NULL, NULL, '通过打电话获取的线索', '2023-11-30 13:33:51', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1139, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', NULL, NULL, NULL, NULL, NULL, '近期在看车', '2023-11-27 20:33:25', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1140, 1, 47, '张怡然', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', NULL, NULL, NULL, NULL, NULL, '通过打电话获取的线索', '2023-11-30 10:33:51', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1142, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', NULL, NULL, NULL, NULL, NULL, '近期在看车', '2023-12-27 09:20:21', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1143, 1, 47, '张珊珊', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', NULL, NULL, NULL, NULL, NULL, '通过打电话获取的线索', '2023-11-30 13:33:51', NULL, NULL, NULL, NULL);
INSERT INTO `t_clue` VALUES (1155, 7, 47, '张怡然', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 50, 46, 4, 6, 16, '通过打电话获取的线索', '2023-11-30 10:33:51', '2024-05-07 15:42:25', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1156, 12, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', NULL, 26, NULL, 9.00, '天津和平', 49, 48, 7, 30, 17, '有购车意向，需要跟踪', '2023-11-15 10:30:00', '2024-05-07 15:42:25', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1157, 19, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 50, 48, 6, 24, 17, '近期在看车', '2023-12-27 09:20:21', '2024-05-07 15:42:25', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1158, 19, 47, '张珊珊', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 9, 6, 17, '通过打电话获取的线索', '2023-11-30 13:33:51', '2024-05-07 15:42:25', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1159, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 2, 24, 3, '近期在看车', '2023-11-27 20:33:25', '2024-05-09 15:42:35', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1160, 7, 47, '张怡然', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 50, 46, 4, 6, 16, '通过打电话获取的线索', '2023-11-30 10:33:51', '2024-05-09 15:42:35', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1161, 12, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', NULL, 26, NULL, 9.00, '天津和平', 49, 48, 7, 30, 17, '有购车意向，需要跟踪', '2023-11-15 10:30:00', '2024-05-09 15:42:35', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1162, 19, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 50, 48, 6, 24, 17, '近期在看车', '2023-12-27 09:20:21', '2024-05-09 15:42:35', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1163, 19, 47, '张珊珊', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 9, 6, 17, '通过打电话获取的线索', '2023-11-30 13:33:51', '2024-05-09 15:42:35', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1164, 1, 47, NULL, 5, '13233445455', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-05-10 10:45:22', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1166, 1, 47, '甘雨', 41, '15477889988', '15477889988', '12022233', 'a@qq.com', 22, '无业', 12.00, '中关村', 50, 46, 8, 27, 25, '拿下大对哇对哇', '2024-05-22 00:00:00', '2024-05-09 22:39:39', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1168, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 2, 24, 3, '近期在看车', '2023-11-27 20:33:25', '2024-05-10 18:46:06', 2, NULL, NULL);
INSERT INTO `t_clue` VALUES (1169, 7, 47, '张怡然', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 50, 46, 4, 6, 16, '通过打电话获取的线索', '2023-11-30 10:33:51', '2024-05-10 18:46:06', 2, NULL, NULL);
INSERT INTO `t_clue` VALUES (1172, 19, 47, '张珊珊', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 9, -1, 17, '通过打电话获取的线索还会', '2023-11-30 13:33:51', '2024-05-10 18:46:06', 2, '2024-05-10 22:11:36', 1);
INSERT INTO `t_clue` VALUES (1179, 2, NULL, NULL, 5, '15566886444', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -1, NULL, NULL, NULL, '2024-05-10 22:44:37', 2, '2024-05-11 17:55:18', 1);
INSERT INTO `t_clue` VALUES (1180, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 2, 24, 3, '近期在看车', '2023-11-27 20:33:25', '2024-05-11 15:50:36', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1182, 12, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', NULL, 26, NULL, 9.00, '天津和平', 49, 48, 7, 30, 17, '有购车意向，需要跟踪', '2023-11-15 10:30:00', '2024-05-11 15:50:36', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1183, 19, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 50, 48, 6, 24, 17, '近期在看车', '2023-12-27 09:20:21', '2024-05-11 15:50:36', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1184, 19, 47, '张珊珊', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 9, -1, 17, '通过打电话获取的线索', '2023-11-30 13:33:51', '2024-05-11 15:50:36', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1185, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 2, 24, 3, '近期在看车', '2023-11-27 20:33:25', '2024-05-13 19:16:38', 2, NULL, NULL);
INSERT INTO `t_clue` VALUES (1186, 7, 47, '张怡然', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 50, 46, 4, 6, 16, '通过打电话获取的线索', '2023-11-30 10:33:51', '2024-05-13 19:16:38', 2, NULL, NULL);
INSERT INTO `t_clue` VALUES (1187, 12, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', NULL, 26, NULL, 9.00, '天津和平', 49, 48, 7, 30, 17, '有购车意向，需要跟踪', '2023-11-15 10:30:00', '2024-05-13 19:16:38', 2, NULL, NULL);
INSERT INTO `t_clue` VALUES (1188, 19, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 50, 48, 6, 24, 17, '近期在看车', '2023-12-27 09:20:21', '2024-05-13 19:16:38', 2, NULL, NULL);
INSERT INTO `t_clue` VALUES (1190, 4, NULL, '我爱你中国', 11, '13223334523', '13223334523', '13223334523', '13223334523@qq.com', 12, '术士', 11.00, '吩咐的事都是v', 49, 46, 8, 24, 23, '二粉色粉色粉色', '2024-05-14 18:00:32', '2024-05-14 18:00:34', 4, NULL, NULL);
INSERT INTO `t_clue` VALUES (1191, 4, 47, '挖的', 5, '13321222222', '13321222222', '13321222222', '13321222222@qq.com', 11, '你好', 11.00, '哇发发', 49, 46, 1, -1, 25, '而非发生的VCD', '2024-05-14 18:01:56', '2024-05-14 18:01:57', 4, NULL, NULL);
INSERT INTO `t_clue` VALUES (1192, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 2, 24, 3, '近期在看车', '2023-11-27 20:33:25', '2024-05-18 19:10:02', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1193, 7, 47, '张怡然', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 50, 46, 4, 6, 16, '通过打电话获取的线索', '2023-11-30 10:33:51', '2024-05-18 19:10:02', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1194, 12, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', NULL, 26, NULL, 9.00, '天津和平', 49, 48, 7, 30, 17, '有购车意向，需要跟踪', '2023-11-15 10:30:00', '2024-05-18 19:10:02', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1195, 19, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 50, 48, 6, 24, 17, '近期在看车', '2023-12-27 09:20:21', '2024-05-18 19:10:02', 1, NULL, NULL);
INSERT INTO `t_clue` VALUES (1196, 19, 47, '张珊珊', 41, '13700000001', '13700000001', NULL, NULL, 28, NULL, 8.00, '河北廊坊', 49, 47, 9, 6, 17, '通过打电话获取的线索', '2023-11-30 13:33:51', '2024-05-18 19:10:02', 1, NULL, NULL);

-- ----------------------------
-- Table structure for t_clue_remark
-- ----------------------------
DROP TABLE IF EXISTS `t_clue_remark`;
CREATE TABLE `t_clue_remark`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，线索备注ID',
  `clue_id` int NULL DEFAULT NULL COMMENT '线索ID',
  `note_way` int NULL DEFAULT NULL COMMENT '跟踪方式',
  `note_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '跟踪内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '跟踪时间',
  `create_by` int NULL DEFAULT NULL COMMENT '跟踪人',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '编辑人',
  `deleted` int NULL DEFAULT NULL COMMENT '删除状态（0正常，1删除）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_by`(`create_by` ASC) USING BTREE,
  INDEX `edit_by`(`edit_by` ASC) USING BTREE,
  INDEX `clue_id`(`clue_id` ASC) USING BTREE,
  INDEX `t_clue_remark_ibfk_4`(`note_way` ASC) USING BTREE,
  CONSTRAINT `t_clue_remark_ibfk_1` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_remark_ibfk_2` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_remark_ibfk_3` FOREIGN KEY (`clue_id`) REFERENCES `t_clue` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_clue_remark_ibfk_4` FOREIGN KEY (`note_way`) REFERENCES `t_dic_value` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '线索跟踪记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_clue_remark
-- ----------------------------
INSERT INTO `t_clue_remark` VALUES (5, 5, 65, '2143242354', '2023-04-28 14:24:27', 1, '2024-05-11 15:30:13', 1, NULL);
INSERT INTO `t_clue_remark` VALUES (6, 10, 64, '123412312312', '2023-04-28 14:29:41', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (7, 10, 63, '二位绕弯儿翁人', '2023-04-28 14:30:16', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (8, 21, 61, '12213123123', '2023-05-21 23:43:48', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (9, 21, 62, '3ewrwerewr', '2023-05-21 23:43:52', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (10, 21, 65, 'ewrwerewrewr', '2023-05-21 23:43:55', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (11, 16, 64, '123213123', '2023-05-21 23:54:57', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (12, 16, 63, '23123123', '2023-05-21 23:54:59', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (13, 16, 61, '2312313', '2023-05-21 23:55:02', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (14, 1, 62, NULL, '2023-06-27 22:47:49', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (15, 1, 65, '111111111111111', '2023-06-27 22:47:56', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (16, 1, 64, '1111111111111111111111222222222222222222222222', '2023-06-27 22:48:01', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (23, 1, 64, '用户希望有优惠卷', '2024-05-10 17:27:43', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (24, 24, 61, '达娃大', '2024-05-10 17:58:23', 1, '2024-05-11 15:35:35', 1, NULL);
INSERT INTO `t_clue_remark` VALUES (25, 25, 65, '发放挖坟安抚', '2024-05-10 18:05:08', 1, '2024-05-11 15:35:39', 1, NULL);
INSERT INTO `t_clue_remark` VALUES (26, 26, 62, '俗话u', '2024-05-10 18:05:37', 1, '2024-05-11 15:38:04', 1, NULL);
INSERT INTO `t_clue_remark` VALUES (27, 27, 65, '没办法了', '2024-05-10 18:05:50', 1, '2024-05-11 15:35:45', 1, NULL);
INSERT INTO `t_clue_remark` VALUES (28, 1, 62, '分色法色法', '2024-05-10 18:38:38', 2, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (29, 3, 61, '你找得到我吗', '2024-05-10 18:43:48', 2, NULL, NULL, 1);
INSERT INTO `t_clue_remark` VALUES (30, 3, 62, '找到你啦', '2024-05-10 18:46:33', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (31, 3, 62, '大大大', '2024-05-10 18:46:37', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (32, 1172, 62, '不错', '2024-05-10 22:04:01', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (33, 1169, 62, '删不掉的', '2024-05-10 22:12:21', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (34, 1169, NULL, '所属', '2024-05-10 22:12:23', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (35, 18, 62, '好好好', '2024-05-10 22:48:44', 2, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (36, 3, NULL, NULL, '2024-05-10 22:51:42', 2, NULL, NULL, 1);
INSERT INTO `t_clue_remark` VALUES (37, 3, NULL, '的色法色法色法', '2024-05-10 23:04:46', 2, NULL, NULL, 1);
INSERT INTO `t_clue_remark` VALUES (38, 3, 62, NULL, '2024-05-10 23:04:51', 2, NULL, NULL, 1);
INSERT INTO `t_clue_remark` VALUES (39, 9, 62, 'wdqwd', '2024-05-11 15:40:02', 1, '2024-05-11 15:45:52', 1, 1);
INSERT INTO `t_clue_remark` VALUES (40, 9, 61, '伪装删除达娃大大', '2024-05-11 15:49:34', 1, '2024-05-11 15:49:51', 1, 1);
INSERT INTO `t_clue_remark` VALUES (41, 9, 62, '哇大大伟大', '2024-05-11 15:50:06', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (42, 6, 64, '达娃大', '2024-05-11 15:50:45', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (43, 3, 62, '达瓦达瓦是', '2024-05-11 15:50:54', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (44, 4, NULL, NULL, '2024-05-12 11:04:47', 1, NULL, NULL, 1);
INSERT INTO `t_clue_remark` VALUES (45, 1, 62, '户外地区的', '2024-05-18 17:43:55', 1, NULL, NULL, NULL);
INSERT INTO `t_clue_remark` VALUES (46, 1, 61, '阿达伟大伟大', '2024-05-18 19:09:33', 1, NULL, NULL, 1);

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，客户ID',
  `clue_id` int NULL DEFAULT NULL COMMENT '线索ID',
  `product` int NULL DEFAULT NULL COMMENT '选购产品',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户描述',
  `next_contact_time` datetime NULL DEFAULT NULL COMMENT '下次联系时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建人',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '编辑人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_customer_ibfk_1`(`clue_id` ASC) USING BTREE,
  INDEX `t_customer_ibfk_2`(`product` ASC) USING BTREE,
  INDEX `t_customer_ibfk_3`(`create_by` ASC) USING BTREE,
  INDEX `t_customer_ibfk_4`(`edit_by` ASC) USING BTREE,
  CONSTRAINT `t_customer_ibfk_1` FOREIGN KEY (`clue_id`) REFERENCES `t_clue` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_customer_ibfk_2` FOREIGN KEY (`product`) REFERENCES `t_product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_customer_ibfk_3` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_customer_ibfk_4` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '客户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES (1, 10, 3, '2131231', '2023-05-06 15:36:59', NULL, NULL, NULL, NULL);
INSERT INTO `t_customer` VALUES (2, 8, 6, '124气味儿群', '2023-05-05 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `t_customer` VALUES (3, 6, 6, '1232强21312', '2023-05-06 00:00:00', '2023-04-28 15:42:15', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (4, 2, 3, '阿萨的人', '2023-05-05 00:00:00', '2023-04-28 15:54:04', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (5, 10, 1, '234234', '2023-05-05 00:00:00', '2023-04-28 15:56:44', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (6, 17, 2, '是的啊所大', '2023-05-05 00:00:00', '2023-04-28 15:59:33', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (7, 5, 2, '12312312', '2023-05-12 00:00:00', '2023-05-04 10:03:05', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (8, 19, 2, '气味儿群翁', '2023-05-04 10:03:18', '2023-05-04 10:03:20', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (9, 14, 4, '沃尔沃二', '2023-06-09 00:00:00', '2023-05-04 10:03:39', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (10, 23, 4, '沃尔沃二翁', '2023-05-23 00:00:00', '2023-05-04 10:03:53', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (11, 13, 2, '沃尔沃二', '2023-05-13 00:00:00', '2023-05-04 10:04:03', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (12, NULL, NULL, '近期在看车', '2023-04-27 10:33:47', '2024-05-11 17:31:07', 1, '2024-05-10 22:03:35', 1);
INSERT INTO `t_customer` VALUES (13, 8, 2, '爱你的哇青蛙大王大大', '2024-05-05 00:00:00', '2024-05-11 17:35:57', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (14, 3, 2, '关键词v回复修改下', '2024-05-20 00:00:00', '2024-05-11 17:54:09', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (15, 1179, 2, '尽快改变话剧干嘛·', '2024-05-07 00:00:00', '2024-05-11 17:55:33', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (16, 1184, 3, 'tydthdcrfg', '2024-05-23 00:00:00', '2024-05-12 17:00:29', 1, NULL, NULL);
INSERT INTO `t_customer` VALUES (17, 1172, 12, '针对打的费安抚', '2024-05-29 00:00:00', '2024-05-18 12:24:03', 1, NULL, NULL);

-- ----------------------------
-- Table structure for t_customer_remark
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_remark`;
CREATE TABLE `t_customer_remark`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，客户备注ID',
  `customer_id` int NULL DEFAULT NULL COMMENT '客户ID',
  `note_way` int NULL DEFAULT NULL COMMENT '跟踪方式',
  `note_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '跟踪内容',
  `create_by` int NULL DEFAULT NULL COMMENT '跟踪人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '跟踪时间',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '编辑人',
  `deleted` int NULL DEFAULT NULL COMMENT '删除状态（0正常，1删除）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_customer_remark_ibfk_1`(`customer_id` ASC) USING BTREE,
  INDEX `t_customer_remark_ibfk_2`(`note_way` ASC) USING BTREE,
  INDEX `t_customer_remark_ibfk_3`(`create_by` ASC) USING BTREE,
  INDEX `t_customer_remark_ibfk_4`(`edit_by` ASC) USING BTREE,
  CONSTRAINT `t_customer_remark_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_customer_remark_ibfk_2` FOREIGN KEY (`note_way`) REFERENCES `t_dic_value` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_customer_remark_ibfk_3` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_customer_remark_ibfk_4` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '客户跟踪记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_customer_remark
-- ----------------------------
INSERT INTO `t_customer_remark` VALUES (1, 10, 65, '1111111111111111', 1, '2023-05-04 15:25:51', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (2, 10, 64, '2222222222222', 1, '2023-05-04 15:28:13', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (3, 10, 63, 'EREWREWRWRWR', 1, '2023-05-04 16:21:03', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (4, 7, 61, '13213123', 1, '2023-05-17 17:36:16', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (5, 10, 62, '2342423423423', 1, '2023-05-17 17:36:33', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (6, 1, 65, '3212321321', 1, '2023-05-21 23:50:42', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (7, 1, 64, 'eqwewqeqwe', 1, '2023-05-21 23:50:46', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (8, 1, 63, 'wqeqwewqeqw', 1, '2023-05-21 23:50:48', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (9, 8, 61, 'ewqeqweq', 1, '2023-05-21 23:50:59', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (10, 5, 62, 'weqweqweqweq', 1, '2023-05-21 23:51:05', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (11, 5, 65, 'weqwewqeqw', 1, '2023-05-21 23:51:08', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (12, 5, 64, '23213213123', 1, '2023-05-21 23:51:13', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (13, 5, 65, '23123213213', 1, '2023-05-21 23:51:16', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (14, 5, 64, '23123213213', 1, '2023-05-21 23:51:19', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (15, 5, 63, '3232323123', 1, '2023-05-21 23:51:23', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (16, 1, 61, '12312321321', 1, '2023-05-21 23:56:55', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (17, 2, 62, '1232131231', 1, '2023-05-21 23:57:03', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (18, 10, 65, NULL, 1, '2023-05-22 22:12:52', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (19, 10, 64, '123213214124', 1, '2023-05-22 22:12:58', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (20, 10, 63, '13241242432432', 1, '2023-05-22 22:13:03', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (21, 10, 61, '3423423423', 1, '2023-05-22 22:13:06', NULL, NULL, NULL);
INSERT INTO `t_customer_remark` VALUES (22, 2, 62, '1242412141', 1, '2023-05-31 20:14:08', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_dic_type
-- ----------------------------
DROP TABLE IF EXISTS `t_dic_type`;
CREATE TABLE `t_dic_type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，字典类型ID',
  `type_code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '字典类型代码',
  `type_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '字典类型名称',
  `remark` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `code`(`type_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_dic_type
-- ----------------------------
INSERT INTO `t_dic_type` VALUES (1, 'sex', '性别', NULL);
INSERT INTO `t_dic_type` VALUES (2, 'appellation', '称呼', NULL);
INSERT INTO `t_dic_type` VALUES (3, 'clueState', '线索状态', NULL);
INSERT INTO `t_dic_type` VALUES (4, 'returnPriority', '回访优先级', NULL);
INSERT INTO `t_dic_type` VALUES (5, 'returnState', '回访状态', NULL);
INSERT INTO `t_dic_type` VALUES (6, 'source', '来源', NULL);
INSERT INTO `t_dic_type` VALUES (7, 'stage', '阶段', NULL);
INSERT INTO `t_dic_type` VALUES (8, 'transactionType', '交易类型', NULL);
INSERT INTO `t_dic_type` VALUES (9, 'intentionState', '意向状态', NULL);
INSERT INTO `t_dic_type` VALUES (10, 'needLoan', '是否贷款', NULL);
INSERT INTO `t_dic_type` VALUES (11, 'educational', '学历', NULL);
INSERT INTO `t_dic_type` VALUES (12, 'userState', '用户状态', NULL);
INSERT INTO `t_dic_type` VALUES (13, 'noteWay', '跟踪方式', NULL);

-- ----------------------------
-- Table structure for t_dic_value
-- ----------------------------
DROP TABLE IF EXISTS `t_dic_value`;
CREATE TABLE `t_dic_value`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，字典值ID',
  `type_code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '字典类型代码',
  `type_value` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `order` int NULL DEFAULT NULL COMMENT '字典值排序',
  `remark` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_dic_value_ibfk_1`(`type_code` ASC) USING BTREE,
  CONSTRAINT `t_dic_value_ibfk_1` FOREIGN KEY (`type_code`) REFERENCES `t_dic_type` (`type_code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '字典值表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_dic_value
-- ----------------------------
INSERT INTO `t_dic_value` VALUES (-1, 'clueState', '已转客户', 0, NULL);
INSERT INTO `t_dic_value` VALUES (1, 'clueState', '虚假线索', 4, NULL);
INSERT INTO `t_dic_value` VALUES (2, 'source', '知乎', 8, NULL);
INSERT INTO `t_dic_value` VALUES (3, 'source', '车展会', 11, NULL);
INSERT INTO `t_dic_value` VALUES (4, 'returnPriority', '最高', 2, NULL);
INSERT INTO `t_dic_value` VALUES (5, 'appellation', '教授', 5, NULL);
INSERT INTO `t_dic_value` VALUES (6, 'clueState', '将来联系', 2, NULL);
INSERT INTO `t_dic_value` VALUES (7, 'clueState', '丢失线索', 5, NULL);
INSERT INTO `t_dic_value` VALUES (8, 'returnState', '未启动', 1, NULL);
INSERT INTO `t_dic_value` VALUES (10, 'clueState', '试图联系', 1, NULL);
INSERT INTO `t_dic_value` VALUES (11, 'appellation', '博士', 4, NULL);
INSERT INTO `t_dic_value` VALUES (12, 'stage', '01创建交易', 1, NULL);
INSERT INTO `t_dic_value` VALUES (14, 'source', '汽车之家', 14, NULL);
INSERT INTO `t_dic_value` VALUES (15, 'returnPriority', '低', 3, NULL);
INSERT INTO `t_dic_value` VALUES (16, 'source', '网络广告', 1, NULL);
INSERT INTO `t_dic_value` VALUES (17, 'source', '视频直播', 9, NULL);
INSERT INTO `t_dic_value` VALUES (18, 'appellation', '先生', 1, NULL);
INSERT INTO `t_dic_value` VALUES (19, 'returnPriority', '高', 1, NULL);
INSERT INTO `t_dic_value` VALUES (20, 'appellation', '夫人', 2, NULL);
INSERT INTO `t_dic_value` VALUES (21, 'stage', '06丢失关闭', 7, NULL);
INSERT INTO `t_dic_value` VALUES (22, 'source', '地图', 13, NULL);
INSERT INTO `t_dic_value` VALUES (23, 'source', '合作伙伴', 6, NULL);
INSERT INTO `t_dic_value` VALUES (24, 'clueState', '未联系', 6, NULL);
INSERT INTO `t_dic_value` VALUES (25, 'source', '朋友圈', 10, NULL);
INSERT INTO `t_dic_value` VALUES (26, 'returnState', '进行中', 3, NULL);
INSERT INTO `t_dic_value` VALUES (27, 'clueState', '已联系', 3, NULL);
INSERT INTO `t_dic_value` VALUES (28, 'returnState', '推迟', 2, NULL);
INSERT INTO `t_dic_value` VALUES (29, 'returnState', '完成', 4, NULL);
INSERT INTO `t_dic_value` VALUES (30, 'clueState', '需要条件', 7, NULL);
INSERT INTO `t_dic_value` VALUES (32, 'returnState', '等待某人', 5, NULL);
INSERT INTO `t_dic_value` VALUES (33, 'source', '懂车帝', 2, NULL);
INSERT INTO `t_dic_value` VALUES (34, 'returnPriority', '常规', 5, NULL);
INSERT INTO `t_dic_value` VALUES (35, 'stage', '04产品检验', 5, NULL);
INSERT INTO `t_dic_value` VALUES (36, 'source', '易车网', 12, NULL);
INSERT INTO `t_dic_value` VALUES (37, 'stage', '02确认清单', 3, NULL);
INSERT INTO `t_dic_value` VALUES (38, 'returnPriority', '最低', 4, NULL);
INSERT INTO `t_dic_value` VALUES (39, 'source', '员工介绍', 3, NULL);
INSERT INTO `t_dic_value` VALUES (40, 'stage', '03交付定金', 4, NULL);
INSERT INTO `t_dic_value` VALUES (41, 'appellation', '女士', 3, NULL);
INSERT INTO `t_dic_value` VALUES (42, 'stage', '05付款成交', 6, NULL);
INSERT INTO `t_dic_value` VALUES (43, 'source', '官方网站', 5, NULL);
INSERT INTO `t_dic_value` VALUES (44, 'source', '公众号', 7, NULL);
INSERT INTO `t_dic_value` VALUES (45, 'source', '门店参观', 4, NULL);
INSERT INTO `t_dic_value` VALUES (46, 'intentionState', '有意向', 1, NULL);
INSERT INTO `t_dic_value` VALUES (47, 'intentionState', '无意向', 2, NULL);
INSERT INTO `t_dic_value` VALUES (48, 'intentionState', '意向不明', 3, NULL);
INSERT INTO `t_dic_value` VALUES (49, 'needLoan', '需要', 1, NULL);
INSERT INTO `t_dic_value` VALUES (50, 'needLoan', '不需要', 2, NULL);
INSERT INTO `t_dic_value` VALUES (51, 'sex', '男', 1, NULL);
INSERT INTO `t_dic_value` VALUES (52, 'sex', '女', 2, NULL);
INSERT INTO `t_dic_value` VALUES (53, 'educational', '小学', 1, NULL);
INSERT INTO `t_dic_value` VALUES (54, 'educational', '初中', 2, NULL);
INSERT INTO `t_dic_value` VALUES (55, 'educational', '高中', 3, NULL);
INSERT INTO `t_dic_value` VALUES (56, 'educational', '大学', 4, NULL);
INSERT INTO `t_dic_value` VALUES (57, 'educational', '研究生', 5, NULL);
INSERT INTO `t_dic_value` VALUES (58, 'userState', '正常', 1, NULL);
INSERT INTO `t_dic_value` VALUES (59, 'userState', '锁定', 2, NULL);
INSERT INTO `t_dic_value` VALUES (60, 'userState', '禁用', 3, NULL);
INSERT INTO `t_dic_value` VALUES (61, 'noteWay', '电话', 1, NULL);
INSERT INTO `t_dic_value` VALUES (62, 'noteWay', '微信', 2, NULL);
INSERT INTO `t_dic_value` VALUES (63, 'noteWay', 'QQ', 3, NULL);
INSERT INTO `t_dic_value` VALUES (64, 'noteWay', '面聊', 4, NULL);
INSERT INTO `t_dic_value` VALUES (65, 'noteWay', '其他', 5, NULL);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parent_id` int NULL DEFAULT NULL,
  `order_no` int NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1114 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '市场活动', NULL, NULL, 'menu', 0, 1, 'OfficeBuilding');
INSERT INTO `t_permission` VALUES (2, '市场活动', NULL, '/dashboard/activity', 'menu', 1, 1, 'CreditCard');
INSERT INTO `t_permission` VALUES (3, '市场活动-列表', 'activity:list', NULL, 'button', 2, NULL, NULL);
INSERT INTO `t_permission` VALUES (4, '市场活动-录入', 'activity:add', NULL, 'button', 2, NULL, NULL);
INSERT INTO `t_permission` VALUES (5, '市场活动-编辑', 'activity:edit', NULL, 'button', 2, NULL, NULL);
INSERT INTO `t_permission` VALUES (6, '市场活动-查看', 'activity:view', NULL, 'button', 2, NULL, NULL);
INSERT INTO `t_permission` VALUES (7, '市场活动-删除', 'activity:delete', NULL, 'button', 2, NULL, NULL);
INSERT INTO `t_permission` VALUES (9, '市场活动-搜索', 'activity:search', NULL, 'button', 2, NULL, NULL);
INSERT INTO `t_permission` VALUES (10, '线索管理', NULL, NULL, 'menu', 0, 2, 'Magnet');
INSERT INTO `t_permission` VALUES (12, '线索管理', NULL, '/dashboard/clue', 'menu', 10, 1, 'Paperclip');
INSERT INTO `t_permission` VALUES (13, '线索管理-列表', 'clue:list', NULL, 'button', 12, NULL, NULL);
INSERT INTO `t_permission` VALUES (14, '线索管理-录入', 'clue:add', NULL, 'button', 12, NULL, NULL);
INSERT INTO `t_permission` VALUES (15, '线索管理-编辑', 'clue:edit', NULL, 'button', 12, NULL, NULL);
INSERT INTO `t_permission` VALUES (16, '线索管理-查看', 'clue:view', NULL, 'button', 12, NULL, NULL);
INSERT INTO `t_permission` VALUES (17, '线索管理-删除', 'clue:delete', NULL, 'button', 12, NULL, NULL);
INSERT INTO `t_permission` VALUES (18, '线索管理-导入', 'clue:import', NULL, 'button', 12, NULL, NULL);
INSERT INTO `t_permission` VALUES (19, '客户管理', NULL, NULL, 'menu', 0, 3, 'User');
INSERT INTO `t_permission` VALUES (20, '客户管理', NULL, '/dashboard/customer', 'menu', 19, 1, 'UserFilled');
INSERT INTO `t_permission` VALUES (21, '客户管理-列表', 'customer:list', NULL, 'button', 20, NULL, NULL);
INSERT INTO `t_permission` VALUES (22, '客户管理-查看', 'customer:view', NULL, 'button', 20, NULL, NULL);
INSERT INTO `t_permission` VALUES (23, '客户管理-导出', 'customer:export', NULL, 'button', 20, NULL, NULL);
INSERT INTO `t_permission` VALUES (24, '交易管理', NULL, NULL, 'menu', 0, 4, 'Wallet');
INSERT INTO `t_permission` VALUES (25, '交易管理', NULL, '/dashboard/tran', 'menu', 24, 1, 'Coin');
INSERT INTO `t_permission` VALUES (26, '交易管理-列表', 'tran:list', NULL, 'button', 25, NULL, NULL);
INSERT INTO `t_permission` VALUES (27, '交易管理-查看', 'tran:view', NULL, 'button', 25, NULL, NULL);
INSERT INTO `t_permission` VALUES (28, '产品管理', NULL, NULL, 'menu', 0, 5, 'Memo');
INSERT INTO `t_permission` VALUES (29, '产品管理', NULL, '/dashboard/product', 'menu', 28, 1, 'SetUp');
INSERT INTO `t_permission` VALUES (30, '产品管理-列表', 'product:list', NULL, 'button', 29, NULL, NULL);
INSERT INTO `t_permission` VALUES (31, '产品管理-录入', 'product:add', NULL, 'button', 29, NULL, NULL);
INSERT INTO `t_permission` VALUES (32, '产品管理-编辑', 'product:edit', NULL, 'button', 29, NULL, NULL);
INSERT INTO `t_permission` VALUES (33, '产品管理-查看', 'product:view', NULL, 'button', 29, NULL, NULL);
INSERT INTO `t_permission` VALUES (34, '产品管理-删除', 'product:delete', NULL, 'button', 29, NULL, NULL);
INSERT INTO `t_permission` VALUES (35, '字典管理', NULL, NULL, 'menu', 0, 6, 'Grid');
INSERT INTO `t_permission` VALUES (36, '字典类型', NULL, '/dashboard/dictype', 'menu', 35, 1, 'Postcard');
INSERT INTO `t_permission` VALUES (37, '字典类型-列表', 'dictype:list', NULL, 'button', 36, NULL, NULL);
INSERT INTO `t_permission` VALUES (38, '字典类型-录入', 'dictype:add', NULL, 'button', 36, NULL, NULL);
INSERT INTO `t_permission` VALUES (39, '字典类型-编辑', 'dictype:edit', NULL, 'button', 36, NULL, NULL);
INSERT INTO `t_permission` VALUES (40, '字典类型-查看', 'dictype:view', NULL, 'button', 36, NULL, NULL);
INSERT INTO `t_permission` VALUES (41, '字典类型-删除', 'dictype:delete', NULL, 'button', 36, NULL, NULL);
INSERT INTO `t_permission` VALUES (42, '字典数据', '', '/dashboard/dicvalue', 'menu', 35, 2, 'DataAnalysis');
INSERT INTO `t_permission` VALUES (43, '字典数据-列表', 'dicvalue:list', NULL, 'button', 42, NULL, NULL);
INSERT INTO `t_permission` VALUES (44, '字典数据-录入', 'dicvalue:add', NULL, 'button', 42, NULL, NULL);
INSERT INTO `t_permission` VALUES (45, '字典数据-编辑', 'dicvalue:edit', NULL, 'button', 42, NULL, NULL);
INSERT INTO `t_permission` VALUES (46, '字典数据-查看', 'dicvalue:view', NULL, 'button', 42, NULL, NULL);
INSERT INTO `t_permission` VALUES (47, '字典数据-删除', 'dicvalue:delete', NULL, 'button', 42, NULL, NULL);
INSERT INTO `t_permission` VALUES (48, '用户管理', NULL, NULL, 'menu', 0, 7, 'Stamp');
INSERT INTO `t_permission` VALUES (49, '用户管理', NULL, '/dashboard/user', 'menu', 48, 1, 'User');
INSERT INTO `t_permission` VALUES (50, '用户管理-列表', 'user:list', NULL, 'button', 49, NULL, NULL);
INSERT INTO `t_permission` VALUES (51, '用户管理-录入', 'user:add', NULL, 'button', 49, NULL, NULL);
INSERT INTO `t_permission` VALUES (52, '用户管理-编辑', 'user:edit', NULL, 'button', 49, NULL, NULL);
INSERT INTO `t_permission` VALUES (53, '用户管理-查看', 'user:view', NULL, 'button', 49, NULL, NULL);
INSERT INTO `t_permission` VALUES (54, '用户管理-删除', 'user:delete', NULL, 'button', 49, NULL, NULL);
INSERT INTO `t_permission` VALUES (55, '系统管理', NULL, NULL, 'menu', 0, 8, 'Setting');
INSERT INTO `t_permission` VALUES (56, '系统管理', NULL, '/dashboard/system', 'menu', 55, 1, 'Tools');
INSERT INTO `t_permission` VALUES (57, '系统管理-列表', 'system:list', NULL, 'button', 56, NULL, NULL);
INSERT INTO `t_permission` VALUES (58, '系统管理-录入', 'system:add', NULL, 'button', 56, NULL, NULL);
INSERT INTO `t_permission` VALUES (59, '系统管理-编辑', 'system:edit', NULL, 'button', 56, NULL, NULL);
INSERT INTO `t_permission` VALUES (60, '系统管理-查看', 'system:view', NULL, 'button', 56, NULL, NULL);
INSERT INTO `t_permission` VALUES (61, '系统管理-删除', 'system:delete', NULL, 'button', 56, NULL, NULL);
INSERT INTO `t_permission` VALUES (62, '智慧对话', NULL, NULL, 'menu', 0, 9, 'ChatLineRound');
INSERT INTO `t_permission` VALUES (63, '智慧对话', '', '/dashboard/aiChat', 'menu', 62, NULL, 'Comment');
INSERT INTO `t_permission` VALUES (64, '智慧对话', 'ai:chat', '', 'button', 63, NULL, NULL);

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，线索ID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `guide_price_s` decimal(10, 2) NULL DEFAULT NULL COMMENT '官方指导起始价',
  `guide_price_e` decimal(10, 2) NULL DEFAULT NULL COMMENT '官方指导最高价',
  `quotation` decimal(10, 2) NULL DEFAULT NULL COMMENT '经销商报价',
  `state` int NULL DEFAULT NULL COMMENT '状态 0在售 1售罄',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建人',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '编辑人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_product_ibfk_1`(`create_by` ASC) USING BTREE,
  INDEX `t_product_ibfk_2`(`edit_by` ASC) USING BTREE,
  CONSTRAINT `t_product_ibfk_1` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_product_ibfk_2` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (1, '海鸥', 10.18, 10.58, 9.28, 0, '2023-04-06 18:25:00', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (2, '比亚迪e2', 10.28, 10.98, 9.78, 0, '2023-04-03 15:26:12', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (3, '比亚迪e3', 15.48, 15.98, 14.38, 0, '2023-04-03 11:29:08', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (4, '海豚', 11.68, 13.68, 10.86, 0, '2023-04-09 10:27:47', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (5, '秦EV', 12.99, 16.98, 11.98, 0, '2023-04-08 15:28:23', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (6, '秦PLUS DM-i', 9.98, 16.58, 9.06, 0, '2023-04-10 19:29:53', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (7, '秦PLUS EV', 12.98, 18.08, 12.38, 0, '2023-04-05 09:30:31', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (8, '海豹', 21.28, 28.98, 20.18, 0, '2023-04-02 10:31:08', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (9, '汉DM', 21.78, 32.18, 19.88, 0, '2023-04-07 16:31:45', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (10, '宋PLUS EV', 18.68, 20.38, 17.86, 0, '2023-03-18 21:33:08', 1, NULL, NULL);
INSERT INTO `t_product` VALUES (11, '比亚迪汉', 13.88, 15.99, 12.89, 1, '2024-05-18 12:16:43', 2, NULL, NULL);
INSERT INTO `t_product` VALUES (12, '迫击炮', 222.33, 234.34, 213.44, 0, '2024-06-07 12:23:05', 2, NULL, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin', '管理员');
INSERT INTO `t_role` VALUES (2, 'saler', '销售员');
INSERT INTO `t_role` VALUES (3, 'manager', '销售经理');
INSERT INTO `t_role` VALUES (4, 'marketing ', '市场营销');
INSERT INTO `t_role` VALUES (5, 'accountant', '会计');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL,
  `permission_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_role_permission_ibfk_1`(`role_id` ASC) USING BTREE,
  INDEX `t_role_permission_ibfk_2`(`permission_id` ASC) USING BTREE,
  CONSTRAINT `t_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1, 1);
INSERT INTO `t_role_permission` VALUES (2, 1, 2);
INSERT INTO `t_role_permission` VALUES (3, 1, 3);
INSERT INTO `t_role_permission` VALUES (4, 1, 4);
INSERT INTO `t_role_permission` VALUES (5, 1, 5);
INSERT INTO `t_role_permission` VALUES (6, 1, 6);
INSERT INTO `t_role_permission` VALUES (7, 1, 7);
INSERT INTO `t_role_permission` VALUES (8, 1, 9);
INSERT INTO `t_role_permission` VALUES (9, 1, 10);
INSERT INTO `t_role_permission` VALUES (10, 1, 12);
INSERT INTO `t_role_permission` VALUES (11, 1, 13);
INSERT INTO `t_role_permission` VALUES (12, 1, 14);
INSERT INTO `t_role_permission` VALUES (13, 1, 15);
INSERT INTO `t_role_permission` VALUES (14, 1, 16);
INSERT INTO `t_role_permission` VALUES (15, 1, 17);
INSERT INTO `t_role_permission` VALUES (16, 1, 18);
INSERT INTO `t_role_permission` VALUES (17, 1, 19);
INSERT INTO `t_role_permission` VALUES (18, 1, 20);
INSERT INTO `t_role_permission` VALUES (19, 1, 21);
INSERT INTO `t_role_permission` VALUES (20, 1, 22);
INSERT INTO `t_role_permission` VALUES (21, 1, 23);
INSERT INTO `t_role_permission` VALUES (22, 1, 24);
INSERT INTO `t_role_permission` VALUES (23, 1, 25);
INSERT INTO `t_role_permission` VALUES (24, 1, 26);
INSERT INTO `t_role_permission` VALUES (25, 1, 27);
INSERT INTO `t_role_permission` VALUES (26, 1, 28);
INSERT INTO `t_role_permission` VALUES (27, 1, 29);
INSERT INTO `t_role_permission` VALUES (28, 1, 30);
INSERT INTO `t_role_permission` VALUES (29, 1, 31);
INSERT INTO `t_role_permission` VALUES (30, 1, 32);
INSERT INTO `t_role_permission` VALUES (31, 1, 33);
INSERT INTO `t_role_permission` VALUES (32, 1, 34);
INSERT INTO `t_role_permission` VALUES (33, 1, 35);
INSERT INTO `t_role_permission` VALUES (34, 1, 36);
INSERT INTO `t_role_permission` VALUES (35, 1, 37);
INSERT INTO `t_role_permission` VALUES (36, 1, 38);
INSERT INTO `t_role_permission` VALUES (37, 1, 39);
INSERT INTO `t_role_permission` VALUES (38, 1, 40);
INSERT INTO `t_role_permission` VALUES (39, 1, 41);
INSERT INTO `t_role_permission` VALUES (40, 1, 42);
INSERT INTO `t_role_permission` VALUES (41, 1, 43);
INSERT INTO `t_role_permission` VALUES (42, 1, 44);
INSERT INTO `t_role_permission` VALUES (43, 1, 45);
INSERT INTO `t_role_permission` VALUES (44, 1, 46);
INSERT INTO `t_role_permission` VALUES (45, 1, 47);
INSERT INTO `t_role_permission` VALUES (46, 1, 48);
INSERT INTO `t_role_permission` VALUES (47, 1, 49);
INSERT INTO `t_role_permission` VALUES (48, 1, 50);
INSERT INTO `t_role_permission` VALUES (49, 1, 51);
INSERT INTO `t_role_permission` VALUES (50, 1, 52);
INSERT INTO `t_role_permission` VALUES (51, 1, 53);
INSERT INTO `t_role_permission` VALUES (52, 1, 54);
INSERT INTO `t_role_permission` VALUES (53, 1, 55);
INSERT INTO `t_role_permission` VALUES (54, 1, 56);
INSERT INTO `t_role_permission` VALUES (55, 1, 57);
INSERT INTO `t_role_permission` VALUES (56, 1, 58);
INSERT INTO `t_role_permission` VALUES (57, 1, 59);
INSERT INTO `t_role_permission` VALUES (58, 1, 60);
INSERT INTO `t_role_permission` VALUES (59, 1, 61);
INSERT INTO `t_role_permission` VALUES (60, 2, 10);
INSERT INTO `t_role_permission` VALUES (61, 2, 12);
INSERT INTO `t_role_permission` VALUES (62, 2, 13);
INSERT INTO `t_role_permission` VALUES (63, 2, 14);
INSERT INTO `t_role_permission` VALUES (64, 2, 15);
INSERT INTO `t_role_permission` VALUES (65, 2, 16);
INSERT INTO `t_role_permission` VALUES (66, 2, 57);
INSERT INTO `t_role_permission` VALUES (67, 2, 18);
INSERT INTO `t_role_permission` VALUES (68, 2, 19);
INSERT INTO `t_role_permission` VALUES (69, 2, 20);
INSERT INTO `t_role_permission` VALUES (70, 2, 21);
INSERT INTO `t_role_permission` VALUES (71, 2, 22);
INSERT INTO `t_role_permission` VALUES (72, 2, 23);
INSERT INTO `t_role_permission` VALUES (73, 2, 24);
INSERT INTO `t_role_permission` VALUES (74, 2, 25);
INSERT INTO `t_role_permission` VALUES (75, 2, 26);
INSERT INTO `t_role_permission` VALUES (76, 2, 27);
INSERT INTO `t_role_permission` VALUES (77, 3, 1);
INSERT INTO `t_role_permission` VALUES (78, 3, 2);
INSERT INTO `t_role_permission` VALUES (79, 1, 62);
INSERT INTO `t_role_permission` VALUES (80, 1, 63);
INSERT INTO `t_role_permission` VALUES (81, 3, 3);
INSERT INTO `t_role_permission` VALUES (82, 3, 4);
INSERT INTO `t_role_permission` VALUES (85, 3, 7);
INSERT INTO `t_role_permission` VALUES (86, 3, 9);
INSERT INTO `t_role_permission` VALUES (87, 3, 13);
INSERT INTO `t_role_permission` VALUES (88, 3, 14);
INSERT INTO `t_role_permission` VALUES (89, 3, 10);
INSERT INTO `t_role_permission` VALUES (90, 3, 12);
INSERT INTO `t_role_permission` VALUES (91, 3, 16);
INSERT INTO `t_role_permission` VALUES (95, 3, 48);
INSERT INTO `t_role_permission` VALUES (96, 3, 49);
INSERT INTO `t_role_permission` VALUES (97, 3, 54);
INSERT INTO `t_role_permission` VALUES (99, 3, 50);
INSERT INTO `t_role_permission` VALUES (100, 3, 51);
INSERT INTO `t_role_permission` VALUES (101, 3, 52);
INSERT INTO `t_role_permission` VALUES (102, 3, 53);
INSERT INTO `t_role_permission` VALUES (103, 1, 64);

-- ----------------------------
-- Table structure for t_system_info
-- ----------------------------
DROP TABLE IF EXISTS `t_system_info`;
CREATE TABLE `t_system_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `system_code` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `site` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `logo` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `title` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `keywords` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `shortcuticon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `tel` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `weixin` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `version` varchar(145) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `closeMsg` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `isopen` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'y',
  `create_time` datetime NULL DEFAULT NULL,
  `create_by` int NULL DEFAULT NULL,
  `edit_time` datetime NULL DEFAULT NULL,
  `edit_by` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_system_info_ibfk_1`(`create_by` ASC) USING BTREE,
  INDEX `t_system_info_ibfk_2`(`edit_by` ASC) USING BTREE,
  CONSTRAINT `t_system_info_ibfk_1` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_system_info_ibfk_2` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '系统信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_system_info
-- ----------------------------
INSERT INTO `t_system_info` VALUES (1, 'crm', '动力云客系统', 'http://192.168.52.102/', 'http://localhost:8080/image/logo.png', '动力云客系统', '动力CRM 企业客户智慧云管理', 'crm, 客户, 客户关系, 客户关系管理', 'http://192.168.52.102/', '010-88997766', '123456789', '123456789@qq.com', '洛阳市伊滨区', '系统版本:1.1.0.bate', 'http://192.168.52.102/', 'true', '2024-05-19 13:28:18', 1, NULL, NULL);
INSERT INTO `t_system_info` VALUES (2, 'call', '动力呼叫系统', 'http://192.168.52.102/', 'http://localhost:8080/image/logo.png', '动力呼叫系统', '暂无描述', 'crm, 客户, 客户关系, 客户关系管理', 'http://192.168.52.102/', '010-88997766', '123456789', '123456789@qq.com', '湖北省黄石市', '系统版本:1.1.0.bate', '网站维护中', 'true', '2024-02-15 13:28:21', 1, NULL, NULL);
INSERT INTO `t_system_info` VALUES (3, 'game', '动力列车游戏', 'http://192.168.52.102:22', NULL, '动力列车游戏', '超级好玩', '游戏，小世界', 'http://192.168.52.102:22', '010-88997766', '123456789', '123456789@gmail.com', '山东省济南市', '系统版本:1.1.0.bate', 'http://192.168.52.102/', 'ture', '2024-05-19 21:45:01', 2, NULL, NULL);

-- ----------------------------
-- Table structure for t_tran
-- ----------------------------
DROP TABLE IF EXISTS `t_tran`;
CREATE TABLE `t_tran`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，交易ID',
  `tran_no` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '交易流水号',
  `customer_id` int NULL DEFAULT NULL COMMENT '客户ID',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '交易金额',
  `expected_date` datetime NULL DEFAULT NULL COMMENT '预计成交日期',
  `stage` int NULL DEFAULT NULL COMMENT '交易所处阶段',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '交易描述',
  `next_contact_time` datetime NULL DEFAULT NULL COMMENT '下次联系时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建人',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '编辑人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_tran_ibfk_1`(`customer_id` ASC) USING BTREE,
  INDEX `t_tran_ibfk_2`(`stage` ASC) USING BTREE,
  INDEX `t_tran_ibfk_3`(`create_by` ASC) USING BTREE,
  INDEX `t_tran_ibfk_4`(`edit_by` ASC) USING BTREE,
  CONSTRAINT `t_tran_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_tran_ibfk_2` FOREIGN KEY (`stage`) REFERENCES `t_dic_value` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_tran_ibfk_3` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_tran_ibfk_4` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '交易表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_tran
-- ----------------------------
INSERT INTO `t_tran` VALUES (5, '202311121932019431', 1, 120000.00, '2023-11-14 00:00:00', 42, '123131', '2023-11-29 00:00:00', '2023-11-12 19:32:02', 1, NULL, NULL);
INSERT INTO `t_tran` VALUES (6, '202311121932019432', 1, 93000.00, '2023-11-12 00:00:00', 12, '123131', '2023-11-29 00:00:00', '2023-11-12 19:32:02', 1, NULL, NULL);

-- ----------------------------
-- Table structure for t_tran_history
-- ----------------------------
DROP TABLE IF EXISTS `t_tran_history`;
CREATE TABLE `t_tran_history`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，交易记录ID',
  `tran_id` int NULL DEFAULT NULL COMMENT '交易ID',
  `stage` int NULL DEFAULT NULL COMMENT '交易阶段',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '交易金额',
  `expected_date` datetime NULL DEFAULT NULL COMMENT '交易预计成交时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_tran_history_ibfk_1`(`tran_id` ASC) USING BTREE,
  INDEX `t_tran_history_ibfk_2`(`stage` ASC) USING BTREE,
  INDEX `t_tran_history_ibfk_3`(`create_by` ASC) USING BTREE,
  CONSTRAINT `t_tran_history_ibfk_1` FOREIGN KEY (`tran_id`) REFERENCES `t_tran` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_tran_history_ibfk_2` FOREIGN KEY (`stage`) REFERENCES `t_dic_value` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_tran_history_ibfk_3` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '交易历史记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_tran_history
-- ----------------------------

-- ----------------------------
-- Table structure for t_tran_remark
-- ----------------------------
DROP TABLE IF EXISTS `t_tran_remark`;
CREATE TABLE `t_tran_remark`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，交易备注ID',
  `tran_id` int NULL DEFAULT NULL COMMENT '交易ID',
  `note_way` int NULL DEFAULT NULL COMMENT '跟踪方式',
  `note_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '跟踪内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '跟踪时间',
  `create_by` int NULL DEFAULT NULL COMMENT '跟踪人',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '编辑人',
  `deleted` int NULL DEFAULT NULL COMMENT '删除状态（0正常，1删除）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_tran_remark_ibfk_1`(`tran_id` ASC) USING BTREE,
  INDEX `t_tran_remark_ibfk_2`(`note_way` ASC) USING BTREE,
  INDEX `t_tran_remark_ibfk_3`(`create_by` ASC) USING BTREE,
  INDEX `t_tran_remark_ibfk_4`(`edit_by` ASC) USING BTREE,
  CONSTRAINT `t_tran_remark_ibfk_1` FOREIGN KEY (`tran_id`) REFERENCES `t_tran` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_tran_remark_ibfk_2` FOREIGN KEY (`note_way`) REFERENCES `t_dic_value` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_tran_remark_ibfk_3` FOREIGN KEY (`create_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_tran_remark_ibfk_4` FOREIGN KEY (`edit_by`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '交易跟踪记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_tran_remark
-- ----------------------------

-- ----------------------------
-- Table structure for t_upgrade_log
-- ----------------------------
DROP TABLE IF EXISTS `t_upgrade_log`;
CREATE TABLE `t_upgrade_log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `upgrade_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `version` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_upgrade_log
-- ----------------------------
INSERT INTO `t_upgrade_log` VALUES (1, '市场活动删除及批量删除功能的完善：系统正在进行相关功能的开发和测试，并将在后续完成。\r\n数据库底层修改：对数据库进行了底层修改以支持级联删除活动表信息。尽管存在少许bug，但无法修改其他表的级联删除能力。\r\n备注删除：备注删除是一种假删除，只做了标记，无法真正删除活动等约束数据。', '2024-05-06 13:00:44', 'Dev 0.0.1');
INSERT INTO `t_upgrade_log` VALUES (2, '左连接和右连接：左连接和右连接是用于查询的两种连接方式，确保左表或右表中的数据都包含在结果中。\r\n界面状态保存问题：在进行增删改查等操作后，返回原先界面时无法记住界面以前的状态，该问题待解决。\r\n数据库表变化与Redis同步：用户数据变化后，Redis未能及时更新负责人列表，需要修改缓存逻辑以保持同步。', '2024-05-16 21:01:38', 'Dev 0.0.2');
INSERT INTO `t_upgrade_log` VALUES (3, '更新AI聊天模块，优化工具类设计，优化常量设计类，优化控制台输出及日志记录，改进axios的全局导入逻辑。', '2024-05-19 21:03:25', 'Dev 0.0.3');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长，用户ID',
  `login_act` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `login_pwd` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户手机',
  `email` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `account_no_expired` int NULL DEFAULT NULL COMMENT '账户是否没有过期，0已过期 1正常',
  `credentials_no_expired` int NULL DEFAULT NULL COMMENT '密码是否没有过期，0已过期 1正常',
  `account_no_locked` int NULL DEFAULT NULL COMMENT '账号是否没有锁定，0已锁定 1正常',
  `account_enabled` int NULL DEFAULT NULL COMMENT '账号是否启用，0禁用 1启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int NULL DEFAULT NULL COMMENT '创建人',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  `edit_by` int NULL DEFAULT NULL COMMENT '编辑人',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_act`(`login_act` ASC) USING BTREE,
  UNIQUE INDEX `phone`(`phone` ASC) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '$2a$10$Nlhwhtd0BSCBK95CAifv7eWpCjHloPBMZ3Gaehcc56hRAV3DZALJO', '管理员', '13700000000', 'admin@qq.com', 1, 1, 1, 1, '2023-02-22 09:37:12', 1, '2024-05-18 18:07:05', 1, '2023-12-10 21:18:59');
INSERT INTO `t_user` VALUES (2, 'yuyan', '$2a$10$q.UcRZsrAMuKvaBAgRfm8eplvxazr7ilegDYCIDwZG97NWcfVb/oS', '于嫣', '13213349698', 'yuyaner@163.com', 1, 1, 1, 1, '2023-02-28 12:11:40', NULL, '2024-05-18 18:06:37', 1, '2023-11-29 20:14:31');
INSERT INTO `t_user` VALUES (3, 'zhangqi', '$2a$10$Q0qTW6QqkabTzFyoilViw..YdrVzZkSKe5RvLmjgPgW/IrcPkBoF.', '张琪', '1362362323', 'zhangqi@qq.com', 1, 1, 1, 1, '2023-03-02 11:37:34', NULL, '2023-05-23 00:21:02', NULL, NULL);
INSERT INTO `t_user` VALUES (4, 'suwanting', '$2a$10$3bambNLTCAKtQn2OXPiHb.f0SzH.MucTiLi6GPT6nQrYpsxsdxaFi', '苏婉婷', NULL, 'suwanting@qq.com', 1, 1, 1, 1, '2023-04-03 15:04:54', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (5, 'wuxiaoxiao', '$2a$10$Lmk5wXYkZzQMFJEcXVZAZegIQhnAm6ONHpz09X/.gbOh5ze5fU6MW', '吴潇潇', NULL, 'wuxiaoxiao@qq.com', 1, 1, 1, 1, '2023-01-27 12:15:26', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (6, 'mengyan', '$2a$10$6zGT7CfeuJ/6jZPk1pAqcuiMYDnCJstrceThGD5DVVOA5XvOP/sQq', '孟岩', NULL, 'mengyan@163.com', 1, 1, 1, 1, '2023-03-19 10:17:28', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (7, 'yuanhuimin', '$2a$10$mbsloGtPV7cDwfAVYxuvLemQRWumZKrDxVZxg4fnbfaocnfZFlYuu', '袁慧敏', NULL, 'yuanhuimin@11.com', 1, 1, 1, 1, '2023-04-11 20:18:50', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (8, 'qinxuwen', '$2a$10$ir8uLlBrPMHRtGiu5Ajkv.UKcRacXWRen7zxelp9iUaco3WhGkJ36', '秦旭文', '13820000000', 'qinxuwen@163.com', 1, 1, 1, 1, '2023-03-19 21:11:37', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (9, 'chengjie', '$2a$10$jQR8yyF/ailGP/zW6G4JOOffzWOXhe02Rgw7MZLfxL.IGFdM3cjM2', '程杰', '13500000000', 'chengjie@qq.com', 1, 1, 1, 1, '2023-04-16 07:16:19', NULL, '2023-04-20 21:42:21', NULL, NULL);
INSERT INTO `t_user` VALUES (12, 'dengping', '$2a$10$hpN8orfqUFXb.WWbIoZBkOZrr6D8rdSbl/SWXsMQ0zEuqkldlkpW2', '邓萍', NULL, 'dengping@qq.com', 1, 1, 1, 1, '2023-02-19 20:10:58', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (13, 'zhangxing', '$2a$10$uBVDcCCJQvTfoFCjbjwrf.MhyczNNJfCn76jD61CsAgsUlXjXhxzG', '张欣', NULL, 'zhangxing@qq.com', 1, 1, 1, 1, '2023-03-17 12:12:11', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (14, 'zhangmeng', '$2a$10$MMHG2cQh4H4YFbdf48SnyO9IZ78F110x3.7IWGNExrgk2rFmhrd/u', '张萌', NULL, 'zhangmeng@qq.com', 1, 1, 1, 1, '2023-01-13 08:16:02', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (15, 'shixixiang', '$2a$10$zYwq/QfevFPAZxw4b2DkCeQvjVQ52AUU9c4aC0uS0wTJaRr75G74y', '石喜祥', NULL, 'shixixiang@qq.com', 1, 1, 1, 1, '2023-03-10 15:19:49', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (16, 'chengjiuming', '$2a$10$yNN5TcFkM4OqRsKGNM8CNeqAJhRYKQgXVFqbre5lQPicnIXT7THTu', '陈久明', NULL, 'chengjiuming@163.com', 1, 1, 1, 1, '2023-04-09 23:17:37', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (19, 'xuyan', '$2a$10$S7MF2dOqFcoOJPqpEH2nu.Muhn2XC0BlBTZ5gAoL3axrQxdJEJNnK', '徐燕', '13243543534', 'xuyan@qq.com', 0, 0, 0, 0, '2023-03-29 13:16:15', NULL, '2024-05-13 18:09:23', 1, NULL);
INSERT INTO `t_user` VALUES (50, 'xigua', '$2a$10$1tuEw7uyvBjHOS.055ZkqOnkDrJ6xXdTC2H4SdoYvR835JCqxgFK2', '西瓜', '13233445533', '13233445533@14.com', 1, 1, 1, 1, '2024-05-19 14:14:32', 1, '2024-05-19 14:27:31', 50, NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_user_role_ibfk_1`(`user_id` ASC) USING BTREE,
  INDEX `t_user_role_ibfk_2`(`role_id` ASC) USING BTREE,
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (2, 2, 2);
INSERT INTO `t_user_role` VALUES (3, 3, 2);
INSERT INTO `t_user_role` VALUES (4, 4, 3);
INSERT INTO `t_user_role` VALUES (5, 5, 4);
INSERT INTO `t_user_role` VALUES (6, 6, 5);
INSERT INTO `t_user_role` VALUES (9, 50, 1);

SET FOREIGN_KEY_CHECKS = 1;
