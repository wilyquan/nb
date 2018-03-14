/**
 * 
 */
package com.nb.weixin.company.service;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.nb.entity.CrudService;
import com.nb.weixin.company.dao.SuiteDao;
import com.nb.weixin.company.entity.Suite;

/**
 * @author willie
 *
 */
//@Component("userService")
@Service(value = "suiteService")
@Transactional
@CacheConfig(cacheNames = "qysuite-cache")
public class SuiteService extends CrudService<SuiteDao, Suite, String>{
	
	public Suite findBySuiteId(String suiteId) {
		return dao.findBySuiteId(suiteId);
	}

	public void updateSuiteTicket(Suite suite) {
		Suite oldSuite = dao.findBySuiteId(suite.getSuiteId());
		if (oldSuite != null) {
			oldSuite.setTicket(suite.getTicket());
		}else {
			oldSuite = suite;
		}
		
		this.save(oldSuite);
	}
	
}
