package com.kh.quali.place.model.vo;

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
public class Place {
	/*
	 * 원서접수
	 * 존재하는 장소 클래스. 
	 * 이 장소와 시행되는 시험을 연결해둔게 ExamPlace가 됨
	 */
	private int locationNo; // Primary key, not null
    private String locationName; // 영등포중학교 같은 이름
    private Integer maximumOccupancy; // 해당 중학교의 총 정원
    private District district; // 서울특별시 등의 구를 필드로 가짐
}
