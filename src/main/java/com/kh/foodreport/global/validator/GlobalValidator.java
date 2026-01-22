package com.kh.foodreport.global.validator;

import com.kh.foodreport.global.exception.InvalidKeywordException;
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
	
	public static void checkBlank(String str, String message) {
		if(str == null || str.trim().isBlank()) {
			throw new InvalidKeywordException(message);
		}
		
	}
	
	public static void checkNull(Object data, String message) {
		if(data == null) {
			throw new InvalidValueException(message);
		}
	}
	
}
