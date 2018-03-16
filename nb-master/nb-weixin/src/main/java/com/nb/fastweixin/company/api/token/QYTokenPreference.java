/**
 * 
 */
package com.nb.fastweixin.company.api.token;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nb.fastweixin.company.api.QYMenuAPI;
import com.nb.fastweixin.company.api.QYThirdAPI;
import com.nb.fastweixin.company.api.config.QYConfig;
import com.nb.fastweixin.company.api.config.QYSuite;

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
	
	/**
	 * 第三方应用凭证（suite_access_token）集合。
	 */
	private Map<String, QYSuiteAccessToken> suiteAccessTokens;
	
	/**
	 * 企业微信后台推送的ticket
	 */
	private Map<String, String> suiteTickets;
	
	/**
	 * 所有套件应用
	 */
	private Map<String, QYSuite> suites;
	
	/**
	 * 所有套件对应的预授权码
	 */
	private Map<String, QYPreAuthCodeToken> preAuthCodes;

	private Token jsTicket;
	
	private String corpId;
	private String providerSecret;

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
		init();
	}

	private QYTokenPreference(String corpId, String providerSecret) {
		this.corpId = corpId;
		this.providerSecret = providerSecret;
		
		init();
	}
	
	private void init() {
		QYSuite suite = new QYSuite("ww202a2aaa0ae43950", "8LxOhefxXXjxihd7JSMdCwR7TCsViEB_Q5qTgkBPsts");
		this.putSuite(suite.getSuiteId(), suite);
	}

	

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



	/**
	 * suiteTicket 系统会自动每隔10分钟更新一次
	 * 
	 * 添加或更新suite ticket
	 * 
	 * @param suiteId
	 * @param suiteTicket
	 */
	public void putSuiteTicket(String suiteId, String suiteTicket) {
		if (suiteTickets == null) {
			suiteTickets = new HashMap<String, String>();
		}
		
		suiteTickets.put(suiteId, suiteTicket);
	}
	
	/**
	 * 检索指定的suite ticket
	 * 
	 * @param suiteId
	 * @return
	 */
	public String getSuiteTicket(String suiteId) {
		if (suiteTickets != null) {
			return suiteTickets.get(suiteId);
		}
		
		return null;
	}
	
	/**
	 * 添加或更新token
	 * @param suiteId
	 * @param token
	 */
	public void putSuiteToken(String suiteId, QYSuiteAccessToken token) {
		if (suiteAccessTokens == null) {
			suiteAccessTokens = new HashMap<String, QYSuiteAccessToken>();
		}
		
		suiteAccessTokens.put(suiteId, token);
	}
	
	/**
	 * 检索出指定的token
	 * @param suiteId
	 * @return
	 */
	public QYSuiteAccessToken getSuiteToken(String suiteId) {
		if (suiteAccessTokens != null) {
			return suiteAccessTokens.get(suiteId);
		}
		
		return null;
	}
	
	/**
	 * 添加套件信息
	 * 
	 * @param suiteId
	 * @param suite
	 */
	public void putSuite(String suiteId, QYSuite suite) {
		if (suites == null) {
			suites = new HashMap<String, QYSuite>();
		}
		
		suites.put(suiteId, suite); 
	}
	
	/**
	 * 获得一个套件信息
	 * 
	 * @param suiteId
	 * @return
	 */
	public QYSuite getSuite(String suiteId) {
		if (suites != null) {
			return suites.get(suiteId);
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @param suiteId
	 * @return
	 */
	public String getSuiteAccessToken(String suiteId) {
		
		String suiteTicket = getSuiteTicket(suiteId);

		if (suiteTicket == null) {
			LOG.error("suiteId = {} suiteTicket is null exception!", suiteId);
			return null;
		}
		
		QYSuite suite = getSuite(suiteId);
		if (suite == null) {
			LOG.error("suiteId = {} suite is null exception!", suiteId);
			return null;
		}
		
		QYSuiteAccessToken suiteToken = getSuiteToken(suiteId);
		if (suiteToken == null) {
			suiteToken = new QYSuiteAccessToken(suiteId, suite.getSuiteSecret(), suiteTicket, null, 0l,0l);
		}
		
		return suiteToken.getAccessToken();
		
//		if (suiteToken == null) {
//			
//			QYSuiteToken token = new QYSuiteToken(suiteId, suite.getSuiteSecret(), suiteTicket, null, 0l,0l);
//			this.putSuiteToken(suiteId, token);
//			
//			QYThirdAPI api = new QYThirdAPI();
//			
//			Map r = api.getSuiteToken(suiteId, suite.getSuiteSecret(), suiteTicket);
//			String suite_access_token = (String) r.get("suite_access_token");
//			
//			if (suite_access_token != null) {
//				long expires_in = ((Number) r.get("expires_in")).longValue(); 
//				QYSuiteToken token = new QYSuiteToken(suiteId, suite.getSuiteSecret(), suiteTicket);
//			}
//		}
//		if (suiteToken != null) {
//			return suiteToken.getAccessToken();
//		}
//		
//		return null;
	}
	
	public void putPreAuthCodeToken(String suiteId, QYPreAuthCodeToken token) {
		if (preAuthCodes == null) {
			preAuthCodes = new HashMap<String, QYPreAuthCodeToken>();
		}
		
		preAuthCodes.put(suiteId, token);
	}
	
	public QYPreAuthCodeToken getPreAuthCodeToken(String suiteId) {
		if (preAuthCodes != null) {
			return preAuthCodes.get(suiteId);
		}
		
		return null;
	}
	
	/**
	 * 获取预授权码
	 * 
	 * @param suiteId
	 * @return
	 */
	public String getPreAuthCode(String suiteId) {
		QYPreAuthCodeToken token = getPreAuthCodeToken(suiteId);
		if (token == null) {
			token = new QYPreAuthCodeToken(this.getSuiteAccessToken(suiteId), null, 0l, 0l);
		}
		
		return token.getAccessToken();
	}

}
