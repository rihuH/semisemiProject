package com.kh.quali.member.model.service;

import org.springframework.stereotype.Component;

import com.kh.quali.exception.TooLargeValueException;
import com.kh.quali.exception.UserFoundException;
import com.kh.quali.exception.UserIdNotFoundException;
import com.kh.quali.member.model.dao.MemberMapper;
import com.kh.quali.member.model.vo.Member;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor // fianlŰ���带 ����� ��ü�� �����ڸ� ��������
public class MemberValidator {

	// Member ���� ������ ���� Ŭ����
	private final MemberMapper mapper;
	
	
	// ȸ�����Կ��� �߻��� �� �ִ� ���ܻ�Ȳ
	// 1. ID�ߺ� (UNIQUE)
	// 2. ID�� PWD���� ������ ������ Ŀ�����
	
	// 1. ID�ߺ��� ���
	public void validateDuplicateMember(Member member) {
		Member existingMember = mapper.login(member);
		if(existingMember != null && member.getMemberId().equals(existingMember.getMemberId())) {
			// existingMember != null == ���� ���� ��
			// member.getMemberId ����ڰ� �Է��� ���� DB�� �ִ� ���� ��ġ�ϸ�
			// �̹� �����ϴ� ���̵��̹Ƿ� �߻��� ����
			throw new UserFoundException("�̹� �����ϴ� ���̵�");
		}
	}
	
	// 2. ���� ���ص� ũ�⺸�� Ŭ ���
	public void validateIdLength(Member member) {
		if(member.getMemberId().length() > 30) {
			throw new TooLargeValueException("���̵� �Է� �� �� �ִ� ũ�⸦ �Ѿ����ϴ�.");
		}
	}
	
	// ȸ�������� ���� ���� �޼ҵ�
	public void validateJoinMember(Member member) {
		validateDuplicateMember(member);
		validateIdLength(member);
	}
	
	
	
	
	// �α��ο��� �߻� �� �� �ִ� ���ܻ�Ȳ
	// 1. ȸ���� �������� �ʴ´ٸ�?
	// 2. ���̵�, Ȥ�� ��й�ȣ�� Ʋ�ȴٸ�? 
	// (��й�ȣ�� Ʋ����� PasswordEncryptor ���� �Ÿ����� �س��⶧���� validator������ �� ���� ����.)
	
	public Member validateMemberExist(Member member) {

		// Service���� �ѱ� member ���� ���� DB�� MEMBER���̺�� �� ��ȸ �� ��� ���
		Member existingMember = mapper.login(member);
		
		if(existingMember != null) {
			return existingMember;
		} // ���ƿ°� ������� �����ش�.
		
		// ���ƿ°� ���ٸ� ���ܹ߻���Ű��.
		throw new UserIdNotFoundException("���̵� �߸��Է��ϼ̽��ϴ�");
	}
	
	
	
}
