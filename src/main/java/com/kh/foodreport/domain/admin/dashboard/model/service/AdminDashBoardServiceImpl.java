package com.kh.foodreport.domain.admin.dashboard.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.foodreport.domain.admin.dashboard.model.dao.AdminDashBoardMapper;
import com.kh.foodreport.domain.admin.dashboard.model.dto.DailyMember;
import com.kh.foodreport.domain.admin.dashboard.model.dto.DashBoardRegion;
import com.kh.foodreport.domain.admin.dashboard.model.dto.DashBoardResponse;
import com.kh.foodreport.domain.admin.dashboard.model.dto.DashBoardTag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminDashBoardServiceImpl implements AdminDashBoardService{

	private final AdminDashBoardMapper dashBoardMapper;
	
	@Override
	@Transactional(readOnly = true)
	public DashBoardResponse getDashBoard() {
		
		DashBoardResponse response = new DashBoardResponse();
		response.setTodayMemberCount(getTodayNewMembers()); // 하루 회원수
		response.setTodayReviewCount(getTodayNewReviews()); // 하루 리뷰수
		response.setTodayTotalReply(getTodayNewReplies()); // 하루 댓글수
		response.setWeeklyNewMember(getWeeklyNewMember()); // 주간 회원가입 수
		response.setPopularTags(getCountTag());  // 인기 태그 조회
		response.setPopularRegion(getCountRegion()); // 인기 지역 조회
		return response;
	}
	
	
	// 오늘 하루 사용자 회원가입 수 조회
	@Transactional(readOnly = true)
	private Long getTodayNewMembers() {
		return dashBoardMapper.getTodayNewMembers();
	}
	
	// 오늘 하루 작성된 리뷰 수 조회
	@Transactional(readOnly = true)
	private Long getTodayNewReviews() {
		return dashBoardMapper.getTodayNewReviews();
	}
	
	// 오늘 하루 작성된 리뷰 댓글 수 조회
	@Transactional(readOnly = true)
	private Long getTodayNewReplies() {
		return dashBoardMapper.getTodayNewReplies();
	}
	
	// 주간 사용자 회원가입 수 조회
	@Transactional(readOnly = true)
	private List<DailyMember> getWeeklyNewMember() {
		return dashBoardMapper.getWeeklyNewMember();
	}
	
	// 인기있는 태그(Top 5) 조회 
	@Transactional(readOnly = true)
	private List<DashBoardTag> getCountTag() {
		return dashBoardMapper.getCountTag();
	}
	
	@Transactional(readOnly = true)
	private List<DashBoardRegion> getCountRegion() {
		return dashBoardMapper.getCountRegion();
	}
}
