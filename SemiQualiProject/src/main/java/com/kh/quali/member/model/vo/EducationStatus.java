package com.kh.quali.member.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EducationStatus {

	private int memNo;
	private String education;
	private String schoolName;
	private String graduationDate;
	private String major;
	
}
