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
import com.nb.utils.SpringContextHolder;
import com.nb.utils.StringUtils;
import com.nb.weixin.company.entity.Suite;
import com.nb.weixin.company.service.SuiteAuthService;
import com.nb.weixin.company.service.SuiteService;

/**
 * @author jewelvary
 *
 */
public class QYSuiteAuthExecutor implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(QYSuiteAuthExecutor.class);
	
	private static ExecutorService fixThreadExecutor = Executors.newFixedThreadPool(3);

	private Map reqMap;
	
	
	private SuiteService suiteService;
	/**
	 * 
	 */
	public QYSuiteAuthExecutor(Map reqMap) {
		this.reqMap = reqMap;
		suiteService= SpringContextHolder.getBean(SuiteService.class);
	}
	
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
	public String getSuiteToken() {
		
		String suiteAccessToken = null;
		
		String suiteId = (String) reqMap.get("SuiteId");
		if (StringUtils.isNullOrEmpty(suiteId)) {
			logger.error("suiteId is not exist exception!");
			return suiteAccessToken;
		}
		Suite suite = suiteService.findBySuiteId(suiteId);
		if (suite ==null) {
			logger.error("database suite not exist suiteId = {} exception!", suiteId);
			return suiteAccessToken;
		}
		
		QYThirdAPI thirdAPI = new QYThirdAPI();
		
		Map suiteTokenMap = thirdAPI.getSuiteToken(suiteId, suite.getSecret(), suite.getTicket());
		if (suiteTokenMap == null) {
			logger.error("get_suite_token api result null exeception!");
			return suiteAccessToken;
		}
		
		logger.info("get_suite_token api result {}", suiteTokenMap);
		
		int errcode = (int) suiteTokenMap.get("errcode");
		if (errcode == 0) {
			suiteAccessToken = (String) suiteTokenMap.get("suite_access_token");
			
			int expiresIn = (int) suiteTokenMap.get("expires_in");
			suite.setSuiteAccessToken(suiteAccessToken);
			
			long expires = System.currentTimeMillis() + expiresIn * 1000;
			suite.setSuiteAccessExpires(expires);
			
			suiteService.save(suite);
			
		}else {
			String errmsg = (String) suiteTokenMap.get("errmsg");
			logger.error("errmsg = {}", errmsg);
		}
		
		return suiteAccessToken;
	}
	
	public void getPreAuthCode(String suiteAccessToken) {
		
	}

}
