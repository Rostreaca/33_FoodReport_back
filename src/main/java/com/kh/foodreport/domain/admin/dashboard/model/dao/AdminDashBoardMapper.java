package com.kh.foodreport.domain.admin.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.dashboard.model.dto.DailyMember;
import com.kh.foodreport.domain.admin.dashboard.model.dto.DashBoardTag;

@Mapper
public interface AdminDashBoardMapper {

	
	Long getTodayNewMembers();
	
	Long getTodayNewReviews();
	
	Long getTodayNewReplies();
	
	List<DailyMember> getWeeklyNewMember();

	List<DashBoardTag> getCountTag();
	
}
