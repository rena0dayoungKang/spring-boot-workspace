package com.kosta.baseball.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kosta.baseball.dto.PlayerDto;
import com.kosta.baseball.dto.TeamDto;
import com.kosta.baseball.entity.Player;
import com.kosta.baseball.entity.Team;
import com.kosta.baseball.repository.PlayerRepository;
import com.kosta.baseball.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseballServiceImpl implements BaseballService {
	
	private final TeamRepository teamRepository;
	private final PlayerRepository playerRepository;

	@Override
	public void registTeam(TeamDto teamDto) throws Exception {
		teamRepository.save(teamDto.toEntitiy());
	}

	@Override
	public TeamDto teamInfoByName(String name) throws Exception {
		Team team = teamRepository.findByName(name).orElseThrow(() -> new Exception("팀 오류"));
		return team.toDto();
	}

	@Override
	public TeamDto teamInfoByNum(Integer num) throws Exception {
		Team team = teamRepository.findById(num).orElseThrow(() -> new Exception("팀 오류"));
		return team.toDto();
	}

	@Override
	public List<TeamDto> teamInfoByLoc(String loc) throws Exception {
		return teamRepository.findByLoc(loc)
				.stream()
				.map((t) -> t.toDto())
				.collect(Collectors.toList());
				//팀 리스트를 팀DTO 리스트로 바꾼것
	}
	
	@Override
	public void registPlayerWithTeamName(PlayerDto playerDto) throws Exception {
		Team team = teamRepository.findByName(playerDto.getTeamName()).orElseThrow(() -> new Exception("팀 오류"));
		playerDto.setTeamNum(team.getNum());
		playerRepository.save(playerDto.toEntity());
	}

	@Override
	public void registPlayerWithTeamNum(PlayerDto playerDto) throws Exception {
		Team team = teamRepository.findById(playerDto.getTeamNum()).orElseThrow(() -> new Exception("팀 오류"));
		playerDto.setTeamNum(team.getNum());
		playerRepository.save(playerDto.toEntity());
	}

	@Override
	public PlayerDto playerInfoByNum(Integer num) throws Exception {
		Player player =  playerRepository.findById(num).orElseThrow(() -> new Exception("선수 번호 오류"));
		return player.toDto();
	}

//	@Override
//	public PlayerDto playerInfoByBackNum(Integer backNum) throws Exception {
//		Player player =  playerRepository.
//		return null;
//	}

	@Override
	public List<PlayerDto> playerInfoByName(String name) throws Exception {
		return  playerRepository.findByName(name)
											.stream()
											.map(p -> p.toDto())
											.collect(Collectors.toList());
	}

	@Override
	public List<PlayerDto> playerListInTeamByTeamNum(Integer teamNum) throws Exception {
		Team team = teamRepository.findById(teamNum).orElseThrow(() -> new Exception("팀 오류"));
		return team.getPlayerList().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<PlayerDto> playerListInTeamByTeamName(String teamName) throws Exception {
		Team team = teamRepository.findByName(teamName).orElseThrow(() -> new Exception("팀 오류"));
		return team.getPlayerList().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

}
