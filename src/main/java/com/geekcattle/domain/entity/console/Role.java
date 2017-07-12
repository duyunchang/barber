
package com.geekcattle.domain.entity.console;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * author 
 */
@Entity
@Table(name = "role")
public class Role  implements Serializable {//extends BaseEntity

	/**
	 * 
	 */
	private static final long serialVersionUID = -8282261354486090072L;

	@Id
	@Column(name = "role_id")
	private String roleId;

	@NotEmpty(message = "角色名称不能为空")
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "role_desc")
	private String roleDesc;

	@Column(name = "enable", columnDefinition = "enum(1,0)")//
	private Integer enable;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Transient
	private Admin admin;
	
	 //角色 -- 权限关系：多对多关系;
    @Transient
    private List<Menu> menuList;

    // 用户 - 角色关系定义;

    @Transient
    private List<Admin> adminList;// 一个角色对应多个用户*/

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", enable=" + enable
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", admin=" + admin + ", menuList="
				+ menuList + ", adminList=" + adminList + "]";
	}
    


}
