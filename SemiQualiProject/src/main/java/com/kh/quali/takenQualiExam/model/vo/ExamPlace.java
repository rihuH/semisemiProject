package com.kh.quali.takenQualiExam.model.vo;

import com.kh.quali.place.model.vo.Place;

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
public class ExamPlace {
	private int examLocationNo; // Primary key
    private int examNo;
    private Place place;
}
