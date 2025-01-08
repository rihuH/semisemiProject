package com.kh.quali.provisionalAnswer.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.quali.provisionalAnswer.model.dao.ProvisionalAnswerMapper;
import com.kh.quali.takenQualiExam.model.dao.TakenQualiExamMapper;
import com.kh.quali.takenQualiExam.model.service.TakenQualiExamService;
import com.kh.quali.takenQualiExam.model.vo.Subject;
import com.kh.quali.takenQualiExam.model.vo.TakenQualiExam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProvisionalAnswerServiceImpl implements ProvisionalAnswerService {
	
	private final ProvisionalAnswerMapper mapper;
	private final TakenQualiExamMapper tmapper;
	private final TakenQualiExamService ts;
	
	@Override
	public Map<String, Object> findAllSubject() {
		Map<String, Object> map = new HashMap();
		// 종료된 시행시험들에 대한 subject 리스트 조회
		// 종료한 takenQualiExam을 찾아야함
		
		List<TakenQualiExam> takenExams = tmapper.takenProQualiExamListForSubject();
		takenExams = ts.takenExamRoundCheck(takenExams);
		List<Subject> subjects = getSubjectListForExam(takenExams);
		map.put("proSubjectList", subjects);
		takenExams = tmapper.takenTechQualiExamListForSubject();
		takenExams = ts.takenExamRoundCheck(takenExams);
		
		subjects = getSubjectListForExam(takenExams);
		map.put("techSubjectList", subjects);
		
		return map;
	}
	
	// takenExam에 맞는 subject를 모두 받아오는 메소드
	@Override
	public List<Subject> getSubjectListForExam(List<TakenQualiExam> takenExams){
		List<Subject> list = new ArrayList();
		List<Subject> fullList = new ArrayList();
		Subject subject = new Subject();
		for(TakenQualiExam takenQualiExam : takenExams) {
			// exam부분은 나중에 채울거라서 pro나 tech모두 대충 담기
			list = mapper.findSubject(takenQualiExam);
			for(Subject s : list) {
				s.setTakenQualiExam(takenQualiExam);
			}
			fullList.addAll(list);
		}
		return fullList;
	}

	@Override
	public void updateAnswerFile(MultipartFile upfile1, MultipartFile upfile2, String titleAnd) {
		// TODO Auto-generated method stub
		
	}
	

	
}
