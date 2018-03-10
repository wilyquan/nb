/**
 * 
 */
package com.nb.sys.dao;


import com.nb.entity.CrudDao;
import com.nb.sys.entity.User;

/**
 * @author willie
 *
 */
//public interface UserRepository extends Repository<User, String>  {
public interface UserRepository extends CrudDao<User, String>  {

	User findByName(String name);
	
	User findById(String id);
}
