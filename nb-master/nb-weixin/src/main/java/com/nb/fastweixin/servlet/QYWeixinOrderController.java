/**
 * 
 */
package com.nb.fastweixin.servlet;

import com.nb.fastweixin.company.handle.QYDefaultOrderHandle;
import com.nb.fastweixin.company.handle.QYOrderHandle;
import com.nb.fastweixin.company.handle.QYSuiteHandle;

/**
 * @author jewelvary
 *
 */
public abstract class QYWeixinOrderController extends QYWeixinControllerSupport implements QYSuiteHandle{

	/**
	 * 
	 */
	public QYWeixinOrderController() {
		// TODO Auto-generated constructor stub
	}

//	/* (non-Javadoc)
//	 * @see com.nb.fastweixin.servlet.QYWeixinSupport#getToken()
//	 */
//	@Override
//	protected String getToken() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.nb.fastweixin.servlet.QYWeixinSupport#getCropId()
//	 */
//	@Override
//	protected String getCropId() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.nb.fastweixin.servlet.QYWeixinSupport#getAESKey()
//	 */
//	@Override
//	protected String getAESKey() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	/**
	 * 子类无需实现，如果要实现，则无需继承该类
	 */
	@Override
	protected QYOrderHandle initOrderHandle() {
		return new QYDefaultOrderHandle(this);
	}

}
