SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Indexes */

DROP INDEX sys_user_office_id ON qy_agent;
DROP INDEX sys_user_login_name ON qy_agent;
DROP INDEX sys_user_company_id ON qy_agent;
DROP INDEX sys_user_update_date ON qy_agent;
DROP INDEX sys_user_del_flag ON qy_agent;
DROP INDEX sys_user_office_id ON qy_agent_user;
DROP INDEX sys_user_login_name ON qy_agent_user;
DROP INDEX sys_user_company_id ON qy_agent_user;
DROP INDEX sys_user_update_date ON qy_agent_user;
DROP INDEX sys_user_del_flag ON qy_agent_user;
DROP INDEX sys_user_office_id ON qy_company;
DROP INDEX sys_user_login_name ON qy_company;
DROP INDEX sys_user_company_id ON qy_company;
DROP INDEX sys_user_update_date ON qy_company;
DROP INDEX sys_user_del_flag ON qy_company;
DROP INDEX sys_user_office_id ON qy_corp;
DROP INDEX sys_user_login_name ON qy_corp;
DROP INDEX sys_user_company_id ON qy_corp;
DROP INDEX sys_user_update_date ON qy_corp;
DROP INDEX sys_user_del_flag ON qy_corp;
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
DROP INDEX sys_user_office_id ON qy_suite_order;
DROP INDEX sys_user_login_name ON qy_suite_order;
DROP INDEX sys_user_company_id ON qy_suite_order;
DROP INDEX sys_user_update_date ON qy_suite_order;
DROP INDEX sys_user_del_flag ON qy_suite_order;



/* Drop Tables */

DROP TABLE IF EXISTS qy_agent;
DROP TABLE IF EXISTS qy_agent_user;
DROP TABLE IF EXISTS qy_company;
DROP TABLE IF EXISTS qy_corp;
DROP TABLE IF EXISTS qy_suite;
DROP TABLE IF EXISTS qy_suite_auth;
DROP TABLE IF EXISTS qy_suite_order;




/* Create Tables */

