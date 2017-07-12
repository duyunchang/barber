
package com.geekcattle.service.console;

import com.geekcattle.Constants.Constants;
import com.geekcattle.domain.entity.BaseEntity;
import com.geekcattle.domain.entity.console.Role;
import com.geekcattle.manager.BaseNativeSqlRepository;
import com.geekcattle.manager.console.RoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author 
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private BaseNativeSqlRepository  BaseNativeSqlManager;
    
    @Autowired 
    private BaseEntity baseEntity;

    public List<Role> getPageList(Role role) {
    	Sort sort = new Sort(Sort.Direction.DESC, "createdAt");  
    	Pageable pageable = new PageRequest(baseEntity.getOffset(), baseEntity.getLimit(), sort); 
        //PageHelper.offsetPage(role.getOffset(), role.getLimit(), CamelCaseUtil.toUnderlineName(role.getSort())+" "+role.getOrder());
    	List<Role> content =roleMapper.findAllpage(pageable);
    	
    	//List<Role> content = roleMapper.findAll(pageable).getContent();
    	return content;
        
    }

    public List<Role> getFromAll(){
//        Example example = new Example;
//        example.createCriteria()
//                .andCondition("enable = ", 1);
//
//        return roleMapper.selectByExample(example);
        
        return roleMapper.findAllByEnable(1);
    }

//    public Integer getCount(Example example){
//        return roleMapper.selectCountByExample(example);
//    }

    public Role getById(String id) {
    	
    	
        return roleMapper.findOne(id);
    }

  
    public void deleteById(String id) {
        roleMapper.delete(id);
    }
    
//    public void deleteByIds(String[] id) {
//        roleMapper.deleteAllInId(id);
//    }

    public void deleteByIdsIn(String[] ids) {
        roleMapper.deleteByRoleIdIn(ids);
    }
    
    
    public int save(Role role) {
    	int flag=Constants.update_fail;
    	
    	if(Constants.update_fail==roleMapper.findCountByRoleName(role.getRoleName())){    		
    		 roleMapper.save(role);  		 
    		 flag=Constants.update_success;
    	}
       return flag;
    }

    
    public int insert(Role role){
        Role save = roleMapper.save(role);
        if(save==null){
        	return Constants.update_fail;
        }
        return Constants.update_success;
    }

    public Set<String> findRoleByUserId(String userId) {
    	List<String> findRoleByUserId = BaseNativeSqlManager.findRoleByUserId(userId);
    	Set<String>  set=new HashSet<String>();
    	set.addAll(findRoleByUserId);
    	
        return set;
    }

    public List<Role> selectRoleListByAdminId(String Id){
        return BaseNativeSqlManager.selectRoleListByAdminId(Id);
        
       // return  roleMapper.selectRoleListByAdminId(Id);
    }

}
