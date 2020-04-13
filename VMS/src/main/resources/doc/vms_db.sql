/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_Local
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : vms_db

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 13/04/2020 17:06:00
*/
DROP DATABASE IF EXISTS vms_db;
CREATE DATABASE vms_db CHARACTER SET'utf8mb4';
USE vms_db;
-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` INT(10) NOT NULL COMMENT '主键',
  `parent_id` INT(10) NULL DEFAULT NULL COMMENT '上级菜单',
  `text` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单文本',
  `url` VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `icon` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标样式',
  `status` INT(2) NULL DEFAULT NULL COMMENT '状态：0-禁用，1-启用',
  `create_user` INT(10) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` INT(11) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `status` INT(2) NULL DEFAULT NULL COMMENT '状态：0-禁用，1-启用',
  `create_user` INT(10) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` INT(10) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'super', 1, NULL, '2020-04-13 15:52:36', NULL, '2020-04-13 15:52:36');
INSERT INTO `sys_role` VALUES (2, '管理员', 'admin', 1, NULL, '2020-04-13 15:52:48', NULL, '2020-04-13 15:52:48');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` INT(10) NULL DEFAULT NULL COMMENT '角色主键',
  `menu_id` INT(10) NULL DEFAULT NULL COMMENT '菜单主键'
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `cellphone` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `password` VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `role_id` INT(10) NULL DEFAULT NULL COMMENT '用户角色',
  `gender` INT(2) NULL DEFAULT NULL COMMENT '性别：0-女性，1-男性',
  `id_card` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `email` VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `avatar` VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `status` INT(2) NULL DEFAULT NULL COMMENT '状态：0-禁用，1-启用',
  `create_user` INT(10) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` INT(10) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '王大锤', '17629039600', '21232f297a57a5a743894a0e4a801fc3', 1, 1, '610111111111111111', 'wangdachui@163.com', NULL, 1, NULL, '2020-04-13 15:54:00', NULL, '2020-04-13 15:54:00');

SET FOREIGN_KEY_CHECKS = 1;

