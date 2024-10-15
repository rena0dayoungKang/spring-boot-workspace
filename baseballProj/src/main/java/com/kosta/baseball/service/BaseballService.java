package com.kosta.baseball.service;

import java.util.List;

import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;

public interface BaseballService {
	void registTeam(Team team) throws Exception; //1
	Team teamInfoByName(String name) throws Exception; //2
	Team teamInfoByNum(Integer num) throws Exception; //3
	List<Team> teamInfoByLoc(String loc) throws Exception; //4
	
	void registPlayerWithTeamName(Player player) throws Exception; //5. 선수 등록 (팀 이름으로)
	void registPlayerWithTeamNum(Player player) throws Exception; //6. 선수 등록 (팀 번호로)
	
	Player playerInfoByNum(Integer num) throws Exception; //7
	Player playerInfoByBackNum(Integer backNum) throws Exception; //7-A
	List<Player> playerInfoByName(String name) throws Exception; //8
	List<Player> playerListInTeamByTeamNum(Integer teamNum) throws Exception; //9
	List<Player> playerListInTeamByTeamName(String teamName) throws Exception; //10
	
	
}
