package com.kh.quali.qualification.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.qualification.model.service.QualificationService;
import com.kh.quali.qualification.model.vo.Confirmation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QualificationController {
	
	private final QualificationService qs;
	private final ModelAndViewUtil mv;
	
	@GetMapping("certificate-verify.do")
	public ModelAndView confirmationApply() {
		List<Confirmation> confirmationList = qs.findAllConfirmation();
		List<String> proQualiNameList = qs.findAllProQualificationName();
		ArrayList list = new ArrayList();
		Map map = new HashMap();
		map.put("confirmationList", confirmationList);
		map.put("proQualiNameList", proQualiNameList);
		map.put("list", list);
		return mv.setViewNameAndData("qualification/confirmation_application_form", map);
	}
	
	@ResponseBody
	@GetMapping(value="confirmQualiType", produces="text/html; charset=UTF-8")
	public void typeList(String selected){
		Map<String, Object> map = new HashMap();
		log.info("ajax컨트롤러{}", selected);
	}
	
}
