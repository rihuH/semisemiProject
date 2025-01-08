package com.kh.quali.provisionalAnswer.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.quali.takenQualiExam.model.vo.Subject;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;

public interface ProvisionalAnswerService {

	Map<String, Object> findAllSubject();

	List<Subject> getSubjectListForExam(List<TakenQualiExam> takenExams);


	void updateAnswerFile(MultipartFile[] upfile, Long subjectNo);

}
