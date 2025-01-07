package com.kh.quali.answer.model.service;

import java.util.Map;

import com.kh.quali.answer.model.vo.Answer;
import com.kh.quali.member.model.vo.Member;

public interface AnswerService {

	Map<String, Object> selectAnswerList(int page);

	Map<String, Object> selectAnswerId(int answerNo);

	void insertAsk(Answer answer);

	void updateAnswer(Answer answer);

	void deleteAnswer(int answerNo);


}
