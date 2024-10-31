package com.kosta.shop.dto;

import org.modelmapper.ModelMapper;

import com.kosta.shop.entity.Member;

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
public class MemberDto {

	private String userid;	
	private String username;
	private String passwd;
	private String post;
	private String addr1;
	private String addr2;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email1;
	private String email2;
	
	public Member toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, Member.class);
	}
}
