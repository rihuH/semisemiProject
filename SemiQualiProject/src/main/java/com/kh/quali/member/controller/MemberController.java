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
		
		// headerÀÇ ¸¶ÀÌÆäÀÌÁö¸¦ ´­·¶À» °æ¿ì
		return "/application/applicationRecord";
	}
	
	@GetMapping("login.do")
	public String loginPage() {
		
		return "/member/loginPage";
	}
	
	@PostMapping("login.me")
	public ModelAndView loginMember(Member member, HttpSession session) {
		
<<<<<<< Updated upstream
		//¼­ºñ½º·Î ¿äÃ»º¸³¿
=======
		//ï¿½ê½Œé®ê¾©ë’ªæ¿¡ï¿½ ï¿½ìŠ‚ï§£ï¿½è¹‚ëŒ€ê¹‚
>>>>>>> Stashed changes
		Member loginMember = memberService.loginMember(member);
		
		// ¼­ºñ½º·Î º¸³½ ¿äÃ»ÀÌ validator, passwordEncryptor, mapper, DBµîÀ» °ÅÃÄ¼­ ÀÏÄ¡ÇÒ°æ¿ì µ¹¾Æ¿È
		// µ¹¾Æ¿Â °æ¿ì ¼¼¼Ç¿¡ Ãß°¡ÇÑ´Ù.
		session.setAttribute("loginMember", memberService.loginMember(member));
		
		// ¼¼¼Ç¿¡ Ãß°¡Çß´Ù¸é ¸ðµç ¿äÃ»À» Ã³¸®ÇßÀ¸´Ï È­¸éÁöÁ¤À¸·Î ¸¶¹«¸®ÇÑ´Ù.
		return mv.setViewNameAndData("main", null);
	}
	
	@GetMapping("logout.me")
	public String logout(HttpSession session) {
		session.removeAttribute("loginMember");
		return "main";
	}

	@GetMapping("enrollform.me")
	public String insertMember() {
		
		return "/member/enroll_form";
	}
	
	@PostMapping("sign-up.me")
	public ModelAndView signUp(Member member, HttpSession session) {
		
		memberService.signUp(member);
		
		session.setAttribute("alertMsg", "È¸¿ø°¡ÀÔ ¼º°ø");
		
		return mv.setViewNameAndData("main", null);
	}

	@GetMapping("edit-profile")
	public ModelAndView updateMember(Member member, HttpSession session) {
		
		memberService.updateMember(member, session);
		
		
		
		return mv.setViewNameAndData("redirect:edit-profile", null);
	}
	
	@GetMapping("mypage")
	public String myPage() {
		return "/member/mypage";
	}

	
	public void updateMemberEducation() {
		
	}
	
}
