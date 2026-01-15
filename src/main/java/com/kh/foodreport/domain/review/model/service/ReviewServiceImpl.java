package com.kh.foodreport.domain.review.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.review.model.dao.ReviewMapper;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewResponse;
import com.kh.foodreport.domain.review.model.vo.ReviewImage;
import com.kh.foodreport.global.exception.FileUploadException;
import com.kh.foodreport.global.exception.PageNotFoundException;
import com.kh.foodreport.global.exception.ReviewCreationException;
import com.kh.foodreport.global.file.service.FileService;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	
	private final ReviewMapper reviewMapper;
	private final FileService fileService;
	private final Pagenation pagenation;
	private final GlobalValidator globalValidator;
	
	
	private void saveImage(Long reviewNo,List<MultipartFile> images) {
		
		
		// Mapper에서 Insert문을 성공적으로 처리했는 지 확인 할 변수 
		int result = 1;
		
		// 예외 발생 시 이미지 삭제 요청을 보낼 URL을 모아놓을 리스트
		List<String> imageUrls = new ArrayList();
		
		for(int i=0; i<images.size(); i++){

			// 이미지가 하나라도 존재하지 않을 경우 예외 발생
			if(images.get(i) == null || images.get(i).isEmpty()) {
				result = 0;
				break;
			}

			// 썸네일 여부를 저장할 변수
			int imageLevel = 0;
			
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
		
	}
	
	@Transactional
	@Override
	public void saveReview(ReviewDTO review, List<MultipartFile> images) {
		
		// DB에 리뷰 내용 저장 및 resultSet으로 ReviewDTO의 reviewNo 필드에 값 대입 
		int reviewResult = reviewMapper.saveReview(review);
		
		// 리뷰 INSERT 실패 시 예외 발생
		if(reviewResult == 0) {
			throw new ReviewCreationException("리뷰 생성에 실패하였습니다.");
		}
		
		// 이미지가 존재할 경우 이미지 저장 메서드 호출
		if(!images.isEmpty()) {
			saveImage(review.getReviewNo(),images);
		}
		
		
	}


	@Override
	public ReviewResponse findAllReviews(int page, Map<String, Object> params) {
		
		
		// 페이징 처리용 게시글 개수 SELECT
		int listCount = reviewMapper.countByReviews(params);
		
		// 페이지 처리 메서드 호출, PageInfo객체와 offset, limit를 담은 Map을 반환받음
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 9);

		// Map을 하나로 만들어서 Mapper에 요청을 보내기 위해 putAll을 사용해 두 개의 맵을 하나로 합침
		params.putAll(pages);
		
		// DB에서 전체 리뷰 목록 조회
		List<ReviewDTO> reviews = reviewMapper.findAllReviews(params);

		// 응답 값을 ReviewResponse에 담아 반환
		ReviewResponse response = new ReviewResponse(reviews, ((PageInfo)params.get("pageInfo")));
		
		return response;
	}

	@Override
	public ReviewDTO findByReviewNo(Long reviewNo) {
		
		globalValidator.validateNo(reviewNo, "존재하지 않는 페이지입니다.");
		
		reviewMapper.updateViewCount(reviewNo);
		
		ReviewDTO review = reviewMapper.findByReviewNo(reviewNo);
		
		if(review == null) {
			throw new PageNotFoundException("존재하지 않는 페이지 입니다.");
		}
		
		return review;
	}
	
	
	

}
