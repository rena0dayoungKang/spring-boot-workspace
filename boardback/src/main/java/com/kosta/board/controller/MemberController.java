package com.kosta.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.config.auth.PrincipalDetails;
import com.kosta.board.dto.MemberDto;
import com.kosta.board.entity.Member;
import com.kosta.board.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/join")
	public ResponseEntity<String> join(MemberDto memberDto, 
										@RequestParam(name="profile", required=false) MultipartFile profile){
		try {
			String rawPassword = memberDto.getPassword();
			memberDto.setPassword(bCryptPasswordEncoder.encode(rawPassword));
			memberDto.setRoles("ROLE_USER");
			memberService.join(memberDto, profile);
			return new ResponseEntity<String>(String.valueOf(true), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<MemberDto> login(@RequestBody Map<String, String> param) {
		try {
			MemberDto memberDto = memberService.login(param.get("id"), param.get("password"));
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MemberDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/user") 
	public ResponseEntity<MemberDto> getMember(Authentication authentication) {
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		Member user = principalDetails.getUser();	
		System.out.println("controller:"+user);
		return new ResponseEntity<MemberDto>(user.toDto(), HttpStatus.OK);
	}
	

}
