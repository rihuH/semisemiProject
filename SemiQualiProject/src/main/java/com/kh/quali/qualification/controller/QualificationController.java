package com.kh.quali.qualification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.quali.common.ModelAndViewUtil;

import com.kh.quali.common.ModelAndViewUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Controller
@Slf4j
@RequiredArgsConstructor
public class QualificationController {

	private final ModelAndViewUtil mv; 
	
	@GetMapping("application-guide")
	public String applicationGuide() {
		return "qualification/application-guide";
	}
	
	@GetMapping("confirmation/application")
	public String applicateConfirmation() {
		
		return "confirmation/applicate_confirm";
	}
}
