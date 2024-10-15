package com.kosta.baseball.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;
import com.kosta.baseball.service.BaseballService;

import lombok.RequiredArgsConstructor;
//POSTMAN으로 데이터 확인하는 실습

@RestController //리턴을 view로 주지 않고 모두 Data형태로 return 된다. 즉, 전부 @ResponseBody형태의 메소드임
@RequiredArgsConstructor
public class BaseballController {
	private final BaseballService baseballService;
	
	// 1. 팀등록
	@PostMapping("/regTeam")
	public ResponseEntity<String> regTeam(@RequestBody Team team) {
		//ResponseEntity : 실제 데이터와 상태값을 같이 내려보내주는 객체, 자주 사용함
		//@RequestBody : json 형태의 데이터를 dto (Team)의 형태로 바꿔주는 역할 
		try {
			baseballService.registTeam(team);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
		}	
	}
	
	// 2. 팀 이름으로 팀 조회
	@GetMapping("/teamByName")
	public ResponseEntity<Team> teamInfoByName(@RequestParam("teamName") String teamName) {
		try {
			Team team = baseballService.teamInfoByName(teamName);
			return new ResponseEntity<Team>(team, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Team>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 3. 팀 번호로 팀 조회
	@GetMapping("/teamByNum")
	public ResponseEntity<Team> teamInfoByNum(@RequestParam("teamNum") Integer teamNum) {
		try {
			Team team = baseballService.teamInfoByNum(teamNum);
			return new ResponseEntity<Team>(team, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Team>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 4. 지역으로 팀 조회 
	@GetMapping("/teamByLoc")
	public ResponseEntity<List<Team>> teamInfoByLoc(@RequestParam("loc") String loc) {
		try {
			List<Team> teamList = baseballService.teamInfoByLoc(loc);
			return new ResponseEntity<List<Team>>(teamList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Team>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 5. 선수 등록(팀 이름으로) 
	@PostMapping("/regPlayerByTeamName")
	public ResponseEntity<String> regPlayerByTeamName(@RequestBody Player player) {
		//json 형식으로 post 보내야 등록이 된다 
		try {
			baseballService.registPlayerWithTeamName(player);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 6. 선수 등록(팀 번호로)
	@PostMapping("/regPlayerByTeamNum")
	public ResponseEntity<String> regPlayerByTeamNum(@RequestBody Player player) {
		try {
			baseballService.registPlayerWithTeamNum(player);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 7. 선수 조회(선수 번호로, 팀이름포함)
	@GetMapping("/playerByNum")
	public ResponseEntity<Player> playerByNum(@RequestParam("num") Integer num){
		try {
			Player player = baseballService.playerInfoByNum(num);
			return new ResponseEntity<Player>(player, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// 8. 선수 조회(선수 이름으로, 팀이름포함)
	@GetMapping("/playerByName")
	public ResponseEntity<List<Player>> playerByName(@RequestParam("name") String name) {
		try {
			List<Player> player = baseballService.playerInfoByName(name);
			return new ResponseEntity<List<Player>>(player, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Player>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 9. 특정팀 선수 목록 조회(팀번호로)
	@GetMapping("/playerListByTeamNum")
	public ResponseEntity<List<Player>> playerListByTeamNum(@RequestParam("teamNum") Integer teamNum) {
		try {
			List<Player> player = baseballService.playerListInTeamByTeamNum(teamNum);
			return new ResponseEntity<List<Player>>(player, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Player>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 10. 특정팀 선수 목록 조회(팀이름으로)
	@GetMapping("/playerListByTeamName")
	public ResponseEntity<List<Player>> playerListByTeamName(@RequestParam("teamName") String teamName) {
		try {
			List<Player> player = baseballService.playerListInTeamByTeamName(teamName);
			return new ResponseEntity<List<Player>>(player, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Player>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
