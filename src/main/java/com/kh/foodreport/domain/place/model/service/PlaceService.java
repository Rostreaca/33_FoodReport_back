package com.kh.foodreport.domain.place.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceResponse;

public interface PlaceService {

	PlaceResponse findAllPlaces(int page, Map<String, Object> params);

	void savePlace(PlaceDTO place, List<Long> tagNums, List<MultipartFile> images);

	PlaceDTO findPlaceByPlaceNo(Long placeNo);

	void updatePlace(PlaceDTO place, List<Long> tagNums, List<MultipartFile> images);

}
