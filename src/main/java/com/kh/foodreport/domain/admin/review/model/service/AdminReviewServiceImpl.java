package com.kh.foodreport.domain.admin.review.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.admin.review.model.dao.AdminReviewMapper;
import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewDTO;
import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewResponse;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminReviewServiceImpl implements AdminReviewService {

	private final AdminReviewMapper reviewMapper;
	private final Pagenation pagenation;
	
	@Override
	public AdminReviewResponse findAllReviews(int page) {
		
		GlobalValidator.validateNo(page, "page번호가 0보다 작을순 없습니다.");
		
		int listCount = reviewMapper.countByReviews();
		
		log.info("숫자 잘나?{}", listCount);
		
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 10);
		
		AdminReviewDTO reviews = reviewMapper.findAllReviews(pages);
		
		AdminReviewResponse response = new AdminReviewResponse();
		
		response.setAdminReviews(reviews);
		response.setPages((PageInfo)pages);
		
		return response;
	}

}
