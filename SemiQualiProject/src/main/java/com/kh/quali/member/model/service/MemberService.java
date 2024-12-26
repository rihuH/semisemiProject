package com.kh.quali.member.model.service;

import javax.servlet.http.HttpSession;

import com.kh.quali.member.model.vo.Member;

public interface MemberService {

	// header ���������� Ŭ�� (������������ �̵�)
	void appicationRecord();
	
	// ȸ������
	void insertMember();
	
	// �α���
	Member loginMember(Member member);
	
	// ȸ���������� (������Ʈ)
	void updateMember(Member member, HttpSession session);
	
	// �з��������� (������Ʈ)
	void updateMemberEducation();

	// ȸ������
	void deleteMember();
	
	
}
