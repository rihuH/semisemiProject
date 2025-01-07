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
import com.kh.quali.qualification.model.vo.ProfesionalQualification;
import com.kh.quali.qualification.model.vo.TechCategory;
import com.kh.quali.qualification.model.vo.TechnicalField;
import com.kh.quali.qualification.model.vo.TechnicalQualification;
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
				((ProfesionalDept)(fieldList.get(i))).setTypeQualification(type);
			}
		} else {
			for(int i = 0; i < fieldList.size(); i++) {
				((TechnicalField)(fieldList.get(i))).setTypeQualification(type);
			}
		}
		return fieldList;
	}

	@Override
	public List<TechCategory> selectFieldType(String fieldSelect) {
		TypeQualification type = mapper.findQualiType("국가기술자격");
		TechnicalField field = mapper.findTechFieldByName(fieldSelect);
		field.setTypeQualification(type);
		List<TechCategory> techCategoryList = mapper.findTechCategoryByField(fieldSelect);
		techCategoryList = makeFieldCategory(techCategoryList, field);
		return techCategoryList;
	}
	private List<TechCategory> makeFieldCategory(List<TechCategory> techCategoryList, TechnicalField field){
		for(int i = 0; i < techCategoryList.size(); i++) {
			techCategoryList.get(i).setTechnicalField(field);
		}
		return techCategoryList;
	}

	@Override
	public List<Object> findQualiByCategory(String categorySelect, String typeStr) {
		typeStr = ("pro").equals(typeStr) ? "국가전문자격" : "국가기술자격";
		TypeQualification type = mapper.findQualiType(typeStr);
		List<Object> qualiList = new ArrayList();
		//국가전문자격
		if("국가전문자격".equals(typeStr)) {
			qualiList = findAllProQualiByCategory(type, categorySelect);
		} else {
			qualiList = findAllTechQualiByCategory(type, categorySelect);
		}
		return qualiList;
	}

	private List<Object> findAllTechQualiByCategory(TypeQualification type, String categorySelect) {
		Long fieldNo = mapper.findTechFieldNoInCate(categorySelect);
		TechnicalField technicalField = mapper.findTechFieldByNo(fieldNo);
		technicalField.setTypeQualification(type);
		TechCategory techCategory = mapper.findByTechCategoryName(categorySelect);
		techCategory.setTechnicalField(technicalField);
		//기술자격증
		Long categoryNo = techCategory.getCategoryNo();
		List<Object> qualiList = mapper.findAllTechQuliByCategoryNo(categoryNo);
		for(int i = 0; i < qualiList.size(); i++) {
			((TechnicalQualification)(qualiList.get(i))).setTechCategory(techCategory);
		}
		return qualiList;
	}

	private List<Object> findAllProQualiByCategory(TypeQualification type, String categorySelect) {
		ProfesionalDept profesionalDept = mapper.findByProDeptName(categorySelect);
		profesionalDept.setTypeQualification(type);
		Long categoryNo = profesionalDept.getCategoryNo();
		List<Object> qualiList = mapper.findAllProQualiByCategoryNo(categoryNo);
		for(int i = 0; i < qualiList.size(); i++) {
			((ProfesionalQualification)qualiList.get(i)).setProfesionalDept(profesionalDept);
		}
		log.info("{}", qualiList);
		return qualiList;
	}

	@Override
	public void insertPro(String qualificationName, String relevantDepartment) {
		TypeQualification typeQualification = mapper.findQualiType("국가전문자격");
		// 입력한 관련부처가 있는지 검사
		ProfesionalDept profesionalDept = mapper.findByProDeptName(relevantDepartment);
		// 자격증이름으로 있는지 검색하는 메소드
		int isQuali = isQualiName(qualificationName);
		ProfesionalQualification profesionalQualification = mapper.findProQualiByQualiName(qualificationName);
		if(isQuali > 0) {
			log.info("동일한 이름의 자격증 등록불가");
		} else if(profesionalDept != null) {
			// 관련부처 있으면 타입 대입
			profesionalDept.setTypeQualification(typeQualification);
			// 자격증이름이 있는지 검사
				profesionalQualification = new ProfesionalQualification();
				profesionalQualification.setProfesionalDept(profesionalDept);
				profesionalQualification.setQualificationName(qualificationName);
				log.info("서비스 : {}대입할", profesionalQualification);
				int result = mapper.insertPro(profesionalQualification);
				log.info("서비스 : {}성공", result);
		} else {
			// 관련부처 없음
			// 관련부처 insert하고,
			profesionalDept = new ProfesionalDept();
			profesionalDept.setTypeQualification(typeQualification);
			profesionalDept.setRelevantDepartment(relevantDepartment);
			mapper.insertProDeft(profesionalDept);
			profesionalDept = mapper.findByProDeptName(relevantDepartment);
			//동일 넘버로 자격증도 인서트하기.
			profesionalQualification = new ProfesionalQualification();
			profesionalQualification.setProfesionalDept(profesionalDept);
			profesionalQualification.setQualificationName(qualificationName);
			log.info("서비스 : {}대입할자격증", profesionalQualification);
			int result = mapper.insertPro(profesionalQualification);
			log.info("서비스 : {}성공", result);
		}
	}
	private int isQualiName(String qualificationName) {
		ProfesionalQualification profesionalQualification = mapper.findProQualiByQualiName(qualificationName);
		TechnicalQualification technicalQualification = mapper.findTechQualiByQualiName(qualificationName);
		if(profesionalQualification == null && technicalQualification == null) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public void insertTech(String fieldName, String categoryName, String qualificationName) {
		int isQuali = isQualiName(qualificationName);
		if(isQuali > 0) {
			log.info("동일한 이름의 자격증 등록불가");
		} else {
			TechnicalQualification technicalQualification = new TechnicalQualification();
			TypeQualification typeQualification = mapper.findQualiType("국가기술자격");
			TechnicalField technicalField = mapper.findTechFieldByFieldName(fieldName);// 직무분야명 필드명검색
			if(technicalField == null) { // 필드 없으면
				technicalField = new TechnicalField();
				technicalField.setTypeQualification(typeQualification);
				technicalField.setFieldName(fieldName);
				mapper.insertTechField(technicalField);
				technicalField = mapper.findTechFieldByFieldName(fieldName);
			}
			technicalField.setTypeQualification(typeQualification);
			// 분류명 검색
			TechCategory techCategory = mapper.findByTechCategoryName(categoryName);
			if(techCategory == null) { // 분류명 없으면
				techCategory = new TechCategory();
				techCategory.setCategoryName(categoryName);
				techCategory.setTechnicalField(technicalField);
				mapper.insertTechCategory(techCategory);
				techCategory = mapper.findByTechCategoryName(categoryName);
				// 자격증인서트
			}
			techCategory.setTechnicalField(technicalField);
			technicalQualification.setTechCategory(techCategory);
			technicalQualification.setQualificationName(qualificationName);
			int result = mapper.insertTech(technicalQualification);
			log.info("성공");
		}
	}

	@Override
	public List<TechnicalQualification> findAllTech() {
		List<TechnicalQualification> techList = mapper.findAllTech();
		return techList;
	}

	@Override
	public List<ProfesionalQualification> findAllPro() {
		List<ProfesionalQualification> proList = mapper.findAllPro();
		return proList;
	}

	@Override
	public void updateTech(String updatedQualiName, String qualiName) {
		TechnicalQualification originalTechnicalQualification = new TechnicalQualification().builder().qualificationName(qualiName).build();
		TechnicalQualification updateTechnicalQualification = new TechnicalQualification().builder().qualificationName(updatedQualiName).build();
		Map<String, TechnicalQualification> map = new HashMap();
		map.put("originalTechnicalQualification", originalTechnicalQualification);
		map.put("updateTechnicalQualification", updateTechnicalQualification);
		mapper.updateTech(map);
	}

	@Override
	public void updatePro(String updatedQualiName, String qualiName) {
		ProfesionalQualification originalProfesionalQualification = new ProfesionalQualification().builder().qualificationName(qualiName).build();
		ProfesionalQualification updateProfesionalQualification = new ProfesionalQualification().builder().qualificationName(updatedQualiName).build();
		Map<String, ProfesionalQualification> map = new HashMap();
		map.put("originalProfesionalQualification", originalProfesionalQualification);
		map.put("updateProfesionalQualification", updateProfesionalQualification);
		mapper.updatePro(map);
		
		
	}

	@Override
	public List<TechnicalQualification> searchTechName(String searched) {
		List<TechnicalQualification> techList =  mapper.searchInTechQualiName(searched);
		return techList;
	}

	@Override
	public List<ProfesionalQualification> searchProName(String searched) {
		List<ProfesionalQualification> proList = mapper.searchInProQualiName(searched);
		return proList;
	}

	@Override
	public ProfesionalQualification findProQualiByName(String qualificationName) {
		ProfesionalQualification profesionalQualification = mapper.findProQualiByQualiName(qualificationName);
		return profesionalQualification;
	}

	@Override
	public TechnicalQualification findTechQualiByName(String qualificationName) {
		TechnicalQualification technicalQualification = mapper.findTechQualiByQualiName(qualificationName); 
		return technicalQualification;
	}


}
