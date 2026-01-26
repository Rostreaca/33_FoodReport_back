package com.kh.foodreport.domain.review.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.review.model.dao.ReviewMapper;
import com.kh.foodreport.domain.review.model.dto.ReviewDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewImageDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewReplyDTO;
import com.kh.foodreport.domain.review.model.dto.ReviewResponse;
import com.kh.foodreport.domain.review.model.vo.ReviewImage;
import com.kh.foodreport.domain.review.model.vo.ReviewLike;
import com.kh.foodreport.domain.review.model.vo.ReviewReply;
import com.kh.foodreport.global.exception.BoardDeleteException;
import com.kh.foodreport.global.exception.BoardLikeFailedException;
import com.kh.foodreport.global.exception.FileManipulateException;
import com.kh.foodreport.global.exception.FileUploadException;
import com.kh.foodreport.global.exception.InvalidRequestException;
import com.kh.foodreport.global.exception.ObjectCreationException;
import com.kh.foodreport.global.exception.PageNotFoundException;
import com.kh.foodreport.global.exception.ReplyCreationException;
import com.kh.foodreport.global.exception.ReviewCreationException;
import com.kh.foodreport.global.exception.TagDeleteException;
import com.kh.foodreport.global.file.service.FileService;
import com.kh.foodreport.global.tag.model.dto.TagDTO;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;
import com.kh.foodreport.global.validator.GlobalValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewMapper reviewMapper;
	private final ReviewValidator reviewValidator;
	private final FileService fileService;
	private final Pagenation pagenation;

	private void saveImages(Long reviewNo, List<MultipartFile> images) {

		// Mapper에서 Insert문을 성공적으로 처리했는 지 확인 할 변수
		int result = 1;

		// 예외 발생 시 이미지 삭제 요청을 보낼 URL을 모아놓을 리스트
		List<String> imageUrls = new ArrayList();

		for (int i = 0; i < images.size(); i++) {

			// 이미지가 하나라도 존재하지 않을 경우 예외 발생
			if (images.get(i) == null || images.get(i).isEmpty()) {
				result = 0;
				break;
			}

			// 썸네일 여부를 저장할 변수
			int imageLevel = 0;

			if (i == 0) { // 첫 이미지 : 썸네일(imageLevel : 0), 다른 이미지(imageLevel : 1)
				imageLevel = 0;
			} else {
				imageLevel = 1;
			}

			// S3로 파일 저장 후 DB에 담을 파일경로 + changeName 가져오기
			String changeName = fileService.store(images.get(i));

			imageUrls.add(changeName);

			// Mapper에 전달할 이미지 정보를 담을 reviewImage 객체 생성
			ReviewImage reviewImage = ReviewImage.builder().originName(images.get(i).getOriginalFilename())
					.changeName(changeName).refReviewNo(reviewNo).imageLevel(imageLevel).build();

			result = result * reviewMapper.saveImage(reviewImage);

		}

		// 이미지 INSERT를 하나라도 실패했을 경우
		if (result == 0) {
			deleteImagesFromS3(imageUrls);
		}

	}

	private void deleteImagesFromS3(List<String> imageUrls) {

		imageUrls.forEach(file -> {
			fileService.deleteStoredFile(file);
		});

		throw new FileUploadException("이미지 업로드 실패");
	}
	
	private void saveTags(Long reviewNo, List<Long> tagNums) {

		Map<String, Object> params = new HashMap<>();

		params.put("reviewNo", reviewNo);
		params.put("tagNums", tagNums);
		
		int tagResult = reviewMapper.saveTagByReviewNo(params);
		
		if(tagResult == 0) {
			throw new ObjectCreationException("리뷰에 태그를 추가하는 과정에서 문제가 발생했습니다.");
		}

		
	}

	@Transactional
	@Override
	public void saveReview(ReviewDTO review, List<Long> tagNums,List<MultipartFile> images) {
		
		reviewValidator.validateReview(review);
		
		// DB에 리뷰 내용 저장 및 resultSet으로 ReviewDTO의 reviewNo 필드에 값 대입
		int result = reviewMapper.saveReview(review);

		// 리뷰 INSERT 실패 시 예외 발생
		if (result == 0) {
			throw new ReviewCreationException("리뷰 생성에 실패하였습니다.");
		}
		
		if(tagNums != null && !tagNums.isEmpty()) {
			saveTags(review.getReviewNo(), tagNums);
		}

		// 이미지가 존재할 경우 이미지 저장 메소드 호출
		if (images != null && !images.isEmpty()) {
			saveImages(review.getReviewNo(), images);
		}

	}

	@Override
	public ReviewResponse findAllReviews(int page, Map<String, Object> params) {

		GlobalValidator.validateNo(page, "유효하지 않은 페이지 요청입니다.");
		
		// 페이징 처리용 게시글 개수 SELECT
		int listCount = reviewMapper.countByReviews(params);

		// 페이지 처리 메소드 호출, PageInfo객체와 offset, limit를 담은 Map을 반환받음
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 9);

		// Map을 하나로 만들어서 Mapper에 요청을 보내기 위해 putAll을 사용해 두 개의 맵을 하나로 합침
		params.putAll(pages);

		// DB에서 전체 리뷰 목록 조회
		List<ReviewDTO> reviews = reviewMapper.findAllReviews(params);
		
		// 응답 값을 ReviewResponse에 담아 반환
		return new ReviewResponse(reviews, ((PageInfo) params.get("pageInfo")));
	}
	
	@Override
	public ReviewDTO findByReviewNo(Long reviewNo) {

		GlobalValidator.validateNo(reviewNo, "유효하지 않은 게시글 번호입니다.");

		reviewMapper.updateViewCount(reviewNo);

		ReviewDTO review = reviewMapper.findReviewByReviewNo(reviewNo);

		if (review == null) {
			throw new PageNotFoundException("존재하지 않는 페이지 입니다.");
		}

		List<ReviewReplyDTO> reviewReplies = reviewMapper.findRepliesByReviewNo(reviewNo);
		
		List<TagDTO> tags = reviewMapper.findTagByReviewNo(reviewNo);
		
		review.setReviewReplies(reviewReplies);
		
		review.setTags(tags);
		
		return review;
	}

	@Transactional
	@Override
	public void updateReview(ReviewDTO review, List<Long> tagNums, List<MultipartFile> images) {

		GlobalValidator.validateNo(review.getReviewNo(), "유효하지 않은 게시글 번호입니다.");
		reviewValidator.validateReview(review);

		int result = reviewMapper.updateReview(review);

		if (result == 0) {
			throw new ReviewCreationException("리뷰 내용 수정에 실패했습니다");
		}

		if(images != null && !images.isEmpty()) {
			updateImages(review.getReviewNo(), images);
		}

		if(tagNums != null && !tagNums.isEmpty()) {
			updateTags(review.getReviewNo(), tagNums);
		}

	}
	
	private void updateTags(Long reviewNo, List<Long> tagNums) {
		deleteTags(reviewNo);
		
		saveTags(reviewNo, tagNums);
	}
	
	private void deleteTags(Long reviewNo) {
		int result = reviewMapper.deleteTags(reviewNo);
		
		if(result == 0) {
			throw new TagDeleteException("태그 처리 과정 중 문제가 발생했습니다.");
		}
	}
	
	
	private void updateImages(Long reviewNo, List<MultipartFile> images) {
		
		List<ReviewImageDTO> reviewImages = reviewMapper.findImagesByReviewNo(reviewNo);
		
		if (reviewImages != null && !reviewImages.isEmpty()) { // 기존 이미지 X

			reviewImages.forEach(image -> { // 반복
				deleteImage(image); // 새파일 저장이 성공적으로 끝나면 S3에서 기존 파일 삭제 및 DB STATUS 변경
			});
		}
		
		saveImages(reviewNo, images);
		
	}

	// 이미지 삭제 메소드
	private void deleteImage(ReviewImageDTO image) {

		// DB에서 이미지 삭제
		int result = reviewMapper.deleteImage(image.getImageNo());

		if (result == 0) {
			throw new FileManipulateException("기존 이미지 삭제에 실패했습니다.");
		}

		// S3에서 파일 삭제
		fileService.deleteStoredFile(image.getChangeName());

	}

	@Transactional
	@Override
	public void deleteReview(Long reviewNo) {

		GlobalValidator.validateNo(reviewNo, "유효하지 않은 게시글 번호입니다.");
		
		ReviewDTO review = reviewMapper.findReviewByReviewNo(reviewNo);
		
		if(review == null) {
			throw new PageNotFoundException("존재하지 않는 페이지 입니다.");
		}
		
		int reviewResult = reviewMapper.deleteReview(reviewNo);
		
		if(reviewResult == 0) {
			throw new BoardDeleteException("리뷰 삭제에 실패했습니다.");
		}

		// 리뷰 삭제 성공 시 리뷰에 담겨있는 이미지 전부 같이 삭제 (S3로 삭제하므로 개별 삭제)
		if (review.getReviewImages() != null && !review.getReviewImages().isEmpty()) {
			review.getReviewImages().forEach(image -> {
				deleteImage(image); // 이미지 삭제 메소드 호출
			});
		}
		
	}

	@Override
	public void saveReply(Long reviewNo, ReviewReplyDTO reply) {
		
		GlobalValidator.validateNo(reviewNo, "유효하지 않은 게시글 번호입니다.");
		
		ReviewReply replyVO = ReviewReply.builder()
											  .replyContent(reply.getReplyContent())
											  .replyWriter(reply.getReplyWriter())
											  .refReviewNo(reviewNo)
											  .build();
		
		int result = reviewMapper.saveReply(replyVO);
		
		if(result == 0) {
			throw new ReplyCreationException("댓글 작성에 실패했습니다.");
		}
		
		
	}

	@Override
	public void saveLike(Long reviewNo, Long memberNo) {
		
		GlobalValidator.validateNo(reviewNo, "유효하지 않은 게시글 번호입니다.");

		ReviewLike reviewLike = ReviewLike.createReviewLike(reviewNo, memberNo);
		
		// Postman 등으로 좋아요를 여러번 요청했을 경우 예외 발생용 코드
		int likeCount = reviewMapper.countLikeByMember(reviewLike);
		
		if(likeCount == 1) {
			throw new InvalidRequestException("유효하지 않은 요청입니다.");
		}
		
		int result = reviewMapper.saveLike(reviewLike);
		
		if(result == 0) {
			throw new BoardLikeFailedException("좋아요 등록에 실패했습니다.");
		}
		
	}

	@Override
	public void deleteLike(Long reviewNo, Long memberNo) {
		
		GlobalValidator.validateNo(reviewNo, "유효하지 않은 게시글 번호입니다.");
		
		ReviewLike reviewLike = ReviewLike.createReviewLike(reviewNo, memberNo);
		
		int likeCount = reviewMapper.countLikeByMember(reviewLike);
		
		if(likeCount == 0) {
			throw new InvalidRequestException("유효하지 않은 요청입니다.");
		}
		
		int result = reviewMapper.deleteLike(reviewLike);
		
		if(result == 0) {
			throw new BoardLikeFailedException("좋아요 취소에 실패했습니다.");
		}
		
	}
	
	

}
