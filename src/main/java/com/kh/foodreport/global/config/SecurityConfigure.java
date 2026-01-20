package com.kh.foodreport.global.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kh.foodreport.global.config.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfigure {
	
	private final JwtFilter jwtFilter;
	
//	@Value("${instnace.url}")
//	private String instance;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity
				.formLogin(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(requests -> {
					// 비로그인 허용
					requests.requestMatchers("/ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll();
					requests.requestMatchers(HttpMethod.GET).permitAll();
					// 비로그인 허용(POST)
					requests.requestMatchers(HttpMethod.POST, "/api/reviews/*/replies","/api/members/images").authenticated();
//					requests.requestMatchers(HttpMethod.POST).permitAll();
					// 로그인 필요(GET)
					requests.requestMatchers(HttpMethod.PUT).permitAll(); // 나중에 삭제해야함.
					requests.requestMatchers(HttpMethod.DELETE).permitAll(); // 나중에 삭제해야함.
					
					requests.requestMatchers(HttpMethod.GET).authenticated();
					// 로그인 필요(POST)
					requests.requestMatchers(HttpMethod.POST, "/api/auth/login", "/api/members").permitAll();
					// 로그인 필요(PUT)
					requests.requestMatchers(HttpMethod.PUT, "/api/members").authenticated();
					// 로그인 필요(DELETE)
					requests.requestMatchers(HttpMethod.DELETE, "/api/members").authenticated();
					
					// 관리자
					requests.requestMatchers(HttpMethod.GET).hasAuthority("");
					requests.requestMatchers(HttpMethod.POST).hasAuthority("");
					requests.requestMatchers(HttpMethod.PUT).hasAuthority("");
					requests.requestMatchers(HttpMethod.DELETE).hasAuthority("");
				})
		        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		        .build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
}
