/**
 * 
 */
package com.nb.sys.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nb.entity.service.CrudService;
import com.nb.sys.dao.UserDAO;
import com.nb.sys.dao.UserRepository;
import com.nb.sys.entity.User;

/**
 * @author willie
 *
 */
//@Component("userService")
@Service(value = "userService")
@Transactional
public class UserService  {
	@Autowired
	private  UserRepository dao;
	
	public User getUser(String name) {
		return dao.findById(name);
	}
}
