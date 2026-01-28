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
public class ReviewLikeDTO {
    private Long reviewNo;
    private Long memberNo;
    private LocalDateTime createDate;
    
    // 리뷰 정보
    private String reviewTitle;
    private String reviewContent;
    private String reviewerNickname;
    private int reviewLikeCount;
    private int reviewViewCount;
}


