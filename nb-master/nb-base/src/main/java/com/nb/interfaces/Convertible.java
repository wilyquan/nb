/**
 * 
 */
package com.nb.interfaces;

/**
 * 
 * 可转换的
 * @author jewelvary
 *
 */
public interface Convertible<T, V> {
	/**
	 * 将一个对象转变为新的对象。
	 * 
	 * @param obj
	 * @return
	 */
	T convert(V obj);
}
