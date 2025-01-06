package com.kh.quali.takenQualiExam.model.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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
	}
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
	}

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
		log.info("{}", map);
		return map;
	}

	private List<TakenQualiExam> takenExamRoundCheck(List<TakenQualiExam> list) {
		int round = 0;
		for(TakenQualiExam e : list) {
			log.info("for문 {}", e);
			round = mapper.getRoundOfExam(e);
			e.setRound(String.valueOf(round));
		}
		return list;
	}
	@Override
	public List<ExamPlace> findAllExamPlacesByExam(String exam, String receptionDate) {
		
		return null;
	}


	

}
