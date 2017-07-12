
package com.geekcattle.domain.entity.member;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.geekcattle.domain.entity.BaseEntity;

/**
 * author 
 */
@Entity
@Table(name = "member")
public class Member extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3057468652938529635L;

	@Id
	@Column(name = "uid")
	private String uid;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "salt")
	private String salt;
	
	@Column(name = "state")
	private Integer state;//0账号锁定
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Member [uid=" + uid + ", account=" + account + ", password=" + password + ", salt=" + salt + ", state="
				+ state + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
