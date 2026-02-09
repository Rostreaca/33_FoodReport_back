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
public class ReviewReplyLikeDTO {
    private Long replyNo;
    private Long memberNo;
    private LocalDateTime createDate;
    
    // 댓글 정보
    private String replyContent;
    private Long reviewNo;
    private String reviewTitle;
    private String replyWriterNickname;
    private int replyLikeCount;

}
