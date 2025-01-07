package com.kh.quali.place.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class District {

	private Long districtNo; // Primary key
    private String district; // 영등포구
    private String cityName; // 서울특별시, 경기도
    
}
