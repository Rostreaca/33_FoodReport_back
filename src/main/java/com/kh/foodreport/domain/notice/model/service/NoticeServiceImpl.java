package com.kh.foodreport.domain.notice.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.notice.model.dao.NoticeMapper;
import com.kh.foodreport.domain.notice.model.dto.NoticeResponse;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

	private final NoticeMapper noticeMapper;
	private final Pagenation pagenation;
	
	@Override
	public NoticeResponse findAllNotices(int page) {
		
		NoticeResponse response = new NoticeResponse();
		
		GlobalValidator.validateNo(page, "유효하지 않은 페이지 요청입니다.");
		
		int listCount = noticeMapper.countByNotices();
		
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 15);
		
		response.setNotices(noticeMapper.findAllNotices(pages));
		
		response.setPageInfo((PageInfo)pages.get("pageInfo"));
		
		return response;
	}
	
}
