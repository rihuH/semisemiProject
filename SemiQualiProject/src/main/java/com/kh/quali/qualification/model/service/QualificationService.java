package com.kh.quali.qualification.model.service;

import java.util.List;

import com.kh.quali.qualification.model.vo.Confirmation;

public interface QualificationService {
	
	List<Confirmation> findAllConfirmation();
	
	List<String> findAllProQualificationName();

}
