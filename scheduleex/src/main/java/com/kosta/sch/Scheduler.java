package com.kosta.sch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kosta.sch.repository.HeartRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j // log 출력
public class Scheduler {
	
	@Autowired
	private HeartRepository heartRepository;

//	@Scheduled(fixedDelay = 3000) //3초마다 실행
//	@Scheduled(initialDelay = 3000, fixedRate = 5000) //3초 대기 후에 5초마다 실행
//  @Scheduled(cron = "*/10 * * * * *") // 초(0 - 59) 분(0 - 59) 시간(0 - 24) 일(1 - 31) 월(1 - 12) 
										//요일(0:일, 1:월, 2:화, 3:수, 4:목, 5:금, 6:토)
	
	// (*/10 * * * * *) 10초마다
	// (0 0 15 * * *) 매일 오후 15시에 실행
	// (0 0 14 10,20 * ?) 매달 10일과 20일 14시에 실행
	// (0 0 22 L * ?) 매달 마지막날 22시에 실행
	// (0 0/5 9,18 * * *)  매일 9시 00분 - 9시 55분, 18시 00분 - 18시 55분 사이에 5분 간격으로 실행
	// (0 0/5 9-18 * * *)  매일 9시 00분 - 18시 55분 사이에 5분 간격으로 실행
	// (0 30 10 1 * *)  매달 1일 10시 30분에 실행
	// (0 30 10 ? 3 1-5)  매년 3월내 월-금 10시 30분에 실행
	
	//@Scheduled(cron = "0 0 0 * * *") //매일 24시에 실행
	//	public void run() {
	//		log.info("scheduler test");
	//	}
	
	
	//하트 지우기 스케쥴링
	@Scheduled(cron = "0 20 12 * * *")
	public void run() {
		heartRepository.deleteAll();
		log.info("Heart table clear");
	}
}
