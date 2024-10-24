package com.kosta.board.entity;

import java.io.UnsupportedEncodingException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.tomcat.util.codec.binary.Base64;

import com.kosta.board.dto.MemberDto;

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
	private String nickname;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String address;
	@Column
	private byte[] profileImage;
	
	public MemberDto toDto() {
		MemberDto memberDto = MemberDto.builder()
									   .id(id)
									   .name(name)
									   .nickname(nickname)
									   .password(password)
									   .email(email)
									   .address(address)
									   .profileImage(profileImage)
									   .build();
		if (profileImage != null) {
			try {
				memberDto.setProfileImageStr(new String(Base64.encodeBase64(profileImage), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return memberDto;
	}
	
}
