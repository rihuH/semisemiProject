package com.kh.quali.comment.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.comment.model.vo.Comment;

@Mapper
public interface CommentMapper {

	void insertComment(Comment comment);

	int updateComment(Comment comment);

	int deleteComment(Comment comment);

}
