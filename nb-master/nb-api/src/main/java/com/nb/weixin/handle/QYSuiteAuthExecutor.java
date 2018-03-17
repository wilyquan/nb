/**
 * 
 */
package com.nb.weixin.handle;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nb.fastweixin.api.enums.QYAuthType;
import com.nb.fastweixin.company.api.QYThirdAPI;
import com.nb.fastweixin.company.api.token.QYTokenPreference;
import com.nb.fastweixin.util.JSONUtil;
import com.nb.utils.SpringContextHolder;
import com.nb.utils.StringUtils;
import com.nb.weixin.company.entity.Suite;
import com.nb.weixin.company.service.SuiteAuthService;
import com.nb.weixin.company.service.SuiteService;

/**
 * @author jewelvary
 *
 */
public class QYSuiteAuthExecutor implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(QYSuiteAuthExecutor.class);

	private static ExecutorService fixThreadExecutor = Executors.newFixedThreadPool(3);

//	private Map reqMap;
	
	private String suiteId;

	private SuiteService suiteService;

	public QYSuiteAuthExecutor() {
		suiteService = SpringContextHolder.getBean(SuiteService.class);
	}
	
	public QYSuiteAuthExecutor(String suiteId) {
		this.suiteId = suiteId;
		suiteService = SpringContextHolder.getBean(SuiteService.class);
	}

	/**
	 * 
	 */
