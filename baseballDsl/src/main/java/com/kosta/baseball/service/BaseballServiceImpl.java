package com.kosta.baseball.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kosta.baseball.dto.PlayerDto;
import com.kosta.baseball.dto.TeamDto;
import com.kosta.baseball.entity.Player;
import com.kosta.baseball.entity.Team;
import com.kosta.baseball.repository.BaseballRepository;
import com.querydsl.core.Tuple;

@Service
public class BaseballServiceImpl implements BaseballService {
	
	@Autowired
	private BaseballRepository baseballRepository;
	
//	@Autowired
//	private ObjectMapper objectMapper;

	// 1. 팀등록
	@Override
	public void registTeam(TeamDto teamDto) throws Exception {
		baseballRepository.insertTeam(teamDto.toEntitiy());
	}

	// 2. 팀 이름으로 팀 조회
	@Override
	public TeamDto teamInfoByName(String name) throws Exception {
		return baseballRepository.selectTeamByName(name).toDto();
	}

	// 3. 팀 번호로 팀 조회
	@Override
	public TeamDto teamInfoByNum(Integer num) throws Exception {
		return baseballRepository.selectTeamByNum(num).toDto();
	}

	// 4. 지역으로 팀 조회
	@Override
	public List<TeamDto> teamInfoByLoc(String loc) throws Exception {
		return baseballRepository.selectTeamByLoc(loc)
								 .stream()
								 .map(i -> i.toDto())
								 .collect(Collectors.toList());
	}

	// 5. 선수 등록(팀 이름으로)
	@Override
	public void registPlayerWithTeamName(PlayerDto playerDto) throws Exception {
		Team team = baseballRepository.selectTeamByName(playerDto.getTeamName());
		if(team == null) throw new Exception("팀 이름 오류");
		playerDto.setTeamNum(team.getNum());
		baseballRepository.insertPlayer(playerDto.toEntity());
	}

	// 6. 선수 등록(팀 번호로)
	@Override
	public void registPlayerWithTeamNum(PlayerDto playerDto) throws Exception {
		baseballRepository.insertPlayer(playerDto.toEntity());
	}

	// 7. 선수 조회(선수 번호로, 팀이름포함)
	@Override
	public PlayerDto playerInfoByNum(Integer num) throws Exception {
		Tuple tuple = baseballRepository.selectPlayerWithTeamName(num);
		
		PlayerDto playerDto = tuple.get(0, Player.class).toDto();
		playerDto.setTeamName(tuple.get(1, String.class));
		playerDto.setTeamLoc(tuple.get(2, String.class));
		
		return playerDto;
	}

	// 8. 선수 조회(선수 이름으로, 팀이름포함)
	@Override
	public List<PlayerDto> playerInfoByName(String name) throws Exception {
		List<Tuple> tupleList = baseballRepository.selectPlayerByName(name);
		//System.out.println(tupleList);
		return tupleList.stream()
						.map(i -> {
							PlayerDto playerDto = i.get(0, Player.class).toDto();
							playerDto.setTeamName(i.get(1, String.class));
							playerDto.setTeamLoc(i.get(2, String.class));
							return playerDto;
						})
						.collect(Collectors.toList());
	}

	// 9. 특정팀 선수 목록 조회(팀번호로)
	@Override
	public List<PlayerDto> playerListInTeamByTeamNum(Integer teamNum) throws Exception {
		List<Tuple> playerList = baseballRepository.selectPlayerListByTeamNum(teamNum);
		return playerList.stream()
							.map(i -> {
								PlayerDto playerDto = i.get(0, Player.class).toDto();
								playerDto.setTeamName(i.get(1, String.class));
								playerDto.setTeamLoc(i.get(2, String.class));
								return playerDto;
							})
							.collect(Collectors.toList());
	}

	// 10. 특정팀 선수 목록 조회(팀이름으로)
	@Override
	public List<PlayerDto> playerListInTeamByTeamName(String teamName) throws Exception {
		List<Tuple> playerList = baseballRepository.selectPlayerListByTeamName(teamName);
		return playerList.stream()
						 .map(i -> {
							 PlayerDto playerDto = i.get(0, Player.class).toDto();
							playerDto.setTeamName(i.get(1, String.class));
							playerDto.setTeamLoc(i.get(2, String.class));
							return playerDto;
						 })
						 .collect(Collectors.toList());
		
	}

}
