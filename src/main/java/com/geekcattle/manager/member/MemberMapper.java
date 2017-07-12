package com.geekcattle.manager.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.geekcattle.domain.entity.member.Member;

/**
 * author
 */
public interface MemberMapper extends JpaRepository<Member,String> {
	
	@Query("from Member m where m.account = ?1")
    Member selectByUsername(String username);
    
    @Query("select count(0) from Member m where m.account = ?1")
    int findByAccount(String account);
}
