
package com.geekcattle.service.member;

import com.geekcattle.domain.entity.member.Member;
import com.geekcattle.manager.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author 
 */
@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public List<Member> getPageList(Member member) {
    	Sort sort = new Sort(Sort.Direction.DESC, "createdAt");  
    	Pageable pageable = new PageRequest(member.getOffset(), member.getLimit(), sort); 
    	return memberMapper.findAll(pageable).getContent();
        //PageHelper.offsetPage(member.getOffset(), member.getLimit(), CamelCaseUtil.toUnderlineName(member.getSort())+" "+member.getOrder());
        //return memberMapper.selectAll();
    }

    public int getCount(String account){
    	return memberMapper.findByAccount(account);
        //return memberMapper.selectCountByExample(example);
    }

    public Member findByUsername(String username) {
        return memberMapper.selectByUsername(username);
    }

   
    public void insert(Member member){
        memberMapper.save(member);
    }
}
