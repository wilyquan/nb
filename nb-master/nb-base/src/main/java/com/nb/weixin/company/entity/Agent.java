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
 * 授权方应用信息
 * 
 * @author jewelvary
 *
 */
@Entity
@Table(name = "qy_agent")
public class Agent extends DbEntity {

	private String suiteId;
	private String corpId;
	private String agentId;
	private String name;
	private String squareLogoUrl;

	private String roundLogoUrl;
	private int level;
	private String allowParty;
	private String allowTag;
	private String allowUser;
	private String extraParty;
	private String extraUser;
	private String extraTag;

	/**
	 * 
	 */
	public Agent() {
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

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAllowParty() {
		return allowParty;
	}

	public void setAllowParty(String allowParty) {
		this.allowParty = allowParty;
	}

	public String getAllowTag() {
		return allowTag;
	}

	public void setAllowTag(String allowTag) {
		this.allowTag = allowTag;
	}

	public String getAllowUser() {
		return allowUser;
	}

	public void setAllowUser(String allowUser) {
		this.allowUser = allowUser;
	}

	public String getExtraParty() {
		return extraParty;
	}

	public void setExtraParty(String extraParty) {
		this.extraParty = extraParty;
	}

	public String getExtraUser() {
		return extraUser;
	}

	public void setExtraUser(String extraUser) {
		this.extraUser = extraUser;
	}

	public String getExtraTag() {
		return extraTag;
	}

	public void setExtraTag(String extraTag) {
		this.extraTag = extraTag;
	}

}
