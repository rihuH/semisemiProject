package com.kh.quali.provisionalAnswer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.common.ModelAndViewUtil;
import com.kh.quali.provisionalAnswer.model.service.ProvisionalAnswerService;
import com.kh.quali.takenQualiExam.model.vo.Subject;

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
		map.put("title", title);
		map.put("period", period);
		map.put("subjectNo", subjectNo);
		Map<String, Object> map2 = new HashMap();
		map2 = ps.findSubjectByNo(subjectNo);
		map.putAll(map2);
		log.info("{}", map);
		return mv.setViewNameAndData("provisional_answer/subject_detail", map);
	}
	
	@PostMapping("provisional_answer/answer_enroll")
	public ModelAndView answerEnroll(@RequestParam("upfiles") MultipartFile[] upfiles, @RequestParam("subjectNo") Long subjectNo,
			@RequestParam("file1del") String file1del, @RequestParam("file2del") String file2del) {
		
			log.info("컨트롤러 스트링{} {}",file1del, file2del);
			log.info("컨트롤러{} {}",subjectNo, upfiles);
	        /*ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> map = objectMapper.readValue(mapAsJson, Map.class); // JSON -> Map*/
			ps.updateAnswerFile(upfiles, subjectNo, file1del, file2del);
			Map<String, Object> map = ps.findAllSubject();
			return mv.setViewNameAndData("provisional_answer/subject_list", map);
	}
	
	
	
}
