package com.kh.foodreport.domain.admin.tag.model.service;

import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagDTO;
import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagResponse;

public interface AdminTagService {

	void saveTag(AdminTagDTO tag);

	AdminTagResponse findAllTag(int page);
	
	void updateTag(Long tagNo,AdminTagDTO tag);
	
	void deleteTag(Long tagNo);
}
