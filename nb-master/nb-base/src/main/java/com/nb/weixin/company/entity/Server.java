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
 * 服务商信息
 * 
 * @author jewelvary
 *
 */
@Entity
@Table(name = "qy_server")
public class Server extends DbEntity {

	private String name;
	private String corpId;
	private String providerSecret;
	private String eventUrl;

	private String token;
	private String aesKey;

	public Server(String name, String corpId, String providerSecret, String eventUrl, String token, String aesKey) {
		super();
		this.name = name;
		this.corpId = corpId;
		this.providerSecret = providerSecret;
		this.eventUrl = eventUrl;
		this.token = token;
		this.aesKey = aesKey;
	}
	/**
	 * 
	 */
	public Server() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getProviderSecret() {
		return providerSecret;
	}

	public void setProviderSecret(String providerSecret) {
		this.providerSecret = providerSecret;
	}

	public String getEventUrl() {
		return eventUrl;
	}

	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

}
