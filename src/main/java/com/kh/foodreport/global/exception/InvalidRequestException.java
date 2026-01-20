package com.kh.foodreport.global.exception;

public class InvalidRequestException extends RuntimeException {
	public InvalidRequestException(String message) {
		super(message);
	}
}
