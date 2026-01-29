package com.kh.foodreport.domain.admin.member.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminMemberDTO {
	
	private Long memberNo;
	private String email;
	private String nickname;
	private String phone;
	private String introduce;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private String status;
	private String role;
	private int reviewCount;
	private String profileImage;
	
}
