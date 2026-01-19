package com.kh.foodreport.domain.admin.tag.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.admin.tag.model.dao.AdminTagMapper;
import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagDTO;
import com.kh.foodreport.domain.admin.tag.model.dto.AdminTagResponse;
import com.kh.foodreport.global.exception.ObjectCreationException;
import com.kh.foodreport.global.exception.TagDeleteException;
import com.kh.foodreport.global.exception.TagUpdateException;
import com.kh.foodreport.global.tag.Tag;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminTagServiceImpl implements AdminTagService {
	
	private final AdminTagMapper tagMapper;
	private final Pagenation pagenation;

	@Override
	public void saveTag(AdminTagDTO tag) {
		
		Tag newTag = Tag.createTag(null ,tag.getTagTitle(), tag.getTagContent());
		
		int result = tagMapper.saveTag(newTag);
		
		if(result == 0) {
			throw new ObjectCreationException("태그 생성에 실패하였습니다.");
		}
	}

	@Override
	public AdminTagResponse findAllTag(int page) {
		
		GlobalValidator.validateNo(page, "0보다 큰 값을 입력해주시기 바랍니다.");
		
		int listCount = tagMapper.countByTags(); // 토탈 카운트 세어줌.
		
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 10);
		
		List<AdminTagDTO> tags = tagMapper.findAllTag(pages);
		
		AdminTagResponse response = new AdminTagResponse();
		response.setAdminTag(tags);
		response.setPageInfo((PageInfo)pages.get("pageInfo"));
		// 응답 객체 생성 -> 페이지네이션 정보와 tags를 넣어서 보내줌
		
		return response;
	}

	@Override
	public void updateTag(Long tagNo, AdminTagDTO tagDTO) {
		
		GlobalValidator.validateNo(tagNo,"일치하는 번호가 없습니다.");
		
		Tag tag = Tag.createTag(tagNo, tagDTO.getTagTitle(), tagDTO.getTagContent());
		
		int result = tagMapper.updateTag(tag);
		
		if(result == 0) {
			throw new TagUpdateException("태그 업데이트에 실패하였습니다.");
		}
	}

	@Override
	public void deleteTag(Long tagNo) {
		
		GlobalValidator.validateNo(tagNo, "일치하는 번호가 없습니다.");
		
		Tag tag = Tag.createTag(tagNo, null, null);
		
		int result = tagMapper.deleteTag(tag);
		
		if(result == 0) {
			throw new TagDeleteException("태그 삭제에 실패하였습니다.");
		}
		
	}
	
}
