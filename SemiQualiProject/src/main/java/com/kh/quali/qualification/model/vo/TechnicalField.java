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
public class TechnicalField {

	private Long fieldNo;
	private String fieldName; // 시행분야명
	private TypeQualification typeQualification; // 전문인지, 기술인지 판단하는 객체를 필드로.
	
}
