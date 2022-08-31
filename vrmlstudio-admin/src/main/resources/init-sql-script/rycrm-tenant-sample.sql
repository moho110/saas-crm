/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : rycrm-tenant-2

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 22/01/2022 15:39:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crm_comment
-- ----------------------------
DROP TABLE IF EXISTS `crm_comment`;
CREATE TABLE `crm_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `content` varchar(512) DEFAULT NULL COMMENT '回复内容',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户跟进记录表';

-- ----------------------------
-- Records of crm_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for crm_customer
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer`;
CREATE TABLE `crm_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `code` varchar(32) DEFAULT '' COMMENT '客户编号',
  `name` varchar(64) DEFAULT '' COMMENT '客户名称',
  `linkman` varchar(64) DEFAULT '' COMMENT '联系人',
  `phone` varchar(64) DEFAULT '' COMMENT '联系电话',
  `region` varchar(64) DEFAULT '' COMMENT '地区',
  `customer_industry` varchar(64) DEFAULT '' COMMENT '客户行业',
  `customer_rank` varchar(64) DEFAULT '' COMMENT '客户级别',
  `customer_status` varchar(64) DEFAULT '' COMMENT '客户状态',
  `clues_name` varchar(64) DEFAULT '' COMMENT '线索名称',
  `clues_source` varchar(64) DEFAULT '' COMMENT '线索来源',
  `clues_status` varchar(64) DEFAULT '' COMMENT '线索状态',
  `deal_status` char(1) DEFAULT '0' COMMENT '是否成交(0未成交 1成交)',
  `status` char(1) DEFAULT '1' COMMENT '状态(0线索 1客户 2公海)',
  `owner` varchar(64) DEFAULT NULL COMMENT '负责人',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志(0代表存在 2代表删除)',
  `version` int(11) DEFAULT '0' COMMENT '版本',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `last_update_record` varchar(255) DEFAULT '' COMMENT '最后跟进记录',
  `last_followup_time` datetime DEFAULT NULL COMMENT '最后跟进时间',
  `next_followup_time` datetime DEFAULT NULL COMMENT '下次跟进时间',
  `pre_owner` varchar(64) DEFAULT '' COMMENT '前负责人',
  `to_pool_time` datetime DEFAULT NULL COMMENT '转公海时间',
  `to_customer_time` datetime DEFAULT NULL COMMENT '转客户时间',
  `pool_type` varchar(64) DEFAULT '' COMMENT '公海类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_phone` (`phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户表';

-- ----------------------------
-- Records of crm_customer
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for crm_order
-- ----------------------------
DROP TABLE IF EXISTS `crm_order`;
CREATE TABLE `crm_order` (
  `id` bigint(20) NOT NULL,
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `amount` decimal(19,2) DEFAULT '0.00' COMMENT '订单金额',
  `owner` varchar(64) DEFAULT NULL COMMENT '负责人',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT '1' COMMENT '状态(0待审核 1审核通过 2审核不通过)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_order_no` (`order_no`) USING BTREE,
  KEY `idx_customer_id` (`customer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of crm_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', now(), '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (2, '客户管理-公海规则', 'crm.pool.days', '10', 'Y', 'admin', now(), '', NULL, '超过时间未跟进将客户移入公海，单位：天');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';


-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 0, '北京市', '北京市', 'region_list', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:08:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (101, 0, '上海市', '上海市', 'region_list', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:08:15', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (102, 0, '广东省', '广东省', 'region_list', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:08:25', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (103, 0, '百度', '百度', 'clues_source', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:09:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (104, 1, '抖音', '抖音', 'clues_source', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:09:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (105, 2, '头条', '头条', 'clues_source', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:09:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (106, 3, '电话', '电话', 'clues_source', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:09:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (107, 0, '有效', '有效', 'clues_status', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:09:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (108, 1, '无效', '无效', 'clues_status', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:10:06', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (109, 0, '计算机互联网', '计算机互联网', 'customer_industry', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:10:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (110, 1, '加工生产', '加工生产', 'customer_industry', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:11:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (111, 2, '房地产', '房地产', 'customer_industry', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:11:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (112, 3, '金融', '金融', 'customer_industry', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:11:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (113, 0, 'A', 'A', 'customer_rank', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:11:55', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (114, 1, 'B', 'B', 'customer_rank', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:11:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (115, 2, 'C', 'C', 'customer_rank', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:12:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (116, 3, 'D', 'D', 'customer_rank', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:12:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (117, 4, 'E', 'E', 'customer_rank', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:12:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (118, 0, '测试客户', '测试客户', 'customer_status', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:12:56', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (119, 1, '意向客户', '意向客户', 'customer_status', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:13:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (120, 2, '签单客户', '签单客户', 'customer_status', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:13:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (121, 3, '回款客户', '回款客户', 'customer_status', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:13:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (122, 4, '上门客户', '上门客户', 'customer_status', NULL, 'default', 'N', '0', 'admin', '2022-01-12 18:13:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (123, 0, '普通', 'normal', 'pool_type', NULL, 'default', 'N', '0', 'admin', '2022-01-13 13:33:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (124, 1, '高级', 'high', 'pool_type', NULL, 'default', 'N', '0', 'admin', '2022-01-13 13:33:24', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (125, 0, '未成交', '0', 'deal_status', NULL, 'default', 'N', '0', 'admin', '2022-01-15 18:11:42', 'admin', '2022-01-15 18:12:15', NULL);
INSERT INTO `sys_dict_data` VALUES (126, 1, '已成交', '1', 'deal_status', NULL, 'default', 'N', '0', 'admin', '2022-01-15 18:11:58', 'admin', '2022-01-15 18:12:09', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE KEY `dict_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2022-01-11 19:11:40', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '线索来源', 'clues_source', '0', 'admin', '2022-01-12 18:05:44', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (101, '线索状态', 'clues_status', '0', 'admin', '2022-01-12 18:05:59', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (102, '行业类别', 'customer_industry', '0', 'admin', '2022-01-12 18:06:30', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (103, '客户级别', 'customer_rank', '0', 'admin', '2022-01-12 18:06:42', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (104, '客户状态', 'customer_status', '0', 'admin', '2022-01-12 18:06:54', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (105, '所在地区', 'region_list', '0', 'admin', '2022-01-12 18:07:47', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (106, '公海类型', 'pool_type', '0', 'admin', '2022-01-13 13:28:04', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (107, '成交状态', 'deal_status', '0', 'admin', '2022-01-15 18:10:43', '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2015 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 200, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2022-01-11 19:11:40', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2022-01-11 19:11:40', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2022-01-11 19:11:40', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2022-01-11 19:11:40', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2022-01-11 19:11:40', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2022-01-11 19:11:40', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2022-01-11 19:11:40', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2022-01-11 19:11:40', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2022-01-11 19:11:40', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2022-01-11 19:11:40', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 1, 10, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2022-01-11 19:11:40', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2022-01-11 19:11:40', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2022-01-11 19:11:40', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2022-01-11 19:11:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2006, '公海管理', 0, 100, 'crm/pool', NULL, NULL, 1, 0, 'M', '0', '0', '', 'peoples', 'admin', '2022-01-12 19:23:39', 'admin', '2022-01-12 19:57:42', '');
INSERT INTO `sys_menu` VALUES (2007, '线索管理', 0, 101, 'crm/clues', NULL, NULL, 1, 0, 'M', '0', '0', '', 'time-range', 'admin', '2022-01-12 19:25:51', 'admin', '2022-01-12 19:49:32', '');
INSERT INTO `sys_menu` VALUES (2008, '客户管理', 0, 102, 'crm/customer', NULL, NULL, 1, 0, 'M', '0', '0', '', 'user', 'admin', '2022-01-12 19:26:15', 'admin', '2022-01-12 19:50:13', '');
INSERT INTO `sys_menu` VALUES (2009, '客户公海', 2006, 100, 'list', 'crm/pool/list', NULL, 1, 0, 'C', '0', '0', 'crm:pool:list', '#', 'admin', '2022-01-12 19:33:38', 'admin', '2022-01-12 19:59:51', '');
INSERT INTO `sys_menu` VALUES (2010, '我的线索', 2007, 100, 'person', 'crm/clues/person-list', NULL, 1, 0, 'C', '0', '0', 'crm:clues:person:list', '#', 'admin', '2022-01-12 19:37:30', 'admin', '2022-01-12 19:47:18', '');
INSERT INTO `sys_menu` VALUES (2011, '线索列表', 2007, 101, 'list', 'crm/clues/list', NULL, 1, 0, 'C', '0', '0', 'crm:clues:list', '#', 'admin', '2022-01-12 19:38:49', 'admin', '2022-01-12 19:50:03', '');
INSERT INTO `sys_menu` VALUES (2012, '我的客户', 2008, 100, 'person', 'crm/customer/person-list', NULL, 1, 0, 'C', '0', '0', 'crm:customer:person:list', '#', 'admin', '2022-01-12 19:42:14', 'admin', '2022-01-12 19:47:26', '');
INSERT INTO `sys_menu` VALUES (2013, '客户列表', 2008, 101, 'list', 'crm/customer/list', NULL, 1, 0, 'C', '0', '0', 'crm:customer:list', '#', 'admin', '2022-01-12 19:42:44', 'admin', '2022-01-12 19:50:29', '');
INSERT INTO `sys_menu` VALUES (2014, '成交客户', 2008, 102, 'deal', 'crm/customer/deal-list', NULL, 1, 0, 'C', '0', '0', 'crm:customer:deal:list', '#', 'admin', '2022-01-12 19:43:37', 'admin', '2022-01-12 19:50:32', '');
INSERT INTO `sys_menu` VALUES (2015, '业绩订单', 0, 103, 'crm/order', NULL, NULL, 1, 0, 'M', '0', '0', '', 'money', 'admin', '2022-01-19 10:20:01', 'admin', '2022-01-19 10:20:17', '');
INSERT INTO `sys_menu` VALUES (2016, '我的订单', 2015, 0, 'crm/customer', 'crm/order/person-list', NULL, 1, 0, 'C', '0', '0', 'crm:order:person:list', '#', 'admin', '2022-01-19 10:20:36', 'admin', '2022-01-19 10:21:08', '');
INSERT INTO `sys_menu` VALUES (2017, '订单列表', 2015, 1, 'crm/order', 'crm/order/list', NULL, 1, 0, 'C', '0', '0', 'crm:order:list', '#', 'admin', '2022-01-19 11:07:45', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1061, '基础数据', 0, 5, 'crm-basicdata', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'color', 'admin', '2022-08-03 20:48:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1062, '采购管理', 0, 6, 'crm-buy', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'color', 'admin', '2022-08-03 20:57:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1063, '客户管理', 0, 7, 'crm-customer', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'dict', 'admin', '2022-08-03 20:58:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1064, '财务管理', 0, 8, 'crm-finance', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'code', 'admin', '2022-08-03 20:59:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1065, '人力资源', 0, 9, 'crm-hr', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'code', 'admin', '2022-08-03 20:59:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1066, '我的桌面', 0, 10, 'crm-mytable', NULL, NULL, 1, 0, 'M', '0', '0', '', 'color', 'admin', '2022-08-03 21:00:11', 'admin', '2022-08-03 21:04:12', '');
INSERT INTO `sys_menu` VALUES (1067, '行政管理', 0, 11, 'crm-political', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'dict', 'admin', '2022-08-03 21:01:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1068, '销售管理', 0, 12, 'crm-sales', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'color', 'admin', '2022-08-03 21:01:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1069, '库存管理', 0, 13, 'crm-store', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'dict', 'admin', '2022-08-03 21:02:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1070, '供应商管理', 0, 14, 'crm-supply', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'color', 'admin', '2022-08-03 21:02:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1071, 'X系统管理', 0, 15, 'crm-system', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'color', 'admin', '2022-08-03 21:03:40', '', NULL, '');

/* begin of xsystem */

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1269, '系统代码', 1071, 1, 'code', 'modules/crm-system/code/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:code:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1270, '系统菜单', 1071, 2, 'menu', 'modules/crm-system/menu/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:menu:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1271, '系统配置', 1071, 3, 'systemconfig', 'modules/crm-system/systemconfig/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:systemconfig:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1272, '系统帮助', 1071, 4, 'systemhelp', 'modules/crm-system/systemhelp/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:systemhelp:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1273, '系统语言', 1071, 5, 'systemlang', 'modules/crm-system/systemlang/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:systemlang:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1274, '系统日志', 1071, 6, 'logall', 'modules/crm-system/logall/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:logall:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1275, '系统日志列表', 1071, 7, 'log', 'modules/crm-system/log/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:log:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');




INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1276, '系统日志类型', 1071, 8, 'logtype', 'modules/crm-system/logtype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:logtype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');




INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1277, '系统隐私', 1071, 9, 'systemprivate', 'modules/crm-system/systemprivate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:systemprivate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1278, '系统隐私配置', 1071, 10, 'systemprivateconfig', 'modules/crm-system/systemprivateconfig/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:systemprivateconfig:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');




INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1279, '系统隐私配置信息', 1071, 11, 'systemprivateinc', 'modules/crm-system/systemprivateinc/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:systemprivateinc:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1280, '系统表', 1071, 12, 'systemtable', 'modules/crm-system/systemtable/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-system:systemtable:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



/* end of xsystem */


/* begin of store */

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1254, '库存调整', 1069, 1, 'stockchangemain', 'modules/crm-store/stockchangemain/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:stockchangemain:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1255, '库存列表', 1069, 2, 'stock', 'modules/crm-store/stock/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:stock:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1256, '库存调整明细', 1069, 3, 'changmaindetail', 'modules/crm-store/changmaindetail/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:changmaindetail:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1257, '库存调整状态', 1069, 4, 'stockchangestate', 'modules/crm-store/stockchangestate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:stockchangestate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1258, '库存入库', 1069, 5, 'stockinmain', 'modules/crm-store/stockinmain/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:stockinmain:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1259, '库存入库明细', 1069, 6, 'indetail', 'modules/crm-store/indetail/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:indetail:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1260, '库存入库明细颜色', 1069, 7, 'incolor', 'modules/crm-store/incolor/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:incolor:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1261, '库存出库列表', 1069, 8, 'stockoutmain', 'modules/crm-store/stockoutmain/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:stockoutmain:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1262, '库存出库明细', 1069, 9, 'outdetail', 'modules/crm-store/outdetail/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:outdetail:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1263, '库存出库明细颜色', 1069, 10, 'outdetailcolor', 'modules/crm-store/outdetailcolor/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:outdetailcolor:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1264, '出库列表', 1069, 11, 'store', 'modules/crm-store/store/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:store:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1265, '出库类型', 1069, 12, 'storeaccesstype', 'modules/crm-store/storeaccesstype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:storeaccesstype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1266, '出库盘点', 1069, 13, 'storecheck', 'modules/crm-store/storecheck/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:storecheck:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1267, '出库颜色列表', 1069, 14, 'color', 'modules/crm-store/color/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:color:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1268, '库存初始化', 1069, 15, 'storeinit', 'modules/crm-store/storeinit/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-store:storeinit:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


/* end of store */





/* begin of sales */

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1240, '销售机会', 1068, 1, 'chance', 'modules/crm-sales/chance/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:chance:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1241, '机会成功率', 1068, 2, 'chancerate', 'modules/crm-sales/chancerate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:chancerate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1242, '积分兑换', 1068, 3, 'exchange', 'modules/crm-sales/exchange/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:exchange:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1243, '发货单', 1068, 4, 'fahuodan', 'modules/crm-sales/fahuodan/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:fahuodan:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1244, '发货类型', 1068, 5, 'fahuotype', 'modules/crm-sales/fahuotype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:fahuotype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1245, '发货计划', 1068, 6, 'fukuanplan', 'modules/crm-sales/fukuanplan/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:fukuanplan:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1246, '销售模式', 1068, 7, 'salemode', 'modules/crm-sales/salemode/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:salemode:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1247, '购买清单类型', 1068, 8, 'sellbilltype', 'modules/crm-sales/sellbilltype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:sellbilltype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1248, '购买合同交付', 1068, 9, 'jiaofu', 'modules/crm-sales/jiaofu/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:jiaofu:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1249, '购买计划', 1068, 10, 'sellplanmain', 'modules/crm-sales/sellplanmain/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:sellplanmain:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1250, '购买计划明细', 1068, 11, 'salesdetail', 'modules/crm-sales/salesdetail/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:salesdetail:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1251, '购买计划明细表颜色', 1068, 12, 'scolor', 'modules/crm-sales/scolor/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:scolor:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1252, '购买计划状态', 1068, 13, 'sellplanstate', 'modules/crm-sales/sellplanstate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:sellplanstate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1253, '收票记录', 1068, 14, 'shoupiaorecord', 'modules/crm-sales/shoupiaorecord/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-sales:shoupiaorecord:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


/* end of sales */


/* begin of political */

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1207, '户型列表', 1067, 1, 'huxing', 'modules/crm-political/huxing/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:huxing:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1208, '满意度', 1067, 2, 'satisfaction', 'modules/crm-political/satisfaction/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:satisfaction:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1209, '使用类型', 1067, 3, 'shiyongleixing', 'modules/crm-political/shiyongleixing/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:shiyongleixing:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1210, '宿舍区域', 1067, 4, 'area', 'modules/crm-political/area/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:area:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1211, '宿舍楼层', 1067, 5, 'building', 'modules/crm-political/building/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:building:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1212, '教学楼', 1067, 6, 'edubuilding', 'modules/crm-political/building/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:building:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1213, '固定资产', 1067, 7, 'fixedasset', 'modules/crm-political/fixedasset/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedasset:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1214, '固定资产报废', 1067, 8, 'fixedassetbaofei', 'modules/crm-political/fixedassetbaofei/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedassetbaofei:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1215, '固定资产分类', 1067, 9, 'fixedassetgroup', 'modules/crm-political/fixedassetgroup/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedassetgroup:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1216, '固定资产入库', 1067, 10, 'fixedassetin', 'modules/crm-political/fixedassetin/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedassetin:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1217, '固定资产类别', 1067, 11, 'fixedassetleibie', 'modules/crm-political/fixedassetleibie/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedassetleibie:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1218, '固定资产出库', 1067, 12, 'fixedassetout', 'modules/crm-political/fixedassetout/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedassetout:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1219, '固定资产状态', 1067, 13, 'fixedassetstatus', 'modules/crm-political/fixedassetstatus/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedassetstatus:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1220, '固定资产调库', 1067, 14, 'fixedassettiaoku', 'modules/crm-political/fixedassettiaoku/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedassettiaoku:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1221, '固定资产归还', 1067, 15, 'fixedassettui', 'modules/crm-political/fixedassettui/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedassettui:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1222, '固定资产维修', 1067, 16, 'fixedassetweixiu', 'modules/crm-political/fixedassetweixiu/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:fixedassetweixiu:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1223, '是否已婚', 1067, 17, 'marriage', 'modules/crm-political/marriage/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:marriage:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1224, '国籍', 1067, 18, 'national', 'modules/crm-political/national/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:national:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1225, '政治面貌', 1067, 19, 'political', 'modules/crm-political/political/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:political:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1226, '性别', 1067, 20, 'sex', 'modules/crm-political/sex/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:sex:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1227, '办公用品归还状态', 1067, 21, 'officeguihuanstate', 'modules/crm-political/officeguihuanstate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeguihuanstate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1228, '办公用品列表', 1067, 22, 'officeproduct', 'modules/crm-political/officeproduct/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeproduct:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1229, '办公用品报废', 1067, 23, 'officeproductbaofei', 'modules/crm-political/officeproductbaofei/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeproductbaofei:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1230, '办公用品仓库', 1067, 24, 'officeproductcangku', 'modules/crm-political/officeproductcangku/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeproductcangku:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1231, '办公用品分类', 1067, 25, 'officeproductgroup', 'modules/crm-political/officeproductgroup/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeproductgroup:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1232, '办公用品入库', 1067, 26, 'officeproductin', 'modules/crm-political/officeproductin/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeproductin:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1233, '办公用品类别', 1067, 27, 'officeproductleibie', 'modules/crm-political/officeproductleibie/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeproductleibie:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1234, '办公用品出库', 1067, 28, 'officeproductout', 'modules/crm-political/officeproductout/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeproductout:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1235, '办公用品调库', 1067, 29, 'officeproducttiaoku', 'modules/crm-political/officeproducttiaoku/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeproducttiaoku:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1236, '办公用品归还', 1067, 30, 'officeproducttui', 'modules/crm-political/officeproducttui/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officeproducttui:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1237, '办公任务', 1067, 31, 'officetask', 'modules/crm-political/officetask/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:officetask:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1238, '是否boolean', 1067, 32, 'wuboolean', 'modules/crm-political/wuboolean/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:wuboolean:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1239, '楼层信息', 1067, 33, 'buildinginformation', 'modules/crm-political/buildinginformation/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-political:buildinginformation:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');




