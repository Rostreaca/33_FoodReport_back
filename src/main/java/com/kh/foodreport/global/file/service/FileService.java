package com.kh.foodreport.global.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.global.file.util.RenamePolicy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

	private final RenamePolicy renamePolicy;
	
	private final S3Client s3Client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.region.static}")
	private String region;
	
	public String store(MultipartFile file) {
		
		String originalFilename = file.getOriginalFilename();
		
		String changeFilename = renamePolicy.rename(originalFilename);
		
		PutObjectRequest request = PutObjectRequest.builder()
												   .bucket(changeFilename)
												   .key(changeFilename)
												   .contentType(file.getContentType())
												   .build();
		
		try {
			s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
		} catch (S3Exception e) {
			e.printStackTrace();
		} catch (AwsServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String filePath = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + changeFilename;
		
		return filePath;
	}
}
