/**
 * 
 */
package com.nb.weixin.company.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.nb.entity.DbEntity;
import com.nb.utils.JSONUtil;
import com.nb.utils.StringUtils;

/**
 * 套件授权信息
 * 
 * @author jewelvary
 *
 */
@Entity
@Table(name = "qy_suite_auth")
public class SuiteAuth extends DbEntity<SuiteAuth> {

	// 授权成功
	public static final int STATUS_AUTH = 0;
	// 取消授权
	public static final int STATUS_AUTH_CANCEL = 1;

	private String suiteId;
	private String corpId;
	private String authCode;
	private int status;

	public SuiteAuth() {

	}
	
	public SuiteAuth(String suiteId, String authCode, String corpId) {
		this.suiteId = suiteId;
		this.authCode = authCode;
		this.corpId = corpId;
		
		if (StringUtils.isNullOrEmpty(authCode)) {
			this.status = STATUS_AUTH;
		}else {
			this.status = STATUS_AUTH_CANCEL;
		}
		
	}
	
//	public SuiteAuth(String suiteId, String corpId) {
//		this.suiteId = suiteId;
//		this.corpId = corpId;
//		this.status = STATUS_AUTH_CANCEL;
//	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public SuiteAuth convert(Map obj) {
		// if (obj != null) {
		// SuiteAuth suiteAuth = new SuiteAuth();
		// suiteAuth.setSuiteId((String) obj.get("SuiteId"));
		// suiteOrder.setInfoType((String) obj.get("InfoType"));
		//
		// String timestamp = (String) obj.get("TimeStamp");
		// if (timestamp != null) {
		// suiteOrder.setTimestamp(new Date(Long.parseLong(timestamp) * 1000L));
		// }
		//
		// String authCorpId = (String) obj.get("AuthCorpId");
		// if (authCorpId != null) {
		// suiteOrder.setCorpId(authCorpId);
		// }
		//
		// suiteOrder.setContent(JSONUtil.toJson(obj));
		//
		// return suiteOrder;
		// }

		return null;
	}

	public static void main(String[] args) {
		Date date = new Date(1520932497000L);
		System.out.println(date);
		System.out.println(System.currentTimeMillis());
	}

}
