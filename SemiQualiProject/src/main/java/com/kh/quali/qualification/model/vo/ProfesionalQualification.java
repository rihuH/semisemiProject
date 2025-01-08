package com.kh.quali.qualification.model.vo;

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
public class ProfesionalQualification {

	private Long qualificationNo;
	private String qualificationName; // 자격증명
	private String status; // 삭제안되었으면 Y
	private int requiredRank; // 1차, 2차를 나타내는 필드로 1, 2의 숫자를 가진다.
	private ProfesionalDept profesionalDept; // 관련부처 객체를 필드로 가지고 있음
}
