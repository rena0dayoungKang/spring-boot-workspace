package com.kosta.bank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.serivce.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/login")
	public ResponseEntity<MemberDto> login(@RequestBody Map<String, String> param) {
		try {
			String id = (String) param.get("id");
			String pwd = (String) param.get("password");
			MemberDto memberDto = memberService.login(id, pwd);
			memberDto.setPassword("");
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MemberDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) {
		try {
			memberService.join(memberDto);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/checkMemId/{id}")
	public ResponseEntity<String> checkMemId(@PathVariable String id) {
		try {
			boolean checkId = memberService.checkDoubleId(id);
			return new ResponseEntity<String>(String.valueOf(checkId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("아이디 중복 체크 오류", HttpStatus.BAD_REQUEST);
		}
	}

}
