
package com.geekcattle.service.console;

import com.geekcattle.domain.entity.console.RoleMenu;
import com.geekcattle.manager.console.RoleMenuMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author 
 */
@Service
public class RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    public void insert(RoleMenu roleMenu){
        roleMenuMapper.save(roleMenu);
    }

    public void deleteMenuIds(String[] ids){

      roleMenuMapper. deleteByMenuIdIn(ids);
      
    }
    
    public void deleteMenuId(String id){
//        Example example = new Example(RoleMenu.class);
//        example.createCriteria().andCondition("menu_id =", id);
//        roleMenuMapper.deleteByExample(example);
        roleMenuMapper. deleteByMenuId(id);
        
    }

    public void deleteRoleId(String id){
//        Example example = new Example(RoleMenu.class);
//        example.createCriteria().andCondition("role_id =", id);
//        roleMenuMapper.deleteByExample(example);
        
        roleMenuMapper.deleteByRoleId(id);
    }

//    public RoleMenu selectOne(RoleMenu roleMenu){
//        
//    	
//    	return roleMenuMapper.findByRoleId(roleMenu.getRoleId());
//    }

    public List<RoleMenu> getRoleList(RoleMenu roleMenu){
    	
        return roleMenuMapper.findByRoleId(roleMenu.getRoleId());
    }

    

}
