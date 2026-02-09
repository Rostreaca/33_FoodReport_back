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
public class PlaceLikeDTO {
    private Long placeNo;
    private Long memberNo;
    private LocalDateTime createDate;
    
    // 장소 정보
    private String placeTitle;
    private String placeContent;
    private String placeWriterNickname;
    private int placeLikeCount;
    private int placeViewCount;
}
