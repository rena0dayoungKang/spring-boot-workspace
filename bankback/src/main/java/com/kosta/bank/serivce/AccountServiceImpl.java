package com.kosta.bank.serivce;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.entity.Account;
import com.kosta.bank.repository.BankRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BankRepository bankRepository;

	@Override
	public AccountDto makeAccount(AccountDto accountDto) throws Exception {
		Account acc = bankRepository.findAccountById(accountDto.getId());
		if(acc != null) throw new Exception("중복 계좌 오류");
		bankRepository.insertAccount(accountDto.toEntity());;
		return accountDto;
	}

	@Override
	public AccountDto deposit(String id, Integer money) throws Exception {
		Account acc = bankRepository.findAccountById(id);
		if(acc == null) throw new Exception("계좌번호 오류");
		acc.deposit(money);		
		bankRepository.updateBalance(id, acc.getBalance());
		return acc.toDto();
	}

	@Override
	public AccountDto withdraw(String id, Integer money) throws Exception {
		Account acc = bankRepository.findAccountById(id);
		if(acc == null) throw new Exception("계좌번호 오류");
		acc.withdraw(money);		
		bankRepository.updateBalance(id, acc.getBalance());
		return acc.toDto();
	}

	@Override
	public AccountDto accountinfo(String id) throws Exception {
		return bankRepository.findAccountById(id).toDto();
	}

	@Override
	public List<AccountDto> allacountinfo() throws Exception {
		return bankRepository.findAllAccount().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public void transfer(String sid, String rid, Integer money) throws Exception {
		Account sacc = bankRepository.findAccountById(sid);
		if (sacc == null) throw new Exception("보내는");
		sacc.withdraw(money);;
		Account racc = bankRepository.findAccountById(rid);
		if (racc == null) throw new Exception("받는");
		racc.deposit(money);
		bankRepository.transfer(sid, rid, sacc.getBalance(), racc.getBalance());
		
	}

	@Override
	public boolean doubleId(String id) throws Exception {
		return bankRepository.findAccountById(id) != null;
	}

	

}
