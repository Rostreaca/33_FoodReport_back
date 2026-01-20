package com.kh.foodreport.domain.admin.place.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.place.model.dto.AdminPlaceDTO;

@Mapper
public interface AdminPlaceMapper {
	
	int countByPlace();

	List<AdminPlaceDTO> findAllPlace(Map<String, Object> pages);
	
	int countByPlaceTitle(String placeTitle);
	
	List<AdminPlaceDTO> findByPlaceTitle(Map<String, Object> pages);
	
	int deletePlace(Long placeNo);
	
	int updatePlace(Long placeNo);
}
