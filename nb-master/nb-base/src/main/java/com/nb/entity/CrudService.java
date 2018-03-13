/**
 * 
 */
package com.nb.entity;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author willie
 * @param <V>
 *
 */
public abstract class CrudService<D extends BaseDao<T, ID>, T extends DbEntity, ID extends Serializable> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	/**
	 * 获得单条数据
	 * 
	 * @param id
	 * @return
	 */
	public T get(ID id) {
		return this.findById(id);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		Object id = entity.getId();
		if (id != null && id instanceof String) {
			return get((ID) id);
		}
		return null;
	}

	/**
	 * 获得所有数据
	 * 
	 * @return
	 */
	public Iterable<T> findAll() {
		return dao.findAll();
	}

	public Iterable<T> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	public Page<T> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	/**
	 * 删除所有的数据
	 */
	public void deleteAll() {
		dao.deleteAll();
	}

	/**
	 * 是否存在提供id的数据
	 * 
	 * @param id
	 * @return
	 */
	public boolean existsById(ID id) {
		return dao.existsById(id);
	}

	public void deleteAll(Iterable<? extends T> entities) {
		dao.deleteAll(entities);
	}

	public void delete(T entity) {
		dao.delete(entity);
	}

	public void deleteById(ID id) {
		dao.deleteById(id);
	}

	public void deleteById(T entity) {
		Object id = entity.getId();
		if (id != null && id instanceof String) {
			dao.deleteById((ID) id);
		}
	}

	public T save(T entity) {
		if (entity.isNewRecord()) {
			entity.preInsert();
		}else {
			entity.preUpdate();
		}
		return dao.save(entity);
	}

	public Iterable<T> saveAll(Iterable<T> entities) {
		return dao.saveAll(entities);
	}

	/**
	 * 总数量
	 * 
	 * @return
	 */
	public long count() {
		return dao.count();
	}

	/**
	 * 获得单条数据，通过id
	 * 
	 * @param id
	 * @return
	 */
	public T findById(ID id) {
		Optional<T> optional = dao.findById(id);
		if (optional != null) {
			return optional.get();
		}
		return null;
	}

}
