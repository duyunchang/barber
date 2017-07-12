
package com.geekcattle.domain.entity.console;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "admin_role")
public class AdminRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -613389389291147609L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	
	
	@Column(name = "admin_id")
	private String adminId;

	@Column(name = "role_id")
	private String roleId;

	/**
	 * @return admin_id
	 */
	public String getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return role_id
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AdminRole [id=" + id + ", adminId=" + adminId + ", roleId=" + roleId + "]";
	}

	

}