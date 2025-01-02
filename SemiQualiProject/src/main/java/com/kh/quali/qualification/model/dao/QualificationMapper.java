package com.kh.quali.qualification.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.confirmation.model.vo.Confirmation;
import com.kh.quali.qualification.model.vo.ProfesionalDept;
import com.kh.quali.qualification.model.vo.ProfesionalDept.ProfesionalDeptBuilder;
import com.kh.quali.qualification.model.vo.ProfesionalQualification;
import com.kh.quali.qualification.model.vo.TechCategory;
import com.kh.quali.qualification.model.vo.TechnicalField;
import com.kh.quali.qualification.model.vo.TechnicalField.TechnicalFieldBuilder;
import com.kh.quali.qualification.model.vo.TechnicalQualification;
import com.kh.quali.qualification.model.vo.TypeQualification;

@Mapper
public interface QualificationMapper {
	
	List<Confirmation> findAllConfirmation();
	
	List<String> findAllProQualificationName();

	TypeQualification findQualiType(String qualiType);

	List<ProfesionalDept> findAllProfesionalDeft();

	List<TechnicalField> findAllTechnicalField();

	List<TechCategory> findTechCategoryByField(String fieldSelect);

	TechnicalField findTechFieldByName(String fieldSelect);

	ProfesionalDept findByProDeptName(String categorySelect);

	List<Object> findAllProQualiByCategoryNo(Long categoryNo);

	TechCategory findByTechCategoryName(String categorySelect);

	Long findTechFieldNoInCate(String categorySelect);

	TechnicalField findTechFieldByNo(Long fieldNo);

	List<Object> findAllTechQuliByCategoryNo(Long categoryNo);

	ProfesionalQualification findProQualiByQualiName(String qualificationName);

	int insertPro(ProfesionalQualification profesionalQualification);

	TechnicalQualification findTechQualiByQualiName(String qualificationName);

	int insertProDeft(ProfesionalDept profesionalDept);

	TechnicalField findTechFieldByFieldName(String fieldName);

	int insertTech(TechnicalQualification technicalQualification);

	void insertTechCategory(TechCategory techCategory);

	void insertTechField(TechnicalField technicalField);

	List<TechnicalQualification> findAllTech();

	List<ProfesionalQualification> findAllPro();

	void updateTech(Map<String, TechnicalQualification> map);

	void updatePro(Map<String, ProfesionalQualification> map);





}
