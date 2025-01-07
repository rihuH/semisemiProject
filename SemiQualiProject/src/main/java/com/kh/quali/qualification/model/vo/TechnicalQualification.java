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
	private String qualificationName; // 자격증이름
	private String status; // 삭제안되었으면 Y
	private int requiredRank; // 1차, 2차 등 요구하는 총 회차의 수로, 테이블에는 모두 2가 최대이다.
	private TechCategory techCategory; // 분류에 해당하는 객체를 필드로 가지고 있음.

}
