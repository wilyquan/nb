package com.nb.sys.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.nb.sys.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	@ResponseBody
	@Transactional(readOnly = true)
	public String helloWorld() {
		com.nb.sys.entity.User user = this.userService.getUser("wangquan");
		return user.getName();
	}

}
