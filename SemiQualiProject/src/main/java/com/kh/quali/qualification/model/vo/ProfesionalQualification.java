package com.kh.quali.qualification.model.vo;

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
public class ProfesionalQualification {

	private Long qualificationNo;
	private String qualificationName;
	private String status;
	private int requiredRank;
	private ProfesionalDept profesionalDept;
}
