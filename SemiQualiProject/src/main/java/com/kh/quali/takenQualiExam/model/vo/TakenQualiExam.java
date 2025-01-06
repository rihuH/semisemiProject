package com.kh.quali.takenQualiExam.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TakenQualiExam {
	private Long examNo; // Primary key
	private String examStartDate; // Not null
	private String examFinalDate; // Not null
	private String receptionStartDate; // Not null
	private String receptionEndDate; // Not null
	private String opinionStartDate; // Not null
	private String opinionEndDate;
	private String round;
	private Object qualificationExam; // ProQualification 또는 TechQualification
    
}
