package com.kh.quali.comment.model.service;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.kh.quali.answer.model.vo.Answer;
import com.kh.quali.comment.model.dao.CommentMapper;
import com.kh.quali.comment.model.vo.Comment;
import com.kh.quali.exception.BoardNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

	private final CommentMapper mapper;
	private final ServletContext context;
	
	
	@Override
	public void insertComment(Comment comment) {
		
		mapper.insertComment(comment);
	}


	@Override
	public void updateComment(Comment comment) {
		int result = mapper.updateComment(comment);
		
		if(result <= 0) {
			throw new BoardNotFoundException("답변 업데이트에 실패했습니다.");
		}
	}


	@Override
	public void deleteComment(Comment comment) {
		int result = mapper.deleteComment(comment);
		
		if(result <= 0) {
			throw new BoardNotFoundException("답변 삭제에 실패했습니다.");
		}
	}
	
	
	
}
