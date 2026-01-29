package com.kh.foodreport.domain.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.foodreport.domain.member.model.dto.LikeDTO;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.member.model.dto.MemberReviewDTO;
import com.kh.foodreport.domain.member.model.dto.PlaceLikeDTO;
import com.kh.foodreport.domain.member.model.dto.PlaceReplyLikeDTO;
import com.kh.foodreport.domain.member.model.dto.ReviewLikeDTO;
import com.kh.foodreport.domain.member.model.dto.ReviewReplyLikeDTO;
import com.kh.foodreport.domain.member.model.vo.MemberImage;
import com.kh.foodreport.domain.member.model.vo.MemberVO;
import com.kh.foodreport.domain.member.model.vo.RestaurantVO;

@Mapper
public interface MemberMapper {
	
	// 회원가입
	int signUp(MemberVO member);
	
	// 이메일 중복 확인
	int countByEmail(String email);
	
	// 로그인
	MemberDTO loadUser(String username);
	
	// 비밀번호 변경
	void updatePassword(Map<String, String> changeRequest);
		
	// 회원 탈퇴
	int deleteMember(String memberId);
	
	// 로그인 검증용
	MemberDTO loadUserByMemberNo(Long memberNo);
	
	// 프로필 이미지 저장
	int saveImage(MemberImage image);
	
	// 프로필 이미지 수정
	int updateImage(MemberImage memberImage);
	
	// 프로필 이미지 삭제
	int deleteImage(Long imageNo);
	
	// 내 정보 조회
	MemberDTO findByMemberNo(Long memberNo);
	
	// 내 정보 수정
	int updateMember(MemberDTO updateMember);
	
	// 프로필 이미지 조회
	String findUrlByMemberNo(Long memberNo);
	
	// 리뷰 개수 조회
	int countByReviews(Long memberNo);
	
	// 회원 리뷰 전체 조회
	List<MemberReviewDTO> findAllReviews(Map<String, Object> params);
	
	// 사업자번호 중복 확인
	int countByBusinessNo(String businessNo);
	
	// 사장님 등록
	int saveOwner(RestaurantVO restaurantBuilder);
	
	// 회원 좋아요 목록 전체 조회
	void findAllLikes();
	
	// 전체 좋아요 수 (모든 테이블)
	int countAllLikes(Long memberNo);

	// 카테고리별 좋아요 수
	int countReviewLikes(Long memberNo);
	int countReviewReplyLikes(Long memberNo);
	int countPlaceLikes(Long memberNo);
	int countPlaceReplyLikes(Long memberNo);

	// 카테고리별 좋아요 목록 조회
	List<ReviewLikeDTO> findReviewLikes(@Param("memberNo") Long memberNo, @Param("pageInfo") Map<String, Object> map);
	List<ReviewReplyLikeDTO> findReviewReplyLikes(@Param("memberNo") Long memberNo, @Param("pageInfo") Map<String, Object> map);
	List<PlaceLikeDTO> findPlaceLikes(@Param("memberNo") Long memberNo, @Param("pageInfo") Map<String, Object> map);
	List<PlaceReplyLikeDTO> findPlaceReplyLikes(@Param("memberNo") Long memberNo, @Param("pageInfo") Map<String, Object> map);

	// 전체 좋아요 목록 (통합 조회)
	List<LikeDTO> findAllLikesByMemberNo(@Param("memberNo") Long memberNo, @Param("pageInfo") Map<String, Object> map);



	



}
