package com.kosta.baseball.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kosta.baseball.dto.TeamDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
//import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data		//일대다 관계중 일의 관계에 있는 테이블에서는 @Data 어노테이션을 못쓴다, 
			//즉 @ToString못씀
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Team {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	@Column
	private String name;
	@Column
	private String loc;
	
	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY) //LAZY:지연로딩
	private List<Player> playerList = new ArrayList<>();
	
	public TeamDto toDto() {
		return TeamDto.builder().num(num).name(name).loc(loc).build();
	}
}
