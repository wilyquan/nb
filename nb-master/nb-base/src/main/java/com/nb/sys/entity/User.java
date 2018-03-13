/**
 * 
 */
package com.nb.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nb.entity.DbEntity;

/**
 * @author willie
 *
 */
@Entity
@Table(name="sys_user")
public class User extends DbEntity<User>{
//public class User{

	@Column(nullable = false)
	String name;
	String email;
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
	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
