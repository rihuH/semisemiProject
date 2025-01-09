package com.kh.quali.provisionalAnswer.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.quali.takenQualiExam.model.vo.Subject;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;

public interface ProvisionalAnswerService {

	Map<String, Object> findAllSubject();

	List<Subject> getSubjectListForExam(List<TakenQualiExam> takenExams);


	List<Subject> getAnswersBySubject(List<Subject> subjects);
	

	Map<String, Object> findSubjectByNo(Long subjectNo);

	void updateAnswerFile(MultipartFile[] upfiles, Long subjectNo, String file1_del, String file2_del);

}
