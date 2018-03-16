/**
 * 
 */
package com.nb.fastweixin.company.api.token;

import java.util.Map;

import com.nb.fastweixin.company.api.QYThirdAPI;

/**
 * 
 * 第三方应用凭证（suite_access_token）
 * 
 * @author jewelvary
 *
 */
public class QYSuiteAccessToken extends Token {

	private String suiteId;
	private String suiteSecret;
	private String suiteTicket;

	/**
	 * 
	 * @param suiteId
	 * @param suiteSecret
	 * @param suiteTicket
	 * @param token
	 * @param expiresIn
	 * @param tokenTime
	 */
	public QYSuiteAccessToken(String suiteId, String suiteSecret, String suiteTicket, String token, long expiresIn,
			long tokenTime) {
		super(token, expiresIn, tokenTime);

		this.suiteId = suiteId;
		this.suiteSecret = suiteSecret;
		this.suiteTicket = suiteTicket;
	}

	/**
	 * @param token
	 * @param expiresIn
	 * @param tokenTime
	 */
	public QYSuiteAccessToken(String token, long expiresIn, long tokenTime) {
		super(token, expiresIn, tokenTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getAccessToken() {

		if (super.getAccessToken() == null || this.isExpire()) {
			QYThirdAPI api = new QYThirdAPI();
			Map r = api.getSuiteToken(suiteId, suiteSecret, suiteTicket);
			String suite_access_token = (String) r.get("suite_access_token");
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
	public QYSuiteAccessToken(String token, long expiresIn) {
		super(token, expiresIn);
		// TODO Auto-generated constructor stub
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getSuiteSecret() {
		return suiteSecret;
	}

	public void setSuiteSecret(String suiteSecret) {
		this.suiteSecret = suiteSecret;
	}

	public String getSuiteTicket() {
		return suiteTicket;
	}

	public void setSuiteTicket(String suiteTicket) {
		this.suiteTicket = suiteTicket;
	}

}
