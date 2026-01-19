package com.kh.foodreport.domain.admin.review.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.admin.review.model.dao.AdminReviewMapper;
import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewDTO;
import com.kh.foodreport.domain.admin.review.model.dto.AdminReviewResponse;
import com.kh.foodreport.global.exception.InvalidKeywordException;
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
		
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 10);
		
		List<AdminReviewDTO> reviews = reviewMapper.findAllReviews(pages);
		
		return createFindResponse(reviews ,pages);
	}

	@Override
	public AdminReviewResponse findByReviewTitle(int page, String reviewTitle) {
		
		GlobalValidator.validateNo(page, "page번호가 0보단 작을순 없습니다.");
		
		if(reviewTitle == null || "".equals(reviewTitle.trim())) {
			throw new InvalidKeywordException("키워드를 입력해주세요.");
		}
		
		// 부분 개수 조회
		int listCount = reviewMapper.countByReviewTitle(reviewTitle);
		
		log.info("부분개수조회{}", listCount);
		
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 10);
		
		pages.put("reviewTitle", reviewTitle);
		
		List<AdminReviewDTO> reviews = reviewMapper.findByReviewTitle(pages);
		
		return createFindResponse(reviews, pages);
	}
	
	// 중복 메소드 제거(응답 데이터 만드는 메소드)
	private AdminReviewResponse createFindResponse(List<AdminReviewDTO> reviews
											  ,Map<String, Object> pages) {
		AdminReviewResponse response = new AdminReviewResponse();
		
		response.setAdminReviews(reviews);
		response.setPages((PageInfo)pages.get("pageInfo"));
		
		return response;
	}

}
