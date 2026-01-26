package com.kh.foodreport.domain.place.model.service;

import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;

public interface PlaceReplyService {

	void updateReply(Long replyNo, PlaceReplyDTO placeReply, Long memberNo);

}
