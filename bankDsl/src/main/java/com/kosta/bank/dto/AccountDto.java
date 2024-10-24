package com.kosta.bank.dto;

import com.kosta.bank.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	private String id;
	private String name;
	private Integer balance;
	private String type;
	private String grade;
	
//	@Autowired
//	private ModelMapper modelMapper;
		
	public Account toEntity() {
		return Account.builder()
				 	  .id(id)
				 	  .name(name)
				 	  .balance(balance)
				 	  .type(type)
				 	  .grade(grade)
				 	  .build();
//		return (Account)modelMapper.map(this, Account.class);
	}
}
