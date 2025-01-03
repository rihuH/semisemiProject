package com.kh.quali.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.member.model.vo.EducationStatus;
import com.kh.quali.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member login(Member member);
	
	void signUp(Member member);
	
	int checkId(String memberId);

	void updateMember(Member member);
	
	int selectMemberEducation(int memberNo);
	
	void insertMemberEducation(int memberNo, EducationStatus education);
	
	void updateMemberEducation(int memberNo, EducationStatus education);
	
	void deleteMember(Member member);

}
