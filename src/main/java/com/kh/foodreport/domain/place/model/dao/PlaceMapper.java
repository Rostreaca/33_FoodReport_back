package com.kh.foodreport.domain.place.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceImageDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;
import com.kh.foodreport.domain.place.model.vo.PlaceImage;
import com.kh.foodreport.domain.place.model.vo.PlaceReply;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

@Mapper
public interface PlaceMapper {

	int countByPlaces(String object);

	List<PlaceDTO> findAllPlaces(Map<String, Object> params);

	int saveImage(PlaceImage placeImage);

	int saveTagsByPlaceNo(Map<String, Object> params);

	int savePlace(PlaceDTO place);

	int updateViewCount(Long placeNo);

	PlaceDTO findPlaceByPlaceNo(Long placeNo);

	List<PlaceImageDTO> findImagesByPlaceNo(Long placeNo);

	List<TagDTO> findTagsByPlaceNo(Long placeNo);

	List<PlaceReplyDTO> findRepliesByPlaceNo(Long placeNo);

	int updatePlace(PlaceDTO place);

	int deleteImage(Long imageNo);

	int deleteTags(Long placeNo);

	int deletePlace(Long placeNo);

	int saveReply(PlaceReply replyVO);

}
