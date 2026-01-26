package com.kh.foodreport.domain.place.model.service;

import org.springframework.stereotype.Component;

import com.kh.foodreport.domain.place.model.dao.PlaceMapper;
import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;
import com.kh.foodreport.global.exception.PageNotFoundException;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlaceValidator {

	private final PlaceMapper placeMapper; 
	
	public void validatePlace(PlaceDTO place) {
		
		GlobalValidator.checkNull(place, "데이터가 존재하지 않습니다. 다시 시도해주세요.");
		GlobalValidator.checkBlank(place.getPlaceTitle(),"게시글 제목은 비어있을 수 없습니다.");
		GlobalValidator.checkBlank(place.getPlaceContent(),"게시글 내용은 비어있을 수 없습니다.");	
	}
	
	public void validateReply(PlaceReplyDTO reply) {

		GlobalValidator.checkNull(reply, "데이터가 존재하지 않습니다. 다시 시도해주세요.");
		
		GlobalValidator.checkBlank(reply.getReplyContent(), "댓글 내용은 비어있을 수 없습니다.");
		
	}
	
	public void validateReply(Long placeNo, PlaceReplyDTO reply) {

		GlobalValidator.validateNo(placeNo, "유효하지 않은 게시글 번호입니다.");
		
		int count = placeMapper.countByPlaceNo(placeNo);
		
		if(count == 0) {
			throw new PageNotFoundException("게시글이 존재하지 않습니다.");
		}
		
		validateReply(reply);
	}
	

	
	
}
