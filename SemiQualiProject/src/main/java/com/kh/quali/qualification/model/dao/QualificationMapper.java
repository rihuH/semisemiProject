package com.kh.quali.qualification.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.qualification.model.vo.Confirmation;

@Mapper
public interface QualificationMapper {

	List<Confirmation> findAllConfirmation();
	
	List<String> findAllProQuailficationName();
}
