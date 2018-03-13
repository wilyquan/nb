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
@RequestMapping(value = "weixin/qy/crm")
public class QYWeixinCrmController extends QYWeixinControllerSupport {

	@Override
	protected String getToken() {
		return "qy_yingyong_jewelvary";
	}

	@Override
	protected String getCropId() {
		return "ww202a2aaa0ae43950";
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
