/**
 * 
 */
package com.nb.entity;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author willie
 *
 */
@NoRepositoryBean
public interface BaseDao<T extends DbEntity, ID extends Serializable> extends PagingAndSortingRepository<T, ID>{

	
	
}
