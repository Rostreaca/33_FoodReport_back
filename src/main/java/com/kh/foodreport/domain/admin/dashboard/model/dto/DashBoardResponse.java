package com.kh.foodreport.domain.admin.dashboard.model.dto;

import java.util.List;

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
public class DashBoardResponse {
	
	private Long todayMemberCount;
	private Long todayReviewCount;
	private Long todayTotalReply;
	
	// 주간 멤버 데이터
	private List<DailyMember> weeklyNewMember;
	
	// 사용된 인기 태그
	private List<DashBoardTag> popularTags;
	
	// 특정 지역에 포함된 리뷰 수
	private List<DashBoardRegion> popularRegion;
	

}
