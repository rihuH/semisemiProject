package com.kh.quali.member.model.service;

import org.springframework.stereotype.Component;

import com.kh.quali.exception.UserIdNotFoundException;
import com.kh.quali.member.model.dao.MemberMapper;
import com.kh.quali.member.model.vo.Member;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor // fianl키워드를 사용한 객체에 생성자를 주입해줌
public class MemberValidator {

	// Member 관련 검증에 대한 클래스
	private final MemberMapper mapper;
	
	// 발생 할 수 있는 예외상황
	// 1. 회원이 존재하지 않는다면?
	// 2. 아이디, 혹은 비밀번호가 틀렸다면? 
	// (비밀번호가 틀린경우 PasswordEncryptor 에서 거르도록 해놨기때문에 validator에서는 할 일이 없다.)
	
	public Member validatorMemberExist(Member member) {

		// Service에서 넘긴 member 값을 갖고 DB의 MEMBER테이블과 비교 조회 후 결과 담기
		Member existingMember = mapper.login(member);
		
		if(existingMember != null) {
			return existingMember;
		} // 돌아온게 있을경우 돌려준다.
		
		// 돌아온게 없다면 예외발생시키기.
		throw new UserIdNotFoundException("아이디를 잘못입력하셨습니다");
	}
	
	
	
}
