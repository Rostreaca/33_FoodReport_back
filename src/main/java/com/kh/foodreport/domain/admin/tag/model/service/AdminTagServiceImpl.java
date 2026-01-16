package com.kh.foodreport.domain.admin.tag.model.service;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.admin.tag.model.dao.AdminTagMapper;
import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagDTO;
import com.kh.foodreport.global.exception.ObjectCreationException;
import com.kh.foodreport.global.tag.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminTagServiceImpl implements AdminTagService {
	
	private final AdminTagMapper tagMapper;

	@Override
	public void saveTag(AdminTagDTO tag) {
		
		Tag newTag = Tag.createTag(tag.getTagTitle(), tag.getTagContent());
		
		int result = tagMapper.saveTag(newTag);
		
		if(result <= 0) {
			throw new ObjectCreationException("태그 생성에 실패하였습니다.");
		}
	}
	
}
