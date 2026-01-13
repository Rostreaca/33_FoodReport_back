package com.kh.foodreport.global.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String store(MultipartFile file);
	
	void deleteStoredFile(String fileUrl);
}
