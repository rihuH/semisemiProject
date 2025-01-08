package com.kh.quali.provisionalAnswer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.provisionalAnswer.model.service.ProvisionalAnswerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProvisionalAnswerController {

	private final ProvisionalAnswerService ps;
	private final ModelAndViewUtil mv;
	
	@GetMapping("provisional_answer/subject_list")
	public ModelAndView subjectList() {
		Map<String, Object> map = ps.findAllSubject();
		return mv.setViewNameAndData("provisional_answer/subject_list", map);
	}
	
	@GetMapping("provisional_answer/subject_detail/{title}/{period}/{no}")
	public ModelAndView subjectDetail(@PathVariable(name="title") String title, @PathVariable(name="period") String period,
			@PathVariable(name="no") Long subjectNo) {
		Map<String, Object> map = new HashMap();
		log.info("{}",subjectNo);
		map.put("title", title);
		map.put("period", period);
		return mv.setViewNameAndData("provisional_answer/subject_detail", map);
	}
	
	@PostMapping("provisional_answer/answer_enroll")
	public void answerEnroll(@RequestParam("upfile1") MultipartFile upfile1, @RequestParam("upfile2") MultipartFile upfile2, @RequestParam("mapAsJson") String mapAsJson) {
			log.info("{} {}", upfile1, upfile2);
			log.info("{}", mapAsJson);
	        /*ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> map = objectMapper.readValue(mapAsJson, Map.class); // JSON -> Map*/
			
			ps.updateAnswerFile(upfile1, upfile2, mapAsJson);
			
	        
	        
	}
	
	
}
