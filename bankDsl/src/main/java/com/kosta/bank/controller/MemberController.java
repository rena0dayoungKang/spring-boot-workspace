package com.kosta.bank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.entity.Member;
import com.kosta.bank.serivce.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired
	private HttpSession session; //session에다가 member를 저장하기 위함 

	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join(MemberDto memberDto, Model model) {
		try {
			memberService.join(memberDto);
			return "login";
		} catch (Exception e) {
			model.addAttribute("err", e.getMessage());
			return "err";
		}	
	}
	
	@PostMapping("/memberDoubleId")
	@ResponseBody
	public String memberDoubleId(@RequestParam("id") String id) {
		try {
			boolean check = memberService.checkDoubleId(id);
			return String.valueOf(check); 
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("id") String id, @RequestParam("password") String password, @RequestParam(name="type", required=false) String autoLogin, Model model) {
		try {
			MemberDto memberDto = memberService.login(id, password);
			memberDto.setPassword("");
			session.setAttribute("user", memberDto); //member 정보를 session에 저장 
			return "makeaccount";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}
	
	@GetMapping("/logout") 
	public String logout() {
		session.removeAttribute("user");
		return "login";
	}
}
