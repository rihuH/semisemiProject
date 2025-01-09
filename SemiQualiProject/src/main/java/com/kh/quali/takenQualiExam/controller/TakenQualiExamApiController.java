package com.kh.quali.takenQualiExam.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.quali.common.model.vo.ResponseData;
import com.kh.quali.takenQualiExam.model.service.TakenQualiExamService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TakenQualiExamApiController {

	private final TakenQualiExamService ts;
	
	@GetMapping("taken_quali_exam/selectPlace")
	public ResponseEntity<ResponseData> selectPlace(String exam, String receptionDate, String type){
		Map<String, Object> examPlaces = ts.findAllExamPlacesByExam(exam, receptionDate, type);
		ResponseData responseData = ResponseData.builder().data(examPlaces).status(HttpStatus.OK.toString()).message("등록된 장소와 가능한 장소 리스트").build();
		
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	@GetMapping("taken_quali_exam/placeSearch")
	public ResponseEntity<ResponseData> placeSearch(String searched, String examno) {
		Map<String, Object> examPlaces = ts.searchedAvailPlaceByNo(searched, examno);
ResponseData responseData = ResponseData.builder().data(examPlaces).status(HttpStatus.OK.toString()).message("등록된 장소와 가능한 장소 리스트").build();
		
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	
	
}
