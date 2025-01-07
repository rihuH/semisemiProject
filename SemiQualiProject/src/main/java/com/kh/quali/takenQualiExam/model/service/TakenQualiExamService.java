package com.kh.quali.takenQualiExam.model.service;

import java.util.List;
import java.util.Map;

import com.kh.quali.takenQualiExam.model.vo.ExamPlace;
import com.kh.quali.takenQualiExam.model.vo.Subject;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;

public interface TakenQualiExamService {

	void insertTakenTechExam(TakenQualiExam takenQualiExam, String qualificationName, int qualificationRank);

	Map<String, Object> getTakenExamList();

	void insertTakenProExam(TakenQualiExam takenQualiExam, String qualificationName, int qualificationRank);

	Map<String, Object> findAllExamPlacesByExam(String exam, String receptionDate, String type);

	void insertExamPlace(int[] insertPlaceNo, Long examNo);

	List<TakenQualiExam> takenExamRoundCheck(List<TakenQualiExam> list);

	TakenQualiExam takenExamRoundCheck(TakenQualiExam takenQualiExam);


}
