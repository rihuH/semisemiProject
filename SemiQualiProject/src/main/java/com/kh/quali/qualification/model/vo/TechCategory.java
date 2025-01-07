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
	private String categoryName; // 분류명
	private TechnicalField technicalField; // 시행분야에 해당하는 객체를 필드로 가지고 있음

}
