/**
 * 
 */
package com.nb.weixin.company.dao;

import com.nb.entity.BaseDao;
import com.nb.weixin.company.entity.SuiteAuth;

/**
 * @author jewelvary
 *
 */

public interface SuiteAuthDao extends BaseDao<SuiteAuth, String> {
	
	SuiteAuth findBySuiteIdAndAuthcorpId(String suiteId, String authcorpId);
	
	SuiteAuth findBySuiteIdAndAuthCode(String suiteId, String authCode);
	
}
