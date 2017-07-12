
package com.geekcattle.service.console;

import com.geekcattle.domain.entity.console.AdminRole;
import com.geekcattle.manager.console.AdminRoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * author 
 */
@Service
public class AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    public void insert(AdminRole adminRole){
        adminRoleMapper.save(adminRole);
    }

    
    public void deleteAdminIds(String[] ids){
        
    	adminRoleMapper.deleteByAdminIdIn(ids);
    }

    public void deleteRoleIds(String[] ids){
       
    	adminRoleMapper.deleteByRoleIdIn(ids);    	
    }
    public int deleteRoleId(String role_id){
        
    	return adminRoleMapper.deleteByRoleId(role_id);   	
    }
    
    public int  deleteAdminId(String uid){
    	
    	return adminRoleMapper.deleteByAdminId(uid);
    }
   

    public List<AdminRole> getRoleList(AdminRole adminRole){
    	
    	 //return adminRoleMapper.findAll(adminRole);
    	
    	return adminRoleMapper.findByRoleId(adminRole.getRoleId());
    	
       
    }


}
