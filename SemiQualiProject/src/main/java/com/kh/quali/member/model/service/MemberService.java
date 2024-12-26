package com.kh.quali.member.model.service;

import javax.servlet.http.HttpSession;

import com.kh.quali.member.model.vo.Member;

public interface MemberService {

	// header 마이페이지 클릭 (마이페이지로 이동)
	void appicationRecord();
	
	// 회원가입
	void insertMember();
	
	// 로그인
	Member loginMember(Member member);
	
	// 회원정보수정 (업데이트)
	void updateMember(Member member, HttpSession session);
	
	// 학력정보수정 (업데이트)
	void updateMemberEducation();

	// 회원삭제
	void deleteMember();
	
	
}
