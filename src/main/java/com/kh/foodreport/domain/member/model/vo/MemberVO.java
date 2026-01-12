package com.kh.foodreport.domain.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberVO {
	private Long memberNo;
	private String email;
	private String password;
	private String nickname;
	private String phone;
	private String introduce;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private String status;
	private String role;

}
