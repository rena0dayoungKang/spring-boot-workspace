package com.kosta.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.bank.dto.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account {
	@Id
	private String id;
	@Column
	private String name;
	@Column
	private Integer balance;
	@Column
	private String type;
	@Column
	private String grade;
	
//	@Transient	//컬럼에서 제외한다는 의미
//	@Autowired
//	private ModelMapper modelMapper;
	
	public void deposit(Integer money) throws Exception {
		if(money < 0) throw new Exception("입금 오류");
		balance += money;
	}
	
	public void withdraw(Integer money) throws Exception {
		if(balance < money) throw new Exception("잔액 부족");
		balance -= money;
	}
	
	public AccountDto toDto() {
		return AccountDto.builder()
						 .id(id)
						 .name(name)
						 .balance(balance)
						 .type(type)
						 .grade(grade)
						 .build();
//		return (AccountDto)modelMapper.map(this, AccountDto.class);
	}
	
}
