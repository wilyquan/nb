/**
 * 
 */
package com.nb.weixin.company.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.nb.entity.DbEntity;
import com.nb.utils.JSONUtil;

/**
 * 套件指令
 * 
 * @author jewelvary
 *
 */
@Entity
@Table(name = "qy_suite_order")
public class SuiteOrder extends DbEntity<SuiteOrder> {
	private String suiteId;
	private String infoType;
	private Date timestamp;
	private String corpId;
	private String content;

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SuiteOrder convert(Map obj) {
		if (obj != null) {
			SuiteOrder suiteOrder = new SuiteOrder();
			suiteOrder.setSuiteId((String) obj.get("SuiteId"));
			suiteOrder.setInfoType((String) obj.get("InfoType"));

			String timestamp = (String) obj.get("TimeStamp");
			if (timestamp != null) {
				suiteOrder.setTimestamp(new Date(Long.parseLong(timestamp) * 1000L));
			}

			String authCorpId = (String) obj.get("AuthCorpId");
			if (authCorpId != null) {
				suiteOrder.setCorpId(authCorpId);
			}

			suiteOrder.setContent(JSONUtil.toJson(obj));

			return suiteOrder;
		}

		return null;
	}

	public static void main(String[] args) {
		Date date = new Date(1520932497000L);
		System.out.println(date);
		System.out.println(System.currentTimeMillis());
	}

}
