package com.kh.quali.provisionalAnswer.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.provisionalAnswer.model.vo.ProvisionalAnswer;
import com.kh.quali.takenQualiExam.model.vo.Subject;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;

@Mapper
public interface ProvisionalAnswerMapper {

	List<Subject> findAllProSubject();

	List<Subject> findAllTechSubject();

	List<Subject> findSubject(TakenQualiExam takenQualiExam);

	void insertProvisionalAnswerFile(ProvisionalAnswer provisionalAnswer);

	List<ProvisionalAnswer> findProvisionalAnswerBySubject(Subject subject);
	

}
