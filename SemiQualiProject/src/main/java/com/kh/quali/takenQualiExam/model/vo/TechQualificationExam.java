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
    private int qualificationRank; // 1, 2의 숫자로 필기인지 실기인지를 나타낸다.
    private TechnicalQualification technicalQualification; // 해당되는 자격증객체를 필드로 가짐.
}
