/*
SQLyog Ultimate v9.30 
MySQL - 5.7.19-log : Database - jeesite_trading
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jeesite_trading` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jeesite_trading`;

/*Table structure for table `js_gen_table` */

DROP TABLE IF EXISTS `js_gen_table`;

CREATE TABLE `js_gen_table` (
  `table_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '表名',
  `class_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '实体类名称',
  `comments` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '表说明',
  `parent_table_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联父表的表名',
  `parent_table_fk_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本表关联父表的外键名',
  `data_source_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据源名称',
  `tpl_category` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '使用的模板',
  `package_name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成模块名',
  `sub_module_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成子模块名',
  `function_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成功能名',
  `function_name_simple` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成功能名（简写）',
  `function_author` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成功能作者',
  `gen_base_dir` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '生成基础路径',
  `options` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`table_name`),
  KEY `idx_gen_table_ptn` (`parent_table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='代码生成表';

/*Data for the table `js_gen_table` */

insert  into `js_gen_table`(`table_name`,`class_name`,`comments`,`parent_table_name`,`parent_table_fk_name`,`data_source_name`,`tpl_category`,`package_name`,`module_name`,`sub_module_name`,`function_name`,`function_name_simple`,`function_author`,`gen_base_dir`,`options`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`) values ('test_data','TestData','测试数据',NULL,NULL,NULL,'crud','com.jeesite.modules','test','','测试数据','数据','ThinkGem',NULL,'{\"isHaveDelete\":\"1\",\"isFileUpload\":\"1\",\"isHaveDisableEnable\":\"1\",\"isImageUpload\":\"1\"}','system','2019-03-08 16:53:36','system','2019-03-08 16:53:36',NULL),('test_data_child','TestDataChild','测试数据子表','test_data','test_data_id',NULL,'crud','com.jeesite.modules','test','','测试子表','数据','ThinkGem',NULL,NULL,'system','2019-03-08 16:53:38','system','2019-03-08 16:53:38',NULL),('test_tree','TestTree','测试树表',NULL,NULL,NULL,'treeGrid','com.jeesite.modules','test','','测试树表','数据','ThinkGem',NULL,'{\"treeViewName\":\"tree_name\",\"isHaveDelete\":\"1\",\"treeViewCode\":\"tree_code\",\"isFileUpload\":\"1\",\"isHaveDisableEnable\":\"1\",\"isImageUpload\":\"1\"}','system','2019-03-08 16:53:38','system','2019-03-08 16:53:38',NULL);

/*Table structure for table `js_gen_table_column` */

DROP TABLE IF EXISTS `js_gen_table_column`;

