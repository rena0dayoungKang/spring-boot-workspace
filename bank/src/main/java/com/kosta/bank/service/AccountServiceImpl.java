package com.kosta.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.bank.dao.AccountDao;
import com.kosta.bank.dto.Account;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	private final AccountDao accountdao;  //상수형태는 초기화를 생성자에서 해준다
	//@Autowired
	//private AccountDao accountdao;

	//@RequiredArgsConstructor 로 대신 	
	//public AccountServiceImpl(AccountDao accountDao) {
	//	this.accountdao = accountDao;
	//}
	
	@Override
	public Account makeAccount(Account acc) throws Exception {
		Account sacc = accountdao.selectAccount(acc.getId());
		if(sacc!=null) throw new Exception("계좌번호 중복");
		accountdao.insertAccount(acc);
		return acc;
	}

	@Override
	public Account deposit(String id, Integer money) throws Exception {
		Account sacc = accountdao.selectAccount(id);
		if(sacc==null) throw new Exception("계좌번호 오류");
		sacc.deposit(money); 
		accountdao.updateBalance(sacc);
		return sacc;
		
	}

	@Override
	public Account withdraw(String id, Integer money) throws Exception {
		Account sacc = accountdao.selectAccount(id);
		if(sacc==null) throw new Exception("계좌번호 오류");
		sacc.withdraw(money);
		accountdao.updateBalance(sacc); 
		return sacc;
	}
	@Override
	public Account accountinfo(String id) throws Exception {
		Account sacc = accountdao.selectAccount(id);
		if(sacc==null) throw new Exception("계좌번호 오류");
		return sacc;
	}
	@Override
	public List<Account> allacountinfo() throws Exception {
		List<Account> accs= accountdao.selectAllAccount();
		if(accs == null) throw new Exception("조회 내역이 없습니다.");
		return accs;
	}
	@Override
	public void transfer(String sid, String rid, Integer money) throws Exception {
		Account sacc = accountdao.selectAccount(sid);
		if(sacc==null) throw new Exception("보내는 계좌번호 오류");
		Account racc = accountdao.selectAccount(rid);
		if(racc==null) throw new Exception("받는 계좌번호 오류");
		sacc.withdraw(money);
		racc.deposit(money);
		accountdao.updateBalance(racc);
		accountdao.updateBalance(sacc);
		
	}

	@Override
	public boolean doubleId(String id) throws Exception {
		Account saccount = accountdao.selectAccount(id); 
		if(saccount == null) return false; 
		
		return true;
	}
}
