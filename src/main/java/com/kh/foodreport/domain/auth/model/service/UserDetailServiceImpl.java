package com.kh.foodreport.domain.auth.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.member.model.dao.MemberMapper;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
	// AuthenticationManager가 실질적으로 사용자의 정보를 조회할 때 메소드를 호출하는 클래스

	private final MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("{}",username);
		
		MemberDTO user = memberMapper.loadUser(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}
		return CustomUserDetails.builder()
								.memberNo(user.getMemberNo())
								.username(user.getEmail())
								.password(user.getPassword())
								.nickname(user.getNickname())
								.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
								.build();

	}

}
