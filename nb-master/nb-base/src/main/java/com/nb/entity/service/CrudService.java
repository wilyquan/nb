/**
 * 
 */
package com.nb.entity.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.nb.entity.CrudDao;
import com.nb.entity.DbEntity;

/**
 * @author willie
 *
 */
public class CrudService<D extends CrudDao<T, ?>, T extends DbEntity<T>> extends BaseService {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	

}