-- 企业应用套件信息
CREATE TABLE qy_agent
(
	id varchar(64) NOT NULL COMMENT '编号',
	suite_id varchar(100) COMMENT '套件id',
	corp_id varchar(100) COMMENT '授权企业id',
	agent_id varchar(100) COMMENT 'agent id',
	name varchar(100) COMMENT 'agent名称',
	square_logo_url varchar(1000) COMMENT '授权方企业微信方形头像',
	round_logo_url varchar(1000) COMMENT '授权方应用圆形头像',
	level char COMMENT '权限等级。
1:通讯录基本信息只读
2:通讯录全部信息只读
3:通讯录全部信息读写
4:单个基本信息只读
5:通讯录全部信息只写',
	allow_party varchar(2000) COMMENT '应用可见范围（部门）',
	allow_tag varchar(2000) COMMENT '可见标签',
	allow_user varchar(2000) COMMENT '应用可见范围（成员）',
	extra_party varchar(2000) COMMENT '额外通讯录（部门）',
	extra_user varchar(2000) COMMENT '额外通讯录（成员）',
	extra_tag varchar(2000) COMMENT '额外通讯录（标签）',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '企业应用套件信息';


-- 企业应用套件信息
CREATE TABLE qy_agent_user
(
	id varchar(64) NOT NULL COMMENT '编号',
	corp_id varchar(100) COMMENT '授权企业id',
	agent_id varchar(100) COMMENT 'agent id',
	userid varchar(64) COMMENT 'userid',
	name varchar(64) COMMENT '成员名称',
	english_name varchar(64) COMMENT '英文名',
	mobile varchar(64) COMMENT '手机号码',
	department varchar(2000) COMMENT '成员所属部门id列表,不超过20个',
	sort varchar(2000) COMMENT '部门内的排序值',
	position varchar(64) COMMENT '职位信息',
	gender varchar(1) DEFAULT '1' COMMENT '性别 1表示男性，2表示女性',
	email varchar(64) COMMENT '邮箱。长度不超过64个字节，且为有效的email格式。企业内必须唯一，mobile/email二者不能同时为空',
	telephone varchar(64) COMMENT '座机。由1-32位的纯数字或’-‘号组成',
	isleader char(1) COMMENT '上级字段，标识是否为上级。在审批等应用里可以用来标识上级审批人',
	avatar_mediaid varchar(255) COMMENT '成员头像的mediaid，通过素材管理接口上传图片获得的mediaid',
	enable char(1) COMMENT '启用/禁用成员。1表示启用成员，0表示禁用成员',
	extattr varchar(64) COMMENT '自定义字段。自定义字段需要先在WEB管理端添加，见扩展属性添加方法，否则忽略未知属性的赋值。自定义字段长度为0~32个字符，超过将被截断',
	to_invite boolean COMMENT '是否邀请该成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。',
	status char(1) COMMENT '激活状态: 1=已激活，2=已禁用，4=未激活。
已激活代表已激活企业微信或已关注微信插件。未激活代表既未激活企业微信又未关注微信插件。',
	openid varchar(100) COMMENT '企业微信成员userid对应的openid',
	appid varchar(100) COMMENT '应用的appid',
	avatar varchar(1000) COMMENT '授权管理员的头像url',
	is_admin char DEFAULT '0' COMMENT '是否是管理员 0、否 1、是',
	suite_id varchar(100) COMMENT '套件应用id',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '企业应用套件信息';


-- 企业应用套件信息
CREATE TABLE qy_company
(
	id varchar(64) NOT NULL COMMENT '编号',
	name varchar(100) COMMENT '企业名称',
	corp_id varchar(100) NOT NULL COMMENT 'corp_id',
	provider_secret varchar(500) COMMENT 'provider_secret',
	event_url varchar(1000) COMMENT '系统事件接收URL',
	token varchar(512) COMMENT 'token',
	aes_key varchar(512) COMMENT 'aes_key',
	describle text COMMENT '企业描述',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '企业应用套件信息';


-- 企业应用套件信息
CREATE TABLE qy_corp
(
	id varchar(64) NOT NULL COMMENT '编号',
	corp_id varchar(100) COMMENT '企业id',
	name varchar(100) COMMENT '企业名称',
	type varchar(64) COMMENT '授权方企业微信类型，认证号：verified, 注册号：unverified',
	square_logo_url varchar(1000) COMMENT '授权方企业微信方形头像',
	round_logo_url varchar(1000) COMMENT '授权方应用圆形头像',
	user_max int COMMENT '授权方企业微信用户规模',
	agent_max int COMMENT '应用总数',
	full_name varchar(255) COMMENT '所绑定的企业微信主体名称',
	subject_type char(2) COMMENT '企业类型，1. 企业; 2. 政府以及事业单位; 3. 其他组织, 4.团队号',
	verified_end_time int(20) COMMENT '认证到期时间',
	wxqrcode varchar(1000) COMMENT '授权方企业微信二维码',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '企业应用套件信息';


-- 企业应用套件信息
CREATE TABLE qy_suite
(
	id varchar(64) NOT NULL COMMENT '编号',
	corp_id varchar(100) COMMENT '企业id',
	name varchar(100) COMMENT '套件名称',
	suite_id varchar(100) NOT NULL COMMENT 'suite_id',
	suite_secret varchar(500) COMMENT '应用secret',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '企业应用套件信息';


-- 套件授权企业信息
CREATE TABLE qy_suite_auth
(
	id varchar(64) NOT NULL COMMENT '编号',
	suite_id varchar(100) NOT NULL COMMENT 'suite_id',
	authcorp_id varchar(100) NOT NULL COMMENT '授权方corpid',
	permanent_code varchar(512) NOT NULL COMMENT '永久授权码，通过get_permanent_code获取',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '套件授权企业信息';


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

CREATE INDEX sys_user_office_id ON qy_agent ();
CREATE INDEX sys_user_login_name ON qy_agent ();
CREATE INDEX sys_user_company_id ON qy_agent ();
CREATE INDEX sys_user_update_date ON qy_agent ();
CREATE INDEX sys_user_del_flag ON qy_agent ();
CREATE INDEX sys_user_office_id ON qy_agent_user ();
CREATE INDEX sys_user_login_name ON qy_agent_user ();
CREATE INDEX sys_user_company_id ON qy_agent_user ();
CREATE INDEX sys_user_update_date ON qy_agent_user ();
CREATE INDEX sys_user_del_flag ON qy_agent_user ();
CREATE INDEX sys_user_office_id ON qy_company ();
CREATE INDEX sys_user_login_name ON qy_company ();
CREATE INDEX sys_user_company_id ON qy_company ();
CREATE INDEX sys_user_update_date ON qy_company ();
CREATE INDEX sys_user_del_flag ON qy_company ();
CREATE INDEX sys_user_office_id ON qy_corp ();
CREATE INDEX sys_user_login_name ON qy_corp ();
CREATE INDEX sys_user_company_id ON qy_corp ();
CREATE INDEX sys_user_update_date ON qy_corp ();
CREATE INDEX sys_user_del_flag ON qy_corp ();
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
CREATE INDEX sys_user_office_id ON qy_suite_order ();
CREATE INDEX sys_user_login_name ON qy_suite_order ();
CREATE INDEX sys_user_company_id ON qy_suite_order ();
CREATE INDEX sys_user_update_date ON qy_suite_order ();
CREATE INDEX sys_user_del_flag ON qy_suite_order ();



