/**
 * 
 */
package com.nb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * db对象基类
 * @author willie
 *
 */
@MappedSuperclass  
public class DbEntity<T> extends com.nb.entity.Entity {

	@Id
	protected String id;

	protected String remarks; // 备注
	// protected User createBy; // 创建者
	@Column(nullable = false)
	protected Date createDate; // 创建日期
	// protected User updateBy; // 更新者
	
	@Column(nullable = false)
	protected Date updateDate; // 更新日期
	
	@Column(nullable = false)
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

	/**
	 * 
	 */
	public DbEntity() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
