package com.kh.quali.qualification.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.confirmation.model.vo.Confirmation;
import com.kh.quali.qualification.model.service.QualificationService;
import com.kh.quali.qualification.model.vo.ProfesionalQualification;
import com.kh.quali.qualification.model.vo.TechCategory;
import com.kh.quali.qualification.model.vo.TechnicalQualification;

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
	
	@ResponseBody
	@GetMapping(value="qualification/typeSelect.quali", produces="application/json; charset=UTF-8")
	public List typeSelect(String qualiType) {
		// 국가전문/국가기술 선택여부에 따라 그 다음 카테고리를 보내주는 메소드
		//ajax
		List fieldList = qs.selectQualiType(qualiType);
		return fieldList;
	}
	
	@ResponseBody
	@GetMapping(value="qualification/fieldSelect.quali", produces="application/json; charset=UTF-8")
	public List<TechCategory> fieldSelect(String fieldSelect) {
		// 국가기술분야일 때만 여기 오게 된다. 
		// 같은 TechnicalField를 가지는 TechCategory를 만들어서 보여줘야함.
		List<TechCategory> techCategoryList = qs.selectFieldType(fieldSelect);
		return techCategoryList;
	}
	
	@ResponseBody
	@PostMapping(value="qualification/categorySelect.quali", produces="application/json; charset=UTF-8")
	public List<Object> categorySelect(@RequestParam String categorySelect, @RequestParam String typeStr){
		
		List<Object> qualiList = qs.findQualiByCategory(categorySelect, typeStr);
		
		//log.info("돌아온 {}", qualiList);
		return qualiList;
	}
	
	@GetMapping("qualification/insert_form")
	public String qualificationInsertForm() {
		// 관리자만 가능하도록 인터셉트 또는 인서트 필요
		return "qualification/qualification_insert";
	}
	
	@PostMapping("qualification/insert_pro")
	public void proQualificationInsert(String qualificationName, String relevantDepartment) {
		log.info("컨트롤러 넘겨온값 {}, {}", qualificationName, relevantDepartment);
		qs.insertPro(qualificationName, relevantDepartment);
	}

	@PostMapping("qualification/insert_tech")
	public void techQualificationInsert(String fieldName, String categoryName, String qualificationName) {
		log.info("기술도착");
		qs.insertTech(fieldName, categoryName, qualificationName);
	}
	
	@GetMapping("qualification/qualiList")
	public ModelAndView qualificationUpdateList() {
		// 업데이트 폼으로 이동. 인터셉트 필요
		// 자격증 정보 모두 맵에 담아서 보내기.
		// 타입에 따라 각 자격증을 리스트에 담는 메소드 호출
		List<TechnicalQualification> techList = qs.findAllTech();
		List<ProfesionalQualification> proList = qs.findAllPro();
		Map<String, Object> map = new HashMap();
		map.put("techList", techList);
		map.put("proList", proList);	
		return mv.setViewNameAndData("qualification/qualiList", map);
	}
	
	@GetMapping("qualification/updateForm")
	public ModelAndView qualificationUpdateForm(String type, String fieldName, String category, String name,
			String dept) {
		log.info(dept);
		Map<String, Object> map = new HashMap();
		map.put("type", type);
		map.put("fieldName", fieldName);
		map.put("category", category);
		map.put("name", name);
		map.put("dept", dept);
		return mv.setViewNameAndData("qualification/updateForm", map);
	}
	
	@PostMapping("qualification/updateTech")
	public ModelAndView updateTech(String updatedQualiName, String qualiName) {
		log.info("{}, {}", updatedQualiName, qualiName);
		qs.updateTech(updatedQualiName, qualiName);
		return mv.setViewNameAndData("redirect:/qualification/qualiList", null);
	}
	
	@PostMapping("qualification/updatePro")
	public ModelAndView updatePro(String updatedQualiName, String qualiName) {
		log.info("{}, {}", updatedQualiName, qualiName);
		qs.updatePro(updatedQualiName, qualiName);
		return mv.setViewNameAndData("redirect:/qualification/qualiList", null);
	}
	
	
}
