package com.kh.foodreport.global.config.filter;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kh.foodreport.domain.auth.model.vo.CustomUserDetails;
import com.kh.foodreport.domain.member.model.dao.MemberMapper;
import com.kh.foodreport.domain.member.model.dto.MemberDTO;
import com.kh.foodreport.domain.token.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
// 사용자가 토큰을 요청에 포함시켜 보냈을때 이 토큰이 유효한 것인지 검증할 필터
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	private final MemberMapper memberMapper;
		
	// 필터의 주요 로직을 구현하는 메서드,
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// log.info("요청 들어올 때 호출되는지 확인");
		
		String uri = request.getRequestURI();
		// log.info("요청 확인{}", uri);
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authorization == null || uri.equals("/auth/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// 토큰 검증
		
		//log.info("헤더에 포함시킨 Authorization : {}", authorization);
		String token = authorization.split(" ")[1];
		
		//log.info("토큰 값 : {}", token);
		// 1. 서버에서 관리하는 secret key로 만들었는가
		// 2. 유효기간이 지나지 않았는가
		
		try {		
			Claims claims = jwtUtil.parseJwt(token);
			String memberNo = claims.getSubject();
			
			//log.info("토큰 소유주의 아이디 값 : {}", memberNo);
			
			MemberDTO member = memberMapper.loadUserByMemberNo(Long.parseLong(memberNo));
			CustomUserDetails user = CustomUserDetails.builder()
					 								  .memberNo(member.getMemberNo())
					 								  .username(member.getEmail())
					 								  .password(member.getPassword())
					 								  .nickname(member.getNickname())
					 								  .phone(member.getPhone())
					 								  .introduce(member.getIntroduce())
					 								  .createDate(member.getCreateDate())
					 								  .updateDate(member.getUpdateDate())
					 								  .deleteDate(member.getDeleteDate())
					 								  .status(member.getStatus())
					 								  .role(member.getRole())
					 								  .build();
					 								  
													
			
			// log.info("DB에서 조회해온 user정보 : {}", user);
			UsernamePasswordAuthenticationToken authentication
				= new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			// 세부설정관련 사용자의 IP주소, MAC주소, sessionID 등을 포함시켜서 세팅
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			// 요청이 만료될 때까지 Authentication에 담겨있는 사용자의 정보를 사용
		
		} catch(ExpiredJwtException e) {
			// log.info("토큰의 유효기간 만료");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("토큰 만료");
			return;
		} catch(JwtException e) {
			// log.info("서버에서 만들어진 토큰이 아님");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("유효하지 않은 토큰입니다.");
			return;
		}
		filterChain.doFilter(request, response);
	}
	
}
