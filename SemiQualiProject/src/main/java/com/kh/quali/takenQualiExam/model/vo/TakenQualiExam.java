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
	/*
	 * 원서접수
	 * 시행되는 시험을 나타내는 클래스.
	 */
	private Long examNo; // Primary key
	private String examStartDate; // 시험시작일
	private String examFinalDate; // 시험종료일
	private String receptionStartDate; // 원서접수 시작일
	private String receptionEndDate; // 원서접수 종료일
	private String opinionStartDate; // 가답안 의견제출 시작일
	private String opinionEndDate; // 가답안 의견제출 종료일
	private String round; // 몇 회차인지. 숫자로 들어간다. insert당시에는 없으나 table에서 count로 조회해오는 값
	private Object qualificationExam; // ProQualificationExam 또는 TechQualificationExam이 각각 들어가게 됨.
    
}
