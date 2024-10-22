package com.kosta.baseball.dto;

import com.kosta.baseball.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDto {
	private Integer num;
	private String name;
	private Integer backNum;
	private Integer teamNum;
	
	private String teamName;
	private String teamLoc;
	
	public Player toEntity() { //DTO : 데이터를 다시 Player 엔티티로 변환하는 역할
		return Player.builder().num(num).name(name)
						.backNum(backNum)
						.teamNum(teamNum)
						.build();
	}
}
