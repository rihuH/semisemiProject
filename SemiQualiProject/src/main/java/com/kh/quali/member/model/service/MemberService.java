package com.kh.quali.member.model.service;

import javax.servlet.http.HttpSession;

import com.kh.quali.member.model.vo.EducationStatus;
import com.kh.quali.member.model.vo.Member;

public interface MemberService {

	// header 마이페이지 클릭 (마이페이지로 이동)
	void appicationRecord();
	
	// 회원가입
	void signUp(Member member);

	// 아이디 중복체크
	String checkId(String memberId);
	
	// 로그인
	Member loginMember(Member member);
	
	// 회원정보수정 (업데이트)
	void updateMember(Member member, HttpSession session);
	
	// 학력정보수정 (업데이트)
	EducationStatus selectMemberEducation(int memberNo);
	EducationStatus insertMemberEducation(EducationStatus educationStatus);
	void updateMemberEducation(EducationStatus educationStatus, HttpSession session);

	// 회원삭제
	void deleteMember(String memberPwd, HttpSession session);


	
	
}
