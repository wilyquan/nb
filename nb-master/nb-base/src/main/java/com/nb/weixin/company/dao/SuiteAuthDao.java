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
	
	SuiteAuth findBySuiteId(String suiteId);
	
}
