package com.kh.quali.member.model.service;

import org.springframework.stereotype.Component;

import com.kh.quali.exception.UserIdNotFoundException;
import com.kh.quali.member.model.dao.MemberMapper;
import com.kh.quali.member.model.vo.Member;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor // fianlŰ���带 ����� ��ü�� �����ڸ� ��������
public class MemberValidator {

	// Member ���� ������ ���� Ŭ����
	private final MemberMapper mapper;
	
	// �߻� �� �� �ִ� ���ܻ�Ȳ
	// 1. ȸ���� �������� �ʴ´ٸ�?
	// 2. ���̵�, Ȥ�� ��й�ȣ�� Ʋ�ȴٸ�? 
	// (��й�ȣ�� Ʋ����� PasswordEncryptor ���� �Ÿ����� �س��⶧���� validator������ �� ���� ����.)
	
	public Member validatorMemberExist(Member member) {

		// Service���� �ѱ� member ���� ���� DB�� MEMBER���̺�� �� ��ȸ �� ��� ���
		Member existingMember = mapper.login(member);
		
		if(existingMember != null) {
			return existingMember;
		} // ���ƿ°� ������� �����ش�.
		
		// ���ƿ°� ���ٸ� ���ܹ߻���Ű��.
		throw new UserIdNotFoundException("���̵� �߸��Է��ϼ̽��ϴ�");
	}
	
	
	
}
