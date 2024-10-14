package com.kosta.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //전체생성자
public class Account {
	private String id;
	private String name;
	private Integer balance;
	private String type;
	private String grade;
	
	public void deposit(Integer money) throws Exception {
		if(money<=0) throw new Exception("입금액 오류");
		balance += money;
	}
	
	public void withdraw(Integer money) throws Exception {
		if(balance<money) throw new Exception("입금액 오류");
			balance -= money;
	}
}
