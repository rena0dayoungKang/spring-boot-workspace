package com.kosta.jpaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.jpaex.entity.Actor;
import com.kosta.jpaex.repository.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {
	
	@Autowired
	private ActorRepository actorRepository;

	@Override
	public void addActor(Actor actor) throws Exception {
		actorRepository.save(actor); //jpaRepo에서 save라는 interface를 가지고 있음
		
	}

	@Override
	public Actor actorInfo(String id) throws Exception {
		return actorRepository.findById(id).orElseThrow(()->new Exception("아이디 오류"));
	}

	@Override
	public void modifyActor(Actor actor) throws Exception {
		actorRepository.findById(actor.getId()).orElseThrow(()->new Exception("아이디 오류"));
		actorRepository.save(actor);
	}

	@Override
	public void removeActor(String id) throws Exception {
		actorRepository.deleteById(id); // ~ById는 프라이머리키를 찾는다
		
	}

	@Override
	public Actor actorInfoByName(String name) throws Exception {
		return actorRepository.findByName(name).orElseThrow(() -> new Exception("데이터 없음")); 
		//내가 actorRepo에서 만든게 나옴
	}

	@Override
	public List<Actor> actorInfoByAgency(String agency) throws Exception {
		return actorRepository.findByAgency(agency);
	}
	
	

}
