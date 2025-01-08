package com.kh.quali.answer.model.vo;

import lombok.Data;

@Data
public class Answer {
	
	private int answerNo;
	private String answerTitle;
	private String answerContent;
	private String answerCreatedAt;
	private String answerUpdatedAt;
	private String answerStatus;
	private String hasComment;
	private String memNo;
	
}
