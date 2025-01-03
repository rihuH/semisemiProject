package com.kh.quali.qualification.model.service;

import java.util.List;
import java.util.Map;

import com.kh.quali.confirmation.model.vo.Confirmation;
import com.kh.quali.qualification.model.vo.ProfesionalQualification;
import com.kh.quali.qualification.model.vo.TechCategory;
import com.kh.quali.qualification.model.vo.TechnicalQualification;

public interface QualificationService {
	
	List<Confirmation> findAllConfirmation();
	
	List<String> findAllProQualificationName();
	
	List selectQualiType(String qualiType);

	List<TechCategory> selectFieldType(String fieldSelect);

	List<Object> findQualiByCategory(String categorySelect, String type);

	void insertPro(String qualificationName, String relevantDepartment);

	void insertTech(String fieldName, String categoryName, String qualificationName);

	List<TechnicalQualification> findAllTech();

	List<ProfesionalQualification> findAllPro();

	TechnicalQualification findTechByName(String qualificationName);

	void updateTech(String updatedQualiName, String qualiName);

	void updatePro(String updatedQualiName, String qualiName);

	List<TechnicalQualification> searchTechName(String searched);

	List<ProfesionalQualification> searchProName(String searched);

}
