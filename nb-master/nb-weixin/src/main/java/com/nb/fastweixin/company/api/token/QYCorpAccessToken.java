/**
 * 
 */
package com.nb.fastweixin.company.api.token;

import java.util.Map;

import com.nb.fastweixin.company.api.QYThirdAPI;

/**
 * 
 * 第三方应用 获得企业微信权限的access_token
 * 
 * 
 * @author jewelvary
 *
 */
public class QYCorpAccessToken extends Token {

	private String suiteId;
	private String suiteAccesToken;
	private String authCorpid;
	private String permanentCode;

	/**
	 * 
	 * @param suiteId
	 * @param suiteSecret
	 * @param suiteTicket
	 * @param token
	 * @param expiresIn
	 * @param tokenTime
	 */
	public QYCorpAccessToken(String suiteId, String suiteAccesToken, String authCorpid, String permanentCode, String token, long expiresIn,
			long tokenTime) {
		super(token, expiresIn, tokenTime);

		this.suiteId = suiteId;
		this.suiteAccesToken = suiteAccesToken;
		this.authCorpid = authCorpid;
		this.permanentCode = permanentCode;
	}

	/**
	 * @param token
	 * @param expiresIn
	 * @param tokenTime
	 */
	public QYCorpAccessToken(String token, long expiresIn, long tokenTime) {
		super(token, expiresIn, tokenTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getAccessToken() {

		if (super.getAccessToken() == null || this.isExpire()) {
			QYThirdAPI api = new QYThirdAPI();
			Map r = api.getCorpAccessToken(suiteAccesToken, authCorpid, permanentCode);
			String suite_access_token = (String) r.get("access_token");
			if (suite_access_token != null) {
				this.setAccessToken(suite_access_token);
				long expires_in = ((Number) r.get("expires_in")).longValue();

				this.setExpiresIn(expires_in);
			}
		}
		return super.getAccessToken();
	}

	/**
	 * @param token
	 * @param expiresIn
	 */
	public QYCorpAccessToken(String token, long expiresIn) {
		super(token, expiresIn);
		// TODO Auto-generated constructor stub
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getSuiteAccesToken() {
		return suiteAccesToken;
	}

	public void setSuiteAccesToken(String suiteAccesToken) {
		this.suiteAccesToken = suiteAccesToken;
	}

	public String getAuthCorpid() {
		return authCorpid;
	}

	public void setAuthCorpid(String authCorpid) {
		this.authCorpid = authCorpid;
	}

	public String getPermanentCode() {
		return permanentCode;
	}

	public void setPermanentCode(String permanentCode) {
		this.permanentCode = permanentCode;
	}

	public String getKey() {
		return suiteId+"_"+authCorpid;
	}
	
	public static String getAccessKey(String suiteId, String corpId) {
		return suiteId+"_"+corpId;
	}
	

}
