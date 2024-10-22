package com.kosta.baseball.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.kosta.baseball.dto.TeamDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Team {
	@Id
	private Integer num;
	@Column
	private String name;
	@Column
	private String loc;
	
//	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY) //LAZY:지연로딩
//	private List<Player> playerList = new ArrayList<>();
	//동적쿼리 dsl에서는 이렇게 일대다관계 안해줘도 됨
	
	public TeamDto toDto() {
		return TeamDto.builder().num(num).name(name).loc(loc).build();
	}
}
