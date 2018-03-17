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
	
	public void update(String suiteId, String authCorpId, String permanentCode, int status) {
		SuiteAuth oldSuiteAuth = dao.findBySuiteIdAndAuthcorpId(suiteId, authCorpId);
		if (oldSuiteAuth != null) {
			oldSuiteAuth.setPermanentCode(permanentCode);
			oldSuiteAuth.setStatus(status);
		}else {
			oldSuiteAuth = new SuiteAuth(suiteId, authCorpId, permanentCode, status);
		}
		
		this.save(oldSuiteAuth);
	}
	
	/**
	 * 更新永久授权码
	 * 
	 * @param suiteId
	 * @param authCorpId
	 * @param permanentCode
	 */
	public void updatePermanentCode(String suiteId, String authCorpId, String permanentCode) {
		update(suiteId, authCorpId, null, SuiteAuth.STATUS_AUTH);
	}
	
	/**
	 * 授权成功
	 * 
	 * @param suiteId
	 * @param authCorpId
	 */
	public void createAuth(String suiteId, String authCorpId) {
		update(suiteId, authCorpId, null, SuiteAuth.STATUS_AUTH);
	}
	
	/**
	 * 取消授权
	 * 
	 * @param suiteId
	 * @param authCorpId
	 */
	public void cancelAuth(String suiteId, String authCorpId) {
		update(suiteId, authCorpId, null, SuiteAuth.STATUS_AUTH_CANCEL);
	}
	
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
