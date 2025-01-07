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
	/*
	 * 시행되는 시험에 대한 장소
	 */
	private Long examLocationNo; // Primary key
    private TakenQualiExam takenQualiExam; // 시행되는 시험을 객체필드로 가지고 있음.
    private Place place; // 해당 장소클래스를 객체필드로 가지고 있음.
}
