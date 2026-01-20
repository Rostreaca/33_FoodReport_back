package com.kh.foodreport.domain.admin.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.foodreport.domain.admin.member.model.dto.AdminMemberDTO;

@Mapper
public interface AdminMemberMapper {
	
	int countByMembers();
	
	List<AdminMemberDTO> findAllMember(Map<String, Object> pages);
	
	int countByNickname(String nickname);
	
	List<AdminMemberDTO> findByNickname(Map<String, Object> pages);

	int deleteMember(Long memberNo);
}
