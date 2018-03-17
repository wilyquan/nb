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
 * 
 * 授权方企业信息
 * 
 * @author jewelvary
 *
 */
@Entity
@Table(name = "qy_corp")
public class Corp extends DbEntity {

	private String name;
	private String fullName;
	private String corpId;
	private String type;
	private String squareLogoUrl;

	private String roundLogoUrl;
	private String subjectType;
	private String verifiedEndTime;
	private Long wxqrcode;
	private Integer userMax;
	private Integer agentMax;

	/**
	 * 
	 */
	public Corp() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSquareLogoUrl() {
		return squareLogoUrl;
	}

	public void setSquareLogoUrl(String squareLogoUrl) {
		this.squareLogoUrl = squareLogoUrl;
	}

	public String getRoundLogoUrl() {
		return roundLogoUrl;
	}

	public void setRoundLogoUrl(String roundLogoUrl) {
		this.roundLogoUrl = roundLogoUrl;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getVerifiedEndTime() {
		return verifiedEndTime;
	}

	public void setVerifiedEndTime(String verifiedEndTime) {
		this.verifiedEndTime = verifiedEndTime;
	}

	public Long getWxqrcode() {
		return wxqrcode;
	}

	public void setWxqrcode(Long wxqrcode) {
		this.wxqrcode = wxqrcode;
	}

	public Integer getUserMax() {
		return userMax;
	}

	public void setUserMax(Integer userMax) {
		this.userMax = userMax;
	}

	public Integer getAgentMax() {
		return agentMax;
	}

	public void setAgentMax(Integer agentMax) {
		this.agentMax = agentMax;
	}

}
