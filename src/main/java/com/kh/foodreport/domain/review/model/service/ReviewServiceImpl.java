package com.kh.foodreport.domain.review.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.review.model.dao.ReviewMapper;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.vo.ReviewImage;
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
		int result = 0;
		
		for(int i=0; i<images.size(); i++){
			
			if(i == 0) { // 첫 이미지 : 썸네일(imageLevel : 0), 다른 이미지(imageLevel : 1)
				imageLevel = 0;
			} else {
				imageLevel = 1;
			}
			
			// S3로 파일 저장 후 DB에 담을 파일경로 + changeName 가져오기
			String changeName = fileService.store(images.get(i));
			
			// 
			ReviewImage reviewImage = ReviewImage.builder()
												.originName(images.get(i).getOriginalFilename())
												.changeName(changeName)
												.refReviewNo(reviewNo)
												.imageLevel(imageLevel)
												.build();
			
			result = result * reviewMapper.saveImage(reviewImage) == 1 ? 1 : 0;
		}
		
		return result;
		
	}
	
	@Transactional
	@Override
	public void saveReview(ReviewDTO review, List<MultipartFile> images) {

		log.info("{}", review);
		log.info("{}", images);
		
		// DB에 리뷰 내용 저장 및 resultSet으로 ReviewDTO의 reviewNo 필드에 값 대입 
		int reviewResult = reviewMapper.saveReview(review);
		
		int imageResult = saveImage(review.getReviewNo(),images);
		
//		if(reviewResult * imageResult == 0) {
//			
//		}
		
	}

}
