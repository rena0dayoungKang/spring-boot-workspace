package com.kosta.baseball.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;

@Mapper
@Repository
public interface BaseballDao {
	void insertTeam(Team team) throws Exception;
	Team selectTeamByNum(@Param("num") Integer num) throws Exception;
	Team selectTeamByName(@Param("name") String name) throws Exception;
	List<Team> selectTeamByLoc(@Param("loc") String loc) throws Exception;
	
	void insertPlayer(Player player) throws Exception;
	Player selectPlayerByNum(@Param("num") Integer num) throws Exception;
	Player selectPlayerByBackNum(@Param("backNum") Integer backNum) throws Exception;
	
	List<Player> selectPlayerByName(@Param("name") String name) throws Exception;
	List<Player> selectPlayerListByTeamNum(@Param("teamNum") Integer teamNum) throws Exception;
	List<Player> selectPlayerListByTeamName(@Param("teamName") String teamName) throws Exception;
	
}
