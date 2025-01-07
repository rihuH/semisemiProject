package com.kh.quali.comment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.quali.comment.model.service.CommentService;
import com.kh.quali.comment.model.vo.Comment;
import com.kh.quali.common.ModelAndViewUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	private final ModelAndViewUtil mv;
	
	
	// 답변 불러오기, 작성, 수정, 삭제
	
	@PostMapping("faq/comment-insert")
	public ModelAndView insertComment(Comment comment, HttpSession session) {
		
		commentService.insertComment(comment);
		
		session.setAttribute("alertMsg", "답변작성성공");
		
		return mv.setViewNameAndData("redirect:/answer/" + comment.getAnswerNo(), null);
	}
	
	@PostMapping("faq/comment-update")
	public ModelAndView updateComment(Comment comment, HttpSession session) {
		
		commentService.updateComment(comment);
		
		session.setAttribute("alertMsg", "답변수정성공");

		return mv.setViewNameAndData("redirect:/answer/" + comment.getAnswerNo(), null);
	}
	
	@PostMapping("comment/delete")
	public ModelAndView deleteComment(Comment comment, HttpSession session) {
		
		commentService.deleteComment(comment);
		
		session.setAttribute("alertMsg", "답변삭제성공");
		
		return mv.setViewNameAndData("redirect:/answer/" + comment.getAnswerNo(), null);
	}
	
}
