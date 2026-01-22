package com.kh.foodreport.domain.place.model.service;

import java.util.Map;

import com.kh.foodreport.domain.place.model.dto.PlaceResponse;

public interface PlaceService {

	PlaceResponse findAllPlaces(int page, Map<String, Object> params);

}
