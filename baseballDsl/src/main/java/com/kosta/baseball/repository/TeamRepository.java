package com.kosta.baseball.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.baseball.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
