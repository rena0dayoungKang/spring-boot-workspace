package com.kosta.baseball.service;

import java.util.List;

import com.kosta.baseball.dto.PlayerDto;
import com.kosta.baseball.dto.TeamDto;
import com.kosta.baseball.entity.Player;
import com.kosta.baseball.entity.Team;


public interface BaseballService {
	void registTeam(TeamDto teamDto) throws Exception; //1
	TeamDto teamInfoByName(String name) throws Exception; //2
	TeamDto teamInfoByNum(Integer num) throws Exception; //3
	List<TeamDto> teamInfoByLoc(String loc) throws Exception; //4
	
	void registPlayerWithTeamName(PlayerDto playerDto) throws Exception; //5. 선수 등록 (팀 이름으로)
	void registPlayerWithTeamNum(PlayerDto playerDto) throws Exception; //6. 선수 등록 (팀 번호로)
	
	PlayerDto playerInfoByNum(Integer num) throws Exception; //7
	//PlayerDto playerInfoByBackNum(Integer backNum) throws Exception; //7-A
	List<PlayerDto> playerInfoByName(String name) throws Exception; //8
	List<PlayerDto> playerListInTeamByTeamNum(Integer teamNum) throws Exception; //9
	List<PlayerDto> playerListInTeamByTeamName(String teamName) throws Exception; //10
	
	
}
