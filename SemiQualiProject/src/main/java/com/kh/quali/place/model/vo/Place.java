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
	
	private int locationNo; // Primary key, not null
    private String locationName; // Not null
    private Integer maximumOccupancy;
    private District district;
}
