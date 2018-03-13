/**
 * 
 */
package com.nb.weixin.handle;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nb.fastweixin.company.handle.QYOrderHandle;
import com.nb.fastweixin.servlet.QYWeixinSupport;
import com.nb.fastweixin.util.JSONUtil;

/**
 * @author jewelvary
 *
 */
public class QYOrderCrmHandle implements QYOrderHandle{

	private static final Logger LOG = LoggerFactory.getLogger(QYOrderCrmHandle.class);
	/**
	 * 
	 */
	public QYOrderCrmHandle() {
	}

	@Override
	public boolean handle(Map req) {
		LOG.info(JSONUtil.toJson(req));
		return true;
	}

}
