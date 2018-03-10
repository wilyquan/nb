package com.nb;

import org.springframework.beans.factory.annotation.Autowired;

import com.nb.sys.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "user")
public class User1Controller {

	@Autowired
	private UserService userService;
	
	

	@RequestMapping("/")
	@ResponseBody
	@Transactional(readOnly = true)
	public String helloWorld(String name) {
		com.nb.sys.entity.User user = this.userService.get(name);
		if (user == null) {
			return "user is null";
		}
		return user.getName();
	}

}
