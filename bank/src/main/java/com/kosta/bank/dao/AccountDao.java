package com.kosta.bank.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kosta.bank.dto.Account;

@Mapper		//AccountDaoImpl 안만들어도 됨
@Repository //dao형태의 객체를 만들어줌 new 와 같은 의미 
public interface AccountDao {
	//스프링부트에서는 xml에있는 쿼리 이름, 변수명  ex) #{id}  와 똑같이 만들거나 @Param이용 
	void insertAccount(Account account) throws Exception;
	Account selectAccount(@Param("id") String id) throws Exception;
	void updateBalance(Account account) throws Exception;
	List<Account> selectAllAccount() throws Exception;
}
