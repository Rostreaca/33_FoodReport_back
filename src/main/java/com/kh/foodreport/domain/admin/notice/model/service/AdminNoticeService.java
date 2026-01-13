package com.kh.foodreport.domain.admin.notice.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeDTO;

public interface AdminNoticeService {

	void saveNotice(AdminNoticeDTO notice, MultipartFile file);
	
}
