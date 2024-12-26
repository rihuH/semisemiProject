package com.kh.quali.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.member.model.service.MemberService;
import com.kh.quali.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final ModelAndViewUtil mv;
	
	@GetMapping("mypage.me")
	public String applicatioRecord() {
		
		// header의 마이페이지를 눌렀을 경우
		return "/application/applicationRecord";
	}
	
	@PostMapping("login.me")
	public ModelAndView loginMember(Member member, HttpSession session) {
		
		//서비스로 요청보냄
		Member loginMember = memberService.loginMember(member);
		
		// 서비스로 보낸 요청이 validator, passwordEncryptor, mapper, DB등을 거쳐서 일치할경우 돌아옴
		// 돌아온 경우 세션에 추가한다.
		session.setAttribute("loginMember", memberService.loginMember(member));
		
		// 세션에 추가했다면 모든 요청을 처리했으니 화면지정으로 마무리한다.
		return mv.setViewNameAndData("main", null);
	}

	@PostMapping("enrollform.me")
	public void insertMember() {
		
	}

	@PostMapping("edit-profile")
	public ModelAndView updateMember(Member member, HttpSession session) {
		
		memberService.updateMember(member, session);
		
		
		
		return mv.setViewNameAndData("redirect:edit-profile", null);
	}

	
	public void updateMemberEducation() {
		
	}
	
}
