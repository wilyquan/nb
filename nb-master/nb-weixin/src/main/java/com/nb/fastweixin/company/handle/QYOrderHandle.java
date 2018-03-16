package com.nb.fastweixin.company.handle;

import java.util.Map;

import com.nb.fastweixin.company.message.req.QYBaseEvent;
import com.nb.fastweixin.company.message.resp.QYBaseRespMsg;

/**
 *  微信企业号指令处理器接口
 *  ====================================================================
 *  
 *  --------------------------------------------------------------------
 *  @author willie
 *  @version 1.0.beta
 *  @since 1.3.6
 *  ====================================================================
 */
public interface QYOrderHandle{
	
	/**
	 * 获得套件业务处理对象
	 * 
	 * @return
	 */
	public QYSuiteHandle getSuiteHandle();

    /**
     * 处理指令事件
     * 
     * @param req
     * @return
     */
    boolean handle(Map req);

    /**
     * 在处理之前，判断本事件是否符合处理的条件
     *
     * @param event 事件
     * @return 是否需要处理
     */
//    boolean beforeHandle(E event);
}