/* end of political */


/* begin of mytable */

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1186, '事务列表', 1066, 1, 'affair', 'modules/crm-mytable/affair/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:affair:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1187, '日历列表', 1066, 2, 'calendar', 'modules/crm-mytable/calendar/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:calendar:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1188, '日历类型', 1066, 3, 'calendartype', 'modules/crm-mytable/calendartype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:calendartype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1189, '呼叫处理', 1066, 4, 'callchuli', 'modules/crm-mytable/callchuli/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:callchuli:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1190, '呼叫者类型', 1066, 5, 'callertype', 'modules/crm-mytable/callertype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:callertype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1191, '呼叫类型', 1066, 6, 'calltype', 'modules/crm-mytable/calltype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:calltype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1192, '评论列表', 1066, 7, 'comment', 'modules/crm-mytable/comment/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:comment:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1193, '通用日志', 1066, 8, 'commlog', 'modules/crm-mytable/commlog/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:commlog:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1194, '我的桌面', 1066, 9, 'mytable', 'modules/crm-mytable/mytable/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:mytable:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1195, '桌面便签', 1066, 10, 'mytablenotes', 'modules/crm-mytable/mytablenotes/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:mytablenotes:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1196, '桌面位置', 1066, 11, 'mytablewz', 'modules/crm-mytable/mytablewz/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:mytablewz:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1197, '桌面显示属性', 1066, 12, 'mytablexssx', 'modules/crm-mytable/mytablexssx/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:mytablexssx:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1198, '字典提醒', 1066, 13, 'dictnotify', 'modules/crm-mytable/dictnotify/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:dictnotify:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1199, '邮件列表', 1066, 14, 'email', 'modules/crm-mytable/email/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:email:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1200, '邮件状态', 1066, 15, 'emailstate', 'modules/crm-mytable/emailstate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:emailstate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1201, '发送列表', 1066, 16, 'sendlist', 'modules/crm-mytable/sendlist/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:sendlist:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1202, '工作计划', 1066, 17, 'workplanmain', 'modules/crm-mytable/workplanmain/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:workplanmain:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1203, '工作计划明细', 1066, 18, 'workdetail', 'modules/crm-mytable/workdetail/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:workdetail:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1204, '工作计划审核', 1066, 19, 'workplanshenhe', 'modules/crm-mytable/workplanshenhe/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:workplanshenhe:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1205, '工作计划状态', 1066, 20, 'workplanstate', 'modules/crm-mytable/workplanstate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:workplanstate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1206, '工作报告列表', 1066, 21, 'workreport', 'modules/crm-mytable/workreport/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-mytable:workreport:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