CREATE TABLE `js_gen_table_column` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `table_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '表名',
  `column_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '列名',
  `column_sort` decimal(10,0) DEFAULT NULL COMMENT '列排序（升序）',
  `column_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型',
  `column_label` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '列标签名',
  `comments` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '列备注说明',
  `attr_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类的属性名',
  `attr_type` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类的属性类型',
  `is_pk` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否主键',
  `is_null` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否可为空',
  `is_insert` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否插入字段',
  `is_update` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否更新字段',
  `is_list` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否列表字段',
  `is_query` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否查询字段',
  `query_type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '查询方式',
  `is_edit` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否编辑字段',
  `show_type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表单类型',
  `options` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '其它生成选项',
  PRIMARY KEY (`id`),
  KEY `idx_gen_table_column_tn` (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='代码生成表列';

/*Data for the table `js_gen_table_column` */

insert  into `js_gen_table_column`(`id`,`table_name`,`column_name`,`column_sort`,`column_type`,`column_label`,`comments`,`attr_name`,`attr_type`,`is_pk`,`is_null`,`is_insert`,`is_update`,`is_list`,`is_query`,`query_type`,`is_edit`,`show_type`,`options`) values ('1103941827021410304','test_data','id','10','varchar(64)','编号','编号','id','String','1','0','1',NULL,NULL,NULL,NULL,'1','hidden','{\"fieldValid\":\"abc\"}'),('1103941827126267904','test_data','test_input','20','varchar(200)','单行文本','单行文本','testInput','String',NULL,'1','1','1','1','1','LIKE','1','input',NULL),('1103941828065792000','test_data','test_textarea','30','varchar(200)','多行文本','多行文本','testTextarea','String',NULL,'1','1','1','1','1','LIKE','1','textarea','{\"isNewLine\":\"1\",\"gridRowCol\":\"12/2/10\"}'),('1103941828107735040','test_data','test_select','40','varchar(10)','下拉框','下拉框','testSelect','String',NULL,'1','1','1','1','1',NULL,'1','select','{\"dictName\":\"sys_menu_type\",\"dictType\":\"sys_menu_type\"}'),('1103941828166455296','test_data','test_select_multiple','50','varchar(200)','下拉多选','下拉多选','testSelectMultiple','String',NULL,'1','1','1','1','1',NULL,'1','select_multiple','{\"dictName\":\"sys_menu_type\",\"dictType\":\"sys_menu_type\"}'),('1103941829424746496','test_data','test_radio','60','varchar(10)','单选框','单选框','testRadio','String',NULL,'1','1','1','1','1',NULL,'1','radio','{\"dictName\":\"sys_menu_type\",\"dictType\":\"sys_menu_type\"}'),('1103941829479272448','test_data','test_checkbox','70','varchar(200)','复选框','复选框','testCheckbox','String',NULL,'1','1','1','1','1',NULL,'1','checkbox','{\"dictName\":\"sys_menu_type\",\"dictType\":\"sys_menu_type\"}'),('1103941829953228800','test_data','test_date','80','datetime','日期选择','日期选择','testDate','java.util.Date',NULL,'1','1','1','1','1','BETWEEN','1','date',NULL),('1103941830037114880','test_data','test_datetime','90','datetime','日期时间','日期时间','testDatetime','java.util.Date',NULL,'1','1','1','1','1','BETWEEN','1','datetime',NULL),('1103941830104223744','test_data','test_user_code','100','varchar(64)','用户选择','用户选择','testUser','com.jeesite.modules.sys.entity.User',NULL,'1','1','1','1','1',NULL,'1','userselect',NULL),('1103941830519459840','test_data','test_office_code','110','varchar(64)','机构选择','机构选择','testOffice','com.jeesite.modules.sys.entity.Office',NULL,'1','1','1','1','1',NULL,'1','officeselect',NULL),('1103941830557208576','test_data','test_area_code','120','varchar(64)','区域选择','区域选择','testAreaCode|testAreaName','String',NULL,'1','1','1','1','1',NULL,'1','areaselect',NULL),('1103941830615928832','test_data','test_area_name','130','varchar(100)','区域名称','区域名称','testAreaName','String',NULL,'1','1','1','1','0','LIKE','0','input',NULL),('1103941830884364288','test_data','status','140','char(1)','状态','状态（0正常 1删除 2停用）','status','String',NULL,'0','1','0','1','1',NULL,NULL,'select','{\"dictName\":\"sys_search_status\",\"dictType\":\"sys_search_status\"}'),('1103941830934695936','test_data','create_by','150','varchar(64)','创建者','创建者','createBy','String',NULL,'0','1',NULL,NULL,NULL,NULL,NULL,'input',NULL),('1103941830989221888','test_data','create_date','160','datetime','创建时间','创建时间','createDate','java.util.Date',NULL,'0','1',NULL,NULL,NULL,NULL,NULL,'dateselect',NULL),('1103941831253463040','test_data','update_by','170','varchar(64)','更新者','更新者','updateBy','String',NULL,'0','1','1',NULL,NULL,NULL,NULL,'input',NULL),('1103941831303794688','test_data','update_date','180','datetime','更新时间','更新时间','updateDate','java.util.Date',NULL,'0','1','1','1',NULL,NULL,NULL,'dateselect',NULL),('1103941831345737728','test_data','remarks','190','varchar(500)','备注信息','备注信息','remarks','String',NULL,'1','1','1','1','1','LIKE','1','textarea','{\"isNewLine\":\"1\",\"gridRowCol\":\"12/2/10\"}'),('1103941831974883328','test_data_child','id','10','varchar(64)','编号','编号','id','String','1','0','1',NULL,NULL,NULL,NULL,'1','hidden','{\"fieldValid\":\"abc\"}'),('1103941832012632064','test_data_child','test_sort','20','int(11)','排序号','排序号','testSort','Long',NULL,'1','1','1','1','1',NULL,'1','input','{\"fieldValid\":\"digits\"}'),('1103941832075546624','test_data_child','test_data_id','30','varchar(64)','父表主键','父表主键','testData','String',NULL,'1','1','1','1','1',NULL,'1','input',NULL),('1103941832348176384','test_data_child','test_input','40','varchar(200)','单行文本','单行文本','testInput','String',NULL,'1','1','1','1','1','LIKE','1','input',NULL),('1103941832381730816','test_data_child','test_textarea','50','varchar(200)','多行文本','多行文本','testTextarea','String',NULL,'1','1','1','1','1','LIKE','1','textarea','{\"isNewLine\":\"1\",\"gridRowCol\":\"12/2/10\"}'),('1103941832645971968','test_data_child','test_select','60','varchar(10)','下拉框','下拉框','testSelect','String',NULL,'1','1','1','1','1',NULL,'1','select','{\"dictName\":\"sys_menu_type\",\"dictType\":\"sys_menu_type\"}'),('1103941832687915008','test_data_child','test_select_multiple','70','varchar(200)','下拉多选','下拉多选','testSelectMultiple','String',NULL,'1','1','1','1','1',NULL,'1','select_multiple','{\"dictName\":\"sys_menu_type\",\"dictType\":\"sys_menu_type\"}'),('1103941832721469440','test_data_child','test_radio','80','varchar(10)','单选框','单选框','testRadio','String',NULL,'1','1','1','1','1',NULL,'1','radio','{\"dictName\":\"sys_menu_type\",\"dictType\":\"sys_menu_type\"}'),('1103941832759218176','test_data_child','test_checkbox','90','varchar(200)','复选框','复选框','testCheckbox','String',NULL,'1','1','1','1','1',NULL,'1','checkbox','{\"dictName\":\"sys_menu_type\",\"dictType\":\"sys_menu_type\"}'),('1103941832792772608','test_data_child','test_date','100','datetime','日期选择','日期选择','testDate','java.util.Date',NULL,'1','1','1','1','1','BETWEEN','1','date',NULL),('1103941832960544768','test_data_child','test_datetime','110','datetime','日期时间','日期时间','testDatetime','java.util.Date',NULL,'1','1','1','1','1','BETWEEN','1','datetime',NULL),('1103941832989904896','test_data_child','test_user_code','120','varchar(64)','用户选择','用户选择','testUser','com.jeesite.modules.sys.entity.User',NULL,'1','1','1','1','1',NULL,'1','userselect',NULL),('1103941833027653632','test_data_child','test_office_code','130','varchar(64)','机构选择','机构选择','testOffice','com.jeesite.modules.sys.entity.Office',NULL,'1','1','1','1','1',NULL,'1','officeselect',NULL),('1103941833178648576','test_data_child','test_area_code','140','varchar(64)','区域选择','区域选择','testAreaCode|testAreaName','String',NULL,'1','1','1','1','1',NULL,'1','areaselect',NULL),('1103941833208008704','test_data_child','test_area_name','150','varchar(100)','区域名称','区域名称','testAreaName','String',NULL,'1','1','1','1','0','LIKE','0','input',NULL),('1103941833564524544','test_tree','tree_code','10','varchar(64)','节点编码','节点编码','treeCode','String','1','0','1',NULL,NULL,NULL,NULL,'1','input','{\"fieldValid\":\"abc\"}'),('1103941833707130880','test_tree','parent_code','20','varchar(64)','父级编号','父级编号','parentCode|parentName','This',NULL,'0','1','1',NULL,NULL,NULL,NULL,'treeselect',NULL),('1103941833744879616','test_tree','parent_codes','30','varchar(1000)','所有父级编号','所有父级编号','parentCodes','String',NULL,'0','1','1',NULL,NULL,'LIKE','0','input',NULL),('1103941833782628352','test_tree','tree_sort','40','decimal(10,0)','本级排序号','本级排序号（升序）','treeSort','Integer',NULL,'0','1','1','1',NULL,NULL,'1','input','{\"fieldValid\":\"digits\"}'),('1103941833904263168','test_tree','tree_sorts','50','varchar(1000)','所有级别排序号','所有级别排序号','treeSorts','String',NULL,'0','1','1','0',NULL,NULL,'0','input',NULL),('1103941833950400512','test_tree','tree_leaf','60','char(1)','是否最末级','是否最末级','treeLeaf','String',NULL,'0','1','1',NULL,NULL,NULL,NULL,'input',NULL),('1103941833983954944','test_tree','tree_level','70','decimal(4,0)','层次级别','层次级别','treeLevel','Integer',NULL,'0','1','1',NULL,NULL,NULL,NULL,'input','{\"fieldValid\":\"digits\"}'),('1103941834227224576','test_tree','tree_names','80','varchar(1000)','全节点名','全节点名','treeNames','String',NULL,'0','1','1',NULL,'1','LIKE',NULL,'input',NULL),('1103941834260779008','test_tree','tree_name','90','varchar(200)','节点名称','节点名称','treeName','String',NULL,'0','1','1','1','1','LIKE','1','input',NULL),('1103941834407579648','test_tree','status','100','char(1)','状态','状态（0正常 1删除 2停用）','status','String',NULL,'0','1','0','1','1',NULL,NULL,'select','{\"dictName\":\"sys_search_status\",\"dictType\":\"sys_search_status\"}'),('1103941834445328384','test_tree','create_by','110','varchar(64)','创建者','创建者','createBy','String',NULL,'0','1',NULL,NULL,NULL,NULL,NULL,'input',NULL),('1103941834483077120','test_tree','create_date','120','datetime','创建时间','创建时间','createDate','java.util.Date',NULL,'0','1',NULL,NULL,NULL,NULL,NULL,'dateselect',NULL),('1103941834734735360','test_tree','update_by','130','varchar(64)','更新者','更新者','updateBy','String',NULL,'0','1','1',NULL,NULL,NULL,NULL,'input',NULL),('1103941834789261312','test_tree','update_date','140','datetime','更新时间','更新时间','updateDate','java.util.Date',NULL,'0','1','1','1',NULL,NULL,NULL,'dateselect',NULL),('1103941834818621440','test_tree','remarks','150','varchar(500)','备注信息','备注信息','remarks','String',NULL,'1','1','1','1','1','LIKE','1','textarea','{\"isNewLine\":\"1\",\"gridRowCol\":\"12/2/10\"}');

/*Table structure for table `js_job_blob_triggers` */

DROP TABLE IF EXISTS `js_job_blob_triggers`;

CREATE TABLE `js_job_blob_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `js_job_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `js_job_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_blob_triggers` */

/*Table structure for table `js_job_calendars` */

DROP TABLE IF EXISTS `js_job_calendars`;

CREATE TABLE `js_job_calendars` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_calendars` */

/*Table structure for table `js_job_cron_triggers` */

DROP TABLE IF EXISTS `js_job_cron_triggers`;

CREATE TABLE `js_job_cron_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `js_job_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `js_job_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_cron_triggers` */

/*Table structure for table `js_job_fired_triggers` */

DROP TABLE IF EXISTS `js_job_fired_triggers`;

CREATE TABLE `js_job_fired_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ENTRY_ID` varchar(95) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_fired_triggers` */

/*Table structure for table `js_job_job_details` */

DROP TABLE IF EXISTS `js_job_job_details`;

CREATE TABLE `js_job_job_details` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_job_details` */

/*Table structure for table `js_job_locks` */

DROP TABLE IF EXISTS `js_job_locks`;

CREATE TABLE `js_job_locks` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `LOCK_NAME` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_locks` */

/*Table structure for table `js_job_paused_trigger_grps` */

DROP TABLE IF EXISTS `js_job_paused_trigger_grps`;

CREATE TABLE `js_job_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_paused_trigger_grps` */

/*Table structure for table `js_job_scheduler_state` */

DROP TABLE IF EXISTS `js_job_scheduler_state`;

CREATE TABLE `js_job_scheduler_state` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_scheduler_state` */

/*Table structure for table `js_job_simple_triggers` */

DROP TABLE IF EXISTS `js_job_simple_triggers`;

CREATE TABLE `js_job_simple_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `js_job_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `js_job_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_simple_triggers` */

/*Table structure for table `js_job_simprop_triggers` */

DROP TABLE IF EXISTS `js_job_simprop_triggers`;

CREATE TABLE `js_job_simprop_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `js_job_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `js_job_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_simprop_triggers` */

/*Table structure for table `js_job_triggers` */

DROP TABLE IF EXISTS `js_job_triggers`;

CREATE TABLE `js_job_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `js_job_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `js_job_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `js_job_triggers` */

/*Table structure for table `js_sys_area` */

DROP TABLE IF EXISTS `js_sys_area`;

CREATE TABLE `js_sys_area` (
  `area_code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区域编码',
  `parent_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '父级编号',
  `parent_codes` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有父级编号',
  `tree_sort` decimal(10,0) NOT NULL COMMENT '本级排序号（升序）',
  `tree_sorts` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有级别排序号',
  `tree_leaf` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否最末级',
  `tree_level` decimal(4,0) NOT NULL COMMENT '层次级别',
  `tree_names` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '全节点名',
  `area_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区域名称',
  `area_type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区域类型',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`area_code`),
  KEY `idx_sys_area_pc` (`parent_code`),
  KEY `idx_sys_area_ts` (`tree_sort`),
  KEY `idx_sys_area_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='行政区划';

/*Data for the table `js_sys_area` */

insert  into `js_sys_area`(`area_code`,`parent_code`,`parent_codes`,`tree_sort`,`tree_sorts`,`tree_leaf`,`tree_level`,`tree_names`,`area_name`,`area_type`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`) values ('370000','0','0,','370000','0000370000,','0','0','山东省','山东省','1','0','system','2019-03-08 16:51:26','system','2019-03-08 16:51:26',NULL),('370100','370000','0,370000,','370100','0000370000,0000370100,','0','1','山东省/济南市','济南市','2','0','system','2019-03-08 16:51:30','system','2019-03-08 16:51:30',NULL),('370102','370100','0,370000,370100,','370102','0000370000,0000370100,0000370102,','1','2','山东省/济南市/历下区','历下区','3','0','system','2019-03-08 16:51:31','system','2019-03-08 16:51:31',NULL),('370103','370100','0,370000,370100,','370103','0000370000,0000370100,0000370103,','1','2','山东省/济南市/市中区','市中区','3','0','system','2019-03-08 16:51:31','system','2019-03-08 16:51:32',NULL),('370104','370100','0,370000,370100,','370104','0000370000,0000370100,0000370104,','1','2','山东省/济南市/槐荫区','槐荫区','3','0','system','2019-03-08 16:51:32','system','2019-03-08 16:51:32',NULL),('370105','370100','0,370000,370100,','370105','0000370000,0000370100,0000370105,','1','2','山东省/济南市/天桥区','天桥区','3','0','system','2019-03-08 16:51:32','system','2019-03-08 16:51:32',NULL),('370112','370100','0,370000,370100,','370112','0000370000,0000370100,0000370112,','1','2','山东省/济南市/历城区','历城区','3','0','system','2019-03-08 16:51:32','system','2019-03-08 16:51:32',NULL),('370113','370100','0,370000,370100,','370113','0000370000,0000370100,0000370113,','1','2','山东省/济南市/长清区','长清区','3','0','system','2019-03-08 16:51:33','system','2019-03-08 16:51:33',NULL),('370114','370100','0,370000,370100,','370114','0000370000,0000370100,0000370114,','1','2','山东省/济南市/章丘区','章丘区','3','0','system','2019-03-08 16:51:33','system','2019-03-08 16:51:33',NULL),('370124','370100','0,370000,370100,','370124','0000370000,0000370100,0000370124,','1','2','山东省/济南市/平阴县','平阴县','3','0','system','2019-03-08 16:51:33','system','2019-03-08 16:51:33',NULL),('370125','370100','0,370000,370100,','370125','0000370000,0000370100,0000370125,','1','2','山东省/济南市/济阳县','济阳县','3','0','system','2019-03-08 16:51:33','system','2019-03-08 16:51:34',NULL),('370126','370100','0,370000,370100,','370126','0000370000,0000370100,0000370126,','1','2','山东省/济南市/商河县','商河县','3','0','system','2019-03-08 16:51:34','system','2019-03-08 16:51:34',NULL),('370200','370000','0,370000,','370200','0000370000,0000370200,','0','1','山东省/青岛市','青岛市','2','0','system','2019-03-08 16:51:30','system','2019-03-08 16:51:31',NULL),('370202','370200','0,370000,370200,','370202','0000370000,0000370200,0000370202,','1','2','山东省/青岛市/市南区','市南区','3','0','system','2019-03-08 16:51:34','system','2019-03-08 16:51:34',NULL),('370203','370200','0,370000,370200,','370203','0000370000,0000370200,0000370203,','1','2','山东省/青岛市/市北区','市北区','3','0','system','2019-03-08 16:51:34','system','2019-03-08 16:51:34',NULL),('370211','370200','0,370000,370200,','370211','0000370000,0000370200,0000370211,','1','2','山东省/青岛市/黄岛区','黄岛区','3','0','system','2019-03-08 16:51:34','system','2019-03-08 16:51:34',NULL),('370212','370200','0,370000,370200,','370212','0000370000,0000370200,0000370212,','1','2','山东省/青岛市/崂山区','崂山区','3','0','system','2019-03-08 16:51:34','system','2019-03-08 16:51:35',NULL),('370213','370200','0,370000,370200,','370213','0000370000,0000370200,0000370213,','1','2','山东省/青岛市/李沧区','李沧区','3','0','system','2019-03-08 16:51:35','system','2019-03-08 16:51:35',NULL),('370214','370200','0,370000,370200,','370214','0000370000,0000370200,0000370214,','1','2','山东省/青岛市/城阳区','城阳区','3','0','system','2019-03-08 16:51:35','system','2019-03-08 16:51:35',NULL),('370281','370200','0,370000,370200,','370281','0000370000,0000370200,0000370281,','1','2','山东省/青岛市/胶州市','胶州市','3','0','system','2019-03-08 16:51:35','system','2019-03-08 16:51:35',NULL),('370282','370200','0,370000,370200,','370282','0000370000,0000370200,0000370282,','1','2','山东省/青岛市/即墨区','即墨区','3','0','system','2019-03-08 16:51:35','system','2019-03-08 16:51:35',NULL),('370283','370200','0,370000,370200,','370283','0000370000,0000370200,0000370283,','1','2','山东省/青岛市/平度市','平度市','3','0','system','2019-03-08 16:51:35','system','2019-03-08 16:51:36',NULL),('370285','370200','0,370000,370200,','370285','0000370000,0000370200,0000370285,','1','2','山东省/青岛市/莱西市','莱西市','3','0','system','2019-03-08 16:51:36','system','2019-03-08 16:51:36',NULL);

/*Table structure for table `js_sys_company` */

DROP TABLE IF EXISTS `js_sys_company`;

CREATE TABLE `js_sys_company` (
  `company_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司编码',
  `parent_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '父级编号',
  `parent_codes` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有父级编号',
  `tree_sort` decimal(10,0) NOT NULL COMMENT '本级排序号（升序）',
  `tree_sorts` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有级别排序号',
  `tree_leaf` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否最末级',
  `tree_level` decimal(4,0) NOT NULL COMMENT '层次级别',
  `tree_names` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '全节点名',
  `view_code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司代码',
  `company_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司名称',
  `full_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司全称',
  `area_code` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区域编码',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'JeeSite' COMMENT '租户名称',
  `extend_s1` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 1',
  `extend_s2` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 2',
  `extend_s3` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 3',
  `extend_s4` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 4',
  `extend_s5` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 5',
  `extend_s6` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 6',
  `extend_s7` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 7',
  `extend_s8` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 8',
  `extend_i1` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 1',
  `extend_i2` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 2',
  `extend_i3` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 3',
  `extend_i4` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 4',
  `extend_f1` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 1',
  `extend_f2` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 2',
  `extend_f3` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 3',
  `extend_f4` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 4',
  `extend_d1` datetime DEFAULT NULL COMMENT '扩展 Date 1',
  `extend_d2` datetime DEFAULT NULL COMMENT '扩展 Date 2',
  `extend_d3` datetime DEFAULT NULL COMMENT '扩展 Date 3',
  `extend_d4` datetime DEFAULT NULL COMMENT '扩展 Date 4',
  PRIMARY KEY (`company_code`),
  KEY `idx_sys_company_cc` (`corp_code`),
  KEY `idx_sys_company_pc` (`parent_code`),
  KEY `idx_sys_company_ts` (`tree_sort`),
  KEY `idx_sys_company_status` (`status`),
  KEY `idx_sys_company_vc` (`view_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公司表';

/*Data for the table `js_sys_company` */

insert  into `js_sys_company`(`company_code`,`parent_code`,`parent_codes`,`tree_sort`,`tree_sorts`,`tree_leaf`,`tree_level`,`tree_names`,`view_code`,`company_name`,`full_name`,`area_code`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`corp_code`,`corp_name`,`extend_s1`,`extend_s2`,`extend_s3`,`extend_s4`,`extend_s5`,`extend_s6`,`extend_s7`,`extend_s8`,`extend_i1`,`extend_i2`,`extend_i3`,`extend_i4`,`extend_f1`,`extend_f2`,`extend_f3`,`extend_f4`,`extend_d1`,`extend_d2`,`extend_d3`,`extend_d4`) values ('SD','0','0,','40','0000000040,','0','0','山东公司','SD','山东公司','山东公司',NULL,'0','system','2019-03-08 16:53:29','system','2019-03-08 16:53:29',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDJN','SD','0,SD,','30','0000000040,0000000030,','1','1','山东公司/济南公司','SDJN','济南公司','山东济南公司',NULL,'0','system','2019-03-08 16:53:29','system','2019-03-08 16:53:29',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDQD','SD','0,SD,','40','0000000040,0000000040,','1','1','山东公司/青岛公司','SDQD','青岛公司','山东青岛公司',NULL,'0','system','2019-03-08 16:53:29','system','2019-03-08 16:53:29',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `js_sys_company_office` */

DROP TABLE IF EXISTS `js_sys_company_office`;

CREATE TABLE `js_sys_company_office` (
  `company_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司编码',
  `office_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构编码',
  PRIMARY KEY (`company_code`,`office_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公司部门关联表';

/*Data for the table `js_sys_company_office` */

/*Table structure for table `js_sys_config` */

DROP TABLE IF EXISTS `js_sys_config`;

CREATE TABLE `js_sys_config` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `config_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `config_key` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参数键',
  `config_value` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '参数值',
  `is_sys` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '系统内置（1是 0否）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_config_key` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='参数配置表';

/*Data for the table `js_sys_config` */

insert  into `js_sys_config`(`id`,`config_name`,`config_key`,`config_value`,`is_sys`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`) values ('1103941372799258624','研发工具-代码生成默认包名','gen.defaultPackageName','com.jeesite.modules','0','system','2019-03-08 16:51:48','system','2019-03-08 16:51:48','新建项目后，修改该键值，在生成代码的时候就不要再修改了'),('1103941376687378432','主框架页-桌面仪表盘首页地址','sys.index.desktopUrl','/desktop','0','system','2019-03-08 16:51:49','system','2019-03-08 16:51:49','主页面的第一个页签首页桌面地址'),('1103941378809696256','主框架页-主导航菜单显示风格','sys.index.menuStyle','1','0','system','2019-03-08 16:51:50','system','2019-03-08 16:51:50','1：菜单全部在左侧；2：根菜单显示在顶部'),('1103941381393387520','主框架页-侧边栏的默认显示样式','sys.index.sidebarStyle','1','0','system','2019-03-08 16:51:50','system','2019-03-08 16:51:50','1：默认显示侧边栏；2：默认折叠侧边栏'),('1103941382836228096','主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue-light','0','system','2019-03-08 16:51:51','system','2019-03-08 16:51:51','skin-black-light、skin-black、skin-blue-light、skin-blue、skin-green-light、skin-green、skin-purple-light、skin-purple、skin-red-light、skin-red、skin-yellow-light、skin-yellow'),('1103941386162311168','用户登录-登录失败多少次数后显示验证码','sys.login.failedNumAfterValidCode','100','0','system','2019-03-08 16:51:51','system','2019-03-08 16:51:51','设置为0强制使用验证码登录'),('1103941386858565632','用户登录-登录失败多少次数后锁定账号','sys.login.failedNumAfterLockAccount','200','0','system','2019-03-08 16:51:52','system','2019-03-08 16:51:52','登录失败多少次数后锁定账号'),('1103941389651972096','用户登录-登录失败多少次数后锁定账号的时间','sys.login.failedNumAfterLockMinute','20','0','system','2019-03-08 16:51:52','system','2019-03-08 16:51:52','锁定账号的时间（单位：分钟）'),('1103941392852226048','账号自助-是否开启用户注册功能','sys.account.registerUser','true','0','system','2019-03-08 16:51:53','system','2019-03-08 16:51:53','是否开启注册用户功能'),('1103941395511414784','账号自助-允许自助注册的用户类型','sys.account.registerUser.userTypes','-1','0','system','2019-03-08 16:51:54','system','2019-03-08 16:51:54','允许注册的用户类型（多个用逗号隔开，如果注册时不选择用户类型，则第一个为默认类型）'),('1103941396782288896','账号自助-验证码有效时间（分钟）','sys.account.validCodeTimeout','10','0','system','2019-03-08 16:51:54','system','2019-03-08 16:51:54','找回密码时，短信/邮件验证码有效时间（单位：分钟，0表示不限制）'),('1103941398875246592','用户管理-账号默认角色-员工类型','sys.user.defaultRoleCodes.employee','default','0','system','2019-03-08 16:51:54','system','2019-03-08 16:51:54','所有员工用户都拥有的角色权限（适用于菜单授权查询）'),('1103941399848325120','用户管理-账号初始密码','sys.user.initPassword','123456','0','system','2019-03-08 16:51:55','system','2019-03-08 16:51:55','创建用户和重置密码的时候用户的密码'),('1103941402432016384','用户管理-初始密码修改策略','sys.user.initPasswordModify','1','0','system','2019-03-08 16:51:55','system','2019-03-08 16:51:56','0：初始密码修改策略关闭，没有任何提示；1：提醒用户，如果未修改初始密码，则在登录时和点击菜单就会提醒修改密码对话框；2：强制实行初始密码修改，登录后若不修改密码则不能进行系统操作'),('1103941405699379200','用户管理-账号密码修改策略','sys.user.passwordModify','1','0','system','2019-03-08 16:51:56','system','2019-03-08 16:51:56','0：密码修改策略关闭，没有任何提示；1：提醒用户，如果未修改初始密码，则在登录时和点击菜单就会提醒修改密码对话框；2：强制实行初始密码修改，登录后若不修改密码则不能进行系统操作。'),('1103941407729422336','用户管理-账号密码修改策略验证周期','sys.user.passwordModifyCycle','30','0','system','2019-03-08 16:51:57','system','2019-03-08 16:51:57','密码安全修改周期是指定时间内提醒或必须修改密码（例如设置30天）的验证时间（天），超过这个时间登录系统时需，提醒用户修改密码或强制修改密码才能继续使用系统。单位：天，如果设置30天，则第31天开始强制修改密码'),('1103941409021267968','用户管理-密码修改多少次内不允许重复','sys.user.passwordModifyNotRepeatNum','1','0','system','2019-03-08 16:51:57','system','2019-03-08 16:51:57','默认1次，表示不能与上次密码重复；若设置为3，表示并与前3次密码重复'),('1103941409763659776','用户管理-账号密码修改最低安全等级','sys.user.passwordModifySecurityLevel','0','0','system','2019-03-08 16:51:57','system','2019-03-08 16:51:57','0：不限制等级（用户在修改密码的时候不进行等级验证）\r；1：限制最低等级为很弱\r；2：限制最低等级为弱\r；3：限制最低等级为安全\r；4：限制最低等级为很安全');

/*Table structure for table `js_sys_dict_data` */

DROP TABLE IF EXISTS `js_sys_dict_data`;

CREATE TABLE `js_sys_dict_data` (
  `dict_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典编码',
  `parent_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '父级编号',
  `parent_codes` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有父级编号',
  `tree_sort` decimal(10,0) NOT NULL COMMENT '本级排序号（升序）',
  `tree_sorts` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有级别排序号',
  `tree_leaf` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否最末级',
  `tree_level` decimal(4,0) NOT NULL COMMENT '层次级别',
  `tree_names` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '全节点名',
  `dict_label` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典标签',
  `dict_value` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典键值',
  `dict_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典类型',
  `is_sys` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '系统内置（1是 0否）',
  `description` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '字典描述',
  `css_style` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'css样式（如：color:red)',
  `css_class` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'css类名（如：red）',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'JeeSite' COMMENT '租户名称',
  `extend_s1` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 1',
  `extend_s2` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 2',
  `extend_s3` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 3',
  `extend_s4` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 4',
  `extend_s5` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 5',
  `extend_s6` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 6',
  `extend_s7` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 7',
  `extend_s8` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 8',
  `extend_i1` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 1',
  `extend_i2` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 2',
  `extend_i3` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 3',
  `extend_i4` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 4',
  `extend_f1` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 1',
  `extend_f2` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 2',
  `extend_f3` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 3',
  `extend_f4` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 4',
  `extend_d1` datetime DEFAULT NULL COMMENT '扩展 Date 1',
  `extend_d2` datetime DEFAULT NULL COMMENT '扩展 Date 2',
  `extend_d3` datetime DEFAULT NULL COMMENT '扩展 Date 3',
  `extend_d4` datetime DEFAULT NULL COMMENT '扩展 Date 4',
  PRIMARY KEY (`dict_code`),
  KEY `idx_sys_dict_data_cc` (`corp_code`),
  KEY `idx_sys_dict_data_dt` (`dict_type`),
  KEY `idx_sys_dict_data_pc` (`parent_code`),
  KEY `idx_sys_dict_data_status` (`status`),
  KEY `idx_sys_dict_data_ts` (`tree_sort`),
  KEY `idx_sys_dict_data_dv` (`dict_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典数据表';

/*Data for the table `js_sys_dict_data` */

insert  into `js_sys_dict_data`(`dict_code`,`parent_code`,`parent_codes`,`tree_sort`,`tree_sorts`,`tree_leaf`,`tree_level`,`tree_names`,`dict_label`,`dict_value`,`dict_type`,`is_sys`,`description`,`css_style`,`css_class`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`corp_code`,`corp_name`,`extend_s1`,`extend_s2`,`extend_s3`,`extend_s4`,`extend_s5`,`extend_s6`,`extend_s7`,`extend_s8`,`extend_i1`,`extend_i2`,`extend_i3`,`extend_i4`,`extend_f1`,`extend_f2`,`extend_f3`,`extend_f4`,`extend_d1`,`extend_d2`,`extend_d3`,`extend_d4`) values ('1103941454776930304','0','0,','30','0000000030,','1','0','是','是','1','sys_yes_no','1','','','','0','system','2019-03-08 16:52:08','system','2019-03-08 16:52:08',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941456907636736','0','0,','40','0000000040,','1','0','否','否','0','sys_yes_no','1','','color:#aaa;','','0','system','2019-03-08 16:52:08','system','2019-03-08 16:52:09',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941461542342656','0','0,','20','0000000020,','1','0','正常','正常','0','sys_status','1','','','','0','system','2019-03-08 16:52:09','system','2019-03-08 16:52:10',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941466671976448','0','0,','30','0000000030,','1','0','删除','删除','1','sys_status','1','','color:#f00;','','0','system','2019-03-08 16:52:11','system','2019-03-08 16:52:11',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941469398274048','0','0,','40','0000000040,','1','0','停用','停用','2','sys_status','1','','color:#f00;','','0','system','2019-03-08 16:52:11','system','2019-03-08 16:52:11',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941470518153216','0','0,','50','0000000050,','1','0','冻结','冻结','3','sys_status','1','','color:#fa0;','','0','system','2019-03-08 16:52:11','system','2019-03-08 16:52:12',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941471705141248','0','0,','60','0000000060,','1','0','待审','待审','4','sys_status','1','','','','0','system','2019-03-08 16:52:12','system','2019-03-08 16:52:12',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941473005375488','0','0,','70','0000000070,','1','0','驳回','驳回','5','sys_status','1','','','','0','system','2019-03-08 16:52:12','system','2019-03-08 16:52:12',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941474532102144','0','0,','80','0000000080,','1','0','草稿','草稿','9','sys_status','1','','color:#aaa;','','0','system','2019-03-08 16:52:12','system','2019-03-08 16:52:13',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941475861696512','0','0,','30','0000000030,','1','0','显示','显示','1','sys_show_hide','1','','','','0','system','2019-03-08 16:52:13','system','2019-03-08 16:52:13',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941477958848512','0','0,','40','0000000040,','1','0','隐藏','隐藏','0','sys_show_hide','1','','color:#aaa;','','0','system','2019-03-08 16:52:13','system','2019-03-08 16:52:14',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941479758204928','0','0,','30','0000000030,','1','0','简体中文','简体中文','zh_CN','sys_lang_type','1','','','','0','system','2019-03-08 16:52:14','system','2019-03-08 16:52:15',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941486766886912','0','0,','40','0000000040,','1','0','英语','英语','en','sys_lang_type','1','','','','0','system','2019-03-08 16:52:15','system','2019-03-08 16:52:16',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941487647690752','0','0,','30','0000000030,','1','0','PC电脑','PC电脑','pc','sys_device_type','1','','','','0','system','2019-03-08 16:52:16','system','2019-03-08 16:52:16',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941489103114240','0','0,','40','0000000040,','1','0','手机APP','手机APP','mobileApp','sys_device_type','1','','','','0','system','2019-03-08 16:52:16','system','2019-03-08 16:52:16',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941490202021888','0','0,','50','0000000050,','1','0','手机Web','手机Web','mobileWeb','sys_device_type','1','','','','0','system','2019-03-08 16:52:16','system','2019-03-08 16:52:16',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941491422564352','0','0,','60','0000000060,','1','0','微信设备','微信设备','weixin','sys_device_type','1','','','','0','system','2019-03-08 16:52:16','system','2019-03-08 16:52:17',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941492307562496','0','0,','30','0000000030,','1','0','主导航菜单','主导航菜单','default','sys_menu_sys_code','1','','','','0','system','2019-03-08 16:52:17','system','2019-03-08 16:52:17',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941494102724608','0','0,','30','0000000030,','1','0','菜单','菜单','1','sys_menu_type','1','','','','0','system','2019-03-08 16:52:17','system','2019-03-08 16:52:17',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941495545565184','0','0,','40','0000000040,','1','0','权限','权限','2','sys_menu_type','1','','color:#c243d6;','','0','system','2019-03-08 16:52:17','system','2019-03-08 16:52:18',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941498741624832','0','0,','30','0000000030,','1','0','默认权重','默认权重','20','sys_menu_weight','1','','','','0','system','2019-03-08 16:52:18','system','2019-03-08 16:52:19',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941501086240768','0','0,','40','0000000040,','1','0','二级管理员','二级管理员','40','sys_menu_weight','1','','','','0','system','2019-03-08 16:52:19','system','2019-03-08 16:52:19',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941502327754752','0','0,','50','0000000050,','1','0','系统管理员','系统管理员','60','sys_menu_weight','1','','','','0','system','2019-03-08 16:52:19','system','2019-03-08 16:52:19',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941503208558592','0','0,','60','0000000060,','1','0','超级管理员','超级管理员','80','sys_menu_weight','1','','color:#c243d6;','','0','system','2019-03-08 16:52:19','system','2019-03-08 16:52:20',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941505557368832','0','0,','30','0000000030,','1','0','国家','国家','0','sys_area_type','1','','','','0','system','2019-03-08 16:52:20','system','2019-03-08 16:52:21',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941509428711424','0','0,','40','0000000040,','1','0','省份直辖市','省份直辖市','1','sys_area_type','1','','','','0','system','2019-03-08 16:52:21','system','2019-03-08 16:52:21',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941511546834944','0','0,','50','0000000050,','1','0','地市','地市','2','sys_area_type','1','','','','0','system','2019-03-08 16:52:21','system','2019-03-08 16:52:22',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941513459437568','0','0,','60','0000000060,','1','0','区县','区县','3','sys_area_type','1','','','','0','system','2019-03-08 16:52:22','system','2019-03-08 16:52:22',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941514348630016','0','0,','30','0000000030,','1','0','省级公司','省级公司','1','sys_office_type','1','','','','0','system','2019-03-08 16:52:22','system','2019-03-08 16:52:22',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941515481092096','0','0,','40','0000000040,','1','0','市级公司','市级公司','2','sys_office_type','1','','','','0','system','2019-03-08 16:52:22','system','2019-03-08 16:52:22',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941516441587712','0','0,','50','0000000050,','1','0','部门','部门','3','sys_office_type','1','','','','0','system','2019-03-08 16:52:22','system','2019-03-08 16:52:23',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941517343363072','0','0,','30','0000000030,','1','0','正常','正常','0','sys_search_status','1','','','','0','system','2019-03-08 16:52:23','system','2019-03-08 16:52:23',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941518131892224','0','0,','40','0000000040,','1','0','停用','停用','2','sys_search_status','1','','color:#f00;','','0','system','2019-03-08 16:52:23','system','2019-03-08 16:52:23',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941518895255552','0','0,','30','0000000030,','1','0','男','男','1','sys_user_sex','1','','','','0','system','2019-03-08 16:52:23','system','2019-03-08 16:52:23',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941520157741056','0','0,','40','0000000040,','1','0','女','女','2','sys_user_sex','1','','','','0','system','2019-03-08 16:52:23','system','2019-03-08 16:52:24',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941521617358848','0','0,','30','0000000030,','1','0','正常','正常','0','sys_user_status','1','','','','0','system','2019-03-08 16:52:24','system','2019-03-08 16:52:24',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941522670129152','0','0,','40','0000000040,','1','0','停用','停用','2','sys_user_status','1','','color:#f00;','','0','system','2019-03-08 16:52:24','system','2019-03-08 16:52:24',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941523995529216','0','0,','50','0000000050,','1','0','冻结','冻结','3','sys_user_status','1','','color:#fa0;','','0','system','2019-03-08 16:52:24','system','2019-03-08 16:52:25',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941525727776768','0','0,','30','0000000030,','1','0','员工','员工','employee','sys_user_type','1','','','','0','system','2019-03-08 16:52:25','system','2019-03-08 16:52:25',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941526935736320','0','0,','40','0000000040,','1','0','会员','会员','member','sys_user_type','1','','','','0','system','2019-03-08 16:52:25','system','2019-03-08 16:52:25',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941528051421184','0','0,','50','0000000050,','1','0','单位','单位','btype','sys_user_type','1','','','','0','system','2019-03-08 16:52:25','system','2019-03-08 16:52:25',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941529246797824','0','0,','60','0000000060,','1','0','个人','个人','persion','sys_user_type','1','','','','0','system','2019-03-08 16:52:25','system','2019-03-08 16:52:26',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941530341511168','0','0,','70','0000000070,','1','0','专家','专家','expert','sys_user_type','1','','','','0','system','2019-03-08 16:52:26','system','2019-03-08 16:52:26',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941531327172608','0','0,','30','0000000030,','1','0','高管','高管','1','sys_role_type','1','','','','0','system','2019-03-08 16:52:26','system','2019-03-08 16:52:26',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941532124090368','0','0,','40','0000000040,','1','0','中层','中层','2','sys_role_type','1','','','','0','system','2019-03-08 16:52:26','system','2019-03-08 16:52:26',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941533076197376','0','0,','50','0000000050,','1','0','基层','基层','3','sys_role_type','1','','','','0','system','2019-03-08 16:52:26','system','2019-03-08 16:52:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941534196076544','0','0,','60','0000000060,','1','0','其它','其它','4','sys_role_type','1','','','','0','system','2019-03-08 16:52:27','system','2019-03-08 16:52:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941535475339264','0','0,','30','0000000030,','1','0','未设置','未设置','0','sys_role_data_scope','1','','','','0','system','2019-03-08 16:52:27','system','2019-03-08 16:52:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941536091901952','0','0,','40','0000000040,','1','0','全部数据','全部数据','1','sys_role_data_scope','1','','','','0','system','2019-03-08 16:52:27','system','2019-03-08 16:52:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941536955928576','0','0,','50','0000000050,','1','0','自定义数据','自定义数据','2','sys_role_data_scope','1','','','','0','system','2019-03-08 16:52:27','system','2019-03-08 16:52:28',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941538054836224','0','0,','60','0000000060,','1','0','本部门数据','本部门数据','3','sys_role_data_scope','1','','','','0','system','2019-03-08 16:52:28','system','2019-03-08 16:52:28',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941539120189440','0','0,','70','0000000070,','1','0','本公司数据','本公司数据','4','sys_role_data_scope','1','','','','0','system','2019-03-08 16:52:28','system','2019-03-08 16:52:28',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941540172959744','0','0,','80','0000000080,','1','0','本部门和本公司数据','本部门和本公司数据','5','sys_role_data_scope','1','','','','0','system','2019-03-08 16:52:28','system','2019-03-08 16:52:28',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941541062152192','0','0,','30','0000000030,','1','0','高管','高管','1','sys_post_type','1','','','','0','system','2019-03-08 16:52:28','system','2019-03-08 16:52:29',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941542060396544','0','0,','40','0000000040,','1','0','中层','中层','2','sys_post_type','1','','','','0','system','2019-03-08 16:52:29','system','2019-03-08 16:52:29',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941543008309248','0','0,','50','0000000050,','1','0','基层','基层','3','sys_post_type','1','','','','0','system','2019-03-08 16:52:29','system','2019-03-08 16:52:29',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941543704563712','0','0,','60','0000000060,','1','0','其它','其它','4','sys_post_type','1','','','','0','system','2019-03-08 16:52:29','system','2019-03-08 16:52:29',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941544920911872','0','0,','30','0000000030,','1','0','接入日志','接入日志','access','sys_log_type','1','','','','0','system','2019-03-08 16:52:29','system','2019-03-08 16:52:29',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941545948516352','0','0,','40','0000000040,','1','0','修改日志','修改日志','update','sys_log_type','1','','','','0','system','2019-03-08 16:52:30','system','2019-03-08 16:52:30',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941547269722112','0','0,','50','0000000050,','1','0','查询日志','查询日志','select','sys_log_type','1','','','','0','system','2019-03-08 16:52:30','system','2019-03-08 16:52:30',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941548762894336','0','0,','60','0000000060,','1','0','登录登出','登录登出','loginLogout','sys_log_type','1','','','','0','system','2019-03-08 16:52:30','system','2019-03-08 16:52:30',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941549593366528','0','0,','30','0000000030,','1','0','默认','默认','DEFAULT','sys_job_group','1','','','','0','system','2019-03-08 16:52:30','system','2019-03-08 16:52:31',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941550570639360','0','0,','40','0000000040,','1','0','系统','系统','SYSTEM','sys_job_group','1','','','','0','system','2019-03-08 16:52:31','system','2019-03-08 16:52:31',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941551346585600','0','0,','30','0000000030,','1','0','错过计划等待本次计划完成后立即执行一次','错过计划等待本次计划完成后立即执行一次','1','sys_job_misfire_instruction','1','','','','0','system','2019-03-08 16:52:31','system','2019-03-08 16:52:31',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941552378384384','0','0,','40','0000000040,','1','0','本次执行时间根据上次结束时间重新计算（时间间隔方式）','本次执行时间根据上次结束时间重新计算（时间间隔方式）','2','sys_job_misfire_instruction','1','','','','0','system','2019-03-08 16:52:31','system','2019-03-08 16:52:31',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941553296936960','0','0,','30','0000000030,','1','0','正常','正常','0','sys_job_status','1','','','','0','system','2019-03-08 16:52:31','system','2019-03-08 16:52:31',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941554232266752','0','0,','40','0000000040,','1','0','删除','删除','1','sys_job_status','1','','','','0','system','2019-03-08 16:52:31','system','2019-03-08 16:52:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941555469586432','0','0,','50','0000000050,','1','0','暂停','暂停','2','sys_job_status','1','','color:#f00;','','0','system','2019-03-08 16:52:32','system','2019-03-08 16:52:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941556509773824','0','0,','30','0000000030,','1','0','完成','完成','3','sys_job_status','1','','','','0','system','2019-03-08 16:52:32','system','2019-03-08 16:52:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941557495435264','0','0,','40','0000000040,','1','0','错误','错误','4','sys_job_status','1','','color:#f00;','','0','system','2019-03-08 16:52:32','system','2019-03-08 16:52:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941558405599232','0','0,','50','0000000050,','1','0','运行','运行','5','sys_job_status','1','','','','0','system','2019-03-08 16:52:32','system','2019-03-08 16:52:33',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941559571615744','0','0,','30','0000000030,','1','0','计划日志','计划日志','scheduler','sys_job_type','1','','','','0','system','2019-03-08 16:52:33','system','2019-03-08 16:52:33',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941560548888576','0','0,','40','0000000040,','1','0','任务日志','任务日志','job','sys_job_type','1','','','','0','system','2019-03-08 16:52:33','system','2019-03-08 16:52:33',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941561496801280','0','0,','50','0000000050,','1','0','触发日志','触发日志','trigger','sys_job_type','1','','','','0','system','2019-03-08 16:52:33','system','2019-03-08 16:52:33',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941562591514624','0','0,','30','0000000030,','1','0','计划创建','计划创建','jobScheduled','sys_job_event','1','','','','0','system','2019-03-08 16:52:33','system','2019-03-08 16:52:34',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941563661062144','0','0,','40','0000000040,','1','0','计划移除','计划移除','jobUnscheduled','sys_job_event','1','','','','0','system','2019-03-08 16:52:34','system','2019-03-08 16:52:34',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941564952907776','0','0,','50','0000000050,','1','0','计划暂停','计划暂停','triggerPaused','sys_job_event','1','','','','0','system','2019-03-08 16:52:34','system','2019-03-08 16:52:34',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941565871460352','0','0,','60','0000000060,','1','0','计划恢复','计划恢复','triggerResumed','sys_job_event','1','','','','0','system','2019-03-08 16:52:34','system','2019-03-08 16:52:34',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941566701932544','0','0,','70','0000000070,','1','0','调度错误','调度错误','schedulerError','sys_job_event','1','','','','0','system','2019-03-08 16:52:34','system','2019-03-08 16:52:35',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941567851171840','0','0,','80','0000000080,','1','0','任务执行','任务执行','jobToBeExecuted','sys_job_event','1','','','','0','system','2019-03-08 16:52:35','system','2019-03-08 16:52:35',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941569348538368','0','0,','90','0000000090,','1','0','任务结束','任务结束','jobWasExecuted','sys_job_event','1','','','','0','system','2019-03-08 16:52:35','system','2019-03-08 16:52:35',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941570535526400','0','0,','100','0000000100,','1','0','任务停止','任务停止','jobExecutionVetoed','sys_job_event','1','','','','0','system','2019-03-08 16:52:35','system','2019-03-08 16:52:36',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941571600879616','0','0,','110','0000000110,','1','0','触发计划','触发计划','triggerFired','sys_job_event','1','','','','0','system','2019-03-08 16:52:36','system','2019-03-08 16:52:36',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941572938862592','0','0,','120','0000000120,','1','0','触发验证','触发验证','vetoJobExecution','sys_job_event','1','','','','0','system','2019-03-08 16:52:36','system','2019-03-08 16:52:36',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941574062936064','0','0,','130','0000000130,','1','0','触发完成','触发完成','triggerComplete','sys_job_event','1','','','','0','system','2019-03-08 16:52:36','system','2019-03-08 16:52:36',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941575228952576','0','0,','140','0000000140,','1','0','触发错过','触发错过','triggerMisfired','sys_job_event','1','','','','0','system','2019-03-08 16:52:36','system','2019-03-08 16:52:37',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941576072007680','0','0,','30','0000000030,','1','0','PC','PC','pc','sys_msg_type','1','消息类型','','','0','system','2019-03-08 16:52:37','system','2019-03-08 16:52:37',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941577112195072','0','0,','40','0000000040,','1','0','APP','APP','app','sys_msg_type','1','','','','0','system','2019-03-08 16:52:37','system','2019-03-08 16:52:37',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941578072690688','0','0,','50','0000000050,','1','0','短信','短信','sms','sys_msg_type','1','','','','0','system','2019-03-08 16:52:37','system','2019-03-08 16:52:37',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941579058352128','0','0,','60','0000000060,','1','0','邮件','邮件','email','sys_msg_type','1','','','','0','system','2019-03-08 16:52:37','system','2019-03-08 16:52:38',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941579997876224','0','0,','70','0000000070,','1','0','微信','微信','weixin','sys_msg_type','1','','','','0','system','2019-03-08 16:52:38','system','2019-03-08 16:52:38',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941581075812352','0','0,','30','0000000030,','1','0','待推送','待推送','0','sys_msg_push_status','1','推送状态','','','0','system','2019-03-08 16:52:38','system','2019-03-08 16:52:38',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941582183108608','0','0,','30','0000000030,','1','0','成功','成功','1','sys_msg_push_status','1','','','','0','system','2019-03-08 16:52:38','system','2019-03-08 16:52:38',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941583013580800','0','0,','40','0000000040,','1','0','失败','失败','2','sys_msg_push_status','1','','color:#f00;','','0','system','2019-03-08 16:52:38','system','2019-03-08 16:52:39',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941584003436544','0','0,','30','0000000030,','1','0','未送达','未送达','0','sys_msg_read_status','1','读取状态','','','0','system','2019-03-08 16:52:39','system','2019-03-08 16:52:39',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941585517580288','0','0,','40','0000000040,','1','0','已读','已读','1','sys_msg_read_status','1','','','','0','system','2019-03-08 16:52:39','system','2019-03-08 16:52:39',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941586553573376','0','0,','50','0000000050,','1','0','未读','未读','2','sys_msg_read_status','1','','','','0','system','2019-03-08 16:52:39','system','2019-03-08 16:52:39',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941587711201280','0','0,','30','0000000030,','1','0','普通','普通','1','msg_inner_content_level','1','内容级别','','','0','system','2019-03-08 16:52:39','system','2019-03-08 16:52:40',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941588944326656','0','0,','40','0000000040,','1','0','一般','一般','2','msg_inner_content_level','1','','','','0','system','2019-03-08 16:52:40','system','2019-03-08 16:52:40',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941590072594432','0','0,','50','0000000050,','1','0','紧急','紧急','3','msg_inner_content_level','1','','color:#f00;','','0','system','2019-03-08 16:52:40','system','2019-03-08 16:52:40',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941591083421696','0','0,','30','0000000030,','1','0','公告','公告','1','msg_inner_content_type','1','内容类型','','','0','system','2019-03-08 16:52:40','system','2019-03-08 16:52:40',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941591960031232','0','0,','40','0000000040,','1','0','新闻','新闻','2','msg_inner_content_type','1','','','','0','system','2019-03-08 16:52:40','system','2019-03-08 16:52:41',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941593113464832','0','0,','50','0000000050,','1','0','会议','会议','3','msg_inner_content_type','1','','','','0','system','2019-03-08 16:52:41','system','2019-03-08 16:52:41',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941594606637056','0','0,','60','0000000060,','1','0','其它','其它','4','msg_inner_content_type','1','','','','0','system','2019-03-08 16:52:41','system','2019-03-08 16:52:41',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941595571326976','0','0,','30','0000000030,','1','0','用户','用户','1','msg_inner_receiver_type','1','接受类型','','','0','system','2019-03-08 16:52:41','system','2019-03-08 16:52:41',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941596544405504','0','0,','40','0000000040,','1','0','部门','部门','2','msg_inner_receiver_type','1','','','','0','system','2019-03-08 16:52:42','system','2019-03-08 16:52:42',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941597890777088','0','0,','50','0000000050,','1','0','角色','角色','3','msg_inner_receiver_type','1','','','','0','system','2019-03-08 16:52:42','system','2019-03-08 16:52:42',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941598943547392','0','0,','60','0000000060,','1','0','岗位','岗位','4','msg_inner_receiver_type','1','','','','0','system','2019-03-08 16:52:42','system','2019-03-08 16:52:42',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941599774019584','0','0,','30','0000000030,','1','0','正常','正常','0','msg_inner_msg_status','1','消息状态','','','0','system','2019-03-08 16:52:42','system','2019-03-08 16:52:43',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941600889704448','0','0,','40','0000000040,','1','0','删除','删除','1','msg_inner_msg_status','1','','','','0','system','2019-03-08 16:52:43','system','2019-03-08 16:52:43',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941602009583616','0','0,','50','0000000050,','1','0','审核','审核','4','msg_inner_msg_status','1','','','','0','system','2019-03-08 16:52:43','system','2019-03-08 16:52:43',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941604610052096','0','0,','60','0000000060,','1','0','驳回','驳回','5','msg_inner_msg_status','1','','color:#f00;','','0','system','2019-03-08 16:52:43','system','2019-03-08 16:52:44',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941605574742016','0','0,','70','0000000070,','1','0','草稿','草稿','9','msg_inner_msg_status','1','','','','0','system','2019-03-08 16:52:44','system','2019-03-08 16:52:44',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `js_sys_dict_type` */

DROP TABLE IF EXISTS `js_sys_dict_type`;

CREATE TABLE `js_sys_dict_type` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `dict_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典名称',
  `dict_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典类型',
  `is_sys` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否系统字典',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `idx_sys_dict_type_is` (`is_sys`),
  KEY `idx_sys_dict_type_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典类型表';

/*Data for the table `js_sys_dict_type` */

insert  into `js_sys_dict_type`(`id`,`dict_name`,`dict_type`,`is_sys`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`) values ('1103941432425484288','是否','sys_yes_no','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941433419534336','状态','sys_status','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941433658609664','显示隐藏','sys_show_hide','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941433864130560','国际化语言类型','sys_lang_type','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941434145148928','客户端设备类型','sys_device_type','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941434531024896','菜单归属系统','sys_menu_sys_code','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941434719768576','菜单类型','sys_menu_type','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941434900123648','菜单权重','sys_menu_weight','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941435067895808','区域类型','sys_area_type','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941435311165440','机构类型','sys_office_type','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941435554435072','查询状态','sys_search_status','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941436309409792','用户性别','sys_user_sex','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941436523319296','用户状态','sys_user_status','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941436733034496','用户类型','sys_user_type','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941436921778176','角色分类','sys_role_type','1','0','system','2019-03-08 16:52:03','system','2019-03-08 16:52:03',NULL),('1103941437106327552','角色数据范围','sys_role_data_scope','1','0','system','2019-03-08 16:52:04','system','2019-03-08 16:52:04',NULL),('1103941437378957312','岗位分类','sys_post_type','1','0','system','2019-03-08 16:52:04','system','2019-03-08 16:52:04',NULL),('1103941438444310528','日志类型','sys_log_type','1','0','system','2019-03-08 16:52:04','system','2019-03-08 16:52:04',NULL),('1103941438750494720','作业分组','sys_job_group','1','0','system','2019-03-08 16:52:04','system','2019-03-08 16:52:04',NULL),('1103941439027318784','作业错过策略','sys_job_misfire_instruction','1','0','system','2019-03-08 16:52:04','system','2019-03-08 16:52:04',NULL),('1103941439258005504','作业状态','sys_job_status','1','0','system','2019-03-08 16:52:04','system','2019-03-08 16:52:04',NULL),('1103941439492886528','作业任务类型','sys_job_type','1','0','system','2019-03-08 16:52:04','system','2019-03-08 16:52:04',NULL),('1103941441086722048','作业任务事件','sys_job_event','1','0','system','2019-03-08 16:52:05','system','2019-03-08 16:52:05',NULL),('1103941442214989824','消息类型','sys_msg_type','1','0','system','2019-03-08 16:52:05','system','2019-03-08 16:52:05',NULL),('1103941446887444480','推送状态','sys_msg_push_status','1','0','system','2019-03-08 16:52:06','system','2019-03-08 16:52:06',NULL),('1103941447101353984','读取状态','sys_msg_read_status','1','0','system','2019-03-08 16:52:06','system','2019-03-08 16:52:06',NULL);

/*Table structure for table `js_sys_employee` */

DROP TABLE IF EXISTS `js_sys_employee`;

CREATE TABLE `js_sys_employee` (
  `emp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '员工编码',
  `emp_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '员工姓名',
  `emp_name_en` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '英文名',
  `office_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构编码',
  `office_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构名称',
  `company_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公司编码',
  `company_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公司名称',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态（0在职 1删除 2离职）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'JeeSite' COMMENT '租户名称',
  PRIMARY KEY (`emp_code`),
  KEY `idx_sys_employee_cco` (`company_code`),
  KEY `idx_sys_employee_cc` (`corp_code`),
  KEY `idx_sys_employee_ud` (`update_date`),
  KEY `idx_sys_employee_oc` (`office_code`),
  KEY `idx_sys_employee_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工表';

/*Data for the table `js_sys_employee` */

insert  into `js_sys_employee`(`emp_code`,`emp_name`,`emp_name_en`,`office_code`,`office_name`,`company_code`,`company_name`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`corp_code`,`corp_name`) values ('user1_4sjg','用户01',NULL,'SDJN01','企管部','SDJN','济南公司','0','system','2019-03-08 16:53:31','system','2019-03-08 16:53:31',NULL,'0','JeeSite'),('user10_ur0u','用户10',NULL,'SDJN03','研发部','SDJN','济南公司','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite'),('user11_76jk','用户11',NULL,'SDJN03','研发部','SDJN','济南公司','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite'),('user12_rukk','用户12',NULL,'SDJN03','研发部','SDJN','济南公司','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite'),('user13_b9ub','用户13',NULL,'SDJN03','研发部','SDJN','济南公司','0','system','2019-03-08 16:53:33','system','2019-03-08 16:53:33',NULL,'0','JeeSite'),('user14_knnm','用户14',NULL,'SDJN03','研发部','SDJN','济南公司','0','system','2019-03-08 16:53:33','system','2019-03-08 16:53:33',NULL,'0','JeeSite'),('user15_phwm','用户15',NULL,'SDQD01','企管部','SDQD','青岛公司','0','system','2019-03-08 16:53:33','system','2019-03-08 16:53:33',NULL,'0','JeeSite'),('user16_wff7','用户16',NULL,'SDQD01','企管部','SDQD','青岛公司','0','system','2019-03-08 16:53:33','system','2019-03-08 16:53:33',NULL,'0','JeeSite'),('user17_sb7d','用户17',NULL,'SDQD01','企管部','SDQD','青岛公司','0','system','2019-03-08 16:53:34','system','2019-03-08 16:53:34',NULL,'0','JeeSite'),('user18_vjwb','用户18',NULL,'SDQD02','财务部','SDQD','青岛公司','0','system','2019-03-08 16:53:34','system','2019-03-08 16:53:34',NULL,'0','JeeSite'),('user19_oy3w','用户19',NULL,'SDQD02','财务部','SDQD','青岛公司','0','system','2019-03-08 16:53:34','system','2019-03-08 16:53:34',NULL,'0','JeeSite'),('user2_atnd','用户02',NULL,'SDJN01','企管部','SDJN','济南公司','0','system','2019-03-08 16:53:31','system','2019-03-08 16:53:31',NULL,'0','JeeSite'),('user20_cnj4','用户20',NULL,'SDQD02','财务部','SDQD','青岛公司','0','system','2019-03-08 16:53:35','system','2019-03-08 16:53:35',NULL,'0','JeeSite'),('user21_ggns','用户21',NULL,'SDQD03','研发部','SDQD','青岛公司','0','system','2019-03-08 16:53:35','system','2019-03-08 16:53:35',NULL,'0','JeeSite'),('user22_umxl','用户22',NULL,'SDQD03','研发部','SDQD','青岛公司','0','system','2019-03-08 16:53:35','system','2019-03-08 16:53:35',NULL,'0','JeeSite'),('user23_e5h9','用户23',NULL,'SDQD03','研发部','SDQD','青岛公司','0','system','2019-03-08 16:53:36','system','2019-03-08 16:53:36',NULL,'0','JeeSite'),('user3_ueg6','用户03',NULL,'SDJN01','企管部','SDJN','济南公司','0','system','2019-03-08 16:53:31','system','2019-03-08 16:53:31',NULL,'0','JeeSite'),('user4_wuva','用户04',NULL,'SDJN02','财务部','SDJN','济南公司','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite'),('user5_ksvv','用户05',NULL,'SDJN02','财务部','SDJN','济南公司','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite'),('user6_hwof','用户06',NULL,'SDJN02','财务部','SDJN','济南公司','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite'),('user7_m0xh','用户07',NULL,'SDJN03','研发部','SDJN','济南公司','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite'),('user8_ahbj','用户08',NULL,'SDJN03','研发部','SDJN','济南公司','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite'),('user9_jqmb','用户09',NULL,'SDJN03','研发部','SDJN','济南公司','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite');

/*Table structure for table `js_sys_employee_post` */

DROP TABLE IF EXISTS `js_sys_employee_post`;

CREATE TABLE `js_sys_employee_post` (
  `emp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '员工编码',
  `post_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
  PRIMARY KEY (`emp_code`,`post_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工与岗位关联表';

/*Data for the table `js_sys_employee_post` */

insert  into `js_sys_employee_post`(`emp_code`,`post_code`) values ('user1_4sjg','dept'),('user10_ur0u','user'),('user11_76jk','user'),('user12_rukk','user'),('user13_b9ub','user'),('user14_knnm','dept'),('user15_phwm','dept'),('user16_wff7','user'),('user17_sb7d','user'),('user18_vjwb','dept'),('user19_oy3w','user'),('user2_atnd','user'),('user20_cnj4','user'),('user21_ggns','dept'),('user22_umxl','user'),('user23_e5h9','user'),('user3_ueg6','user'),('user4_wuva','dept'),('user5_ksvv','user'),('user6_hwof','user'),('user7_m0xh','dept'),('user8_ahbj','user'),('user9_jqmb','user');

/*Table structure for table `js_sys_file_entity` */

DROP TABLE IF EXISTS `js_sys_file_entity`;

CREATE TABLE `js_sys_file_entity` (
  `file_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件编号',
  `file_md5` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件MD5',
  `file_path` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件相对路径',
  `file_content_type` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件内容类型',
  `file_extension` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件后缀扩展名',
  `file_size` decimal(31,0) NOT NULL COMMENT '文件大小(单位B)',
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `file_md5` (`file_md5`),
  KEY `idx_sys_file_entity_md5` (`file_md5`),
  KEY `idx_sys_file_entity_size` (`file_size`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文件实体表';

/*Data for the table `js_sys_file_entity` */

/*Table structure for table `js_sys_file_upload` */

DROP TABLE IF EXISTS `js_sys_file_upload`;

CREATE TABLE `js_sys_file_upload` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `file_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件编号',
  `file_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件名称',
  `file_type` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件分类（image、media、file）',
  `biz_key` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务主键',
  `biz_type` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务类型',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `idx_sys_file_biz_ft` (`file_type`),
  KEY `idx_sys_file_biz_fi` (`file_id`),
  KEY `idx_sys_file_biz_status` (`status`),
  KEY `idx_sys_file_biz_cb` (`create_by`),
  KEY `idx_sys_file_biz_ud` (`update_date`),
  KEY `idx_sys_file_biz_bt` (`biz_type`),
  KEY `idx_sys_file_biz_bk` (`biz_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文件上传表';

/*Data for the table `js_sys_file_upload` */

/*Table structure for table `js_sys_job` */

DROP TABLE IF EXISTS `js_sys_job`;

CREATE TABLE `js_sys_job` (
  `job_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务组名',
  `description` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务描述',
  `invoke_target` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Cron执行表达式',
  `misfire_instruction` decimal(1,0) NOT NULL COMMENT '计划执行错误策略',
  `concurrent` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否并发执行',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态（0正常 1删除 2暂停）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`job_name`,`job_group`),
  KEY `idx_sys_job_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='作业调度表';

/*Data for the table `js_sys_job` */

insert  into `js_sys_job`(`job_name`,`job_group`,`description`,`invoke_target`,`cron_expression`,`misfire_instruction`,`concurrent`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`) values ('MsgLocalMergePushTask','SYSTEM','消息推送服务 (延迟推送)','msgLocalMergePushTask.execute()','0 0/30 * * * ?','2','0','2','system','2019-03-08 16:53:36','system','2019-03-08 16:53:36',NULL),('MsgLocalPushTask','SYSTEM','消息推送服务 (实时推送)','msgLocalPushTask.execute()','0/3 * * * * ?','2','0','2','system','2019-03-08 16:53:36','system','2019-03-08 16:53:36',NULL);

/*Table structure for table `js_sys_job_log` */

DROP TABLE IF EXISTS `js_sys_job_log`;

CREATE TABLE `js_sys_job_log` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `job_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务组名',
  `job_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日志类型',
  `job_event` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日志事件',
  `job_message` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日志信息',
  `is_exception` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否异常',
  `exception_info` text COLLATE utf8mb4_unicode_ci COMMENT '异常信息',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_sys_job_log_jn` (`job_name`),
  KEY `idx_sys_job_log_jg` (`job_group`),
  KEY `idx_sys_job_log_t` (`job_type`),
  KEY `idx_sys_job_log_e` (`job_event`),
  KEY `idx_sys_job_log_ie` (`is_exception`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='作业调度日志表';

/*Data for the table `js_sys_job_log` */

/*Table structure for table `js_sys_lang` */

DROP TABLE IF EXISTS `js_sys_lang`;

CREATE TABLE `js_sys_lang` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `module_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '归属模块',
  `lang_code` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '语言编码',
  `lang_text` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '语言译文',
  `lang_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '语言类型',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `idx_sys_lang_code` (`lang_code`),
  KEY `idx_sys_lang_type` (`lang_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='国际化语言';

/*Data for the table `js_sys_lang` */

/*Table structure for table `js_sys_log` */

DROP TABLE IF EXISTS `js_sys_log`;

CREATE TABLE `js_sys_log` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `log_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日志类型',
  `log_title` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日志标题',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_by_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名称',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `request_uri` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求URI',
  `request_method` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作方式',
  `request_params` longtext COLLATE utf8mb4_unicode_ci COMMENT '操作提交的数据',
  `diff_modify_data` text COLLATE utf8mb4_unicode_ci COMMENT '新旧数据比较结果',
  `biz_key` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务主键',
  `biz_type` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务类型',
  `remote_addr` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作IP地址',
  `server_addr` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求服务器地址',
  `is_exception` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否异常',
  `exception_info` text COLLATE utf8mb4_unicode_ci COMMENT '异常信息',
  `user_agent` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户代理',
  `device_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备名称/操作系统',
  `browser_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '浏览器名称',
  `execute_time` decimal(19,0) DEFAULT NULL COMMENT '执行时间',
  `corp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'JeeSite' COMMENT '租户名称',
  PRIMARY KEY (`id`),
  KEY `idx_sys_log_cb` (`create_by`),
  KEY `idx_sys_log_cc` (`corp_code`),
  KEY `idx_sys_log_lt` (`log_type`),
  KEY `idx_sys_log_bk` (`biz_key`),
  KEY `idx_sys_log_bt` (`biz_type`),
  KEY `idx_sys_log_ie` (`is_exception`),
  KEY `idx_sys_log_cd` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表';

/*Data for the table `js_sys_log` */

insert  into `js_sys_log`(`id`,`log_type`,`log_title`,`create_by`,`create_by_name`,`create_date`,`request_uri`,`request_method`,`request_params`,`diff_modify_data`,`biz_key`,`biz_type`,`remote_addr`,`server_addr`,`is_exception`,`exception_info`,`user_agent`,`device_name`,`browser_name`,`execute_time`,`corp_code`,`corp_name`) values ('1103942633490882560','loginLogout','系统登录','system','超级管理员','2019-03-08 16:56:49','/trading/a/login','POST','username=F3EDC7D2C193E0B8DCF554C726719ED2&password=*&validCode=',NULL,NULL,NULL,'127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','0','0','JeeSite'),('1103942694983573504','loginLogout','系统退出','system','超级管理员','2019-03-08 16:57:03','/trading/a/logout','GET','',NULL,NULL,NULL,'127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','0','0','JeeSite'),('1103942908192628736','loginLogout','系统登录','system','超级管理员','2019-03-08 16:57:54','/trading/a/login','POST','username=F3EDC7D2C193E0B8DCF554C726719ED2&password=*&validCode=',NULL,NULL,NULL,'127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','0','0','JeeSite'),('1103942932561534976','access','系统管理/组织管理/用户管理','system','超级管理员','2019-03-08 16:58:00','/trading/a/sys/empUser/index','GET','',NULL,'','EmpUser','127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','582','0','JeeSite'),('1103942943286370304','select','系统管理/组织管理/用户管理/查看','system','超级管理员','2019-03-08 16:58:03','/trading/a/sys/empUser/list','GET','',NULL,'','EmpUser','127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','1627','0','JeeSite'),('1103942962034909184','select','系统管理/组织管理/用户管理','system','超级管理员','2019-03-08 16:58:07','/trading/a/sys/empUser/listData','POST','ctrlPermi=2&loginCode=&userName=&email=&mobile=&phone=&refName=&employee.office.officeName=&employee.office.officeCode=&employee.company.companyName=&employee.company.companyCode=&employee.postCode=&status=&pageNo=&pageSize=&orderBy=',NULL,'','EmpUser','127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','1917','0','JeeSite'),('1103997552443252736','loginLogout','系统登录','system','超级管理员','2019-03-08 20:35:02','/trading/a/login','POST','username=F3EDC7D2C193E0B8DCF554C726719ED2&password=*&validCode=',NULL,NULL,NULL,'127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','0','0','JeeSite'),('1103997611457110016','access','系统管理/组织管理/用户管理','system','超级管理员','2019-03-08 20:35:17','/trading/a/sys/empUser/index','GET','',NULL,'','EmpUser','127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','533','0','JeeSite'),('1103997621506662400','select','系统管理/组织管理/用户管理/查看','system','超级管理员','2019-03-08 20:35:19','/trading/a/sys/empUser/list','GET','',NULL,'','EmpUser','127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','1448','0','JeeSite'),('1103997635691798528','select','系统管理/组织管理/用户管理','system','超级管理员','2019-03-08 20:35:22','/trading/a/sys/empUser/listData','POST','ctrlPermi=2&loginCode=&userName=&email=&mobile=&phone=&refName=&employee.office.officeName=&employee.office.officeCode=&employee.company.companyName=&employee.company.companyCode=&employee.postCode=&status=&pageNo=&pageSize=&orderBy=',NULL,'','EmpUser','127.0.0.1','http://127.0.0.1:8080','0','','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36','Windows 10','Chrome','1661','0','JeeSite');

/*Table structure for table `js_sys_menu` */

DROP TABLE IF EXISTS `js_sys_menu`;

CREATE TABLE `js_sys_menu` (
  `menu_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单编码',
  `parent_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '父级编号',
  `parent_codes` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有父级编号',
  `tree_sort` decimal(10,0) NOT NULL COMMENT '本级排序号（升序）',
  `tree_sorts` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有级别排序号',
  `tree_leaf` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否最末级',
  `tree_level` decimal(4,0) NOT NULL COMMENT '层次级别',
  `tree_names` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '全节点名',
  `menu_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
  `menu_type` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单类型（1菜单 2权限 3开发）',
  `menu_href` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '链接',
  `menu_target` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '目标',
  `menu_icon` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `menu_color` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '颜色',
  `permission` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限标识',
  `weight` decimal(4,0) DEFAULT NULL COMMENT '菜单权重',
  `is_show` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否显示（1显示 0隐藏）',
  `sys_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '归属系统（default:主导航菜单、mobileApp:APP菜单）',
  `module_codes` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '归属模块（多个用逗号隔开）',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  `extend_s1` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 1',
  `extend_s2` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 2',
  `extend_s3` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 3',
  `extend_s4` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 4',
  `extend_s5` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 5',
  `extend_s6` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 6',
  `extend_s7` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 7',
  `extend_s8` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 8',
  `extend_i1` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 1',
  `extend_i2` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 2',
  `extend_i3` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 3',
  `extend_i4` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 4',
  `extend_f1` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 1',
  `extend_f2` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 2',
  `extend_f3` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 3',
  `extend_f4` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 4',
  `extend_d1` datetime DEFAULT NULL COMMENT '扩展 Date 1',
  `extend_d2` datetime DEFAULT NULL COMMENT '扩展 Date 2',
  `extend_d3` datetime DEFAULT NULL COMMENT '扩展 Date 3',
  `extend_d4` datetime DEFAULT NULL COMMENT '扩展 Date 4',
  PRIMARY KEY (`menu_code`),
  KEY `idx_sys_menu_pc` (`parent_code`),
  KEY `idx_sys_menu_ts` (`tree_sort`),
  KEY `idx_sys_menu_status` (`status`),
  KEY `idx_sys_menu_mt` (`menu_type`),
  KEY `idx_sys_menu_sc` (`sys_code`),
  KEY `idx_sys_menu_is` (`is_show`),
  KEY `idx_sys_menu_mcs` (`module_codes`),
  KEY `idx_sys_menu_wt` (`weight`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单表';

/*Data for the table `js_sys_menu` */

insert  into `js_sys_menu`(`menu_code`,`parent_code`,`parent_codes`,`tree_sort`,`tree_sorts`,`tree_leaf`,`tree_level`,`tree_names`,`menu_name`,`menu_type`,`menu_href`,`menu_target`,`menu_icon`,`menu_color`,`permission`,`weight`,`is_show`,`sys_code`,`module_codes`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`extend_s1`,`extend_s2`,`extend_s3`,`extend_s4`,`extend_s5`,`extend_s6`,`extend_s7`,`extend_s8`,`extend_i1`,`extend_i2`,`extend_i3`,`extend_i4`,`extend_f1`,`extend_f2`,`extend_f3`,`extend_f4`,`extend_d1`,`extend_d2`,`extend_d3`,`extend_d4`) values ('1103941623899656192','0','0,','9000','0000009000,','0','0','系统管理','系统管理','1','','','icon-settings','','','40','1','default','core','0','system','2019-03-08 16:52:48','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941626831474688','1103941623899656192','0,1103941623899656192,','300','0000009000,0000000300,','0','1','系统管理/组织管理','组织管理','1','','','icon-grid','','','40','1','default','core','0','system','2019-03-08 16:52:49','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941628077182976','1103941626831474688','0,1103941623899656192,1103941626831474688,','40','0000009000,0000000300,0000000040,','0','2','系统管理/组织管理/用户管理','用户管理','1','/sys/empUser/index','','icon-user','','','40','1','default','core','0','system','2019-03-08 16:52:49','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941629532606464','1103941628077182976','0,1103941623899656192,1103941626831474688,1103941628077182976,','30','0000009000,0000000300,0000000040,0000000030,','1','3','系统管理/组织管理/用户管理/查看','查看','2','','','','','sys:empUser:view','40','1','default','core','0','system','2019-03-08 16:52:49','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941630707011584','1103941628077182976','0,1103941623899656192,1103941626831474688,1103941628077182976,','40','0000009000,0000000300,0000000040,0000000040,','1','3','系统管理/组织管理/用户管理/编辑','编辑','2','','','','','sys:empUser:edit','40','1','default','core','0','system','2019-03-08 16:52:50','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941632162435072','1103941628077182976','0,1103941623899656192,1103941626831474688,1103941628077182976,','60','0000009000,0000000300,0000000040,0000000060,','1','3','系统管理/组织管理/用户管理/分配角色','分配角色','2','','','','','sys:empUser:authRole','40','1','default','core','0','system','2019-03-08 16:52:50','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941633521389568','1103941628077182976','0,1103941623899656192,1103941626831474688,1103941628077182976,','50','0000009000,0000000300,0000000040,0000000050,','1','3','系统管理/组织管理/用户管理/分配数据','分配数据','2','','','','','sys:empUser:authDataScope','40','1','default','core','0','system','2019-03-08 16:52:50','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941634922287104','1103941628077182976','0,1103941623899656192,1103941626831474688,1103941628077182976,','60','0000009000,0000000300,0000000040,0000000060,','1','3','系统管理/组织管理/用户管理/停用启用','停用启用','2','','','','','sys:empUser:updateStatus','40','1','default','core','0','system','2019-03-08 16:52:51','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941636574842880','1103941628077182976','0,1103941623899656192,1103941626831474688,1103941628077182976,','70','0000009000,0000000300,0000000040,0000000070,','1','3','系统管理/组织管理/用户管理/重置密码','重置密码','2','','','','','sys:empUser:resetpwd','40','1','default','core','0','system','2019-03-08 16:52:51','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941638084792320','1103941626831474688','0,1103941623899656192,1103941626831474688,','50','0000009000,0000000300,0000000050,','0','2','系统管理/组织管理/机构管理','机构管理','1','/sys/office/list','','icon-grid','','','60','1','default','core','0','system','2019-03-08 16:52:51','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941639435358208','1103941638084792320','0,1103941623899656192,1103941626831474688,1103941638084792320,','30','0000009000,0000000300,0000000050,0000000030,','1','3','系统管理/组织管理/机构管理/查看','查看','2','','','','','sys:office:view','60','1','default','core','0','system','2019-03-08 16:52:52','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941640546848768','1103941638084792320','0,1103941623899656192,1103941626831474688,1103941638084792320,','40','0000009000,0000000300,0000000050,0000000040,','1','3','系统管理/组织管理/机构管理/编辑','编辑','2','','','','','sys:office:edit','60','1','default','core','0','system','2019-03-08 16:52:52','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941641930969088','1103941626831474688','0,1103941623899656192,1103941626831474688,','70','0000009000,0000000300,0000000070,','0','2','系统管理/组织管理/公司管理','公司管理','1','/sys/company/list','','icon-fire','','','60','1','default','core','0','system','2019-03-08 16:52:52','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941643134734336','1103941641930969088','0,1103941623899656192,1103941626831474688,1103941641930969088,','30','0000009000,0000000300,0000000070,0000000030,','1','3','系统管理/组织管理/公司管理/查看','查看','2','','','','','sys:company:view','60','1','default','core','0','system','2019-03-08 16:52:53','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941644393025536','1103941641930969088','0,1103941623899656192,1103941626831474688,1103941641930969088,','40','0000009000,0000000300,0000000070,0000000040,','1','3','系统管理/组织管理/公司管理/编辑','编辑','2','','','','','sys:company:edit','60','1','default','core','0','system','2019-03-08 16:52:53','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941645894586368','1103941626831474688','0,1103941623899656192,1103941626831474688,','70','0000009000,0000000300,0000000070,','0','2','系统管理/组织管理/岗位管理','岗位管理','1','/sys/post/list','','icon-trophy','','','60','1','default','core','0','system','2019-03-08 16:52:53','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941647358398464','1103941645894586368','0,1103941623899656192,1103941626831474688,1103941645894586368,','30','0000009000,0000000300,0000000070,0000000030,','1','3','系统管理/组织管理/岗位管理/查看','查看','2','','','','','sys:post:view','60','1','default','core','0','system','2019-03-08 16:52:54','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941648960622592','1103941645894586368','0,1103941623899656192,1103941626831474688,1103941645894586368,','40','0000009000,0000000300,0000000070,0000000040,','1','3','系统管理/组织管理/岗位管理/编辑','编辑','2','','','','','sys:post:edit','60','1','default','core','0','system','2019-03-08 16:52:54','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941650747396096','1103941623899656192','0,1103941623899656192,','400','0000009000,0000000400,','0','1','系统管理/权限管理','权限管理','1','','','icon-social-dropbox','','','60','1','default','core','0','system','2019-03-08 16:52:54','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941652156682240','1103941650747396096','0,1103941623899656192,1103941650747396096,','30','0000009000,0000000400,0000000030,','1','2','系统管理/权限管理/角色管理','角色管理','1','/sys/role/list','','icon-people','','sys:role','60','1','default','core','0','system','2019-03-08 16:52:55','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941653456916480','1103941650747396096','0,1103941623899656192,1103941650747396096,','40','0000009000,0000000400,0000000040,','1','2','系统管理/权限管理/二级管理员','二级管理员','1','/sys/secAdmin/list','','icon-user-female','','sys:secAdmin','60','1','default','core','0','system','2019-03-08 16:52:55','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941654727790592','1103941650747396096','0,1103941623899656192,1103941650747396096,','50','0000009000,0000000400,0000000050,','1','2','系统管理/权限管理/系统管理员','系统管理员','1','/sys/corpAdmin/list','','icon-badge','','sys:corpAdmin','80','1','default','core','0','system','2019-03-08 16:52:55','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941656166436864','1103941623899656192','0,1103941623899656192,','500','0000009000,0000000500,','0','1','系统管理/系统设置','系统设置','1','','','icon-settings','','','60','1','default','core','0','system','2019-03-08 16:52:56','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941657449893888','1103941656166436864','0,1103941623899656192,1103941656166436864,','30','0000009000,0000000500,0000000030,','1','2','系统管理/系统设置/菜单管理','菜单管理','1','/sys/menu/list','','icon-book-open','','sys:menu','80','1','default','core','0','system','2019-03-08 16:52:56','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941658628493312','1103941656166436864','0,1103941623899656192,1103941656166436864,','40','0000009000,0000000500,0000000040,','1','2','系统管理/系统设置/模块管理','模块管理','1','/sys/module/list','','icon-grid','','sys:module','80','1','default','core','0','system','2019-03-08 16:52:56','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941660461404160','1103941656166436864','0,1103941623899656192,1103941656166436864,','50','0000009000,0000000500,0000000050,','1','2','系统管理/系统设置/参数设置','参数设置','1','/sys/config/list','','icon-wrench','','sys:config','60','1','default','core','0','system','2019-03-08 16:52:57','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941661614837760','1103941656166436864','0,1103941623899656192,1103941656166436864,','60','0000009000,0000000500,0000000060,','0','2','系统管理/系统设置/字典管理','字典管理','1','/sys/dictType/list','','icon-social-dropbox','','','60','1','default','core','0','system','2019-03-08 16:52:57','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941662944432128','1103941661614837760','0,1103941623899656192,1103941656166436864,1103941661614837760,','30','0000009000,0000000500,0000000060,0000000030,','1','3','系统管理/系统设置/字典管理/类型查看','类型查看','2','','','','','sys:dictType:view','60','1','default','core','0','system','2019-03-08 16:52:57','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941664290803712','1103941661614837760','0,1103941623899656192,1103941656166436864,1103941661614837760,','40','0000009000,0000000500,0000000060,0000000040,','1','3','系统管理/系统设置/字典管理/类型编辑','类型编辑','2','','','','','sys:dictType:edit','80','1','default','core','0','system','2019-03-08 16:52:58','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941665859473408','1103941661614837760','0,1103941623899656192,1103941656166436864,1103941661614837760,','50','0000009000,0000000500,0000000060,0000000050,','1','3','系统管理/系统设置/字典管理/数据查看','数据查看','2','','','','','sys:dictData:view','60','1','default','core','0','system','2019-03-08 16:52:58','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941667205844992','1103941661614837760','0,1103941623899656192,1103941656166436864,1103941661614837760,','60','0000009000,0000000500,0000000060,0000000060,','1','3','系统管理/系统设置/字典管理/数据编辑','数据编辑','2','','','','','sys:dictData:edit','60','1','default','core','0','system','2019-03-08 16:52:58','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941668577382400','1103941656166436864','0,1103941623899656192,1103941656166436864,','70','0000009000,0000000500,0000000070,','1','2','系统管理/系统设置/行政区划','行政区划','1','/sys/area/list','','icon-map','','sys:area','60','1','default','core','0','system','2019-03-08 16:52:59','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941669865033728','1103941656166436864','0,1103941623899656192,1103941656166436864,','80','0000009000,0000000500,0000000080,','1','2','系统管理/系统设置/国际化管理','国际化管理','1','/sys/lang/list','','icon-globe','','sys:lang','80','1','default','core','0','system','2019-03-08 16:52:59','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941671232376832','1103941656166436864','0,1103941623899656192,1103941656166436864,','90','0000009000,0000000500,0000000090,','1','2','系统管理/系统设置/产品许可信息','产品许可信息','1','//licence','','icon-paper-plane','','','80','1','default','core','0','system','2019-03-08 16:52:59','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941672427753472','1103941623899656192','0,1103941623899656192,','600','0000009000,0000000600,','0','1','系统管理/系统监控','系统监控','1','','','icon-ghost','','','60','1','default','core','0','system','2019-03-08 16:53:00','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941673715404800','1103941672427753472','0,1103941623899656192,1103941672427753472,','40','0000009000,0000000600,0000000040,','1','2','系统管理/系统监控/访问日志','访问日志','1','/sys/log/list','','fa fa-bug','','sys:log','60','1','default','core','0','system','2019-03-08 16:53:00','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941675103719424','1103941672427753472','0,1103941623899656192,1103941672427753472,','50','0000009000,0000000600,0000000050,','1','2','系统管理/系统监控/数据监控','数据监控','1','//druid','','icon-disc','','sys:state:druid','80','1','default','core','0','system','2019-03-08 16:53:00','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941676580114432','1103941672427753472','0,1103941623899656192,1103941672427753472,','60','0000009000,0000000600,0000000060,','1','2','系统管理/系统监控/缓存监控','缓存监控','1','/state/cache/index','','icon-social-dribbble','','sys:stste:cache','80','1','default','core','0','system','2019-03-08 16:53:01','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941677658050560','1103941672427753472','0,1103941623899656192,1103941672427753472,','70','0000009000,0000000600,0000000070,','1','2','系统管理/系统监控/服务器监控','服务器监控','1','/state/server/index','','icon-speedometer','','sys:state:server','80','1','default','core','0','system','2019-03-08 16:53:01','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941678933118976','1103941672427753472','0,1103941623899656192,1103941672427753472,','80','0000009000,0000000600,0000000080,','1','2','系统管理/系统监控/作业监控','作业监控','1','/job/list','','icon-notebook','','sys:job','80','1','default','core','0','system','2019-03-08 16:53:01','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941680447262720','1103941672427753472','0,1103941623899656192,1103941672427753472,','90','0000009000,0000000600,0000000090,','1','2','系统管理/系统监控/在线用户','在线用户','1','/sys/online/list','','icon-social-twitter','','sys:online','60','1','default','core','0','system','2019-03-08 16:53:02','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941682691215360','1103941672427753472','0,1103941623899656192,1103941672427753472,','100','0000009000,0000000600,0000000100,','1','2','系统管理/系统监控/在线文档','在线文档','1','//swagger-ui.html','','icon-book-open','','sys:swagger','80','1','default','core','0','system','2019-03-08 16:53:03','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941691859963904','1103941623899656192','0,1103941623899656192,','700','0000009000,0000000700,','0','1','系统管理/消息推送','消息推送','1','','','icon-envelope-letter','','','60','1','default','core','0','system','2019-03-08 16:53:05','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941703096504320','1103941691859963904','0,1103941623899656192,1103941691859963904,','30','0000009000,0000000700,0000000030,','1','2','系统管理/消息推送/未完成消息','未完成消息','1','/msg/msgPush/list','','','','msg:msgPush','60','1','default','core','0','system','2019-03-08 16:53:07','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941717868843008','1103941691859963904','0,1103941623899656192,1103941691859963904,','40','0000009000,0000000700,0000000040,','1','2','系统管理/消息推送/已完成消息','已完成消息','1','/msg/msgPush/list?pushed=true','','','','msg:msgPush','60','1','default','core','0','system','2019-03-08 16:53:11','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941724403568640','1103941691859963904','0,1103941623899656192,1103941691859963904,','50','0000009000,0000000700,0000000050,','1','2','系统管理/消息推送/消息模板管理','消息模板管理','1','/msg/msgTemplate/list','','','','msg:msgTemplate','60','1','default','core','0','system','2019-03-08 16:53:12','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941726228090880','1103941623899656192','0,1103941623899656192,','900','0000009000,0000000900,','0','1','系统管理/研发工具','研发工具','1','','','fa fa-code','','','80','1','default','core','0','system','2019-03-08 16:53:13','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941728493015040','1103941726228090880','0,1103941623899656192,1103941726228090880,','30','0000009000,0000000900,0000000030,','1','2','系统管理/研发工具/代码生成工具','代码生成工具','1','/gen/genTable/list','','fa fa-code','','gen:genTable','80','1','default','core','0','system','2019-03-08 16:53:13','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941730091044864','1103941726228090880','0,1103941623899656192,1103941726228090880,','100','0000009000,0000000900,0000000100,','0','2','系统管理/研发工具/代码生成实例','代码生成实例','1','','','icon-social-dropbox','','','80','1','default','core','0','system','2019-03-08 16:53:13','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941733077389312','1103941730091044864','0,1103941623899656192,1103941726228090880,1103941730091044864,','30','0000009000,0000000900,0000000100,0000000030,','1','3','系统管理/研发工具/代码生成实例/单表_主子表','单表/主子表','1','/test/testData/list','','','','test:testData','80','1','default','core','0','system','2019-03-08 16:53:14','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941735346507776','1103941730091044864','0,1103941623899656192,1103941726228090880,1103941730091044864,','40','0000009000,0000000900,0000000100,0000000040,','1','3','系统管理/研发工具/代码生成实例/树表_树结构表','树表/树结构表','1','/test/testTree/list','','','','test:testTree','80','1','default','core','0','system','2019-03-08 16:53:15','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941737766621184','1103941726228090880','0,1103941623899656192,1103941726228090880,','200','0000009000,0000000900,0000000200,','0','2','系统管理/研发工具/数据表格实例','数据表格实例','1','','','','','','80','1','default','core','0','system','2019-03-08 16:53:15','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941739247210496','1103941737766621184','0,1103941623899656192,1103941726228090880,1103941737766621184,','30','0000009000,0000000900,0000000200,0000000030,','1','3','系统管理/研发工具/数据表格实例/多表头分组小计合计','多表头分组小计合计','1','/demo/dataGrid/groupGrid','','','','','80','1','default','core','0','system','2019-03-08 16:53:16','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941740916543488','1103941737766621184','0,1103941623899656192,1103941726228090880,1103941737766621184,','40','0000009000,0000000900,0000000200,0000000040,','1','3','系统管理/研发工具/数据表格实例/编辑表格多行编辑','编辑表格多行编辑','1','/demo/dataGrid/editGrid','','','','','80','1','default','core','0','system','2019-03-08 16:53:16','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941742472630272','1103941726228090880','0,1103941623899656192,1103941726228090880,','300','0000009000,0000000900,0000000300,','0','2','系统管理/研发工具/表单组件实例','表单组件实例','1','','','','','','80','1','default','core','0','system','2019-03-08 16:53:17','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941745362505728','1103941742472630272','0,1103941623899656192,1103941726228090880,1103941742472630272,','30','0000009000,0000000900,0000000300,0000000030,','1','3','系统管理/研发工具/表单组件实例/组件应用实例','组件应用实例','1','/demo/form/editForm','','','','','80','1','default','core','0','system','2019-03-08 16:53:17','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941749430980608','1103941742472630272','0,1103941623899656192,1103941726228090880,1103941742472630272,','40','0000009000,0000000900,0000000300,0000000040,','1','3','系统管理/研发工具/表单组件实例/栅格布局实例','栅格布局实例','1','/demo/form/layoutForm','','','','','80','1','default','core','0','system','2019-03-08 16:53:18','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941751196782592','1103941742472630272','0,1103941623899656192,1103941726228090880,1103941742472630272,','50','0000009000,0000000900,0000000300,0000000050,','1','3','系统管理/研发工具/表单组件实例/表格表单实例','表格表单实例','1','/demo/form/tableForm','','','','','80','1','default','core','0','system','2019-03-08 16:53:18','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941753692393472','1103941726228090880','0,1103941623899656192,1103941726228090880,','400','0000009000,0000000900,0000000400,','0','2','系统管理/研发工具/前端界面实例','前端界面实例','1','','','','','','80','1','default','core','0','system','2019-03-08 16:53:19','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941756162838528','1103941753692393472','0,1103941623899656192,1103941726228090880,1103941753692393472,','30','0000009000,0000000900,0000000400,0000000030,','1','3','系统管理/研发工具/前端界面实例/图标样式查找','图标样式查找','1','//tags/iconselect','','','','','80','1','default','core','0','system','2019-03-08 16:53:20','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941758373236736','1103941623899656192','0,1103941623899656192,','999','0000009000,0000000999,','0','1','系统管理/JeeSite社区','JeeSite社区','1','','','fa fa-code','','','80','1','default','core','0','system','2019-03-08 16:53:20','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941760713658368','1103941758373236736','0,1103941623899656192,1103941758373236736,','30','0000009000,0000000999,0000000030,','1','2','系统管理/JeeSite社区/官方网站','官方网站','1','http://jeesite.com','_blank','','','','80','1','default','core','0','system','2019-03-08 16:53:21','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941763116994560','1103941758373236736','0,1103941623899656192,1103941758373236736,','50','0000009000,0000000999,0000000050,','1','2','系统管理/JeeSite社区/作者博客','作者博客','1','https://my.oschina.net/thinkgem','_blank','','','','80','1','default','core','0','system','2019-03-08 16:53:21','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941765839097856','1103941758373236736','0,1103941623899656192,1103941758373236736,','40','0000009000,0000000999,0000000040,','1','2','系统管理/JeeSite社区/问题反馈','问题反馈','1','https://gitee.com/thinkgem/jeesite4/issues','_blank','','','','80','1','default','core','0','system','2019-03-08 16:53:22','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1103941767873335296','1103941758373236736','0,1103941623899656192,1103941758373236736,','60','0000009000,0000000999,0000000060,','1','2','系统管理/JeeSite社区/开源社区','开源社区','1','http://jeesite.net','_blank','','','','80','1','default','core','0','system','2019-03-08 16:53:22','system','2019-03-08 20:32:12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `js_sys_module` */

DROP TABLE IF EXISTS `js_sys_module`;

CREATE TABLE `js_sys_module` (
  `module_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模块编码',
  `module_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模块名称',
  `description` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块描述',
  `main_class_name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主类全名',
  `current_version` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '当前版本',
  `upgrade_info` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '升级信息',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`module_code`),
  KEY `idx_sys_module_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='模块表';

/*Data for the table `js_sys_module` */

insert  into `js_sys_module`(`module_code`,`module_name`,`description`,`main_class_name`,`current_version`,`upgrade_info`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`) values ('cms','内容管理','网站、站点、栏目、文章、链接、评论、留言板','com.jeesite.modules.cms.web.CmsController','4.0.0',NULL,'0','system','2019-03-08 16:52:00','system','2019-03-08 16:52:00',NULL),('core','核心模块','用户、角色、组织、模块、菜单、字典、参数','com.jeesite.modules.sys.web.LoginController','4.1.3',NULL,'0','system','2019-03-08 16:52:00','system','2019-03-08 16:52:00',NULL);

/*Table structure for table `js_sys_msg_inner` */

DROP TABLE IF EXISTS `js_sys_msg_inner`;

CREATE TABLE `js_sys_msg_inner` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `msg_title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息标题',
  `content_level` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容级别（1普通 2一般 3紧急）',
  `content_type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容类型（1公告 2新闻 3会议 4其它）',
  `msg_content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息内容',
  `receive_type` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者类型（1用户 2部门 3角色 4岗位）',
  `receive_codes` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者字符串',
  `receive_names` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者名称字符串',
  `send_user_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发送者用户编码',
  `send_user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发送者用户姓名',
  `send_date` datetime NOT NULL COMMENT '发送时间',
  `is_attac` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否有附件',
  `notify_types` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '通知类型（PC APP 短信 邮件 微信）多选',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态（0正常 1删除 4审核 5驳回 9草稿）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `idx_sys_msg_inner_cb` (`create_by`),
  KEY `idx_sys_msg_inner_status` (`status`),
  KEY `idx_sys_msg_inner_cl` (`content_level`),
  KEY `idx_sys_msg_inner_sc` (`send_user_code`),
  KEY `idx_sys_msg_inner_sd` (`send_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='内部消息';

/*Data for the table `js_sys_msg_inner` */

/*Table structure for table `js_sys_msg_inner_record` */

DROP TABLE IF EXISTS `js_sys_msg_inner_record`;

CREATE TABLE `js_sys_msg_inner_record` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `msg_inner_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属消息',
  `receive_user_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '接受者用户编码',
  `receive_user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者用户姓名',
  `read_status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '读取状态（0未送达 1已读 2未读）',
  `read_date` datetime DEFAULT NULL COMMENT '阅读时间',
  `is_star` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否标星',
  PRIMARY KEY (`id`),
  KEY `idx_sys_msg_inner_r_mi` (`msg_inner_id`),
  KEY `idx_sys_msg_inner_r_ruc` (`receive_user_code`),
  KEY `idx_sys_msg_inner_r_status` (`read_status`),
  KEY `idx_sys_msg_inner_r_star` (`is_star`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='内部消息发送记录表';

/*Data for the table `js_sys_msg_inner_record` */

/*Table structure for table `js_sys_msg_push` */

DROP TABLE IF EXISTS `js_sys_msg_push`;

CREATE TABLE `js_sys_msg_push` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `msg_type` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息类型（PC APP 短信 邮件 微信）',
  `msg_title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息标题',
  `msg_content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息内容',
  `biz_key` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务主键',
  `biz_type` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务类型',
  `receive_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者账号',
  `receive_user_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者用户编码',
  `receive_user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者用户姓名',
  `send_user_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发送者用户编码',
  `send_user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发送者用户姓名',
  `send_date` datetime NOT NULL COMMENT '发送时间',
  `is_merge_push` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否合并推送',
  `plan_push_date` datetime DEFAULT NULL COMMENT '计划推送时间',
  `push_number` int(11) DEFAULT NULL COMMENT '推送尝试次数',
  `push_return_code` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推送返回结果码',
  `push_return_msg_id` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推送返回消息编号',
  `push_return_content` text COLLATE utf8mb4_unicode_ci COMMENT '推送返回的内容信息',
  `push_status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推送状态（0未推送 1成功  2失败）',
  `push_date` datetime DEFAULT NULL COMMENT '推送时间',
  `read_status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '读取状态（0未送达 1已读 2未读）',
  `read_date` datetime DEFAULT NULL COMMENT '读取时间',
  PRIMARY KEY (`id`),
  KEY `idx_sys_msg_push_type` (`msg_type`),
  KEY `idx_sys_msg_push_rc` (`receive_code`),
  KEY `idx_sys_msg_push_uc` (`receive_user_code`),
  KEY `idx_sys_msg_push_suc` (`send_user_code`),
  KEY `idx_sys_msg_push_pd` (`plan_push_date`),
  KEY `idx_sys_msg_push_ps` (`push_status`),
  KEY `idx_sys_msg_push_rs` (`read_status`),
  KEY `idx_sys_msg_push_bk` (`biz_key`),
  KEY `idx_sys_msg_push_bt` (`biz_type`),
  KEY `idx_sys_msg_push_imp` (`is_merge_push`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息推送表';

/*Data for the table `js_sys_msg_push` */

/*Table structure for table `js_sys_msg_pushed` */

DROP TABLE IF EXISTS `js_sys_msg_pushed`;

CREATE TABLE `js_sys_msg_pushed` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `msg_type` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息类型（PC APP 短信 邮件 微信）',
  `msg_title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息标题',
  `msg_content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息内容',
  `biz_key` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务主键',
  `biz_type` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务类型',
  `receive_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者账号',
  `receive_user_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者用户编码',
  `receive_user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接受者用户姓名',
  `send_user_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发送者用户编码',
  `send_user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发送者用户姓名',
  `send_date` datetime NOT NULL COMMENT '发送时间',
  `is_merge_push` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否合并推送',
  `plan_push_date` datetime DEFAULT NULL COMMENT '计划推送时间',
  `push_number` int(11) DEFAULT NULL COMMENT '推送尝试次数',
  `push_return_content` text COLLATE utf8mb4_unicode_ci COMMENT '推送返回的内容信息',
  `push_return_code` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推送返回结果码',
  `push_return_msg_id` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推送返回消息编号',
  `push_status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推送状态（0未推送 1成功  2失败）',
  `push_date` datetime DEFAULT NULL COMMENT '推送时间',
  `read_status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '读取状态（0未送达 1已读 2未读）',
  `read_date` datetime DEFAULT NULL COMMENT '读取时间',
  PRIMARY KEY (`id`),
  KEY `idx_sys_msg_pushed_type` (`msg_type`),
  KEY `idx_sys_msg_pushed_rc` (`receive_code`),
  KEY `idx_sys_msg_pushed_uc` (`receive_user_code`),
  KEY `idx_sys_msg_pushed_suc` (`send_user_code`),
  KEY `idx_sys_msg_pushed_pd` (`plan_push_date`),
  KEY `idx_sys_msg_pushed_ps` (`push_status`),
  KEY `idx_sys_msg_pushed_rs` (`read_status`),
  KEY `idx_sys_msg_pushed_bk` (`biz_key`),
  KEY `idx_sys_msg_pushed_bt` (`biz_type`),
  KEY `idx_sys_msg_pushed_imp` (`is_merge_push`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息已推送表';

/*Data for the table `js_sys_msg_pushed` */

/*Table structure for table `js_sys_msg_template` */

DROP TABLE IF EXISTS `js_sys_msg_template`;

CREATE TABLE `js_sys_msg_template` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `module_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '归属模块',
  `tpl_key` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模板键值',
  `tpl_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模板名称',
  `tpl_type` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模板类型',
  `tpl_content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模板内容',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `idx_sys_msg_tpl_key` (`tpl_key`),
  KEY `idx_sys_msg_tpl_type` (`tpl_type`),
  KEY `idx_sys_msg_tpl_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息模板';

/*Data for the table `js_sys_msg_template` */

/*Table structure for table `js_sys_office` */

DROP TABLE IF EXISTS `js_sys_office`;

CREATE TABLE `js_sys_office` (
  `office_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构编码',
  `parent_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '父级编号',
  `parent_codes` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有父级编号',
  `tree_sort` decimal(10,0) NOT NULL COMMENT '本级排序号（升序）',
  `tree_sorts` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有级别排序号',
  `tree_leaf` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否最末级',
  `tree_level` decimal(4,0) NOT NULL COMMENT '层次级别',
  `tree_names` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '全节点名',
  `view_code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构代码',
  `office_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构名称',
  `full_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构全称',
  `office_type` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构类型',
  `leader` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '办公电话',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮政编码',
  `email` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'JeeSite' COMMENT '租户名称',
  `extend_s1` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 1',
  `extend_s2` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 2',
  `extend_s3` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 3',
  `extend_s4` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 4',
  `extend_s5` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 5',
  `extend_s6` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 6',
  `extend_s7` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 7',
  `extend_s8` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 8',
  `extend_i1` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 1',
  `extend_i2` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 2',
  `extend_i3` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 3',
  `extend_i4` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 4',
  `extend_f1` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 1',
  `extend_f2` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 2',
  `extend_f3` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 3',
  `extend_f4` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 4',
  `extend_d1` datetime DEFAULT NULL COMMENT '扩展 Date 1',
  `extend_d2` datetime DEFAULT NULL COMMENT '扩展 Date 2',
  `extend_d3` datetime DEFAULT NULL COMMENT '扩展 Date 3',
  `extend_d4` datetime DEFAULT NULL COMMENT '扩展 Date 4',
  PRIMARY KEY (`office_code`),
  KEY `idx_sys_office_cc` (`corp_code`),
  KEY `idx_sys_office_pc` (`parent_code`),
  KEY `idx_sys_office_status` (`status`),
  KEY `idx_sys_office_ot` (`office_type`),
  KEY `idx_sys_office_vc` (`view_code`),
  KEY `idx_sys_office_ts` (`tree_sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='组织机构表';

/*Data for the table `js_sys_office` */

insert  into `js_sys_office`(`office_code`,`parent_code`,`parent_codes`,`tree_sort`,`tree_sorts`,`tree_leaf`,`tree_level`,`tree_names`,`view_code`,`office_name`,`full_name`,`office_type`,`leader`,`phone`,`address`,`zip_code`,`email`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`corp_code`,`corp_name`,`extend_s1`,`extend_s2`,`extend_s3`,`extend_s4`,`extend_s5`,`extend_s6`,`extend_s7`,`extend_s8`,`extend_i1`,`extend_i2`,`extend_i3`,`extend_i4`,`extend_f1`,`extend_f2`,`extend_f3`,`extend_f4`,`extend_d1`,`extend_d2`,`extend_d3`,`extend_d4`) values ('SD','0','0,','40','0000000040,','0','0','山东公司','SD','山东公司','山东公司','1',NULL,NULL,NULL,NULL,NULL,'0','system','2019-03-08 16:53:27','system','2019-03-08 16:53:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDJN','SD','0,SD,','30','0000000040,0000000030,','0','1','山东公司/济南公司','SDJN','济南公司','山东济南公司','2',NULL,NULL,NULL,NULL,NULL,'0','system','2019-03-08 16:53:27','system','2019-03-08 16:53:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDJN01','SDJN','0,SD,SDJN,','30','0000000040,0000000030,0000000030,','1','2','山东公司/济南公司/企管部','SDJN01','企管部','山东济南企管部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-03-08 16:53:27','system','2019-03-08 16:53:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDJN02','SDJN','0,SD,SDJN,','40','0000000040,0000000030,0000000040,','1','2','山东公司/济南公司/财务部','SDJN02','财务部','山东济南财务部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-03-08 16:53:27','system','2019-03-08 16:53:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDJN03','SDJN','0,SD,SDJN,','50','0000000040,0000000030,0000000050,','1','2','山东公司/济南公司/研发部','SDJN03','研发部','山东济南研发部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-03-08 16:53:27','system','2019-03-08 16:53:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDQD','SD','0,SD,','40','0000000040,0000000040,','0','1','山东公司/青岛公司','SDQD','青岛公司','山东青岛公司','2',NULL,NULL,NULL,NULL,NULL,'0','system','2019-03-08 16:53:27','system','2019-03-08 16:53:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDQD01','SDQD','0,SD,SDQD,','30','0000000040,0000000040,0000000030,','1','2','山东公司/青岛公司/企管部','SDQD01','企管部','山东青岛企管部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-03-08 16:53:27','system','2019-03-08 16:53:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDQD02','SDQD','0,SD,SDQD,','40','0000000040,0000000040,0000000040,','1','2','山东公司/青岛公司/财务部','SDQD02','财务部','山东青岛财务部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-03-08 16:53:27','system','2019-03-08 16:53:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SDQD03','SDQD','0,SD,SDQD,','50','0000000040,0000000040,0000000050,','1','2','山东公司/青岛公司/研发部','SDQD03','研发部','山东青岛研发部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-03-08 16:53:27','system','2019-03-08 16:53:27',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `js_sys_post` */

DROP TABLE IF EXISTS `js_sys_post`;

CREATE TABLE `js_sys_post` (
  `post_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
  `post_type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '岗位分类（高管、中层、基层）',
  `post_sort` decimal(10,0) DEFAULT NULL COMMENT '岗位排序（升序）',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'JeeSite' COMMENT '租户名称',
  PRIMARY KEY (`post_code`),
  KEY `idx_sys_post_cc` (`corp_code`),
  KEY `idx_sys_post_status` (`status`),
  KEY `idx_sys_post_ps` (`post_sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工岗位表';

/*Data for the table `js_sys_post` */

insert  into `js_sys_post`(`post_code`,`post_name`,`post_type`,`post_sort`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`corp_code`,`corp_name`) values ('ceo','总经理',NULL,'1','0','system','2019-03-08 16:53:30','system','2019-03-08 16:53:30',NULL,'0','JeeSite'),('cfo','财务经理',NULL,'2','0','system','2019-03-08 16:53:30','system','2019-03-08 16:53:30',NULL,'0','JeeSite'),('dept','部门经理',NULL,'2','0','system','2019-03-08 16:53:30','system','2019-03-08 16:53:30',NULL,'0','JeeSite'),('hrm','人力经理',NULL,'2','0','system','2019-03-08 16:53:30','system','2019-03-08 16:53:30',NULL,'0','JeeSite'),('user','普通员工',NULL,'3','0','system','2019-03-08 16:53:30','system','2019-03-08 16:53:30',NULL,'0','JeeSite');

/*Table structure for table `js_sys_role` */

DROP TABLE IF EXISTS `js_sys_role`;

CREATE TABLE `js_sys_role` (
  `role_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `role_type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色分类（高管、中层、基层、其它）',
  `role_sort` decimal(10,0) DEFAULT NULL COMMENT '角色排序（升序）',
  `is_sys` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '系统内置（1是 0否）',
  `user_type` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户类型（employee员工 member会员）',
  `data_scope` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据范围设置（0未设置  1全部数据 2自定义数据）',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'JeeSite' COMMENT '租户名称',
  PRIMARY KEY (`role_code`),
  KEY `idx_sys_role_cc` (`corp_code`),
  KEY `idx_sys_role_is` (`is_sys`),
  KEY `idx_sys_role_status` (`status`),
  KEY `idx_sys_role_rs` (`role_sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

/*Data for the table `js_sys_role` */

insert  into `js_sys_role`(`role_code`,`role_name`,`role_type`,`role_sort`,`is_sys`,`user_type`,`data_scope`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`corp_code`,`corp_name`) values ('corpAdmin','系统管理员',NULL,'200','1','none','0','0','system','2019-03-08 16:52:46','system','2019-03-08 16:52:46','客户方使用的管理员角色，客户方管理员，集团管理员','0','JeeSite'),('default','默认角色',NULL,'100','1','none','0','0','system','2019-03-08 16:52:46','system','2019-03-08 16:52:46','非管理员用户，共有的默认角色，在参数配置里指定','0','JeeSite'),('dept','部门经理',NULL,'40','0','employee','0','0','system','2019-03-08 16:52:46','system','2019-03-08 16:52:46','部门经理','0','JeeSite'),('user','普通员工',NULL,'50','0','employee','0','0','system','2019-03-08 16:52:46','system','2019-03-08 16:52:46','普通员工','0','JeeSite');

/*Table structure for table `js_sys_role_data_scope` */

DROP TABLE IF EXISTS `js_sys_role_data_scope`;

CREATE TABLE `js_sys_role_data_scope` (
  `role_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '控制角色编码',
  `ctrl_type` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '控制类型',
  `ctrl_data` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '控制数据',
  `ctrl_permi` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '控制权限',
  PRIMARY KEY (`role_code`,`ctrl_type`,`ctrl_data`,`ctrl_permi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色数据权限表';

/*Data for the table `js_sys_role_data_scope` */

/*Table structure for table `js_sys_role_menu` */

DROP TABLE IF EXISTS `js_sys_role_menu`;

CREATE TABLE `js_sys_role_menu` (
  `role_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
  `menu_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单编码',
  PRIMARY KEY (`role_code`,`menu_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色与菜单关联表';

/*Data for the table `js_sys_role_menu` */

insert  into `js_sys_role_menu`(`role_code`,`menu_code`) values ('corpAdmin','1103941623899656192'),('corpAdmin','1103941626831474688'),('corpAdmin','1103941628077182976'),('corpAdmin','1103941629532606464'),('corpAdmin','1103941630707011584'),('corpAdmin','1103941632162435072'),('corpAdmin','1103941633521389568'),('corpAdmin','1103941634922287104'),('corpAdmin','1103941636574842880'),('corpAdmin','1103941638084792320'),('corpAdmin','1103941639435358208'),('corpAdmin','1103941640546848768'),('corpAdmin','1103941641930969088'),('corpAdmin','1103941643134734336'),('corpAdmin','1103941644393025536'),('corpAdmin','1103941645894586368'),('corpAdmin','1103941647358398464'),('corpAdmin','1103941648960622592'),('corpAdmin','1103941650747396096'),('corpAdmin','1103941652156682240'),('corpAdmin','1103941653456916480'),('corpAdmin','1103941654727790592'),('corpAdmin','1103941656166436864'),('corpAdmin','1103941657449893888'),('corpAdmin','1103941658628493312'),('corpAdmin','1103941660461404160'),('corpAdmin','1103941661614837760'),('corpAdmin','1103941662944432128'),('corpAdmin','1103941664290803712'),('corpAdmin','1103941665859473408'),('corpAdmin','1103941667205844992'),('corpAdmin','1103941668577382400'),('corpAdmin','1103941669865033728'),('corpAdmin','1103941671232376832'),('corpAdmin','1103941672427753472'),('corpAdmin','1103941673715404800'),('corpAdmin','1103941675103719424'),('corpAdmin','1103941676580114432'),('corpAdmin','1103941677658050560'),('corpAdmin','1103941678933118976'),('corpAdmin','1103941680447262720'),('corpAdmin','1103941682691215360'),('corpAdmin','1103941691859963904'),('corpAdmin','1103941703096504320'),('corpAdmin','1103941717868843008'),('corpAdmin','1103941724403568640'),('corpAdmin','1103941726228090880'),('corpAdmin','1103941728493015040'),('corpAdmin','1103941730091044864'),('corpAdmin','1103941733077389312'),('corpAdmin','1103941735346507776'),('corpAdmin','1103941737766621184'),('corpAdmin','1103941739247210496'),('corpAdmin','1103941740916543488'),('corpAdmin','1103941742472630272'),('corpAdmin','1103941745362505728'),('corpAdmin','1103941749430980608'),('corpAdmin','1103941751196782592'),('corpAdmin','1103941753692393472'),('corpAdmin','1103941756162838528'),('corpAdmin','1103941758373236736'),('corpAdmin','1103941760713658368'),('corpAdmin','1103941763116994560'),('corpAdmin','1103941765839097856'),('corpAdmin','1103941767873335296');

/*Table structure for table `js_sys_user` */

DROP TABLE IF EXISTS `js_sys_user`;

CREATE TABLE `js_sys_user` (
  `user_code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户编码',
  `login_code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录密码',
  `email` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `phone` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '办公电话',
  `sex` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户性别',
  `avatar` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像路径',
  `sign` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '个性签名',
  `wx_openid` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '绑定的微信号',
  `mobile_imei` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '绑定的手机串号',
  `user_type` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户类型',
  `ref_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户类型引用编号',
  `ref_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户类型引用姓名',
  `mgr_type` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '管理员类型（0非管理员 1系统管理员  2二级管理员）',
  `pwd_security_level` decimal(1,0) DEFAULT NULL COMMENT '密码安全级别（0初始 1很弱 2弱 3安全 4很安全）',
  `pwd_update_date` datetime DEFAULT NULL COMMENT '密码最后更新时间',
  `pwd_update_record` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码修改记录',
  `pwd_question` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题',
  `pwd_question_answer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题答案',
  `pwd_question_2` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题2',
  `pwd_question_answer_2` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题答案2',
  `pwd_question_3` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题3',
  `pwd_question_answer_3` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题答案3',
  `pwd_quest_update_date` datetime DEFAULT NULL COMMENT '密码问题修改时间',
  `last_login_ip` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后登陆IP',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `freeze_date` datetime DEFAULT NULL COMMENT '冻结时间',
  `freeze_cause` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '冻结原因',
  `user_weight` decimal(8,0) DEFAULT '0' COMMENT '用户权重（降序）',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态（0正常 1删除 2停用 3冻结）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'JeeSite' COMMENT '租户名称',
  `extend_s1` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 1',
  `extend_s2` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 2',
  `extend_s3` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 3',
  `extend_s4` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 4',
  `extend_s5` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 5',
  `extend_s6` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 6',
  `extend_s7` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 7',
  `extend_s8` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展 String 8',
  `extend_i1` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 1',
  `extend_i2` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 2',
  `extend_i3` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 3',
  `extend_i4` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 4',
  `extend_f1` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 1',
  `extend_f2` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 2',
  `extend_f3` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 3',
  `extend_f4` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 4',
  `extend_d1` datetime DEFAULT NULL COMMENT '扩展 Date 1',
  `extend_d2` datetime DEFAULT NULL COMMENT '扩展 Date 2',
  `extend_d3` datetime DEFAULT NULL COMMENT '扩展 Date 3',
  `extend_d4` datetime DEFAULT NULL COMMENT '扩展 Date 4',
  PRIMARY KEY (`user_code`),
  KEY `idx_sys_user_lc` (`login_code`),
  KEY `idx_sys_user_email` (`email`),
  KEY `idx_sys_user_mobile` (`mobile`),
  KEY `idx_sys_user_wo` (`wx_openid`),
  KEY `idx_sys_user_imei` (`mobile_imei`),
  KEY `idx_sys_user_rt` (`user_type`),
  KEY `idx_sys_user_rc` (`ref_code`),
  KEY `idx_sys_user_mt` (`mgr_type`),
  KEY `idx_sys_user_us` (`user_weight`),
  KEY `idx_sys_user_ud` (`update_date`),
  KEY `idx_sys_user_status` (`status`),
  KEY `idx_sys_user_cc` (`corp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

/*Data for the table `js_sys_user` */

insert  into `js_sys_user`(`user_code`,`login_code`,`user_name`,`password`,`email`,`mobile`,`phone`,`sex`,`avatar`,`sign`,`wx_openid`,`mobile_imei`,`user_type`,`ref_code`,`ref_name`,`mgr_type`,`pwd_security_level`,`pwd_update_date`,`pwd_update_record`,`pwd_question`,`pwd_question_answer`,`pwd_question_2`,`pwd_question_answer_2`,`pwd_question_3`,`pwd_question_answer_3`,`pwd_quest_update_date`,`last_login_ip`,`last_login_date`,`freeze_date`,`freeze_cause`,`user_weight`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`corp_code`,`corp_name`,`extend_s1`,`extend_s2`,`extend_s3`,`extend_s4`,`extend_s5`,`extend_s6`,`extend_s7`,`extend_s8`,`extend_i1`,`extend_i2`,`extend_i3`,`extend_i4`,`extend_f1`,`extend_f2`,`extend_f3`,`extend_f4`,`extend_d1`,`extend_d2`,`extend_d3`,`extend_d4`) values ('admin','admin','系统管理员','ced72d11985a5993aeda42bdb20a69a26a2e98dbeaf2d50cd2cf0b6c',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'none',NULL,NULL,'1','1','2019-03-08 16:53:25',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:25','system','2019-03-08 16:53:25','客户方使用的系统管理员，用于一些常用的基础数据配置。','0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('system','system','超级管理员','31737964816e6cd0fba64dae36022b4d8de20510b298967df2e64e3a',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'none',NULL,NULL,'0','1','2019-03-08 16:53:24',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'127.0.0.1','2019-03-08 20:35:02',NULL,NULL,'0','0','system','2019-03-08 16:53:24','system','2019-03-08 16:53:25','开发者使用的最高级别管理员，主要用于开发和调试。','0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user1_4sjg','user1','用户01','9499e320dc1d4d03c27ec6101528c1decb8a4745673425d7601d74d2','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user1_4sjg','用户01','0','0','2019-03-08 16:53:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:31','system','2019-03-08 16:53:31',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user10_ur0u','user10','用户10','ab09fdbf94fdca2719b625cf8546c4327b0c69d5436980d03b66cc4e','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user10_ur0u','用户10','0','0','2019-03-08 16:53:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user11_76jk','user11','用户11','c1d7542c58029eb5f58ce4fe96baa853a2ef713f3e7651f70cc89e86','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user11_76jk','用户11','0','0','2019-03-08 16:53:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user12_rukk','user12','用户12','8ffbb524f4737cfba06871d35d90e1505f817d5c09577df59d76fa62','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user12_rukk','用户12','0','0','2019-03-08 16:53:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user13_b9ub','user13','用户13','7414889407a75d0081f386d372f359fc3fc337a75257e5fb0ee258b6','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user13_b9ub','用户13','0','0','2019-03-08 16:53:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:33','system','2019-03-08 16:53:33',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user14_knnm','user14','用户14','be49607d857764423dffd4a69d047610a2370190608a09099ad713a8','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user14_knnm','用户14','0','0','2019-03-08 16:53:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:33','system','2019-03-08 16:53:33',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user15_phwm','user15','用户15','cfe9cc02ced19272602441dfa62f6064da19492a965d0b58ebb7ec50','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user15_phwm','用户15','0','0','2019-03-08 16:53:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:33','system','2019-03-08 16:53:33',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user16_wff7','user16','用户16','c4eb3084290fe71b325ce953bb3a96ddc9634a1ac2e96b5d7c6582ff','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user16_wff7','用户16','0','0','2019-03-08 16:53:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:33','system','2019-03-08 16:53:33',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user17_sb7d','user17','用户17','19fba574a550bb65335d7dd97ade404e84269b2c63edea0d587a9cf7','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user17_sb7d','用户17','0','0','2019-03-08 16:53:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:33','system','2019-03-08 16:53:34',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user18_vjwb','user18','用户18','836e1e78de3b37627118e02d6112d7e673f7892d0dd4594d31e247d7','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user18_vjwb','用户18','0','0','2019-03-08 16:53:34',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:34','system','2019-03-08 16:53:34',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user19_oy3w','user19','用户19','46ec96edc561867d8cdbad15f88068b7ebab45a0ffc4f5d14fb7c419','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user19_oy3w','用户19','0','0','2019-03-08 16:53:34',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:34','system','2019-03-08 16:53:34',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user2_atnd','user2','用户02','690750e8e0b3da9cc9eb3f640a7f0e0708cf66cc0e47161722957f3c','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user2_atnd','用户02','0','0','2019-03-08 16:53:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:31','system','2019-03-08 16:53:31',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user20_cnj4','user20','用户20','3f4dc5645e60453dca356930fb15d617cead0578d00bd8cdf5a6ac9d','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user20_cnj4','用户20','0','0','2019-03-08 16:53:34',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:34','system','2019-03-08 16:53:34',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user21_ggns','user21','用户21','b00e2b8207aa7d9ce002288c244e96385ba4cf7513ae04f9e73c75c1','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user21_ggns','用户21','0','0','2019-03-08 16:53:35',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:35','system','2019-03-08 16:53:35',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user22_umxl','user22','用户22','18ca0accd9788d21bf4c8930ae0c1fb712518e402676848c50090bbe','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user22_umxl','用户22','0','0','2019-03-08 16:53:35',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:35','system','2019-03-08 16:53:35',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user23_e5h9','user23','用户23','4ef5991d21dbf8444329c8252d7f4f10c7488f1cfb9a38359eb2ea67','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user23_e5h9','用户23','0','0','2019-03-08 16:53:35',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:35','system','2019-03-08 16:53:35',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user3_ueg6','user3','用户03','5445de7afc99a26b4895d4db38badcfe4fad33bfd60b935717a61a64','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user3_ueg6','用户03','0','0','2019-03-08 16:53:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:31','system','2019-03-08 16:53:31',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user4_wuva','user4','用户04','d50e88f42b319b3aafa9965df61e6c95af10ac2e0faf548f4ec0bbce','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user4_wuva','用户04','0','0','2019-03-08 16:53:31',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:31','system','2019-03-08 16:53:31',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user5_ksvv','user5','用户05','10fd5cce2fca4865434a318d0676d0a481c854b4d1bbc2ea658f1924','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user5_ksvv','用户05','0','0','2019-03-08 16:53:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user6_hwof','user6','用户06','48db993398dc18c0daca4b17446841d05f5c00ed29639a943f67268c','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user6_hwof','用户06','0','0','2019-03-08 16:53:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user7_m0xh','user7','用户07','1701b2dec699a75633e4f4603077298259e57903fbbfea70e036fe40','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user7_m0xh','用户07','0','0','2019-03-08 16:53:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user8_ahbj','user8','用户08','7e851e0aa62e0eac5a15537d62c0cf31a3dd8dcef4b5f1598f01ba8a','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user8_ahbj','用户08','0','0','2019-03-08 16:53:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('user9_jqmb','user9','用户09','ee6a310d317e503fd999089972a497f58f7017ac84f0a7a7efbf4557','user@test.com','18555555555','053188888888',NULL,NULL,NULL,NULL,NULL,'employee','user9_jqmb','用户09','0','0','2019-03-08 16:53:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','0','system','2019-03-08 16:53:32','system','2019-03-08 16:53:32',NULL,'0','JeeSite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `js_sys_user_data_scope` */

DROP TABLE IF EXISTS `js_sys_user_data_scope`;

CREATE TABLE `js_sys_user_data_scope` (
  `user_code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '控制用户编码',
  `ctrl_type` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '控制类型',
  `ctrl_data` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '控制数据',
  `ctrl_permi` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '控制权限',
  PRIMARY KEY (`user_code`,`ctrl_type`,`ctrl_data`,`ctrl_permi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户数据权限表';

/*Data for the table `js_sys_user_data_scope` */

/*Table structure for table `js_sys_user_role` */

DROP TABLE IF EXISTS `js_sys_user_role`;

CREATE TABLE `js_sys_user_role` (
  `user_code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户编码',
  `role_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
  PRIMARY KEY (`user_code`,`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户与角色关联表';

/*Data for the table `js_sys_user_role` */

insert  into `js_sys_user_role`(`user_code`,`role_code`) values ('user1_4sjg','dept'),('user10_ur0u','user'),('user11_76jk','user'),('user12_rukk','user'),('user13_b9ub','user'),('user14_knnm','dept'),('user15_phwm','dept'),('user16_wff7','user'),('user17_sb7d','user'),('user18_vjwb','dept'),('user19_oy3w','user'),('user2_atnd','user'),('user20_cnj4','user'),('user21_ggns','dept'),('user22_umxl','user'),('user23_e5h9','user'),('user3_ueg6','user'),('user4_wuva','dept'),('user5_ksvv','user'),('user6_hwof','user'),('user7_m0xh','dept'),('user8_ahbj','user'),('user9_jqmb','user');

/*Table structure for table `test_data` */

DROP TABLE IF EXISTS `test_data`;

CREATE TABLE `test_data` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `test_input` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单行文本',
  `test_textarea` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '多行文本',
  `test_select` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下拉框',
  `test_select_multiple` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下拉多选',
  `test_radio` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单选框',
  `test_checkbox` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '复选框',
  `test_date` datetime DEFAULT NULL COMMENT '日期选择',
  `test_datetime` datetime DEFAULT NULL COMMENT '日期时间',
  `test_user_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户选择',
  `test_office_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构选择',
  `test_area_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区域选择',
  `test_area_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区域名称',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试数据';

/*Data for the table `test_data` */

/*Table structure for table `test_data_child` */

DROP TABLE IF EXISTS `test_data_child`;

CREATE TABLE `test_data_child` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编号',
  `test_sort` int(11) DEFAULT NULL COMMENT '排序号',
  `test_data_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '父表主键',
  `test_input` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单行文本',
  `test_textarea` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '多行文本',
  `test_select` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下拉框',
  `test_select_multiple` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下拉多选',
  `test_radio` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单选框',
  `test_checkbox` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '复选框',
  `test_date` datetime DEFAULT NULL COMMENT '日期选择',
  `test_datetime` datetime DEFAULT NULL COMMENT '日期时间',
  `test_user_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户选择',
  `test_office_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构选择',
  `test_area_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区域选择',
  `test_area_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区域名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试数据子表';

/*Data for the table `test_data_child` */

/*Table structure for table `test_tree` */

DROP TABLE IF EXISTS `test_tree`;

CREATE TABLE `test_tree` (
  `tree_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '节点编码',
  `parent_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '父级编号',
  `parent_codes` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有父级编号',
  `tree_sort` decimal(10,0) NOT NULL COMMENT '本级排序号（升序）',
  `tree_sorts` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所有级别排序号',
  `tree_leaf` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否最末级',
  `tree_level` decimal(4,0) NOT NULL COMMENT '层次级别',
  `tree_names` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '全节点名',
  `tree_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '节点名称',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`tree_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试树表';

/*Data for the table `test_tree` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
