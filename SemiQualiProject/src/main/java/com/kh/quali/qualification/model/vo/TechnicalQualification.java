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
public class TechnicalQualification {
	
	private Long qualificationNo;
	private String qualificationName;
	private String status;
	private int requiredRank;
	private TechCategory techCategory;

}
