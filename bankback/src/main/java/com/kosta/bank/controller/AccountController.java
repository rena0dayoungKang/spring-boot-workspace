package com.kosta.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.serivce.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/checkAccId/{id}")
	public ResponseEntity<String> checkAccId(@PathVariable String id) {
		try {
			boolean check = accountService.doubleId(id);
			return new ResponseEntity<String>(String.valueOf(check), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/makeAccount")
	public ResponseEntity<String> makeAccount(@RequestBody AccountDto accountDto) {
		try {
			accountService.makeAccount(accountDto);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/deposit")
	public ResponseEntity<String> deposit(@RequestBody Map<String, String> param) {
		try {
			String id = (String) param.get("id");
			Integer money = Integer.parseInt(param.get("money"));
			AccountDto accDto = accountService.deposit(id, money);
			return new ResponseEntity<String>(accDto.getBalance() + "", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/withdraw")
	public ResponseEntity<String> withdraw(@RequestBody Map<String, String> param) {
		try {
			String id = (String) param.get("id");
			Integer money = Integer.parseInt(param.get("money"));
			AccountDto accDto = accountService.withdraw(id, money);
			return new ResponseEntity<String>(accDto.getBalance() + "", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/accountInfo/{id}")
	public ResponseEntity<AccountDto> accountInfo(@PathVariable String id) {
		try {
			AccountDto accDto = accountService.accountinfo(id);
			return new ResponseEntity<AccountDto>(accDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AccountDto>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/allAccountInfo")
	public ResponseEntity<List<AccountDto>> allAccountInfo() {
		try {
			List<AccountDto> accDtoList = accountService.allacountinfo();
			return new ResponseEntity<List<AccountDto>>(accDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody Map<String, String> param) {
		try {
			String sid = (String) param.get("sid");
			String rid = (String) param.get("rid");
			Long money = Long.parseLong(param.get("money"));
			Integer balance = accountService.transfer(sid, rid, money.intValue());
			return new ResponseEntity<String>(balance + "", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
