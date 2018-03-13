/**
 * 
 */
package com.nb.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nb.fastweixin.servlet.QYWeixinControllerSupport;
import com.nb.fastweixin.servlet.WeixinControllerSupport;

/**
 * @author jewelvary
 *
 */
@Controller
@RequestMapping(value = "weixin")
public class WeixinController extends WeixinControllerSupport {

	@Override
	protected String getToken() {
		// TODO Auto-generated method stub
		return "websiteweixin_token";
	}

	// 使用安全模式时设置：APPID
	// 不再强制重写，有加密需要时自行重写该方法
	@Override
	protected String getAppId() {
		return "wx812ada61d299ea13";
	}

}
