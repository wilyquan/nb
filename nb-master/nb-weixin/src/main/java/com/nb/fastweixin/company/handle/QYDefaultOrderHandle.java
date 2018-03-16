package com.nb.fastweixin.company.handle;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nb.fastweixin.company.message.req.QYBaseEvent;
import com.nb.fastweixin.company.message.req.QYOrderType;
import com.nb.fastweixin.company.message.resp.QYBaseRespMsg;
import com.nb.fastweixin.util.JSONUtil;

/**
 * 微信企业号指令处理器接口
 * ====================================================================
 * 
 * --------------------------------------------------------------------
 * 
 * @author willie
 * @version 1.0.beta
 * @since 1.3.6
 *        ====================================================================
 */
public class QYDefaultOrderHandle implements QYOrderHandle {

	private static final Logger LOG = LoggerFactory.getLogger(QYDefaultOrderHandle.class);
	
	private static ExecutorService fixThreadExecutor = Executors.newFixedThreadPool(3);
	
	private QYSuiteHandle suiteHandle;
	
	public QYDefaultOrderHandle(QYSuiteHandle suiteHandle) {
		this.suiteHandle = suiteHandle;
	}
	/**
	 * 处理指令事件
	 * 
	 * @param req
	 * @return
	 */
	public boolean handle(Map reqMap) {

		//命令回调协议利用线程处理，因为企业微信接口接受回复有时间限制，先应答，之后慢慢处理业务
		OrederHandle handle = new OrederHandle(reqMap, suiteHandle);
		
		fixThreadExecutor.execute(handle);
		
		return true;
	}


	@Override
	public QYSuiteHandle getSuiteHandle() {
		return suiteHandle;
	}
	
	/**
	 * 命令处理线程
	 * @author jewelvary
	 *
	 */
	class OrederHandle implements Runnable{
		
		private QYSuiteHandle suiteHandle;
		private Map reqMap;
		
		public OrederHandle(Map reqMap, QYSuiteHandle suiteHandle) {
			this.suiteHandle = suiteHandle;
			this.reqMap = reqMap;
		}
		@Override
		public void run() {
			
			LOG.info(JSONUtil.toJson(reqMap));
			String infoType = (String) reqMap.get("InfoType");
			
			//获得套件的ticket
			if (QYOrderType.SUITE_TICKET.equalsIgnoreCase(infoType)) {
				String suiteId = (String) reqMap.get("SuiteId");
				String suiteTicket = (String) reqMap.get("SuiteTicket");
				
				//更新ticket信息
				QYDefaultSuiteHandle.updateSuiteTicket(suiteId, suiteTicket, getSuiteHandle());
			} else if (QYOrderType.CREATE_AUTH.equalsIgnoreCase(infoType)) {
				//获得授权的auth_code,最长为512字节。用于获取企业的永久授权码
				
				QYDefaultSuiteHandle.handleCreateAuth((String) reqMap.get("SuiteId"), (String) reqMap.get("AuthCode"), getSuiteHandle());

			} else if (QYOrderType.CANCEL_AUTH.equalsIgnoreCase(infoType)) {
				
			}

			if (suiteHandle != null) {
				suiteHandle.receiveOrder(reqMap);
			}
		}
		
	}

	/**
	 * 在处理之前，判断本事件是否符合处理的条件
	 *
	 * @param event
	 *            事件
	 * @return 是否需要处理
	 */
	// boolean beforeHandle(E event);
}
