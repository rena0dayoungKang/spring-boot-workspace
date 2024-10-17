package com.kosta.baseball.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.baseball.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	Optional<Team> findByName(String name) throws Exception;
	List<Team> findByLoc(String loc) throws Exception;
}


