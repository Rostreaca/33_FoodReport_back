package com.kh.foodreport.global.common.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.global.common.model.dao.GlobalMapper;
import com.kh.foodreport.global.common.model.dto.BoardResponse;
import com.kh.foodreport.global.exception.InvalidRequestException;
import com.kh.foodreport.global.exception.InvalidValueException;
import com.kh.foodreport.global.tag.model.dto.TagDTO;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GlobalServiceImpl implements GlobalService{

	private final GlobalMapper globalMapper;

	@Override
	public BoardResponse findAllByKeyword(String keyword) {
		
		GlobalValidator.checkNull(keyword, "유효하지 않은 요청입니다.");

		List<ReviewDTO> reviews = new ArrayList<>();
		List<PlaceDTO> places = new ArrayList<>();
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("keyword", keyword);
		params.put("offset", 0); // 첫 조회 시 offset은 항상 0
		
		if(!"".equals(keyword.trim()) ) {
			reviews = globalMapper.findAllReviews(params);		

			places = globalMapper.findAllPlaces(params);
		}
		
		BoardResponse response = new BoardResponse(reviews, places);
		
		return response;
	}

	@Override
	public List<ReviewDTO> findAllReviewsByKeyword(String keyword, int offset) {
		
		GlobalValidator.checkNull(keyword, "유효하지 않은 요청입니다.");
		
		if(offset < 3) { // 2번째 조회부터는 항상 offset이 3 이상이어야 함
			throw new InvalidValueException("유효하지 않은 요청값입니다.");
		}
		
		List<ReviewDTO> reviews = new ArrayList<>();
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("keyword", keyword);
		params.put("offset", offset);
		
		if(!"".equals(keyword.trim()) ) {
			reviews = globalMapper.findAllReviews(params);		
		}
		
		return reviews;
	}

	@Override
	public List<PlaceDTO> findAllPlacesByKeyword(String keyword, int offset) {
		
		GlobalValidator.checkNull(keyword, "유효하지 않은 요청입니다.");
		
		if(offset < 3) { // 2번째 조회부터는 항상 offset이 3 이상이어야 함
			throw new InvalidValueException("유효하지 않은 요청값입니다.");
		}
		
		List<PlaceDTO> places = new ArrayList<>();
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("keyword", keyword);
		params.put("offset", offset);
		
		if(!"".equals(keyword.trim()) ) {
			places = globalMapper.findAllPlaces(params);		
		}
		
		return places;
	}

	@Override
	public List<TagDTO> findAllTags() {
		
		List<TagDTO> tags = globalMapper.findAllTags();
		
		if(tags == null || tags.isEmpty()) { // 태그는 반드시 1개 이상 존재
			throw new InvalidRequestException("태그 조회 중 오류가 발생했습니다.");
		}
		
		return tags;
	}
	
	
	
	
}
