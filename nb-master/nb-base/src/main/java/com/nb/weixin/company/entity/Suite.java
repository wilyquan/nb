/**
 * 
 */
package com.nb.weixin.company.entity;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.nb.entity.DbEntity;
import com.nb.interfaces.Convertible;

/**
 * @author jewelvary
 *
 */
@Entity
@Table(name = "qy_suite")
public class Suite extends DbEntity {

	private String corpId;
	private String name;
	private String suiteId;
	private String suiteSecret;

	/**
	 * 
	 */
	public Suite() {
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getSuiteSecret() {
		return suiteSecret;
	}

	public void setSuiteSecret(String suiteSecret) {
		this.suiteSecret = suiteSecret;
	}

	public Suite convert(Map obj) {
		if (obj != null) {
			Suite suite = new Suite();
			suite.setSuiteId((String) obj.get("SuiteId"));
//			suite.setTicket((String) obj.get("SuiteTicket"));

			return suite;
		}

		return null;
	}

}
