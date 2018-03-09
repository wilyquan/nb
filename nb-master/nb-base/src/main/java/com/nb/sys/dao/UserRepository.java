/**
 * 
 */
package com.nb.sys.dao;

import org.springframework.data.repository.Repository;

import com.nb.sys.entity.User;

/**
 * @author willie
 *
 */
public interface UserRepository extends Repository<User, String>  {

	User findByName(String name);
}
