/**
 * 
 */
package com.nb.weixin.handle;

import com.nb.fastweixin.company.handle.QYEventHandle;
import com.nb.fastweixin.company.message.req.QYBaseEvent;
import com.nb.fastweixin.company.message.resp.QYBaseRespMsg;
import com.nb.fastweixin.handle.EventHandle;

/**
 * @author jewelvary
 *
 */
public class QYCrmEventHandle implements QYEventHandle {


	@Override
	public QYBaseRespMsg handle(QYBaseEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean beforeHandle(QYBaseEvent event) {
		
		String eventType = event.getEvent();
		
		return false;
	}

}
