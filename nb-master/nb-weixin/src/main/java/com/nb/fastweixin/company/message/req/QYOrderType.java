package com.nb.fastweixin.company.message.req;
/**
 *  微信企业号异步任务类型
 *  ====================================================================
 *  上海聚攒软件开发有限公司
 *  --------------------------------------------------------------------
 *  @author Nottyjay
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public final class QYOrderType {

	public static final String SUITE_TICKET     = "suite_ticket";// 推送suite_ticket
	public static final String CREATE_AUTH  = "create_auth";// 授权成功通知
	public static final String CHANGE_AUTH   = "change_auth";// 变更授权通知
	public static final String CANCEL_AUTH = "cancel_auth";// 取消授权通知
    
    
	public static final String CHANGE_CONTACT = "change_contact";// 通讯录变更事件通知
    
	public static final String EVENT_CREATE_USER = "create_user";// 新增成员事件
	public static final String UPDATE_USER = "update_user";// 更新成员事件
	public static final String DELETE_USER = "delete_user";// 删除成员事件
    
	public static final String CREATE_PARTY = "create_party";// 新增部门事件
	public static final String UPDATE_PARTY = "update_party";// 更新部门事件
	public static final String DELETE_PARTY = "delete_party";// 删除部门事件
	public static final String UPDATE_TAG = "update_tag";// 标签成员变更事件

    private QYOrderType() {
    }
}
