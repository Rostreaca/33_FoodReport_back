package com.kh.foodreport.domain.admin.tag.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.tag.model.vo.AdminTag;

@Mapper
public interface AdminTagMapper {
	
	int saveTag(AdminTag tag);
}
