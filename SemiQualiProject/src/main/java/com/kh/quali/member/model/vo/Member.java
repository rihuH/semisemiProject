package com.kh.quali.member.model.vo;

import lombok.Data;

@Data
public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberRrn;
	private String phone;
	private String email;
	private String createDate;
	private String status;
		
}
