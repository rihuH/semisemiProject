package com.kh.quali.takenQualiExam.model.service;

import java.util.List;
import java.util.Map;

import com.kh.quali.takenQualiExam.model.vo.ExamPlace;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;

public interface TakenQualiExamService {

	void insertTakenTechExam(TakenQualiExam takenQualiExam, String qualificationName, int qualificationRank);

	Map<String, Object> getTakenExamList();

	void insertTakenProExam(TakenQualiExam takenQualiExam, String qualificationName, int qualificationRank);

	List<ExamPlace> findAllExamPlacesByExam(String exam, String receptionDate);

}
