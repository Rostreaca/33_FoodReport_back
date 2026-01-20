package com.kh.foodreport.domain.member.model.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.foodreport.domain.admin.notice.model.vo.AdminNoticeImage;
import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.member.model.dao.MemberMapper;
import com.kh.foodreport.domain.member.model.dto.ChangePasswordDTO;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.member.model.vo.MemberImage;
import com.kh.foodreport.domain.member.model.vo.MemberVO;
import com.kh.foodreport.domain.token.model.dao.TokenMapper;
import com.kh.foodreport.global.exception.CustomAuthenticationException;
import com.kh.foodreport.global.exception.EmailDuplicateException;
import com.kh.foodreport.global.exception.FileUploadException;
import com.kh.foodreport.global.exception.SignUpFailedException;
import com.kh.foodreport.global.exception.TokenDeleteException;
import com.kh.foodreport.global.file.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final TokenMapper tokenMapper;
	private final PasswordEncoder passwordEncoder;
	private final FileService fileService;


	@Override
	public void signUp(MemberDTO member) {
		
		// 유효성 검사  ==> Validator에게 위임
		// 이메일 중복 검사
		int count = memberMapper.countByEmail(member.getEmail());
		
		if(1 == count) {
			throw new EmailDuplicateException("이미 존재하는 이메일입니다.");
		}
		// 비밀번호 암호화
		
		// ROLE 주기
		MemberVO memberBuilder = MemberVO.builder()
										 .email(member.getEmail())
										 .password(passwordEncoder.encode(member.getPassword()))
										 .nickname(member.getNickname())
										 .phone(member.getPhone())
										 .role("ROLE_USER")
										 .build();

		// 매퍼 호출
		int result = memberMapper.signUp(memberBuilder);
		log.info("사용자 등록 성공 : {} ", memberBuilder);
		 
		if(0 == result) {
			throw new SignUpFailedException("회원가입에 실패했습니다.");
		}

	}

	@Override
	public void changePassword(ChangePasswordDTO password) {
		
		// 현재 비밀번호가 맞는지 검증
		// Authentication에서 현재 인증된 사용자의 정보 뽑아오기
		/*
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		
		String currentPassword = password.getCurrentPassword();
		String encodedPassword = user.getPassword();
		if(passwordEncoder.matches(currentPassword, currentPassword)) {
			throw new CustomAuthenticationException("일치하지 않는 비밀번호");
		}
		*/
		CustomUserDetails user = validatePassword(password.getCurrentPassword());
		// 현재 비밀번호가 맞다면 새 비밀번호를 암호화
		String newPassword = passwordEncoder.encode(password.getNewPassword());
		// UPDATE FR_MEMBER PASSWORD = "newpassword" WHERE EMAIL = "사용자ID"
		
		Map<String, String> changeRequest = Map.of("email", user.getUsername(),
												   "newPassword", newPassword);
		
		memberMapper.changePassword(changeRequest);
		
	}

	@Override
	@Transactional
	public void deleteByPassword(String password) {
		// 사용자가 입력한 비밀번호가 DB에 저장된 비밀번호 암호문이 맞는지 검증
		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		// 검증이 맞다면
		//if(!passwordEncoder.matches(password, user.getPassword())) {
		//	throw new CustomAuthenticationException("비밀번호가 일치하지 않습니다.");
		
		// DELETE FROM FR_MEMBER WHERE EMAIL = 사용자 아이디(이메일)
		CustomUserDetails user = validatePassword(password);
		int tokenResult = tokenMapper.deleteToken(user.getMemberNo());
		if(tokenResult == 0) {
			throw new TokenDeleteException("토큰 삭제에 실패했습니다.");
		}
		
		int memberResult = memberMapper.deleteMember(user.getUsername());
		if(memberResult == 0) {
			throw new SignUpFailedException("회원 탈퇴에 실패했습니다.");
		}
	}
	
	private CustomUserDetails validatePassword(String password) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
	// 검증이 맞다면
	if(!passwordEncoder.matches(password, user.getPassword())) {
		throw new CustomAuthenticationException("비밀번호가 일치하지 않습니다.");
	}
	return user;
		
	}
	
	public void saveImage(MemberDTO member, MultipartFile image) {

		if(image == null || image.isEmpty()) {
			return;
		}

		String changeName = fileService.store(image);
		
			// Mapper에 전달할 이미지 정보 memberImage 객체 생성
			MemberImage memberImage = MemberImage.builder()
					.originName(image.getOriginalFilename())
					.changeName(changeName).refMemberNo(member.getMemberNo()).build();

			int result = memberMapper.saveImage(memberImage);

			if(result == 0) {
				throw new FileUploadException("이미지 업로드에 실패했습니다.");
			}
	}

	
	
	
		
}
