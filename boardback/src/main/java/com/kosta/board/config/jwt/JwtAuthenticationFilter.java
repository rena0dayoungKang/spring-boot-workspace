package com.kosta.board.config.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.board.config.auth.PrincipalDetails;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	private JwtToken jwtToken = new JwtToken();
	
	//super의 attempAuthentication 메소드가 실행되고 성공하면 successfulAuthentication가 호출된다.
	//attempAuthentication 메소드가 리턴해준 Authentication을 파라미터로 받아옴
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		System.out.println("JwtAuthenticationFilter==============================");
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		String accessToken = jwtToken.makeAccessToken(principalDetails.getUsername());
		String refreshToken = jwtToken.makeRefreshToken(principalDetails.getUsername());
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		map.put("access_token", JwtProperties.TOKEN_PREFIX+accessToken);
		map.put("refresh_token", JwtProperties.TOKEN_PREFIX+refreshToken);
		
		String token = objectMapper.writeValueAsString(map); //map을 Json String으로 바꿔줌 
		//System.err.println("map을 Json String으로 바꿔줌 "+token);
		System.out.println(token);
		
		response.addHeader(JwtProperties.HEADER_STRING, token);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write("true");
	}
	
	
	
	
}
