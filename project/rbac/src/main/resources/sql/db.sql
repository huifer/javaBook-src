/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 27/05/2020 16:04:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_filed
-- ----------------------------
DROP TABLE IF EXISTS `base_filed`;
CREATE TABLE `base_filed`  (
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除标记'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `version` bigint(32) NULL DEFAULT 0,
  `is_delete` tinyint(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for middle_role_btn
-- ----------------------------
DROP TABLE IF EXISTS `middle_role_btn`;
CREATE TABLE `middle_role_btn`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(32) NOT NULL,
  `btn_id` bigint(32) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和按钮的关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for middle_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `middle_role_menu`;
CREATE TABLE `middle_role_menu`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(32) NOT NULL,
  `menu_id` bigint(32) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单的关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for middle_role_url
-- ----------------------------
DROP TABLE IF EXISTS `middle_role_url`;
CREATE TABLE `middle_role_url`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(32) NOT NULL,
  `url_id` bigint(32) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和后端url的关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for middle_user_role
-- ----------------------------
DROP TABLE IF EXISTS `middle_user_role`;
CREATE TABLE `middle_user_role`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(32) NOT NULL COMMENT '用户表id',
  `role_id` bigint(32) NOT NULL COMMENT '角色表id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色的关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_btn
-- ----------------------------
DROP TABLE IF EXISTS `t_btn`;
CREATE TABLE `t_btn`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '终端类型 PC , IOS , ANDROID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '按钮表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '终端类型 PC , IOS , ANDROID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_url
-- ----------------------------
DROP TABLE IF EXISTS `t_url`;
CREATE TABLE `t_url`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '后端接口地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后端url' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(32) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(32) NULL DEFAULT NULL COMMENT '修改人',
  `version` bigint(32) NULL DEFAULT 0 COMMENT '版本',
  `is_delete` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
