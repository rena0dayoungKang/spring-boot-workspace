package com.kosta.board.entitiy;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

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

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", address="
				+ address + ", nickname=" + nickname + "]";
	}
	
	public MemberDto toDto() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, MemberDto.class);
	}

	
//	@Id
//	private String id;  
//	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)  //Board 테이블의 writer와 연결 
//	private List<Board> boardList = new ArrayList<>();
//	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)  //Heart 테이블의 memId와 연결 
//	private List<Heart> heartList = new ArrayList<>();
//	
//	@Column
//	private String name;
//	@Column
//	private String password;
//	@Column
//	private String email;
//	@Column
//	private String address;
//	@Column
//	private String nickname;
//	
//	@Column(columnDefinition = "BLOB")
//	@Lob
//	private byte[] profile_image;

}
