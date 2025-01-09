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
		// 각 subject객체에 연결된 provisionalAnswer들을 subjectList 필드에 채워줌
		subjects = getAnswersBySubject(subjects);
		map.put("proSubjectList", subjects);
		
		takenExams = tmapper.takenTechQualiExamListForSubject();
		takenExams = ts.takenExamRoundCheck(takenExams);
		
		subjects = getSubjectListForExam(takenExams);
		subjects = getAnswersBySubject(subjects);
		map.put("techSubjectList", subjects);
		
		return map;
	}
	
	@Override
	public List<Subject> getAnswersBySubject(List<Subject> subjects) {
		// 돌려줄 맵. subjectNo와 List<Subject>를 키:밸류로 담아간다
		Subject subject = null;
		for(int i = 0; i < subjects.size(); i++) {
			// subjectNo로 조회해서 해당되는 ProvisionalAnswer를 모두 가져옴
			subject = subjects.get(i);
			List<ProvisionalAnswer> list = mapper.findProvisionalAnswerBySubject(subject);
			// provision 번호 가공해주는 메소드
			for(ProvisionalAnswer p : list) {
				p = changedFileNo(p);
			}
			// 이 list를 subject필드에 대입
			subject.setProvisionalAnswers(list);
			// list에서 subject 수정
		}
		return subjects;
	}



	private ProvisionalAnswer changedFileNo(ProvisionalAnswer p) {
		String originalChangeName = p.getChangedFileName();
		String orNo = originalChangeName.substring(originalChangeName.lastIndexOf(".") - 1,originalChangeName.lastIndexOf(".") );
		p.setChangedFileNo(orNo);
		return p;
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
	public void updateAnswerFile(MultipartFile[] upfiles, Long subjectNo, 
			String file1del, String file2del) {
		Subject subject = new Subject().builder().subjectNo(subjectNo).build();
		Map<String, Object> map = new HashMap();
		// 첨부된 파일 있으면 
		log.info("서비스 upfiles {} 스트링1 {} 스트링2 ", upfiles, file1del +"|"+ file2del);
		// 원래 있는 파일들의 존재를 확인하고
		List<ProvisionalAnswer> provisionalAnswerList = mapper.findProvisionalAnswerBySubject(subject);
		
		//이름 가공하고
		String originalFileName = null;
		// 물리적 경로
		String savePath = context.getRealPath("/resources/upload_files/provisional_answer_files/");
		for(int i = 0; i < upfiles.length; i++) {
			// 기존 파일중 동일한 파일이 있는지 확인
			originalFileName = upfiles[i].getOriginalFilename();
			if(!!!"".equals(originalFileName)) {
				for(ProvisionalAnswer p : provisionalAnswerList) {
					// 있으면 가서 status를 n로 바꾸기
					// 비교할 값
					String originalChangeName = p.getChangedFileName();
					
					String orNo = originalChangeName.substring(originalChangeName.lastIndexOf(".") - 1,originalChangeName.lastIndexOf(".") );
					String intStr = String.valueOf(i);
					
					log.info("orNo {} intStr {} ", orNo, intStr);
					if(intStr.equals(orNo)) {
						// 있으면
						String find = "order_" + orNo;
						map.clear();
						map.put("find", find);
						map.put("subjectNo", subjectNo);
						mapper.deleteProvisionalAnswerByOrderNo(map); // 지움
					}
				}
				// 메소드에 넘겨주기
				uploadFile(upfiles[i], originalFileName, subject, savePath, i);
			} else {
				if(i == 0 && ("delete".equals(file1del))) {
					String find = "order_0";
					map.clear();
					map.put("find", find);
					map.put("subjectNo", subjectNo);
					mapper.deleteProvisionalAnswerByOrderNo(map); // 지움
				}
				if(i == 1 && "delete".equals(file2del)) {
					String find = "order_1";
					map.clear();
					map.put("find", find);
					map.put("subjectNo", subjectNo);
					mapper.deleteProvisionalAnswerByOrderNo(map); // 지움
				}
				
				
			}
		}
		
		
		
	}

	private void uploadFile(MultipartFile upfile, String originalFileName, Subject subject, String savePath, int i) {
		String changedFileName = null;
		String filePath = null;
		
		// 이름 수정해서 String을 돌려줌 이름 수정해주는 메소드
		changedFileName = changeFileName(originalFileName, subject.getSubjectNo(), i);
		filePath = "/quali/resources/upload_files/provisional_answer_files/" + changedFileName;
		ProvisionalAnswer provisionalAnswer = new ProvisionalAnswer().builder().subjectNo(subject.getSubjectNo()).originalFileName(originalFileName).changedFileName(changedFileName).filePath(filePath).build();
		mapper.insertProvisionalAnswerFile(provisionalAnswer);
		
		try {
			upfile.transferTo(new File(savePath + changedFileName));
		} catch (IllegalStateException | IOException e) {
			
		}
		
	}

	private String changeFileName(String originalFileName, Long subjectNo, int i) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String localdate = LocalDateTime.now().format(formatter);
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		return localdate + (Math.random()*90000) + "subjectNo_" + subjectNo + "order_" + i + ext;
	}

	@Override
	public Map<String, Object> findSubjectByNo(Long subjectNo) {
		Subject subject = mapper.findSubjectByNo(subjectNo);
		List<ProvisionalAnswer> list = mapper.findProvisionalAnswerBySubject(subject);
		String firstFile = null;
		String SecondFile = null;
		Map<String, Object> map = new HashMap();
		ProvisionalAnswer p = null;
		String changedFileName = null;
		for(int i = 0; i < list.size(); i++) {
			p = list.get(i);
			changedFileName = p.getChangedFileName();
			String number = changedFileName.substring(changedFileName.lastIndexOf(".") - 1,changedFileName.lastIndexOf(".") );
			p.setSubjectNo(subjectNo);
			p = changedFileNo(p);
			map.put("File" + number, list.get(i).getFilePath());
		}
		// 이 list를 subject필드에 대입
		subject.setProvisionalAnswers(list);
		map.put("subject", subject);
		return map;
	}



	

	
}
