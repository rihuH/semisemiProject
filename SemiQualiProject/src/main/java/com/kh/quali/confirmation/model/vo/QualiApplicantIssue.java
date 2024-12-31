package com.kh.quali.confirmation.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QualiApplicantIssue {
	private Long applicantIssueNo;   
	private String purposeIssuance;  
	private Date issueDate;           
	private Long applicationNo;     
	private String status;        
	private String confirmationNo;
	
}
