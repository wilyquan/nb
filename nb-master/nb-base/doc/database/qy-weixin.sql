SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Indexes */

DROP INDEX sys_user_office_id ON qy_suite;
DROP INDEX sys_user_login_name ON qy_suite;
DROP INDEX sys_user_company_id ON qy_suite;
DROP INDEX sys_user_update_date ON qy_suite;
DROP INDEX sys_user_del_flag ON qy_suite;
DROP INDEX sys_user_office_id ON qy_suite_order;
DROP INDEX sys_user_login_name ON qy_suite_order;
DROP INDEX sys_user_company_id ON qy_suite_order;
DROP INDEX sys_user_update_date ON qy_suite_order;
DROP INDEX sys_user_del_flag ON qy_suite_order;



/* Drop Tables */

DROP TABLE IF EXISTS qy_suite;
DROP TABLE IF EXISTS qy_suite_order;




/* Create Tables */

-- 企业应用套件信息
CREATE TABLE qy_suite
(
	id varchar(64) NOT NULL COMMENT '编号',
	name varchar(100) COMMENT '套件名称',
	suite_id varchar(100) NOT NULL COMMENT 'suite_id',
	secret varchar(255) COMMENT 'secret',
	ticket varchar(1000) COMMENT 'ticket,每隔10分钟更新一次',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '企业应用套件信息';


-- 企业应用套件指令日志表
CREATE TABLE qy_suite_order
(
	id varchar(64) NOT NULL COMMENT '编号',
	suite_id varchar(100) COMMENT 'suite_id',
	info_type varchar(64) COMMENT '信息类型',
	timestamp datetime COMMENT '时间戳',
	corp_id varchar(100) COMMENT '授权企业的CorpID',
	content text COMMENT '请求内容',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '企业应用套件指令日志表';



/* Create Triggers */

CREATE TRIGGER TRI_sys_log_id BEFORE INSERT ON sys_log
FOR EACH ROW
BEGIN
	SELECT SEQ_sys_log_id.nextval
	INTO :new.id
	FROM dual;
END;



/* Create Indexes */

CREATE INDEX sys_user_office_id ON qy_suite ();
CREATE INDEX sys_user_login_name ON qy_suite ();
CREATE INDEX sys_user_company_id ON qy_suite ();
CREATE INDEX sys_user_update_date ON qy_suite ();
CREATE INDEX sys_user_del_flag ON qy_suite ();
CREATE INDEX sys_user_office_id ON qy_suite_order ();
CREATE INDEX sys_user_login_name ON qy_suite_order ();
CREATE INDEX sys_user_company_id ON qy_suite_order ();
CREATE INDEX sys_user_update_date ON qy_suite_order ();
CREATE INDEX sys_user_del_flag ON qy_suite_order ();



