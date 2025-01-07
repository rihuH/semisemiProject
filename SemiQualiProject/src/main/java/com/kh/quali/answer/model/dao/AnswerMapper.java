package com.kh.quali.answer.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.quali.answer.model.vo.Answer;
import com.kh.quali.comment.model.vo.Comment;

@Mapper
public interface AnswerMapper {

	int selectTotalCount();

	List<Answer> selectBoardList(RowBounds rowBounds);

	Answer selectBoardId(int answerNo);

	void insertAsk(Answer answer);

	int updateBoard(Answer answer);

	int deleteBoard(int answerNo);


	// 답변 불러오는 메소드
	Comment findCommentById(int answerNo);

}
