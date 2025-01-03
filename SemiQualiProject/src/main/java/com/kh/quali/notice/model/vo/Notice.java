package com.kh.quali.notice.model.vo;

import lombok.Data;

@Data
public class Notice {
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeCreateAt;
	private String noticeUpdateAt;
	private String noticeStatus;
	private String memNo;
	
}
