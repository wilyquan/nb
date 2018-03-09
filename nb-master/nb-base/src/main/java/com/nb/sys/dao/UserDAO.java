/**
 * 
 */
package com.nb.sys.dao;

import org.springframework.data.repository.Repository;

import com.nb.entity.CrudDao;
import com.nb.sys.entity.User;

/**
 * @author willie
 *
 */
public interface UserDAO extends CrudDao<User, String> {

	User findByName(String name);
}
