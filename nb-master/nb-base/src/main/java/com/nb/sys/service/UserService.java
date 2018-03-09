/**
 * 
 */
package com.nb.sys.service;

import com.nb.entity.service.CrudService;
import com.nb.sys.dao.UserDAO;
import com.nb.sys.entity.User;

/**
 * @author willie
 *
 */
public class UserService extends CrudService<UserDAO, User> {
	public User getUser(String name) {
		return dao.findByName(name);
	}
}
