package com.kosta.bank.serivce;

import java.util.List;

import com.kosta.bank.dto.AccountDto;

public interface AccountService {
	AccountDto makeAccount(AccountDto accountDto) throws Exception;
	AccountDto deposit(String id, Integer money) throws Exception;
	AccountDto withdraw(String id,Integer money) throws Exception;
	AccountDto accountinfo(String id) throws Exception;
	List<AccountDto> allacountinfo() throws Exception;
	Integer transfer(String sid,String rid,Integer money) throws Exception;
	boolean doubleId(String id) throws Exception;
}
