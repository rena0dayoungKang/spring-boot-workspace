package com.kosta.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {
	@Id
	private String id; 
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String address;
	@Column
	private String nickname;
	@Column
	private String profileImage;

}
