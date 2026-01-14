package com.kh.foodreport.domain.admin.notice.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeDTO;
import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeResponse;

public interface AdminNoticeService {

	void saveNotice(AdminNoticeDTO notice, MultipartFile file);
	
	AdminNoticeResponse findAllNotices(int page);
	
	AdminNoticeResponse findByNoticeTitle(int page, String noticeTitle);
	
	void deleteNotice(Long noticeNo);
}
