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
		TechnicalQualification technicalQualification = qs.findTechQualiByName(qualificationName);
		Map<String, Object> map = new HashMap();
		map.put("tech", technicalQualification);
		return mv.setViewNameAndData("takenQualiExam/exam_insert", map);
	}
	@GetMapping("/taken_quali_exam/exam_insert_form/pro{qualificationName}")
	public ModelAndView examProInsertForm(@PathVariable(name="qualificationName") String qualificationName) {
		ProfesionalQualification profesionalQualification = qs.findProQualiByName(qualificationName);
		Map<String, Object> map = new HashMap();
		map.put("pro", profesionalQualification);
		return mv.setViewNameAndData("takenQualiExam/exam_insert", map);
	}
	
	
	@PostMapping("taken_quali_exam/tech_exam_insert")
	public String examTechInsert(TakenQualiExam takenQualiExam, @RequestParam(name="qualificationName")String qualificationName,
			@RequestParam(name="qualificationRank")int qualificationRank) {
		// 테크 인서트
		ts.insertTakenTechExam(takenQualiExam, qualificationName, qualificationRank);
		// 일단 관리자페이지로 돌아가기
		return "redirect:../eligibility-check.do";
	}
	@PostMapping("taken_quali_exam/pro_exam_insert")
	public String examProInsert(TakenQualiExam takenQualiExam, @RequestParam(name="qualificationName")String qualificationName,
			@RequestParam(name="qualificationRank")int qualificationRank) {
		// 프로 인서트
		ts.insertTakenProExam(takenQualiExam, qualificationName, qualificationRank);
		// 일단 관리자페이지로 돌아가기
		return "redirect:../eligibility-check.do";
	}
	
	@GetMapping("taken_quali_exam/taken_quali_exam_list")
	public ModelAndView takenExamList() {
		Map<String, Object> takenExamList = ts.getTakenExamList();
		return mv.setViewNameAndData("takenQualiExam/exam_list", takenExamList);
	}
	
	@GetMapping("taken_quali_exam/place_select_form")
	public ModelAndView placeSelectForm() {
		Map<String, Object> takenExamList = ts.getTakenExamList(); 
		return mv.setViewNameAndData("takenQualiExam/place_insert_form", takenExamList);
	}

	
	@PostMapping("taken_quali_exam/insert_place")
	public String insertPlace(int[] insertPlaceNo, Long examNo) {
		ts.insertExamPlace(insertPlaceNo, examNo);
		return "redirect:../eligibility-check.do";
	}
	



	@GetMapping("taken_quali_exam/application_list")
	public ModelAndView selsectApplicationList() {
		
		// 현재 접수 가능한 시험목록 가져옴
		Map<String, Object> takenExamList = ts.getTakenExamList();
		
		log.info("{}", takenExamList);
		
		// application_list.jsp로 접수가능한 시험목록을 보내서 보내서 선택하게함
		return mv.setViewNameAndData("application/application_list", takenExamList);
	}

	@PostMapping("taken_quali_exam/application_exam_place")
	public ModelAndView selectExamPlace(String examStartDate, String receptionDate, String Type) {
		
		Map<String, Object> takenExamList = ts.findAllExamPlacesByExam(examStartDate, receptionDate, Type);
		
		
		return mv.setViewNameAndData("application/application_place", takenExamList);
	}


	
}
