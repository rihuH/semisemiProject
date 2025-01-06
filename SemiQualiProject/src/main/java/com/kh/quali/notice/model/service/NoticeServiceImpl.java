package com.kh.quali.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.kh.quali.exception.BoardNoValueException;
import com.kh.quali.exception.BoardNotFoundException;
import com.kh.quali.exception.InvalidParameterException;
import com.kh.quali.member.model.vo.Member;
import com.kh.quali.notice.model.dao.NoticeMapper;
import com.kh.quali.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

	private final NoticeMapper mapper;
	private final ServletContext context;
	
	
	// 게시글 있는지 없는지 확인하는 메소드
	private int getTotalCount() {
		int totalCount = mapper.selectTotalCount();
		if(totalCount == 0) {
			throw new BoardNotFoundException("게시글이 없습니다.");
		}
		return totalCount;
	}
	
	// 게시글 형식 검사 메소드
	private void validateBoard(Notice notice) {
		if(notice == null ||
		   notice.getNoticeTitle() == null || notice.getNoticeTitle().trim().isEmpty() ||
		   notice.getNoticeContent() == null || notice.getNoticeContent().trim().isEmpty() ||
		   notice.getMemNo() == null || notice.getMemNo().trim().isEmpty()) {
			throw new BoardNoValueException("부적절한 입력값");
		}
		
		String boardTitle = escapeHtml(notice.getNoticeTitle());
		String boardContent = escapeHtml(notice.getNoticeContent());
		notice.setNoticeTitle(covertNewlineToBr(boardTitle));
		notice.setNoticeContent(covertNewlineToBr(boardContent));
		
	}
	
	// 게시글 공격 방지 메소드 1
	private String escapeHtml(String value) {
		return value.replaceAll("<", "&lt;")
					.replaceAll(">", "&gt;");
	}
	
	// 게시글 공격 방지 메소드 2
	private String covertNewlineToBr(String value) {
		return value.replaceAll("\n", "<br>");
	}
	
	//-----------------------------------------------------------------
	
	// 게시글 번호 검증 메서드
	// 수업 때 사용한 long 대신 int를 사용하니 문제가 발생함.
	// long은 참조자료형이라 null 값이 들어갈 수 있지만 int에는 null값이 들어갈 수 없기때문
	// int는 항상 초기화된 값을 가지기때문에 null값이 들어갈 수 없다.
	// int대신 Integer를 사용하면 Integer는 참조자료형이기때문에 null값을 허용해 사용가능하다.
	private void validateBoardNo(int noticeNo) {
		if(noticeNo <= 0) {
			throw new InvalidParameterException("게시글 번호가 유호하지 않습니다.");
		}
	}
	
	private Notice findBoardById(int noticeNo) {
		Notice notice = mapper.selectById(noticeNo);
		if(notice == null) {
			throw new BoardNotFoundException("게시글을 찾을 수 없습니다");
		}
		return notice;
	}
	
	@Override
	public Map<String, Object> selectNoticeList() {
		
		// 게시글이 있는지, 없는지 먼저 확인한다.
		int totalCount = getTotalCount();
		
		// 게시글이 있는걸 확인했다면 게시글 정보를 불러온다.
		List<Notice> noticeList = mapper.selectBoardList();
		
		Map<String, Object> map = new HashMap();
		
		map.put("noticeList", noticeList);
		
		return map;
	}
	
	/*
	private String getMemNo(Notice notice, Member member){
		String result = notice.setMemNo(mapper.getMemNo());
		
		log.info("{}", result);
		
		return result;
	}
	*/

	@Override
	public void insertNotice(Notice notice, Member member) {
		
		// memNo에 담겨있는 값이 MEMBER_ID 값으로 오기때문에 MEM_NO로 변환
		
		log.info("{}", notice);
		
		//getMemNo(notice, member);
		mapper.insertNotice(notice);
		
	}

	@Override
	public Map<String, Object> selectNoticeId(int noticeNo) {
		
		validateBoardNo(noticeNo);
		
		Notice notice = findBoardById(noticeNo);
		
		Map<String, Object> responseData = new HashMap();
		responseData.put("noticeList", notice);
		
		return responseData;
	}

	@Override
	public void updateNotice(Notice notice) {
		validateBoardNo(notice.getNoticeNo());
		findBoardById(notice.getNoticeNo());
		
		int result = mapper.updateBoard(notice);
		
		if(result <= 0) {
			throw new BoardNotFoundException("업데이트에 실패했습니다.");
		}
		
	}
	
	@Override
	public void deleteNotice(int noticeNo) {
		
		Notice notice = findBoardById(noticeNo);
		
		int result = mapper.deleteBoard(noticeNo);
		
		if(result <= 0) {
			throw new BoardNotFoundException("게시글 삭제에 실패했습니다.");
		}
		
	}

}
