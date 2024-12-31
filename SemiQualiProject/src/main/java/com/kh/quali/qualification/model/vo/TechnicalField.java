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
public class TechnicalField {

	private Long fieldNo;
	private String fieldName;
	private TypeQualification type;
	
}
