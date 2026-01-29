package com.kh.foodreport.domain.member.model.dto;

import java.util.List;
import java.util.Map;

import com.kh.foodreport.global.util.PageInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LikeResponse {
	List<ReviewLikeDTO> ReviewLikes;
	List<ReviewReplyLikeDTO> ReviewReplyLikes;
	List<PlaceLikeDTO> PlaceLikes;
	List<PlaceReplyLikeDTO> PlaceReplyLikes;
	List<LikeDTO> AllLikes;
	
	String likeType;
	PageInfo pageInfo;

		
}