/* end of mytable */



/* begin of hr */

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1139, '教育列表', 1065, 1, 'education', 'modules/crm-hr/education/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:education:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1140, '周日天', 1065, 2, 'weekday', 'modules/crm-hr/weekday/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:weekday:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1141, '性别', 1065, 3, 'xingbie', 'modules/crm-hr/xingbie/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:xingbie:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1142, '行政请假', 1065, 4, 'xingzhengqingjia', 'modules/crm-hr/xingzhengqingjia/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:xingzhengqingjia:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1143, '证件类型', 1065, 5, 'zhengjianleixing', 'modules/crm-hr/zhengjianleixing/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:zhengjianleixing:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1144, '是否教育', 1065, 6, 'boolean', 'modules/crm-hr/boolean/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:boolean:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1145, '校历列表', 1065, 7, 'schoolcalendar', 'modules/crm-hr/schoolcalendar/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:schoolcalendar:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1146, '教师考勤', 1065, 8, 'teacherkaoqin', 'modules/crm-hr/teacherkaoqin/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:teacherkaoqin:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1147, '行政班次', 1065, 9, 'xingzhengbanci', 'modules/crm-hr/xingzhengbanci/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:xingzhengbanci:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1148, '行政分类', 1065, 10, 'xingzhenggroup', 'modules/crm-hr/xingzhenggroup/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:xingzhenggroup:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1149, '行政加班补休', 1065, 11, 'jiabanbuxiu', 'modules/crm-hr/jiabanbuxiu/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:jiabanbuxiu:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1150, '行政考勤补登', 1065, 12, 'kaoqinbudeng', 'modules/crm-hr/kaoqinbudeng/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:kaoqinbudeng:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1151, '行政排班', 1065, 13, 'xingzhengpaiban', 'modules/crm-hr/xingzhengpaiban/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:xingzhengpaiban:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1152, '行政请假', 1065, 14, 'xingzhengqingjia', 'modules/crm-hr/xingzhengqingjia/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:xingzhengqingjia:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1153, '行政调班', 1065, 15, 'xingzhengtiaoban', 'modules/crm-hr/xingzhengtiaoban/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:xingzhengtiaoban:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1154, '行政调班项目', 1065, 16, 'tiaobanxianghu', 'modules/crm-hr/tiaobanxianghu/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:tiaobanxianghu:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1155, '行政调休补班', 1065, 17, 'tiaoxiububan', 'modules/crm-hr/tiaoxiububan/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:tiaoxiububan:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1156, '行政工作检查', 1065, 18, 'workcheckregister', 'modules/crm-hr/workcheckregister/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:workcheckregister:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1157, '行政年检', 1065, 19, 'yearcheck', 'modules/crm-hr/yearcheck/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:yearcheck:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1158, '行政学期', 1065, 20, 'xueqiexec', 'modules/crm-hr/xueqiexec/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:xueqiexec:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1159, '行政中层测评', 1065, 21, 'zhongcengceping', 'modules/crm-hr/zhongcengceping/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:zhongcengceping:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1160, '行政中层明细', 1065, 22, 'zhongcengmingxi', 'modules/crm-hr/zhongcengmingxi/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:zhongcengmingxi:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1161, '行政中层人员', 1065, 23, 'zhongcengrenyuan', 'modules/crm-hr/zhongcengrenyuan/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:zhongcengrenyuan:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1162, '行政中层项目', 1065, 24, 'zhongcengxingmu', 'modules/crm-hr/zhongcengxingmu/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:zhongcengxingmu:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1163, '是否人力资源', 1065, 25, 'hrboolean', 'modules/crm-hr/hrboolean/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:hrboolean:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1164, '教育经历', 1065, 26, 'educationalexperience', 'modules/crm-hr/educationalexperience/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:educationalexperience:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1165, '教育成本', 1065, 27, 'expense', 'modules/crm-hr/expense/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:expense:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1166, '教育成本类型', 1065, 28, 'type', 'modules/crm-hr/type/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:type:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1167, '人力资源', 1065, 29, 'hrmsfile', 'modules/crm-hr/hrmsfile/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:hrmsfile:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1168, '复职列表', 1065, 30, 'hrfuzhi', 'modules/crm-hr/hrfuzhi/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:hrfuzhi:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1169, '离职列表', 1065, 31, 'lizhi', 'modules/crm-hr/lizhi/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:lizhi:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1170, '录用列表', 1065, 32, 'luyong', 'modules/crm-hr/luyong/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:luyong:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1171, '劳动技能', 1065, 33, 'laboringskill', 'modules/crm-hr/laboringskill/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:laboringskill:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1172, '奖罚列表', 1065, 34, 'punishment', 'modules/crm-hr/punishment/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:punishment:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1173, '是否奖罚', 1065, 35, 'p', 'modules/crm-hr/p/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:p:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1174, '奖罚名称', 1065, 36, 'name', 'modules/crm-hr/name/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:name:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1175, '奖罚状态', 1065, 37, 'rpstatus', 'modules/crm-hr/rpstatus/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:rpstatus:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1176, '社会关系', 1065, 38, 'socialrelation', 'modules/crm-hr/socialrelation/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:socialrelation:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1177, '转职列表', 1065, 39, 'hrtransfer', 'modules/crm-hr/hrtransfer/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:hrtransfer:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1178, '转职类型', 1065, 40, 'trtype', 'modules/crm-hr/trtype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:trtype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1179, '工作合同', 1065, 41, 'workerhetong', 'modules/crm-hr/workerhetong/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:workerhetong:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1180, '工作证照', 1065, 42, 'zhengzhao', 'modules/crm-hr/zhengzhao/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:zhengzhao:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1181, '工作职称', 1065, 43, 'zhicheng', 'modules/crm-hr/zhicheng/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:zhicheng:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1182, '工作经验', 1065, 44, 'workexperience', 'modules/crm-hr/workexperience/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:workexperience:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1183, '工作状态', 1065, 45, 'wstatus', 'modules/crm-hr/wstatus/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:status:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1184, '招聘计划', 1065, 46, 'zpjihua', 'modules/crm-hr/zpjihua/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:zpjihua:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1185, '人才库列表', 1065, 47, 'zprencaiku', 'modules/crm-hr/zprencaiku/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-hr:zprencaiku:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

/* end of hr */


/*  begin of finance */


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1133, '帐户操作', 1064, 1, 'accessbank', 'modules/crm-finance/accessbank/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-finance:accessbank:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1134, '预支付', 1064, 2, 'accessprepays', 'modules/crm-finance/accessprepays/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-finance:accessprepays:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1135, '预收款', 1064, 3, 'accesspreshou', 'modules/crm-finance/accesspreshou/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-finance:accesspreshou:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1136, '存取类型', 1064, 4, 'accesstype', 'modules/crm-finance/accesstype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-finance:accesstype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1137, '帐户注入', 1064, 5, 'bankzhuru', 'modules/crm-finance/bankzhuru/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-finance:bankzhuru:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1138, '支付状态', 1064, 6, 'paystate', 'modules/crm-finance/paystate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-finance:paystate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


/*  end of finance */


/*  begin of customer */

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1117, '合同标识', 1063, 1, 'contractflag', 'modules/crm-customer/contractflag/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:contractflag:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1118, '联系方式', 1063, 2, 'contact', 'modules/crm-customer/contact/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:contact:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1119, '客户移除', 1063, 3, 'customermove', 'modules/crm-customer/customermove/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:customermove:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1120, '审核状态', 1063, 4, 'shenhezhuangtai', 'modules/crm-customer/shenhezhuangtai/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:shenhezhuangtai:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1121, '申请报备', 1063, 5, 'shenqingbaobei', 'modules/crm-customer/shenqingbaobei/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:shenqingbaobei:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1122, '状态列表', 1063, 6, 'zhuangtai', 'modules/crm-customer/zhuangtai/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:zhuangtai:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1123, '客户列表', 1063, 7, 'customer', 'modules/crm-customer/customer/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:customer:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1124, '客户区域', 1063, 8, 'customerarea', 'modules/crm-customer/customerarea/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:customerarea:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1125, '客户归属', 1063, 9, 'customerbelong', 'modules/crm-customer/customerbelong/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:customerbelong:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1126, '客户自定义类型', 1063, 10, 'customerdefinetype', 'modules/crm-customer/customerdefinetype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:customerdefinetype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1127, '客户方案', 1063, 11, 'fangan', 'modules/crm-customer/fangan/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:fangan:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1128, '客户等级', 1063, 12, 'customerlever', 'modules/crm-customer/customerlever/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:customerlever:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1129, '客户来源', 1063, 13, 'customerorigin', 'modules/crm-customer/customerorigin/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:customerorigin:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1130, '客户产品列表', 1063, 14, 'customerproduct', 'modules/crm-customer/customerproduct/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:customerproduct:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1131, '客户产品明细', 1063, 15, 'customerdetail', 'modules/crm-customer/customerdetail/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:customerdetail:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1132, '客户需求', 1063, 16, 'xuqiu', 'modules/crm-customer/xuqiu/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-customer:xuqiu:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

/*  end of customer */


/*  begin of buy */

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1107, '认证列表', 1062, 1, 'buyplanmain', 'modules/crm-buy/buyplanmain/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:buyplanmain:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1108, '采购计划单颜色', 1062, 2, 'color', 'modules/crm-buy/color/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:color:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1109, '采购计划明细', 1062, 3, 'mingxi', 'modules/crm-buy/mingxi/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:mingxi:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1110, '采购计划单颜色', 1062, 4, 'color', 'modules/crm-buy/color/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:color:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1111, '采购计划临时颜色', 1062, 5, 'tmpcolor', 'modules/crm-buy/tmpcolor/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:tmpcolor:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1112, '采购计划状态', 1062, 6, 'buyplanstate', 'modules/crm-buy/buyplanstate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:buyplanstate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1113, '费用申请', 1062, 7, 'feiyongsq', 'modules/crm-buy/feiyongsq/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:feiyongsq:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1114, '完成状态', 1062, 8, 'finishstate', 'modules/crm-buy/finishstate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:finishstate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1115, '汇款计划', 1062, 9, 'huikuanplan', 'modules/crm-buy/huikuanplan/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:huikuanplan:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1116, '汇款记录', 1062, 10, 'huikuanrecord', 'modules/crm-buy/huikuanrecord/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:huikuanrecord:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');



INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1117, '是否需求', 1062, 11, 'ifneed', 'modules/crm-buy/ifneed/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:ifneed:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1118, '重要性', 1062, 12, 'important', 'modules/crm-buy/important/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:important:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1119, '输入输出', 1062, 13, 'inorout', 'modules/crm-buy/inorout/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:inorout:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1120, '开票记录', 1062, 14, 'kaipiaorecord', 'modules/crm-buy/kaipiaorecord/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:kaipiaorecord:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1121, '开票状态', 1062, 15, 'kaipiaostate', 'modules/crm-buy/kaipiaostate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-buy:kaipiaostate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


/*  end of buy */


