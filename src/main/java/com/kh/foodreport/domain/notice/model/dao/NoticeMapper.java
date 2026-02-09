package com.kh.foodreport.domain.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.notice.model.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {

	int countByNotices();

	List<NoticeDTO> findAllNotices(Map<String, Object> pages);

}
