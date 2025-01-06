package com.kh.quali.takenQualiExam.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.takenQualiExam.model.vo.ProQualificationExam;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;
import com.kh.quali.takenQualiExam.model.vo.TechQualificationExam;

@Mapper
public interface TakenQualiExamMapper {

	TechQualificationExam findTechExamNoByNameAndRank(TechQualificationExam techQualificationExam);

	int insertTakenTechExam(Map<String, Object> map);

	List<TakenQualiExam> takenProQualiExamList();

	List<TakenQualiExam> takenTechQualiExam();

	int getRoundOfExam(TakenQualiExam e);

	ProQualificationExam findProExamByNameAndRank(ProQualificationExam proQualificationExam);


}