/*  begin of basicdata */


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1073, '认证列表', 1061, 2, 'certificate', 'modules/crm-basicdata/certificate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:certificate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1074, '认证类型', 1061, 3, 'certificatetype', 'modules/crm-basicdata/certificatetype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:certificatetype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1075, '国家代码', 1061, 4, 'countrycode', 'modules/crm-basicdata/countrycode/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:countrycode:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1076, '单元用途', 1061, 5, 'danyuanyongtu', 'modules/crm-basicdata/danyuanyongtu/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:danyuanyongtu:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1077, '服务来源', 1061, 6, 'servicesource', 'modules/crm-basicdata/servicesource/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:servicesource:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1078, '服务状态', 1061, 7, 'servicestatus', 'modules/crm-basicdata/servicestatus/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:servicestatus:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1079, '服务类型', 1061, 8, 'servicetype', 'modules/crm-basicdata/servicetype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:servicetype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1080, '阶段列表', 1061, 9, 'jieduan', 'modules/crm-basicdata/jieduan/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:jieduan:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1081, '部门列表', 1061, 10, 'department', 'modules/crm-basicdata/department/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:department:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1082, '单元用途', 1061, 11, 'certificate', 'modules/crm-basicdata/certificate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:certificate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1083, '单元状态', 1061, 12, 'danyuanzhuangtai', 'modules/crm-basicdata/danyuanzhuangtai/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:danyuanzhuangtai:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1084, '费用分类', 1061, 13, 'feiyongclasse', 'modules/crm-basicdata/feiyongclasse/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:feiyongclasse:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1085, '费用记录', 1061, 14, 'feiyongrecord', 'modules/crm-basicdata/feiyongrecord/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:feiyongrecord:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1086, '费用类型', 1061, 15, 'feiyongtype', 'modules/crm-basicdata/feiyongtype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:feiyongtype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');


INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1087, '联系人列表', 1061, 16, 'linkman', 'modules/crm-basicdata/linkman/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:linkman:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1088, '监测列表', 1061, 17, 'measure', 'modules/crm-basicdata/measure/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:measure:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1089, '消息列表', 1061, 18, 'message', 'modules/crm-basicdata/message/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:message:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1090, '修改记录', 1061, 19, 'modifyrecord', 'modules/crm-basicdata/modifyrecord/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:modifyrecord:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1091, '通知列表', 1061, 20, 'basicnotify', 'modules/crm-basicdata/basicnotify/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:basicnotify:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1092, '产品列表', 1061, 21, 'product', 'modules/crm-basicdata/product/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:product:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1093, '产品颜色', 1061, 22, 'productcolor', 'modules/crm-basicdata/productcolor/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:productcolor:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1094, '产品类型', 1061, 23, 'producttype', 'modules/crm-basicdata/producttype/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:producttype:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1095, '产品组装', 1061, 24, 'productzuzhuang', 'modules/crm-basicdata/productzuzhuang/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:productzuzhuang:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1096, '产品组装明细', 1061, 25, 'detail', 'modules/crm-basicdata/detail/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:detail:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1097, '产品组装明细', 1061, 26, '2detail', 'modules/crm-basicdata/detail/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:detail:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1098, '产品组装状态', 1061, 27, 'productzuzhuangstate', 'modules/crm-basicdata/productzuzhuangstate/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:productzuzhuangstate:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1099, '行业归属', 1061, 28, 'property', 'modules/crm-basicdata/property/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:property:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1100, '回收站', 1061, 28, 'basicbin', 'modules/crm-basicdata/basicbin/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:basicbin:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1101, '供应商列表', 1061, 28, 'supply', 'modules/crm-basicdata/supply/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:supply:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1102, '供应商等级', 1061, 28, 'supplylever', 'modules/crm-basicdata/supplylever/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:supplylever:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1103, '供应商联系人', 1061, 28, 'supplylinkman', 'modules/crm-basicdata/supplylinkman/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:supplylinkman:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1104, '供应商商品', 1061, 28, 'supplyproduct', 'modules/crm-basicdata/supplyproduct/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:supplyproduct:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1105, '单元列表', 1061, 28, 'unit', 'modules/crm-basicdata/unit/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:unit:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

INSERT INTO `sys_menu` 
(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`,
 `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1106, '单元属性', 1061, 28, 'unitprop', 'modules/crm-basicdata/unitprop/index', 
  NULL, 1, 0, 'C', '0', '0', 'crm-basicdata:unitprop:list', 
  'code', 'admin', '2022-08-07 05:59:44', 'admin', '2022-08-07 06:04:42', '');

/* end of basicdata */

COMMIT;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', now(), '', NULL, '超级管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';


-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_post` VALUES (1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
COMMIT;

CREATE TABLE `cmf_accessbank` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bankid` int(11) NOT NULL,
  `oldjine` double NOT NULL,
  `jine` double NOT NULL,
  `opertype` tinyint(1) NOT NULL,
  `guanlianbillid` int(11) NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



CREATE TABLE `cmf_accessprepays` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `supplyid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `linkmanid` int(11) NOT NULL,
  `curchuzhi` double NOT NULL,
  `jine` double NOT NULL,
  `createman` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opertype` tinyint(1) NOT NULL,
  `guanlianbillid` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `billdeptid` int(11) NOT NULL,
  `accountid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `beizhu` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `cmf_accesspreshou` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customerid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `linkman` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `curchuzhi` double NOT NULL,
  `accountid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `jine` double NOT NULL,
  `opertype` tinyint(1) NOT NULL,
  `guanlianbillid` int(11) NOT NULL,
  `createman` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `beizhu` text COLLATE utf8_unicode_ci NOT NULL,
  `billdeptid` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `cmf_accesstype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parent` int(11) NOT NULL,
  `inout` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_affair`
--

CREATE TABLE `cmf_affair` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `BEGIN_TIME` datetime NOT NULL,
  `END_TIME` datetime NOT NULL,
  `TYPE` int(11) NOT NULL,
  `REMIND_DATE` date NOT NULL,
  `REMIND_TIME` datetime NOT NULL,
  `CONTENT` text COLLATE utf8_unicode_ci NOT NULL,
  `LAST_REMIND` date NOT NULL,
  `SMS2_REMIND` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `LAST_SMS2_REMIND` date NOT NULL,
  `MANAGER_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_bank`
--

CREATE TABLE `cmf_bank` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bankid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `bankcode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `bankname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `syslock` int(11) NOT NULL,
  `jine` double NOT NULL,
  `belong` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_bankzhuru`
--

CREATE TABLE `cmf_bankzhuru` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `accountid` int(11) NOT NULL,
  `jine` double NOT NULL,
  `userid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opertime` datetime NOT NULL,
  `inouttype` int(11) NOT NULL,
  `memo` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_buyplanmain`
--

CREATE TABLE `cmf_buyplanmain` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplyid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `linkman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `caigoudate` date NOT NULL,
  `storeid` int(11) NOT NULL,
  `daohuodate` date NOT NULL,
  `danhao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `totalmoney` double NOT NULL,
  `guanliandingdan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `guanliankehu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beizhu` text COLLATE utf8_unicode_ci NOT NULL,
  `beiyong1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beiyong2` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beiyong3` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `paymoney` double(8,2) NOT NULL,
  `state` int(11) NOT NULL,
  `rukumoney` double NOT NULL,
  `shoupiaomoney` double NOT NULL,
  `ifpay` int(11) NOT NULL,
  `shoupiaostate` int(11) NOT NULL,
  `oddment` double NOT NULL,
  `user_flag` smallint(6) NOT NULL,
  `accountid` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



CREATE TABLE `cmf_buyplanmain_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` double NOT NULL,
  `beizhu` text COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `recnum` double NOT NULL,
  `jine` double NOT NULL,
  `oldprodid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `lastprice` double(8,2) NOT NULL,
  `opertype` smallint(6) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- --------------------------------------------------------

--
-- 表的结构 `cmf_buyplanmain_detail_color`
--

CREATE TABLE `cmf_buyplanmain_detail_color` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_buyplanmain_mingxi`
--

CREATE TABLE `cmf_buyplanmain_mingxi` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `oldproductid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `prodid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `lastprice` double NOT NULL,
  `prodname` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `num` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `jine` double NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_buyplanmain_tmp_color`
--

CREATE TABLE `cmf_buyplanmain_tmp_color` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `color` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_buyplanstate`
--

CREATE TABLE `cmf_buyplanstate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_calendar`
--

CREATE TABLE `cmf_calendar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(20) NOT NULL DEFAULT '',
  `CAL_TIME` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `END_TIME` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `CAL_TYPE` varchar(20) NOT NULL DEFAULT '个人日程',
  `CAL_LEVEL` smallint(6) NOT NULL DEFAULT '1',
  `CONTENT` longtext NOT NULL,
  `MANAGER_ID` varchar(20) NOT NULL DEFAULT '',
  `OVER_STATUS` varchar(20) NOT NULL DEFAULT '0',
  `ifsms` smallint(6) DEFAULT NULL,
  `tixingtime` datetime DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  `guanlianid` int(11) DEFAULT NULL,
  PRIMARY KEY(id)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;



CREATE TABLE `cmf_calendar_type` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_callchuli`
--

CREATE TABLE `cmf_callchuli` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tel` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `customer` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `linkman` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `ifchuli` tinyint(1) NOT NULL,
  `chulitime` datetime NOT NULL,
  `calltype` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `callertype` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `customerid` int(11) NOT NULL,
  `chulicontent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `linkmanid` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_callertype`
--

CREATE TABLE `cmf_callertype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_calltype`
--

CREATE TABLE `cmf_calltype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_certificate`
--

CREATE TABLE `cmf_certificate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `supplyid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `certificatetype` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `certificateno` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `certificateinfo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fromdate` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `enddate` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `explianstr` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `fileaddress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_certificatetype`
--

CREATE TABLE `cmf_certificatetype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_cmdict_countrycode`
--

CREATE TABLE `cmf_cmdict_countrycode` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `countryName` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `countryCode` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `postCode` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_cmdict_danyuanyongtu`
--

CREATE TABLE `cmf_cmdict_danyuanyongtu` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_comment`
--

CREATE TABLE `cmf_comment` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `to_user_id` int(11) NOT NULL,
  `object_id` int(11) NOT NULL,
  `like_count` int(11) NOT NULL,
  `dislike_count` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `delete_time` datetime NOT NULL,
  `status` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `table_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `full_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `path` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url` text COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `more` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_commlog`
--

CREATE TABLE `cmf_commlog` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sysuser` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `customerid` int(11) NOT NULL,
  `commdate` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `linkmanid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `project` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `productid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `projectphase` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `iscompete` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `comminfo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `moniterinfo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `moniterman` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_competeproduct`
--

CREATE TABLE `cmf_competeproduct` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `customerid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `productid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `infofrom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `comproduct` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `compname` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `compexcel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `compdefect` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `compprice` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `fileaddress` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_contract_flag`
--

CREATE TABLE `cmf_contract_flag` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_chance`
--

CREATE TABLE `cmf_crm_chance` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `chanceTheme` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `clientName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `linkMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `findTime` date NOT NULL,
  `clientNeed` text COLLATE utf8_unicode_ci NOT NULL,
  `preSignTime` date NOT NULL,
  `preCount` double NOT NULL,
  `enable` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `recentStatus` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `bak` text COLLATE utf8_unicode_ci NOT NULL,
  `lastContactTime` datetime NOT NULL,
  `attach` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_chance_rate`
--

CREATE TABLE `cmf_crm_chance_rate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `enable` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_contact`
--

CREATE TABLE `cmf_crm_contact` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `linkmanid` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `contact` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `chance` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `stage` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `describes` text COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `contacttime` datetime NOT NULL,
  `nextcontacttime` datetime NOT NULL,
  `nextissue` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `alreadycontact` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `public` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `priority` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_customer_move`
--

CREATE TABLE `cmf_crm_customer_move` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `clientId` int(11) NOT NULL,
  `preUser` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nextUser` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `operator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `operateTime` datetime NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_dict_servicesource`
--

CREATE TABLE `cmf_crm_dict_servicesource` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_dict_servicestatus`
--

CREATE TABLE `cmf_crm_dict_servicestatus` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_dict_servicetype`
--

CREATE TABLE `cmf_crm_dict_servicetype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_feiyong_sq`
--

CREATE TABLE `cmf_crm_feiyong_sq` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `feeType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `count` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `feeUse` text COLLATE utf8_unicode_ci NOT NULL,
  `applyDate` date NOT NULL,
  `clientName` int(11) NOT NULL,
  `reimburseMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `recorder` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `isExamine` smallint(6) NOT NULL,
  `examineDate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `cashier` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `payTime` datetime NOT NULL,
  `bak` text COLLATE utf8_unicode_ci NOT NULL,
  `examineMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_finishstate`
