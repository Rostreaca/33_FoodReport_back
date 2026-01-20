package com.kh.foodreport.domain.admin.place.model.service;

import com.kh.foodreport.domain.admin.place.model.dto.AdminPlaceResponse;

public interface AdminPlaceService {
	
	AdminPlaceResponse findAllPlace(int page);
	
	AdminPlaceResponse findByPlaceTitle(int page, String placeTitle);
	
	void deletePlace(Long placeNo);
}
