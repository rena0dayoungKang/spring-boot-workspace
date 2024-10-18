package com.kosta.board.dto;

import org.modelmapper.ModelMapper;

import com.kosta.board.entitiy.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
	private String id;
	private String name;
	private String password;
	private String email;
	private String address;
	private String nickname;
	private byte[] profileImage;
	
	public Member toEntity() {
		ModelMapper mapper = new ModelMapper();  //gradle추가함
		return mapper.map(this, Member.class);   //똑같은 항목들은 mapper를 통해 toEntity바로 해줄수있음(.build()안쓰고)
	}
	

}
