package com.kh.quali.qualification.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.confirmation.model.vo.Confirmation;
import com.kh.quali.qualification.model.vo.ProfesionalDept.ProfesionalDeptBuilder;
import com.kh.quali.qualification.model.vo.TechnicalField.TechnicalFieldBuilder;
import com.kh.quali.qualification.model.vo.TypeQualification;

@Mapper
public interface QualificationMapper {
	
	List<Confirmation> findAllConfirmation();
	
	List<String> findAllProQualificationName();

	TypeQualification findQualiType(String qualiType);

	List<ProfesionalDeptBuilder> findAllProfesionalDeft();

	List<TechnicalFieldBuilder> findAllTechnicalField();
}
