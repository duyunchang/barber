package com.geekcattle.manager.console;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.geekcattle.domain.entity.console.Role;

/**
 * author 
 */
public interface RoleMapper extends JpaRepository<Role,String> {
    
	@Query("from Role r where r.enable=?1 ")
    List<Role> findAllByEnable(Integer enable);
	@Query("from Role r where 1=1 ")
	List<Role> findAllpage(Pageable pageable);
	@Query("select count (0) from Role r where roleName=?1 ")
	int findCountByRoleName(String roleName);
	
	@Modifying
	int deleteByRoleIdIn(String[] roleId);
	
	
	
//	@Query("from Role r inner join AdminRole ar on r.roleId = ar.roleId left join Admin a on a.uid = ar.adminId  where a.uid = ?1 and r.enable = 1")
//	List<Role> selectRoleListByAdminId(String Id);
}
