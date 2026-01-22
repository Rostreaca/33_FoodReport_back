package com.kh.foodreport.domain.place.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.place.model.dao.PlaceMapper;
import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceResponse;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{
	
	private final PlaceMapper placeMapper;
	private final Pagenation pagenation;
	
	@Override
	public PlaceResponse findAllPlaces(int page, Map<String, Object> params) {
		
		GlobalValidator.validateNo(page, "유효하지 않은 페이지 요청입니다.");
		
		int listCount = placeMapper.countByPlaces(String.valueOf(params.get("keyword")));
		
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 9);
		
		params.putAll(pages);
		
		List<PlaceDTO> places = placeMapper.findAllPlaces(params);
		
		return new PlaceResponse(places, ((PageInfo) params.get("pageInfo")));
	}
	
	

}
