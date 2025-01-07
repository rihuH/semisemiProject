package com.kh.quali.qualification.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProfesionalDept {

	private Long categoryNo;
	private String relevantDepartment; // 관련부처명
	private TypeQualification typeQualification; // 전문, 기술자격증인지 타입객체를 필드로 가짐
	
}
