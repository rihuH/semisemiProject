package com.kh.quali.takenQualiExam.model.vo;

import com.kh.quali.qualification.model.vo.ProfesionalQualification;
import com.kh.quali.qualification.model.vo.TechCategory;
import com.kh.quali.qualification.model.vo.TechnicalQualification;

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
public class ProQualificationExam {
	
	private Long examTypeNo; // Primary key
    private int qualificationRank; // 1차,2차/ 필기, 실기를 나타냄/ 숫자 1, 2가 들어가있음
    private ProfesionalQualification profesionalQualification; // 해당되는 자격증을 객체 필드로 가짐
}
