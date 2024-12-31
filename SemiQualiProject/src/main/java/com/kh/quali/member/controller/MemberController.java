package com.kh.quali.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.member.model.service.MemberService;
import com.kh.quali.member.model.vo.EducationStatus;
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
	public String applicatioRecord1() {
		
		// header�쓽 留덉씠�럹�씠吏�瑜� �닃���쓣 寃쎌슦
		return "/application/applicationRecord";
	}
	
	@GetMapping("login.do")
	public String loginPage() {
		
		return "/member/loginPage";
	}
	
	@PostMapping("login.me")
	public ModelAndView loginMember(Member member, HttpSession session) {
		
		Member loginMember = memberService.loginMember(member);
		
		// �꽌鍮꾩뒪濡� 蹂대궦 �슂泥��씠 validator, passwordEncryptor, mapper, DB�벑�쓣 嫄곗퀜�꽌 �씪移섑븷寃쎌슦 �룎�븘�샂
		// �룎�븘�삩 寃쎌슦 �꽭�뀡�뿉 異붽��븳�떎.
		session.setAttribute("loginMember", loginMember);
		
		session.setAttribute("education", memberService.selectMemberEducation(loginMember.getMemberNo()));
		
		/*
		 * 로그인하면서 loginMember에 member 정보를 다 담아옴
		 * 추후 수정페이지를 불러올 땐 session에 저장된 loginMember를 불러와서 보여줌.
		 * EducationStatus도 수정페이지를 불러올 때 같이 불러오기위해선
		 * 미리 DB에 접근해서 가져올 필요가 있음.
		 * 로그인 할 때 Service단의 selectMeberEducation을 사용해서 EducationStatus를 미리 불러옴
		 * (원래는 int형으로 있는지 없는지 구분했는데, 이제 EducationStatus라는 객체를 받아와서
		 * null인지 아닌지로 구분.
		 */
		
		// �꽭�뀡�뿉 異붽��뻽�떎硫� 紐⑤뱺 �슂泥��쓣 泥섎━�뻽�쑝�땲 �솕硫댁��젙�쑝濡� 留덈Т由ы븳�떎.
		return mv.setViewNameAndData("main", null);
	}
	
	@GetMapping("logout.me")
	public String logout(HttpSession session) {
		session.removeAttribute("loginMember");
		session.removeAttribute("education");
		return "main";
	}

	@GetMapping("enrollform.me")
	public String insertMember() {
		
		return "/member/enroll_form";
	}
	
	@PostMapping("sign-up.me")
	public ModelAndView signUp(Member member, HttpSession session) {
		
		memberService.signUp(member);
		
		session.setAttribute("alertMsg", "�쉶�썝媛��엯 �꽦怨�");
		
		return mv.setViewNameAndData("main", null);
	}

	
	@GetMapping("mypage")
	public String myPage() {
		return "/member/mypage";
		
	}
	
	@PostMapping("edit-profile")
	public ModelAndView updateMember(Member member, HttpSession session) {
		
		memberService.updateMember(member, session);
		
		System.out.println(member);
		
		session.setAttribute("alertMsg", "�젙蹂댁닔�젙�뿉 �꽦怨듯뻽�뒿�땲�떎");
		
		session.removeAttribute("loginMember");
		
		return mv.setViewNameAndData("main", null);
	}
	
	

	
	@PostMapping("edit-education")
	public ModelAndView updateMemberEducation(EducationStatus educationStatus, HttpSession session) {
		
		/*
		EducationStatus edu = (EducationStatus) session.getAttribute("education");
		log.info("{}",edu);
		*/ //session에 아무것도 없을땐 null값이 돌아옴

		
		memberService.updateMemberEducation(educationStatus, session);
		
		//log.info("{}", educationStatus);
		
		
		return mv.setViewNameAndData("main", null);
	}
	

	
}
