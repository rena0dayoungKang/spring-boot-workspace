package com.kosta.baseball.dto;

import lombok.NoArgsConstructor;

import com.kosta.baseball.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDto {
	private Integer num;
	private String name;
	private String loc;
	
	public Team toEntitiy() {
		return Team.builder().num(num).name(name).loc(loc).build();
	}
	
	
}
