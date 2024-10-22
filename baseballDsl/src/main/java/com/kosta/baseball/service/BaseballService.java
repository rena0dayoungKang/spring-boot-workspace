package com.kosta.baseball.service;

import java.util.List;

import com.kosta.baseball.dto.PlayerDto;
import com.kosta.baseball.dto.TeamDto;


public interface BaseballService {
	
	// 1. 팀등록
	void registTeam(TeamDto teamDto) throws Exception;
	// 2. 팀 이름으로 팀 조회
	TeamDto teamInfoByName(String name) throws Exception;
	// 3. 팀 번호로 팀 조회
	TeamDto teamInfoByNum(Integer num) throws Exception;
	// 4. 지역으로 팀 조회
	List<TeamDto> teamInfoByLoc(String loc) throws Exception;
	
	// 5. 선수 등록(팀 이름으로)
	void registPlayerWithTeamName(PlayerDto playerDto) throws Exception; 
	// 6. 선수 등록(팀 번호로)
	void registPlayerWithTeamNum(PlayerDto playerDto) throws Exception;
	
	// 7. 선수 조회(선수 번호로, 팀이름포함)
	PlayerDto playerInfoByNum(Integer num) throws Exception;
	//PlayerDto playerInfoByBackNum(Integer backNum) throws Exception; //7-A
	// 8. 선수 조회(선수 이름으로, 팀이름포함)
	List<PlayerDto> playerInfoByName(String name) throws Exception;
	// 9. 특정팀 선수 목록 조회(팀번호로)
	List<PlayerDto> playerListInTeamByTeamNum(Integer teamNum) throws Exception;
	// 10. 특정팀 선수 목록 조회(팀이름으로)
	List<PlayerDto> playerListInTeamByTeamName(String teamName) throws Exception;
	
	
}
