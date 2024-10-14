package com.kosta.bank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	
	@GetMapping("/makeAccount")
	public String makeAccount() {
		return "makeaccount";
	}
	
	@PostMapping("/makeAccount")
	public ModelAndView makeAccount(Account acc) {
		ModelAndView mav = new ModelAndView();
		try {
			Account sacc = accountService.makeAccount(acc);
			mav.addObject("acc", sacc);
			mav.setViewName("accountinfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	@GetMapping("/accountInfo")
	public String accountInfo() {
		return "accountinfoform";
	}
	
	@PostMapping("/accountInfo")
	public String accountInfo(@RequestParam("id") String id, Model model) {
		try {
			Account acc = accountService.accountinfo(id);
			model.addAttribute("acc", acc); 
			return "accountinfo";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "계좌조회 오류");
			return "err";
		}
	}
	
	@GetMapping("/deposit")
	public String deposit() {
		return "deposit";
	}
	
	@PostMapping("/deposit")
	public ModelAndView deposit(@RequestParam("id") String id, @RequestParam("money") Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			Account acc = accountService.deposit(id, money);
			mav.addObject("acc", acc);
			mav.setViewName("accountinfo");
		} catch (Exception e) {
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	@GetMapping("/withdraw")
	public String withdraw() {
		return "withdraw";
	}
	
	@PostMapping("/withdraw")
	public String withdraw(HttpServletRequest request, Model model) {
		try {
			String id = request.getParameter("id");
			Integer money = Integer.parseInt(request.getParameter("money"));
			Account acc = accountService.withdraw(id, money);
			model.addAttribute("acc", acc);
			return "accountinfo";
		} catch (Exception e) {
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}
	
	@GetMapping("/allAccountInfo")
	public ModelAndView allAccountinfo() {
		ModelAndView mav = new ModelAndView();
		try {
			List<Account> accs = accountService.allacountinfo();
			mav.addObject("accs", accs);
			mav.setViewName("allaccountinfo");
		} catch (Exception e) {
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	@GetMapping("/transfer")
	public String transfer() {
		return "transfer";
	}
	
	@PostMapping("/transfer")
	public ModelAndView transfer(@RequestParam("sid") String sid, @RequestParam("rid") String rid, @RequestParam("money") Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.transfer(sid, rid, money);
			Account acc = accountService.accountinfo(sid);
			mav.addObject("acc", acc);
			mav.setViewName("accountinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@PostMapping("/accountDoubleId")
	@ResponseBody
	public String accDoubleId(@RequestParam("id") String id) {
		try {
			boolean check = accountService.doubleId(id);
			return String.valueOf(check);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
