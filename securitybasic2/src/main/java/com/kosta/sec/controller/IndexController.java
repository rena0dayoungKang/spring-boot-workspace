package com.kosta.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.sec.auth.PrincipalDetails;
import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
		
	@GetMapping({"", "/"}) //두개의 url 매핑 쓰는방법
	@ResponseBody
	public String index() {
		return "인덱스입니다.";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/joinProc")
	public String joinProc(User user) {
		
		//패스워드를 암호화해서 저장해야한다 -->  BCryptPasswordEncoder
		String rawPassword = user.getPassword();
		String encodePassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encodePassword);
		userRepository.save(user);
		
		return "redirect:/";
	}
	
	//방식1
	@GetMapping("/user")
	@ResponseBody
	public String user() {
		return "유저입니다.";
	}
	
	//방식2
	@GetMapping("/user1")
	@ResponseBody
	public String user1(@AuthenticationPrincipal PrincipalDetails principal) { 
						//함수의 파라미터에 @AuthenticationPrincipal 생성도 가능한 방법
		User user = principal.getUser();
		System.out.println(user);
		return "유저입니다.";
	}
	
//	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") //prePostEnabled = true라서 가능
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "관리자입니다.";
	}
	
	//@Secured("ROLE_MANAGER") //securedEnabled = true 라서 가능, 권한이 한개인경우만 가능 
//	@PreAuthorize("hasRole('ROLE_MANAGER')") //권한 두개를 줄 경우는 PreAuthorize로 hasRole으로 쓸 수 밖에 없음
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "매니저입니다.";
	}
	
	
}
