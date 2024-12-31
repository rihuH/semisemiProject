package com.kh.quali.qualification.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.confirmation.model.vo.Confirmation;
import com.kh.quali.qualification.model.service.QualificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QualificationController {
	
	private final QualificationService qs;
	private final ModelAndViewUtil mv;
	
	@GetMapping("certificate-verify.do")
	public String confirmationApply() {
		return "qualification/confirmation_application_form";
	}
	
	@ResponseBody
	@GetMapping(value="confirmQualiType", produces="application/json; charset=UTF-8")
	public List<String> typeList(String selected){
		log.info("ajax컨트롤러{}", selected);
		List<String> list = new ArrayList();
		if(selected.equals("pro")) {
			list = qs.findAllProQualificationName();
		} else {
			list.add("전체");
			list.add("기능사");
			list.add("기능장");
			list.add("기사");
			list.add("기술사");
		}
		return list;
	}
	
	@ResponseBody
	@GetMapping(value="confirmType", produces="application/json; charset=UTF-8")
	public List<Confirmation> confirmList(){
		List<Confirmation> list = qs.findAllConfirmation();
		return list;
	}
	
	@GetMapping("eligibility-check.do")
	public String gogo() {
		// 관리자페이지로 돌리는 메소드
		return "qualification/qualiAdmin";
	}
	
	@GetMapping("qualification/select")
	public String selectQualiInfo() {
		return "qualification/select";
	}
	
	@GetMapping("qualification/typeSelect.quali")
	public void typeSelect(String qualiType) {
		// 국가전문/국가기술 선택여부에 따라 그 다음 카테고리를 보내주는 메소드
		
		
	}

	
}
