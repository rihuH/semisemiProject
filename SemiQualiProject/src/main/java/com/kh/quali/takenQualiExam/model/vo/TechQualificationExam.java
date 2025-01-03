package com.kh.quali.takenQualiExam.model.vo;

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
public class TechQualificationExam {

	private Long examTypeNo; // Primary key
    private int qualificationRank;
    private TechnicalQualification technicalQualification;
}
