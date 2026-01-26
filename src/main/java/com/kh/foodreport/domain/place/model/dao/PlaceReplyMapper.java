package com.kh.foodreport.domain.place.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;

@Mapper
public interface PlaceReplyMapper {

	int updateReply(PlaceReplyDTO placeReply);

}
