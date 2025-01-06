package com.kh.quali.takenQualiExam.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.quali.common.model.vo.ResponseData;
import com.kh.quali.takenQualiExam.model.service.TakenQualiExamService;
import com.kh.quali.takenQualiExam.model.vo.ExamPlace;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TakenQualiExamApiController {

	private final TakenQualiExamService ts;
	
	@GetMapping("taken_quali_exam/selectPlace")
	public ResponseEntity<ResponseData> selectPlace(String exam, String receptionDate){
		log.info("{} {}", exam, receptionDate);
		List<ExamPlace> examPlaces = ts.findAllExamPlacesByExam(exam, receptionDate);  
		return null;
	}
	
	
}
