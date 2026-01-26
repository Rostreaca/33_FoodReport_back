package com.kh.foodreport.domain.place.model.service;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.place.model.dao.PlaceReplyMapper;
import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;
import com.kh.foodreport.global.exception.ReplyUpdateException;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceReplyServiceImpl implements PlaceReplyService{

	private final PlaceReplyMapper placeReplyMapper;
	private final PlaceValidator placeValidator;
	
	
	@Override
	public void updateReply(Long replyNo, PlaceReplyDTO placeReply, Long memberNo) {
		
		GlobalValidator.validateNo(replyNo, "존재하지 않는 댓글입니다.");
		
		placeValidator.validateReply(placeReply);
		
		placeReply.setReplyNo(replyNo);
		
		placeReply.setReplyWriter(String.valueOf(memberNo));
		
		int result = placeReplyMapper.updateReply(placeReply);
		
		if(result == 0) {
			throw new ReplyUpdateException("댓글 수정에 실패했습니다.");
		}
	}
	
}
