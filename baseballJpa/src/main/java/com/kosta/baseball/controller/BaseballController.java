package com.kosta.baseball.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.baseball.dto.PlayerDto;
import com.kosta.baseball.dto.TeamDto;
import com.kosta.baseball.service.BaseballService;

import lombok.RequiredArgsConstructor;
//POSTMAN으로 데이터 확인하는 실습

@RestController //리턴을 view로 주지 않고 모두 Data형태로 return 된다. 즉, 전부 @ResponseBody형태의 메소드임
@RequiredArgsConstructor
public class BaseballController {
	private final BaseballService baseballService;
	
	// 1. 팀등록
	@PostMapping("/regTeam")
	public ResponseEntity<String> regTeam(@RequestBody TeamDto teamDto) {
		//ResponseEntity : 실제 데이터와 상태값을 같이 내려보내주는 객체, 자주 사용함
		//@RequestBody : json 형태의 데이터를 dto (Team)의 형태로 바꿔주는 역할 
		try {
			baseballService.registTeam(teamDto);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
		}	
	}
	
	// 2. 팀 이름으로 팀 조회
	@GetMapping("/teamByName")
	public ResponseEntity<TeamDto> teamInfoByName(@RequestParam("teamName") String teamName) {
		try {
			TeamDto teamDto = baseballService.teamInfoByName(teamName);
			return new ResponseEntity<TeamDto>(teamDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<TeamDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 3. 팀 번호로 팀 조회
	@GetMapping("/teamByNum")
	public ResponseEntity<TeamDto> teamInfoByNum(@RequestParam("teamNum") Integer teamNum) {
		try {
			TeamDto teamDto = baseballService.teamInfoByNum(teamNum);
			return new ResponseEntity<TeamDto>(teamDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<TeamDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 4. 지역으로 팀 조회 
	@GetMapping("/teamByLoc")
	public ResponseEntity<List<TeamDto>> teamInfoByLoc(@RequestParam("loc") String loc) {
		try {
			List<TeamDto> teamList = baseballService.teamInfoByLoc(loc);
			return new ResponseEntity<List<TeamDto>>(teamList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<TeamDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 5. 선수 등록(팀 이름으로) 
	@PostMapping("/regPlayerByTeamName")
	public ResponseEntity<String> regPlayerByTeamName(@RequestBody PlayerDto playerDto){
		//json 형식으로 post 보내야 등록이 된다 
		try {
			baseballService.registPlayerWithTeamName(playerDto);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 6. 선수 등록(팀 번호로)
	@PostMapping("/regPlayerByTeamNum")
	public ResponseEntity<String> regPlayerByTeamNum(@RequestBody PlayerDto playerDto) {
		try {
			baseballService.registPlayerWithTeamNum(playerDto);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 7. 선수 조회(선수 번호로, 팀이름포함)
	@GetMapping("/playerByNum")
	public ResponseEntity<PlayerDto> playerByNum(@RequestParam("num") Integer num){
		try {
			PlayerDto playerDto = baseballService.playerInfoByNum(num);
			return new ResponseEntity<PlayerDto>(playerDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<PlayerDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// 8. 선수 조회(선수 이름으로, 팀이름포함)
	@GetMapping("/playerByName")
	public ResponseEntity<List<PlayerDto>> playerByName(@RequestParam("name") String name) {
		try {
			List<PlayerDto> playerDto = baseballService.playerInfoByName(name);
			return new ResponseEntity<List<PlayerDto>>(playerDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<PlayerDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 9. 특정팀 선수 목록 조회(팀번호로)
	@GetMapping("/playerListByTeamNum")
	public ResponseEntity<List<PlayerDto>> playerListByTeamNum(@RequestParam("teamNum") Integer teamNum) {
		try {
			List<PlayerDto> playerDto = baseballService.playerListInTeamByTeamNum(teamNum);
			return new ResponseEntity<List<PlayerDto>>(playerDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PlayerDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 10. 특정팀 선수 목록 조회(팀이름으로)
	@GetMapping("/playerListByTeamName")
	public ResponseEntity<List<PlayerDto>> playerListByTeamName(@RequestParam("teamName") String teamName) {
		try {
			List<PlayerDto> playerDto = baseballService.playerListInTeamByTeamName(teamName);
			return new ResponseEntity<List<PlayerDto>>(playerDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PlayerDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
