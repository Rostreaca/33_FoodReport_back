package com.kh.foodreport.domain.admin.notice.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.admin.notice.model.dao.AdminNoticeMapper;
import com.kh.foodreport.domain.admin.notice.model.dto.AdminNoticeDTO;
import com.kh.foodreport.domain.admin.notice.model.vo.AdminNoticeImage;
import com.kh.foodreport.global.exception.NoticeCreationException;
import com.kh.foodreport.global.exception.NoticeImageUploadException;
import com.kh.foodreport.global.file.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminNoticeServiceImpl implements AdminNoticeService {

	private final AdminNoticeMapper noticeMapper;
	private final FileService fileService;

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
		    throw new NoticeImageUploadException("이미지 저장 실패");
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

}
