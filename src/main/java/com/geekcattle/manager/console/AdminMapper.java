package com.geekcattle.manager.console;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.geekcattle.domain.entity.console.Admin;

/**
 * author 
 */
public interface AdminMapper  extends JpaRepository<Admin ,String> {
    
	@Query("from Admin l where l.username=?1")
	Admin selectByUserName(String username);
    
	@Query("select count(0) from Admin l where l.username=?1")
	int selectCountByUserName(String username);
	
    @Modifying 
    @Query("update  Admin l  set l.password =?1  where l.uid=?2")
    int updatePasswordByUid(String password,String uid);
    
    @Modifying 
    int  deleteByUidIn(String[] uid);
}
