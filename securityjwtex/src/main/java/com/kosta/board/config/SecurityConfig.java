//package com.kosta.board.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.CorsFilter;
//
//import com.kosta.board.config.jwt.JwtAuthenticationFilter;
//
//@Configuration	//IoC빈 (Bean)등록
//@EnableWebSecurity //필터 체인 관리 시작 어노테이션 
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class SecurityConfig {
//	
//	@Autowired
//	private CorsFilter corsFilter;
//	
////	@Bean
////	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
////		return authenticationConfiguration.getAuthenticationManager();
////	}
//	
//	
//	
//	@Bean
//	public BCryptPasswordEncoder encoderPassword() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean 
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//		
//		http.addFilter(corsFilter)  //다른 도메인 접근 허용 
//			.csrf().disable()		//csrf 공격 비활성화
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //session 비활성화
//		
//		//로그인 
//		http.formLogin().disable() //로그인 폼 비활성화
//			.httpBasic().disable() //httpBasic은 header에 username, password를 암호화하지 않은 상태로 주고 받는다. 이를 사용하지 않겠다
//			.addFilterAt(new JwtAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
//			
//		return http.build();
//	}
//	
//}
