package com.kh.foodreport.global.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kh.foodreport.global.exception.PageNotFoundException;

@Component //클래스를 빈으로 등록하는것이다. 즉 spring제어하게 만드는 것객체를 생성안해도됨 
public class Pagenation {


	public PageInfo getPageInfo(int listCount
							  , int currentPage
							  , int boardLimit
							  , int pageLimit) {
		int maxPage = (int)Math.ceil((double)listCount/ boardLimit);
		int startPage =(currentPage-1)/pageLimit*pageLimit +1;
		int endPage =startPage + pageLimit -1 ;
		if(endPage>maxPage) endPage = maxPage;
		return new PageInfo(listCount,currentPage,boardLimit,pageLimit,maxPage,
				startPage,endPage);
	}
	
	public Map<String, Object> getPageRequest(int listCount, int page, int boardLimit) {
		
		if (page <= 0) {
			throw new PageNotFoundException("잘못된접근입니다.( 0보다 큰값을 입력해주세요.)");
		}
		
		Map<String, Object> map = new HashMap<>();
		
		int offset = (page - 1) * boardLimit;
		
		map.put("offset", offset);
		map.put("limit", boardLimit);
		
		PageInfo pageInfo = getPageInfo(listCount, page, 5, boardLimit);
		
		if(page > pageInfo.getMaxPage()) {
			throw new PageNotFoundException("초과된 페이지입니다.");
		}
		
		map.put("pageInfo", pageInfo);
		
		return map;
	}
	
}
