package com.kh.quali.takenQualiExam.model.service;

import org.springframework.stereotype.Service;

import com.kh.quali.qualification.model.service.QualificationService;
import com.kh.quali.qualification.model.vo.TechnicalQualification;
import com.kh.quali.takenQualiExam.model.dao.TakenQualiExamMapper;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;
import com.kh.quali.takenQualiExam.model.vo.TechQualificationExam;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TakenQualiExamServiceImpl implements TakenQualiExamService{

	private final QualificationService qs;
	private final TakenQualiExamMapper mapper;
	
	@Override
	public void insertTakenTechExam(TakenQualiExam takenQualiExam, String qualificationName, int qualificationRank) {
		TechnicalQualification technicalQualification = qs.findTechByName(qualificationName);
		TechQualificationExam techQualificationExam = new TechQualificationExam().builder().qualificationRank(qualificationRank).technicalQualification(technicalQualification).build();
		techQualificationExam = mapper.findTechExamNoByNameAndRank(techQualificationExam);
		
	}

}
