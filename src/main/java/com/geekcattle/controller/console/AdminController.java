package com.geekcattle.controller.console;

import com.geekcattle.domain.entity.console.Admin;
import com.geekcattle.domain.entity.console.AdminRole;
import com.geekcattle.domain.entity.console.Role;
import com.geekcattle.service.console.AdminRoleService;
import com.geekcattle.service.console.AdminService;
import com.geekcattle.service.console.RoleService;
import com.geekcattle.util.*;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author 
 */
@Controller
@RequestMapping("/console/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private RoleService roleService;

    @RequiresPermissions("admin:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {
        return "console/admin/index";
    }

    @RequiresPermissions("admin:edit")
    @RequestMapping(value = "/from", method = {RequestMethod.GET})
    public String from(Admin admin, Model model) {
        String checkRoleId = "";
        if (!StringUtils.isEmpty(admin.getUid())) {
            admin = adminService.getById(admin.getUid());
            if (admin!=null&&!"null".equals(admin)) {
                AdminRole adminRole = new AdminRole();
                adminRole.setAdminId(admin.getUid());
                List<AdminRole> adminRoleLists = adminRoleService.getRoleList(adminRole);
                //admin.setCreatedAt(new Date());
                admin.setUpdatedAt(new Date());
                
                ArrayList<String> checkRoleIds = new ArrayList<String>();
                for (AdminRole adminRoleList : adminRoleLists) {
                    checkRoleIds.add(adminRoleList.getRoleId());
                }
                checkRoleId = String.join(",", checkRoleIds);
            }
        }else {
            admin.setIsSystem(0);
        }
        model.addAttribute("checkRoleId", checkRoleId);
        model.addAttribute("roleLists", this.getRoleList());
        model.addAttribute("admin", admin);
        return "console/admin/from";
    }

    private List<Role> getRoleList() {
        ModelMap map = new ModelMap();
        List<Role> roleList = roleService.getFromAll();
        return roleList;
    }

    @RequiresPermissions("admin:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(Admin admin) {
        ModelMap map = new ModelMap();
       // List<Admin> Lists = adminService.getPageList(admin);
        List<Admin> Lists = adminService. getPageList(admin);
        for (Admin list : Lists) {
            List<Role> rolelist = roleService.selectRoleListByAdminId(list.getUid());
            list.setRoleList(rolelist);
        }
        map.put("pageInfo", new PageInfo<Admin>(Lists));
        map.put("queryParam", admin);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @Transactional
    @RequiresPermissions("admin:save")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ModelMap save(@Valid Admin admin, BindingResult result) {
        try {
            if (result.hasErrors()) {
                for (ObjectError er : result.getAllErrors())
                    return ReturnUtil.Error(er.getDefaultMessage(), null, null);
            }	
           // System.out.println("admin parms="+admin.toString());
            admin.setCreatedAt(new Date());
            admin.setUpdatedAt(new Date());
            if (StringUtils.isEmpty(admin.getUid())) {
            	if (StringUtils.isEmpty(admin.getPassword())) {
                    return ReturnUtil.Error("密码不能为空", null, null);
                }else if(StringUtils.isEmpty(admin.getUsername())){
                	return ReturnUtil.Error("用户名不能为空", null, null);
                }
               
                Integer userCount = adminService.getCount(admin.getUsername());
                if (userCount > 0) {
                    return ReturnUtil.Error("用户名已存在", null, null);
                }
                
               
                admin=adminService.insert(admin);
            } else {
                Admin updateAdmin = adminService.getById(admin.getUid());
                if (!"null".equals(updateAdmin)) {
                   
                admin= adminService.save(admin,updateAdmin);
                
                } else {
                    return ReturnUtil.Error("操作失败", null, null);
                }
            }
            
            if (admin.getRoleId() != null) {
                adminRoleService.deleteAdminId(admin.getUid());
                for (String roleid : admin.getRoleId()) {
                    AdminRole adminRole = new AdminRole();
                    adminRole.setAdminId(admin.getUid());
                    adminRole.setRoleId(roleid);
                    adminRoleService.insert(adminRole);
                }
            }else{
                adminRoleService.deleteAdminId(admin.getUid());
            }
            return ReturnUtil.Success("操作成功", null, "/barber/console/admin/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }
    
    @Transactional
    @RequiresPermissions("admin:editpwd")
    @RequestMapping(value = "/savepwd", method = {RequestMethod.POST})
    @ResponseBody
    public ModelMap editPwd(String uid, String password) {
        try {
            if (StringUtils.isNotEmpty(uid) && StringUtils.isNotEmpty(password)) {
                Admin admin = adminService.getById(uid);
                if (!"null".equals(admin)) {
                    String newPassword = PasswordUtil.createAdminPwd(password, admin.getSalt());
                    Admin pwdAdmin = new Admin();
                    pwdAdmin.setPassword(newPassword);
                   // Example example = new Example(Admin.class);
                   // example.createCriteria().andCondition("uid", uid);
                    adminService.updateExample(newPassword, uid);
                    return ReturnUtil.Success("操作成功", null, null);
                } else {
                    return ReturnUtil.Error("对像不存在，修改失败", null, null);
                }
            } else {
                return ReturnUtil.Error("参数错误，修改失败", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("修改失败", null, null);
        }
    }

    @Transactional
    @RequiresPermissions("admin:delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap delete(String[] ids) {
        try {
            if (ids != null) {
                if (StringUtils.isNotBlank(ids.toString())) {
                	
                	adminRoleService.deleteAdminIds(ids);
                	adminService.deleteByUid(ids);
//                   for (String id : ids) {
//                        
//                        adminService.deleteById(id);
//                    }
                }
                return ReturnUtil.Success("删除成功", null, null);
            } else {
                return ReturnUtil.Error("删除失败", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败", null, null);
        }
    }
    public static void main(String[] args) {
    	String admin=null;
    
    	if (!"null".equals(admin)) {
    		System.out.println("xxxx");
    	}
	}

}
