package com.kh.quali.takenQualiExam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.qualification.model.service.QualificationService;
import com.kh.quali.qualification.model.vo.ProfesionalQualification;
import com.kh.quali.qualification.model.vo.TechnicalQualification;
import com.kh.quali.takenQualiExam.model.service.TakenQualiExamService;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TakenQualiExamController {

	private final ModelAndViewUtil mv;

	private final TakenQualiExamService ts;
	private final QualificationService qs;
	
	@GetMapping("/taken_quali_exam/exam_insert_select")
	public ModelAndView examInsertSelect() {
		List<ProfesionalQualification> proList = qs.findAllPro();
		List<TechnicalQualification> techList = qs.findAllTech();
		Map<String, Object> map = new HashMap();
		map.put("proList", proList);
		map.put("techList", techList);
		return mv.setViewNameAndData("takenQualiExam/exam_insert_select", map) ;
	}
	
	@GetMapping("/taken_quali_exam/exam_insert_form/tech{qualificationName}")
	public ModelAndView examInsertForm(@PathVariable(name="qualificationName") String qualificationName) {
		log.info("{}",qualificationName);
		TechnicalQualification technicalQualification = qs.findTechByName(qualificationName);
		Map<String, Object> map = new HashMap();
		map.put("tech", technicalQualification);
		return mv.setViewNameAndData("takenQualiExam/exam_insert", map);
	}
	
	@PostMapping("taken_quali_exam/exam_insert")
	public ModelAndView examInsert(TakenQualiExam takenQualiExam, @RequestParam(name="qualificationName")String qualificationName,
			@RequestParam(name="qualificationRank")int qualificationRank) {
		log.info("{}, {}" + qualificationRank, takenQualiExam, qualificationName);
		
		ts.insertTakenTechExam(takenQualiExam, qualificationName, qualificationRank);
		
		return null;
	}
	

	
}
