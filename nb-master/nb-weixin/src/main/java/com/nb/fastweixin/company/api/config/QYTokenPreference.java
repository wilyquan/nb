/**
 * 
 */
package com.nb.fastweixin.company.api.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nb.fastweixin.company.api.QYMenuAPI;
import com.nb.fastweixin.company.api.QYThirdAPI;

/**
 * 
 * 企业微信token全局配置信息
 * 
 * @author willie
 *
 */
public class QYTokenPreference {

	private static final Logger LOG = LoggerFactory.getLogger(QYTokenPreference.class);

	private static QYTokenPreference instance;

	/**
	 * 企业微信：服务商的access_token
	 */
	private Token providerAccessToken;

	private Token jsTicket;

	/**
	 * 
	 */
	public static QYTokenPreference getInstance() {

		synchronized (QYTokenPreference.class) {
			if (instance == null) {
				instance = new QYTokenPreference();
			}
		}
		return instance;
	}

	private QYTokenPreference() {
		this.corpId = QYConfig.corpid;
		this.providerSecret = QYConfig.corpsecret;
	}

	private QYTokenPreference(String corpId, String providerSecret) {
		this.corpId = corpId;
		this.providerSecret = providerSecret;
	}

	private String corpId;
	private String providerSecret;

	/**
	 * 获得微信token
	 * 
	 * @return
	 */
	public String getProviderAccessToken() {
		if (providerAccessToken == null || providerAccessToken.isExpire()) {
			providerAccessToken = QYThirdAPI.getProviderAccessToken(corpId, providerSecret);
		}
		if (providerAccessToken != null) {
			return providerAccessToken.getAccessToken();
		}

		return null;
	}

	// public String getJsTicket() {
	// if (jsTicket == null || jsTicket.isExpire()){
	// jsTicket = APIUtils.getJSTicketToken(getAccessToken());
	// }
	// if (jsTicket != null) {
	// return jsTicket.getAccessToken();
	// }
	// return null;
	// }

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

}
