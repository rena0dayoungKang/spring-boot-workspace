package com.kosta.baseball.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.baseball.service.BaseballService;

import lombok.RequiredArgsConstructor;

@RestController //리턴을 view로 주지 않고 모두 Data형태로 return 된다. 즉, 전부 @ResponseBody형태의 메소드임
@RequiredArgsConstructor
public class BaseballController {
	private final BaseballService baseballService;
	
	// 1. 팀등록
	
	// 2. 팀 이름으로 팀 조회
	
	// 3. 팀 번호로 팀 조회
	
	// 4. 지역으로 팀 조회 
	
	// 5. 선수 등록(팀 이름으로) 
	
	// 6. 선수 등록(팀 번호로)
	
	// 7. 선수 조회(선수 등번호로, 팀이름포함)
	
	// 8. 선수 조회(선수 이름으로, 팀이름포함)
	
	// 9. 특정팀 선수 목록 조회(팀번호로)
	
	// 10. 특정팀 선수 목록 조회(팀이름으로)

}
