package com.kosta.board.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kosta.board.config.auth.PrincipalDetails;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.MemberRepository;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {
	
	@Autowired
	private MemberRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("PrincipalOAuth2UserService===========================");
		// 유저정보를 가져옴

		System.out.println("getClientRegistration:" + userRequest.getClientRegistration());
		System.out.println("getAccessToken:" + userRequest.getAccessToken());
		System.out.println("getAdditionalParameters:" + userRequest.getAdditionalParameters());

		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("oAuth2User:" + oAuth2User);
		System.out.println(oAuth2User.getAttributes());
		return processOAuth2User(userRequest, oAuth2User);
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		System.out.println("Service :  =======================================");
		OAuth2UserInfo oAuth2UserInfo = null;
		if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			System.out.println("카카오 로그인");
			oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인");
			oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttribute("response"));
		} else {
			System.out.println("카카오와 네이버만 지원합니다.");	
		}
		
		//1. DB에서 조회
		Member user = userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());

		if (user != null) {  //1-1. 이미 가입되어 있으면 정보 수정
			user.setEmail(oAuth2UserInfo.getEmail());
			userRepository.save(user);
		} else {  //1-2. 가입되어 있지 않으면 삽입
			Member nUser = Member.builder()
								.id(oAuth2UserInfo.getProviderId())
								.email(oAuth2UserInfo.getEmail())
								.roles("ROLE_USER")
								.provider(oAuth2UserInfo.getProvider())
								.providerId(oAuth2UserInfo.getProviderId())
								.build();
			userRepository.save(nUser);
		}
		return new PrincipalDetails(user, oAuth2User.getAttributes());
	}

}
