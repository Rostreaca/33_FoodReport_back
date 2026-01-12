package com.kh.foodreport.global.file.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.global.exception.FileUploadException;
import com.kh.foodreport.global.file.util.RenamePolicy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

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
												   .bucket(bucketName)
												   .key(changeFilename)
												   .contentType(file.getContentType())
												   .build();
		
		try {
			s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
		} catch (S3Exception e) {
			log.error("S3 서비스 오류 (인증/권한/버킷문제): {}", e.getMessage());
			throw new FileUploadException("AWS S3 서버에서 에러가 발생했습니다.");
		} catch (AwsServiceException e) {
			log.error("파일 읽기 오류: {}", e.getMessage());
			throw new FileUploadException("파일 저장 중 입출력 오류가 발생했습니다.");
		} catch (SdkClientException e) {
			log.error("알 수 없는 파일 업로드 오류: {}", e.getMessage());
			throw new FileUploadException("파일 업로드 중 알 수 없는 오류가 발생했습니다.");
		} catch (IOException e) {
			log.error("알 수 없는 입 출력 오류: {}", e.getMessage());
			throw new FileUploadException("알 수 없는 파일 업로드 오류가 발생했습니다.");
		}
		
		String filePath = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + changeFilename;
		
		return filePath;
	}
	
	public void deleteStoredFile(String fileUrl) {

		if (fileUrl == null || fileUrl.isBlank()) {
			return;
		}

		try {
			// https://버킷.s3.리전.amazonaws.com/파일명 → 파일명만 추출
			String key = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

			DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder().bucket(bucketName).key(key).build();

			s3Client.deleteObject(deleteRequest);

			log.info("S3 파일 삭제 완료: {}", key);

		} catch (S3Exception | SdkClientException e) {
	        log.error("AWS S3 삭제 실패: {}", e.getMessage());
	        throw new FileUploadException("S3 파일 삭제 중 오류가 발생했습니다.");
	    } catch (Exception e) {
	        log.error("파일 삭제 중 일반 에러 발생: {}", e.getMessage());
	        throw new FileUploadException("파일 삭제 작업이 실패했습니다.");
	    }
	}
}
