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
	private String authcorpId;
	private String permanentCode;
	private int status;

	public SuiteAuth() {

	}

	public SuiteAuth(String suiteId, String authcorpId, String permanentCode, int status) {
		this.suiteId = suiteId;
		this.authcorpId = authcorpId;
		this.permanentCode = permanentCode;

		this.status = status;

	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getAuthcorpId() {
		return authcorpId;
	}

	public void setAuthcorpId(String authcorpId) {
		this.authcorpId = authcorpId;
	}

	public String getPermanentCode() {
		return permanentCode;
	}

	public void setPermanentCode(String permanentCode) {
		this.permanentCode = permanentCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
