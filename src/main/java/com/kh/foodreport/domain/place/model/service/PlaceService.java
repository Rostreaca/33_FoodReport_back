package com.kh.foodreport.domain.place.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceResponse;

public interface PlaceService {

	PlaceResponse findAllPlaces(int page, Map<String, Object> params);

	void savePlace(PlaceDTO place, List<Long> tagNums, List<MultipartFile> images, Long regionNo);

	PlaceDTO findPlaceByPlaceNo(Long placeNo);

	void updatePlace(PlaceDTO place, List<Long> tagNums, List<MultipartFile> images, Long regionNo);

	void deletePlace(Long placeNo);

	void saveReply(Long placeNo, PlaceReplyDTO reply);

	void saveLike(Long placeNo, Long memberNo);

	void deleteLike(Long placeNo, Long memberNo);

}
