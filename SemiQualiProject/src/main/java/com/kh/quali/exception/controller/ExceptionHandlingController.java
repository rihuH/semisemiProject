package com.kh.quali.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.exception.BoardNotFoundException;
import com.kh.quali.exception.ComparePasswordException;
import com.kh.quali.exception.QualificationDuplicateException;
import com.kh.quali.exception.UserIdNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice // ����ó�� �ֳ����̼�
public class ExceptionHandlingController {

	// ��� ����ó���� ����ϴ� Ŭ����

	private ModelAndView createErrorResponse(String errorMsg, Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", errorMsg)
		  .setViewName("common/error_page");
		log.info("안내메세지", e.getMessage(), e);
		return mv;
	}
	
	
	@ExceptionHandler(UserIdNotFoundException.class)
	protected ModelAndView noSearchUserIdError(UserIdNotFoundException e) {
		// ���̵� ã�� ����������� ���� �߻�
		return createErrorResponse("존재하지 않는 회원입니다.", e);
	}
	
	@ExceptionHandler(ComparePasswordException.class)
	protected ModelAndView notMatchingPasswordError(ComparePasswordException e) {
		// ��й�ȣ�� ���ϴ� �޼ҵ�, ��й�ȣ�� Ʋ���� ��� ���� �߻�
		return createErrorResponse("비밀번호가 잘못됐습니다.", e);
	}
	
	@ExceptionHandler(BoardNotFoundException.class)
	protected ModelAndView BoardNotFoundException(BoardNotFoundException e) {
		// ��й�ȣ�� ���ϴ� �޼ҵ�, ��й�ȣ�� Ʋ���� ��� ���� �߻�
		return createErrorResponse("게시글을 찾지 못했습니다.", e);
	}
	
	@ExceptionHandler(QualificationDuplicateException.class)
	protected ModelAndView qualificationDuplicateException(BoardNotFoundException e) {
		return createErrorResponse("이미 등록된 자격증입니다.", e);
	}
	
	
	
}
