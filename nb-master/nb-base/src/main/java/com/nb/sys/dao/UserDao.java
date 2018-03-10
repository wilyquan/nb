/**
 * 
 */
package com.nb.sys.dao;


import org.springframework.cache.annotation.CacheConfig;

import com.nb.entity.BaseDao;
import com.nb.sys.entity.User;

/**
 * @author willie
 *
 */
//public interface UserRepository extends Repository<User, String>  {
@CacheConfig(cacheNames = "user")
public interface UserDao extends BaseDao<User, String>  {
	
}
