package com.kh.foodreport.domain.admin.tag.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.global.tag.Tag;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

@Mapper
public interface AdminTagMapper {
	
	int saveTag(Tag tag);
	
	int countByTags();
	
	List<TagDTO> findAllTag(Map<String, Object> pages);
	
	int updateTag(Tag tag);
	
	int deleteTag(Tag tag);
}
