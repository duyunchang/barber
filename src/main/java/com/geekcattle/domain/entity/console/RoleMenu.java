
package com.geekcattle.domain.entity.console;


import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "role_menu")
public class RoleMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -932632647409728103L;


	@Id
	@GeneratedValue
	@Column(name = "id")	
	private Long id;
	
	
	@Column(name = "role_id")
	private String roleId;

	@Column(name = "menu_id")
	private String menuId;

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

	/**
	 * @return menu_id
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RoleMenu [id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + "]";
	}

	

}