/**
 * 
 */
package com.nb.sys.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nb.entity.CrudService;
import com.nb.sys.dao.UserDao;
import com.nb.sys.entity.User;

/**
 * @author willie
 *
 */
//@Component("userService")
@Service(value = "userService")
@Transactional
@CacheConfig(cacheNames = "user-cache")
public class UserService extends CrudService<UserDao, User, String>{

}
