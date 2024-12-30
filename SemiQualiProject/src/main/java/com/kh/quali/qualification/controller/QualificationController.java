package com.kh.quali.qualification.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.qualification.model.service.QualificationServiceImpl;
import com.kh.quali.qualification.model.vo.Confirmation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Controller
@Slf4j
@RequiredArgsConstructor
public class QualificationController {

	private final ModelAndViewUtil mv; 
	private final QualificationServiceImpl qs;
	
	@GetMapping("application-guide")
	public String applicationGuide() {
		return "qualification/application-guide";
	}
	
	@GetMapping("confirmation/application")
	public ModelAndView applicateConfirmation() {
		List<Confirmation> confirmationList = qs.findAllConfirmation();
		List<String> qualificationNameList = qs.findAllProQuailficationName();
		return "confirmation/applicate_confirm";
	}
}
