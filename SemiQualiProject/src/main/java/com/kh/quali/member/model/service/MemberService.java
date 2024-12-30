package com.kh.quali.member.model.service;

import javax.servlet.http.HttpSession;

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
	// ���� �ִ��� ������ Ȯ���غ���
	void selectMemberEducation();
	// ������ insert
	void insertMemberEducation();
	// ������ update
	void updateMemberEducation();

	// ȸ������
	void deleteMember();

	
	
}
