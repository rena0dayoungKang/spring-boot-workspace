package com.kosta.baseball.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration //여러가지 공통 Bean 객체를 설정할 수 있는 어노테이션임
public class QuerydslConfig { //query dsl 설정과 관련된 객체들을 공통으로 설정할 클래스
	
	@Autowired
	EntityManager entityManager; //jpa를사용하면 자동으로 entityManager사용가능
    
	@Bean	
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
		//자동생성된 객체를 Bean이라고 하는데 
		//원래 스프링 레거시에서는 servlet-context에 하나씩 넣어주던 것을 
		//이제는 config 클래스에서 @Bean으로 사용하는 것 
	}
		
//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