--

CREATE TABLE `cmf_crm_finishstate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_jieduan`
--

CREATE TABLE `cmf_crm_jieduan` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `stage` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_mytable`
--

CREATE TABLE `cmf_crm_mytable` (
  `id` int(11) NOT NULL COMMENT '编号' AUTO_INCREMENT,
  `moduleId` varchar(200) NOT NULL COMMENT '模块编号',
  `moduleName` varchar(200) NOT NULL COMMENT '模块名称',
  `modulePosition` varchar(200) NOT NULL COMMENT '模块位置',
  `moduleAttr` varchar(200) NOT NULL COMMENT '模块属性',
  `displayLineNumber` varchar(200) NOT NULL COMMENT '显示行数',
  `scrollDisplay` varchar(200) NOT NULL COMMENT '滚动显示',
  `creator` varchar(200) NOT NULL COMMENT '创建人',
  `createTime` varchar(200) NOT NULL COMMENT '创建时间',
  `memo` varchar(200) NOT NULL COMMENT '备注',
  PRIMARY KEY(id)
) ENGINE=MyISAM DEFAULT CHARSET=gbk COMMENT='桌面模块表';


CREATE TABLE `cmf_crm_mytable_note` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `noteContent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_mytable_wz`
--

CREATE TABLE `cmf_crm_mytable_wz` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `displayPos` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_mytable_xssx`
--

CREATE TABLE `cmf_crm_mytable_xssx` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `displayAttr` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_piaoju_type`
--

CREATE TABLE `cmf_crm_piaoju_type` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `billType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_remember`
--

CREATE TABLE `cmf_crm_remember` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `linkmanid` int(11) NOT NULL,
  `mem_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `customerid` int(11) NOT NULL,
  `mem_date` date NOT NULL,
  `createtime` datetime NOT NULL,
  `operman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_service`
--

CREATE TABLE `cmf_crm_service` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `serviceId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `serviceStage` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lastLimited` datetime NOT NULL,
  `serviceSummary` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `clientName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `linkMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `seriousDegree` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `solveMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `solveMethod` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `solveStatus` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `isExamine` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `examineMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `examineTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `attach` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `relationSaleList` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `customTwo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `customThree` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `customFour` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `customFive` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




CREATE TABLE `cmf_crm_shenhezhuangtai` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




CREATE TABLE `cmf_crm_shenqingbaobei` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `linkmanid` int(11) NOT NULL,
  `chanceid` int(11) NOT NULL,
  `introduce` text COLLATE utf8_unicode_ci NOT NULL,
  `zhichi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fujian` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `state` smallint(6) NOT NULL,
  `piyu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `shenheman` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `shenhetime` datetime NOT NULL,
  `zhuti` text COLLATE utf8_unicode_ci NOT NULL,
  `tixingren` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_crm_zhuangtai`
--

CREATE TABLE `cmf_crm_zhuangtai` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_customer`
--

CREATE TABLE `cmf_customer` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `supplyid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `supplyname` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `supplyshortname` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `enterstype` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `calling` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `membercard` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `chargesection` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `postalcode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fax` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `contactaddress` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `netword` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `bank` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `accountnumber` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `startdate` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `enddate` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `style` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `supplycn` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `sysuser` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `explainStr` text COLLATE utf8_unicode_ci NOT NULL,
  `briefStr` text COLLATE utf8_unicode_ci NOT NULL,
  `prodprice` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `amtagio` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `recommand` text COLLATE utf8_unicode_ci NOT NULL,
  `getaccount` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `yuchuzhi` double NOT NULL,
  `factgetmoney` double NOT NULL,
  `nogetmoney` double NOT NULL,
  `origin` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `salemode` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `property` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `datascope` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `createdate` datetime NOT NULL,
  `integral` int(11) NOT NULL,
  `lasttracetime` datetime NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




CREATE TABLE `cmf_customerarea` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rowid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `leverno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_customerbelong`
--

CREATE TABLE `cmf_customerbelong` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_customerdefinetype`
--

CREATE TABLE `cmf_customerdefinetype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



CREATE TABLE `cmf_customerlever` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `relatePrice` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_customerorigin`
--

CREATE TABLE `cmf_customerorigin` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_customerproduct`
--

CREATE TABLE `cmf_customerproduct` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `theme` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `priceMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `clients` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `reiceiver` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `priceTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `count` double(8,2) NOT NULL,
  `saleChance` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jiaofuIntro` text COLLATE utf8_unicode_ci NOT NULL,
  `toPayIntro` text COLLATE utf8_unicode_ci NOT NULL,
  `packageExpIntro` text COLLATE utf8_unicode_ci NOT NULL,
  `memo` text COLLATE utf8_unicode_ci NOT NULL,
  `attach` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `isExamine` smallint(6) NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `examineMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `examineTime` datetime NOT NULL,
  `unitid` smallint(6) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_customerproduct_detail`
--

CREATE TABLE `cmf_customerproduct_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` int(11) NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `jine` double NOT NULL,
  `oldprodid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opertype` smallint(6) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_customer_fangan`
--

CREATE TABLE `cmf_customer_fangan` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `customerid` int(11) NOT NULL,
  `chanceid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fankui` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `fujian` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `cmf_customer_xuqiu` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tigongren` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `customerid` int(11) NOT NULL,
  `chanceid` int(11) NOT NULL,
  `import` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `fujian` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fangan` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_department`
--

CREATE TABLE `cmf_department` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DEPT_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `TEL_NO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `FAX_NO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DEPT_NO` int(11) NOT NULL,
  `DEPT_PARENT` int(11) NOT NULL,
  `MANAGER` text COLLATE utf8_unicode_ci NOT NULL,
  `LEADER1` text COLLATE utf8_unicode_ci NOT NULL,
  `LEADER2` text COLLATE utf8_unicode_ci NOT NULL,
  `DEPT_FUNC` text COLLATE utf8_unicode_ci NOT NULL,
  `orderno` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_danyuanyongtu`
--

CREATE TABLE `cmf_dict_danyuanyongtu` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_danyuanzhuangtai`
--

CREATE TABLE `cmf_dict_danyuanzhuangtai` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `cmf_dict_education` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Dict_EducationName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Dict_EducationCode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_huxing`
--

CREATE TABLE `cmf_dict_huxing` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_notify`
--

CREATE TABLE `cmf_dict_notify` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_satisfaction`
--

CREATE TABLE `cmf_dict_satisfaction` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Satisfaction` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `PY_Code` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_shiyongleixing`
--

CREATE TABLE `cmf_dict_shiyongleixing` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_weekday`
--

CREATE TABLE `cmf_dict_weekday` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_xingbie`
--

CREATE TABLE `cmf_dict_xingbie` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_xingzheng_qingjia`
--

CREATE TABLE `cmf_dict_xingzheng_qingjia` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dict_zhengjianleixing`
--

CREATE TABLE `cmf_dict_zhengjianleixing` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dorm_area`
--

CREATE TABLE `cmf_dorm_area` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `dormAreaName` varchar(22) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(22) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_dorm_building`
--

CREATE TABLE `cmf_dorm_building` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `buildingName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `buildingSum` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `floorNumber` int(11) NOT NULL,
  `masterTeacherOne` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manageBoundaryOne` int(11) NOT NULL,
  `masterTeacherTwo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manageBoundaryTwo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `masterTeacherThree` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manageBoundaryThree` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `masterTeacherFour` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manageBoundaryFour` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stuSex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_boolean`
--

CREATE TABLE `cmf_edu_boolean` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_building`
--

CREATE TABLE `cmf_edu_building` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `buildingNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `buildingName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_schoolcalendar`
--

CREATE TABLE `cmf_edu_schoolcalendar` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `holiday` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `term` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_teacherkaoqin`
--

CREATE TABLE `cmf_edu_teacherkaoqin` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `teacherUserName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `teacherName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dutyDate` date NOT NULL,
  `cardTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dutyId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dutyPosition` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dutyIP` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_banci`
--

CREATE TABLE `cmf_edu_xingzheng_banci` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `className` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `dutyTime` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `isStartTimeEnable` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `isEndTimeEnable` date NOT NULL,
  `startCardTime` datetime NOT NULL,
  `endCardTime` datetime NOT NULL,
  `startDelayCardTime` datetime NOT NULL,
  `endDelayCardTime` datetime NOT NULL,
  `laterTime` datetime NOT NULL,
  `earlyTime` datetime NOT NULL,
  `classManageOne` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `classManageTwo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_group`
--

CREATE TABLE `cmf_edu_xingzheng_group` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `departName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `groupName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memberName` text COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_jiabanbuxiu`
--

CREATE TABLE `cmf_edu_xingzheng_jiabanbuxiu` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `term` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `person` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `delayWorkTime` date NOT NULL,
  `delayWorkNum` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `buxiuTime` date NOT NULL,
  `buxiuClass` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `delayWorkStatus` int(11) NOT NULL,
  `delayWorkID` int(11) NOT NULL,
  `delayWorkMan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `delayWorkExTime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `buxiuStatus` int(11) NOT NULL,
  `buxiuFlowId` int(11) NOT NULL,
  `buxiuExMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `buxiuExTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `personName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_kaoqinbudeng`
--

CREATE TABLE `cmf_edu_xingzheng_kaoqinbudeng` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `term` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `person` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `time` date NOT NULL,
  `day` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `class` date NOT NULL,
  `budengProject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `budengStatus` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `workFlowID` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `exMan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `exTime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `personName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_kaoqinmingxi`
--

CREATE TABLE `cmf_edu_xingzheng_kaoqinmingxi` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `term` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `person` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `week` int(11) NOT NULL,
  `day` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `class` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `startRealityCard` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `startKaoqinStatus` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `endRealityCard` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `endKaoqinStatus` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `startCardBGN` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `startCardEND` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `endCartBGN` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `endCardEND` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `startDelayTime` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `delayMinutesNum` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `delayQuitTime` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `earlyQuitMinutesNum` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `personName` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_paiban`
--

CREATE TABLE `cmf_edu_xingzheng_paiban` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `termName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `week` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `kaoqinDate` date NOT NULL,
  `className` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `paipanPerson` text COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_qingjia`
--

CREATE TABLE `cmf_edu_xingzheng_qingjia` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `term` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `person` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `time` date NOT NULL,
  `week` int(11) NOT NULL,
  `class` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `qingjiaType` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `exStatus` int(11) NOT NULL,
  `workFlowID` int(11) NOT NULL,
  `exMan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `exTime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `personName` varchar(200) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_tiaoban`
--

CREATE TABLE `cmf_edu_xingzheng_tiaoban` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `term` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `person` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `oriStartTime` date NOT NULL,
  `oriStart` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `newStartTime` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `newStart` date NOT NULL,
  `exStatus` int(11) NOT NULL,
  `workFlowID` int(11) NOT NULL,
  `exMan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `exTime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `personName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_tiaobanxianghu`
--

