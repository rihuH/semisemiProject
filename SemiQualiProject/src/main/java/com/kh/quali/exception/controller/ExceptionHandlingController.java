package com.kh.quali.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.exception.ComparePasswordException;
import com.kh.quali.exception.UserIdNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice // 예외처리 애노테이션
public class ExceptionHandlingController {

	// 모든 예외처리를 담당하는 클래스

	private ModelAndView createErrorResponse(String errorMsg, Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", errorMsg)
		  .setViewName("common/error_page");
		log.info("발생 예외 : {}", e.getMessage(), e);
		return mv;
	}
	
	
	@ExceptionHandler(UserIdNotFoundException.class)
	protected ModelAndView noSearchUserIdError(UserIdNotFoundException e) {
		// 아이디를 찾지 못했을경우의 예외 발생
		return createErrorResponse("아이디를 잘못입력하셨습니다.", e);
	}
	
	@ExceptionHandler(ComparePasswordException.class)
	protected ModelAndView notMatchingPasswordError(ComparePasswordException e) {
		// 비밀번호를 비교하는 메소드, 비밀번호가 틀렸을 경우 예외 발생
		return createErrorResponse("비밀번호가 틀렸습니다.", e);
	}
	
	
}
