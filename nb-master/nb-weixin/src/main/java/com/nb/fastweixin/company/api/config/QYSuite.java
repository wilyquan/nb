/**
 * 
 */
package com.nb.fastweixin.company.api.config;

/**
 * 企业第三发应用suite信息
 * 
 * @author jewelvary
 *
 */
public class QYSuite {

	private String suiteId;
	private String suiteSecret;

	/**
	 * 
	 */
	public QYSuite() {
	}
	
	public QYSuite(String suiteId, String suiteSecret) {
		this.suiteId = suiteId;
		this.suiteSecret = suiteSecret;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getSuiteSecret() {
		return suiteSecret;
	}

	public void setSuiteSecret(String suiteSecret) {
		this.suiteSecret = suiteSecret;
	}

}
