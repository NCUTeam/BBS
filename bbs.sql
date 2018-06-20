/*
 Navicat MySQL Data Transfer

 Source Server         : Connect1
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : bbs

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 20/06/2018 08:44:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类名',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for good_article
-- ----------------------------
DROP TABLE IF EXISTS `good_article`;
CREATE TABLE `good_article`  (
  `good_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `user_id` int(11) NOT NULL COMMENT '添加精华帖的用户id',
  PRIMARY KEY (`good_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '精华帖表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for inter_article
-- ----------------------------
DROP TABLE IF EXISTS `inter_article`;
CREATE TABLE `inter_article`  (
  `request_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `author_id` int(11) NULL DEFAULT NULL COMMENT '发互动帖者id',
  `title` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '互动帖标题',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `article_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '互动帖内容',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '类别id',
  `toppost` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`request_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '互动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for post_article
-- ----------------------------
DROP TABLE IF EXISTS `post_article`;
CREATE TABLE `post_article`  (
  `article_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `author_id` int(11) NULL DEFAULT NULL COMMENT '发帖者id',
  `title` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '帖子标题',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `article_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '帖子内容',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '类别id',
  `toppost` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否为置顶帖',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发帖表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_article
-- ----------------------------
INSERT INTO `post_article` VALUES (1, 1, 'WEB程序设计实验', '2018-06-18 15:57:57', '王其乐讲的真实太好了！！', 1, NULL);
INSERT INTO `post_article` VALUES (2, 1, 'springboot', '2018-06-19 22:05:22', 'SpringBoot太好用了', 5, NULL);
INSERT INTO `post_article` VALUES (3, 1, '编译原理实验', '2018-06-19 22:07:21', '编译原理真是太好用了', 5, NULL);
INSERT INTO `post_article` VALUES (4, 1, '微机原理实验', '2018-06-19 22:10:27', '微机原理真是太好玩了', 5, NULL);
INSERT INTO `post_article` VALUES (5, 1, '计算机图形学实验', '2018-06-19 22:12:45', '计算机图形学实验真是太坑人了', 5, NULL);
INSERT INTO `post_article` VALUES (6, 1, 'java实验', '2018-06-19 22:14:44', 'java实验真是太有用了', 5, NULL);
INSERT INTO `post_article` VALUES (7, 1, '数据库实验', '2018-06-19 22:16:46', '数据库实验真是太有趣了！', 5, NULL);
INSERT INTO `post_article` VALUES (8, 1, '数值分析实验', '2018-06-19 22:21:21', '数值分析实验真实太坑爹了', 5, NULL);
INSERT INTO `post_article` VALUES (9, 1, '计算机科学导论实验', '2018-06-19 22:23:05', '计算机科学导论实验真实太有趣了', 5, NULL);
INSERT INTO `post_article` VALUES (10, 1, '计算机操作系统试验', '2018-06-19 22:25:07', '计算机实验操作真是太有趣了！！', 2, NULL);
INSERT INTO `post_article` VALUES (11, 1, '计算机组织与结构', '2018-06-19 22:26:51', '计算机组织与结构真是太好了！！', 4, NULL);

-- ----------------------------
-- Table structure for reply_article
-- ----------------------------
DROP TABLE IF EXISTS `reply_article`;
CREATE TABLE `reply_article`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL COMMENT '评论所在文章的id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '评论的用户的id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '评论的父id',
  `reply_user_id` int(11) NULL DEFAULT NULL COMMENT '被回复人的id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `article_comment` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容，限制500个字符',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回帖表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply_inter
-- ----------------------------
DROP TABLE IF EXISTS `reply_inter`;
CREATE TABLE `reply_inter`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL COMMENT '评论所在文章的id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '评论的用户的id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '评论的父id',
  `reply_user_id` int(11) NULL DEFAULT NULL COMMENT '被回复人的id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `article_comment` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容，限制500个字符',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回帖表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply_request
-- ----------------------------
DROP TABLE IF EXISTS `reply_request`;
CREATE TABLE `reply_request`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL COMMENT '评论所在文章的id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '评论的用户的id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '评论的父id',
  `reply_user_id` int(11) NULL DEFAULT NULL COMMENT '被回复人的id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `article_comment` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容，限制500个字符',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回帖表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for requst_article
-- ----------------------------
DROP TABLE IF EXISTS `requst_article`;
CREATE TABLE `requst_article`  (
  `request_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `author_id` int(11) NULL DEFAULT NULL COMMENT '发需求帖者id',
  `title` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '需求帖标题',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `article_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '需求帖内容',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '类别id',
  `toppost` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`request_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '需求表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` bigint(11) NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `work` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `interests` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '王其东', '123456', '男', NULL, NULL, '山东', '码农', '布衣空空', '音乐其他', 'true');
INSERT INTO `user` VALUES (24, 'admin', '123', NULL, '1213935663@qq.com', 15864546931, NULL, NULL, NULL, NULL, 'false');
INSERT INTO `user` VALUES (27, 'll123', '123', NULL, '123', 123, NULL, NULL, NULL, NULL, 'false');
INSERT INTO `user` VALUES (28, '123456', '123', NULL, '123', 123, NULL, NULL, NULL, NULL, 'false');
INSERT INTO `user` VALUES (31, '111', '111', NULL, '111', 111, NULL, NULL, NULL, NULL, 'false');
INSERT INTO `user` VALUES (32, '1111', '1111', NULL, '1111', 1111, NULL, NULL, NULL, NULL, 'false');

SET FOREIGN_KEY_CHECKS = 1;
