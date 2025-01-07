package com.kh.quali.answer.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.answer.model.service.AnswerService;
import com.kh.quali.answer.model.vo.Answer;
import com.kh.quali.common.ModelAndViewUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AnswerController {

	private final AnswerService answerService;
	private final ModelAndViewUtil mv;
	
	
	@GetMapping("help/ask")
	public ModelAndView selectFaqList(@RequestParam(value="page", defaultValue="1") int page) {
		
		Map<String, Object> map = answerService.selectAnswerList(page);
		
		return mv.setViewNameAndData("faq/list", map);
	}
	
	@GetMapping("answerInsert")
	public String insertForm() {
		return "faq/insert";
	}
	
	@PostMapping("help/ask")
	public ModelAndView insertAsk(Answer answer, HttpSession session) {
		
		answerService.insertAsk(answer);
		
		session.setAttribute("alertMsg", "게시글 등록 성공");
		
		return mv.setViewNameAndData("redirect:/help/ask", null);
	}
	
	@GetMapping("answer/{id}")
	public ModelAndView selectAnswerNo(@PathVariable(name="id") int id) {
				
		Map<String, Object> responseData = answerService.selectAnswerId(id);
		
		return mv.setViewNameAndData("faq/detail", responseData);
	}

	@PostMapping("faq/answer-update")
	public ModelAndView updateAnswer(int answerNo) {
		
		Map<String, Object> responseData = answerService.selectAnswerId(answerNo);
		
		//log.info("{}", responseData);
		// 여기까지 key=value 형태로 잘 옴 answerNo 유지중
		
		return mv.setViewNameAndData("faq/update", responseData);
	}
	
	@PostMapping("faq/update")
	public ModelAndView updateNotice(Answer answer) {
		
		answerService.updateAnswer(answer);
		
		return mv.setViewNameAndData("redirect:/answer/" + answer.getAnswerNo(), null);
	}
	
	@PostMapping("answer/delete")
	public ModelAndView deleteNotice(int answerNo, HttpSession session) {
		answerService.deleteAnswer(answerNo);
		
		session.setAttribute("alertMsg", "게시글이 정상적으로 삭제되었습니다");
		
		return mv.setViewNameAndData("redirect:/help/ask", null);
	}
	
}
