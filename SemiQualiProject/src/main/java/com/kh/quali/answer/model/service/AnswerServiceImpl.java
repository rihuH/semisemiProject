package com.kh.quali.answer.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.quali.answer.model.dao.AnswerMapper;
import com.kh.quali.answer.model.vo.Answer;
import com.kh.quali.comment.model.vo.Comment;
import com.kh.quali.common.model.vo.PageInfo;
import com.kh.quali.common.template.Pagination;
import com.kh.quali.exception.BoardNoValueException;
import com.kh.quali.exception.BoardNotFoundException;
import com.kh.quali.exception.InvalidParameterException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerServiceImpl implements AnswerService {
	
	private final AnswerMapper mapper;
	private final ServletContext context;

	// 게시글의 총 개수 확인 메소드
	private int getTotalCount() {
		int totalCount = mapper.selectTotalCount();
		
		return totalCount;
	}
	
	private PageInfo getPageInfo(int totalCount, int page) {
		//총 페이지를 나누는 메소드, 몇페이지인가, 한 페이지에 몇개 보여줄건가
		return Pagination.getPageInfo(totalCount, page, 5, 10);
	}
	
	private List<Answer> getBoardList(PageInfo pi){
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());

		return mapper.selectBoardList(rowBounds);
	}
	
	private List<Comment> getCommentList(PageInfo pi){
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return mapper.getCommentList(rowBounds);
	}
	
	// 게시글 형식 검사 메소드
	private void validateBoard(Answer answer) {
		if(answer == null ||
			answer.getAnswerTitle() == null || answer.getAnswerTitle().trim().isEmpty() ||
			answer.getAnswerContent() == null || answer.getAnswerContent().trim().isEmpty() ||
			answer.getMemNo() == null || answer.getMemNo().trim().isEmpty()) {
			throw new BoardNoValueException("부적절한 입력값");
		}
		
		String boardTitle = escapeHtml(answer.getAnswerTitle());
		String boardContent = escapeHtml(answer.getAnswerContent());
		answer.setAnswerTitle(covertNewlineToBr(boardTitle));
		answer.setAnswerContent(covertNewlineToBr(boardContent));
		
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
	private void validateBoardNo(int answerNo) {
		
		//log.info("{}", answerNo);
		
		if(answerNo <= 0) {
			throw new InvalidParameterException("게시글 번호가 유효하지 않습니다.");
		}
	}
	
	private Answer findBoardById(int answerNo) {
		Answer answer = mapper.selectBoardId(answerNo);
		if(answer == null) {
			throw new BoardNotFoundException("게시글을 찾을 수 없습니다");
		}
		return answer;
	}
	
	
	@Override
	public Map<String, Object> selectAnswerList(int currentPage) {
		
		int totalCount = getTotalCount();
		
		PageInfo pi = getPageInfo(totalCount, currentPage);
		
		List<Answer> answerList = getBoardList(pi);
		
		Map<String, Object> map = new HashMap();
		
		map.put("answer", answerList);
		map.put("pageInfo", pi);
		
		return map;
	}

	@Override
	public void insertAsk(Answer answer) {
		mapper.insertAsk(answer);
	}

	@Override
	public Map<String, Object> selectAnswerId(int answerNo) {
		
		validateBoardNo(answerNo);
		
		Answer answer = findBoardById(answerNo);
		
		Comment comment = mapper.findCommentById(answerNo);
		
		Map<String, Object> responseData = new HashMap();
		responseData.put("answer", answer);
		responseData.put("comment", comment);
		
		return responseData;
	}

	@Override
	public void updateAnswer(Answer answer) {
		
		//log.info("{}", answer);
		
		validateBoardNo(answer.getAnswerNo());
		findBoardById(answer.getAnswerNo());
		
		int result = mapper.updateBoard(answer);
		
		if(result <= 0) {
			throw new BoardNotFoundException("업데이트에 실패했습니다.");
		}
		
	}

	@Override
	public void deleteAnswer(int answerNo) {

		Answer answer = findBoardById(answerNo);
		
		int result = mapper.deleteBoard(answerNo);
		
		if(result <= 0) {
			throw new BoardNotFoundException("게시글 삭제에 실패했습니다.");
		}
	}
	
	
	
	
	

}
