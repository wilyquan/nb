/**
 * 
 */
package com.nb.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.nb.entity.DbEntity;

/**
 * @author willie
 *
 */
@Entity
@Table(name="wx_user")
public class User extends DbEntity<User>{

	@Column(nullable = false)
	String name;
	@Column(nullable = false)
	String mail;
	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	

}
