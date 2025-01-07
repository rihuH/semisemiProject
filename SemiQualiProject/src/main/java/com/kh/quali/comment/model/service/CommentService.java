package com.kh.quali.comment.model.service;

import com.kh.quali.answer.model.vo.Answer;
import com.kh.quali.comment.model.vo.Comment;

public interface CommentService {

	void insertComment(Comment comment);

	void updateComment(Comment comment);

	void deleteComment(Comment comment);

}
