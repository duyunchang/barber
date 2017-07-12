
package com.geekcattle.domain.entity.console;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu  implements Serializable {//extends BaseEntity

	/**
	 * 
	 */
	private static final long serialVersionUID = 7921890302086240376L;

	@Id
	@Column(name = "menu_id")
	private String menuId;

	@NotEmpty(message = "菜单名称不能为空")
	@Column(name = "menu_name")
	private String menuName;

	@Column(name = "menu_type", columnDefinition = "enum('menu','auth','button')")//'资源类型，菜单或都按钮(menu,button)',
	private String menuType;

	@NotEmpty(message = "菜单URL不能为空")
	@Column(name = "menu_url")
	private String menuUrl;

	@NotEmpty(message = "菜单标识不能为空")
	@Column(name = "menu_code")
	private String menuCode;

	@NotEmpty(message = "父类ID不能为空")
	@Column(name = "parent_id")
	private String parentId;
	
	@Column(name = "parent_ids")
	private String parentIds;
	
	@Column(name = "child_num")
	private Integer childNum;
	
	@Column(name = "listorder")
	private Integer listorder;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

	@Transient
	private List<Menu> children;

	@Transient
	private List<Role> roleList;

	@Transient
	private List<Admin> adminList;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public Integer getListorder() {
		return listorder;
	}

	public void setListorder(Integer listorder) {
		this.listorder = listorder;
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

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", menuType=" + menuType + ", menuUrl=" + menuUrl
				+ ", menuCode=" + menuCode + ", parentId=" + parentId + ", parentIds=" + parentIds + ", childNum="
				+ childNum + ", listorder=" + listorder + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", children=" + children + ", roleList=" + roleList + ", adminList=" + adminList + "]";
	}

}