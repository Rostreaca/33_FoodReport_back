package com.kh.foodreport.domain.member.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
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
public class RestaurantDTO {
	
	private Long restaurantNo;
	private String businessNo;
	@NotBlank(message = "사업자 번호는 비어있을 수 없습니다.")
	private String restaurantName;
	@NotBlank(message = "업장명은 비어있을 수 없습니다.")
	private String address;
	@NotBlank(message = "주소는 비어있을 수 없습니다.")
	private String status;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private Long refMemberNo;

	
}
