/**
 * 
 */
package com.nb.weixin.handle;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jewelvary
 *
 */
public class QYSuiteAuthExecutor implements Runnable{
	
	private static ExecutorService fixThreadExecutor = Executors.newFixedThreadPool(3);

	private Map reqMap;
	/**
	 * 
	 */
	public QYSuiteAuthExecutor(Map reqMap) {
		this.reqMap = reqMap;
	}
	
	public void execute() {
		fixThreadExecutor.execute(this);
	}

	@Override
	public void run() {
		
	}

}
