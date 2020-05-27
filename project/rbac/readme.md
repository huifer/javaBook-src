# RBAC

## RBAC 简介

用户：参与系统活动的主体，如人。 

角色：特定权限的集合； 

权限：角色可使用的功能，分角色的功能权限和角色的数据权限；

操作类型：对资源可能的访问方法，如增加、删除、修改等； 

功能：对资源的操作，是资源与操作类型的二元组，如增加销售单、修改销售单等；

 资源：系统中的资源，主要是各种业务对象，如销售单、付款单等； 数据类型：业务系统中常用的数据权限类型，如公司、部门、项目、个人等；

 数据对象：具体的业务对象，如甲公司、乙部门等等，包括所有涉及到数据权限的对象值；权限设计原则：第一：权限的粒度要做到最小，保证在权限分配时，只赋予用户足够完成其工作的权限；第二：避免出现权限互斥的情况

权限设计原则：第一：权限的粒度要做到最小，保证在权限分配时，只赋予用户足够完成其工作的权限；第二：避免出现权限互斥的情况；







## 应用

- 我们将一个web应用分为前端、后端、移动端。如何做好权限控制？ 在这里提出一个简单的解决方案。



- 笔者将数据做如下分类
  - 按钮
  - 菜单
  - 后端url
  - 角色
  - 权限





### 具体功能

1. 用户管理
   1. 用户列表
   2. 添加用户
   3. 修改用户
   4. 删除用户
   5. 设置角色
2. 角色管理
   1. 角色列表
   2. 添加角色
   3. 修改角色
   4. 删除角色
   5. 设置资源
3. 资源管理
   1. 菜单(PC，IOS,Android)
      1. 菜单列表
      2. 添加菜单
      3. 修改菜单
      4. 删除菜单
   2. 按钮(PC，IOS,Android)
      1. 按钮列表
      2. 添加按钮
      3. 修改按钮
      4. 删除按钮
   3. 后端url
      1. url列表
      2. 添加url
      3. 修改url
      4. 删除url



- 关联性
  1. 用户与角色多对多
  3. 橘色和资源多对多



## 技术选型

- Spring Boot
- Mybatis
- Mybatis-plus

- Redis

- JWT

## 表设计
```sql


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for middle_role_btn
-- ----------------------------
DROP TABLE IF EXISTS `middle_role_btn`;
CREATE TABLE `middle_role_btn`  (
  `id` bigint(32) NOT NULL,
  `role_id` bigint(32) NOT NULL,
  `btn_id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和按钮的关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for middle_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `middle_role_menu`;
CREATE TABLE `middle_role_menu`  (
  `id` bigint(32) NOT NULL,
  `role_id` bigint(32) NOT NULL,
  `menu_id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单的关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for middle_role_url
-- ----------------------------
DROP TABLE IF EXISTS `middle_role_url`;
CREATE TABLE `middle_role_url`  (
  `id` bigint(32) NOT NULL,
  `role_id` bigint(32) NOT NULL,
  `url_id` bigint(32) NOT NULL,
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_url
-- ----------------------------
DROP TABLE IF EXISTS `t_url`;
CREATE TABLE `t_url`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '后端接口地址',
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

```