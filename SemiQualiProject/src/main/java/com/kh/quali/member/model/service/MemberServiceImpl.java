package com.kh.quali.member.model.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kh.quali.exception.ComparePasswordException;
import com.kh.quali.member.model.dao.MemberMapper;
import com.kh.quali.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableTransactionManagement
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper mapper;
	private final PasswordEncryptor	passwordEncoder;
	private final MemberValidator validator;
	
	@Override
	public void appicationRecord() {
		
	}

	@Override
	public Member loginMember(Member member) {
		
		// ���̵� ������ validator���� �ñ��.
		Member loginMember = validator.validatorMemberExist(member);
		
		// ���� loginUser�� ���ƿԴٸ�, ��й�ȣ ������ PasswordEncryptor�� �̿��Ѵ�.
		if(!passwordEncoder.matches(member.getMemberPwd(), loginMember.getMemberPwd())) {
			//��ġ���� �ʴ´ٸ� ���� �߻�, ExceptionHandling Ŭ������ �ѱ��.
			throw new ComparePasswordException("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		} else {
			// ��й�ȣ�� �´´ٸ� Member�� ������ ��� Controller�� ���ư���.
			return loginMember;
		}
	}
	
	@Override
	public void insertMember() {
		
	}


	@Override
	public void updateMember(Member member, HttpSession session) {
		
		// session�� ���� �޾ƿ� ���� : �մܿ��� �Ѿ�� memberId�� session�� loginMember�� idŰ���� �������� Ȯ���ؾ��ϱ⶧��
		// -> ����ڰ� �Է��� userId Ű���� DB�� �ִ��� Ȯ��
		// ����ڰ� ������Ʈ �ϰ�;��ϴ� ������ DB�� �����ϴ� �÷��� ũ�⿡ ��ġ�� �ʴ��� || �������ǿ� �����ϴ���
		// ���Ͱ��� ������ validator���� ����
		
		validator.validatorMemberExist(member);
		log.info("{}", session.getAttribute("loginMember"));
		log.info("{}", member);
		
		mapper.updateMember(member);
		
		session.setAttribute("loginMember", mapper.login(member));
		
	}

	@Override
	public void deleteMember() {
		
	}

	@Override
	public void updateMemberEducation() {
		
	}

}
