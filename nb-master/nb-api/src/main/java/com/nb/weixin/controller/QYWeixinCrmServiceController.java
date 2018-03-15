/**
 * 
 */
package com.nb.weixin.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nb.fastweixin.company.handle.QYEventHandle;
import com.nb.fastweixin.company.handle.QYOrderHandle;
import com.nb.fastweixin.servlet.QYWeixinControllerSupport;
import com.nb.fastweixin.servlet.QYWeixinSupport;
import com.nb.weixin.handle.QYCrmOrderHandle;
import com.nb.weixin.handle.QYSuiteAuthExecutor;

/**
 * 
 * 企业微信业务
 * 
 * 第三方应用的业务设置及数据需前往服务商后台查看
 * 
 * @author jewelvary
 *
 */
@RestController
@RequestMapping(value = "weixin/qy/crm/service")
public class QYWeixinCrmServiceController {

	protected static final Logger LOG = LoggerFactory.getLogger(QYWeixinCrmServiceController.class);
	
	private static final String suiteId = "ww202a2aaa0ae43950";
	
	/**
	 * 用户企业微信后台，前往商户服务台是被调用。
	 * 
	 * 可以获得登陆用户的信息
	 * 
	 * @param auth_code
	 * @return
	 */
	@GetMapping
	public String index(String auth_code) {
		LOG.info("auth_code = {} ", auth_code);
//		model.put("time", new Date());
//		model.put("message", this.message);
		QYSuiteAuthExecutor executor = new QYSuiteAuthExecutor(suiteId);
		String permanentCode = executor.getLoginInfo(auth_code);
//		if(permanentCode !=null) {
//			LOG.info("permanentCode = {} ", permanentCode);
//		}
		return "sucess";
	}
	
	@GetMapping("loginAuth")
	public ModelAndView loginAuth(String auth_code) {
		LOG.info("auth_code = {} ", auth_code);
//		model.put("time", new Date());
//		model.put("message", this.message);
		QYSuiteAuthExecutor executor = new QYSuiteAuthExecutor(suiteId);
		String permanentCode = executor.getLoginInfo(auth_code);
//		if(permanentCode !=null) {
//			LOG.info("permanentCode = {} ", permanentCode);
//		}
		return new ModelAndView("example/index");
	}
	
	@GetMapping("login")
	public ModelAndView login(String auth_code) {
		LOG.info("auth_code = {} ", auth_code);
//		model.put("time", new Date());
//		model.put("message", this.message);
//		QYSuiteAuthExecutor executor = new QYSuiteAuthExecutor(suiteId);
//		String permanentCode = executor.getLoginInfo(auth_code);
//		if(permanentCode !=null) {
//			LOG.info("permanentCode = {} ", permanentCode);
//		}
		return new ModelAndView("example/index");
	}
}

// 授权信息
//
// <xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[PUFDPq5hSzxDl5XPu1HVtzbAmf7wW8at3l8SBlvcLn/09ZS3YngX+NiCMotaHuJSMiTPzAZbEHx6lfPzcFAXP+ruKts6eqYlyS/yaUBkea7H1ndGGJ3oGdsD8s0lxoxWZ3bPf4BZCyDdT75Gh5mW4I/x6Y1E3X0Swct8lN1LV35KNXAVkwz/mngvaX+Jt0pl91Ja+4PemkR7IUwKSwca/jU7wYBQh1N77AEh8B6Fd0mw5LoaoHMKvPcUt5kvUq/gRGg4QnLRFFNpggV44hmdb+QmbDrHRGrZyanfxi5Q1ZwDzxvgG2h/oMF9usUJ+FJ98NBcjMixTQRqqjAnbPqqRNrCrxjHbxGeK1O5szQxhG7tpDfWO3Dwcg7I8Q5agVSXwhv2LtSqWzoPiJv2TAFxaC4iFIZKwxs/SG1MvPqHlug=]]></Encrypt></xml>

// 不知道
// <xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[NAI0nmXjKYE0jmiWpUD7eAHD3tk6gKR46qaF5UkkIX8XOHZCt8puONYG4OusSkZGXt02nQdDmhqMgYectezIGKoWUmIKEYXwOGhNmPB08n89U6SBXseHQwAaJpavmBult6ChGOpjVLHkpRFttu5VmrqNbT07SbgHRDfJUGVB3gDbTw3U8dphmOuPMLSthBDzW96BcxbKcCVYJ6gMeG+KVKXcel0y2ejtYGjMeIBryFeBlcoQrMtrcFTXY3R4uChlKAJVmDHgsf34Vwau7cz6eFsEsBiGz/xT5Ua6lk/gEsT+YCBvdgRV8Hj6zPOPVV4g5DES0mn6wYkwwwjTO0tUhZV3IkN6s3yNXG0cxRMV9umOhBjzSPot7zq6zIpqsI9w]]></Encrypt></xml>