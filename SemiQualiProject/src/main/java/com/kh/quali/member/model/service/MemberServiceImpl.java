package com.kh.quali.member.model.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kh.quali.exception.ComparePasswordException;
import com.kh.quali.member.model.dao.MemberMapper;
import com.kh.quali.member.model.vo.EducationStatus;
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
		Member loginMember = validator.validateMemberExist(member);
		
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
	public void signUp(Member member) {
		
		// validator Ŭ������ �̿��ؼ� ���̵��ߺ�, ���̵���� �� ������ ��ġ�� ���ƿ�
		validator.validateJoinMember(member);
		
		// ���ܻ����� �߻������ʰ� ���ƿԴٸ� ��й�ȣ�� ��ȣȭ ���ش�.
		member.setMemberPwd(passwordEncoder.encode(member.getMemberPwd()));
		
		
		
		mapper.signUp(member);
		
	}
	
	@Override
	public String checkId(String memberId) {
		// ���̵� �ߺ��̶�� "NNNNN", �ߺ��� �ƴ϶�� "NNNNY"
		return mapper.checkId(memberId) > 0 ? "NNNNN" : "NNNNY";
	}


	@Override
	public void updateMember(Member member, HttpSession session) {
		
		// session�� ���� �޾ƿ� ���� : �մܿ��� �Ѿ�� memberId�� session�� loginMember�� idŰ���� �������� Ȯ���ؾ��ϱ⶧��
		// -> ����ڰ� �Է��� userId Ű���� DB�� �ִ��� Ȯ��
		// ����ڰ� ������Ʈ �ϰ�;��ϴ� ������ DB�� �����ϴ� �÷��� ũ�⿡ ��ġ�� �ʴ��� || �������ǿ� �����ϴ���
		// ���Ͱ��� ������ validator���� ����
		
		validator.validateMemberExist(member);
		log.info("{}", session.getAttribute("loginMember"));
		log.info("{}", member);
		
		if(member.getMemberPwd().length() < 15) {
			member.setMemberPwd(passwordEncoder.encode(member.getMemberPwd()));
		}
		
		mapper.updateMember(member);
		
		
		session.setAttribute("loginMember", mapper.login(member));
		
	}

	@Override
	public int selectMemberEducation(int memberNo) {
		int result = mapper.selectMemberEducation(memberNo);
		return result;
	}
	
	@Override
	public void insertMemberEducation(int memberNo, EducationStatus education) {
		mapper.insertMemberEducation(memberNo, education);
	}

	@Override
	public void updateMemberEducation(int memberNo, EducationStatus education) {
		
		// select�� ���� �ϰ� �ִ��� ������ ���ƿ��� ���� ���� Ȯ��
		int select = selectMemberEducation(memberNo);
		
		if(select > 0) {
			// ���� ���ƿ� ���� 0���� ũ�ٸ� �ִٴ°Ŵϱ� update�� ��������
			mapper.updateMemberEducation(memberNo, education);
		} else {
			// ���ƿ� ���� 0�̶�� ���ٴ°Ŵϱ� insert�� ���� �߰�
			insertMemberEducation(memberNo, education);
		}
		
	}

	@Override
	public void deleteMember() {
		
	}
	
	
	
}
