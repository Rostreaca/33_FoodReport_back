package com.kh.foodreport.domain.place.model.service;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.place.model.dao.PlaceReplyMapper;
import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;
import com.kh.foodreport.domain.place.model.vo.PlaceReply;
import com.kh.foodreport.domain.place.model.vo.PlaceReplyLike;
import com.kh.foodreport.global.exception.InvalidRequestException;
import com.kh.foodreport.global.exception.ReplyDeleteException;
import com.kh.foodreport.global.exception.ReplyLikeFailedException;
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


	@Override
	public void deleteReply(Long replyNo, Long memberNo) {
		
		GlobalValidator.validateNo(replyNo, "존재하지 않는 댓글입니다.");
		
		PlaceReply placeReply = PlaceReply.builder().replyNo(replyNo).replyWriter(String.valueOf(memberNo)).build();
		
		int result = placeReplyMapper.deleteReply(placeReply);
		
		if(result == 0) {
			throw new ReplyDeleteException("댓글 삭제에 실패했습니다.");
		}
		
	}


	@Override
	public void saveReplyLike(Long replyNo, Long memberNo) {
		
		GlobalValidator.validateNo(replyNo, "존재하지 않는 댓글입니다.");
		
		PlaceReplyLike placeReplyLike = PlaceReplyLike.createPlaceReplyLike(replyNo, memberNo);
		
		int likeCount = placeReplyMapper.countReplyLikeByMember(placeReplyLike);
		
		if(likeCount == 1 ) {
			throw new InvalidRequestException("유효하지 않은 요청입니다.");
		}
		
		int result = placeReplyMapper.saveReplyLike(placeReplyLike);
		
		if(result == 0) {
			throw new ReplyLikeFailedException("좋아요 등록에 실패했습니다.");
		}
		
	}
	
}
