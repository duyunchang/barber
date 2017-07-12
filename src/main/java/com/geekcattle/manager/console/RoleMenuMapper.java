package com.geekcattle.manager.console;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.geekcattle.domain.entity.console.RoleMenu;

public interface RoleMenuMapper extends JpaRepository<RoleMenu,Long> {
	
	 @Query("from RoleMenu r where r.roleId=?1 ")
	 List<RoleMenu> findByRoleId(String roleId);
	 
	 @Modifying 
	 int deleteByRoleId(String roleId);
	 
	 @Modifying 
	 int deleteByRoleIdIn(String[] roleId);
	 
	 @Modifying 
	 int deleteByMenuId(String menuId);
	 
	 @Modifying 
	 int deleteByMenuIdIn(String[] menuId);
	 
	 
}