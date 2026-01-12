package com.kh.foodreport.domain.admin.notice.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeDTO;
import com.kh.foodreport.domain.admin.notice.model.vo.AdminNoticeImage;

@Mapper
public interface AdminNoticeMapper {

	int saveNotice(AdminNoticeDTO notiece);
	
	int saveImage(AdminNoticeImage image);
	
}
