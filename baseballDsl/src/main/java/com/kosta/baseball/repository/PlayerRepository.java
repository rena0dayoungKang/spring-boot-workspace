package com.kosta.baseball.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.baseball.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
