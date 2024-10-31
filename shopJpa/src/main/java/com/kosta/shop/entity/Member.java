package com.kosta.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.modelmapper.ModelMapper;

import com.kosta.shop.dto.MemberDto;

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
public class Member {
	
	@Id
	private String userid;	
	@OneToOne(mappedBy = "memId", fetch = FetchType.LAZY)
	private OrderInfo orderMemId;
	
	@Column
	private String username;
	@Column
	private String passwd;
	@Column
	private String post;
	@Column
	private String addr1;
	@Column
	private String addr2;
	@Column
	private String phone1;
	@Column
	private String phone2;
	@Column
	private String phone3;
	@Column
	private String email1;
	@Column
	private String email2;
	
	public MemberDto toDto() {
		ModelMapper mapper = new ModelMapper();
		MemberDto memberDto = mapper.map(this, MemberDto.class);
		return memberDto;
	}
	
}
