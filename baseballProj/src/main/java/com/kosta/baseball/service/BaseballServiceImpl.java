package com.kosta.baseball.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.baseball.dao.BaseballDao;
import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseballServiceImpl implements BaseballService {
	private final BaseballDao baseballDao;

	//1.
	@Override
	public void registTeam(Team team) throws Exception {
		baseballDao.insertTeam(team);		
	}

	//2.
	@Override
	public Team teamInfoByName(String name) throws Exception {
		return baseballDao.selectTeamByName(name);
	}

	//3
	@Override
	public Team teamInfoByNum(Integer num) throws Exception {
		return baseballDao.selectTeamByNum(num);
	}

	//4
	@Override
	public List<Team> teamInfoByLoc(String loc) throws Exception {
		return baseballDao.selectTeamByLoc(loc);
	}
	
	//5. 선수등록 (팀 이름으로)
	@Override
	public void registPlayerWithTeamName(Player player) throws Exception {
		Team team = baseballDao.selectTeamByName(player.getTeamName());
		player.setTeamNum(team.getNum());
		baseballDao.insertPlayer(player);
	}

	//6. 선수 등록(팀 번호로)
	@Override
	public void registPlayerWithTeamNum(Player player) throws Exception {
		Team team = baseballDao.selectTeamByNum(player.getTeamNum());
		player.setTeamNum(team.getNum());
		baseballDao.insertPlayer(player);
	}

	//7
	@Override
	public Player playerInfoByNum(Integer num) throws Exception {
		return baseballDao.selectPlayerByNum(num);
	}
	
	//7-A
	@Override
	public Player playerInfoByBackNum(Integer backNum) throws Exception {
		return baseballDao.selectPlayerByBackNum(backNum);
	}

	//8
	@Override
	public List<Player> playerInfoByName(String name) throws Exception {
		return baseballDao.selectPlayerByName(name);
	}

	//9
	@Override
	public List<Player> playerListInTeamByTeamNum(Integer teamNum) throws Exception {
		return baseballDao.selectPlayerListByTeamNum(teamNum);
	}

	//10
	@Override
	public List<Player> playerListInTeamByTeamName(String teamName) throws Exception {
		return baseballDao.selectPlayerListByTeamName(teamName);
	}	

}
