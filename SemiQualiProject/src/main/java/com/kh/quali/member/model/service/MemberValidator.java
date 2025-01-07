package com.kh.quali.member.model.service;

import org.springframework.stereotype.Component;

import com.kh.quali.exception.TooLargeValueException;
import com.kh.quali.exception.UserFoundException;
import com.kh.quali.exception.UserIdNotFoundException;
import com.kh.quali.member.model.dao.MemberMapper;
import com.kh.quali.member.model.vo.Member;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor 
public class MemberValidator {

	private final MemberMapper mapper;
	
	
	public void validateDuplicateMember(Member member) {
		Member existingMember = mapper.login(member);
		if(existingMember != null && member.getMemberId().equals(existingMember.getMemberId())) {
			throw new UserFoundException("이미 존재하는 아이디입니다.");
		}
	}
	
	public void validateIdLength(Member member) {
		if(member.getMemberId().length() > 30) {
			throw new TooLargeValueException("아이디가 올바르지 않습니다. 다시 입력해주세요.");
		}
	}
	
	public void validateJoinMember(Member member) {
		validateDuplicateMember(member);
		validateIdLength(member);
	}
	
	public Member validateMemberExist(Member member) {

		Member existingMember = mapper.login(member);
		
		if(existingMember != null) {
			return existingMember;
		} 
		throw new UserIdNotFoundException("존재하지 않는 아이디입니다.");
	}
	
	
	
}
