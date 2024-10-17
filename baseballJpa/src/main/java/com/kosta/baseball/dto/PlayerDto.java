package com.kosta.baseball.dto;

import com.kosta.baseball.entity.Player;
import com.kosta.baseball.entity.Team;

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
	
	public Player toEntity() { //DTO 데이터를 다시 Player 엔티티로 변환하는 역할
		return Player.builder().num(num).name(name).backNum(backNum)
					  .team(Team.builder().num(teamNum).name(teamName).loc(teamLoc).build())
					   .build();
	}
	
	//DTO 클래스는 클라이언트와 서버 간의 데이터 전송을 위해 사용. 
	//필요한 데이터만 선택적으로 전송할 수 있다.
}
