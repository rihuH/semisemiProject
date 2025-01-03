package com.kh.quali.member.model.service;

import javax.servlet.http.HttpSession;

import com.kh.quali.member.model.vo.EducationStatus;
import com.kh.quali.member.model.vo.Member;

public interface MemberService {

	// header ���������� Ŭ�� (������������ �̵�)
	void appicationRecord();
	
	// ȸ������
	void signUp(Member member);

	// ���̵� �ߺ�üũ
	String checkId(String memberId);
	
	// �α���
	Member loginMember(Member member);
	
	// ȸ���������� (������Ʈ)
	void updateMember(Member member, HttpSession session);
	
	// �з��������� (������Ʈ)
	EducationStatus selectMemberEducation(int memberNo);
	EducationStatus insertMemberEducation(EducationStatus educationStatus);
	void updateMemberEducation(EducationStatus educationStatus, HttpSession session);

	// ȸ������
	void deleteMember(String memberPwd, HttpSession session);


	
	
}
