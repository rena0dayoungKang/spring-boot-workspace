package com.kosta.jpaex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.jpaex.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor, String> {
		
	Optional<Actor> findByName(String name);  //이름으로 find하기
	List<Actor> findByAgency(String agency);  //소속사명으로 find하기
}
