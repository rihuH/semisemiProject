package com.kh.quali.qualification.model.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.kh.quali.qualification.model.dao.QualificationMapper;
import com.kh.quali.qualification.model.vo.Confirmation;

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

}
