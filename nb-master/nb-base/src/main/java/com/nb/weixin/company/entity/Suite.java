/**
 * 
 */
package com.nb.weixin.company.entity;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.nb.entity.DbEntity;
import com.nb.interfaces.Convertible;

/**
 * @author jewelvary
 *
 */
@Entity
@Table(name = "qy_suite")
public class Suite extends DbEntity {

	private String name;
	private String suiteId;
	//suite_secret
	private String secret;
	//suite_ticket,每隔10分钟更新一次
	private String ticket;

	// 第三方应用access_token,最长为512字节
	private String suiteAccessToken;
	// access_token到期时间
	private long suiteAccessExpires;
	
	// 预授权码
	private String preAuthCode;
	// 预授权码到期时间
	private long preAuthCodeExpires;

	// 永久授权码
	private String authCode;
	
	// 企业access_token
	private String accessToken;
	// 企业access_token到期时间
	private long accessExpires; 

	/**
	 * 
	 */
	public Suite() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getSuiteAccessToken() {
		return suiteAccessToken;
	}

	public void setSuiteAccessToken(String suiteAccessToken) {
		this.suiteAccessToken = suiteAccessToken;
	}

	public long getSuiteAccessExpires() {
		return suiteAccessExpires;
	}

	public void setSuiteAccessExpires(long suiteAccessExpires) {
		this.suiteAccessExpires = suiteAccessExpires;
	}

	public String getPreAuthCode() {
		return preAuthCode;
	}

	public void setPreAuthCode(String preAuthCode) {
		this.preAuthCode = preAuthCode;
	}

	public long getPreAuthCodeExpires() {
		return preAuthCodeExpires;
	}

	public void setPreAuthCodeExpires(long preAuthCodeExpires) {
		this.preAuthCodeExpires = preAuthCodeExpires;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getAccessExpires() {
		return accessExpires;
	}

	public void setAccessExpires(long accessExpires) {
		this.accessExpires = accessExpires;
	}
	
	/**
	 * suite_access_token 是否过期
	 */
	public boolean isSuiteAccessExpiresIn() {
		long now = System.currentTimeMillis();
		if (now >= this.suiteAccessExpires) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 预授权码 是否过期
	 * @return
	 */
	public boolean isPreAuthCodeExpiresIn() {
		long now = System.currentTimeMillis();
		if (now >= this.preAuthCodeExpires) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 企业access_token 是否过期
	 * @return
	 */
	public boolean isAccessExpiresIn() {
		long now = System.currentTimeMillis();
		if (now >= this.accessExpires) {
			return true;
		}
		
		return false;
	}
	
	
	

	public Suite convert(Map obj) {
		if (obj != null) {
			Suite suite = new Suite();
			suite.setSuiteId((String) obj.get("SuiteId"));
			suite.setTicket((String) obj.get("SuiteTicket"));

			return suite;
		}

		return null;
	}

}
