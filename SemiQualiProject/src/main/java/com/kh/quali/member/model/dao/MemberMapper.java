package com.kh.quali.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member login(Member member);
	
	void signUp(Member member);
	
	int checkId(String memberId);

	void updateMember(Member member);
	
<<<<<<< Updated upstream
=======
	void selectMemberEducation(int memberNo);
	
	void insertMemberEducation();
	
	void updateMemberEducation();
	
>>>>>>> Stashed changes
	void deleteMember(Member member);

}
