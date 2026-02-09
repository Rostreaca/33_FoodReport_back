package com.kh.foodreport.domain.member.model.dto;

import java.util.Date;
import java.util.List;

import com.kh.foodreport.domain.review.model.dto.ReviewImageDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;

import jakarta.validation.constraints.Email;
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
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	@Size(min = 5, max = 30, message = "이메일은 5글자 이상 30글자 이하만 사용할 수 있습니다.")
	@NotBlank(message = "아이디는 비어있을 수 없습니다.")
	private String email;
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$", message = "비밀번호는 8글자 이상 20글자 이하 영문,숫자,특수문자만 사용할 수 있습니다.")
	@NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
	private String password;
	private String nickname;
	private String phone;
	private String introduce;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private String status;
	private String role;

	private MemberImageDTO memberImages;
	private RestaurantDTO restaurants;
	
}
