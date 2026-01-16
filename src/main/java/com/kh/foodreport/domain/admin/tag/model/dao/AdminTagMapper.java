package com.kh.foodreport.domain.admin.tag.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.global.tag.Tag;

@Mapper
public interface AdminTagMapper {
	
	int saveTag(Tag tag);
}
