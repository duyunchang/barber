
package com.geekcattle.service.console;

import com.geekcattle.domain.entity.BaseEntity;
import com.geekcattle.domain.entity.console.Admin;
import com.geekcattle.manager.BaseNativeSqlRepository;
import com.geekcattle.manager.console.AdminMapper;
import com.geekcattle.manager.console.AdminRoleMapper;
import com.geekcattle.util.PasswordUtil;
import com.geekcattle.util.UuidUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
/**
 * author 
 */
@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private BaseNativeSqlRepository  BaseNativeSqlManager;
    
    @Autowired 
    private BaseEntity baseEntity;

    public List<Admin> getPageList(Admin admin) {
    	Sort sort = new Sort(Sort.Direction.DESC, "createdAt");  
    	Pageable pageable = new PageRequest(baseEntity.getOffset(), baseEntity.getLimit(), sort); 
    	
        //PageHelper.offsetPage(admin.getOffset(), admin.getLimit(), CamelCaseUtil.toUnderlineName(admin.getSort())+" "+admin.getOrder());
        return adminMapper.findAll(pageable).getContent();
    }
    
    public Page<Admin> getPageListNew(Admin admin) {
    	Sort sort = new Sort(Sort.Direction.DESC, "createdAt");  
    	Pageable pageable = new PageRequest(baseEntity.getOffset(), baseEntity.getLimit(), sort); 
    	
        //PageHelper.offsetPage(admin.getOffset(), admin.getLimit(), CamelCaseUtil.toUnderlineName(admin.getSort())+" "+admin.getOrder());
        return adminMapper.findAll(pageable);
    }

    public int getCount(String username){
    	return  adminMapper.selectCountByUserName(username);
    	
        //return adminMapper.selectCountByExample(example);
    }

    public Admin getById(String id) {
        return adminMapper.findOne(id);
    }

    public Admin findByUsername(String username) {
        return adminMapper.selectByUserName(username);
    }

//    public void deleteById(String id) {
//    	
//    	adminRoleMapper.deleteByAdminId(id);
//    	adminMapper.delete(id);
//    	//BaseNativeSqlManager.deleteById( id);;
//        //adminMapper.deleteById( id);
//    }
    
    public void deleteByUid(String[] ids) {
    	
    	//adminRoleMapper.deleteByAdminId(id);
    	adminMapper.deleteByUidIn(ids);
    	//BaseNativeSqlManager.deleteById( id);;
        //adminMapper.deleteById( id);
    }

    public Admin insert(Admin admin){//新添加用户
    	
    	
    	 String Id = UuidUtil.getUUID();
         admin.setUid(Id);
         String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
         admin.setSalt(salt);
         String password = PasswordUtil.createAdminPwd(admin.getPassword(), admin.getCredentialsSalt());
         admin.setPassword(password);
         admin.setIsSystem(0);
         admin.setCreatedAt(new Date());
         admin.setUpdatedAt(new Date());
         
         
        adminMapper.save(admin);
        return admin;
    }

    public Admin save(Admin admin, Admin updateAdmin) {//更新存在用户
    	
    	 admin.setSalt(updateAdmin.getSalt());
         if (!StringUtils.isEmpty(admin.getPassword())) {
             String password = PasswordUtil.createAdminPwd(admin.getPassword(), updateAdmin.getCredentialsSalt());
             admin.setPassword(password);
         } else {
             admin.setPassword(updateAdmin.getPassword());
         }
         admin.setUpdatedAt(new Date());
         
    	adminMapper.save(admin);
    	
    	 return admin;
    }

    public void updateExample(String newPassword,String uid){
    	
        adminMapper.updatePasswordByUid(newPassword, uid);
    }



}
