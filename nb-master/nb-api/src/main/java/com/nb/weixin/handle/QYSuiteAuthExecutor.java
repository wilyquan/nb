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
	
	public void getSuiteToken() {
		
		String suiteId = (String) reqMap.get("SuiteId");
		if (StringUtils.isNullOrEmpty(suiteId)) {
			logger.error("suiteId is not exist exception!");
			return;
		}
		Suite suite = suiteService.findBySuiteId(suiteId);
		if (suite ==null) {
			logger.error("database suite not exist suiteId = {} exception!", suiteId);
			return;
		}
		
		QYThirdAPI thirdAPI = new QYThirdAPI();
		
		Map suiteTokenMap = thirdAPI.getSuiteToken(suiteId, suite.getSecret(), suite.getTicket());
		if (suiteTokenMap == null) {
			logger.error("get_suite_token api result null exeception!");
			return;
		}
		
		logger.info("get_suite_token api result {}", suiteTokenMap);
		
//		suiteTokenMap
		
	}

}