CREATE TABLE `cmf_edu_xingzheng_tiaobanxianghu` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `term` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `oriDepartment` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `oriPerson` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `oriStartTime` date NOT NULL,
  `oriStart` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `newDepartment` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `newCruit` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `newStartTime` date NOT NULL,
  `newStart` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `exStatus` int(11) NOT NULL,
  `workFlowID` int(11) NOT NULL,
  `exMan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `exTime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `oriPersonName` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `newPersonName` varchar(200) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_tiaoxiububan`
--

CREATE TABLE `cmf_edu_xingzheng_tiaoxiububan` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `term` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `person` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tiaoxiuTime` date NOT NULL,
  `tiaoxiuStart` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `bubanTime` date NOT NULL,
  `bubanStart` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tiaoxiuExStatus` int(11) NOT NULL,
  `tiaoxiuWorkFlowID` int(11) NOT NULL,
  `tiaoxiuExMan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tiaoxiuExTime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `bubanExStatus` int(11) NOT NULL,
  `bubanWorkFlowID` int(11) NOT NULL,
  `bubanExMan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `bubanExTime` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `personName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_work_check_register`
--

CREATE TABLE `cmf_edu_xingzheng_work_check_register` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `schoolName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `studyYear` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `classRoom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staff` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fillDate` date NOT NULL,
  `dutyAndYearObj` text COLLATE utf8_unicode_ci NOT NULL,
  `personSummary` text COLLATE utf8_unicode_ci NOT NULL,
  `personSySign` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `personSySignDate` date NOT NULL,
  `recentYearPunishThings` text COLLATE utf8_unicode_ci NOT NULL,
  `classRoomIdeal` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rankandLevel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `classRoomSign` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `classRoomDate` date NOT NULL,
  `schoolOpinion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ExLeaderGroupSign` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ExLeaderGroupSignDate` date NOT NULL,
  `personOpinion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `personSign` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `personOpinionDate` date NOT NULL,
  `workFlow` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xingzheng_yearcheck`
--

CREATE TABLE `cmf_edu_xingzheng_yearcheck` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `term` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `classRoom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `kezhang` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pindetaiduAbillityScore` double(8,2) NOT NULL,
  `chuqinScore` double(8,2) NOT NULL,
  `workquantityScore` double(8,2) NOT NULL,
  `workjilvScore` double(8,2) NOT NULL,
  `workSpecScore` double(8,2) NOT NULL,
  `workxiaoguoScore` double(8,2) NOT NULL,
  `banzhurenScore` double(8,2) NOT NULL,
  `tempWorkScore` double(8,2) NOT NULL,
  `thesisScore` double(8,2) NOT NULL,
  `teachingScore` double(8,2) NOT NULL,
  `sumScore` double(8,2) NOT NULL,
  `exTime` date NOT NULL,
  `pindeAbillityScore` double(8,2) NOT NULL,
  `chuqinbiaoxianScore` double(8,2) NOT NULL,
  `workExScore` double(8,2) NOT NULL,
  `workJilvExScore` double(8,2) NOT NULL,
  `workSpecExScore` double(8,2) NOT NULL,
  `workxiaoguoExScore` double(8,2) NOT NULL,
  `banzhurenWorkExScore` double(8,2) NOT NULL,
  `tempWorkExScore` double(8,2) NOT NULL,
  `thesisExScore` double(8,2) NOT NULL,
  `techingExScore` double(8,2) NOT NULL,
  `sumExScore` double(8,2) NOT NULL,
  `keshiScoreMan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `classRoomScoreTime` date NOT NULL,
  `workflow` varchar(100) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_xueqiexec`
--

CREATE TABLE `cmf_edu_xueqiexec` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `termName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `recentTerm` int(11) NOT NULL,
  `isMorningHaveClass` int(11) NOT NULL,
  `isEveningHaveClass` int(11) NOT NULL,
  `studyYear` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `isSatClass` int(11) NOT NULL,
  `isSunClass` int(11) NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_zhongcengceping`
--

CREATE TABLE `cmf_edu_zhongcengceping` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `testName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `endTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `isUse` int(11) NOT NULL,
  `testManby` text COLLATE utf8_unicode_ci NOT NULL,
  `joinTestMan` text COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_zhongcengmingxi`
--

CREATE TABLE `cmf_edu_zhongcengmingxi` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `testName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `testManBy` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `uit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staff` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pindeEvl` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pindeMemo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `abillityPingjia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `abillityMemo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `studyPingjia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `studyMemo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jixiaoPingjia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jixiaoMemo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lianzhengPingjia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lianzhengMemo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `PingjiaMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pingjiaTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_zhongcengrenyuan`
--

CREATE TABLE `cmf_edu_zhongcengrenyuan` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `testName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `testManBy` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `uit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staff` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pindeDesp` text COLLATE utf8_unicode_ci NOT NULL,
  `pindeSelf` text COLLATE utf8_unicode_ci NOT NULL,
  `abillityDesp` text COLLATE utf8_unicode_ci NOT NULL,
  `abillitySelf` text COLLATE utf8_unicode_ci NOT NULL,
  `studyDesp` text COLLATE utf8_unicode_ci NOT NULL,
  `studySelf` text COLLATE utf8_unicode_ci NOT NULL,
  `jixiaoDesp` text COLLATE utf8_unicode_ci NOT NULL,
  `jixiaoSelf` text COLLATE utf8_unicode_ci NOT NULL,
  `lianzhengDesp` text COLLATE utf8_unicode_ci NOT NULL,
  `lianzhengSelf` text COLLATE utf8_unicode_ci NOT NULL,
  `attach` text COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_edu_zhongcengxingmu`
--

CREATE TABLE `cmf_edu_zhongcengxingmu` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `projects` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `scoreinvalue` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_email`
--

CREATE TABLE `cmf_email` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `FROM_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `customers` text COLLATE utf8_unicode_ci NOT NULL,
  `supplys` text COLLATE utf8_unicode_ci NOT NULL,
  `others` text COLLATE utf8_unicode_ci NOT NULL,
  `SUBJECT` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `CONTENT` text COLLATE utf8_unicode_ci NOT NULL,
  `SEND_TIME` datetime NOT NULL,
  `ATTACHMENT_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `ATTACHMENT_NAME` text COLLATE utf8_unicode_ci NOT NULL,
  `SEND_FLAG` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_emailstate`
--

CREATE TABLE `cmf_emailstate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_exchange`
--

CREATE TABLE `cmf_exchange` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customid` int(11) NOT NULL,
  `prodid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `xinghao` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `guige` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `integral` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `createman` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `exchangenum` int(11) NOT NULL,
  `stockid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `beizhu` text COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fahuodan`
--

CREATE TABLE `cmf_fahuodan` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `dingdanbillid` int(11) NOT NULL,
  `fahuodan` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fahuoren` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fahuodate` datetime NOT NULL,
  `shouhuoren` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mailcode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fahuotype` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `package` int(11) NOT NULL,
  `weight` double(8,2) NOT NULL,
  `yunfei` double(8,2) NOT NULL,
  `jiesuantype` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `totalnum` double NOT NULL,
  `totalmoney` double NOT NULL,
  `outtype` varchar(20) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fahuodan_detail`
--

CREATE TABLE `cmf_fahuodan_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` double NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `jine` double NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fahuostate`
--

CREATE TABLE `cmf_fahuostate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fahuotype`
--

CREATE TABLE `cmf_fahuotype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `kuaididan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `design` text COLLATE utf8_unicode_ci NOT NULL,
  `linkman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `shunxu` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_feiyongclasse`
--

CREATE TABLE `cmf_feiyongclasse` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `classname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `kind` smallint(6) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_feiyongrecord`
--

CREATE TABLE `cmf_feiyongrecord` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `typeid` int(11) NOT NULL,
  `jine` double NOT NULL,
  `kind` smallint(6) NOT NULL,
  `accountid` int(11) NOT NULL,
  `chanshengdate` date NOT NULL,
  `createman` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_feiyongtype`
--

CREATE TABLE `cmf_feiyongtype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `typename` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `classid` int(11) NOT NULL,
  `iflock` smallint(6) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedasset`
--

CREATE TABLE `cmf_fixedasset` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `setName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `setNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `setPichi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `setType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `purchaseID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supply` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `person` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `size` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `count` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `unit` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `place` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `purchaseDate` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `billNum` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `IDNumber` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `useDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `useDirect` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `testDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dutyMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `qiyongDate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedassetbaofei`
--

CREATE TABLE `cmf_fixedassetbaofei` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `setName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `setNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `baofeiTime` date NOT NULL,
  `baofeiApplyMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedassetgroup`
--

CREATE TABLE `cmf_fixedassetgroup` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sort` int(11) NOT NULL,
  `preLeveType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedassetin`
--

CREATE TABLE `cmf_fixedassetin` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `setName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `setNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedassetleibie`
--

CREATE TABLE `cmf_fixedassetleibie` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedassetout`
--

CREATE TABLE `cmf_fixedassetout` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `setName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `setNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jielingCondition` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jielingMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedassetstatus`
--

CREATE TABLE `cmf_fixedassetstatus` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedassettiaoku`
--

CREATE TABLE `cmf_fixedassettiaoku` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `setName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `setNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `oriInDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nowInDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedassettui`
--

CREATE TABLE `cmf_fixedassettui` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `setName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `setNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `returnMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fixedassetweixiu`
--

CREATE TABLE `cmf_fixedassetweixiu` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `setName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `setNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `repairUnit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `repairMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `thingIntro` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creatTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_fukuanplan`
--

CREATE TABLE `cmf_fukuanplan` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `supplyid` int(11) NOT NULL,
  `caigoubillid` int(11) NOT NULL,
  `plandate` date NOT NULL,
  `qici` int(11) NOT NULL,
  `jine` double NOT NULL,
  `createman` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `ifpay` varchar(20) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- 表的结构 `cmf_fukuanrecord`
--

CREATE TABLE `cmf_fukuanrecord` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `supplyid` int(11) NOT NULL,
  `caigoubillid` int(11) NOT NULL,
  `qici` int(11) NOT NULL,
  `paydate` date NOT NULL,
  `jine` double NOT NULL,
  `ifkaipiao` int(11) NOT NULL,
  `accountid` int(11) NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `guanlianplanid` int(11) NOT NULL,
  `oddment` double NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_gb_marriage`
--

CREATE TABLE `cmf_gb_marriage` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_gb_national`
--

CREATE TABLE `cmf_gb_national` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_gb_political`
--

CREATE TABLE `cmf_gb_political` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_gb_sex`
--

CREATE TABLE `cmf_gb_sex` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_boolean`
--

CREATE TABLE `cmf_hrms_boolean` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_educationalexperience`
--

CREATE TABLE `cmf_hrms_educationalexperience` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `school` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `professional` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `experience` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proveMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` date NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_expense`
--

CREATE TABLE `cmf_hrms_expense` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `feeType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `feeNumber` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `useReason` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_expense_type`
--

CREATE TABLE `cmf_hrms_expense_type` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_file`
--

CREATE TABLE `cmf_hrms_file` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `marriage` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `national` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `political` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `IDNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hukou` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jiguan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `insuranceNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `postalCode` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `studyExp` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `professional` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `college` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staff` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staffName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `politicalLevel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `computerLevel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `foreign` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `foreignLevel` int(11) NOT NULL,
  `whenWork` date NOT NULL,
  `workTime` int(11) NOT NULL,
  `workStatus` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `special` text COLLATE utf8_unicode_ci NOT NULL,
  `memo` text COLLATE utf8_unicode_ci NOT NULL,
  `attach` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `entryUnitDate` date NOT NULL,
  `unitWorkTime` int(11) NOT NULL,
  `quitDate` date NOT NULL,
  `nowContractDate` date NOT NULL,
  `nowContractEndDate` date NOT NULL,
  `tuixiuDate` date NOT NULL,
  `tuixiuAge` int(11) NOT NULL,
  `workInFormal` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `firstContractDate` date NOT NULL,
  `techLevel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `techExDate` date NOT NULL,
  `staffType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staffbyName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staffTechScore` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bank` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bankNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `salaryLevel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staffyinliBirthday` date NOT NULL,
  `personDesp` text COLLATE utf8_unicode_ci NOT NULL,
  `passport` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bloody` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `height` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `weight` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `eyesight` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `health` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `drivers` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `workExp` text COLLATE utf8_unicode_ci NOT NULL,
  `workExpAttach` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `socialRelation` text COLLATE utf8_unicode_ci NOT NULL,
  `socialRelationAttach` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `recoverStaffDate` date NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_file_fuzhi`
