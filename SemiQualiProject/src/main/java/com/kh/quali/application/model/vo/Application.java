package com.kh.quali.application.model.vo;

import lombok.Data;

@Data
public class Application {
	
	private int applicationNo;
	private String applicationType;
	private int examEntryNo;
	private Long examNo;
	private Long examLocationNo;
	private int memNo;
	private String applicationDate;
	
}
