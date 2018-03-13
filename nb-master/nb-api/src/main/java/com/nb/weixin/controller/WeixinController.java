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
public class WeixinController extends WeixinControllerSupport{

	@Override
	protected String getToken() {
		// TODO Auto-generated method stub
		return "websiteweixin_token";
	}

	
	

}
