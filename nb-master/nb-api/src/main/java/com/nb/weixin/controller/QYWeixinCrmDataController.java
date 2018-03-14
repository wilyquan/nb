/**
 * 
 */
package com.nb.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nb.fastweixin.company.handle.QYOrderHandle;
import com.nb.fastweixin.servlet.QYWeixinControllerSupport;
import com.nb.weixin.handle.QYOrderCrmHandle;

/**
 * 
 * 企业微信应用
 * 
 * @author jewelvary
 *
 */
@Controller
@RequestMapping(value = "weixin/qy/crm/data")
public class QYWeixinCrmDataController extends QYWeixinControllerSupport {

	@Override
	protected String getToken() {
		return "qy_yingyong_jewelvary";
	}

	@Override
	protected String getCropId() {
		return "ww202a2aaa0ae43950";
//		return "ww99d64c7308049ec2";
	}

	@Override
	protected String getAESKey() {
		return "xrIh4pDOWFfoWsJeqqpyRplbqbpMix3iAaKp4vWwgl7";
	}

	@Override
	protected QYOrderHandle initOrderHandle() {
		return new QYOrderCrmHandle();
	}

}

//授权信息
//
//<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[PUFDPq5hSzxDl5XPu1HVtzbAmf7wW8at3l8SBlvcLn/09ZS3YngX+NiCMotaHuJSMiTPzAZbEHx6lfPzcFAXP+ruKts6eqYlyS/yaUBkea7H1ndGGJ3oGdsD8s0lxoxWZ3bPf4BZCyDdT75Gh5mW4I/x6Y1E3X0Swct8lN1LV35KNXAVkwz/mngvaX+Jt0pl91Ja+4PemkR7IUwKSwca/jU7wYBQh1N77AEh8B6Fd0mw5LoaoHMKvPcUt5kvUq/gRGg4QnLRFFNpggV44hmdb+QmbDrHRGrZyanfxi5Q1ZwDzxvgG2h/oMF9usUJ+FJ98NBcjMixTQRqqjAnbPqqRNrCrxjHbxGeK1O5szQxhG7tpDfWO3Dwcg7I8Q5agVSXwhv2LtSqWzoPiJv2TAFxaC4iFIZKwxs/SG1MvPqHlug=]]></Encrypt></xml>

//不知道
//<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[NAI0nmXjKYE0jmiWpUD7eAHD3tk6gKR46qaF5UkkIX8XOHZCt8puONYG4OusSkZGXt02nQdDmhqMgYectezIGKoWUmIKEYXwOGhNmPB08n89U6SBXseHQwAaJpavmBult6ChGOpjVLHkpRFttu5VmrqNbT07SbgHRDfJUGVB3gDbTw3U8dphmOuPMLSthBDzW96BcxbKcCVYJ6gMeG+KVKXcel0y2ejtYGjMeIBryFeBlcoQrMtrcFTXY3R4uChlKAJVmDHgsf34Vwau7cz6eFsEsBiGz/xT5Ua6lk/gEsT+YCBvdgRV8Hj6zPOPVV4g5DES0mn6wYkwwwjTO0tUhZV3IkN6s3yNXG0cxRMV9umOhBjzSPot7zq6zIpqsI9w]]></Encrypt></xml>