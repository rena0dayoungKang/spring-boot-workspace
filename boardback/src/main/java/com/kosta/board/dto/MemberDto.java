package com.kosta.board.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;

import com.kosta.board.entity.Member;

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
	private String id;
	private String name;
	private String nickname;
	private String password;
	private String email;
	private String address;
	private byte[] profileImage;
	private String profileImageStr; 
	private String roles;
	private Timestamp createDate;
	private String provider;
	private String providerId;
	
	public Member toEntity() {
		return Member.builder()
					 .id(id)
					 .name(name)
					 .nickname(nickname)
					 .password(password)
					 .email(email)
					 .address(address)
					 .profileImage(profileImage)
					 .roles(roles)
					 .build();
	}
}
