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
		
		// header占쎌벥 筌띾뜆�뵠占쎈읂占쎌뵠筌욑옙�몴占� 占쎈땭占쏙옙占쎌뱽 野껋럩�뒭
		return "/application/applicationRecord";
	}
	
	@GetMapping("login.do")
	public String loginPage() {
		
		return "/member/loginPage";
	}
	
	@PostMapping("login.me")
	public ModelAndView loginMember(Member member, HttpSession session) {
		
		Member loginMember = memberService.loginMember(member);
		
		// 占쎄퐣�뜮袁⑸뮞嚥∽옙 癰귣�沅� 占쎌뒄筌ｏ옙占쎌뵠 validator, passwordEncryptor, mapper, DB占쎈쾻占쎌뱽 椰꾧퀣�쒙옙苑� 占쎌뵬燁살꼹釉룟칰�럩�뒭 占쎈즼占쎈툡占쎌긾
		// 占쎈즼占쎈툡占쎌궔 野껋럩�뒭 占쎄쉭占쎈�∽옙肉� �빊遺쏙옙占쎈립占쎈뼄.
		session.setAttribute("loginMember", loginMember);
		
		session.setAttribute("education", memberService.selectMemberEducation(loginMember.getMemberNo()));
		
		/*
		 * 濡쒓렇�씤�븯硫댁꽌 loginMember�뿉 member �젙蹂대�� �떎 �떞�븘�샂
		 * 異뷀썑 �닔�젙�럹�씠吏�瑜� 遺덈윭�삱 �븧 session�뿉 ���옣�맂 loginMember瑜� 遺덈윭���꽌 蹂댁뿬以�.
		 * EducationStatus�룄 �닔�젙�럹�씠吏�瑜� 遺덈윭�삱 �븣 媛숈씠 遺덈윭�삤湲곗쐞�빐�꽑
		 * 誘몃━ DB�뿉 �젒洹쇳빐�꽌 媛��졇�삱 �븘�슂媛� �엳�쓬.
		 * 濡쒓렇�씤 �븷 �븣 Service�떒�쓽 selectMeberEducation�쓣 �궗�슜�빐�꽌 EducationStatus瑜� 誘몃━ 遺덈윭�샂
		 * (�썝�옒�뒗 int�삎�쑝濡� �엳�뒗吏� �뾾�뒗吏� 援щ텇�뻽�뒗�뜲, �씠�젣 EducationStatus�씪�뒗 媛앹껜瑜� 諛쏆븘���꽌
		 * null�씤吏� �븘�땶吏�濡� 援щ텇.
		 */
		
		// 占쎄쉭占쎈�∽옙肉� �빊遺쏙옙占쎈뻥占쎈뼄筌롳옙 筌뤴뫀諭� 占쎌뒄筌ｏ옙占쎌뱽 筌ｌ꼶�봺占쎈뻥占쎌몵占쎈빍 占쎌넅筌롫똻占쏙옙�젟占쎌몵嚥∽옙 筌띾뜄龜�뵳�뗫립占쎈뼄.
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
		
		session.setAttribute("alertMsg", "占쎌돳占쎌뜚揶쏉옙占쎌뿯 占쎄쉐�⑨옙");
		
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
		
		session.setAttribute("alertMsg", "정보 업데이트에 성공하였습니다");
		
		session.removeAttribute("loginMember");
		
		return mv.setViewNameAndData("main", null);
	}
	
	

	
	@PostMapping("edit-education")
	public ModelAndView updateMemberEducation(EducationStatus educationStatus, HttpSession session) {
		
		/*
		EducationStatus edu = (EducationStatus) session.getAttribute("education");
		log.info("{}",edu);
		*/ //session�뿉 �븘臾닿쾬�룄 �뾾�쓣�븧 null媛믪씠 �룎�븘�샂

		
		memberService.updateMemberEducation(educationStatus, session);
		
		//log.info("{}", educationStatus);
		
		
		return mv.setViewNameAndData("main", null);
	}
	
	@PostMapping("delete.me")
	public ModelAndView deleteMember(String memberPwd, HttpSession session) {
		
		memberService.deleteMember(memberPwd, session);
		session.removeAttribute("loginMember");
		session.removeAttribute("education");
		
		session.setAttribute("alertMsg", "회원삭제에 성공하셨습니다.");

		return mv.setViewNameAndData("main", null);
	}

	
}
