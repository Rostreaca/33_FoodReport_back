package com.kh.foodreport.domain.place.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.vo.PlaceImage;

@Mapper
public interface PlaceMapper {

	int countByPlaces(String object);

	List<PlaceDTO> findAllPlaces(Map<String, Object> params);

	int saveImage(PlaceImage reviewImage);

	int saveTagsByPlaceNo(Map<String, Object> params);

	int savePlace(PlaceDTO place);

}
