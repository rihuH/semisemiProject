package com.kh.quali.application.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.quali.application.model.dao.ApplicationMapper;
import com.kh.quali.application.model.vo.Application;
import com.kh.quali.takenQualiExam.model.service.TakenQualiExamService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
	
	private final TakenQualiExamService ts;
	private final ApplicationMapper mapper;
	
	@Override
	public Map<String, Object> insertApplication(Long examNo, Long examLocationNo, int memNo) {
		
		// 수험번호 랜덤생성
		int examEntryNo = (int) (Math.random()*10000); 
		
		// Application에 담기
		Application application = new Application();
		application.setExamNo(examNo);
		application.setExamLocationNo(examLocationNo);
		application.setMemNo(memNo);
		application.setExamEntryNo(examEntryNo);
		
		// Application에 담긴 값 insert
		mapper.insertApplication(application);
		
		// 정보 다 불러오기
		Map<String, Object> examInfo = ts.getTakenExamByNo(examLocationNo);//맵을 반환해줌
		
		// 일단 회원 원서접수 테이블 값이나 거기 examLocation 리스트받아서
		// 리스트 for
		// for( examLocationNo : 리스트) {
		// Map = getTaken....(No)...
		// map. 
		
		// 바로 보여줄 정보 불러오기
		List<Application> appList = mapper.selectApplication(memNo);
		
		// map에 담기
		examInfo.put("appList", appList);
		
		log.info("{}", examInfo);
		
		return examInfo;
		
	}
	
	@Override
	public Map<String, Object> selectApplicationList(int memNo, Long examLoacationNo){
		
		Map<String, Object> examInfo = ts.getTakenExamByNo(examLoacationNo);
		
		List<Application> appList = mapper.selectApplication(memNo);
		
		examInfo.put("appList", appList);
		
		return examInfo;
	}

	
	
}
