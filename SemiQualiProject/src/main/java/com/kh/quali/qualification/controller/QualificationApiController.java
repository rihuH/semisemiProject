package com.kh.quali.qualification.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.quali.common.model.vo.ResponseData;
import com.kh.quali.qualification.model.service.QualificationService;
import com.kh.quali.qualification.model.vo.ProfesionalQualification;
import com.kh.quali.qualification.model.vo.TechnicalQualification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// AJAX 컨트롤러
@RestController
@RequiredArgsConstructor
@Slf4j
public class QualificationApiController {

	private final QualificationService qs;
	
	@GetMapping("qualification/search")
	public ResponseEntity<ResponseData> searchQuali(String searched){
		
		// 검색어가 자격증이름에 포함되어있는지 검색해서 리스트로 돌려주는 서비스메소드 호출
		List<TechnicalQualification> techList  = qs.searchTechName(searched);
		List<ProfesionalQualification> proList = qs.searchProName(searched);
		
		Map<String, Object> map = new HashMap();
		map.put("techList", techList);
		map.put("proList", proList);
		ResponseData responseData =  ResponseData.builder().data(map).status(HttpStatus.OK.toString()).message("자격목록").build();
		 return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
}
