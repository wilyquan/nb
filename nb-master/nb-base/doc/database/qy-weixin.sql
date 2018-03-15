SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Indexes */

DROP INDEX sys_user_office_id ON qy_suite;
DROP INDEX sys_user_login_name ON qy_suite;
DROP INDEX sys_user_company_id ON qy_suite;
DROP INDEX sys_user_update_date ON qy_suite;
DROP INDEX sys_user_del_flag ON qy_suite;
DROP INDEX sys_user_office_id ON qy_suite_auth;
DROP INDEX sys_user_login_name ON qy_suite_auth;
DROP INDEX sys_user_company_id ON qy_suite_auth;
DROP INDEX sys_user_update_date ON qy_suite_auth;
DROP INDEX sys_user_del_flag ON qy_suite_auth;
DROP INDEX sys_user_office_id ON qy_suite_corp;
DROP INDEX sys_user_login_name ON qy_suite_corp;
DROP INDEX sys_user_company_id ON qy_suite_corp;
DROP INDEX sys_user_update_date ON qy_suite_corp;
DROP INDEX sys_user_del_flag ON qy_suite_corp;
DROP INDEX sys_user_office_id ON qy_suite_order;
DROP INDEX sys_user_login_name ON qy_suite_order;
DROP INDEX sys_user_company_id ON qy_suite_order;
DROP INDEX sys_user_update_date ON qy_suite_order;
DROP INDEX sys_user_del_flag ON qy_suite_order;



/* Drop Tables */

DROP TABLE IF EXISTS qy_suite;
DROP TABLE IF EXISTS qy_suite_auth;
DROP TABLE IF EXISTS qy_suite_corp;
DROP TABLE IF EXISTS qy_suite_order;




/* Create Tables */

-- 企业应用套件信息
CREATE TABLE qy_suite
(
	id varchar(64) NOT NULL COMMENT '编号',
	name varchar(100) COMMENT '套件名称',
	suite_id varchar(100) NOT NULL COMMENT 'suite_id',
	secret varchar(500) COMMENT '应用secret',
	corp_id varchar(255) COMMENT '授权方的corpid',
	ticket varchar(1000) COMMENT 'ticket,每隔10分钟更新一次',
	suite_access_token varchar(1000) COMMENT '第三方应用access_token,最长为512字节',
	suite_access_expires int(20) COMMENT 'access_token到期时间',
	pre_auth_code varchar(1000) COMMENT '预授权码',
	pre_auth_code_expires int(20) COMMENT '预授权码到期时间',
	auth_code varchar(1000) COMMENT '永久授权码',
	access_token varchar(1000) COMMENT '企业access_token',
	access_expires int(20) COMMENT '企业access_token到期时间',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '企业应用套件信息';


-- 企业应用套件信息
CREATE TABLE qy_suite_auth
(
	id varchar(64) NOT NULL COMMENT '编号',
	suite_id varchar(100) NOT NULL COMMENT 'suite_id',
	corp_id varchar(255) NOT NULL COMMENT '授权方的corpid',
	status char(1) DEFAULT '0' NOT NULL COMMENT '状态（0、授权 1、取消授权）',
	auth_code varchar(1000) COMMENT '授权的auth_code,最长为512字节。用于获取企业的永久授权码',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '企业应用套件信息';


-- 企业应用套件信息
CREATE TABLE qy_suite_corp
(
	id varchar(64) NOT NULL COMMENT '编号',
	suite_id varchar(100) NOT NULL COMMENT 'suite_id',
	corp_id varchar(255) COMMENT '授权方的corpid',
	access_token varchar(1000) COMMENT '企业access_token',
	access_expires int(20) COMMENT '企业access_token到期时间',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	permanent_code varchar(1000) COMMENT '永久授权码',
	corp_name varchar(64) COMMENT '授权方企业微信名称',
	corp_type varchar(64) COMMENT '授权方企业微信类型，认证号：verified, 注册号：unverified',
	corp_square_logo_url varchar(1000) COMMENT '授权方企业微信方形头像',
	corp_user_max int COMMENT '授权方企业微信用户规模',
	corp_full_name varchar(100) COMMENT '所绑定的企业微信主体名称',
	subject_type char(1) COMMENT '企业类型，1. 企业; 2. 政府以及事业单位; 3. 其他组织, 4.团队号',
	verified_end_time datetime COMMENT '认证到期时间',
	corp_wxqrcode varchar(2000) COMMENT '授权方企业微信二维码',
	agentid int COMMENT '授权方应用id',
	agent_name varchar(100) COMMENT '授权方应用名字',
	agent_square_logo_url varchar(1000) COMMENT '授权方应用方形头像',
	agent_round_logo_url varchar(1000) COMMENT '授权方应用圆形头像',
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
CREATE INDEX sys_user_office_id ON qy_suite_auth ();
CREATE INDEX sys_user_login_name ON qy_suite_auth ();
CREATE INDEX sys_user_company_id ON qy_suite_auth ();
CREATE INDEX sys_user_update_date ON qy_suite_auth ();
CREATE INDEX sys_user_del_flag ON qy_suite_auth ();
CREATE INDEX sys_user_office_id ON qy_suite_corp ();
CREATE INDEX sys_user_login_name ON qy_suite_corp ();
CREATE INDEX sys_user_company_id ON qy_suite_corp ();
CREATE INDEX sys_user_update_date ON qy_suite_corp ();
CREATE INDEX sys_user_del_flag ON qy_suite_corp ();
CREATE INDEX sys_user_office_id ON qy_suite_order ();
CREATE INDEX sys_user_login_name ON qy_suite_order ();
CREATE INDEX sys_user_company_id ON qy_suite_order ();
CREATE INDEX sys_user_update_date ON qy_suite_order ();
CREATE INDEX sys_user_del_flag ON qy_suite_order ();



