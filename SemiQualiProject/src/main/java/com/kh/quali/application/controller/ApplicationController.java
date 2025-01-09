package com.kh.quali.application.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.application.model.service.ApplicationService;
import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.takenQualiExam.model.service.TakenQualiExamService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ApplicationController {

	private final ModelAndViewUtil mv;
	
	private final TakenQualiExamService ts;
	private final ApplicationService as;
	
	@GetMapping("taken_quali_exam/application_list")
	public ModelAndView selectApplicationList() {
		
		Map<String, Object> takenExamList = ts.getTakenExamList();
		
		return mv.setViewNameAndData("application/list", takenExamList);
	}
	
	
	
	@PostMapping("taken_quali_exam/application_list/{examNo}")
	public ModelAndView selectApplicationExamNo(@PathVariable(name="examNo") Long examNo) {
		
		Map<String, Object> takenExamList = ts.findAllExamPlacesByExamNo(examNo);
		
		
		
		return mv.setViewNameAndData("application/insert", takenExamList);
	}
	
	
	

	@PostMapping("application_record/{memberId}")
	public ModelAndView insertApplication(Long examNo,Long examLocationNo, int memNo, @PathVariable(name="memberId") String memberId) {
		
		log.info("{}", memNo);
		log.info("{}", examNo);
		log.info("{}", examLocationNo);
		
		Map<String, Object> examInfo = as.insertApplication(examNo, examLocationNo, memNo);
		//log.info("{}", examInfo);
		
		return mv.setViewNameAndData("application/applicationRecord", examInfo);
	}
	


}
