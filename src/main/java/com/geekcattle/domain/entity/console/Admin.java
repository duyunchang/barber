
package com.geekcattle.domain.entity.console;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin  implements Serializable {//extends BaseEntity

	/**
	 * 
	 */
	private static final long serialVersionUID = 7539126739028335926L;

	@Id
	@Column(name = "uid")
	private String uid;

	@NotEmpty(message = "账号不能为空")
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	
	@Column(name = "state")
	private Integer state;
	
	@Column(name = "salt")
	private String salt;
	
	@Column(name = "is_system")
	private Integer isSystem;  //1为管理员，其他按权限查询
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "isdelete")//1已经删除，2未删除
	private Integer isDelete;
	
	
	@Transient
	private String[] roleId;

	@Transient
	private List<Role> roleList;

	
	
	
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
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

	
	/**
	 * 密码盐.
	 * 
	 * @return
	 */
	public String getCredentialsSalt() {
		return this.username + this.salt;
	}

	public String[] getRoleId() {
		return roleId;
	}

	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "Admin [uid=" + uid + ", username=" + username + ", password=" + password + ", salt=" + salt + ", state="
				+ state + ", isSystem=" + isSystem + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", roleId=" + Arrays.toString(roleId) + ", roleList=" + roleList + "]";
	}

}