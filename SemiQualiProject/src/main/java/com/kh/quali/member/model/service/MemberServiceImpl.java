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
		
		// 아이디 검증을 validator에게 맡긴다.
		Member loginMember = validator.validateMemberExist(member);
		
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
	public void signUp(Member member) {
		
		// validator 클래스를 이용해서 아이디중복, 아이디길이 등 검증을 거치고 돌아옴
		validator.validateJoinMember(member);
		
		// 예외사항이 발생하지않고 돌아왔다면 비밀번호를 암호화 해준다.
		member.setMemberPwd(passwordEncoder.encode(member.getMemberPwd()));
		
		
		
		mapper.signUp(member);
		
	}
	
	@Override
	public String checkId(String memberId) {
		// 아이디가 중복이라면 "NNNNN", 중복이 아니라면 "NNNNY"
		return mapper.checkId(memberId) > 0 ? "NNNNN" : "NNNNY";
	}


	@Override
	public void updateMember(Member member, HttpSession session) {
		
		// session도 같이 받아온 이유 : 앞단에서 넘어온 memberId와 session의 loginMember의 id키값이 동일한지 확인해야하기때문
		// -> 사용자가 입력한 userId 키값이 DB에 있는지 확인
		// 사용자가 업데이트 하고싶어하는 내용이 DB에 존재하는 컬럼의 크기에 넘치지 않는지 || 제약조건에 부합하는지
		// 위와같은 검증인 validator에서 진행
		
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
		
		// select를 먼저 하고 있는지 없는지 돌아오는 값을 보고 확인
		//EducationStatus edu = selectMemberEducation(memberNo);
		// 위 과정은 로그인할때 진행했음
		
		//log.info("{}", educationStatus);
		// 여기는 html에서 값을 담아왓으니 null일수가 없음.
		// selectMemberEducation을 한번 더 실행해서 null인지 아닌지 판단해야함
		
		Member member = (Member) session.getAttribute("loginMember");
		
		// log.info("{}",selectMemberEducation(member.getMemberNo()));
		EducationStatus edu = selectMemberEducation(member.getMemberNo());
		// 뒤의 getMemNo()를 지우고 != null을 쓸 경우 null이 하나라도 있으면 insert문을 실행해서 안됨.
		// selectMemberEducation(member.getMemberNo()).getMemNo() 로 진행하면
		// insert문이 실행되지 않음 (NullpointException발생)
		if(edu != null) {
			// 돌아온값이 null이라면 정보 수정
			mapper.updateMemberEducation(educationStatus);
		} else {
			// 돌아온 값이 null이 아니라면 추가
			insertMemberEducation(educationStatus);
		}
		
		session.setAttribute("education", educationStatus);
	}
	

	@Override
	public void deleteMember(String memberPwd, HttpSession session) {
		
		// 일단 member에 로그인되어있는 유저의 정보를 담는다.
		Member loginMember = (Member)session.getAttribute("loginMember");
		loginMember.setMemberPwd(memberPwd);
		Member memberInfo = validator.validateMemberExist(loginMember);
		
		// 비밀번호는 여기서 확인하기때문에 SQL문을 쓸 때 확인하지 않아도 된다.
		if(!(passwordEncoder.matches(loginMember.getMemberPwd(), memberInfo.getMemberPwd()))) {
			throw new ComparePasswordException("비밀번호가 일치하지 않습니다.");
		}
		
		mapper.deleteMember(memberInfo);
	}
	
	
	
}
