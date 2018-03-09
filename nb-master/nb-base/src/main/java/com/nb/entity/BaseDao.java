/**
 * 
 */
package com.nb.entity;

import org.springframework.data.repository.Repository;


/**
 * @author willie
 *
 */
public interface BaseDao<T, ID> extends Repository<T, ID>{

}
