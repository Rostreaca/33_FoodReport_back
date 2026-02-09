package com.kh.foodreport.domain.admin.place.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.admin.place.model.dao.AdminPlaceMapper;
import com.kh.foodreport.domain.admin.place.model.dto.AdminPlaceDTO;
import com.kh.foodreport.domain.admin.place.model.dto.AdminPlaceResponse;
import com.kh.foodreport.global.exception.BoardDeleteException;
import com.kh.foodreport.global.exception.BoardUpdateException;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminPlaceServiceImpl implements AdminPlaceService {

	private final AdminPlaceMapper placeMapper;
	private final Pagenation pageNation;
	
	private AdminPlaceResponse createFindResponse(List<AdminPlaceDTO> places
			  ,Map<String, Object> pages) {
	AdminPlaceResponse response = new AdminPlaceResponse();
	
	response.setAdminPlace(places);
	response.setPageInfo((PageInfo)pages.get("pageInfo"));
	
	return response;
	}
	
	@Override
	public AdminPlaceResponse findAllPlace(int page) {
		
		GlobalValidator.validateNo(page, "0보다 큰값을 넣어주시길 바랍니다.");
		
		int listCount = placeMapper.countByPlace();
		
		Map<String, Object> pages = pageNation.getPageRequest(listCount, page, 10);
		
		List<AdminPlaceDTO> places = placeMapper.findAllPlace(pages);
		
		return createFindResponse(places, pages);
	}

	@Override
	public AdminPlaceResponse findByPlaceTitle(int page, String placeTitle) {
		
		GlobalValidator.validateNo(page, "0보다 큰값을 넣어주시길 바랍니다.");
		
		int listCount = placeMapper.countByPlaceTitle(placeTitle);
		
		Map<String, Object> pages = pageNation.getPageRequest(listCount, page, 10);
		
		pages.put("placeTitle", placeTitle);
		
		List<AdminPlaceDTO> places = placeMapper.findByPlaceTitle(pages);
		
		return createFindResponse(places, pages);
	}

	@Override
	public void deletePlace(Long placeNo) {
		
		GlobalValidator.validateNo(placeNo, "0보다 큰 값을 넣어주시길 바랍니다.");

		int deleteResult = placeMapper.deletePlace(placeNo);
		
		if(deleteResult == 0) {
			throw new BoardDeleteException("삭제에 실패하였습니다.");
		}
	}

	@Override
	public void updatePlace(Long placeNo) {
		
		GlobalValidator.validateNo(placeNo, "0보다 큰 값을 넣어주시길 바랍니다.");
		
		int updateResult = placeMapper.updatePlace(placeNo);
		
		if(updateResult == 0) {
			throw new BoardUpdateException("업데이트에 실패하였습니다.");
		}
		
	}
	
}
