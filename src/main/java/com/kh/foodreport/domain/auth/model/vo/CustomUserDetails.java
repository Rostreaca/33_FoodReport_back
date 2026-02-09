package com.kh.foodreport.domain.auth.model.vo;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
public class CustomUserDetails implements UserDetails {
	
	private Long memberNo;
	private String username; // EMAIL 컬럼값 담는 용도
	private String password;
	private String nickname;
	private String phone;
	private String introduce;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private String status;
	private String role;
	private Collection<? extends GrantedAuthority> authorities;
	
	

	



}
