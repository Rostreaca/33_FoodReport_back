package com.kh.foodreport.domain.member.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
	
	private Long memberNo;
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	@Size(min = 5, max = 50)
	@NotBlank
	private String email;
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	@Size(min = 4, max = 20)
	@NotBlank
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
