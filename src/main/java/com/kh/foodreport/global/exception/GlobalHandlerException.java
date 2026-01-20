package com.kh.foodreport.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.foodreport.global.common.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {

	
	


	/* 공통 응답 포맷 */
	private ResponseEntity<ApiResponse<Object>> createErrorResponseEntity(Exception e, HttpStatus status) {
		log.error("잘못된 상태 : {}", e.getMessage());
		return ApiResponse.failure(e.getMessage(), status);
	}

	// 인증 실패 예외
	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<ApiResponse<Object>> handleCustomAuthenticationException(CustomAuthenticationException e){
		return createErrorResponseEntity(e, HttpStatus.UNAUTHORIZED);
	}
	
	// 유저 정보 전달 실패 예외
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleUsernameNotFoundException(UsernameNotFoundException e) {
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 이메일 중복 예외
	@ExceptionHandler(EmailDuplicateException.class)
	public ResponseEntity<ApiResponse<Object>> handleEmailDuplicateException(EmailDuplicateException e) {
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 회원가입 실패 예외
	@ExceptionHandler(SignUpFailedException.class)
	public ResponseEntity<ApiResponse<Object>> handleSignUpFailedException(SignUpFailedException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 토큰 삭제 실패 예외
	@ExceptionHandler(TokenDeleteException.class)
	public ResponseEntity<ApiResponse<Object>> handleTokenDeleteException(TokenDeleteException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 회원 탈퇴 실패 예외
	@ExceptionHandler(SignOutFailedException.class)
	public ResponseEntity<ApiResponse<Object>> handleSignOutFailedException(SignOutFailedException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 잘못된 상태 전달시
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalStateException e) {
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 공지사항 못만들었을 때 예외 
	@ExceptionHandler(NoticeCreationException.class)
	public ResponseEntity<ApiResponse<Object>> handleNoticeCreationException(NoticeCreationException e) {
		return createErrorResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 리뷰 생성 실패 예외
	@ExceptionHandler(ReviewCreationException.class)
	public ResponseEntity<ApiResponse<Object>> handleReviewCreationException(ReviewCreationException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 파일 업로드 예외
	@ExceptionHandler(FileUploadException.class)
	public ResponseEntity<ApiResponse<Object>> handleFileUploadException(FileUploadException e) {
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 파일 조작(수정/ 삭제) 예외
	@ExceptionHandler(FileManipulateException.class)
	public ResponseEntity<ApiResponse<Object>> handleFileManipulateException(FileManipulateException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}

	// 페이징 처리 예외
	@ExceptionHandler(PageNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handlePageNotFoundException(PageNotFoundException e) {
		return createErrorResponseEntity(e, HttpStatus.NOT_FOUND);
	}
	
	// 키워드 공백 및 입력 null값 예외
	@ExceptionHandler(InvalidKeywordException.class)
	public ResponseEntity<ApiResponse<Object>> handleInvalidKeywordException(InvalidKeywordException e) {
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 생성 실패 예외
	@ExceptionHandler(ObjectCreationException.class)
	public ResponseEntity<ApiResponse<Object>> handleObjectCreationException(ObjectCreationException e) {
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 게시글 삭제 실패 예외
	@ExceptionHandler(BoardDeleteException.class)
	public ResponseEntity<ApiResponse<Object>> handleBoardDeleteException(BoardDeleteException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 태그 업데이트 실패 예외
	@ExceptionHandler(TagUpdateException.class)
	public ResponseEntity<ApiResponse<Object>> handleTagUpdateException(TagUpdateException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 태그 삭제 실패 예외
	@ExceptionHandler(TagDeleteException.class)
	public ResponseEntity<ApiResponse<Object>> handleTagDeleteException(TagDeleteException e) {
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 댓글 등록 실패 예외
	@ExceptionHandler(ReplyCreationException.class)
	public ResponseEntity<ApiResponse<Object>> handleReplyCreationException(ReplyCreationException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 댓글 수정 실패 예외
	@ExceptionHandler(ReplyUpdateException.class)
	public ResponseEntity<ApiResponse<Object>> handleReplyUpdateException(ReplyUpdateException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}

	// 댓글 삭제 실패 예외
	@ExceptionHandler(ReplyDeleteException.class)
	public ResponseEntity<ApiResponse<Object>> handleReplyDeleteException(ReplyDeleteException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 좋아요 등록 실패 예외
	@ExceptionHandler(BoardLikeFailedException.class)
	public ResponseEntity<ApiResponse<Object>> handleBoardLikeFailedException(BoardLikeFailedException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	// 유효하지 않은 요청 시 에외
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ApiResponse<Object>> handleInvalidRequestException(InvalidRequestException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidValueException.class)
	public ResponseEntity<ApiResponse<Object>> handleInvalidValueException(InvalidValueException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BoardUpdateException.class)
	public ResponseEntity<ApiResponse<Object>> handleBoardUpdateException(BoardUpdateException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ReplyLikeFailedException.class)
	public ResponseEntity<ApiResponse<Object>> handleReplyLikeFailedException(ReplyLikeFailedException e){
		return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
}
