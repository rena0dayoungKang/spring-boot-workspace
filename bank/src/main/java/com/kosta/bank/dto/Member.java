package com.kosta.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private String id; 
	private String name;
	private String password;
	private String email;
	private String address;
	private String nickname;
	private String profile_image;
	
}
