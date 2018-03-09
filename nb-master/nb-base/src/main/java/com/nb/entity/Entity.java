/**
 * 
 */
package com.nb.entity;

import java.io.Serializable;
import java.util.Map;

import com.nb.interfaces.Jsonable;
import com.nb.interfaces.Mapable;
import com.nb.utils.JSONUtil;

/**
 * @author willie
 *
 */
public class Entity implements Jsonable, Mapable, Serializable {

	/**
	 * 将model转成json字符串
	 *
	 * @return json字符串
	 */
	@Override
	public String toJsonString() {
		return JSONUtil.toJson(this);
	}

	@Override
	public Map toMap() {
		return JSONUtil.toMap(this.toJsonString());
	}

}