--

CREATE TABLE `cmf_hrms_file_fuzhi` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `telephone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `studyExp` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `national` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` text COLLATE utf8_unicode_ci NOT NULL,
  `recoverDate` date NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_file_lizhi`
--

CREATE TABLE `cmf_hrms_file_lizhi` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `telephone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `studyExp` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `national` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` text COLLATE utf8_unicode_ci NOT NULL,
  `quitDate` date NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_file_luyong`
--

CREATE TABLE `cmf_hrms_file_luyong` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `national` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `political` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `IDNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jiguan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `college` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `studyExp` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staffName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `professional` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` date NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_laboringskill`
--

CREATE TABLE `cmf_hrms_laboringskill` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `certifiationUnit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `certificationDep` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `certificationName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proveMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` date NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_reward_punishment`
--

CREATE TABLE `cmf_hrms_reward_punishment` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `punishmentName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `punishmentReason` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `punishmentContent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalDep` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalTime` date NOT NULL,
  `shengxiaobyDate` date NOT NULL,
  `memo` text COLLATE utf8_unicode_ci NOT NULL,
  `cancelTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cancelReason` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` date NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_r_p`
--

CREATE TABLE `cmf_hrms_r_p` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_r_p_name`
--

CREATE TABLE `cmf_hrms_r_p_name` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `classType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_r_p_status`
--

CREATE TABLE `cmf_hrms_r_p_status` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_socialrelation`
--

CREATE TABLE `cmf_hrms_socialrelation` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `relation` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `political` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staff` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` date NOT NULL,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `named` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `workAddress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_transfer`
--

CREATE TABLE `cmf_hrms_transfer` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `transferDate` date NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `afterDep` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beforeStaff` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `afterStaff` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beforegangwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `aftergangwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `transferReason` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staffMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jobsSituation` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `transferType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creatTime` date NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_transfer_type`
--

CREATE TABLE `cmf_hrms_transfer_type` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_worker_hetong`
--

CREATE TABLE `cmf_hrms_worker_hetong` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `signTime` date NOT NULL,
  `endTime` date NOT NULL,
  `workedYear` int(11) NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_worker_zhengzhao`
--

CREATE TABLE `cmf_hrms_worker_zhengzhao` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `certificationType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `certificationName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `certificationScan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prizeTime` date NOT NULL,
  `agency` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` date NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_worker_zhicheng`
--

CREATE TABLE `cmf_hrms_worker_zhicheng` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staffName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `getTime` date NOT NULL,
  `agency` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` date NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_workexperience`
--

CREATE TABLE `cmf_hrms_workexperience` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `workId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inDepartment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `company` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staff` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `proveMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` date NOT NULL,
  `quitReason` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_work_status`
--

CREATE TABLE `cmf_hrms_work_status` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_zhiwei_status`
--

CREATE TABLE `cmf_hrms_zhiwei_status` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_zpjihua`
--

CREATE TABLE `cmf_hrms_zpjihua` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `need` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `personNumber` int(11) NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `applyMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `applyTime` date NOT NULL,
  `exMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `exTime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `exStatus` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_hrms_zprencaiku`
--

CREATE TABLE `cmf_hrms_zprencaiku` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `contact` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `IDNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `national` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `political` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jiguan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hukouInPos` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `studyExp` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staffName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `college` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `professional` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `secPro` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `familyTelephone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `familyAddr` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `postalCode` int(11) NOT NULL,
  `edution` text COLLATE utf8_unicode_ci NOT NULL,
  `workExp` text COLLATE utf8_unicode_ci NOT NULL,
  `selfEval` text COLLATE utf8_unicode_ci NOT NULL,
  `englishAbillity` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `traingExp` text COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `attach` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `projectExp` text COLLATE utf8_unicode_ci NOT NULL,
  `resumeLetter` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proObject` text COLLATE utf8_unicode_ci NOT NULL,
  `luyongStatus` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_huikuanplan`
--

CREATE TABLE `cmf_huikuanplan` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `dingdanbillid` int(11) NOT NULL,
  `plandate` date NOT NULL,
  `qici` int(11) NOT NULL,
  `jine` double NOT NULL,
  `createman` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `ifpay` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `billtype` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_huikuanrecord`
--

CREATE TABLE `cmf_huikuanrecord` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `dingdanbillid` int(11) NOT NULL,
  `qici` int(11) NOT NULL,
  `paydate` date NOT NULL,
  `jine` double NOT NULL,
  `ifkaipiao` int(11) NOT NULL,
  `accountid` int(11) NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `guanlianplanid` int(11) NOT NULL,
  `oddment` double NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_ifneed`
--

CREATE TABLE `cmf_ifneed` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_important`
--

CREATE TABLE `cmf_important` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_inorout`
--

CREATE TABLE `cmf_inorout` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_kaipiaorecord`
--

CREATE TABLE `cmf_kaipiaorecord` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `dingdanbillid` int(11) NOT NULL,
  `kaipiaoneirong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `piaojutype` int(11) NOT NULL,
  `piaojujine` double NOT NULL,
  `fapiaono` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `kaipiaodate` date NOT NULL,
  `qici` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `kaipiaoren` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ifhuikuan` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `huikuanid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_kaipiaostate`
--

CREATE TABLE `cmf_kaipiaostate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_linkman`
--

CREATE TABLE `cmf_linkman` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `linkmanname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `linkmanpy` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fax` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `postcode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mark` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `business` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `businessexpian` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplycn` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `customerid` int(11) NOT NULL,
  `mobile` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_measure`
--

CREATE TABLE `cmf_measure` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` date NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_message`
--

CREATE TABLE `cmf_message` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `msgtype` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `guanlianid` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `readtime` datetime NOT NULL,
  `flag` smallint(6) NOT NULL,
  `attime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_modifyrecord`
--

CREATE TABLE `cmf_modifyrecord` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tablename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `keyfield` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `keyvalue` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `modifycontent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `ip` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_notify`
--

CREATE TABLE `cmf_notify` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `to_user` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `from_user` date NOT NULL,
  `ATTACHMENT` int(11) NOT NULL,
  `createtime` date NOT NULL,
  `ifread` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ifsms` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `grade` datetime NOT NULL,
  `ifemail` double NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_numzero`
--

CREATE TABLE `cmf_numzero` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeguihuanstate`
--

CREATE TABLE `cmf_officeguihuanstate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeproduct`
--

CREATE TABLE `cmf_officeproduct` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `officeName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `officeNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `officeType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `specSize` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `unit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `warehouseInfo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `totalCount` double(8,2) NOT NULL,
  `brand` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `savedPlace` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeproductbaofei`
--

CREATE TABLE `cmf_officeproductbaofei` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `officeName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `officeNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inWarehouse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `baofeiQuantity` int(11) NOT NULL,
  `baofeiApplyMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dutyMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creatTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeproductcangku`
--

CREATE TABLE `cmf_officeproductcangku` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `warehouseName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `warehouseMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telphone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `warehouseAddress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeproductgroup`
--

CREATE TABLE `cmf_officeproductgroup` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sort` int(11) NOT NULL,
  `preClassType` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeproductin`
--

CREATE TABLE `cmf_officeproductin` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `officeName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `officeNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inWarehouse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inQuantity` int(11) NOT NULL,
  `staffMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` date NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeproductleibie`
--

CREATE TABLE `cmf_officeproductleibie` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeproductout`
--

CREATE TABLE `cmf_officeproductout` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `officeName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `officeNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `outWarehouse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `outQuantity` int(11) NOT NULL,
  `applyMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `outStatus` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `isExamine` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,
  `returnLimited` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `isReturn` int(11) NOT NULL,
  `returnReiceiver` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `realReturnDate` datetime NOT NULL,
  `examineTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeproducttiaoku`
--

CREATE TABLE `cmf_officeproducttiaoku` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `officeName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `officeNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `transferOutWarehouse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `transferInWarehouse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `transferQuantity` int(11) NOT NULL,
  `staffMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creatTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,
  `isExamine` int(11) NOT NULL,
  `exTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_officeproducttui`
--

CREATE TABLE `cmf_officeproducttui` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `officeName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `officeNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quitWarehouse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quitWareQuantity` int(11) NOT NULL,
  `quitObjects` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `staffMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `approvalMan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creatTime` datetime NOT NULL,
  `price` double(8,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `count` double(8,2) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_office_task`
--

CREATE TABLE `cmf_office_task` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TASK_TYPE` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `INTERVAL` int(11) NOT NULL,
  `EXEC_TIME` time NOT NULL,
  `LAST_EXEC` date NOT NULL,
  `TASK_URL` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `TASK_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `TASK_DESC` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `TASK_CODE` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `USE_FLAG` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `SYS_TASK` varchar(4) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_paystate`
--

CREATE TABLE `cmf_paystate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_product`
--

CREATE TABLE `cmf_product` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `productname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `measureid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mode` text COLLATE utf8_unicode_ci NOT NULL,
  `standard` text COLLATE utf8_unicode_ci NOT NULL,
  `producttype` int(11) NOT NULL,
  `storemin` double(8,2) NOT NULL,
  `storemax` double(8,2) NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sellprice` double NOT NULL,
  `productcn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sellprice2` double NOT NULL,
  `sellprice3` double NOT NULL,
  `fileaddress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `oldproductid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ifkucun` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `relatefiles` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplyid` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `hascolor` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `sellprice4` double NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_productcolor`
--

CREATE TABLE `cmf_productcolor` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `colorvalue` varchar(20) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_producttype`
--

CREATE TABLE `cmf_producttype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Code` int(11) NOT NULL,
  `viewtype` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `leverno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `isbuyplan` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `isautoout` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `parentid` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_productzuzhuang`
--

CREATE TABLE `cmf_productzuzhuang` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `outstoreid` int(11) NOT NULL,
  `instoreid` int(11) NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `totalmoney` double NOT NULL,
  `state` int(11) NOT NULL,
  `outstoreshenhe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `instoreshenhe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `outshenhetime` datetime NOT NULL,
  `inshenhetime` datetime NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_productzuzhuang2_detail`
--

CREATE TABLE `cmf_productzuzhuang2_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` double NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `jine` double NOT NULL,
  `oldprodid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opertype` smallint(6) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_productzuzhuangstate`
--

CREATE TABLE `cmf_productzuzhuangstate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_productzuzhuang_detail`
--

CREATE TABLE `cmf_productzuzhuang_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` double NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `jine` double NOT NULL,
  `oldprodid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opertype` smallint(6) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_property`
--

CREATE TABLE `cmf_property` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_recycle_bin`
--

CREATE TABLE `cmf_recycle_bin` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `object_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `table_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_salemode`
--

CREATE TABLE `cmf_salemode` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_sellbilltype`
--

CREATE TABLE `cmf_sellbilltype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_sellcontract_jiaofu`
--

CREATE TABLE `cmf_sellcontract_jiaofu` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customerid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hetongid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `productid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `planid` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `price` double(8,2) NOT NULL,
  `jieshouren` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jiaofudate` date NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `beiyong1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beiyong2` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beiyong3` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jine` double(8,2) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_sellplanmain`
--

