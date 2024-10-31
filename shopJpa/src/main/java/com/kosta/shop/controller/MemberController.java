package com.kosta.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.MemberDto;
import com.kosta.shop.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@Autowired
	private HttpSession session;

	@GetMapping("/signUp")
	public String signUp() {
		return "signUpForm";
	}

	@PostMapping("/signUp")
	public ModelAndView signUp(MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("action", "회원가입");
		mav.setViewName("memberResult");
		try {
			memberService.signUp(memberDto);
			mav.addObject("message", "회원가입 성공");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", "회원가입 실패");
		}
		return mav;
	}

	@RequestMapping(value = "/idCheck", produces="text/plain;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody //Ajax니깐 ResponseBody필요 
	public String idCheck(@RequestParam("id") String userid, @RequestParam("pw") String pw) {
		// ** 자바스크립트 data에서 id와 pw를 보내주기 때문에 받아오는 컨트롤러에서도 두개다 받아와야 함 **
		try {
			if(memberService.idCheck(userid)) {
				return "사용불가능";
			} else {
				return "사용가능";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@GetMapping("/index") 
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("userid") String userid, @RequestParam("passwd") String passwd, Model model) {
		try {
			MemberDto memberDto = memberService.login(userid, passwd);
			session.setAttribute("user", memberDto);
			return "redirect:/main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("action", "로그인");
			model.addAttribute("message", e.getMessage());
			return "memberResult";
		}
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute("member");
		return "login";
	}

}
