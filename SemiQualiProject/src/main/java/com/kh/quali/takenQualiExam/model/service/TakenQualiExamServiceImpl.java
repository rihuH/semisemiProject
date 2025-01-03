package com.kh.quali.takenQualiExam.model.service;

import org.springframework.stereotype.Service;

import com.kh.quali.qualification.model.service.QualificationService;
import com.kh.quali.qualification.model.vo.TechnicalQualification;
import com.kh.quali.takenQualiExam.model.dao.TakenQualiExamMapper;
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
		TechnicalQualification technicalQualification = qs.findTechByName(qualificationName);
		TechQualificationExam techQualificationExam = new TechQualificationExam().builder().qualificationRank(qualificationRank).technicalQualification(technicalQualification).build();
		techQualificationExam = mapper.findTechExamNoByNameAndRank(techQualificationExam);
		log.info("{}", techQualificationExam);
		// takenQualiExam 필드 잘 됐나 검사 메소드
		takenQualiExam = checkTakenQualiExam(takenQualiExam);
	}

	private TakenQualiExam checkTakenQualiExam(TakenQualiExam takenQualiExam) {
		// opinion Date가 비어있다면 시험 전, 후 일주일로 바꾸어줌.
		if(takenQualiExam.getOpinionStartDate().equals("")) {
			
		}
		if(takenQualiExam.getOpinionEndDate().equals("")) {
			
		}
		return null;
	}
	

}
