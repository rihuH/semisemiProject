package com.kh.quali.takenQualiExam.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.kh.quali.provisionalAnswer.model.vo.ProvisionalAnswer;

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
public class Subject {
	private Long subjectNo; // Primary key
    private int subjectPeriod;
    private TakenQualiExam takenQualiExam;
    private List<ProvisionalAnswer> provisionalAnswers;

}
     