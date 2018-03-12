/**
 * 
 */
package com.nb.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nb.fastweixin.servlet.QYWeixinControllerSupport;

/**
 * @author jewelvary
 *
 */
@Controller
@RequestMapping(value = "weixin/qy")
public class QYWeixinController extends QYWeixinControllerSupport{

	@Override
	protected String getToken() {
		return "qy-weixin-token-jewelvary";
	}

	@Override
	protected String getCropId() {
		return "ww99d64c7308049ec2";
	}

	@Override
	protected String getAESKey() {
		return "6Gaj8NfANdjzn8wh9xYA4UsUv8XiRjfDbJExUSsJfdC";
	}

	

}
