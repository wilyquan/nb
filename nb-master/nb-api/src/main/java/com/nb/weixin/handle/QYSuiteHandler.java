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
	
	/**
	 * 获取企业授权信息
	 * 
	 * @param suiteId
	 * @param authCorpId
	 * @param permanentCode
	 */
	public static void handleCorpAuthInfo(String suiteId, String authCorpId, String permanentCode) {
		String suiteAccessToken = QYTokenPreference.getInstance().getSuiteAccessToken(suiteId);
		if (suiteAccessToken != null) {
			QYThirdAPI thirdAPI = new QYThirdAPI();
			Map r = thirdAPI.getAuthInfo(suiteAccessToken, authCorpId, permanentCode);
			
			if (isOk(r)) {
				logger.info("----------------handleCorpAuthInfo 授权信息成功---------------");
				logger.info(JSONUtil.toJson(r));

			} else {
				logger.info("----------------handleCorpAuthInfo 授权信息失败---------------");
				logger.info(JSONUtil.toJson(r));
			}
			
		}else {
			logger.info("suiteAccessToken is null exception!");
		}
	}
	
	public static void handleCorpAccessToken(String suiteId, String authCorpId, String permanentCode) {
		String suiteAccessToken = QYTokenPreference.getInstance().getSuiteAccessToken(suiteId);
		if (suiteAccessToken != null) {
			QYThirdAPI thirdAPI = new QYThirdAPI();
			Map r = thirdAPI.getCorpAccessToken(suiteAccessToken, authCorpId, permanentCode);
			
			if (isOk(r)) {
				logger.info("----------------handleCorpAuthInfo 授权信息成功---------------");
				logger.info(JSONUtil.toJson(r));

			} else {
				logger.info("----------------handleCorpAuthInfo 授权信息失败---------------");
				logger.info(JSONUtil.toJson(r));
			}
			
		}else {
			logger.info("suiteAccessToken is null exception!");
		}
	}

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

/*
 * 
 * 
 * 
 * {
        "access_token":"acfpQhgVDNIPNbdDrZYqPiDa_zaD72rbL6vVj9zZIOFu-y6yacM1dq3niFCNPYiNnDy7NccMdyzOT2eZ_-OO06B3Q5tk40zs0waNVoQ_rEp9MozI-Y052jXhcpomNFGp0mbKoMut58p7P7gr6JIIaAGZil4q-hEMPNTh7O_ZsKm3qSX8EHKy45qGiYisI0-Esq1_fMZa-NO21hcl_HdM0A",
        "expires_in":7200.0,
        "permanent_code":"rn1HUdjupH-HbULWm9I-LIGs98L5YQuT5tywgsnXK6I",
        "auth_corp_info":{
                "corpid":"ww99d64c7308049ec2",
                "corp_name":"JEWELVARY意大利珠宝定制",
                "corp_type":"verified",
                "corp_round_logo_url":"http://p.qpic.cn/pic_wework/3777001839/4046834be7a5f2711feaaa3cc4e691e1bcb1e526cb4544b5/0",
                "corp_square_logo_url":"http://p.qlogo.cn/bizmail/XwkN1nSADmicVshZ7A85uh6VMyGrb2E8kHKf4Z9IrVaQUASpsH6iaAtA/0",
                "corp_user_max":500.0,
                "corp_agent_max":0.0,
                "corp_wxqrcode":"http://p.qpic.cn/pic_wework/476814639/b8be41b3d5dad15c2f694b726b7d616388f77e65d0a5d283/0",
                "corp_full_name":"卓法利珠宝(上海)有限公司",
                "subject_type":1.0,
                "verified_end_time":1.535194209E9
        },
        "auth_info":{
                "agent":[
                        {
                                "agentid":1000029.0,
                                "name":"企业CRM",
                                "square_logo_url":"https://p.qlogo.cn/bizmail/PtVRSfMj4DSkw8ibEzfMiaSSaN8wZWeZ8nTnmoXVqmLh9mdTZOHpUiaicA/0",
                                "privilege":{
                                        "level":1.0,
                                        "allow_party":[
                                                2.0
                                        ],
                                        "allow_user":[],
                                        "allow_tag":[],
                                        "extra_party":[],
                                        "extra_user":[],
                                        "extra_tag":[]
                                }
                        }
                ]
        },
        "auth_user_info":{
                "userid":"WangQuan",
                "mobile":"15201801512",
                "email":"wangquan@huayingmedia.com",
                "name":"王权",
                "avatar":"http://p.qlogo.cn/bizmail/PtVRSfMj4DSkw8ibEzfMiaSSaN8wZWeZ8nDxEicAictmSq62oDDw8FJ9eQ/0"
        }
}
 * 
 * */
