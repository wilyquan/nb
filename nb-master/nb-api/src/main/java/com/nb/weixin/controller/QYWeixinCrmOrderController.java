/**
 * 
 */
package com.nb.weixin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nb.fastweixin.company.handle.QYEventHandle;
import com.nb.fastweixin.company.handle.QYOrderHandle;
import com.nb.fastweixin.servlet.QYWeixinControllerSupport;
import com.nb.fastweixin.servlet.QYWeixinOrderController;
import com.nb.utils.SpringContextHolder;
import com.nb.weixin.company.service.SuiteAuthService;
import com.nb.weixin.company.service.SuiteService;
import com.nb.weixin.handle.QYCrmOrderHandle;

/**
 * 
 * 企业微信应用
 * 
 * 数据回调url和指令回调url 被确认校验时，appid需要设置为 corp_id 而不是该套件的suit_id
 * 数据回调url被回调时，appid需要设置为 corp_id 而不是该套件的suit_id 指令回调url被回调时，appid需要设置为 suit_id
 * 而不是该套件的corp_id 同时token和EncodingAESKey用suite里面的。 非常奇怪企业微信为什么会这样设置?
 * 
 * @author jewelvary
 *
 */
@Controller
@RequestMapping(value = "weixin/qy/crm/order")
public class QYWeixinCrmOrderController extends QYWeixinOrderController {

	@Autowired
	private SuiteAuthService suiteAuthService;
	
	@Override
	protected String getToken() {
		return "qy_yingyong_jewelvary";
	}

	@Override
	protected String getCropId() {
		// return "ww202a2aaa0ae43950";
		return "ww99d64c7308049ec2";
	}

	@Override
	protected String getAESKey() {
		return "xrIh4pDOWFfoWsJeqqpyRplbqbpMix3iAaKp4vWwgl7";
	}

//	@Override
//	protected QYOrderHandle initOrderHandle() {
//		return new QYCrmOrderHandle();
//	}

	@Override
	protected String getSuitId() {
		return "ww202a2aaa0ae43950";
	}

	@Override
	protected boolean isSuiteOrderCall() {
		return true;
	}

	protected List<QYEventHandle> initEventHandles() {
		return null;
	}

	@Override
	public void permanentCode(String suiteId, String authCorpId, String permanentCode, Map m) {
		suiteAuthService.updatePermanentCode(suiteId, authCorpId, permanentCode);
	}
	
	@Override
	public void authCorpInfo(Map m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveOrder(Map m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelAuth(String suiteId, String authCorpId) {
		suiteAuthService.cancelAuth(suiteId, authCorpId);
	}

	

}

// 授权信息
//
// <xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[PUFDPq5hSzxDl5XPu1HVtzbAmf7wW8at3l8SBlvcLn/09ZS3YngX+NiCMotaHuJSMiTPzAZbEHx6lfPzcFAXP+ruKts6eqYlyS/yaUBkea7H1ndGGJ3oGdsD8s0lxoxWZ3bPf4BZCyDdT75Gh5mW4I/x6Y1E3X0Swct8lN1LV35KNXAVkwz/mngvaX+Jt0pl91Ja+4PemkR7IUwKSwca/jU7wYBQh1N77AEh8B6Fd0mw5LoaoHMKvPcUt5kvUq/gRGg4QnLRFFNpggV44hmdb+QmbDrHRGrZyanfxi5Q1ZwDzxvgG2h/oMF9usUJ+FJ98NBcjMixTQRqqjAnbPqqRNrCrxjHbxGeK1O5szQxhG7tpDfWO3Dwcg7I8Q5agVSXwhv2LtSqWzoPiJv2TAFxaC4iFIZKwxs/SG1MvPqHlug=]]></Encrypt></xml>

// 不知道
// <xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[NAI0nmXjKYE0jmiWpUD7eAHD3tk6gKR46qaF5UkkIX8XOHZCt8puONYG4OusSkZGXt02nQdDmhqMgYectezIGKoWUmIKEYXwOGhNmPB08n89U6SBXseHQwAaJpavmBult6ChGOpjVLHkpRFttu5VmrqNbT07SbgHRDfJUGVB3gDbTw3U8dphmOuPMLSthBDzW96BcxbKcCVYJ6gMeG+KVKXcel0y2ejtYGjMeIBryFeBlcoQrMtrcFTXY3R4uChlKAJVmDHgsf34Vwau7cz6eFsEsBiGz/xT5Ua6lk/gEsT+YCBvdgRV8Hj6zPOPVV4g5DES0mn6wYkwwwjTO0tUhZV3IkN6s3yNXG0cxRMV9umOhBjzSPot7zq6zIpqsI9w]]></Encrypt></xml>