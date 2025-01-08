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


	void applicationInsert(String exam, String receptionDate, String type);

	//examLocationNo를 가지고 시험장소 시행되는 시험 및 여러가지 객체 반환해주는 메소드
	Map<String, Object> getTakenExamByNo(Long examLocationNo);

	/**
	 * examNo로 관련 location 맵을 반환해줌
	 */
	Map<String, Object> findAllExamPlacesByExamNo(Long examNo);
	
}
