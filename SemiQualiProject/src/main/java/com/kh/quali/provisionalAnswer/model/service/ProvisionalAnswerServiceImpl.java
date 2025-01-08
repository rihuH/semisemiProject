package com.kh.quali.provisionalAnswer.model.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.quali.provisionalAnswer.model.dao.ProvisionalAnswerMapper;
import com.kh.quali.provisionalAnswer.model.vo.ProvisionalAnswer;
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
	private final ServletContext context;
	
	@Override
	public Map<String, Object> findAllSubject() {
		Map<String, Object> map = new HashMap();
		// 종료된 시행시험들에 대한 subject 리스트 조회
		// 종료한 takenQualiExam을 찾아야함
		List<TakenQualiExam> takenExams = tmapper.takenProQualiExamListForSubject();
		takenExams = ts.takenExamRoundCheck(takenExams);
		List<Subject> subjects = getSubjectListForExam(takenExams);
		log.info("파인드{}", subjects);
		// 각 subject객체에 연결된 provisionalAnswer들을 subjectList 필드에 채워줌
		subjects = getAnswersBySubject(subjects);
		map.put("proSubjectList", subjects);
		
		takenExams = tmapper.takenTechQualiExamListForSubject();
		takenExams = ts.takenExamRoundCheck(takenExams);
		
		subjects = getSubjectListForExam(takenExams);
		log.info("파인드2{}", subjects);
		subjects = getAnswersBySubject(subjects);
		map.put("techSubjectList", subjects);
		
		return map;
	}
	
	@Override
	public List<Subject> getAnswersBySubject(List<Subject> subjects) {
		// 돌려줄 맵. subjectNo와 List<Subject>를 키:밸류로 담아간다
		Subject subject = null;
		log.info("{}", subjects.size());
		for(int i = 0; i < subjects.size(); i++) {
			// subjectNo로 조회해서 해당되는 ProvisionalAnswer를 모두 가져옴
			subject = subjects.get(i);
			List<ProvisionalAnswer> list = mapper.findProvisionalAnswerBySubject(subject);
			// 이 list를 subject필드에 대입
			subject.setProvisionalAnswers(list);
			log.info("{}", subject);
			// list에서 subject 수정
		}
		return subjects;
	}

	// takenExam에 맞는 subject를 모두 받아오는 메소드
	@Override
	public List<Subject> getSubjectListForExam(List<TakenQualiExam> takenExams){
		List<Subject> list = new ArrayList();
		List<Subject> fullList = new ArrayList();
		Subject subject = new Subject();
		log.info("테이큰{}", takenExams);
		for(TakenQualiExam takenQualiExam : takenExams) {
			// exam부분은 나중에 채울거라서 pro나 tech모두 대충 담기
			list = mapper.findSubject(takenQualiExam);
			log.info("리스트{}", list);
			for(Subject s : list) {
				s.setTakenQualiExam(takenQualiExam);
			}
			fullList.addAll(list);
			log.info("풀리스트{}", fullList);
		}
		return fullList;
	}


	@Override
	public void updateAnswerFile(MultipartFile[] upfiles, Long subjectNo) {
		// 파일 있으면 이름 가공하고
		Subject subject = new Subject().builder().subjectNo(subjectNo).build();
		String originalFileName = null;
		String savePath = context.getRealPath("/resources/upload_files/provisional_answer_files/");
		for(MultipartFile upfile : upfiles) {
			
			originalFileName = upfile.getOriginalFilename();
			
			if(!!!"".equals(originalFileName)) {
				// 메소드에 넘겨주기
				uploadFile(upfile, originalFileName, subject, savePath);
			}
		}
	}

	private void uploadFile(MultipartFile upfile, String originalFileName, Subject subject, String savePath) {
		String changedFileName = null;
		String filePath = null;
		
		// 이름 수정해서 String을 돌려줌
		changedFileName = changeFileName(originalFileName, subject.getSubjectNo());
		filePath = "/quali/resources/upload_files/provisional_answer_files/" + changedFileName;
		ProvisionalAnswer provisionalAnswer = new ProvisionalAnswer().builder().subjectNo(subject.getSubjectNo()).originalFileName(originalFileName).changedFileName(changedFileName).filePath(filePath).build();
		mapper.insertProvisionalAnswerFile(provisionalAnswer);
		
		try {
			upfile.transferTo(new File(savePath + changedFileName));
		} catch (IllegalStateException | IOException e) {
			
		}
		
	}

	private String changeFileName(String originalFileName, Long subjectNo) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String localdate = LocalDateTime.now().format(formatter);
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		
		return localdate + (Math.random()*90000) + "subjectNo&" + subjectNo + ext;
	}



	

	
}
