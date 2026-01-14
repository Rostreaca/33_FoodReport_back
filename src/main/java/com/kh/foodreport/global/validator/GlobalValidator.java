package com.kh.foodreport.global.validator;

import org.springframework.stereotype.Component;

import com.kh.foodreport.global.exception.PageNotFoundException;

@Component
public class GlobalValidator {

	public void validateNo(int number,String message) {
		if (number <= 0) {
			throw new PageNotFoundException(message);
		}
	}
}
