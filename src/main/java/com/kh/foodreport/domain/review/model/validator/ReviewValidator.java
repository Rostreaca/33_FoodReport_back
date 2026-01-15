package com.kh.foodreport.domain.review.model.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kh.foodreport.global.exception.FileUploadException;
import com.kh.foodreport.global.file.service.FileService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewValidator {

	private final FileService fileService;
	
	private void failedUpload(List<String> imageUrls) {

		imageUrls.forEach(file -> {
			fileService.deleteStoredFile(file);
		});
		
		throw new FileUploadException("이미지 업로드 실패");
	}
	
	public void validateImages(List<String> imageUrls) {
		
		failedUpload(imageUrls);
	}
	
}
