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
		
		// 아이디 검증을 validator에게 맡긴다.
		Member loginMember = validator.validatorMemberExist(member);
		
		// 만약 loginUser가 돌아왔다면, 비밀번호 검증은 PasswordEncryptor을 이용한다.
		if(!passwordEncoder.matches(member.getMemberPwd(), loginMember.getMemberPwd())) {
			//일치하지 않는다면 예외 발생, ExceptionHandling 클래스로 넘긴다.
			throw new ComparePasswordException("비밀번호가 틀렸습니다.");
		} else {
			// 비밀번호가 맞는다면 Member의 정보를 들고 Controller로 돌아간다.
			return loginMember;
		}
	}
	
	@Override
	public void insertMember() {
		
	}


	@Override
	public void updateMember(Member member, HttpSession session) {
		
		// session도 같이 받아온 이유 : 앞단에서 넘어온 memberId와 session의 loginMember의 id키값이 동일한지 확인해야하기때문
		// -> 사용자가 입력한 userId 키값이 DB에 있는지 확인
		// 사용자가 업데이트 하고싶어하는 내용이 DB에 존재하는 컬럼의 크기에 넘치지 않는지 || 제약조건에 부합하는지
		// 위와같은 검증인 validator에서 진행
		
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
