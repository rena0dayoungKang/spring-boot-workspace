package com.kosta.baseball.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.baseball.entity.Player;
import com.kosta.baseball.entity.QPlayer;
import com.kosta.baseball.entity.QTeam;
import com.kosta.baseball.entity.Team;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BaseballRepository { // 레포지토리가 class 형태임

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	// 1. 팀등록
	public void insertTeam(Team team) throws Exception {
		teamRepository.save(team);
	}

	// 2. 팀 이름으로 팀 조회
	public Team selectTeamByName(String name) throws Exception {
		QTeam team = QTeam.team;
		return jpaQueryFactory.select(team).from(team).where(team.name.eq(name)).fetchOne();
	}

	// 3. 팀 번호로 팀 조회
	public Team selectTeamByNum(Integer num) throws Exception {
		QTeam team = QTeam.team;
		return jpaQueryFactory.select(team).from(team).where(team.num.eq(num)).fetchOne();
	}

	// 4. 지역으로 팀 조회
	public List<Team> selectTeamByLoc(String loc) throws Exception {
		QTeam team = QTeam.team;
		return jpaQueryFactory.select(team).from(team).where(team.loc.eq(loc)).fetch();
	}

	// 5. 선수 등록(팀 이름으로) , 6. 선수 등록(팀 번호로)
	public void insertPlayer(Player player) throws Exception {
		playerRepository.save(player);
	}

	// 7. 선수 조회(선수 번호로, 팀이름포함)
	public Tuple selectPlayerWithTeamName(Integer num) throws Exception {
		//딱 맞는 타입의 엔터티가 없을 때는 Tuple이라는 것을 사용
		QPlayer player = QPlayer.player;
		QTeam team = QTeam.team;
		return jpaQueryFactory.select(player, team.name, team.loc) //데이터0번째가 player객체, 1번째가 team name
					   .from(player)
					   .leftJoin(team)
					   .on(player.teamNum.eq(team.num))
					   .where(player.num.eq(num))
					   .fetchOne();
	}
	
	// 8. 선수 조회(선수 이름으로, 팀이름포함)
	public List<Tuple> selectPlayerByName(String name) throws Exception {
		QPlayer player = QPlayer.player;
		QTeam team = QTeam.team;
		return jpaQueryFactory.select(player, team.name, team.loc)
							  .from(player)
							  .leftJoin(team)
							  .on(player.teamNum.eq(team.num))
							  .where(player.name.eq(name))
							  .fetch();
	}
	
	// 9. 특정팀 선수 목록 조회(팀번호로)
	public List<Tuple> selectPlayerListByTeamNum(Integer teamNum) throws Exception {
		QPlayer player = QPlayer.player;
		QTeam team = QTeam.team;
		return jpaQueryFactory.select(player, team.name, team.loc)
							  .from(player)
							  .leftJoin(team)
							  .on(player.teamNum.eq(team.num))
							  .where(player.teamNum.eq(teamNum))
							  .fetch();
	}
	
	// 10. 특정팀 선수 목록 조회(팀이름으로)
	public List<Tuple> selectPlayerListByTeamName(String teamName) throws Exception {
		QPlayer player = QPlayer.player;
		QTeam team = QTeam.team;
		return jpaQueryFactory.select(player, team.name, team.loc)
							  .from(player)
							  .leftJoin(team)
							  .on(player.teamNum.eq(team.num))
							  .where(team.name.eq(teamName))
							  .fetch();
	}
}
