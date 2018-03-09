/**
 * 
 */
package com.nb.sys.dao;

import org.springframework.stereotype.Repository;
import com.nb.entity.CrudDao;
import com.nb.sys.entity.User;

/**
 * @author willie
 *
 */
@Repository
//public interface UserDAO extends CrudDao<User, String> {
public interface UserDAO {

	User findByName(String name);
}
