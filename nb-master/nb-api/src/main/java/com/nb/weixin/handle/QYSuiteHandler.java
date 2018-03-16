/**
 * 
 */
package com.nb.weixin.handle;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nb.fastweixin.company.api.QYThirdAPI;
import com.nb.fastweixin.company.api.token.QYTokenPreference;
import com.nb.fastweixin.util.JSONUtil;
import com.nb.fastweixin.util.StrUtil;

import io.netty.util.internal.StringUtil;

/**
 * @author jewelvary
 *
 */
public class QYSuiteHandler {

	private static final Logger logger = LoggerFactory.getLogger(QYSuiteHandler.class);

	private static ExecutorService fixThreadExecutor = Executors.newFixedThreadPool(3);

	private String suiteId;

	/**
	 * 
	 */
	public QYSuiteHandler(String suiteId) {
		this.suiteId = suiteId;
	}

	/**
	 * 更新suiteticket
	 * 
	 * @param suiteId
	 * @param suiteTicket
	 */
	public static void updateSuiteTicket(String suiteId, String suiteTicket) {
		QYTokenPreference preference = QYTokenPreference.getInstance();
		preference.putSuiteTicket(suiteId, suiteTicket);
		if (!preference.isExistPermanentCode(suiteId)) {
			String authCode = preference.getCreateAuthCode(suiteId);
			if (!StrUtil.isBlank(authCode)) {
				handleCreateAuth(suiteId, authCode);
			}else {
				logger.info("authCode is null exception ...");
			}
		}else {
			logger.info("isExistPermanentCode true");
		}
	}

	/**
	 * 授权成功通知
	 * 
	 * @param suiteId
	 * @param authCode
	 */
	public static void handleCreateAuth(String suiteId, String authCode) {
		logger.info("handleCreateAuth function ...");
		fixThreadExecutor.execute(new Runnable() {

			@Override
			public void run() {
				String suiteAccessToken = QYTokenPreference.getInstance().getSuiteAccessToken(suiteId);
				// 首次获得suiteaccesstoken因为suite_ticket没有发送到平台，可能会存在空的内容
				if (suiteAccessToken != null) {

					QYThirdAPI thirdAPI = new QYThirdAPI();
					Map r = thirdAPI.getPermanentCode(suiteAccessToken, authCode);
					// int errcode = (int) r.get("errcode");
					if (isOk(r)) {
						logger.info("----------------CreateAuth 授权信息成功---------------");
						logger.info(JSONUtil.toJson(r));

						String permanentCode = (String) r.get("permanent_code");
						String accessToken = (String) r.get("access_token");
						String expires_in = (String) r.get("expires_in");

						Map authCorpInfo = (Map) r.get("auth_corp_info");
						String corpid = (String) authCorpInfo.get("corpid");
						String corp_name = (String) authCorpInfo.get("corp_name");

						// return permanentCode;
						// Suite suite = suiteService.findBySuiteId(suiteId);
					} else {
						logger.info("----------------CreateAuth 授权信息失败---------------");
						logger.info(JSONUtil.toJson(r));
					}
				}else {
					logger.info("suiteAccessToken is null exception!");
				}
			}

		});

	}

	// class PermanentCode implements Runnable{
	//
	// String suiteId;
	//
	// @Override
	// public void run() {
	//
	// String suiteAccessToken =
	// QYTokenPreference.getInstance().getSuiteAccessToken(suiteId);
	// }
	//
	// }

	public static boolean isOk(Map r) {
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
