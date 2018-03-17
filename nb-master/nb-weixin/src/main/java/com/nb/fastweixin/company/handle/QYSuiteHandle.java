/**
 * 
 */
package com.nb.fastweixin.company.handle;

import java.util.Map;

/**
 * 第三方应用套件业务层
 * 需要实现的方法
 * 
 * @author jewelvary
 *
 */
public interface QYSuiteHandle {

	/**
	 * 处理企业永久授权码信息
	 * 里面包括：企业永久授权码、企业信息、授权信息、授权用户信息
	 * 
	 * 方便业务层面处理
	 * 
	 *  
	 * @param m
	 */
	public void permanentCode(String suiteId, String authCorpId, String permanentCode, Map m);
	
	/**
	 * 通过获取企业授权信息接口获得
	 * 
	 * 内容包括：企业信息、授权信息 等
	 * 
	 * 
	 * @param m
	 */
	public void authCorpInfo(Map m);
	
	
	/**
	 * 处理回调协议指令
	 * 
	 * @param m
	 */
	public void receiveOrder(Map m);
	
	/**
	 * 取消授权，相当于卸载第三方应用
	 * 
	 * @param m
	 */
	public void cancelAuth(Map m);
	
	
}
