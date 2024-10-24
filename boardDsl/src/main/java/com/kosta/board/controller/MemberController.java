package com.kosta.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.MemberDto;
import com.kosta.board.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("id") String id, @RequestParam("password") String password, 
						@RequestParam(name="type", required=false) String autoLogin, Model model) {
		try {
			MemberDto memberDto = memberService.login(id, password);
			memberDto.setPassword("");
			session.setAttribute("member", memberDto); //member 정보를 session에 저장 
			return "redirect:boardList"; //컨트롤러의 boardList를 다시 요청해야 다시 조회해서 리스트를 조회할 수 있음.
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}
	
	@PostMapping("/memberDoubldId")
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
	
	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute("member");
		return "login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join(MemberDto memberDto, MultipartFile file, Model model) {
		try {
			if(file != null && !file.isEmpty()) {
				memberDto.setProfileImage(file.getBytes());
			}
			memberService.join(memberDto);
			return "login";
		} catch (Exception e) {
			model.addAttribute("err", e.getMessage());
			return "err";
		}
		
	}	
}
