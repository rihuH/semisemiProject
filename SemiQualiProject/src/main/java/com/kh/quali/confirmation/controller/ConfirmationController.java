package com.kh.quali.confirmation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.confirmation.model.service.ConfirmationService;
import com.kh.quali.confirmation.model.vo.QualiApplicantIssue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ConfirmationController {

	private final ConfirmationService cs;
	private final ModelAndViewUtil mv;
	
	@PostMapping("confirmation-application")
	public void insertConfirmationApply(QualiApplicantIssue qai, HttpServletRequest request) {
		String qualiType = request.getParameter("quali_type");// pro 또는 tech
		String sortType= request.getParameter("sort-select"); //전문자격증종류 또는 기사 기능사....
		//회원이 접수한 원서접수 조회하는 기능 필요
	}
	
	// 인터셉트 필요
	@GetMapping("certificate-status.do")
	public ModelAndView confirmHistory(HttpSession session) {
		List<QualiApplicationIssue>
		return mv.setViewNameAndData("confirmation/history", null);
	}
}
