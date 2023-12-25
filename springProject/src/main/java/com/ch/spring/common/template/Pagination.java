package com.ch.spring.common.template;

import com.ch.spring.common.model.vo.PageInfo;

public class Pagination {

	public static PageInfo getPageInfo(int listCount, int currentPage, int boardLimit, int pageLimit) {
		int maxPage = (int)Math.ceil((double)listCount / boardLimit); // 총 글 수 / 한 페이지 표시 글 수
		int startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1; // (n구하기) * pageLimit블럭반복 + 1
		int endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) { endPage = maxPage; }
		return new PageInfo(listCount, currentPage, boardLimit, pageLimit, maxPage, startPage, endPage);
	}
	
}
