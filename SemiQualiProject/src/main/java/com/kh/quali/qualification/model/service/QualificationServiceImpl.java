package com.kh.quali.qualification.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.quali.qualification.model.dao.QualificationMapper;
import com.kh.quali.qualification.model.vo.Confirmation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QualificationServiceImpl implements QualificationService {

	private final QualificationMapper mapper;
	
	@Override
	public List<Confirmation> findAllConfirmation() {
		List<Confirmation> list = mapper.findAllConfirmation();
		return list;
	}

	@Override
	public List<String> findAllQuailficationName() {
		List<String> list = mapper.findAllQuailficationName();
		return list;
	}

}
