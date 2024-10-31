package com.kosta.bank.dto;

import com.kosta.bank.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private String profileImage;
	
//	@Autowired
//	private ModelMapper modelMapper; //엔티티클래스와 동일한 변수만 있을때 사용
	
	public Member toEntity() {
//		return (Member)modelMapper.map(this, Member.class);
		return Member.builder()
				.id(id)
				.name(name)
				.password(password)
				.email(email)
				.address(address)
				.nickname(nickname)
				.profileImage(profileImage)
				.build();
	}
}
