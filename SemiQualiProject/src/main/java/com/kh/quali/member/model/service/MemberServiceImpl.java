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
			throw new ComparePasswordException("비밀번호가 일치하지 않습니다.");
			
		} else {
			// ��й�ȣ�� �´´ٸ� Member�� ������ ��� Controller�� ���ư���.
			return loginMember;
		}
	}
	
	@Override
	public void signUp(Member member) {
		
		validator.validateJoinMember(member);
		
		member.setMemberPwd(passwordEncoder.encode(member.getMemberPwd()));
		
		
		
		mapper.signUp(member);
		
	}
	
	@Override
	public String checkId(String memberId) {
		return mapper.checkId(memberId) > 0 ? "NNNNN" : "NNNNY";
	}


	@Override
	public void updateMember(Member member, HttpSession session) {
		
		validator.validateMemberExist(member);
		
		if(member.getMemberPwd().length() < 15) {
			member.setMemberPwd(passwordEncoder.encode(member.getMemberPwd()));
		}
		
		mapper.updateMember(member);
		
		session.setAttribute("loginMember", mapper.login(member));
		
	}

	@Override
	public EducationStatus selectMemberEducation(int memberNo) {
		return mapper.selectMemberEducation(memberNo);
	}
	
	@Override
	public EducationStatus insertMemberEducation(EducationStatus educationStatus) {
		mapper.insertMemberEducation(educationStatus);
		return educationStatus;
	}

	@Override
	public void updateMemberEducation(EducationStatus educationStatus, HttpSession session) {
		
		Member member = (Member) session.getAttribute("loginMember");
		
		EducationStatus edu = selectMemberEducation(member.getMemberNo());
		if(edu != null) {
			mapper.updateMemberEducation(educationStatus);
		} else {
			insertMemberEducation(educationStatus);
		}
		
		session.setAttribute("education", educationStatus);
	}
	

	@Override
	public void deleteMember(String memberPwd, HttpSession session) {
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		loginMember.setMemberPwd(memberPwd);
		Member memberInfo = validator.validateMemberExist(loginMember);
		
		if(!(passwordEncoder.matches(loginMember.getMemberPwd(), memberInfo.getMemberPwd()))) {
			throw new ComparePasswordException("비밀번호가 일치하지 않습니다.");
		}
		
		mapper.deleteMember(memberInfo);
	}
	
	
	
}
