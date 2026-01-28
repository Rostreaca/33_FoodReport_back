package com.kh.foodreport.domain.member.model.dto;

import java.time.LocalDateTime;

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
public class LikeDTO {
    private String likeType; // REVIEW, REVIEW_REPLY, PLACE, PLACE_REPLY
    private Long targetNo;
    private LocalDateTime createDate;
    
    // 공통 정보
    private String title; // 리뷰 제목 또는 장소 제목
    private String content; // 리뷰/댓글 내용 또는 장소 내용
    private String nickname; // 작성자 닉명
    private int likeCount;
    private int viewCount;
   
    // 추가 정보
    private Long relatedNo; // 관련 번호 (댓글의 경우 원글 번호)
    private String relatedTitle; // 관련 제목
}
