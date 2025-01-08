package com.kh.quali.takenQualiExam.model.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.quali.place.model.vo.Place;
import com.kh.quali.qualification.model.service.QualificationService;
import com.kh.quali.qualification.model.vo.ProfesionalQualification;
import com.kh.quali.qualification.model.vo.TechnicalQualification;
import com.kh.quali.takenQualiExam.model.dao.TakenQualiExamMapper;
import com.kh.quali.takenQualiExam.model.vo.ExamPlace;
import com.kh.quali.takenQualiExam.model.vo.ProQualificationExam;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;
import com.kh.quali.takenQualiExam.model.vo.TechQualificationExam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TakenQualiExamServiceImpl implements TakenQualiExamService{

	private final QualificationService qs;
	private final TakenQualiExamMapper mapper;
	
	@Transactional
	@Override
	public void insertTakenTechExam(TakenQualiExam takenQualiExam, String qualificationName, int qualificationRank) {
		TechnicalQualification technicalQualification = qs.findTechQualiByName(qualificationName);
		TechQualificationExam techQualificationExam = new TechQualificationExam().builder().qualificationRank(qualificationRank).technicalQualification(technicalQualification).build();
		techQualificationExam = mapper.findTechExamNoByNameAndRank(techQualificationExam);
		// takenQualiExam opinion필드 잘 됐나 검사 메소드
		takenQualiExam = checkTakenQualiExam(takenQualiExam);
		// 인서트하러 가기
		Map<String, Object> map = new HashMap();
		map.put("takenQualiExam", takenQualiExam);
		map.put("examTypeNo", techQualificationExam.getExamTypeNo());
		int result = mapper.insertTakenTechExam(map);
		// 시험 하나당 자동으로 세부과목 1개등록
		mapper.insertTechSubject();
	}
	@Transactional
	@Override
	public void insertTakenProExam(TakenQualiExam takenQualiExam, String qualificationName, int qualificationRank) {
		ProfesionalQualification profesionalQualification = qs.findProQualiByName(qualificationName);
		ProQualificationExam proQualificationExam = new ProQualificationExam().builder().qualificationRank(qualificationRank).profesionalQualification(profesionalQualification).build();
		proQualificationExam = mapper.findProExamByNameAndRank(proQualificationExam);
		// option필드 검사
		takenQualiExam = checkTakenQualiExam(takenQualiExam);
		Map<String, Object> map = new HashMap();
		map.put("takenQualiExam", takenQualiExam);
		map.put("examTypeNo", proQualificationExam.getExamTypeNo());
		int result = mapper.insertTakenTechExam(map);
		// 시험 하나당 자동으로 세부과목 2개등록
		// INSERT ALL 하려 했지만 SEQ를 TECH와 같이 사용하도록 설정해놔서 INCREMENT BY 도 쓸 수 없고 결국 2번 갔다와야함
		mapper.insertProSubject(1);
		mapper.insertProSubject(2);
	}
	/*
	CREATE TABLE TB_SUBJECT (
			SUBJECT_NO NUMBER PRIMARY KEY,
			SUBJECT_PERIOD NUMBER DEFAULT 1	NOT NULL,
			EXAM_NO	NUMBER REFERENCES TB_TAKEN_QUALI_EXAM ON DELETE SET NULL
		);

		COMMENT ON COLUMN TB_SUBJECT.SUBJECT_NO IS 'pk';
		COMMENT ON COLUMN TB_SUBJECT.SUBJECT_PERIOD IS '교시';
		COMMENT ON COLUMN TB_SUBJECT.EXAM_NO IS '외래키 시행된 시험';

		DROP SEQUENCE SEQ_SUBJECT_NO;
		CREATE SEQUENCE SEQ_SUBJECT_NO;

		--12
		--리후 가답안
		DROP TABLE TB_PROVISIONAL_ANSWER CASCADE CONSTRAINTS;
		CREATE TABLE TB_PROVISIONAL_ANSWER (
			PROVISIONAL_ANSWER_NO NUMBER PRIMARY KEY,
			SUBJECT_NO NUMBER REFERENCES TB_SUBJECT ON DELETE SET NULL
		);

		COMMENT ON COLUMN TB_PROVISIONAL_ANSWER.PROVISIONAL_ANSWER_NO IS 'pk';
		COMMENT ON COLUMN TB_PROVISIONAL_ANSWER.SUBJECT_NO IS '외래키 세부과목시험 번호';

		DROP SEQUENCE SEQ_PRO_ANSWER_NO;
		CREATE SEQUENCE SEQ_PRO_ANSWER_NO;*/
	private TakenQualiExam checkTakenQualiExam(TakenQualiExam takenQualiExam) {
		// opinion Date가 비어있다면 시험 당일부터 14일후 일주일로 바꾸어줌.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = null;
		if(takenQualiExam.getOpinionStartDate().equals("")) {
			takenQualiExam.setOpinionStartDate(takenQualiExam.getExamStartDate());
			if(takenQualiExam.getOpinionEndDate().equals("")) {
				date = LocalDate.parse(takenQualiExam.getExamFinalDate(), formatter);
				takenQualiExam.setOpinionEndDate(date.plusDays(14).format(formatter));
			}
		} else if(takenQualiExam.getOpinionStartDate().equals("")) {
			date = LocalDate.parse(takenQualiExam.getOpinionStartDate(),formatter);
			takenQualiExam.setOpinionEndDate(date.plusDays(14).format(formatter));
		}
		return takenQualiExam;
	}

	
	/** 원서접수
	 * 현재 접수할 수 있는 시험을 받아와서
	 * 전문자격증은 proList, 기술자격증은 techList로 둘 다 map에 담아서 돌려줌
	 */
	@Override
	public Map<String, Object> getTakenExamList() {
		Map<String, Object> map = new HashMap();
		// 시험접수일자, 시험날짜가 지금으로부터 일주일 이내인 시험들 조회
		List<TakenQualiExam> list = mapper.takenProQualiExamList();
		// ROUND 세서 필드에 대입
		list = takenExamRoundCheck(list);
		map.put("proList", list);
		// tech리스트도 조회
		list = mapper.takenTechQualiExam();
		//테크도 라운드 세서 대입
		list = takenExamRoundCheck(list);
		map.put("techList", list);
		return map;
	}
	@Override
	public List<TakenQualiExam> takenExamRoundCheck(List<TakenQualiExam> list) {
		int round = 0;
		for(TakenQualiExam e : list) {
			round = mapper.getRoundOfExam(e);
			e.setRound(String.valueOf(round));
		}
		return list;
	}
	
	
	/**
	 *takenQualiExam에 round값을 채워줌
	 */
	@Override
	public TakenQualiExam takenExamRoundCheck(TakenQualiExam takenQualiExam) {
		int round = mapper.getRoundOfExam(takenQualiExam);
		takenQualiExam.setRound(String.valueOf(round));
		return takenQualiExam;
	}
	
	
	@Override
	public Map<String, Object> findAllExamPlacesByExam(String exam, String receptionDate, String type) {
		//원서접수
		// Exam 먼저 특정하고 그거에 등록된 시험장소와, 그 날 비어있는 시험장소들을 가져감
		// exam이름이랑 receptionDate일치여부로 시험을 특정하는 메소드
		// 가공 먼저
		String qualificationRank = exam.substring(exam.length() - 2, exam.length()-1);
		String qualificationName = exam.substring(6 , exam.length() - 6);
		String receptionStartDate = receptionDate.substring(0 , 10);
		String round = exam.substring(exam.length() - 5, exam.length()-4);
		//log.info(round+ qualificationRank + qualificationName + receptionStartDate);
		Map<String, Object> map = new HashMap();
		switch(qualificationRank) {
		case "필" : qualificationRank = "1"; break;
		case "실" : qualificationRank = "2"; break;
		default : break;
		}
		map.put("qualificationRank", qualificationRank);
		map.put("qualificationName", qualificationName);
		map.put("receptionStartDate", receptionStartDate);
		TakenQualiExam takenQualiExam = null;
		// 가져가야할 등록된 시험장소목록
		List<ExamPlace> placesOfExam = null;
		if("pro".equals(type)) {
			takenQualiExam = mapper.findTakenProExamByNameAndDate(map);
			placesOfExam = mapper.findAllPlaceOfProExam(takenQualiExam.getExamNo()); 
		} else {
			takenQualiExam = mapper.findTakenTechExamByNameAndDate(map);
			placesOfExam = mapper.findAllPlaceOfTechExam(takenQualiExam.getExamNo()); 
		}
		takenQualiExam.setRound(round);
		
		// 이 시험에 등록된 시험장소 목록
		
		// 겹치는 시험장소 목록 조회
		List<ExamPlace> unavailableTechPlaces = mapper.findAllTechPlaceByDate(takenQualiExam);
		// 혹시 시험일정을 자세히 넣을까봐 따로 만들어놨는데 안 써서 메소드가 똑같음
		//List<ExamPlace> unavailableProPlaces = mapper.findAllProPlaceByDate(takenQualiExam);
		// 전체 장소 중 위의 것이 아닌 장소리스트 출력
		
		// LOCATION_NO리스트 만들기
		List locationNos = new ArrayList();
		for(ExamPlace e : unavailableTechPlaces) {
			locationNos.add(e.getPlace().getLocationNo());
		}
		List<Place> availableTechPlaces = mapper.findAllPlaceByLocationNo(locationNos);
		
		map.clear();
		map.put("placesOfExam", placesOfExam);
		map.put("availablePlaces", availableTechPlaces);
		map.put("takenQualiExam", takenQualiExam);
		return map;
	}
  
  
	@Override
	public void insertExamPlace(int[] insertPlaceNo, Long examNo) {
		Map<String, Object> map = new HashMap();
		for(int locationNo : insertPlaceNo) {
			map.clear();
			map.put("examNo", examNo);
			map.put("locationNo", locationNo);
			mapper.insertExamPlace(map);
		}
	}

	
	
	@Override
	public void applicationInsert(String exam, String receptionDate, String type) {
		
		findAllExamPlacesByExam(exam, receptionDate, type);
		
		
	}
	/**
	 * locaionNo로 ExamPlace, TakenQualiExam, 그
	 */
	@Override
	public Map<String, Object> getTakenExamByNo(Long examLocationNo) {
		String qualificationType = null;
		String relevantDepartment = null;
		int qualificationRank = 0;
		String categoryName = null;
		String fieldName = null;
		String qualificationName = null;

		//examLocationNo로 examNo 반환
		Long examNo = mapper.findExamNoByExamLocationNo(examLocationNo);
		//examLocationNo로 locationNo반환 
		Long locationNo = mapper.findLocationNoByExamLocationNo(examLocationNo);
		
		//examNo로 EXAM_TYPE_NO 반환받기
		Long examTypeNo = mapper.findExamTypeNoByExamNo(examNo);
		
		// EXAM_TYPE_NO로 해당되는 자격증시험 객체 반환받기 위해서
		//QUALIFICATION_NO 를 먼저 반환받기
		Long qualificationNo = mapper.findQualiNoByExamTypeNo(examTypeNo);
		//qualificationNo를 가지고 전문분야인지 기술분야인지 먼저 구분하기
		//examNo로 takenQualiExam객체 받아옴
		TakenQualiExam takenQualiExam = mapper.findTakenQualiExamByExamNo(examNo);
		if(qualificationNo.toString().charAt(0) == 1) { // 기술자격증
			qualificationType = "국가기술자격";
			// examTypeNo 로 TechQualificationExam 객체 반환
			TechQualificationExam techQualificationExam = mapper.findTechQualiExamByExamTypeNo(examTypeNo);
			takenQualiExam.setQualificationExam(techQualificationExam);
			qualificationRank = techQualificationExam.getQualificationRank();
			categoryName = techQualificationExam.getTechnicalQualification().getTechCategory().getCategoryName();
			fieldName =  techQualificationExam.getTechnicalQualification().getTechCategory().getTechnicalField().getFieldName();
			qualificationName = techQualificationExam.getTechnicalQualification().getQualificationName();
		} else { // 전문자격증
			qualificationType = "국가전문자격";
			// examTypeNo 로 ProQualificationExam 객체 반환
			ProQualificationExam proQualificationExam = mapper.findProQualiExamByExamTypeNo(examTypeNo);
			relevantDepartment = proQualificationExam.getProfesionalQualification().getProfesionalDept().getRelevantDepartment();
			takenQualiExam.setQualificationExam(proQualificationExam);
			qualificationRank = proQualificationExam.getQualificationRank();
			qualificationName = proQualificationExam.getProfesionalQualification().getQualificationName();
		}
		takenQualiExam = takenExamRoundCheck(takenQualiExam);
		
		// locationNo로 Place객체 반환
		Place place = mapper.findAllPlaceByLocationNo(locationNo);
		
		// ExamPlace 꽉 채우기
		ExamPlace examPlace = new ExamPlace().builder().examLocationNo(examLocationNo).takenQualiExam(takenQualiExam).place(place).build();
		
		// map에 객체 담기
		Map<String, Object> map = new HashMap();
		// 시행되는 시험 객체
		map.put("takenQualiExam", takenQualiExam);
		// 장소 객체
		map.put("place", place);
		// 시험장소 객체
		map.put("examPlace", examPlace);
		// 국가기술자격, 국가전문자격 스트링
		map.put("qualificationType", qualificationType);
		// 국가전문자격의 경우 관련부처
		map.put("relevantDepartment", relevantDepartment);
		// 국가기술자격의 경우 직무분야명, 
		map.put("categoryName", categoryName);
		// 국가기술자격의 경우 분류명, 
		map.put("fieldName", fieldName);
		// 자격증이름, 국가기술자격의 경우 시행종목, 국가전문자격의 경우 분류
		// 자격증 이름 스트링
		map.put("qualificationName", qualificationName);
		// 1차, 2차인지, 필기 실기인지 여부를 나타내는 rank
		map.put("qualificationRank",qualificationRank);
		// 51회차 등 몇 회차 시험인지를 나타내는 round
		map.put("round", takenQualiExam.getRound());
		// 시험 시작 일자
		map.put("examStartDate", takenQualiExam.getExamStartDate());
		// 시험 종료 일자
		map.put("examFinalDate", takenQualiExam.getExamFinalDate());
		// 시험장소 서울특별시, 경기도 여부
		map.put("cityName", place.getDistrict().getCityName());
		// 시험장소 영등포구 등 여부
		map.put("district", place.getDistrict().getDistrict());
		// 시험장소의 최종 이름 영등포중학교
		map.put("locationName", place.getLocationName());		
		
		return map;
	}
	
	/**
	 * examNo로 관련 location 맵을 반환해줌
	 */
	@Override
	public Map<String, Object> findAllExamPlacesByExamNo(Long examNo) {
		String qualificationType = null;
		String relevantDepartment = null;
		int qualificationRank = 0;
		String categoryName = null;
		String fieldName = null;
		String qualificationName = null;
		
		//examNo로 EXAM_TYPE_NO 반환받기
				Long examTypeNo = mapper.findExamTypeNoByExamNo(examNo);
				
				// EXAM_TYPE_NO로 해당되는 자격증시험 객체 반환받기 위해서
				//QUALIFICATION_NO 를 먼저 반환받기
				Long qualificationNo = mapper.findQualiNoByExamTypeNo(examTypeNo);
				//qualificationNo를 가지고 전문분야인지 기술분야인지 먼저 구분하기
				//examNo로 takenQualiExam객체 받아옴
				TakenQualiExam takenQualiExam = mapper.findTakenQualiExamByExamNo(examNo);
				if(qualificationNo.toString().charAt(0) == 1) { // 기술자격증
					qualificationType = "국가기술자격";
					// examTypeNo 로 TechQualificationExam 객체 반환
					TechQualificationExam techQualificationExam = mapper.findTechQualiExamByExamTypeNo(examTypeNo);
					takenQualiExam.setQualificationExam(techQualificationExam);
					qualificationRank = techQualificationExam.getQualificationRank();
					categoryName = techQualificationExam.getTechnicalQualification().getTechCategory().getCategoryName();
					fieldName =  techQualificationExam.getTechnicalQualification().getTechCategory().getTechnicalField().getFieldName();
					qualificationName = techQualificationExam.getTechnicalQualification().getQualificationName();
				} else { // 전문자격증
					qualificationType = "국가전문자격";
					// examTypeNo 로 ProQualificationExam 객체 반환
					ProQualificationExam proQualificationExam = mapper.findProQualiExamByExamTypeNo(examTypeNo);
					relevantDepartment = proQualificationExam.getProfesionalQualification().getProfesionalDept().getRelevantDepartment();
					takenQualiExam.setQualificationExam(proQualificationExam);
					qualificationRank = proQualificationExam.getQualificationRank();
					qualificationName = proQualificationExam.getProfesionalQualification().getQualificationName();
				}
				takenQualiExam = takenExamRoundCheck(takenQualiExam);
				
				
				
				// 이 시험에 등록된 시험장소 목록
				// examNo로 examPlace 객체 리스트 받아오기
				List<ExamPlace> examPlaceList = mapper.findAllExamPlaceByExamNo2(examNo);
				
				
				// map에 객체 담기
				Map<String, Object> map = new HashMap();
				// 시행되는 시험 객체
				map.put("takenQualiExam", takenQualiExam);
				// 국가기술자격, 국가전문자격 스트링
				map.put("qualificationType", qualificationType);
				// 국가전문자격의 경우 관련부처
				map.put("relevantDepartment", relevantDepartment);
				// 국가기술자격의 경우 직무분야명, 
				map.put("categoryName", categoryName);
				// 국가기술자격의 경우 분류명, 
				map.put("fieldName", fieldName);
				// 자격증이름, 국가기술자격의 경우 시행종목, 국가전문자격의 경우 분류
				// 자격증 이름 스트링
				map.put("qualificationName", qualificationName);
				// 1차, 2차인지, 필기 실기인지 여부를 나타내는 rank
				map.put("qualificationRank",qualificationRank);
				// 51회차 등 몇 회차 시험인지를 나타내는 round
				map.put("round", takenQualiExam.getRound());
				// 시행되는 시험 장소 리스트 담기
				map.put("examPlaceList", examPlaceList);
				
				
				return map;
	}

	

}
