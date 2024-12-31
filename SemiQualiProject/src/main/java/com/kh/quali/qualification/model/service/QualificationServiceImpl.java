package com.kh.quali.qualification.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.kh.quali.confirmation.model.vo.Confirmation;
import com.kh.quali.qualification.model.dao.QualificationMapper;
import com.kh.quali.qualification.model.vo.ProfesionalDept;
import com.kh.quali.qualification.model.vo.TechnicalField;
import com.kh.quali.qualification.model.vo.TypeQualification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class QualificationServiceImpl implements QualificationService {

	private final QualificationMapper mapper;
	private final ServletContext context;
	
	@Override
	public List<Confirmation> findAllConfirmation() {
		List<Confirmation> list = mapper.findAllConfirmation();
		return list;
	}

	@Override
	public List<String> findAllProQualificationName() {
		List<String> list = mapper.findAllProQualificationName();
		return list;
	}

	@Override
	public List selectQualiType(String qualiType) {
		log.info("서비스 {}", qualiType);
		TypeQualification type = mapper.findQualiType(qualiType);
		List fieldList = new ArrayList();
		if(type.getQualificationCode().equals("A")) {
			fieldList = mapper.findAllProfesionalDeft();
		} else {
			fieldList = mapper.findAllTechnicalField();
		}
		fieldList = makeFieldVo(type, fieldList);
		return fieldList;
	}
	private List makeFieldVo(TypeQualification type, List fieldList) {
		if(fieldList.get(0) instanceof ProfesionalDept) {
			for(int i = 0; i < fieldList.size(); i++) {
				((ProfesionalDept)(fieldList.get(i))).setType(type);
			}
		} else {
			for(int i = 0; i < fieldList.size(); i++) {
				((TechnicalField)(fieldList.get(i))).setType(type);
			}
		}
		return fieldList;
	}

}
