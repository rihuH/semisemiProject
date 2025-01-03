package com.kh.quali.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.quali.member.model.vo.Member;
import com.kh.quali.notice.model.vo.Notice;

@Mapper
public interface NoticeMapper {

	// 글 갯수 확인
	int selectTotalCount();
	
	// 글 전체보기
	List<Notice> selectBoardList();

	// 글 작성
	void insertNotice(Notice notice);

	Member getMemNo(Member member);

	String getMemNo();
	

}
