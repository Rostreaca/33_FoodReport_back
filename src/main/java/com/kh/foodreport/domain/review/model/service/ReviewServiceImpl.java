package com.kh.foodreport.domain.review.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.review.model.dao.ReviewMapper;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.vo.Review;
import com.kh.foodreport.domain.review.model.vo.ReviewImage;
import com.kh.foodreport.global.exception.FileUploadException;
import com.kh.foodreport.global.exception.ReviewCreationException;
import com.kh.foodreport.global.file.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	
	private final ReviewMapper reviewMapper;
	private final FileService fileService;
	
	private int saveImage(Long reviewNo,List<MultipartFile> images) {
		
		// 썸네일 여부를 저장할 변수
		int imageLevel = 1;
		
		// Mapper에서 Insert문을 성공적으로 처리했는 지 확인 할 변수 
		int result = 1;
		
		// 예외 발생 시 이미지 삭제 요청을 보낼 URL을 모아놓을 리스트
		List<String> imageUrls = new ArrayList();
		
		for(int i=0; i<images.size(); i++){
			
			if(images.get(i) == null || images.get(i).isEmpty()) {
				return 0;
			}
			
			if(i == 0) { // 첫 이미지 : 썸네일(imageLevel : 0), 다른 이미지(imageLevel : 1)
				imageLevel = 0;
			} else {
				imageLevel = 1;
			}
			
			// S3로 파일 저장 후 DB에 담을 파일경로 + changeName 가져오기
			String changeName = fileService.store(images.get(i));
			
			imageUrls.add(changeName);
			
			// Mapper에 전달할 이미지 정보를 담을 reviewImage 객체 생성 
			ReviewImage reviewImage = ReviewImage.builder()
												.originName(images.get(i).getOriginalFilename())
												.changeName(changeName)
												.refReviewNo(reviewNo)
												.imageLevel(imageLevel)
												.build();
			
			
			result = result * reviewMapper.saveImage(reviewImage);
			
		}

		// 이미지 INSERT를 하나라도 실패했을 경우
		if(result == 0) {
			
			// 저장한 url들로 반복하여 삭제 요청을 보냄
			imageUrls.forEach(file -> {
				fileService.deleteStoredFile(file);
			});
			
			throw new FileUploadException("이미지 업로드 실패 ");
		}
		
		return result;
		
	}
	
	@Transactional
	@Override
	public void saveReview(ReviewDTO review, List<MultipartFile> images) {

		int imageResult = 0;
		int reviewResult = 0;
		
		log.info("{}", review);
		log.info("{}", images);
		
		// DB에 리뷰 내용 저장 및 resultSet으로 ReviewDTO의 reviewNo 필드에 값 대입 
		reviewResult = reviewMapper.saveReview(review);
		
		// 리뷰 INSERT 실패 시 예외 발생
		if(reviewResult == 0) {
			throw new ReviewCreationException("리뷰 생성에 실패하였습니다.");
		}
		
		// 이미지가 존재할 경우 이미지 저장 메서드 호출
		if(!images.isEmpty()) {
			imageResult = saveImage(review.getReviewNo(),images);
		}
		
		
	}

}
