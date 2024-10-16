package com.kosta.jpaex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kosta.jpaex.service.ActorService;

@SpringBootApplication
public class JpaExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaExamApplication.class, args);
	}

}
