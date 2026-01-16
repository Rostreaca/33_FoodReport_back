package com.kh.foodreport.global.config.filter;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kh.foodreport.domain.token.util.JwtUtil;

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
	
	private final JwtUtil jwtutil;
		
	// 필터의 주요 로직을 구현하는 메서드,
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// log.info("요청 들어올 때 호출되는지 확인");
		
		String uri = request.getRequestURI();
		// log.info("요청 확인{}", uri);
		if(uri.equals("/api/auth/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// 토큰 검증
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		log.info("헤더에 포함시킨 Authorization : {}", authorization);
		String token = authorization.split(" ")[1];
		log.info("토큰 값 : {}", token);
				
		
	}
	
}
