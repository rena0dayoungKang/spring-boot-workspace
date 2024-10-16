package com.kosta.jpaex;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.jpaex.entity.Actor;
import com.kosta.jpaex.service.ActorService;

@SpringBootTest
class JpaExamApplicationTests {
	
	@Autowired
	private ActorService actorService;

	@Test
	void addActor() throws Exception {
		actorService.addActor(Actor.builder()
					.id("minji")
					.name("민지")
					.agency("어도어")
					.birthday(Date.valueOf("2004-05-07")).build());
	}
	
	@Test
	void findActor() throws Exception {
		Actor actor = actorService.actorInfo("hani");
		System.out.println("---->"+actor);
	}
	
	@Test
	void modifyActor() throws Exception {
		actorService.addActor(Actor.builder()
				.id("hani")
				.name("하니")
				.agency("어도어")
				.birthday(Date.valueOf("2004-10-06")).build());
	}
	
	@Test
	void removeActor() throws Exception {
		actorService.removeActor("hani");
	}
	
	@Test
	void findActorByName() throws Exception {
		Actor actor = actorService.actorInfoByName("민지");
		System.out.println("---->"+actor);
	}
	
	@Test
	void findActorByAgency() throws Exception {
		List<Actor> actors = actorService.actorInfoByAgency("어도어");
		System.out.println("---->"+actors);
	}

}
