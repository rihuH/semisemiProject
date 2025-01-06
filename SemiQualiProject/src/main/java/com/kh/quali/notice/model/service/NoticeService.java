package com.kh.quali.notice.model.service;

import java.util.List;
import java.util.Map;

import com.kh.quali.member.model.vo.Member;
import com.kh.quali.notice.model.vo.Notice;

public interface NoticeService {
	// 리스트 전체보기
	Map<String, Object> selectNoticeList();

	
	// 상세보기
	
	
	// 글 작성
	void insertNotice(Notice notice, Member member);


	Map<String, Object> selectNoticeId(int noticeNo);

	
	// 글 수정
	void updateNotice(Notice notice);
	
	// 글 삭제
	void deleteNotice(int noticeNo);
	
	// 공지에 댓글은 작성못하게할것.
}
