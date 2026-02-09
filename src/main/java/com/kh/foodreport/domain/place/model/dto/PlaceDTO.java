package com.kh.foodreport.domain.place.model.dto;

import java.util.Date;
import java.util.List;

import com.kh.foodreport.global.region.model.dto.RegionDTO;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlaceDTO {
	private Long placeNo;
	private String placeTitle;
	private String placeContent;
	private String placeWriter;
	private Long memberNo;
	private String profileImage;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private int likes;
	private int viewCount;
	private String status;
	
	private List<TagDTO> tags;
	private List<PlaceImageDTO> placeImages;
	private List<PlaceReplyDTO> placeReplies;
	private RegionDTO region;
	
	private List<Long> likeMembers;
	
}
