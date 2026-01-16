package com.kh.foodreport.domain.member.model.dto;

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
public class ChangePasswordDTO {
	@Size(min = 8, max = 20, message="비밀번호는 8 ~ 20자 사이로 입력해주세요.")
	private String currentPassword;
	@Size(min = 8, max = 20, message="비밀번호는 8 ~ 20자 사이로 입력해주세요.")
	private String newPassword;

}
