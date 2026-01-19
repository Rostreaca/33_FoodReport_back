package com.kh.foodreport.domain.admin.tag.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagDTO;
import com.kh.foodreport.global.tag.Tag;

@Mapper
public interface AdminTagMapper {
	
	int saveTag(Tag tag);
	
	int countByTags();
	
	List<AdminTagDTO> findAllTag(Map<String, Object> pages);
	
	int updateTag(Tag tag);
}
