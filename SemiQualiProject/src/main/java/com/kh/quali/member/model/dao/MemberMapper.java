package com.kh.quali.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member login(Member member);
	
	void signUp(Member member);
	
	int checkId(String memberId);

	void updateMember(Member member);
	
	void deleteMember(Member member);

}
