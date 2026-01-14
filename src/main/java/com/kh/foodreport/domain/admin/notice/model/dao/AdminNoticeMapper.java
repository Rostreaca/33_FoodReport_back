package com.kh.foodreport.domain.admin.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeDTO;
import com.kh.foodreport.domain.admin.notice.model.vo.AdminNoticeImage;

@Mapper
public interface AdminNoticeMapper {

	int saveNotice(AdminNoticeDTO notiece);
	
	int saveImage(AdminNoticeImage image);
	
	int countByNotices();
	
	List<AdminNoticeDTO> findAllNotices(Map<String, Object> pages);
	
	int countByNoticeTitle(String noticeTitle);
	
	List<AdminNoticeDTO> findByNoticeTitle(Map<String, Object> pages);
	
	int deleteNoticeImage(Long noticeNo);
	
	int deleteNotice(Long noticeNo);
}