//	public QYSuiteAuthExecutor(Map reqMap) {
//		this.reqMap = reqMap;
//		suiteService = SpringContextHolder.getBean(SuiteService.class);
//	}

	public void execute() {
		fixThreadExecutor.execute(this);
	}

	@Override
	public void run() {
		if (suiteService == null) {
			logger.error("suitService is null exception!");
			return;
		}
	}

	/**
	 * 该API用于获取第三方应用凭证（suite_access_token）
	 * 
	 * 由于第三方服务商可能托管了大量的企业，其安全问题造成的影响会更加严重，故API中除了合法来源IP校验之外，还额外增加了suite_ticket作为安全凭证。
	 * 获取suite_access_token时，需要suite_ticket参数。suite_ticket由企业微信后台定时推送给“指令回调URL”，每十分钟更新一次，见推送suite_ticket。
	 * suite_ticket实际有效期为30分钟，可以容错连续两次获取suite_ticket失败的情况，但是请永远使用最新接收到的suite_ticket。
	 * 通过本接口获取的suite_access_token有效期为2小时，开发者需要进行缓存，不可频繁获取。
	 * 
	 * @return
	 */
	public String getSuiteAccessToken() {

		String suiteAccessToken = null;

//		String suiteId = (String) reqMap.get("SuiteId");
		if (StringUtils.isNullOrEmpty(suiteId)) {
			logger.error("suiteId is not exist exception!");
			return suiteAccessToken;
		}
//		Suite suite = suiteService.findBySuiteId(suiteId);
//		if (suite == null) {
//			logger.error("database suite not exist suiteId = {} exception!", suiteId);
//			return suiteAccessToken;
//		}
//
//		// 判断是否过期
//		if (!suite.isSuiteAccessExpiresIn()) {
//			return suite.getSuiteAccessToken();
//		}
//
//		QYThirdAPI thirdAPI = new QYThirdAPI();
//
//		Map suiteTokenMap = thirdAPI.getSuiteToken(suiteId, suite.getSecret(), suite.getTicket());
//		if (suiteTokenMap == null) {
//			logger.error("get_suite_token api result null exeception!");
//			return suiteAccessToken;
//		}

//		logger.info("get_suite_token api result {}", suiteTokenMap);
//
////		int errcode = (int) suiteTokenMap.get("errcode");
//		if (isOk(suiteTokenMap)) {
//			suiteAccessToken = (String) suiteTokenMap.get("suite_access_token");
//
//			long expiresIn = ((Number) suiteTokenMap.get("expires_in")).longValue();
//			suite.setSuiteAccessToken(suiteAccessToken);
//
//			long expires = System.currentTimeMillis() + (expiresIn - 10);
//			suite.setSuiteAccessExpires(expires);
//
//			suiteService.save(suite);
//
//		} else {
//			String errmsg = (String) suiteTokenMap.get("errmsg");
//			logger.error("errmsg = {}", errmsg);
//		}

		return suiteAccessToken;
	}

	/**
	 * 获取预授权码
	 * 
	 * @param suiteId
	 * @return
	 */
	public String getPreAuthCode(String suiteId) {
		String suiteAccessToken = getSuiteAccessToken();

		if (StringUtils.isNullOrEmpty(suiteAccessToken)) {
			logger.error("suiteAccessToken is null exception!");
			return null;
		}

		String preAuthCode = null;

		QYThirdAPI thirdAPI = new QYThirdAPI();
		Map r = thirdAPI.getPreAuthCode(suiteAccessToken);
//		int errcode = (int) r.get("errcode");
		if (isOk(r)) {
			preAuthCode = (String) r.get("pre_auth_code");
			long expiresIn = ((Number) r.get("expires_in")).longValue();

//			Suite suite = suiteService.findBySuiteId(suiteId);
//			suite.setPreAuthCode(preAuthCode);
//			long expires = System.currentTimeMillis() + (expiresIn - 10);
//			suite.setPreAuthCodeExpires(expires);

//			suiteService.save(suite);
		} else {
			String errmsg = (String) r.get("errmsg");
			logger.error("errmsg = {}", errmsg);
		}
		return preAuthCode;
	}

	/**
	 * 设置授权配置
	 * 
	 * @param preAuthCode
	 * @param authType
	 */
	public void setSessionInfo(String preAuthCode, QYAuthType authType) {
		String suiteAccessToken = getSuiteAccessToken();

		if (StringUtils.isNullOrEmpty(suiteAccessToken)) {
			logger.error("suiteAccessToken is null exception!");
			return;
		}

		QYThirdAPI thirdAPI = new QYThirdAPI();
		Map r = thirdAPI.setSessionInfo(suiteAccessToken, preAuthCode, authType);

//		int errcode = (int) r.get("errcode");
		if (isOk(r)) {
			logger.info("设置授权配置 success !");
		} else {
			String errmsg = (String) r.get("errmsg");
			logger.error("errmsg = {}", errmsg);
		}

	}

	/**
	 * 获取企业永久授权码
	 * 该API用于使用临时授权码换取授权方的永久授权码，并换取授权信息、企业access_token，临时授权码一次有效。建议第三方优先以userid为主键，其次以email为主键来建立自己的管理员账号。
	 * 
	 * 临时授权码，从企业微信管理端单点登录，有业务设置url获得
	 * 
	 * @param authCode
	 * @return
	 */
	public String getPermanentCode(String authCode) {
		if (StringUtils.isNullOrEmpty(authCode)) {
			logger.error("authCode is null exception!");
			return null;
		}
		String suiteAccessToken = getSuiteAccessToken();
		if (StringUtils.isNullOrEmpty(suiteAccessToken)) {
			logger.error("suiteAccessToken is null exception!");
			return null;
		}

		QYThirdAPI thirdAPI = new QYThirdAPI();
		Map r = thirdAPI.getPermanentCode(suiteAccessToken, authCode);
//		int errcode = (int) r.get("errcode");
		if (isOk(r)) {
			logger.info("----------------授权信息成功---------------");
			logger.info(JSONUtil.toJson(r));

			String permanentCode = (String) r.get("permanent_code");
			String accessToken = (String) r.get("access_token");
			String expires_in = (String) r.get("expires_in");

			Map authCorpInfo = (Map) r.get("auth_corp_info");
			String corpid = (String) authCorpInfo.get("corpid");
			String corp_name = (String) authCorpInfo.get("corp_name");

			return permanentCode;
			// Suite suite = suiteService.findBySuiteId(suiteId);
		}else {
			logger.info("----------------授权信息失败---------------");
			logger.info(JSONUtil.toJson(r));
		}

		return null;
		// String providerAccessToken =
		// QYTokenPreference.getInstance().getProviderAccessToken();
		// Map r = QYThirdAPI thirdAPI = new QYThirdAPI();
		// thirdAPI.getPermanentCode(suiteAccessToken, authCode)
	}
	
	public String getLoginInfo(String authCode) {
		if (StringUtils.isNullOrEmpty(authCode)) {
			logger.error("authCode is null exception!");
			return null;
		}
		String providerAccessToken =
				 QYTokenPreference.getInstance().getProviderAccessToken();
		if (StringUtils.isNullOrEmpty(providerAccessToken)) {
			logger.error("providerAccessToken is null exception!");
			return null;
		}

		QYThirdAPI thirdAPI = new QYThirdAPI();
		Map r = thirdAPI.getLoginInfo(providerAccessToken, authCode);
//		int errcode = (int) r.get("errcode");
		if (isOk(r)) {
			logger.info("----------------getLoginInfo成功---------------");
			logger.info(r.toString());
			logger.info(JSONUtil.toGJson(r));

//			String permanentCode = (String) r.get("permanent_code");
//			String accessToken = (String) r.get("access_token");
//			String expires_in = (String) r.get("expires_in");
//
//			Map authCorpInfo = (Map) r.get("auth_corp_info");
//			String corpid = (String) authCorpInfo.get("corpid");
//			String corp_name = (String) authCorpInfo.get("corp_name");

			return null;
			// Suite suite = suiteService.findBySuiteId(suiteId);
		}else {
			logger.info("----------------getLoginInfo失败---------------");
			logger.info(JSONUtil.toJson(r));
		}

		return null;
		
		// Map r = QYThirdAPI thirdAPI = new QYThirdAPI();
		// thirdAPI.getPermanentCode(suiteAccessToken, authCode)
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}
	
	public boolean isOk(Map r) {
		Object errCode = r.get("errcode");
		if (errCode == null) {
			return true;
		}
		if (errCode instanceof Number) {
			if (((Number) errCode).intValue() == 0) {
				return true;
			}
		}
		return false;
	}
	
	

}
