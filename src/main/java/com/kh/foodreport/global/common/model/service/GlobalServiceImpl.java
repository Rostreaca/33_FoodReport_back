package com.kh.foodreport.global.common.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceResponse;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewResponse;
import com.kh.foodreport.global.common.model.dao.GlobalMapper;
import com.kh.foodreport.global.exception.InvalidRequestException;
import com.kh.foodreport.global.region.model.dto.RegionDTO;
import com.kh.foodreport.global.tag.model.dto.TagDTO;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GlobalServiceImpl implements GlobalService{

	private final GlobalMapper globalMapper;
	private final Pagenation pagenation;

	@Override
	public ReviewResponse findAllReviewsByKeyword(String keyword, int page, Long tagNo, Long regionNo) {
		
		GlobalValidator.validateNo(page, "유효하지 않은 페이지 요청입니다.");
		
		GlobalValidator.checkNull(keyword, "유효하지 않은 요청입니다.");

		Map<String, Object> params = new HashMap<>();
		
		params.put("regionNo", regionNo);
		params.put("tagNo", tagNo);
		params.put("keyword", keyword);
		
		int listCount = globalMapper.countByReviews(params);

		params.putAll(pagenation.getPageRequest(listCount, page, 3));
		
		List<ReviewDTO> reviews = globalMapper.findAllReviews(params);
		
		ReviewResponse response = new ReviewResponse(reviews, (PageInfo)params.get("pageInfo"));
		
		return response;
	}

	@Override
	public PlaceResponse findAllPlacesByKeyword(String keyword, int page, Long tagNo, Long regionNo) {
		
		GlobalValidator.validateNo(page, "유효하지 않은 페이지 요청입니다.");
		
		GlobalValidator.checkNull(keyword, "유효하지 않은 요청입니다.");
		

		Map<String, Object> params = new HashMap<>();

		params.put("regionNo", regionNo);
		params.put("tagNo", tagNo);
		params.put("keyword", keyword);
				
		int listCount = globalMapper.countByPlaces(params);

		params.putAll(pagenation.getPageRequest(listCount, page, 3));
		
		List<PlaceDTO> places = globalMapper.findAllPlaces(params);		
		
		PlaceResponse response = new PlaceResponse(places, (PageInfo)params.get("pageInfo"));
		
		return response;
	}

	@Override
	public List<TagDTO> findAllTags() {
		
		List<TagDTO> tags = globalMapper.findAllTags();
		
		if(tags == null || tags.isEmpty()) { // 태그는 반드시 1개 이상 존재
			throw new InvalidRequestException("태그 조회 중 오류가 발생했습니다.");
		}
		
		return tags;
	}

	@Override
	public List<RegionDTO> findAllRegions() {
		
		List<RegionDTO> regions = globalMapper.findAllRegions();
		
		if(regions == null || regions.isEmpty()) {
			throw new InvalidRequestException("지역 조회 중 오류가 발생했습니다.");
		}
		
		return regions;
	}
	
	
	
	
}
