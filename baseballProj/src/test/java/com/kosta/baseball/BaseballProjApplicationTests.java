package com.kosta.baseball;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.baseball.dao.BaseballDao;
import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;
import com.kosta.baseball.service.BaseballService;

@SpringBootTest
class BaseballProjApplicationTests {
	
	@Autowired
	private BaseballDao baseballDao;
	
	@Autowired
	private BaseballService baseballService;

	//1
	@Test
	void insertTeam() throws Exception {
		Team team = Team.builder().name("KIA타이거즈").loc("광주").build();
		baseballDao.insertTeam(team);
		//테스트 하면 데이터베이스에서도 insert 됨
	}
	
	//1-1
	@Test
	void insertTeam1() throws Exception {
		Team team = Team.builder().name("NC다이노스").loc("창원").build();
		baseballService.registTeam(team);
	}
	
	//2
	@Test
	void selectTeamByName() throws Exception {
		Team team = baseballDao.selectTeamByName("KT위즈");
		System.out.println(team);
	}
	
	//2-1
	@Test
	void teamInfoByName() throws Exception {
		Team team = baseballService.teamInfoByName("KT위즈");
		System.out.println(team);
	}
	
	//3
	@Test
	void selectTeamByNum() throws Exception {
		Team team = baseballDao.selectTeamByNum(1);
		System.out.println(team);
	}
	
	//3-1
	@Test
	void teamInfoByNum() throws Exception {
		Team team = baseballService.teamInfoByNum(5);
		System.out.println(team);
	}
	
	//4
	@Test
	void selectTeamByLoc() throws Exception {
		List<Team> team = baseballDao.selectTeamByLoc("수원");
		System.out.println(team);
	}
	
	//4-1
	@Test
	void teamInfoByLoc() throws Exception {
		List<Team> team = baseballService.teamInfoByLoc("수원");
		System.out.println(team);
	}
	
	//
	@Test
	void insertPlayer() throws Exception {
		Player player = Player.builder().name("양현종").backNum(0).teamNum(1).build();
		baseballDao.insertPlayer(player);
		System.out.println(player);
	}
	
	//5
	@Test
	void registPlayerWithTeamName() throws Exception {
		Player player = Player.builder().name("김주원").backNum(7).teamName("NC다이노스").build();
		baseballService.registPlayerWithTeamName(player);
	}
	
	//6
	@Test
	void registPlayerWithTeamNum() throws Exception {
		Player player = Player.builder().name("양현종").backNum(54).teamNum(5).build();
		baseballService.registPlayerWithTeamNum(player);
	}
	
	//7
	@Test
	void selectPlayerByNum() throws Exception {
		Player player = baseballDao.selectPlayerByNum(4);
		System.out.println(player);
	}
	
	//7-1
	@Test
	void playerInfoByNum() throws Exception {
		Player player = baseballService.playerInfoByNum(1);
		System.out.println(player);
	}
	
	//7-A
	@Test
	void selectPlayerByBackNum() throws Exception {
		Player player = baseballDao.selectPlayerByBackNum(0);
		System.out.println(player);
	}
	
	//7-A-1
	@Test
	void playerInfoByBackNum() throws Exception{
		Player player = baseballService.playerInfoByBackNum(50);
		System.out.println(player);
	}
	
	//8
	@Test
	void selectPlayerByName() throws Exception {
		List<Player> player = baseballDao.selectPlayerByName("양현종");
		System.out.println(player);
	}
	
	//8-1
	@Test
	void playerInfoByName() throws Exception {
		List<Player> player = baseballService.playerInfoByName("김강민");
		System.out.println(player);
	}
	
	//9
	@Test
	void selectPlayerListByTeamNum() throws Exception {
		List<Player> player = baseballDao.selectPlayerListByTeamNum(1);
		System.out.println(player);
	}
	
	//9-1
	@Test
	void playerListInTeamByTeamNum() throws Exception {
		List<Player> player = baseballService.playerListInTeamByTeamNum(3);
		System.out.println(player);
	}
	
	//10
	@Test
	void selectPlayerListByTeamName() throws Exception {
		List<Player> player = baseballDao.selectPlayerListByTeamName("KT위즈");
		System.out.println(player);
	}
	
	//10-1
	@Test
	void playerListInTeamByTeamName() throws Exception {
		List<Player> player = baseballService.playerListInTeamByTeamName("KT위즈");
		System.out.println(player);
	}
	
}
