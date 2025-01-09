package com.kh.quali.provisionalAnswer.model.vo;

import com.kh.quali.takenQualiExam.model.vo.Subject;

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
public class ProvisionalAnswer {
	 private Long provisionalAnswerNo;
	 private Long subjectNo;
	 private String originalFileName;
	 private String changedFileName;
	 private String filePath;
	 private String status;
}
