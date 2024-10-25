package com.kosta.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // IoC 빈 (bean) 등록. 어디서든 가져다 쓸 수 있는 객체들을 생성
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션. 시큐리티 사용하는 어노테이션
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) 
//컨트롤러 url을 각각 주어서 권한을 확인할 수 있는 어노테이션,  prePostEnabled, securedEnabled
public class SecurityController {

	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();	 // csrf : cross site request forgery attack (크로스 사이트 요청 위조 공격)을 방어
		http.authorizeRequests() //authentication (로그인) authorize(권한)
			.antMatchers("/user/**").authenticated()  //Path가 user밑으로 들어오는 애들은 로그인 필수
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") //로그인 && 권한이 ADMIN 이거나 MANAGER 만 허용
			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')") //로그인 && 권한이 MANAGER만 허용
//			.antMatchers("/manager/**").hasAuthority("ROLE_MANAGER") //윗줄과 똑같은 의미임
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			// "/loginProc" 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행
			// 결과적으로 컨트롤러에 따로 "/loginProc" 을 구현하지 않는다.
			// 이 로그인 과정에서 필요한 것은 PrincipalDetails를 만들어 주는 것이다.
			.loginProcessingUrl("/loginProc") //로그인 페이지 안에서 로그인 처리하는 URL. 컨트롤러에는 존재하지 않는다. 시큐리티 처리함
			.defaultSuccessUrl("/"); //로그인 성공한 다음 페이지처리		
		
		return http.build();
	}
	
	
	@Bean 
	public BCryptPasswordEncoder encoderPassword() {
		return new BCryptPasswordEncoder();
	}
}