CREATE TABLE `cmf_sellplanmain` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplyid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `chanceid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sellplanno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `totalmoney` double(8,2) NOT NULL,
  `paytype` int(11) NOT NULL,
  `huikuanjine` double(8,2) NOT NULL,
  `fahuojine` double(8,2) NOT NULL,
  `kaipiaojine` double(8,2) NOT NULL,
  `plandate` date NOT NULL,
  `zuiwanfahuodate` date NOT NULL,
  `qianyueren` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` smallint(6) NOT NULL,
  `beizhu` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `fileaddress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fahuostate` int(11) NOT NULL,
  `gaiyao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `storeid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `linkman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `billtype` smallint(6) NOT NULL,
  `ifpay` int(11) NOT NULL,
  `beiyong` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `kaipiaostate` smallint(6) NOT NULL,
  `fapiaoneirong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fapiaotype` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fapiaono` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fahuotype` int(11) NOT NULL,
  `fahuodan` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fahuoyunfei` double NOT NULL,
  `yunfeitype` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `oddment` double NOT NULL,
  `integral` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- 表的结构 `cmf_sellplanmain_detail`
--

CREATE TABLE `cmf_sellplanmain_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` double NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `jine` double NOT NULL,
  `chukunum` double NOT NULL,
  `plandate` date NOT NULL,
  `qici` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `yaoqiu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `iftixing` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `lirun` double NOT NULL,
  `oldprodid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opertype` smallint(6) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- 表的结构 `cmf_sellplanmain_detail_color`
--

CREATE TABLE `cmf_sellplanmain_detail_color` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `color` int(11) NOT NULL,
  `num` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_sellplanstate`
--

CREATE TABLE `cmf_sellplanstate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_shoupiaorecord`
--

CREATE TABLE `cmf_shoupiaorecord` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `supplyid` int(11) NOT NULL,
  `caigoubillid` int(11) NOT NULL,
  `kaipiaoneirong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `piaojutype` int(11) NOT NULL,
  `piaojujine` double NOT NULL,
  `fapiaono` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `kaipiaodate` date NOT NULL,
  `qici` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `kaipiaoren` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ifhuikuan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `huikuanid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_sms_sendlist`
--

CREATE TABLE `cmf_sms_sendlist` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `msg` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nowtime` datetime NOT NULL,
  `attime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `destcount` int(11) NOT NULL,
  `result` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `errmsg` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `trytimes` int(11) NOT NULL,
  `userid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batchid` int(11) NOT NULL,
  `destid` text COLLATE utf8_unicode_ci NOT NULL,
  `leftcount` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_stockchangemain`
--

CREATE TABLE `cmf_stockchangemain` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `outstoreid` int(11) NOT NULL,
  `instoreid` int(11) NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `outstoreshenhe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `instoreshenhe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `outshenhetime` datetime NOT NULL,
  `inshenhetime` datetime NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `totalmoney` double NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_stockchangemain_detail`
--

CREATE TABLE `cmf_stockchangemain_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` double NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `jine` double NOT NULL,
  `oldprodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `opertype` smallint(6) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_stockchangestate`
--

CREATE TABLE `cmf_stockchangestate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_stockinmain`
--

CREATE TABLE `cmf_stockinmain` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `storeid` int(11) NOT NULL,
  `indate` datetime NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `caigoubillid` int(11) NOT NULL,
  `tuihuobillid` int(11) NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beiyong1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beiyong2` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beiyong3` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `totalnum` double NOT NULL,
  `totalmoney` double NOT NULL,
  `instoreshenhe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `intype` varchar(50) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_stockinmain_detail`
--

CREATE TABLE `cmf_stockinmain_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` double NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `jine` double NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_stockinmain_detail_color`
--

CREATE TABLE `cmf_stockinmain_detail_color` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `color` int(11) NOT NULL,
  `num` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_stockoutmain`
--

CREATE TABLE `cmf_stockoutmain` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `storeid` int(11) NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `totalnum` double NOT NULL,
  `totalmoney` double NOT NULL,
  `dingdanbillid` int(11) NOT NULL,
  `outdate` datetime NOT NULL,
  `outstoreshenhe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `outtype` varchar(50) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_stockoutmain_detail`
--

CREATE TABLE `cmf_stockoutmain_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` double NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `jine` double NOT NULL,
  `avgprice` double NOT NULL,
  `lirun` double NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_stockoutmain_detail_color`
--

CREATE TABLE `cmf_stockoutmain_detail_color` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `color` int(11) NOT NULL,
  `num` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_store`
--

CREATE TABLE `cmf_store` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `num` double NOT NULL,
  `price` double NOT NULL,
  `storeid` int(11) NOT NULL,
  `memo` varchar(500) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_storeaccesstype`
--

CREATE TABLE `cmf_storeaccesstype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inout` int(11) NOT NULL,
  `linkaction` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_storecheck`
--

CREATE TABLE `cmf_storecheck` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `storeid` int(11) NOT NULL,
  `totalmoney` double NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_storecheck_detail`
--

CREATE TABLE `cmf_storecheck_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodguige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodxinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proddanwei` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(8,2) NOT NULL,
  `zhekou` double(8,2) NOT NULL,
  `num` double NOT NULL,
  `beizhu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mainrowid` int(11) NOT NULL,
  `jine` double NOT NULL,
  `oldprodid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `opertype` smallint(6) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_store_color`
--

CREATE TABLE `cmf_store_color` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `color` int(11) NOT NULL,
  `num` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_store_init`
--

CREATE TABLE `cmf_store_init` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prodid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `storeid` int(11) NOT NULL,
  `price` double NOT NULL,
  `num` double NOT NULL,
  `jine` double NOT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` int(11) NOT NULL,
  `prodname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `guige` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `xinghao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `danwei` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `typename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_supply`
--

CREATE TABLE `cmf_supply` (
  `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `supplyid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplyname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplyshortname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enterstype` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `calling` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `linkman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `artificialperson` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `chargesection` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `postalcode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fax` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `contactaddress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `netword` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bank` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `accountnumber` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `startdate` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enddate` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `style` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplycn` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sysuser` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prodtype` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `amtagio` double(255,2) NOT NULL,
  `remark` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `recommand` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `payaccount` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `paymoney` double NOT NULL,
  `factpaymoney` double NOT NULL,
  `nopaymoney` double NOT NULL,
  `datascope` double NOT NULL,
  `yufukuan` double NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- 表的结构 `cmf_supplylever`
--

CREATE TABLE `cmf_supplylever` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_supplylinkman`
--

CREATE TABLE `cmf_supplylinkman` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `supplyname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `beiyong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fax` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `postcode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mark` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `business` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `businessexpian` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplycn` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplyid` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_supplyproduct`
--

CREATE TABLE `cmf_supplyproduct` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `supplyid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `productid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `productprice` double(8,2) NOT NULL,
  `pricedate` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `explianstr` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cycle` double NOT NULL,
  `fujian` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_systemconfig`
--

CREATE TABLE `cmf_systemconfig` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `UNIT` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `STATUS` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `MEMO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `SHORTCODE` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `TRANSCODE` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_systemhelp`
--

CREATE TABLE `cmf_systemhelp` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `systemhelpname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `integer` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_systemlang`
--

CREATE TABLE `cmf_systemlang` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `fieldname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tablename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `chinese` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `english` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_systemprivate`
--

CREATE TABLE `cmf_systemprivate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `CONTENT` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_systemprivateconfig`
--

CREATE TABLE `cmf_systemprivateconfig` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `category` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `table` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `object` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `views` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `column` int(11) NOT NULL,
  `other` text COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_systemprivateinc`
--

CREATE TABLE `cmf_systemprivateinc` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `FILE` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `MODULE` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DEPT_ID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DEPT_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ROLE_ID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ROLE_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `USER_ID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `USER_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_systemtable`
--

CREATE TABLE `cmf_systemtable` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `systemtablename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_system_log`
--

CREATE TABLE `cmf_system_log` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `loginaction` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DATE` datetime NOT NULL,
  `REMOTE_ADDR` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `HTTP_USER_AGENT` date NOT NULL,
  `QUERY_STRING` int(11) NOT NULL,
  `SCRIPT_NAME` date NOT NULL,
  `USERID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `SQLTEXT` text COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_system_logall`
--

CREATE TABLE `cmf_system_logall` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `currentTime` datetime NOT NULL,
  `executeTime` int(11) NOT NULL,
  `SQL` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Slow_launch_threads` int(11) NOT NULL,
  `Threads_cached` int(11) NOT NULL,
  `Threads_connected` int(11) NOT NULL,
  `Threads_created` int(11) NOT NULL,
  `Threads_running` int(11) NOT NULL,
  `Qcache_free_blocks` int(11) NOT NULL,
  `Qcache_free_memory` int(11) NOT NULL,
  `Qcache_hits` int(11) NOT NULL,
  `Qcache_inserts` int(11) NOT NULL,
  `Qcache_lowmem_prunes` int(11) NOT NULL,
  `Qcache_not_cached` int(11) NOT NULL,
  `Qcache_queries_in_cache` int(11) NOT NULL,
  `Qcache_total_blocks` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_system_logtype`
--

CREATE TABLE `cmf_system_logtype` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `CODE` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_sys_code`
--

CREATE TABLE `cmf_sys_code` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `CODE_NO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `CODE_NAME` text COLLATE utf8_unicode_ci NOT NULL,
  `CODE_ORDER` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `PARENT_NO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `CODE_FLAG` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_sys_menu`
--

CREATE TABLE `cmf_sys_menu` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `MENU_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `IMAGE` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ENGLISHNAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_unit`
--

CREATE TABLE `cmf_unit` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `UNIT_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `TEL_NO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `FAX_NO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `POST_NO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ADDRESS` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `URL` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `EMAIL` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `BANK_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `BANK_NO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `numzero` double(8,2) NOT NULL,
  `buybillid` int(11) NOT NULL,
  `sellbillid` int(11) NOT NULL,
  `stockinbillid` int(11) NOT NULL,
  `stockoutbillid` int(11) NOT NULL,
  `storecheckbillid` int(11) NOT NULL,
  `stockchangebillid` int(11) NOT NULL,
  `zuzhuangbillid` int(11) NOT NULL,
  `feiyongbillid` int(11) NOT NULL,
  `prepaybillid` int(11) NOT NULL,
  `preshoubillid` int(11) NOT NULL,
  `dingjiagongshi` int(11) NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_unitprop`
--

CREATE TABLE `cmf_unitprop` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_workplanmain`
--

CREATE TABLE `cmf_workplanmain` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` smallint(6) NOT NULL,
  `zhuti` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `kind` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `begintime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `zhixingren` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `finishtime` datetime NOT NULL,
  `shenheren` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `shenchastate` smallint(6) NOT NULL,
  `shenhetime` datetime NOT NULL,
  `shenhepishi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fujian` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ifsms` smallint(6) NOT NULL,
  `ifemail` smallint(6) NOT NULL,
  `guanlianshiwu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `guanlianurl` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `guanlianid` int(11) NOT NULL,
  `lastzhixingtime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_workplanmain_detail`
--

CREATE TABLE `cmf_workplanmain_detail` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `mainrowid` int(11) NOT NULL,
  `createman` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `begintime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `result` smallint(6) NOT NULL,
  `nexttime` datetime NOT NULL,
  `nextcontent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fujian` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_workplanshenhe`
--

CREATE TABLE `cmf_workplanshenhe` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_workplanstate`
--

CREATE TABLE `cmf_workplanstate` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_workreport`
--

CREATE TABLE `cmf_workreport` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `createman` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `workdate` date NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `shenheren` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `piyu` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `shenhetime` datetime NOT NULL,
  `state` varchar(6) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_wu_boolean`
--

CREATE TABLE `cmf_wu_boolean` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `yesorno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `memo` text COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `cmf_wu_buildinginformation`
--

CREATE TABLE `cmf_wu_buildinginformation` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `buildingNo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `buildingName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `buildingLocate` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `unit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `buildingStruct` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sum` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `startDate` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `finishDate` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jiaofuDate` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `useIn` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `area` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `saledNum` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rentNum` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `builder` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `memo` text COLLATE utf8_unicode_ci NOT NULL,
  `optManage` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `areaName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


SET FOREIGN_KEY_CHECKS = 1;
