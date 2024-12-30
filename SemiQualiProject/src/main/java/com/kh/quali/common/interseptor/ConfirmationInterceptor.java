package com.kh.quali.common.interseptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.quali.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfirmationInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 확인서 발급신청 눌렀을 때
	 * 로그인된 상태가 아니라면 intercept하는 클래스
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/*
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginUser");
		if(member != null) {
			return true;
		} else {
			response.sendRedirect("redirect:/" + request.getContextPath()); // 로그인창 경로 추가
			return false;
		}
		*/
		return true;
	}

}
