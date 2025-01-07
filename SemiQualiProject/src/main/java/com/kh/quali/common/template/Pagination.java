package com.kh.quali.common.template;

import org.springframework.stereotype.Component;

import com.kh.quali.common.model.vo.PageInfo;

@Component
public class Pagination {

	public static PageInfo getPageInfo(int listCount,
									   int currentPage,
									   int boardLimit,
									   int pageLimit) {
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage ;
		}
		
		/*
		return new PageInfo(listCount, currentPage, boardLimit, pageLimit,
							maxPage, startPage, endPage);
			평소 하던 방식
		*/
		
		return PageInfo.builder()	// builder를 이용한 방식
					   .listCount(listCount)
					   .currentPage(currentPage)
					   .boardLimit(boardLimit)
					   .pageLimit(pageLimit)
					   .maxPage(maxPage)
					   .startPage(startPage)
					   .endPage(endPage)
					   .build();
	}
	
}
