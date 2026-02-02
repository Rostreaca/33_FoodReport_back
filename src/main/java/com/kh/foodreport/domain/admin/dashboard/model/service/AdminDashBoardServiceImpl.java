package com.kh.foodreport.domain.admin.dashboard.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.foodreport.domain.admin.dashboard.model.dao.AdminDashBoardMapper;
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
		
		response.setPopularTags(getCountTag()); 
		
		log.info("response : {}", response);
		return response;
	}
	
	// 인기있는 태그(Top 5) 조회 
	@Transactional(readOnly = true)
	private List<DashBoardTag> getCountTag() {
		return dashBoardMapper.getCountTag();
	}
	
	
	
	
}
