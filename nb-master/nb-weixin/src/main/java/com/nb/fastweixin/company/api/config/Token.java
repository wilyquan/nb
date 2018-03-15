/**
 * 
 */
package com.nb.fastweixin.company.api.config;

import com.nb.fastweixin.util.StrUtil;

/**
 * token信息
 * 
 * @author jewelvary
 *
 */
public class Token {

	/**
	 * 需要保存的token信息
	 */
	private String accessToken;

	/**
	 * token有效时间
	 */
	private long expiresIn;

	/**
	 * token获取的时间戳
	 */
	private long tokenTime;

	/**
	 * 
	 */
	public Token(String token, long expiresIn, long tokenTime) {
		this.accessToken = token;
		this.expiresIn = expiresIn - 50;
		this.tokenTime = tokenTime;
	}
	
	public Token(String token, long expiresIn) {
		this.accessToken = token;
		this.expiresIn = expiresIn - 50;
		this.tokenTime = System.currentTimeMillis() ;
	}

	/**
	 * token是否过期
	 * 
	 * true:过期 false:为过期
	 * 
	 * @return
	 */
	public Boolean isExpire() {
		Boolean expire = true;
		if (!StrUtil.isBlank(accessToken)) {
			long second = (System.currentTimeMillis() - tokenTime) / 1000l;
			if (second < expiresIn) {
				expire = false;
			}

		}
		return expire;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn - 50l;
	}

	public long getTokenTime() {
		return tokenTime;
	}

	public void setTokenTime(long tokenTime) {
		this.tokenTime = tokenTime;
	}

}
