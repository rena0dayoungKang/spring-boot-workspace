package com.kosta.jpaex.service;

import java.util.List;

import com.kosta.jpaex.entity.Actor;

public interface ActorService {
	
	void addActor(Actor actor) throws Exception;
	void modifyActor(Actor actor) throws Exception;
	Actor actorInfo(String id) throws Exception;
	Actor actorInfoByName(String name) throws Exception;
	List<Actor> actorInfoByAgency(String agency) throws Exception;
	void removeActor(String id) throws Exception;
	

}
