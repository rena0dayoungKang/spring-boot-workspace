package com.kosta.baseball.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.baseball.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	List<Player> findByName(String name);
	Optional<Player> findByTeam_Num(Integer teamNum);
}