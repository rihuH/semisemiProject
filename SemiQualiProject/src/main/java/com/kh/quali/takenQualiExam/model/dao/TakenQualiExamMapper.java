package com.kh.quali.takenQualiExam.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.takenQualiExam.model.vo.TechQualificationExam;

@Mapper
public interface TakenQualiExamMapper {

	TechQualificationExam findTechExamNoByNameAndRank(TechQualificationExam techQualificationExam);

}
