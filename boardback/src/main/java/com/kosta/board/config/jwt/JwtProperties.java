package com.kosta.board.config.jwt;

public interface JwtProperties {
	String SECRET = "코스타"; //우리 서버의 고유키 
	Integer ACCESS_EXPIRATION_TIME = 60000*60*2; //2시간
	Integer REFRESH_EXPIRATION_TIME = 60000*60*24; //24시간
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
