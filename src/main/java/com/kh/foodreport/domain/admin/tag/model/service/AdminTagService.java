package com.kh.foodreport.domain.admin.tag.model.service;

import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagResponse;
import com.kh.foodreport.global.tag.model.dto.TagDTO;

public interface AdminTagService {

	void saveTag(TagDTO tag);

	AdminTagResponse findAllTag(int page);
	
	void updateTag(Long tagNo,TagDTO tag);
	
	void deleteTag(Long tagNo);
}
