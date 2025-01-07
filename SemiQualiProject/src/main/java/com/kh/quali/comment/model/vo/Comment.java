package com.kh.quali.comment.model.vo;

import lombok.Data;

@Data
public class Comment {
	private int commentNo;
	private int answerNo;
	private String commentContent;
	private String commentCreatedAt;
	private String commentUpdatedAt;
	private String commentStatus;
	private String memNo;
	
}
