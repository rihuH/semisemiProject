package com.kh.quali.member.model.vo;

import lombok.Data;

@Data
public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberBirth;
	private String memberRRN;
	private String phone;
	private String createDate;
	private String status;
		
}
