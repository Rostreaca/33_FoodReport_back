package com.kh.foodreport.domain.admin.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.admin.notice.model.dao.AdminNoticeMapper;
import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeDTO;
import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeResponse;
import com.kh.foodreport.domain.admin.notice.model.vo.AdminNoticeImage;
import com.kh.foodreport.global.exception.FileUploadException;
import com.kh.foodreport.global.exception.InvalidKeywordException;
import com.kh.foodreport.global.exception.NoticeCreationException;
import com.kh.foodreport.global.file.service.FileService;
import com.kh.foodreport.global.util.PageInfo;
import com.kh.foodreport.global.util.Pagenation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminNoticeServiceImpl implements AdminNoticeService {

	private final AdminNoticeMapper noticeMapper;
	private final FileService fileService;
	private final Pagenation pagenation;

	private void saveImage(MultipartFile file, Long num) {

		// 파일 유효성 검사 // 나중에 파일서비스로 옮길거
		if (file == null || file.isEmpty()) {
			return;
		}

		String imageUrl = fileService.store(file);

		AdminNoticeImage image = AdminNoticeImage.builder().originName(file.getOriginalFilename()).changeName(imageUrl)
				.refNoticeNo(num).build();

		int imgResult = noticeMapper.saveImage(image);

		if (imgResult == 0) {
			fileService.deleteStoredFile(imageUrl);
		    throw new FileUploadException("이미지 저장 실패");
		}

	}
	
	@Override
	@Transactional
	public void saveNotice(AdminNoticeDTO notice, MultipartFile file) {

		// 1. 공지사항을 먼저 저장
		int result = noticeMapper.saveNotice(notice);

		if (result == 0) { // 공지사항이 저장안됐을시(예외처리)
			throw new NoticeCreationException("공지사항 등록에 실패하셨습니다!");
		}
		
		// 공지사항 번호를 뽑음
		Long num = notice.getNoticeNo();

		// 2. 공지사항의 번호로 이미지가 있다면 notice에 이미지 정보 세팅
		saveImage(file, num);
		
	}

	@Override
	@Transactional
	public AdminNoticeResponse findAllNotices(int page) {
		
		// 전체 개수 조회
		int listCount = noticeMapper.countByNotices();
				
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 10);
		
		List<AdminNoticeDTO> notices = noticeMapper.findAllNotices(pages);
		
		return createResponse(notices, pages);
	}
	
	@Override
	public AdminNoticeResponse findByNoticeTitle(int page, String noticeTitle) {
		
		if(noticeTitle == null || "".equals(noticeTitle.trim())) {
			throw new InvalidKeywordException("키워드를 입력해주세요.");
		}
		
		// 부분 개수 조회
		int listCount = noticeMapper.countByNoticeTitle(noticeTitle);
		
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 10);
		
		pages.put("noticeTitle", noticeTitle);
		
		List<AdminNoticeDTO> notices = noticeMapper.findByNoticeTitle(pages);
		
		return createResponse(notices, pages);
	}
	
	// 중복 메소드 분리 
	private AdminNoticeResponse createResponse(List<AdminNoticeDTO> notices, Map<String, Object> pages) {
		AdminNoticeResponse response = new AdminNoticeResponse();
		
		response.setAdminNotice(notices);
		response.setPageInfo(((PageInfo)pages.get("pageInfo")));
		return response;
	}

	@Override
	@Transactional
	public void deleteNotice(Long noticeNo) {
		
		
		
		// 1. 이미지 테이블 접근 1행 반환시 변경됨, 0행 반환시 변경안되거나 없음.
		int imageResult = noticeMapper.deleteNoticeImage(noticeNo);
		
		// 2. 다음 공지사항 테이블 접근 1행 반환시 잘반환됨, 0행 반환시 삭제가 안된거기때문에 예외발생
		int deleteResult = noticeMapper.deleteNotice(noticeNo);
		
		// 이미 삭제됐거나, 없는 데이터
		if(deleteResult == 0) {
			throw new t
		}
	}
	
}
