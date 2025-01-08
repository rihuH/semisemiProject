package com.kh.quali.takenQualiExam.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.place.model.vo.Place;
import com.kh.quali.takenQualiExam.model.vo.ExamPlace;
import com.kh.quali.takenQualiExam.model.vo.ProQualificationExam;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;
import com.kh.quali.takenQualiExam.model.vo.TechQualificationExam;

@Mapper
public interface TakenQualiExamMapper {
	
	/*
	 * 참고, 시험에는
	 * 그냥 시험 자체를 나타내는 xxQualificationExam과 (정보처리기사 실기, 세무사 1차 같은)
	 * 시행되는 시험들을 나타내는 TakenQualiExam이 있다 (1회 정보처리기사 실기, 2회 정보처리기사 실기, 3회 정보처리기사 실기..)
	 */

	// 시험 이름과 해당 rank가 담긴 객체를 전달하여 완전한 기술자격시험 정보를 받아오는 메소드
	TechQualificationExam findTechExamNoByNameAndRank(TechQualificationExam techQualificationExam);
	// 시험 이름과 해당 rank가 담긴 객체를 전달하여 완전한 기술자격시험 정보를 받아오는 메소드
	ProQualificationExam findProExamByNameAndRank(ProQualificationExam proQualificationExam);

	int insertTakenTechExam(Map<String, Object> map);
	
	// 현재 접수가능한 전문자격시험 리스트를 받아옴
	List<TakenQualiExam> takenProQualiExamList();
	// 현재 접수가능한 기술자격시험 리스트를 받아옴
	List<TakenQualiExam> takenTechQualiExam();
	
	// 현재 해당되는 시험 객체를 전달하면 count함수를 사용하여 '회차'를 찾아 round필드에 입력함.
	int getRoundOfExam(TakenQualiExam e);
	
	void insertTechSubject();

	void insertProSubject(int i);
	
	// 시행되고 있는 전문자격시험을 자격증이름과 시행날짜로 받아오는 메소드 
	TakenQualiExam findTakenProExamByNameAndDate(Map<String, Object> map);
	// 시행되고 있는 전문자격시험을 자격증이름과 시행날짜로 받아오는 메소드
	TakenQualiExam findTakenTechExamByNameAndDate(Map<String, Object> map);

	// 시행되고 있는 특정한 전문시험에 등록된 시험장소리스트를 가져오는 메소드.
	List<ExamPlace> findAllPlaceOfProExam(Long examNo);
	// 시행되고 있는 특정한 기술시험에 등록된 시험장소리스트를 가져오는 메소드. 사실상 지금 위 아래 동일함.
	List<ExamPlace> findAllPlaceOfTechExam(Long examNo);

	List<ExamPlace> findAllTechPlaceByDate(TakenQualiExam takenQualiExam);

	List<ExamPlace> findAllProPlaceByDate(TakenQualiExam takenQualiExam);

	List<Place> findAllPlaceByLocationNo(List locationNos);

	void insertExamPlace(Map<String, Object> map);

	// 현재 접수가능한 전문자격시험 리스트를 받아옴
	List<TakenQualiExam> takenProQualiExamListForSubject();
	// 현재 접수가능한 기술자격시험 리스트를 받아옴
	List<TakenQualiExam> takenTechQualiExamListForSubject();
 


}
