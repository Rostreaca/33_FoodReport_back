package com.kh.foodreport.global.tag;

import java.util.Date;

import com.kh.foodreport.global.exception.InvalidKeywordException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Tag{
	
	private Long tagNo;
	
	@NotBlank(message = "태그 제목을 입력해주세요.")
	@Size(min = 1, max = 50, message = "태그 제목은 1~50바이트여야합니다.")
	private String tagTitle;
	
	@NotBlank(message = "태그 내용을 입력해주세요.")
	@Size(min = 1, max = 500, message = "태그 내용은 1~500바이트여야합니다.")
	private String tagContent;
	
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	
	public static Tag createTag(String tagTitle, String tagContent) {
		
		validateTrim(tagTitle, tagContent);
		
		return Tag.builder()
					.tagTitle(tagTitle)
					.tagContent(tagContent)
					.build();
	}
	
	public static void validateTrim(String tagTitle, String tagContent) {
		if(tagTitle == null || tagTitle.trim().length() <= 0) {
			throw new InvalidKeywordException("빈 문자열은 넣을 수 없습니다.");
		}
		
		if(tagContent == null || tagContent.trim().length() <= 0) {
			throw new InvalidKeywordException("빈 문자열은 넣을 수 없습니다.");
		}
	}
}
