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
public class TechCategory {
	
	private Long categoryNo;
	private String categoryName;
	private TechnicalField technicalField;

}
