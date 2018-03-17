/**
 * 
 */
package com.nb.weixin.company.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nb.entity.CrudService;
import com.nb.weixin.company.dao.SuiteAuthDao;
import com.nb.weixin.company.dao.SuiteOrderDao;
import com.nb.weixin.company.entity.Suite;
import com.nb.weixin.company.entity.SuiteAuth;
import com.nb.weixin.company.entity.SuiteOrder;

/**
 * @author willie
 *
 */
// @Component("userService")
@Service(value = "suiteAuthService")
@Transactional
public class SuiteAuthService extends CrudService<SuiteAuthDao, SuiteAuth, String> {

	/**
	 * 授权成功
	 * @param suiteAuth
	 */
//	public void doSuiteAuth(SuiteAuth suiteAuth) {
//		SuiteAuth oldSuiteAuth = dao.findBySuiteId(suiteAuth.getSuiteId());
//		
//		if (oldSuiteAuth != null) {
//			oldSuiteAuth.setAuthCode(suiteAuth.getAuthCode());
//			oldSuiteAuth.setStatus(suiteAuth.getStatus());
//		} else {
//			oldSuiteAuth = suiteAuth;
//		}
//
//		this.save(oldSuiteAuth);
//	}
	
	/**
	 * 授权取消
	 * @param suiteAuth
	 */
//	public void doSuiteAuthCancel(SuiteAuth suiteAuth) {
//		SuiteAuth oldSuiteAuth = dao.findBySuiteId(suiteAuth.getSuiteId());
//		
//		if (oldSuiteAuth != null) {
//			oldSuiteAuth.setCorpId(suiteAuth.getCorpId());
//			oldSuiteAuth.setStatus(suiteAuth.getStatus());
//		} else {
//			oldSuiteAuth = suiteAuth;
//		}
//
//		this.save(oldSuiteAuth);
//	}
	
}
