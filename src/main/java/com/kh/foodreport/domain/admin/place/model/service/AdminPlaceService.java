package com.kh.foodreport.domain.admin.place.model.service;

import com.kh.foodreport.domain.admin.place.model.dto.AdminPlaceResponse;

public interface AdminPlaceService {
	
	AdminPlaceResponse findAllPlace(int page);
}
