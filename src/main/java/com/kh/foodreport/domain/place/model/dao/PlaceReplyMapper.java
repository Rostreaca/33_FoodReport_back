package com.kh.foodreport.domain.place.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;
import com.kh.foodreport.domain.place.model.vo.PlaceReply;
import com.kh.foodreport.domain.place.model.vo.PlaceReplyLike;

@Mapper
public interface PlaceReplyMapper {

	int updateReply(PlaceReplyDTO placeReply);

	int deleteReply(PlaceReply placeReply);

	int countReplyLikeByMember(PlaceReplyLike placeReplyLike);

	int saveReplyLike(PlaceReplyLike placeReplyLike);

	int countByReplyNo(Long replyNo);

	int deleteReplyLike(PlaceReplyLike placeReplyLike);

}
