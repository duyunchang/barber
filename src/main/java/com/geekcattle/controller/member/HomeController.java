package com.geekcattle.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geekcattle.conf.shiro.AdminShiroUtil;
import com.geekcattle.domain.entity.member.Member;

/**
 * author 
 */
@Controller
@RequestMapping("/member")
public class HomeController {

    @RequestMapping("/index")
    public String index(Model model){
        //Member member = (Member) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
    	Member member = AdminShiroUtil.getMemberInfo();
    	if(member!=null&&!"null".equals(member)){
	    	String account = member.getAccount();
	        model.addAttribute("account", account);
	        return "member/home";
    	}
    	return "index";
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        try {
        	
            //Member member = (Member) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        	Member member = AdminShiroUtil.getMemberInfo();
            //System.out.println(member);
        	if(member!=null&&!"null".equals(member)){
                return "redirect:/barber/member/index";//重回定向到一个页面
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "member/login";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String reg(){

        try {
            //Member member = (Member) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        	Member member = AdminShiroUtil.getMemberInfo();
        	if(member!=null&&!"null".equals(member)){
                return "redirect:/barber/member/index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "member/reg";
    }

}
