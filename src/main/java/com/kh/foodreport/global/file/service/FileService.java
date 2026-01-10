package com.kh.foodreport.global.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.global.file.util.RenamePolicy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {

	private final Path fileLocation;
	private final RenamePolicy renamePolicy;
	
	public FileService(RenamePolicy renamePolicy) {
		this.renamePolicy = renamePolicy;
		this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
	}
	
	public String store(MultipartFile file) {
		
		String originalFilename = file.getOriginalFilename();
		
		String changeFilename = renamePolicy.rename(originalFilename);
		
		Path targetLocation = this.fileLocation.resolve(changeFilename);
		
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return "http://localhost:8081/uploads/" + changeFilename;
		
		} catch (IOException e) {
			throw new RuntimeException("이상요상 파일임 ㅎ");
		}
	}
}
