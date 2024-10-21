package com.kosta.board.entitiy;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;

import com.kosta.board.dto.MemberDto;

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

	@Column(columnDefinition = "MEDIUMBLOB")
	@Lob
	private byte[] profileImage;
	
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Board> boardList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<BoardLike> boardLikeList = new ArrayList<>();
	//Member를 불러올 때마다 boardList, boardLikeList를 다 불러오기 때문에 속도, 성능저하가 된다. 
	//(실무에서는 이렇게 잘 안씀) 

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", address="
				+ address + ", nickname=" + nickname + "]";
	}
	
	public MemberDto toDto() {
		ModelMapper mapper = new ModelMapper();
		MemberDto memberDto = mapper.map(this, MemberDto.class);
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
