package com.kh.foodreport.domain.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
public class CustomUserDetails implements UserDetails {
	private String username; // EMAIL 컬럼값 담는 용도
	private String password;
	private String nickname;
	private Collection<? extends GrantedAuthority> authorities;



	

}
