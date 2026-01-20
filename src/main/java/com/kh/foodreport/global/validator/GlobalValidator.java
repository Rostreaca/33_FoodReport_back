package com.kh.foodreport.global.validator;

import com.kh.foodreport.global.exception.InvalidValueException;

public class GlobalValidator {

	private GlobalValidator() { // 기본생성자 private로 막아서 객체생성을 막음.
		
	}
	
	public static void validateNo(int number,String message) {
		if (number <= 0) {
			throw new InvalidValueException(message);
		}
	}
	
	public static void validateNo(Long number,String message) {
		if (number <= 0) {
			throw new InvalidValueException(message);
		}
	}
	
}
