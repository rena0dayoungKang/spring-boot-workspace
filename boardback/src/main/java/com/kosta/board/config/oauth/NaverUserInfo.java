package com.kosta.board.config.oauth;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {
	
	private Map<String, Object> attributes; //가져온 정보를 모두 map형태로 바꿔줌
	
	public NaverUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;		
	}

	@Override
	public String getProviderId() {
		return (String)attributes.get("id");
	}

	@Override
	public String getProvider() {
		return "Naver";
	}

	@Override
	public String getEmail() {
		return (String)attributes.get("email");
	}

	@Override
	public String getName() {
		return (String)attributes.get("name");
	}

}
