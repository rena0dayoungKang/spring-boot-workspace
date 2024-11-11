package com.kosta.sch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //스케쥴러 활성화를 위한 어노테이션
public class ScheduleexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleexApplication.class, args);
	}

}
