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
public class TypeQualification {
	
	private String qualificationCode; // 전문은 A, 기술은 B의 코드를 가진다.
	private String qualificationType; // 국가전문자격, 국가기술자격 의 값을 가진다.
	
}
