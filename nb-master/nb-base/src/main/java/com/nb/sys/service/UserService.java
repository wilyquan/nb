/**
 * 
 */
package com.nb.sys.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nb.entity.service.CrudService;
import com.nb.sys.dao.UserDao;
import com.nb.sys.entity.User;

/**
 * @author willie
 *
 */
//@Component("userService")
@Service(value = "userService")
@Transactional
public class UserService extends CrudService<UserDao, User, String>{
//	@Autowired
//	private  UserRepository dao;
	
	public User getUser(String name) {
		return this.findById(name);
	}
}
