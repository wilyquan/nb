/**
 * 
 */
package com.nb.weixin.handle;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nb.fastweixin.company.handle.QYOrderHandle;
import com.nb.fastweixin.company.message.req.QYOrderType;
import com.nb.fastweixin.util.JSONUtil;
import com.nb.utils.SpringContextHolder;
import com.nb.weixin.company.entity.Suite;
import com.nb.weixin.company.entity.SuiteAuth;
import com.nb.weixin.company.entity.SuiteOrder;
import com.nb.weixin.company.service.SuiteAuthService;
import com.nb.weixin.company.service.SuiteOrderService;
import com.nb.weixin.company.service.SuiteService;

/**
 * @author jewelvary
 *
 */
public class QYCrmOrderHandle implements QYOrderHandle {

	private static final Logger LOG = LoggerFactory.getLogger(QYCrmOrderHandle.class);

	/**
	 * 
	 */
	public QYCrmOrderHandle() {
	}

	@Override
	public boolean handle(Map reqMap) {
		LOG.info(JSONUtil.toJson(reqMap));
		String infoType = (String) reqMap.get("InfoType");
		if (QYOrderType.SUITE_TICKET.equalsIgnoreCase(infoType)) {
			updateSuiteTicket(reqMap);

			return true;
		} else if (QYOrderType.CREATE_AUTH.equalsIgnoreCase(infoType)) {
			suiteAuth(reqMap);
		} else if (QYOrderType.CANCEL_AUTH.equalsIgnoreCase(infoType)) {
			suiteAuthCancel(reqMap);
		}

		addSuiteOrder(reqMap);
		
		return true;
	}

	/**
	 * 授权取消
	 * @param reqMap
	 */
	public void suiteAuthCancel(Map reqMap) {
		String suiteId = (String) reqMap.get("SuiteId");
		String cropId = (String) reqMap.get("AuthCorpId");

		SuiteAuth auth = new SuiteAuth(suiteId, null, cropId);

		SuiteAuthService service = SpringContextHolder.getBean(SuiteAuthService.class);
		
		service.doSuiteAuthCancel(auth);
	}

	/**
	 * 授权成功
	 * @param reqMap
	 */
	public void suiteAuth(Map reqMap) {
		String suiteId = (String) reqMap.get("SuiteId");
		String authCode = (String) reqMap.get("AuthCode");

		SuiteAuth auth = new SuiteAuth(suiteId, authCode, null);

		SuiteAuthService service = SpringContextHolder.getBean(SuiteAuthService.class);
		service.doSuiteAuth(auth);
	}

	public void updateSuiteTicket(Map map) {
		SuiteService service = SpringContextHolder.getBean(SuiteService.class);
		if (service != null) {
			Suite suite = new Suite();
			Suite newSuite = suite.convert(map);
			if (newSuite != null) {
				service.updateSuiteTicket(newSuite);
			}

		}
	}

	public void addSuiteOrder(Map map) {
		SuiteOrderService service = SpringContextHolder.getBean(SuiteOrderService.class);

		if (service != null) {
			SuiteOrder suiteOrder = new SuiteOrder();
			SuiteOrder newSuiteOrder = suiteOrder.convert(map);
			if (newSuiteOrder != null) {
				service.save(newSuiteOrder);
			}
		}
	}

}
