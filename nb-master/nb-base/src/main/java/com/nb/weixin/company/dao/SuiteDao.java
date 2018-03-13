/**
 * 
 */
package com.nb.weixin.company.dao;


import com.nb.entity.BaseDao;
import com.nb.weixin.company.entity.Suite;

/**
 * @author jewelvary
 *
 */

public interface SuiteDao extends BaseDao<Suite, String> {

	Suite findBySuiteId(String suiteId);

}
