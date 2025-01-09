package com.kh.quali.application.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.application.model.vo.Application;

@Mapper
public interface ApplicationMapper {

	void insertApplication(Application application);

	List<Application> selectApplication(int memNo);
	
}
