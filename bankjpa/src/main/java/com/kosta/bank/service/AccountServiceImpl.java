package com.kosta.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.bank.entity.Account;
import com.kosta.bank.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public Account makeAccount(Account acc) throws Exception {
		return accountRepository.save(acc);
	}

	@Override
	public Account deposit(String id, Integer money) throws Exception {
		Account acc = accountRepository.findById(id).orElseThrow(() -> new Exception("계좌번호 오류"));
		acc.deposit(money);
		accountRepository.save(acc);
		return acc;
	}

	@Override
	public Account withdraw(String id, Integer money) throws Exception {
		Account acc = accountRepository.findById(id).orElseThrow(() -> new Exception("계좌번호 오류"));
		acc.withdraw(money);
		accountRepository.save(acc);
		return acc;
	}

	@Override
	public Account accountinfo(String id) throws Exception {
		return accountRepository.findById(id).orElseThrow(() -> new Exception("계좌번호 오류"));
	}

	@Override
	public List<Account> allacountinfo() throws Exception {
		return accountRepository.findAll();
	}

	@Override
	public void transfer(String sid, String rid, Integer money) throws Exception {
		Account sacc = accountRepository.findById(sid).orElseThrow(() -> new Exception("보내는 계좌번호 오류"));
		Account racc = accountRepository.findById(rid).orElseThrow(() -> new Exception("받는 계좌번호 오류"));
		sacc.withdraw(money);
		racc.deposit(money);
		accountRepository.save(sacc);
		accountRepository.save(racc);
	}

	@Override
	public boolean doubleId(String id) throws Exception {
		Optional<Account> oacc = accountRepository.findById(id);
		if(oacc.isPresent()) return true;
		return false;
	}

}
