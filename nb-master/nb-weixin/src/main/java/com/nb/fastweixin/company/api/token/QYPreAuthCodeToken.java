/**
 * 
 */
package com.nb.fastweixin.company.api.token;

import java.util.Map;

import com.nb.fastweixin.company.api.QYThirdAPI;

/**
 * 
 * 预授权码
 * 
 * 该API用于获取预授权码。预授权码用于企业授权时的第三方服务商安全验证。
 * 
 * @author jewelvary
 *
 */
public class QYPreAuthCodeToken extends Token {

	private String suiteAccesToken;
	
	public QYPreAuthCodeToken(String suiteAccesToken, String token, long expiresIn, long tokenTime) {
		super(token, expiresIn, tokenTime);
		
		this.suiteAccesToken = suiteAccesToken;
	}
	
	/**
	 * @param token
	 * @param expiresIn
	 * @param tokenTime
	 */
	public QYPreAuthCodeToken(String token, long expiresIn, long tokenTime) {
		super(token, expiresIn, tokenTime);
	}

	/**
	 * @param token
	 * @param expiresIn
	 */
	public QYPreAuthCodeToken(String token, long expiresIn) {
		super(token, expiresIn);
	}
	
	@Override
	public String getAccessToken() {

		if (super.getAccessToken() == null || this.isExpire()) {
			QYThirdAPI api = new QYThirdAPI();
			Map r = api.getPreAuthCode(suiteAccesToken);
			String pre_auth_code = (String) r.get("pre_auth_code");
			if (pre_auth_code != null) {
				super.setAccessToken(pre_auth_code);
				long expires_in = ((Number) r.get("expires_in")).longValue();

				this.setExpiresIn(expires_in);
			}
		}
		return super.getAccessToken();
	}

}
