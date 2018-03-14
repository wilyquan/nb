/**
 * 
 */
package com.nb.weixin.handle;

import com.nb.fastweixin.company.handle.QYEventHandle;
import com.nb.fastweixin.company.handle.QYMessageHandle;
import com.nb.fastweixin.company.message.req.QYBaseEvent;
import com.nb.fastweixin.company.message.req.QYBaseReqMsg;
import com.nb.fastweixin.company.message.req.QYReqType;
import com.nb.fastweixin.company.message.resp.QYBaseRespMsg;
import com.nb.fastweixin.company.message.resp.QYTextRespMsg;
import com.nb.fastweixin.handle.EventHandle;

/**
 * @author jewelvary
 *
 */
public class QYCrmMessageHandle implements QYMessageHandle {

	@Override
	public QYBaseRespMsg handle(QYBaseReqMsg message) {
		if (message.getMsgType().equalsIgnoreCase(QYReqType.TEXT))
		{
			QYTextRespMsg respMsg = new QYTextRespMsg();
			respMsg.setMsgType(QYReqType.TEXT);
			respMsg.setFromUserName(message.getToUserName());
			respMsg.setToUserName(message.getFromUserName());
			respMsg.setCreateTime((int) System.currentTimeMillis());
			respMsg.setContentBuilder("系统收到您的问题，将稍后回答，敬请稍等...");
			
			return respMsg;
		}
		 
		
		return null;
	}

	@Override
	public boolean beforeHandle(QYBaseReqMsg message) {
		return true;
	}


	

}
