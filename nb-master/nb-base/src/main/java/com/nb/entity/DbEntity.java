/**
 * 
 */
package com.nb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.nb.sys.entity.User;
import com.nb.utils.IdGen;
import com.nb.utils.StringUtils;

/**
 * db对象基类
 * 
 * @author willie
 *
 */
@MappedSuperclass
public class DbEntity<T> extends com.nb.entity.Entity{

	@Id
	protected String id;

	protected String remarks; // 备注
	
	protected User createBy; // 创建者
	@Column(nullable = false)
	protected Date createDate; // 创建日期
	protected User updateBy; // 更新者
	@Column(nullable = false)
	protected Date updateDate; // 更新日期

	@Column(nullable = false)
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	@Transient
	protected boolean isNewRecord = false;

	/**
	 * 
	 */
	public DbEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
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

	public boolean isNewRecord() {
		return isNewRecord || StringUtils.isBlank(getId());
	}

	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert() {
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord) {
			setId(IdGen.uuid());
		}
//		User user = UserUtils.getUser();
//		if (StringUtils.isNotBlank(user.getId())) {
//			this.updateBy = user;
//			this.createBy = user;
//		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate() {
//		User user = UserUtils.getUser();
//		if (StringUtils.isNotBlank(user.getId())) {
//			this.updateBy = user;
//		}
		this.updateDate = new Date();
	}
	
	/**
	 * 删除标记（0：正常；1：删除；2：审核；3：不完整）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	public static final String DEL_FLAG_INCOMPLETE = "3";
}
