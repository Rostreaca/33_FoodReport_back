package com.kh.foodreport.domain.admin.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberDTO;
import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberPlaceDTO;

@Mapper
public interface AdminMemberMapper {
	
	int countByMembers();
	
	List<AdminMemberDTO> findAllMember(Map<String, Object> pages);
	
	int countByNickname(String nickname);
	
	List<AdminMemberDTO> findByNickname(Map<String, Object> pages);

	int deleteMember(Long memberNo);
	
	int updateMember(Map<String, Object> params);
	
	int countByMemberPlace();
	
	List<AdminMemberPlaceDTO> findByMemberPlace(Map<String, Object> pages);
}
