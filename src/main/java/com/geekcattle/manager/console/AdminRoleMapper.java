package com.geekcattle.manager.console;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.geekcattle.domain.entity.console.AdminRole;

public interface AdminRoleMapper  extends JpaRepository<AdminRole,Long> {
	
	@Query("from AdminRole l where l.roleId=?1")
	List<AdminRole> findByRoleId(String roleId);
	
//	@Query("from AdminRole l where l.AdminId=?1")
//	AdminRole findByAdminId(String AdminId);
	
	@Modifying
	int deleteByAdminId(String AdminId);
	
	@Modifying
	int deleteByAdminIdIn(String[] AdminIds);
	
	@Modifying
	int deleteByRoleIdIn(String[] RoleIds);
	
	@Modifying
	int deleteByRoleId(String RoleId);
}