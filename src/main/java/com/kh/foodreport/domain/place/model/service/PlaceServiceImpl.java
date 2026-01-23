package com.kh.foodreport.domain.place.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.place.model.dao.PlaceMapper;
import com.kh.foodreport.domain.place.model.dto.PlaceDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceImageDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceReplyDTO;
import com.kh.foodreport.domain.place.model.dto.PlaceResponse;
import com.kh.foodreport.domain.place.model.vo.PlaceImage;
import com.kh.foodreport.global.exception.BoardCreationException;
import com.kh.foodreport.global.exception.FileUploadException;
import com.kh.foodreport.global.exception.ObjectCreationException;
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
public class PlaceServiceImpl implements PlaceService{
	
	private final FileService fileService;
	private final PlaceMapper placeMapper;
	private final Pagenation pagenation;
	
	@Override
	public PlaceResponse findAllPlaces(int page, Map<String, Object> params) {
		
		GlobalValidator.validateNo(page, "유효하지 않은 페이지 요청입니다.");
		
		int listCount = placeMapper.countByPlaces(String.valueOf(params.get("keyword")));
		
		Map<String, Object> pages = pagenation.getPageRequest(listCount, page, 9);
		
		params.putAll(pages);
		
		List<PlaceDTO> places = placeMapper.findAllPlaces(params);
		
		return new PlaceResponse(places, ((PageInfo) params.get("pageInfo")));
	}

	private void validatePlace(PlaceDTO place) {
		
		GlobalValidator.checkNull(place, "데이터가 존재하지 않습니다. 다시 시도해주세요.");
		GlobalValidator.checkBlank(place.getPlaceTitle(),"게시글 제목은 비어있을 수 없습니다.");
		GlobalValidator.checkBlank(place.getPlaceTitle(),"게시글 내용은 비어있을 수 없습니다.");		
	}
	
	@Transactional
	@Override
	public void savePlace(PlaceDTO place, List<Long> tagNums, List<MultipartFile> images) {
		
		validatePlace(place);
		
		int result = placeMapper.savePlace(place);

		if (result == 0) {
			throw new BoardCreationException("맛집 게시글 작성에 실패하였습니다.");
		}
		
		saveTags(place, tagNums);

		// 이미지가 존재할 경우 이미지 저장 메소드 호출
		if (images != null && !images.isEmpty()) {
			saveImages(place.getPlaceNo(), images);
		}
		
	}
	
	private void saveTags(PlaceDTO place, List<Long> tagNums) {

		Map<String, Object> params = new HashMap<>();
		
		params.put("placeNo", place.getPlaceNo());
		params.put("tagNums", tagNums);
		
		int tagResult = placeMapper.saveTagsByPlaceNo(params);
		
		if(tagResult == 0) {
			throw new ObjectCreationException("리뷰에 태그를 추가하는 과정에서 문제가 발생했습니다.");
		}
	}
	
	private void saveImages(Long placeNo, List<MultipartFile> images) {

		List<String> imageUrls = new ArrayList<>();

		for (int i = 0; i < images.size(); i++) {

			// 이미지가 존재하지 않을 경우 예외 발생
			if (images.get(i) == null || images.get(i).isEmpty()) {
				deleteImagesFromS3(imageUrls);
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

			PlaceImage placeImage = PlaceImage.builder().originName(images.get(i).getOriginalFilename())
														.changeName(changeName)
														.refPlaceNo(placeNo)
														.imageLevel(imageLevel)
														.build();

			int result = placeMapper.saveImage(placeImage);

			// 이미지 INSERT를 하나라도 실패했을 경우 S3에 저장한 모든 이미지 삭제 후 예외 
			if (result == 0) {
				deleteImagesFromS3(imageUrls);
			}
			
		}


	}
	
	private void deleteImagesFromS3(List<String> imageUrls) {

		if(!imageUrls.isEmpty()) {
			imageUrls.forEach(file -> {
				fileService.deleteStoredFile(file);
			});
		}

		throw new FileUploadException("이미지 업로드 실패");
	}

	@Override
	public PlaceDTO findPlaceByPlaceNo(Long placeNo) {
		
		GlobalValidator.validateNo(placeNo, "유효하지 않은 게시글 번호입니다.");
		
		PlaceDTO place = placeMapper.findPlaceByPlaceNo(placeNo); 
		
		// 게시글이 존재하지 않을 경우 예외 발생
		GlobalValidator.checkNull(place, "게시글이 존재하지 않습니다.");
		
		placeMapper.updateViewCount(placeNo);
		
		// 이미지 - 태그 - 댓글은 여러 개 존재할 수 있음 -> 행 조회룰 최소화(데이터 절약)하기 위해 전부 분리해줌 
		// 이미지, 태그, 댓글은 존재하지 않을 수 있으므로 따로 예외 발생 코드가 없음 
		List<PlaceImageDTO> images = placeMapper.findImagesByPlaceNo(placeNo);
		
		place.setPlaceImages(images);
		
		List<TagDTO> tags = placeMapper.findTagsByPlaceNo(placeNo);
		
		place.setTags(tags);
		
		List<PlaceReplyDTO> replies = placeMapper.findRepliesByPlaceNo(placeNo);
		
		place.setPlaceReplies(replies);
		
		return place;
	}

}
