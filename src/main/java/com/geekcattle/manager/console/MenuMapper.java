package com.geekcattle.manager.console;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.geekcattle.domain.entity.console.Menu;

public interface MenuMapper extends JpaRepository<Menu,String> {
	
   @Query("select count(0) from Menu l where l.parentId=?1")
   int findCountByParentId(String parent_id);
   
   @Query("from Menu l where l.parentId=?1 order by listorder asc, createdAt desc")
   List<Menu>  findByParentId(String parent_id);
    
    @Modifying 
    @Query("update  Menu l  set l.listorder =?1  where l.menuId=?2")
    int updatelistorderByid(Integer listorder, String id);
    
    @Modifying 
    int deleteByMenuIdIn(String[] menuIds);
    
    @Query("select m.menuCode from Menu m where 1=1")
    Set<String> getALLMenuCode();
}