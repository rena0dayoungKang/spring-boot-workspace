package com.kosta.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

import com.kosta.board.config.jwt.JwtProperties;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		
		//둘중하나 선택
//		config.addAllowedOrigin("http://localhost:3000"); 
		config.addAllowedOriginPattern("*"); 
		
		config.addAllowedHeader("*"); //Access-Control-Allow-Headers 서버가 모든 종류의 요청 헤더를 허용하도록 설정
		config.addAllowedMethod("*"); //post, put, delete 등 모두 허용
		config.addExposedHeader(JwtProperties.HEADER_STRING); //클라이언트(리액트 등)가 응답에 접근할 수 있는 Header추가
		
		source.registerCorsConfiguration("/*", config);
		source.registerCorsConfiguration("/*/*", config);
		
		return new CorsFilter(source);
	}
}
